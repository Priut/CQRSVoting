package com.licenta.vote.query.infrasctructure.handlers;

import com.licenta.vote.common.events.ResultsCalculatedEvent;
import com.licenta.vote.common.events.VotingEventCreatedEvent;

public interface EventHandler {
    void on(VotingEventCreatedEvent event);
    void on(ResultsCalculatedEvent event);
}
