package com.licenta.vote.cmd.api.commands.updateCommands;

import com.licenta.cqrs.core.commands.BaseCommand;
import lombok.Data;
@Data
public class UpdateCountyCommand extends BaseCommand {
    private String county;
}