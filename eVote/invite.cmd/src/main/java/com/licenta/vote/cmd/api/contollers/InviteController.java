package com.licenta.vote.cmd.api.contollers;

import com.licenta.cqrs.core.inrastructure.CommandDispacher;
import com.licenta.vote.cmd.api.commands.*;
import com.licenta.vote.cmd.api.dtos.UserInvitedResponse;
import com.licenta.vote.common.dtos.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/inviteCollection")
public class InviteController {

    @Autowired
    private CommandDispacher commandDispacher;

    @PostMapping
    public ResponseEntity<BaseResponse> createInvite(@RequestBody InviteUserCommand command) throws Exception {
        String id = UUID.randomUUID().toString();
        command.setId(id);
        commandDispacher.send(command);
        return new ResponseEntity<>(new UserInvitedResponse("Invite creation request completed successfully!", id), HttpStatus.CREATED);
    }
    @PostMapping(path = "/country")
    public ResponseEntity<BaseResponse> inviteByCountry(@RequestBody InviteUsersByCountryCommand command) throws Exception {
        commandDispacher.send(command);
        return new ResponseEntity<>(new BaseResponse("Invite creation request completed successfully!"), HttpStatus.CREATED);
    }
    @PostMapping(path = "/county")
    public ResponseEntity<BaseResponse> inviteByCounty(@RequestBody InviteUsersByCountyCommand command) throws Exception {
        commandDispacher.send(command);
        return new ResponseEntity<>(new BaseResponse("Invite creation request completed successfully!"), HttpStatus.CREATED);
    }
    @PostMapping(path = "/city")
    public ResponseEntity<BaseResponse> inviteByCity(@RequestBody InviteUsersByCityCommand command) throws Exception {
        commandDispacher.send(command);
        return new ResponseEntity<>(new BaseResponse("Invite creation request completed successfully!"), HttpStatus.CREATED);
    }
    @PostMapping(path = "/workplace")
    public ResponseEntity<BaseResponse> inviteByWorkplace(@RequestBody InviteUsersByWorkplaceCommand command) throws Exception {
        commandDispacher.send(command);
        return new ResponseEntity<>(new BaseResponse("Invite creation request completed successfully!"), HttpStatus.CREATED);
    }

    //@CrossOrigin(origins = "http://localhost:5002")
    //TODO
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> deleteInvite(@PathVariable String id) throws Exception {
        commandDispacher.send(new DeleteInviteCommand(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(path = "/status/{id}")
    public ResponseEntity<BaseResponse> changeStatus(@PathVariable String id) throws Exception {
        commandDispacher.send(new ChangeStatusCommand(id));
        return new ResponseEntity<>(new BaseResponse("Status changed successfully!"), HttpStatus.OK);
    }
}
