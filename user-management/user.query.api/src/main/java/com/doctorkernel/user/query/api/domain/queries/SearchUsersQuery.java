package com.doctorkernel.user.query.api.domain.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchUsersQuery {
    private String filter;
}
