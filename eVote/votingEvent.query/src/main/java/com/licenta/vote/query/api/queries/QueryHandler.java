package com.licenta.vote.query.api.queries;

import com.licenta.cqrs.core.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindAllEventsQuery query);
    List<BaseEntity> handle(FindEventByIdQuery query);
}
