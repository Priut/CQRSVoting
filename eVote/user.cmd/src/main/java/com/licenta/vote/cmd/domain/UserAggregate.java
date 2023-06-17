package com.licenta.vote.cmd.domain;

import com.licenta.cqrs.core.domain.AggregateRoot;
import com.licenta.vote.CustomExceptions.*;
import com.licenta.vote.cmd.api.commands.CreateUserCommand;
import com.licenta.vote.common.events.*;
import com.licenta.vote.common.events.userUpdateEvents.*;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
public class UserAggregate extends AggregateRoot {
    private Boolean active;
    private String email;
    private String phoneNumber;
    private String country;
    private String county;
    private String city;
    private String workPlace;
    public Boolean getActive(){
        return active;
    }


    public static void validateEmail(String email) throws InvalidEmailFormatException {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            throw new InvalidEmailFormatException("Invalid email format");
        }
    }
    public static void validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        String phoneRegex = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$";
        if (!phoneNumber.matches(phoneRegex)) {
            throw new InvalidPhoneNumberException("Invalid phone number format");
        }
    }
    public static void validateBirthdate(String birthdate) throws FutureBirthDateException, DateFormatIncorrectException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(birthdate);

            if (date.after(new Date())) {
                throw new FutureBirthDateException("Birthdate cannot be in the future.");
            }

        } catch (ParseException e) {
            throw new DateFormatIncorrectException("Invalid birthdate format. Expected format: dd.MM.yyyy");
        }
    }

    public UserAggregate(CreateUserCommand command){
        var id = command.getId();
        validateEmail(command.getEmail());
        validatePhoneNumber(command.getPhoneNumber());
        validateBirthdate(command.getBirthDate());
        raiseEvent(UserCreatedEvent.builder()
                .id(id)
                .name(command.getName())
                .email(command.getEmail())
                .birthDate(command.getBirthDate())
                .phoneNumber(command.getPhoneNumber())
                .country(command.getCountry())
                .county(command.getCounty())
                .city(command.getCity())
                .workPlace(command.getWorkPlace())
                .build());
    }
    public void apply(UserCreatedEvent event){
        this.id = event.getId();
        this.active = true;
        this.email = event.getEmail();
        this.phoneNumber = event.getPhoneNumber();
        this.country = event.getCountry();
        this.county = event.getCounty();
        this.city = event.getCity();
        this.workPlace = event.getWorkPlace();
    }
    public void changeEmail(String email){
        if(!this.active)
            throw new IllegalStateException("Email cannot be changed for a deleted user!");
        validateEmail(email);
        raiseEvent(UserEmailUpdatedEvent.builder()
                .id(this.id)
                .email(email)
                .build());

    }
    public void apply(UserEmailUpdatedEvent event){
        this.id = event.getId();
        this.active = true;
        this.email = event.getEmail();
    }
    public void changePhoneNumber(String phoneNumber){
        if(!this.active)
            throw new UserDeletedException("Phone number cannot be changed for a deleted user!");
        validatePhoneNumber(phoneNumber);
        raiseEvent(UserPhoneNumberUpdatedEvent.builder()
                .id(this.id)
                .phoneNumber(phoneNumber)
                .build());

    }
    public void apply(UserPhoneNumberUpdatedEvent event){
        this.id = event.getId();
        this.active = true;
        this.phoneNumber = event.getPhoneNumber();
    }
    public void changeWorkPlace(String workPlace){
        if(!this.active)
            throw new UserDeletedException("Work place cannot be changed for a deleted user!");

        raiseEvent(UserWorkPlaceUpdatedEvent.builder()
                .id(this.id)
                .workPlace(workPlace)
                .build());

    }
    public void apply(UserWorkPlaceUpdatedEvent event){
        this.id = event.getId();
        this.active = true;
        this.workPlace = event.getWorkPlace();
    }
    public void changeCountry(String country){
        if(!this.active)
            throw new UserDeletedException("Country cannot be changed for a deleted user!");

        raiseEvent(UserCountryUpdatedEvent.builder()
                .id(this.id)
                .country(country)
                .build());

    }
    public void apply(UserCountryUpdatedEvent event){
        this.id = event.getId();
        this.active = true;
        this.country = event.getCountry();
    }
    public void changeCounty(String county){
        if(!this.active)
            throw new UserDeletedException("County cannot be changed for a deleted user!");

        raiseEvent(UserCountyUpdatedEvent.builder()
                .id(this.id)
                .county(county)
                .build());

    }
    public void apply(UserCountyUpdatedEvent event){
        this.id = event.getId();
        this.active = true;
        this.county = event.getCounty();
    }
    public void changeCity(String city){
        if(!this.active)
            throw new UserDeletedException("City cannot be changed for a deleted user!");

        raiseEvent(UserCityUpdatedEvent.builder()
                .id(this.id)
                .city(city)
                .build());

    }
    public void apply(UserCityUpdatedEvent event){
        this.id = event.getId();
        this.active = true;
        this.city = event.getCity();
    }
    public void deleteUser(){
        if(!this.active)
            throw new UserDeletedException("User was deleted already!");

        raiseEvent(UserDeletedEvent.builder()
                .id(this.id)
                .build());

    }
    public void apply(UserDeletedEvent event){
        this.id = event.getId();
        this.active = false;
    }

}
