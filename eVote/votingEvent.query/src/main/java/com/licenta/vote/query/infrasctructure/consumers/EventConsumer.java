package com.licenta.vote.query.infrasctructure.consumers;

import com.licenta.cqrs.core.events.BaseEvent;
import com.licenta.vote.common.events.AttendanceCalculatedEvent;
import com.licenta.vote.common.events.FinishDateModifiedEvent;
import com.licenta.vote.common.events.ResultsCalculatedEvent;
import com.licenta.vote.common.events.VotingEventCreatedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload VotingEventCreatedEvent event, Acknowledgment ack);
    void consume(@Payload ResultsCalculatedEvent event, Acknowledgment ack);
    void consume(@Payload FinishDateModifiedEvent event,Acknowledgment ack);
    void consume(@Payload AttendanceCalculatedEvent event, Acknowledgment ack);
}
