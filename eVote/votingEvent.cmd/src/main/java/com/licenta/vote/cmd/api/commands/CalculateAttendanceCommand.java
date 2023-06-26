package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.commands.BaseCommand;

public class CalculateAttendanceCommand extends BaseCommand {
    public CalculateAttendanceCommand(String id){
        super(id);
    }
}
