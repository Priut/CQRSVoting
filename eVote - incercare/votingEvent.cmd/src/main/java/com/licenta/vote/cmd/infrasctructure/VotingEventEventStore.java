package com.licenta.vote.cmd.infrasctructure;

import com.licenta.cqrs.core.events.BaseEvent;
import com.licenta.cqrs.core.events.EventModel;
import com.licenta.vote.CustomExceptions.AggregateNotFoundException;
import com.licenta.vote.CustomExceptions.ConcurencyException;
import com.licenta.cqrs.core.inrastructure.EventStore;
import com.licenta.cqrs.core.producers.EventProducer;
import com.licenta.vote.cmd.domain.EventStoreRepository;
import com.licenta.vote.cmd.domain.VotingEventAggregate;
import com.licenta.vote.cmd.domain.VotingEventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VotingEventEventStore implements EventStore {
    @Autowired
    private EventProducer eventProducer;
    @Autowired
    private EventStoreRepository eventStoreRepository;
    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId,"votingEventStore");
        if(expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion){
            throw new ConcurencyException("Concurency exception in votingEventEventStore");
        }
        var version = expectedVersion;
        for(var event:events)
        {
            version++;
            event.setVersion(version);
            var eventModel = VotingEventModel.builder()
                    .timestamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(VotingEventAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            var persistedEvent = eventStoreRepository.save(eventModel,"votingEventStore");
            if(!persistedEvent.getId().isEmpty() && !Objects.equals(persistedEvent.getEventType(), "com.licenta.vote.common.events.VoteRegisteredEvent")){
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId,"votingEventStore");
        if(eventStream == null || eventStream.isEmpty()){
            throw new AggregateNotFoundException("Incorrect account ID provided");
        }
        return eventStream.stream().map(x -> x.getEventData()).collect(Collectors.toList());
    }

    @Override
    public List<String> getAggregateIds() {
        var eventStream = eventStoreRepository.findAll("votingEventStore");
        if(eventStream == null || eventStream.isEmpty()){
            throw new IllegalStateException("Could not retrieve event stream from the event store!");
        }
        return eventStream.stream().map(EventModel::getAggregateIdentifier).distinct().collect(Collectors.toList());
    }
}
