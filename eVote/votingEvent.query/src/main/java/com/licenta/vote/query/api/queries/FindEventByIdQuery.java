package com.licenta.vote.query.api.queries;

import com.licenta.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindEventByIdQuery extends BaseQuery {
    private String id;
}
