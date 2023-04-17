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
public class UserInvitedEvent extends BaseEvent {
    private String id_user;
    private String id_votingEvent;
}
