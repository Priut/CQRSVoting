package com.licenta.vote.query.api.queries;

import com.licenta.cqrs.core.domain.BaseEntity;
import com.licenta.vote.query.domain.InviteEventRepository;
import com.licenta.vote.query.domain.Invite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class InvitesQueryHandler implements QueryHandler {
    @Autowired
    private InviteEventRepository inviteEventRepository;

    @Override
    public List<BaseEntity> handle(FindAllInvitesQuery query) {
        Iterable<Invite> votingEvents = inviteEventRepository.findAll();
        List<BaseEntity> votingEventsList = new ArrayList<>();
        votingEvents.forEach(votingEventsList::add);
        return votingEventsList;
    }

    @Override
    public List<BaseEntity> handle(FindInvitesByEventIdQuery query) {
        Iterable<Invite> invites = inviteEventRepository.findById_votingEvent(query.getId_votingEvent());

        if (invites == null || !invites.iterator().hasNext()) {
            return null;
        }

        List<BaseEntity> inviteList = new ArrayList<>();
        for (Invite invite : invites) {
            inviteList.add(invite);
        }

        return inviteList;
    }
}
