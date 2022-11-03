package com.doctorkernel.user.query.api.interfaces.ddbb.jpa.dto;

import com.doctorkernel.user.core.domain.entities.User;
import com.doctorkernel.user.core.interfaces.rest.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLookupResponse extends BaseResponse {
    private List<User> users;

    public UserLookupResponse(String message){
        super(message);
    }

    public UserLookupResponse(String message, User user){
        super(message);
        List<User> users= new ArrayList<>();
        users.add(user);
    }

    public UserLookupResponse(User user){
        super(null);
        List<User> users= new ArrayList<>();
        users.add(user);
    }
}
