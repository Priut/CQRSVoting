package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class InviteUsersByWorkplaceCommand extends BaseCommand {
    private String id_votingEvent;
    private String workplace;
}
