package com.doctorkernel.user.core.domain.entities;

import com.doctorkernel.user.core.domain.valueobjects.Account;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class User {
    private String id;
    @NotEmpty(message = "firstname is mandatory")
    private String firstName;
    @NotEmpty(message = "lastname is mandatory")
    private String lastname;
    @Email(message = "Please provide a valid email address")
    private String emailAddress;
    @Valid
    @NotNull(message = "Please provide account credentials")
    private Account account;
}
