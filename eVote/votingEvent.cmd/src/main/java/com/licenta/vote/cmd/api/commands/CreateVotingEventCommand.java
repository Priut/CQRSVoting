package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.commands.BaseCommand;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CreateVotingEventCommand extends BaseCommand {
    private String name;
    private String start_date;
    private String finish_date;
    private ArrayList<String> candidates;
}
