package com.doctorkernel.user.cmd.api.interfaces.rest.dto;

import com.doctorkernel.user.core.interfaces.rest.dto.BaseResponse;


public class RegisterUserResponse extends BaseResponse {
    private String id;

    public RegisterUserResponse(String id, String message){
        super(message);
        this.id= id;
    }
}
