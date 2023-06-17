package com.licenta.vote.query.api.queries;

import com.licenta.cqrs.core.domain.BaseEntity;
import com.licenta.vote.query.api.queries.users.*;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindAllUsersQuery query);
    List<BaseEntity> handle(FindUserByIdQuery query);
    List<BaseEntity> handle(FindUserByPhoneNumberQuery query);
    List<BaseEntity> handle(FindUserByEmailQuery query);
    List<BaseEntity> handle(FindUserByCountryQuery query);
    List<BaseEntity> handle(FindUserByCountyQuery query);
    List<BaseEntity> handle(FindUsersByCityQuery query);
    List<BaseEntity> handle(FindUserByWorkPlaceQuery query);
}
