package com.licenta.vote.query.domain;

import com.licenta.cqrs.core.domain.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VotingEventRepository extends CrudRepository <VotingEvent, String>{

}
