package com.licenta.vote.query.api.controllers;

import com.licenta.cqrs.core.inrastructure.QueryDispacher;
import com.licenta.vote.CustomExceptions.InvalidCandidateOptionException;
import com.licenta.vote.CustomExceptions.InviteNotFoundException;
import com.licenta.vote.CustomExceptions.InviteUsedException;
import com.licenta.vote.CustomExceptions.VoteRegistrationException;
import com.licenta.vote.common.events.VoteRegisteredEvent;
import com.licenta.vote.query.api.dto.VotingEventLookupResponse;
import com.licenta.vote.query.api.queries.FindAllEventsQuery;
import com.licenta.vote.query.api.queries.FindEventByIdQuery;
import com.licenta.vote.query.domain.VotingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/votingEventsLookup")
public class VotingEventLookupController {

    @Autowired
    private QueryDispacher queryDispacher;


    @GetMapping(path = "/")
    public ResponseEntity<VotingEventLookupResponse> getEvents( @RequestParam(value = "id", required = false) String id) {

        List<VotingEvent> votingEvents = null;

        if (id != null) {
            votingEvents = queryDispacher.send(new FindEventByIdQuery(id));
            if (votingEvents == null || votingEvents.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            votingEvents = queryDispacher.send(new FindAllEventsQuery());
        }

        var response = VotingEventLookupResponse.builder()
                .votingEvents(votingEvents)
                .message(MessageFormat.format("Successfully returned {0} event(s)!", votingEvents.size()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
