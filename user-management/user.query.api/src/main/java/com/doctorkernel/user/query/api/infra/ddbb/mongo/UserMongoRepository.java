package com.doctorkernel.user.query.api.infra.ddbb.mongo;

import com.doctorkernel.user.core.infra.ddbb.mongo.data.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserMongoRepository extends MongoRepository<UserData, String> {
    @Query("{'$or': [{'firstname': {$regex: ?0, $options: '1'}}, {'lastname': {$regex: ?0, $options: '1'}}, {'emailAddress': {$regex: ?0, $options: '1'}}, {'account.username': {$regex: ?0, $options: '1'}}")
    List<UserData> findByFilter(String filter);
}
