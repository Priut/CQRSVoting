package com.licenta.vote.cmd.domain;

import com.licenta.cqrs.core.events.EventModel;
import com.licenta.vote.CustomExceptions.UniqueConstraintViolationException;
import com.licenta.vote.common.events.UserInvitedEvent;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class EventStoreRepository  {

    private final MongoTemplate mongoTemplate;

    public EventStoreRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public <T extends EventModel> T save(T eventModel, String collectionName) {
        return mongoTemplate.save(eventModel, collectionName);
    }


    public List<InviteEventModel> findByAggregateIdentifier(String aggregateIdentifier, String collectionName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("aggregateIdentifier").is(aggregateIdentifier));
        return mongoTemplate.find(query, InviteEventModel.class, collectionName);
    }
    public void verifyExistingInvite(String userId, String votingEventId, String collectionName) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("eventData.id_user").is(userId)
                        .and("eventData.id_votingEvent").is(votingEventId)
        );

        List<InviteEventModel> existingInvites = mongoTemplate.find(query, InviteEventModel.class, collectionName);
        if (!existingInvites.isEmpty()) {
            throw new UniqueConstraintViolationException("Invite already exists!");
        }
    }

    public List<InviteEventModel> findAll(String collectionName) {
        return mongoTemplate.findAll(InviteEventModel.class, collectionName);
    }
}
