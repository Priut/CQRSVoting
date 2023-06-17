package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.commands.BaseCommand;
public class DeleteInviteCommand extends BaseCommand {
    public DeleteInviteCommand(String id){
        super(id);
    }
}