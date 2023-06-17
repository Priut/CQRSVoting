package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.commands.BaseCommand;

public class ChangeStatusCommand extends BaseCommand {
    public ChangeStatusCommand(String id){
        super(id);
    }
}
