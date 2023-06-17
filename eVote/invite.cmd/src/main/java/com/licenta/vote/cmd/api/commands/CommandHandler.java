package com.licenta.vote.cmd.api.commands;



public interface CommandHandler {
    void handle(InviteUserCommand command);
    void handle(DeleteInviteCommand command);
    void handle(InviteUsersByCountryCommand command);
    void handle(InviteUsersByCountyCommand command);
    void handle(InviteUsersByWorkplaceCommand command);
    void handle(InviteUsersByCityCommand command);
    void handle(ChangeStatusCommand command);
}
