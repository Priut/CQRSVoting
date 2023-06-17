package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class InviteUsersByCityCommand extends BaseCommand {
    private String id_votingEvent;
    private String city;
}
