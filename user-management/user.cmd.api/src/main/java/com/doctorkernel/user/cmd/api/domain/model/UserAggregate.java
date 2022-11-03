package com.doctorkernel.user.cmd.api.domain.model;

import com.doctorkernel.user.cmd.api.domain.commands.RegisterUserCommand;
import com.doctorkernel.user.cmd.api.domain.commands.RemoveUserCommand;
import com.doctorkernel.user.cmd.api.domain.commands.UpdateUserCommand;
import com.doctorkernel.user.cmd.api.domain.services.PasswordEncoder;
import com.doctorkernel.user.cmd.api.domain.services.PasswordEncoderImpl;
import com.doctorkernel.user.core.domain.entities.User;
import com.doctorkernel.user.core.domain.events.UserRegisteredEvent;
import com.doctorkernel.user.core.domain.events.UserRemovedEvent;
import com.doctorkernel.user.core.domain.events.UserUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;

    private final PasswordEncoder passwordEncoder;

    public UserAggregate(){
        this.passwordEncoder= new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command){
        this();

        var newUser= command.getUser();
        newUser.setId(command.getId());
        var hashedPassword= passwordEncoder.hashPassword(newUser.getAccount().getPassword());
        newUser.getAccount().setPassword(hashedPassword);

        var event= UserRegisteredEvent.builder()
                .id(command.getId())
                .user(newUser)
                .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand command){
        var updatedUser= command.getUser();
        updatedUser.setId(command.getId());
        var hashedPassword= passwordEncoder.hashPassword(updatedUser.getAccount().getPassword());
        updatedUser.getAccount().setPassword(hashedPassword);

        var event= UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build();
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command){
        var event= UserRemovedEvent.builder()
                .id(command.getId()).build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event){
        this.id= event.getId();
        this.user= event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event){
        this.user= event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event){
        AggregateLifecycle.markDeleted();
    }
}
