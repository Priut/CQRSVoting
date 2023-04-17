package com.licenta.vote.query.api.queries.users;

import com.licenta.cqrs.core.domain.BaseEntity;
import com.licenta.vote.query.api.queries.QueryHandler;
import com.licenta.vote.query.domain.UserRepository;
import com.licenta.vote.query.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserQueryHandler implements QueryHandler {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<BaseEntity> handle(FindAllUsersQuery query) {
        Iterable<User> users = userRepository.findAll();
        List<BaseEntity> usersList = new ArrayList<>();
        users.forEach(usersList::add);
        return usersList;
    }

    @Override
    public List<BaseEntity> handle(FindUserByIdQuery query) {
        var user = userRepository.findById(query.getId());
        if(user.isEmpty())
            return null;
        List<BaseEntity> usersList = new ArrayList<>();
        usersList.add(user.get());
        return usersList;
    }

    @Override
    public List<BaseEntity> handle(FindUserByPhoneNumberQuery query) {
        var user = userRepository.findByPhoneNumber(query.getPhoneNumber());
        if(user.isEmpty())
            return null;
        List<BaseEntity> usersList = new ArrayList<>();
        usersList.addAll(user);
        return usersList;
    }

    @Override
    public List<BaseEntity> handle(FindUserByEmailQuery query) {
        var user = userRepository.findByEmail(query.getEmail());
        if(user.isEmpty())
            return null;
        List<BaseEntity> usersList = new ArrayList<>();
        usersList.addAll(user);
        return usersList;
    }

    @Override
    public List<BaseEntity> handle(FindUserByCountryQuery query) {
        var user = userRepository.findByCountry(query.getCountry());
        if(user.isEmpty())
            return null;
        List<BaseEntity> usersList = new ArrayList<>();
        usersList.addAll(user);
        return usersList;
    }

    @Override
    public List<BaseEntity> handle(FindUserByCountyQuery query) {
        var user = userRepository.findByCounty(query.getCounty());
        if(user.isEmpty())
            return null;
        List<BaseEntity> usersList = new ArrayList<>();
        usersList.addAll(user);
        return usersList;
    }

    @Override
    public List<BaseEntity> handle(FindUsersByCityQuery query) {
        var user = userRepository.findByCity(query.getCity());
        if(user.isEmpty())
            return null;
        List<BaseEntity> usersList = new ArrayList<>();
        usersList.addAll(user);
        return usersList;
    }

    @Override
    public List<BaseEntity> handle(FindUserByWorkPlaceQuery query) {
        var user = userRepository.findByWorkPlace(query.getWorkPlace());
        if(user.isEmpty())
            return null;
        List<BaseEntity> usersList = new ArrayList<>();
        usersList.addAll(user);
        return usersList;
    }
}
