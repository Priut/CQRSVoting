package com.licenta.vote.query.infrasctructure.handlers;

import com.licenta.vote.common.events.UserCreatedEvent;
import com.licenta.vote.common.events.UserDeletedEvent;
import com.licenta.vote.common.events.userUpdateEvents.*;
import com.licenta.vote.query.domain.User;
import com.licenta.vote.query.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEventHandler implements EventHandler{
    @Autowired
    private UserRepository userRepository;
    @Override
    public void on(UserCreatedEvent event) {
        var user = User.builder()
                .id(event.getId())
                .name(event.getName())
                .email(event.getEmail())
                .birthDate(event.getBirthDate())
                .phoneNumber(event.getPhoneNumber())
                .country(event.getCountry())
                .county(event.getCounty())
                .city(event.getCity())
                .workPlace(event.getWorkPlace())
                .build();
        userRepository.save(user);
    }

    @Override
    public void on(UserEmailUpdatedEvent event) {
        var user = userRepository.findById(event.getId());
        if(user.isEmpty())
                    return;
        user.get().setEmail(event.getEmail());
        userRepository.save(user.get());
    }

    @Override
    public void on(UserPhoneNumberUpdatedEvent event) {
        var user = userRepository.findById(event.getId());
        if(user.isEmpty())
            return;
        user.get().setPhoneNumber(event.getPhoneNumber());
        userRepository.save(user.get());
    }

    @Override
    public void on(UserCountryUpdatedEvent event) {
        var user = userRepository.findById(event.getId());
        if(user.isEmpty())
            return;
        user.get().setCountry(event.getCountry());
        userRepository.save(user.get());
    }

    @Override
    public void on(UserCountyUpdatedEvent event) {
        var user = userRepository.findById(event.getId());
        if(user.isEmpty())
            return;
        user.get().setCounty(event.getCounty());
        userRepository.save(user.get());
    }

    @Override
    public void on(UserCityUpdatedEvent event) {
        var user = userRepository.findById(event.getId());
        if(user.isEmpty())
            return;
        user.get().setCity(event.getCity());
        userRepository.save(user.get());
    }

    @Override
    public void on(UserWorkPlaceUpdatedEvent event) {
        var user = userRepository.findById(event.getId());
        if(user.isEmpty())
            return;
        user.get().setWorkPlace(event.getWorkPlace());
        userRepository.save(user.get());
    }

    @Override
    public void on(UserDeletedEvent event) {
        userRepository.deleteById(event.getId());
    }

}
