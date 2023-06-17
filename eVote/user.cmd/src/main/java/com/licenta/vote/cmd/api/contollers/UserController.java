package com.licenta.vote.cmd.api.contollers;

import com.licenta.cqrs.core.inrastructure.CommandDispacher;
import com.licenta.vote.cmd.api.commands.*;
import com.licenta.vote.cmd.api.commands.updateCommands.*;
import com.licenta.vote.cmd.api.dtos.CreateUserResponse;
import com.licenta.vote.common.dtos.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.Logger;


@RestController
@RequestMapping(path = "/api/v1/userCollection")
public class UserController {
    private final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private CommandDispacher commandDispacher;

    @PostMapping
    public ResponseEntity<BaseResponse> createUser(@RequestBody CreateUserCommand command){
        String id = UUID.randomUUID().toString();
        command.setId(id);
        commandDispacher.send(command);
        return new ResponseEntity<>(new CreateUserResponse("User creation request completed successfully!", id), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}/email")
    private ResponseEntity<BaseResponse> changeEmail(@PathVariable(value = "id")String id,
                                                     @RequestBody UpdateEmailCommand command){
        command.setId(id);
        commandDispacher.send(command);
        return new ResponseEntity<>(new BaseResponse("Email change request completed successfully!"), HttpStatus.OK);
    }
    @PutMapping(path = "/{id}/phoneNumber")
    private ResponseEntity<BaseResponse> changePhoneNumber(@PathVariable(value = "id")String id,
                                                           @RequestBody UpdatePhoneNumberCommand command){

        command.setId(id);
        commandDispacher.send(command);
        return new ResponseEntity<>(new BaseResponse("Phone number change request completed succesfuly!"), HttpStatus.OK);

    }
    @PutMapping(path = "/{id}/country")
    private ResponseEntity<BaseResponse> changeCountry(@PathVariable(value = "id")String id,
                                                           @RequestBody UpdateCountryCommand command){
        command.setId(id);
        commandDispacher.send(command);
        return new ResponseEntity<>(new BaseResponse("Country change request completed succesfuly!"), HttpStatus.OK);
    }
    @PutMapping(path = "/{id}/county")
    private ResponseEntity<BaseResponse> changeCounty(@PathVariable(value = "id")String id,
                                                       @RequestBody UpdateCountyCommand command){
        command.setId(id);
        commandDispacher.send(command);
        return new ResponseEntity<>(new BaseResponse("County change request completed succesfuly!"), HttpStatus.OK);
    }
    @PutMapping(path = "/{id}/city")
    private ResponseEntity<BaseResponse> changeCity(@PathVariable(value = "id")String id,
                                                    @RequestBody UpdateCityCommand command){
        command.setId(id);
        commandDispacher.send(command);
        return new ResponseEntity<>(new BaseResponse("City change request completed succesfuly!"), HttpStatus.OK);
    }
    @PutMapping(path = "/{id}/workPlace")
    private ResponseEntity<BaseResponse> changeWorkPlace(@PathVariable(value = "id")String id,
                                                    @RequestBody UpdateWorkPlaceCommand command){

        command.setId(id);
        commandDispacher.send(command);
        return new ResponseEntity<>(new BaseResponse("Work place change request completed succesfuly!"), HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable(value = "id")String id){

        commandDispacher.send(new DeleteUserCommand(id));
        return new ResponseEntity<>(new BaseResponse("User deleted request completed succesfuly!"), HttpStatus.OK);
    }

    @PostMapping(path = "/restoreDb")
    public ResponseEntity<BaseResponse> restoreReadDb(){
        commandDispacher.send(new RestoreUserReadDbCommand());
        return new ResponseEntity<>(new BaseResponse("Read restore read db request completed succesfuly!"), HttpStatus.CREATED);
    }
}
