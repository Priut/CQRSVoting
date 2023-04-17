package com.licenta.vote.common.events;

import com.licenta.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserCreatedEvent extends BaseEvent {
    private String name;
    private String email;
    private String phoneNumber;
    private String birthDate;
    private String country;
    private String county;
    private String city;
    private String workPlace;
}
