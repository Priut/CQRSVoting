package com.licenta.vote.common.events;

import com.licenta.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class VotingEventCreatedEvent extends BaseEvent {
    private String name;
    private String start_date;
    private String finish_date;
    private ArrayList<String> candidates;
}
