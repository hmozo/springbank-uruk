package com.doctorkernel.user.core.domain.valueobjects;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    READ_PRIVILEGE, WRITE_PRIVILEGE;

    @Override
    public String getAuthority() {
        return name();
    }
}
