package com.doctorkernel.user.core.domain.events;

import com.doctorkernel.user.core.domain.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdatedEvent {
    private String id;
    private User user;
}
