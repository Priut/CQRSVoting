package com.licenta.vote.query.infrasctructure.handlers;

import com.licenta.vote.common.events.UserCreatedEvent;
import com.licenta.vote.common.events.UserDeletedEvent;
import com.licenta.vote.common.events.userUpdateEvents.*;

public interface EventHandler {
    void on(UserCreatedEvent event);
    void on(UserEmailUpdatedEvent event);
    void on(UserPhoneNumberUpdatedEvent event);
    void on(UserCountryUpdatedEvent event);
    void on(UserCountyUpdatedEvent event);
    void on(UserCityUpdatedEvent event);
    void on(UserWorkPlaceUpdatedEvent event);
    void on(UserDeletedEvent event);
}
