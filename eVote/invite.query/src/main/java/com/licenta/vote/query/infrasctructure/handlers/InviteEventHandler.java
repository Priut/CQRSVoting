package com.licenta.vote.query.infrasctructure.handlers;

import com.licenta.vote.CustomExceptions.InviteDeletedException;
import com.licenta.vote.common.events.InviteDeletedEvent;
import com.licenta.vote.common.events.StatusChangedEvent;
import com.licenta.vote.common.events.UserInvitedEvent;
import com.licenta.vote.query.domain.Invite;
import com.licenta.vote.query.domain.InviteEventRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.licenta.vote.CustomExceptions.UniqueConstraintViolationException;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@ComponentScan("com.licenta.vote")
public class InviteEventHandler implements EventHandler {
    @Autowired
    private InviteEventRepository inviteEventRepository;

    @Override
    public void on(UserInvitedEvent event) {

        var invite = Invite.builder()
                .id(event.getId())
                .id_votingEvent(event.getId_votingEvent())
                .id_user(event.getId_user())
                .status(Boolean.FALSE)
                .build();
            inviteEventRepository.save(invite);

    }
    @Override
    public void on(InviteDeletedEvent event) {
        inviteEventRepository.deleteById(event.getId());
    }

    @Override
    public void on(StatusChangedEvent event) {
        var invite = inviteEventRepository.findById(event.getId());
        if(invite.isEmpty())
            return;
        invite.get().setStatus(Boolean.TRUE);
        inviteEventRepository.save(invite.get());
    }
}