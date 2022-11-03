package com.doctorkernel.user.core.domain.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRemovedEvent {
    private String id;
}
