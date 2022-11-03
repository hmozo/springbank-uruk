package com.doctorkernel.user.query.api.interfaces.eventhandlers;

import com.doctorkernel.user.core.domain.events.UserRegisteredEvent;
import com.doctorkernel.user.core.domain.events.UserRemovedEvent;
import com.doctorkernel.user.core.domain.events.UserUpdatedEvent;
import com.doctorkernel.user.core.infra.ddbb.mongo.data.UserData;
import com.doctorkernel.user.query.api.domain.services.UserRepository;
import com.doctorkernel.user.query.api.domain.services.UserUtilities;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler{

    private final UserRepository userRepository;

    @EventHandler
    @Override
    public void on(UserRegisteredEvent event) {
        UserData userEntity= UserUtilities.mappingUserDomain2Data(event.getUser());
        userRepository.save(userEntity);
    }

    @EventHandler
    @Override
    public void on(UserUpdatedEvent event) {
        UserData userEntity= UserUtilities.mappingUserDomain2Data(event.getUser());
        userRepository.save(userEntity);
    }

    @EventHandler
    @Override
    public void on(UserRemovedEvent event) {
        userRepository.deleteById(event.getId());
    }
}
