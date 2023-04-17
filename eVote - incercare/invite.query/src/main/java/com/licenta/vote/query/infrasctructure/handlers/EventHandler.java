package com.licenta.vote.query.infrasctructure.handlers;

import com.licenta.vote.common.events.InviteDeletedEvent;
import com.licenta.vote.common.events.UserInvitedEvent;

public interface EventHandler {
    void on(UserInvitedEvent event);
    void on(InviteDeletedEvent event);
}
