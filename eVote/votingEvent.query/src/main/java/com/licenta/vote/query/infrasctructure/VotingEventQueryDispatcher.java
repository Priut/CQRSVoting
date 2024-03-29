package com.licenta.vote.query.infrasctructure;

import com.licenta.cqrs.core.domain.BaseEntity;
import com.licenta.cqrs.core.inrastructure.QueryDispacher;
import com.licenta.cqrs.core.queries.BaseQuery;
import com.licenta.cqrs.core.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;
import com.licenta.vote.CustomExceptions.MultipleQueryHandlersFoundException;
import com.licenta.vote.CustomExceptions.NoQueryHandlerRegisteredException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class VotingEventQueryDispatcher implements QueryDispacher {
    private final Map<Class<? extends BaseQuery>,List<QueryHandlerMethod>> routes = new HashMap<>();
    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        var handlers = routes.get(query.getClass());
        if (handlers == null || handlers.size() <= 0) {
            throw new NoQueryHandlerRegisteredException("No query handler was registered!");
        }
        if (handlers.size() > 1) {
            throw new MultipleQueryHandlersFoundException("Cannot send query to more than one handler!");
        }
        return handlers.get(0).handle(query);
    }
}
