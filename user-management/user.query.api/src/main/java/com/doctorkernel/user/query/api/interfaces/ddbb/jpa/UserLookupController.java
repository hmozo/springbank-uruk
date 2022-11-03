package com.doctorkernel.user.query.api.interfaces.ddbb.jpa;

import com.doctorkernel.user.query.api.domain.queries.FindAllUsersQuery;
import com.doctorkernel.user.query.api.domain.queries.FindUserByIdQuery;
import com.doctorkernel.user.query.api.domain.queries.SearchUsersQuery;
import com.doctorkernel.user.query.api.interfaces.ddbb.jpa.dto.UserLookupResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Slf4j
public class UserLookupController {
    private final QueryGateway queryGateway;

    @GetMapping
    public ResponseEntity<UserLookupResponse> getAllUsers(){
        try{
            var response= queryGateway.query(new FindAllUsersQuery(), ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response==null || response.getUsers()==null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception ex){
            var safeErrorMessage= "Failed to complete get all users request";
            log.error(ex.toString());
            return new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<UserLookupResponse> getUserById(@PathVariable("id") String id){
        try{
            var response= queryGateway.query(new FindUserByIdQuery(id), ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response==null || response.getUsers()==null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception ex){
            var safeErrorMessage= "Failed to complete get user by id request";
            log.error(ex.toString());
            return new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<UserLookupResponse> getUserByFilter(@PathVariable("filter") String filter){
        try{
            var response= queryGateway.query(new SearchUsersQuery(filter), ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response==null || response.getUsers()==null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception ex){
            var safeErrorMessage= "Failed to complete get user by filter request";
            log.error(ex.toString());
            return new ResponseEntity<>(new UserLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
