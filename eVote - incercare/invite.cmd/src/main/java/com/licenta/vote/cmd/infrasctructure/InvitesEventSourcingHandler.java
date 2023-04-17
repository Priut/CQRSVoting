package com.licenta.vote.cmd.infrasctructure;

import com.licenta.cqrs.core.domain.AggregateRoot;
import com.licenta.cqrs.core.handlers.EventSourcingHandler;
import com.licenta.cqrs.core.inrastructure.EventStore;
import com.licenta.cqrs.core.producers.EventProducer;
import com.licenta.vote.cmd.domain.InviteAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class InvitesEventSourcingHandler implements EventSourcingHandler<InviteAggregate> {
    @Autowired
    private EventStore eventStore;
    @Autowired
    private EventProducer eventProducer;
    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommitedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommited();

    }

    @Override
    public InviteAggregate getById(String id) {
        var aggregate = new InviteAggregate();
        var events = eventStore.getEvents(id);
        if(events != null && !events.isEmpty()){
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(x -> x.getVersion()).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }

    @Override
    public void republishEvents() {
        var aggregateIds = eventStore.getAggregateIds();
        for(var aggregateId: aggregateIds){
            var aggregate = getById(aggregateId);
            if(aggregate == null || !aggregate.getActive())
                continue;
            var events = eventStore.getEvents(aggregateId);
            for (var event : events){
                eventProducer.produce("RepublishUserEvents", event);
            }
        }
    }
}

