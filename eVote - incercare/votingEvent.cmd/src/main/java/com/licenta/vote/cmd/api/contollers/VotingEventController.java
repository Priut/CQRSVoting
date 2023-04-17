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
    private final Logger logger = Logger.getLogger(VotingEventController.class.getName());

    @Autowired
    private CommandDispacher commandDispacher;

    @PostMapping
    public ResponseEntity<BaseResponse> createVotingEvent(@RequestBody CreateVotingEventCommand command){
            String id = UUID.randomUUID().toString();
            command.setId(id);
            commandDispacher.send(command);
            return new ResponseEntity<>(new CreateVotingEventResponse("Voting event creation request completed succesfuly!", id), HttpStatus.CREATED);
    }

    @PutMapping(path = "/results/{id}")
    public ResponseEntity<BaseResponse> calculateResult(@PathVariable(value = "id")String id,@RequestBody CalculateResultCommand command){

        command.setId(id);
        commandDispacher.send(command);
        return new ResponseEntity<>(new CreateVotingEventResponse("Results calculation request completed succesfuly!", id), HttpStatus.CREATED);
    }
    @PostMapping(path = "/vote/{id_event}")
    public ResponseEntity<BaseResponse> registerVote(@PathVariable(value = "id_event")String id_event,@RequestBody RegisterVoteCommand command){

        command.setId(id_event);
        commandDispacher.send(command);
        return new ResponseEntity<>(new CreateVotingEventResponse("Vote registered succesfuly!", id_event), HttpStatus.CREATED);
    }
}
