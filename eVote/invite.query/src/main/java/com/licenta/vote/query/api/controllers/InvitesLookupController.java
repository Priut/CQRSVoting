package com.licenta.vote.query.api.controllers;

import com.licenta.cqrs.core.inrastructure.QueryDispacher;
import com.licenta.vote.query.api.dto.InvitesLookupResponse;
import com.licenta.vote.query.api.queries.FindAllInvitesQuery;
import com.licenta.vote.query.api.queries.FindInvitesByEventIdQuery;
import com.licenta.vote.query.domain.Invite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/invitesLookup")
public class InvitesLookupController {

    @Autowired
    private QueryDispacher queryDispacher;

    @GetMapping(path = "/")
    public ResponseEntity<InvitesLookupResponse> getEvents(@RequestParam(value = "id", required = false) String id) throws Exception {
        List<Invite> invites;

        if (id != null) {
            invites = queryDispacher.send(new FindInvitesByEventIdQuery(id));
        } else {
            invites = queryDispacher.send(new FindAllInvitesQuery());
        }

        if (invites == null || invites.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        var response = InvitesLookupResponse.builder()
                .invites(invites)
                .message(String.format("Successfully returned %d invite(s)!", invites.size()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
