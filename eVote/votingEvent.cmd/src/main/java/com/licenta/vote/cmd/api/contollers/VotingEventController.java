package com.licenta.vote.cmd.api.contollers;

import com.licenta.cqrs.core.inrastructure.CommandDispacher;
import com.licenta.vote.cmd.api.commands.*;
import com.licenta.vote.cmd.api.dtos.CreateVotingEventResponse;
import com.licenta.vote.common.dtos.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.Logger;


@RestController
@RequestMapping(path = "/api/v1/votingEventCollection")
public class VotingEventController {

    @Autowired
    private CommandDispacher commandDispacher;

    @PostMapping
    public ResponseEntity<BaseResponse> createVotingEvent(@RequestBody CreateVotingEventCommand command){
            String id = UUID.randomUUID().toString();
            command.setId(id);
            commandDispacher.send(command);
            return new ResponseEntity<>(new CreateVotingEventResponse("Voting event creation request completed succesfuly!", id), HttpStatus.CREATED);
    }
    @PostMapping(path = "/{id}/attendance")
    public ResponseEntity<BaseResponse> calcuateAttendance(@PathVariable(value = "id")String id){

        commandDispacher.send(new CalculateAttendanceCommand(id));
        return new ResponseEntity<>(new CreateVotingEventResponse("Vote attendance calculated succesfuly!", id), HttpStatus.OK);
    }
    @PostMapping(path = "/{id}/results")
    public ResponseEntity<BaseResponse> calculateResult(@PathVariable(value = "id")String id){

        commandDispacher.send(new CalculateResultCommand(id));
        return new ResponseEntity<>(new CreateVotingEventResponse("Results calculation request completed succesfuly!", id), HttpStatus.OK);
    }
    @PostMapping(path = "/{id_event}/vote")
    public ResponseEntity<BaseResponse> registerVote(@PathVariable(value = "id_event")String id_event,@RequestBody RegisterVoteCommand command){

        command.setId(id_event);
        commandDispacher.send(command);
        return new ResponseEntity<>(new CreateVotingEventResponse("Vote registered succesfuly!", id_event), HttpStatus.OK);
    }
    @PostMapping(path = "/{id_event}/finishdate")
    public ResponseEntity<BaseResponse> modifyFinishDate(@PathVariable(value = "id_event")String id_event,@RequestBody ModifyFinishDateCommand command){

        command.setId(id_event);
        commandDispacher.send(command);
        return new ResponseEntity<>(new CreateVotingEventResponse("Vote finish date modified succesfuly!", id_event), HttpStatus.OK);
    }

}
