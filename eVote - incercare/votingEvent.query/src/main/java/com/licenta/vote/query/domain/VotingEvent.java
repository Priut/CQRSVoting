package com.licenta.vote.query.domain;

import com.licenta.cqrs.core.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "votingEventTable")
public class VotingEvent extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String start_date;
    private String finish_date;
    private String winner;

    @ManyToMany
    @JoinTable(name = "voting_event_candidate",
            joinColumns = @JoinColumn(name = "voting_event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "id"))
    private Set<Candidate> candidates;
}