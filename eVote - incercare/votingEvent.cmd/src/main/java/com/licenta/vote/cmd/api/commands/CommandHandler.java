package com.licenta.vote.cmd.api.commands;



public interface CommandHandler {
    void handle(CreateVotingEventCommand command);
    void handle(CalculateResultCommand command);
    void handle(RegisterVoteCommand command);
}
