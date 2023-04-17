package com.licenta.vote.cmd.api.commands.updateCommands;

import com.licenta.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class UpdateWorkPlaceCommand extends BaseCommand {
    private String workPlace;
}