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
public class VotingEventCommandApplication {

	@Autowired
	private CommandDispacher commandDispacher;
	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(VotingEventCommandApplication.class, args);
	}
	@PostConstruct
	public void registerHandlers(){

		commandDispacher.registerHandler(CreateVotingEventCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(CalculateResultCommand.class, commandHandler::handle);
		commandDispacher.registerHandler(RegisterVoteCommand.class, commandHandler::handle);

	}

}
