package com.licenta.vote.query.infrasctructure.consumers;

import com.licenta.cqrs.core.events.BaseEvent;
import com.licenta.vote.common.events.UserCreatedEvent;
import com.licenta.vote.common.events.UserDeletedEvent;
import com.licenta.vote.common.events.userUpdateEvents.*;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload UserCreatedEvent event, Acknowledgment ack);
    void consume(@Payload UserEmailUpdatedEvent event, Acknowledgment ack);
    void consume(@Payload UserPhoneNumberUpdatedEvent event, Acknowledgment ack);

    void consume(@Payload UserCountryUpdatedEvent event, Acknowledgment ack);
    void consume(@Payload UserCountyUpdatedEvent event, Acknowledgment ack);
    void consume(@Payload UserCityUpdatedEvent event, Acknowledgment ack);
    void consume(@Payload UserWorkPlaceUpdatedEvent event, Acknowledgment ack);
    void consume(@Payload UserDeletedEvent event, Acknowledgment ack);
    void consume(@Payload BaseEvent event, Acknowledgment ack);
}
