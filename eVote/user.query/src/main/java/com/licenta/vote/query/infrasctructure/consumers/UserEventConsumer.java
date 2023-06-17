package com.licenta.vote.query.infrasctructure.consumers;

import com.licenta.cqrs.core.events.BaseEvent;
import com.licenta.vote.common.events.UserCreatedEvent;
import com.licenta.vote.common.events.UserDeletedEvent;
import com.licenta.vote.common.events.userUpdateEvents.*;
import com.licenta.vote.query.infrasctructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer implements EventConsumer{
    @Autowired
    private EventHandler eventHandler;
    @KafkaListener(topics = "UserCreatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload UserCreatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
    @KafkaListener(topics = "UserEmailUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload UserEmailUpdatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
    @KafkaListener(topics = "UserPhoneNumberUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload UserPhoneNumberUpdatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
    @KafkaListener(topics = "UserCountryUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload UserCountryUpdatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
    @KafkaListener(topics = "UserCountyUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload UserCountyUpdatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
    @KafkaListener(topics = "UserCityUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload UserCityUpdatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
    @KafkaListener(topics = "UserWorkPlaceUpdatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload UserWorkPlaceUpdatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "UserDeletedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload UserDeletedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "RepublishUserEvents", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload BaseEvent event, Acknowledgment ack) {
        if(event instanceof UserCreatedEvent)
            eventHandler.on((UserCreatedEvent) event);
        if(event instanceof UserEmailUpdatedEvent)
            eventHandler.on((UserEmailUpdatedEvent) event);
        if(event instanceof UserPhoneNumberUpdatedEvent)
            eventHandler.on((UserPhoneNumberUpdatedEvent) event);
        if(event instanceof UserDeletedEvent)
            eventHandler.on((UserDeletedEvent) event);
        ack.acknowledge();
    }


}
