package com.licenta.vote.query.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InviteEventRepository extends CrudRepository <Invite, String>{
    @Query("SELECT i FROM Invite i WHERE i.id_votingEvent = :id_votingEvent")
    Iterable<Invite> findById_votingEvent(@Param("id_votingEvent") String id_votingEvent);


}
