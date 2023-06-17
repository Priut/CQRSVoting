package com.licenta.vote.cmd.domain;

import com.licenta.cqrs.core.domain.AggregateRoot;
import com.licenta.vote.CustomExceptions.*;
import com.licenta.vote.cmd.api.commands.InviteUserCommand;
import com.licenta.vote.cmd.api.commands.InviteUsersByCountryCommand;
import com.licenta.vote.common.events.InviteDeletedEvent;
import com.licenta.vote.common.events.StatusChangedEvent;
import com.licenta.vote.common.events.UserInvitedEvent;
import lombok.NoArgsConstructor;
import org.bson.json.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@NoArgsConstructor
public class InviteAggregate extends AggregateRoot {
    private Boolean active;
    private Boolean status;
    private final Logger logger = Logger.getLogger(InviteAggregate.class.getName());
    public Boolean getActive(){
        return active;
    }
    public Boolean getStatus(){return status;}
    public void setStatus(Boolean s){status = s;}
    public InviteAggregate(InviteUserCommand command) {
        var id = command.getId();
        String id_user = "";
        String id_event = "";

        HttpClient httpClient = HttpClient.newHttpClient();
//TODO de inlocuit cand urc in docker cu user-query si votingEvent-query
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5001/api/v1/usersLookup/?id=" + command.getId_user()))
                .GET()
                .build();
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5003/api/v1/votingEventsLookup/?id=" + command.getId_votingEvent()))
                .GET()
                .build();

        try {
            HttpResponse<String> response1 = httpClient.send(request1, HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> response2 = httpClient.send(request2, HttpResponse.BodyHandlers.ofString());

            if (response1.statusCode() == 200) {
                id_user = command.getId_user();
            } else {
                throw new UserNotFoundException("User not found: " + command.getId_user());
            }

            if (response2.statusCode() == 200) {
                JSONObject responseJson = new JSONObject(response2.body());
                JSONArray votingEvents = responseJson.getJSONArray("votingEvents");
                JSONObject votingEvent = votingEvents.getJSONObject(0);
                String finishDateStr = votingEvent.getString("finish_date");
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date finishDate = dateFormat.parse(finishDateStr);
                Date currentDate = new Date();
                if (currentDate.before(finishDate)) {
                    id_event = command.getId_votingEvent();
                } else {
                    throw new VotingEventExpiredException("Voting event has ended: " + command.getId_votingEvent());
                }
            } else {
                throw new VotingEventNotFoundException("Voting event not found: " + command.getId_votingEvent());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, Arrays.toString(e.getStackTrace()));
        } catch (ParseException e) {
            throw new DateFormatIncorrectException("Cannot parse string to date: "+ Arrays.toString(e.getStackTrace()));
        }
        if (!Objects.equals(id_user, "") && !Objects.equals(id_event, ""))
            raiseEvent(UserInvitedEvent.builder()
                    .id(id)
                    .id_user(command.getId_user())
                    .id_votingEvent(command.getId_votingEvent())
                    .build());
    }
    public void apply(UserInvitedEvent event){
        this.id = event.getId();
        active = true;
        status = false;
    }
    public void deleteInvite() {
        if (!this.active) {
            throw new InviteDeletedException("Invite was deleted already!");
        }

        raiseEvent(InviteDeletedEvent.builder()
                .id(this.id)
                .build());
    }

    public void apply(InviteDeletedEvent event){
        this.id = event.getId();
        this.active = false;
    }
    public void changeStatus() {
        if (!this.active) {
            throw new InviteDeletedException("Invite was deleted!");
        }
        if (this.status) {
            throw new InviteUsedException("Invite was already used!");
        }

        raiseEvent(StatusChangedEvent.builder()
                .id(this.id)
                .build());
    }
    public void apply(StatusChangedEvent event){
        this.id = event.getId();
        this.status = true;
    }
}
