package com.licenta.vote.cmd.domain;

import com.licenta.cqrs.core.events.EventModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "invitesEventStore")
public class InviteEventModel extends EventModel {
}
