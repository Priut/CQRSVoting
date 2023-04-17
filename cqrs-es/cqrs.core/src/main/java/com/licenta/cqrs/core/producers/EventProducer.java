package com.licenta.cqrs.core.producers;

import com.licenta.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
