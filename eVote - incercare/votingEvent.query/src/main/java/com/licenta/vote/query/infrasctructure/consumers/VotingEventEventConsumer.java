package com.licenta.vote.query.infrasctructure.consumers;

import com.licenta.vote.common.events.ResultsCalculatedEvent;
import com.licenta.vote.common.events.VotingEventCreatedEvent;
import com.licenta.vote.query.infrasctructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class VotingEventEventConsumer implements EventConsumer{
    @Autowired
    private EventHandler eventHandler;
    @KafkaListener(topics = "VotingEventCreatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload VotingEventCreatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
    @KafkaListener(topics = "ResultsCalculatedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload ResultsCalculatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }


}
