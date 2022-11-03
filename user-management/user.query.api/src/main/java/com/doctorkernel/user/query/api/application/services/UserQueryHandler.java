package com.doctorkernel.user.query.api.application.services;

import com.doctorkernel.user.core.domain.queries.FindAllUsersQuery;
import com.doctorkernel.user.core.domain.queries.FindUserByIdQuery;
import com.doctorkernel.user.core.domain.queries.SearchUsersQuery;
import com.doctorkernel.user.query.api.interfaces.ddbb.jpa.dto.UserLookupResponse;

public interface UserQueryHandler {
    UserLookupResponse getUsersById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
