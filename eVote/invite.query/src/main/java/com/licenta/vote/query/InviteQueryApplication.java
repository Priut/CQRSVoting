package com.licenta.vote.query;

import com.licenta.cqrs.core.inrastructure.QueryDispacher;
import com.licenta.vote.query.api.queries.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.licenta.vote")
public class InviteQueryApplication {
	@Autowired
	private QueryDispacher queryDispacher;

	@Autowired
	private QueryHandler queryHandler;
	public static void main(String[] args) {
		SpringApplication.run(InviteQueryApplication.class, args);
	}
	@PostConstruct
	public void registerHandlers(){
		queryDispacher.registerHandler(FindAllInvitesQuery.class, queryHandler::handle);
		queryDispacher.registerHandler(FindInvitesByEventIdQuery.class, queryHandler::handle);
	}

}
