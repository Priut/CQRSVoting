package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.handlers.EventSourcingHandler;
import com.licenta.vote.cmd.domain.VotingEventAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VotingEventCommandHandler implements CommandHandler {

    @Autowired
    private EventSourcingHandler<VotingEventAggregate> eventSourcingHandler;

    @Override
    public void handle(CreateVotingEventCommand command) {
        var aggregate = new VotingEventAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(CalculateResultCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.calculateResult();
        eventSourcingHandler.save(aggregate);
    }
    @Override
    public void handle(RegisterVoteCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.registerVote(command.getOption(),command.getId_invite());
        eventSourcingHandler.save(aggregate);
    }


}