package com.licenta.vote.cmd.domain;

import com.licenta.cqrs.core.domain.AggregateRoot;
import com.licenta.vote.CustomExceptions.*;
import com.licenta.vote.cmd.api.commands.CreateVotingEventCommand;
import com.licenta.vote.common.events.*;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@NoArgsConstructor
public class VotingEventAggregate extends AggregateRoot {
    private Boolean active;
    private HashMap<String, Integer> votes = new HashMap<>();
    private ArrayList<String> candidates = new ArrayList<>();
    private LocalDate startDate;
    private LocalDate endDate;
    private double attendance;
    private String winner;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public Boolean getActive(){
        return active;
    }
    public VotingEventAggregate(CreateVotingEventCommand command){
        var id = command.getId();
        votes = new HashMap<>();
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
        this.attendance = 0.0;
        startDate = LocalDate.parse(event.getStart_date(), formatter);
        endDate = LocalDate.parse(event.getFinish_date(), formatter);
    }
    public void calculateResult(){
        if(!this.active)
            throw new VotingEventDeletedException("Results cannot be calculated for a deleted voting event!");
        if (LocalDate.now().isBefore(endDate)){
            throw new ResultsDateException("Results can only be calculated after the event has ended!");
        }
        Map<String, Integer> voteCounts = new HashMap<>();
        for (String candidate : candidates) {
            voteCounts.put(candidate, 0);
        }

        String winner = null;
        int maxVotes = 0;
        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
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
            throw new InvalidVotingDateException("Voting date is outside of the voting event's start and finish dates!");
        }
        if (!candidates.contains(option)) {
            throw new InvalidCandidateOptionException("Invalid candidate option!");
        }
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5004/api/v1/inviteCollection/" + id_invite + "/status"))
                .method("PUT", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response1 = httpClient.send(request1, HttpResponse.BodyHandlers.ofString());

            if (response1.statusCode() == 204) {
                    raiseEvent(VoteRegisteredEvent.builder()
                            .id(id)
                            .id_invite(id_invite)
                            .option(option)
                            .build());
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
        if(this.votes.containsKey(event.getOption())) {
            votes.put(event.getOption(), votes.get(event.getOption()) + 1);
        } else {
            votes.put(event.getOption(), 1);
        }
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
    public void calculateAttendance() {
        if (!this.active) {
            throw new VotingEventDeletedException("Attendance cannot be calculated for a deleted voting event!");
        }
        if (LocalDate.now().isBefore(startDate)) {
            throw new ResultsDateException("Attendance can only be calculated after the event has started!");
        }
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5005/api/v1/invitesLookup/?id=" + id))
                .GET()
                .build();

        try {
            HttpResponse<String> response1 = httpClient.send(request1, HttpResponse.BodyHandlers.ofString());

            if (response1.statusCode() == 200) {
                String responseBody = response1.body();

                JSONObject jsonObject = new JSONObject(responseBody);
                JSONArray invitesArray = jsonObject.getJSONArray("invites");

                int totalInvites = invitesArray.length();
                int attendedInvites = 0;

                for (int i = 0; i < totalInvites; i++) {
                    JSONObject inviteObject = invitesArray.getJSONObject(i);
                    boolean status = inviteObject.getBoolean("status");
                    if (status) {
                        attendedInvites++;
                    }
                }

                double attendance = (double) attendedInvites / totalInvites * 100;
                double roundedAttendance = Math.round(attendance * 100.0) / 100.0;

                raiseEvent(AttendanceCalculatedEvent.builder()
                        .id(this.id)
                        .attendance(roundedAttendance)
                        .build());
            } else {
                throw new InviteNotFoundException("Invites not found for this voting event!");
            }
        } catch (IOException | InterruptedException | JSONException e) {
            throw new AttendanceCalculationException("Error while calculating attendance vote: " + e.getMessage());
        }
    }
    public void apply(AttendanceCalculatedEvent event){
        this.id = event.getId();
        this.active = true;
        this.attendance = event.getAttendance();
    }
}
