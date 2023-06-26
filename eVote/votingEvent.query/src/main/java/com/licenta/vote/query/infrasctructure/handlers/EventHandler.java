package com.licenta.vote.query.infrasctructure.handlers;

import com.licenta.vote.common.events.AttendanceCalculatedEvent;
import com.licenta.vote.common.events.FinishDateModifiedEvent;
import com.licenta.vote.common.events.ResultsCalculatedEvent;
import com.licenta.vote.common.events.VotingEventCreatedEvent;

public interface EventHandler {
    void on(VotingEventCreatedEvent event);
    void on(ResultsCalculatedEvent event);
    void on(FinishDateModifiedEvent event);
    void on(AttendanceCalculatedEvent event);
}
