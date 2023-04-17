package com.licenta.vote.cmd.infrasctructure;

import com.licenta.cqrs.core.events.BaseEvent;
import com.licenta.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class VotingEventEventProducer implements EventProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Override
    public void produce(String topic, BaseEvent event) {
        this.kafkaTemplate.send(topic,event);

    }
}
