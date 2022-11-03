package com.doctorkernel.user.cmd.api.interfaces.rest;

import com.doctorkernel.user.cmd.api.domain.commands.UpdateUserCommand;
import com.doctorkernel.user.core.interfaces.rest.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Slf4j
public class UpdateUserController {

    private final CommandGateway commandGateway;

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable(value="id") String id, @Valid @RequestBody UpdateUserCommand command){
        try{
            command.setId(id);
            commandGateway.send(command);
            return new ResponseEntity<>(new BaseResponse("User successfully updated"), HttpStatus.OK);
        }catch(Exception ex){
            var safeErrorMessage= "Error while processing update user request for id - " + id;
            log.error(ex.toString());
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
