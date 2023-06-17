package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.commands.BaseCommand;
import lombok.Data;

import java.util.ArrayList;
@Data
public class RegisterVoteCommand extends BaseCommand {
    private String id_invite;
    private String option;
}
