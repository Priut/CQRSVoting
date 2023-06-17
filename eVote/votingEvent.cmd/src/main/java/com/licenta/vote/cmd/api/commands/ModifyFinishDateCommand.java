package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class ModifyFinishDateCommand  extends BaseCommand {
    private String finish_date;
}