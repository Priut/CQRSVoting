package com.licenta.vote.query.api.queries;

import com.licenta.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindInvitesByEventIdQuery extends BaseQuery {
    private String id_votingEvent;
}
