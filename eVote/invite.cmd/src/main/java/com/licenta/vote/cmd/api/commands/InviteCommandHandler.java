package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.handlers.EventSourcingHandler;
import com.licenta.vote.CustomExceptions.UserNotFoundException;
import com.licenta.vote.CustomExceptions.VotingEventNotFoundException;
import com.licenta.vote.cmd.domain.EventStoreRepository;
import com.licenta.vote.cmd.domain.InviteAggregate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class InviteCommandHandler implements CommandHandler {

    @Autowired
    private EventSourcingHandler<InviteAggregate> eventSourcingHandler;

    @Autowired
    private EventStoreRepository eventStoreRepository;
    @Override
    public void handle(InviteUserCommand command) {
        var aggregate = new InviteAggregate(command);
        eventStoreRepository.verifyExistingInvite(command.getId_user(), command.getId_votingEvent(), "invitesEventStore");
        eventSourcingHandler.save(aggregate);
    }
    @Override
    public void handle(ChangeStatusCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.changeStatus();
        eventSourcingHandler.save(aggregate);
    }
    @Override
    public void handle(DeleteInviteCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.deleteInvite();
        eventSourcingHandler.save(aggregate);
    }
    @Override
    public void handle(InviteUsersByCountryCommand command) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String id_event = getEvent(command.getId_votingEvent());
        ArrayList<String> userIds = getUserswithQuery("country", command.getCountry());

        if(!Objects.equals(id_event, "") & userIds.size() != 0){
            for (String uId:
                    userIds) {
                    var invComm = new InviteUserCommand(uId,id_event);
                    String id = UUID.randomUUID().toString();
                    invComm.setId(id);
                    handle(invComm);
            }
        }
    }
    @Override
    public void handle(InviteUsersByCountyCommand command) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String id_event =getEvent(command.getId_votingEvent());
        ArrayList<String> userIds = getUserswithQuery("county", command.getCounty());

        if(!Objects.equals(id_event, "") & userIds.size() != 0){
            for (String uId:
                    userIds) {
                var invComm = new InviteUserCommand(uId,id_event);
                String id = UUID.randomUUID().toString();
                invComm.setId(id);
                handle(invComm);
            }
        }
    }
    @Override
    public void handle(InviteUsersByWorkplaceCommand command) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String id_event = getEvent(command.getId_votingEvent());
        ArrayList<String> userIds = getUserswithQuery("workplace", command.getWorkplace());

        if(!Objects.equals(id_event, "") & userIds.size() != 0){
            for (String uId:
                    userIds) {
                var invComm = new InviteUserCommand(uId,id_event);
                String id = UUID.randomUUID().toString();
                invComm.setId(id);
                handle(invComm);
            }
        }
    }
    @Override
    public void handle(InviteUsersByCityCommand command) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String id_event =getEvent(command.getId_votingEvent());
        ArrayList<String> userIds = getUserswithQuery("city", command.getCity());

        if(!Objects.equals(id_event, "") & userIds.size() != 0){
            for (String uId:
                    userIds) {
                var invComm = new InviteUserCommand(uId,id_event);
                String id = UUID.randomUUID().toString();
                invComm.setId(id);
                handle(invComm);
            }
        }
    }
    public ArrayList<String> getUserswithQuery(String query, String value){
        ArrayList<String> userIds = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5001/api/v1/usersLookup/?"+query+"=" + value))
                .GET()
                .build();
        try {
            HttpResponse<String> response1 = httpClient.send(request1, HttpResponse.BodyHandlers.ofString());
            if (response1.statusCode() == 200) {
                String usersResponse = response1.body();
                JSONObject responseJson = new JSONObject(response1.body());
                JSONArray usersArray = responseJson.getJSONArray("users");
                for (int i = 0; i < usersArray.length(); i++) {
                    JSONObject userJson = usersArray.getJSONObject(i);
                    String userId = userJson.getString("id");
                    userIds.add(userId);
                }
            } else {
                throw new UserNotFoundException("User not found for this "+query+": " + value);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Logger.getLogger(InviteCommandHandler.class.getName()).log(Level.WARNING, Arrays.toString(e.getStackTrace()));
        }
        return userIds;
    }
    public String getEvent(String id_event){
        HttpClient httpClient = HttpClient.newHttpClient();
        String validId ="";
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5003/api/v1/votingEventsLookup/?id=" + id_event))
                .GET()
                .build();
        try {
            HttpResponse<String> response2 = httpClient.send(request2, HttpResponse.BodyHandlers.ofString());
            if (response2.statusCode() == 200) {
                validId = id_event;
            } else {
                throw new VotingEventNotFoundException("Voting event not found: " + id_event);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Logger.getLogger(InviteCommandHandler.class.getName()).log(Level.WARNING, Arrays.toString(e.getStackTrace()));
        }
        return validId;
    }

}