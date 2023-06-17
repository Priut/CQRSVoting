package com.licenta.vote.query.infrasctructure.consumers;

import com.licenta.vote.common.events.InviteDeletedEvent;
import com.licenta.vote.common.events.StatusChangedEvent;
import com.licenta.vote.common.events.UserInvitedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload UserInvitedEvent event, Acknowledgment ack);
    void consume(@Payload InviteDeletedEvent event, Acknowledgment ack);

    void consume(@Payload StatusChangedEvent event, Acknowledgment ack);
}
