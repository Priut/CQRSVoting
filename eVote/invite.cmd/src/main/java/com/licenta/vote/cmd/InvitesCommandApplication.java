package com.licenta.vote.cmd;

import com.licenta.cqrs.core.inrastructure.CommandDispacher;
import com.licenta.vote.cmd.api.commands.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.licenta.vote")
public class InvitesCommandApplication {

	@Autowired
	private CommandDispacher commandDispacher;
	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(InvitesCommandApplication.class, args);
	}
	@PostConstruct
	public void registerHandlers(){

		commandDispacher.registerHandler(InviteUserCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(DeleteInviteCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(InviteUsersByCountryCommand.class,commandHandler::handle);
		commandDispacher.registerHandler(InviteUsersByCountyCommand.class,commandHandler::handle);
		commandDispacher.registerHandler(InviteUsersByWorkplaceCommand.class,commandHandler::handle);
		commandDispacher.registerHandler(InviteUsersByCityCommand.class,commandHandler::handle);
		commandDispacher.registerHandler(ChangeStatusCommand.class,commandHandler::handle);

	}

}
