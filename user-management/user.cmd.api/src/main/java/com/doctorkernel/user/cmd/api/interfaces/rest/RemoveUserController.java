package com.doctorkernel.user.cmd.api.interfaces.rest;

import com.doctorkernel.user.cmd.api.domain.commands.RemoveUserCommand;
import com.doctorkernel.user.core.interfaces.rest.dto.BaseResponse;
import com.doctorkernel.user.cmd.api.interfaces.rest.dto.RegisterUserResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Slf4j
public class RemoveUserController {
    private final CommandGateway commandGateway;

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> removeUser(@PathVariable(value="id") String id){
        try{
            commandGateway.send(new RemoveUserCommand(id));

            return new ResponseEntity<>(new BaseResponse("User was successfully removed"), HttpStatus.OK);
        }catch(Exception ex){
            var safeErrorMessage= "Error while processing user remove request for id - " + id;
            log.error(ex.toString());

            return new ResponseEntity<>(new RegisterUserResponse(id,safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
