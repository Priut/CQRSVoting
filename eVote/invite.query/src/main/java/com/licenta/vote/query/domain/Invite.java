package com.licenta.vote.query.domain;

import com.licenta.cqrs.core.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "invitesTable", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_user", "id_votingEvent"})
})
public class Invite extends BaseEntity {
    @Id
    private String id;
    private String id_user;
    private String id_votingEvent;
    private Boolean status;
}