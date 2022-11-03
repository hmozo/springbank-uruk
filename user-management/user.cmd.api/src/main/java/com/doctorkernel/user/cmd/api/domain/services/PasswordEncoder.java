package com.doctorkernel.user.cmd.api.domain.services;

public interface PasswordEncoder {
    String hashPassword(String password);
}
