package com.licenta.vote.query.api.queries.users;

import com.licenta.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindUserByEmailQuery extends BaseQuery {
    private String email;
}