package com.licenta.vote.query.infrasctructure.handlers;

import com.licenta.vote.common.events.ResultsCalculatedEvent;
import com.licenta.vote.common.events.VotingEventCreatedEvent;
import com.licenta.vote.query.domain.Candidate;
import com.licenta.vote.query.domain.CandidateRepository;
import com.licenta.vote.query.domain.VotingEvent;
import com.licenta.vote.query.domain.VotingEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VotingEventEventHandler implements EventHandler {
    @Autowired
    private VotingEventRepository votingEventRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public void on(VotingEventCreatedEvent event) {
        List<String> candidates_names = event.getCandidates();
        Set<Candidate> candidates = new HashSet<>();
        for (var candidate_name : candidates_names) {
            var c = new Candidate();
            c.setName(candidate_name);
            candidateRepository.save(c);
            candidates.add(c);
        }
        var votingEvent = VotingEvent.builder()
                .id(event.getId())
                .name(event.getName())
                .start_date(event.getStart_date())
                .finish_date(event.getFinish_date())
                .candidates(candidates)
                .winner(null)
                .build();
        votingEventRepository.save(votingEvent);
    }
    @Override
    public void on(ResultsCalculatedEvent event) {
        var votingEvent = votingEventRepository.findById(event.getId());
        if (votingEvent.isEmpty())
                return;
        votingEvent.get().setWinner(event.getWinner());
        votingEventRepository.save(votingEvent.get());
    }

}