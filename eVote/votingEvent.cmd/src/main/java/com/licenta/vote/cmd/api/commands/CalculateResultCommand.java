package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.commands.BaseCommand;
import lombok.Data;

public class CalculateResultCommand extends BaseCommand {
    public CalculateResultCommand(String id){
        super(id);
    }
}
