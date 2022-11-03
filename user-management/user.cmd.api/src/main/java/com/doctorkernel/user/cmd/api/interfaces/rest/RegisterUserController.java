package com.doctorkernel.user.cmd.api.interfaces.rest;

import com.doctorkernel.user.cmd.api.domain.commands.RegisterUserCommand;
import com.doctorkernel.user.cmd.api.interfaces.rest.dto.RegisterUserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Slf4j
public class RegisterUserController {
    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command){
        var id= UUID.randomUUID().toString();
        command.setId(id);

        try{
            commandGateway.send(command);
            return new ResponseEntity<>(new RegisterUserResponse(id,"User successfully registered"), HttpStatus.CREATED);
        }catch(Exception ex){
            var safeErrorMessage= "Error while processing user request for id - " + id;
            log.error(ex.toString());

            return new ResponseEntity<>(new RegisterUserResponse(id,safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
