package com.doctorkernel.user.query.api.domain.services;


import com.doctorkernel.user.core.domain.entities.User;
import com.doctorkernel.user.core.infra.ddbb.mongo.data.UserData;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(UserData user);
    void deleteById(String id);
    List<UserData> findAll();
    Optional<UserData> findById(String userId);
    List<UserData> findByFilter(String filter);
}
