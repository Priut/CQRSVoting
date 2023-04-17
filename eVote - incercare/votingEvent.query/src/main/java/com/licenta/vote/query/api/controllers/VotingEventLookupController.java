package com.licenta.vote.query.api.controllers;

import com.licenta.cqrs.core.inrastructure.QueryDispacher;
import com.licenta.vote.query.api.dto.VotingEventLookupResponse;
import com.licenta.vote.query.api.queries.FindAllEventsQuery;
import com.licenta.vote.query.api.queries.FindEventByIdQuery;
import com.licenta.vote.query.domain.VotingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/votingEventsLookup")
public class VotingEventLookupController {
    private final Logger logger = Logger.getLogger(VotingEventLookupController.class.getName());

    @Autowired
    private QueryDispacher queryDispacher;


    @GetMapping(path = "/")
    public ResponseEntity<VotingEventLookupResponse> getEvents( @RequestParam(value = "id", required = false) String id) {

        List<VotingEvent> votingEvents = null;

        if (id != null) {
            votingEvents = queryDispacher.send(new FindEventByIdQuery(id));
        } else {
            votingEvents = queryDispacher.send(new FindAllEventsQuery());
        }

        if (votingEvents == null || votingEvents.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        var response = VotingEventLookupResponse.builder()
                .votingEvents(votingEvents)
                .message(MessageFormat.format("Successfully returned {0} event(s)!", votingEvents.size()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
