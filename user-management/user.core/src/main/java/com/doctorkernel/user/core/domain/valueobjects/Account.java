package com.doctorkernel.user.core.domain.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Size(min = 2, message ="username min 2 characters")
    private String username;
    @Size(min = 7,message = "username min 7 characters")
    private String password;
    @Valid
    @NotNull(message = "specify at least 1 role")
    private List<Role> roles;
}
