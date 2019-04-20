package com.personal.evote.core.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "schema_core")
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class RunningVote {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column
    private UUID voterId;

    @Column
    private UUID candidateId;

    @Column
    private UUID candidateCategoryId;

    @Column
    @CreatedDate
    private LocalDateTime createdDate;
}
