package com.doctorkernel.user.query.api.application.services;

import com.doctorkernel.user.core.domain.entities.User;
import com.doctorkernel.user.core.domain.queries.FindAllUsersQuery;
import com.doctorkernel.user.core.domain.queries.FindUserByIdQuery;
import com.doctorkernel.user.core.domain.queries.SearchUsersQuery;
import com.doctorkernel.user.core.infra.ddbb.mongo.data.UserData;
import com.doctorkernel.user.query.api.domain.services.UserRepository;
import com.doctorkernel.user.query.api.domain.services.UserUtilities;
import com.doctorkernel.user.query.api.interfaces.ddbb.jpa.dto.UserLookupResponse;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserQueryHandlerImpl implements UserQueryHandler{
    private final UserRepository userRepository;

    @QueryHandler
    @Override
    public UserLookupResponse getUsersById(FindUserByIdQuery query) {
        Optional<UserData> userDataOpt= userRepository.findById(query.getId());
        return userDataOpt.isPresent()
                ?new UserLookupResponse(UserUtilities.mappingUserData2Domain(userDataOpt.get()))
                :null;
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUsers(SearchUsersQuery query) {
        var listUserData= userRepository.findByFilter(query.getFilter());
        return new UserLookupResponse(
                listUserData.stream().map(UserUtilities::mappingUserData2Domain).collect(Collectors.toList()));
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        var listUserData= userRepository.findAll();
        return new UserLookupResponse(
                listUserData.stream().map(UserUtilities::mappingUserData2Domain).collect(Collectors.toList()));
    }
}
