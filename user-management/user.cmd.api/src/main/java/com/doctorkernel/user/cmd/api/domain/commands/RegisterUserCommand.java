package com.doctorkernel.user.cmd.api.domain.commands;

import com.doctorkernel.user.core.domain.entities.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class RegisterUserCommand {
    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "no user details were supplied")
    @Valid
    private User user;
}
