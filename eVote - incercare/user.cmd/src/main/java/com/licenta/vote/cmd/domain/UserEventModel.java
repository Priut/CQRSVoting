package com.licenta.vote.cmd.domain;

import com.licenta.cqrs.core.events.BaseEvent;
import com.licenta.cqrs.core.events.EventModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserEventModel extends EventModel {
}
