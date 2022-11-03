package com.doctorkernel.user.query.api.interfaces.eventhandlers;

import com.doctorkernel.user.core.domain.events.UserRegisteredEvent;
import com.doctorkernel.user.core.domain.events.UserRemovedEvent;
import com.doctorkernel.user.core.domain.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on (UserRegisteredEvent event);
    void on (UserUpdatedEvent event);
    void on (UserRemovedEvent event);
}
