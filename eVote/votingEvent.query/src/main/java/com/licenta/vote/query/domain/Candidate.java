package com.licenta.vote.query.domain;


import com.licenta.cqrs.core.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "candidateTable")
public class Candidate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}