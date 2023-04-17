package com.licenta.vote.query.domain;

import com.licenta.cqrs.core.domain.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository <User, String>{
    Optional<User> findByName(String name);
    List<BaseEntity> findByEmail(String email);
    List<BaseEntity> findByPhoneNumber(String phoneNumber);
    List<BaseEntity> findByCountry(String country);
    List<BaseEntity> findByCounty(String county);
    List<BaseEntity> findByCity(String city);
    List<BaseEntity> findByWorkPlace(String workPlace);
}
