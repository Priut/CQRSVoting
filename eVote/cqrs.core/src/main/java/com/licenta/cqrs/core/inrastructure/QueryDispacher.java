package com.licenta.cqrs.core.inrastructure;

import com.licenta.cqrs.core.domain.BaseEntity;
import com.licenta.cqrs.core.events.BaseEvent;
import com.licenta.cqrs.core.queries.BaseQuery;
import com.licenta.cqrs.core.queries.QueryHandlerMethod;
import com.mongodb.internal.operation.BaseWriteOperation;

import java.util.List;

public interface QueryDispacher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
