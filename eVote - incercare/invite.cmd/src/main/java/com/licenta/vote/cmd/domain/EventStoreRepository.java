package com.licenta.vote.cmd.domain;

import com.licenta.cqrs.core.events.EventModel;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<InviteEventModel> findAll(String collectionName) {
        return mongoTemplate.findAll(InviteEventModel.class, collectionName);
    }
}
