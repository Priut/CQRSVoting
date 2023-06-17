package com.licenta.vote.query.api.queries;

import com.licenta.cqrs.core.domain.BaseEntity;
import com.licenta.vote.CustomExceptions.VotingEventNotFoundException;
import com.licenta.vote.query.domain.VotingEventRepository;
import com.licenta.vote.query.domain.VotingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class VotingEventQueryHandler implements QueryHandler {
    @Autowired
    private VotingEventRepository votingEventRepository;

    @Override
    public List<BaseEntity> handle(FindAllEventsQuery query) {
        Iterable<VotingEvent> votingEvents = votingEventRepository.findAll();
        List<BaseEntity> votingEventsList = new ArrayList<>();
        votingEvents.forEach(votingEventsList::add);
        return votingEventsList;
    }

    @Override
    public List<BaseEntity> handle(FindEventByIdQuery query) {
        var votingEvent = votingEventRepository.findById(query.getId());
        if (votingEvent.isEmpty())
            throw new VotingEventNotFoundException("Voting event does not exist.") ;
        List<BaseEntity> votingEventList = new ArrayList<>();
        votingEventList.add(votingEvent.get());
        return votingEventList;
    }
}
