package com.licenta.vote.common.events;

import com.licenta.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AttendanceCalculatedEvent extends BaseEvent {
    private Double attendance;
}
