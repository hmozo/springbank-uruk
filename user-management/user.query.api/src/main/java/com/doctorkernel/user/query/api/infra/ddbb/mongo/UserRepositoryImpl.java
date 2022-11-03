package com.doctorkernel.user.query.api.infra.ddbb.mongo;

import com.doctorkernel.user.core.infra.ddbb.mongo.data.UserData;
import com.doctorkernel.user.query.api.domain.services.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMongoRepository userMongoRepository;

    @Override
    public void save(UserData user) {
        userMongoRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        userMongoRepository.deleteById(id);
    }

    @Override
    public List<UserData> findAll() {
        return userMongoRepository.findAll();
    }

    @Override
    public Optional<UserData> findById(String userId) {
        return userMongoRepository.findById(userId);
    }

    @Override
    public List<UserData> findByFilter(String filter) {
        return userMongoRepository.findByFilter(filter);
    }
}
