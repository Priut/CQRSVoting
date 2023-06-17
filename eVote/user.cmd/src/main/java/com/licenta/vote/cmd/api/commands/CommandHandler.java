package com.licenta.vote.cmd.api.commands;


import com.licenta.vote.cmd.api.commands.updateCommands.*;

public interface CommandHandler {
    void handle(CreateUserCommand command);
    void handle(DeleteUserCommand command);
    void handle(UpdateEmailCommand command);
    void handle(UpdatePhoneNumberCommand command);
    void handle(UpdateWorkPlaceCommand command);
    void handle(UpdateCountryCommand command);
    void handle(UpdateCountyCommand command);
    void handle(UpdateCityCommand command);
    void handle(RestoreUserReadDbCommand command);
}
