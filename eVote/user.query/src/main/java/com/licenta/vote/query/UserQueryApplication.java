package com.licenta.vote.query;

import com.licenta.cqrs.core.inrastructure.QueryDispacher;
import com.licenta.vote.query.api.queries.*;
import com.licenta.vote.query.api.queries.users.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.licenta.vote")
public class UserQueryApplication {
	@Autowired
	private QueryDispacher queryDispacher;

	@Autowired
	private QueryHandler queryHandler;
	public static void main(String[] args) {
		SpringApplication.run(UserQueryApplication.class, args);
	}
	@PostConstruct
	public void registerHandlers(){
		queryDispacher.registerHandler(FindAllUsersQuery.class, queryHandler::handle);
		queryDispacher.registerHandler(FindUserByIdQuery.class, queryHandler::handle);
		queryDispacher.registerHandler(FindUserByPhoneNumberQuery.class, queryHandler::handle);
		queryDispacher.registerHandler(FindUserByEmailQuery.class, queryHandler::handle);
		queryDispacher.registerHandler(FindUserByCountryQuery.class, queryHandler::handle);
		queryDispacher.registerHandler(FindUserByCountyQuery.class, queryHandler::handle);
		queryDispacher.registerHandler(FindUsersByCityQuery.class, queryHandler::handle);
		queryDispacher.registerHandler(FindUserByWorkPlaceQuery.class, queryHandler::handle);
	}

}
