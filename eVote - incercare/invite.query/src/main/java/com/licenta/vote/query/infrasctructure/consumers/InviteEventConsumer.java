package com.licenta.vote.query.infrasctructure.consumers;

import com.licenta.vote.common.events.InviteDeletedEvent;
import com.licenta.vote.common.events.UserInvitedEvent;
import com.licenta.vote.query.infrasctructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class InviteEventConsumer implements EventConsumer{
    @Autowired
    private EventHandler eventHandler;
    @KafkaListener(topics = "UserInvitedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload UserInvitedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
    @KafkaListener(topics = "InviteDeletedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload InviteDeletedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }


}
