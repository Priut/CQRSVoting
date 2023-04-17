package com.licenta.vote.cmd;

import com.licenta.cqrs.core.inrastructure.CommandDispacher;
import com.licenta.vote.cmd.api.commands.*;
import com.licenta.vote.cmd.api.commands.updateCommands.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.licenta.vote")
public class UserCommandApplication {

	@Autowired
	private CommandDispacher commandDispacher;
	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(UserCommandApplication.class, args);
	}
	@PostConstruct
	public void registerHandlers(){

		commandDispacher.registerHandler(CreateUserCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(UpdateEmailCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(UpdatePhoneNumberCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(DeleteUserCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(UpdateWorkPlaceCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(UpdateCountryCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(UpdateCountyCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(UpdateCityCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(RestoreUserReadDbCommand.class, commandHandler::handle);

	}

}
