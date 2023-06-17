package com.licenta.vote.common.events;

import com.licenta.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class UserDeletedEvent extends BaseEvent {
}
