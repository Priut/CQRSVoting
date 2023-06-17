package com.licenta.vote.query.domain;

import com.licenta.cqrs.core.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "userTable")
public class User extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String birthDate;
    private String email;
    private String phoneNumber;
    private String country;
    private String county;
    private String city;
    private String workPlace;
}