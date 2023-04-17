package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.commands.BaseCommand;


public class DeleteUserCommand extends BaseCommand {
    public DeleteUserCommand(String id){
        super(id);
    }
}
