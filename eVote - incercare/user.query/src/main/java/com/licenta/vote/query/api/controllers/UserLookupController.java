package com.licenta.vote.query.api.controllers;

import com.licenta.cqrs.core.inrastructure.QueryDispacher;
import com.licenta.vote.query.api.dto.UserLookupResponse;
import com.licenta.vote.query.api.queries.users.*;
import com.licenta.vote.query.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.licenta.vote.CustomExceptions.TooManyQueryParametersException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/usersLookup")
public class UserLookupController {

    @Autowired
    private QueryDispacher queryDispacher;

    @GetMapping(path = "/")
    public ResponseEntity<UserLookupResponse> getUsers(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "county", required = false) String county,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "workPlace", required = false) String workPlace) {

        List<User> users = null;

        int paramsCount = (id != null ? 1 : 0) + (email != null ? 1 : 0) + (phoneNumber != null ? 1 : 0) +
                (country != null ? 1 : 0) + (county != null ? 1 : 0) + (city != null ? 1 : 0) +
                (workPlace != null ? 1 : 0);

        if (paramsCount > 1) {
            throw new TooManyQueryParametersException("Only one query parameter at a time is allowed.");
        }

        if (id != null) {
            users = queryDispacher.send(new FindUserByIdQuery(id));
        } else if (email != null) {
            users = queryDispacher.send(new FindUserByEmailQuery(email));
        } else if (phoneNumber != null) {
            users = queryDispacher.send(new FindUserByPhoneNumberQuery(phoneNumber));
        } else if (country != null) {
            users = queryDispacher.send(new FindUserByCountryQuery(country));
        } else if (county != null) {
            users = queryDispacher.send(new FindUserByCountyQuery(county));
        } else if (city != null) {
            users = queryDispacher.send(new FindUsersByCityQuery(city));
        } else if (workPlace != null) {
            users = queryDispacher.send(new FindUserByWorkPlaceQuery(workPlace));
        } else {
            users = queryDispacher.send(new FindAllUsersQuery());
        }

        if (users == null || users.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        var response = UserLookupResponse.builder()
                .users(users)
                .message(String.format("Successfully returned %d users(s)!", users.size()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
