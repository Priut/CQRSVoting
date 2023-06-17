package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.handlers.EventSourcingHandler;
import com.licenta.vote.cmd.api.commands.updateCommands.*;
import com.licenta.vote.cmd.domain.UserAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserCommandHandler implements CommandHandler {

    @Autowired
    private EventSourcingHandler<UserAggregate> eventSourcingHandler;

    @Override
    public void handle(CreateUserCommand command) {
        var aggregate = new UserAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(DeleteUserCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.deleteUser();
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(UpdateEmailCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.changeEmail(command.getEmail());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(UpdatePhoneNumberCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.changePhoneNumber(command.getPhoneNumber());
        eventSourcingHandler.save(aggregate);
    }
    @Override
    public void handle(UpdateWorkPlaceCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.changeWorkPlace(command.getWorkPlace());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(UpdateCountryCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.changeCountry(command.getCountry());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(UpdateCountyCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.changeCounty(command.getCounty());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(UpdateCityCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.changeCity(command.getCity());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(RestoreUserReadDbCommand command) {
        eventSourcingHandler.republishEvents();
    }
}
