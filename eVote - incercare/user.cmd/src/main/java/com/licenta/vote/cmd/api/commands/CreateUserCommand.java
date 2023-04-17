package com.licenta.vote.cmd.api.commands;

import com.licenta.cqrs.core.commands.BaseCommand;
import lombok.Data;
@Data
public class CreateUserCommand extends BaseCommand {
    private String name;
    private String email;
    private String phoneNumber;
    private String birthDate;
    private String country;
    private String county;
    private String city;
    private String workPlace;
}
