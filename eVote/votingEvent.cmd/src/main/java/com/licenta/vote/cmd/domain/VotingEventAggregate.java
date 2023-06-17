package com.licenta.vote.cmd.domain;

import com.licenta.cqrs.core.domain.AggregateRoot;
import com.licenta.vote.CustomExceptions.*;
import com.licenta.vote.cmd.api.commands.CreateVotingEventCommand;
import com.licenta.vote.common.events.FinishDateModifiedEvent;
import com.licenta.vote.common.events.ResultsCalculatedEvent;
import com.licenta.vote.common.events.VoteRegisteredEvent;
import com.licenta.vote.common.events.VotingEventCreatedEvent;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.logging.Logger;

@NoArgsConstructor
public class VotingEventAggregate extends AggregateRoot {
    private Boolean active;
    private ArrayList<Integer> votes = new ArrayList<>();
    private ArrayList<String> candidates = new ArrayList<>();
    private LocalDate startDate;
    private LocalDate endDate;
    private String winner;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final Logger logger = Logger.getLogger(VotingEventAggregate.class.getName());
    public Boolean getActive(){
        return active;
    }
    public VotingEventAggregate(CreateVotingEventCommand command){
        var id = command.getId();
        votes = new ArrayList<>();
        candidates = new ArrayList<>();
        try{
            LocalDate date1 = LocalDate.parse(command.getStart_date(), formatter);
            LocalDate date2 = LocalDate.parse(command.getFinish_date(), formatter);
        } catch (DateTimeParseException e) {
            throw new DateFormatIncorrectException("Error parsing the date strings: " + e.getMessage());

        }

        raiseEvent(VotingEventCreatedEvent.builder()
                .id(id)
                .name(command.getName())
                .start_date(command.getStart_date())
                .finish_date(command.getFinish_date())
                .candidates(command.getCandidates())
                .build());

    }
    public void apply(VotingEventCreatedEvent event){
        this.id = event.getId();
        this.candidates = event.getCandidates();
        this.active = true;
        startDate = LocalDate.parse(event.getStart_date(), formatter);
        endDate = LocalDate.parse(event.getFinish_date(), formatter);
    }
    public void calculateResult(){
        if(!this.active)
            throw new VotingEventDeletedException("Results cannot be calculated for a deleted voting event!");
        if (LocalDate.now().isBefore(endDate)){
            throw new ResultsDateException("Results can only be calculated after the event has ended");
        }
        Map<String, Integer> voteCounts = new HashMap<>();
        for (String candidate : candidates) {
            voteCounts.put(candidate, 0);
        }

        for (int vote : votes) {
            String candidate = candidates.get(vote);
            voteCounts.put(candidate, voteCounts.get(candidate) + 1);
        }

        String winner = null;
        int maxVotes = 0;
        for (Map.Entry<String, Integer> entry : voteCounts.entrySet()) {
            if (entry.getValue() > maxVotes) {
                winner = entry.getKey();
                maxVotes = entry.getValue();
            }
        }
        raiseEvent(ResultsCalculatedEvent.builder()
                .id(this.id)
                .winner(winner)
                .build());

    }
    public void apply(ResultsCalculatedEvent event){
        this.id = event.getId();
        this.active = true;
        this.winner = event.getWinner();
    }

    public void registerVote(String option, String id_invite) {
        if (!this.active) {
            throw new VotingEventDeletedException("Vote cannot be registered for a deleted voting event!");
        }

        if (LocalDate.now().isBefore(startDate) || LocalDate.now().isAfter(endDate)) {
            throw new InvalidVotingDateException("Voting date is outside of the voting event's start and finish dates");
        }
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5004/api/v1/inviteCollection/status/" + id_invite))
                .method("PUT", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response1 = httpClient.send(request1, HttpResponse.BodyHandlers.ofString());

            if (response1.statusCode() == 200) {
                if (candidates.contains(option)) {
                    raiseEvent(VoteRegisteredEvent.builder()
                            .id(id)
                            .id_invite(id_invite)
                            .option(option)
                            .build());
                } else {
                    throw new InvalidCandidateOptionException("Invalid candidate option!");
                }
            } else if(response1.statusCode() == 409){
                throw new InviteUsedException("Invite already used!");
            }else{
                throw new InviteNotFoundException("Invite not found!");
            }
        } catch (IOException | InterruptedException e) {
            throw new VoteRegistrationException("Error while registering vote: " + e.getMessage());
        }
    }

    public void apply(VoteRegisteredEvent event){
        this.id = event.getId();
        this.active = true;
        this.votes.add(candidates.indexOf(event.getOption()));
    }
    public void modifyFinishDate(String finish_date) {
        if (!this.active) {
            throw new VotingEventDeletedException("Date cannot be modified for a deleted voting event!");
        }
        try{

            var date = LocalDate.parse(finish_date, formatter);
        } catch (DateTimeParseException e) {
            throw new DateFormatIncorrectException("Error parsing the date strings: " + e.getMessage());
        }
        raiseEvent(FinishDateModifiedEvent.builder().id(id).finish_date(finish_date).build());
    }
    public void apply(FinishDateModifiedEvent event){
        this.id = event.getId();
        this.active = true;
        this.endDate = LocalDate.parse(event.getFinish_date(), formatter);
    }
}
