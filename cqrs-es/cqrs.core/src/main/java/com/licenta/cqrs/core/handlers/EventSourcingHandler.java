package com.licenta.cqrs.core.handlers;

import com.licenta.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);

    T getById(String id);
    void republishEvents();
}