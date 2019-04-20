package com.personal.evote.lookup.candidate.model;

import com.personal.evote.common.base.model.AuditModel;
import com.personal.evote.lookup.candidatecategory.model.CandidateCategory;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "schema_lookup")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate extends AuditModel {
    @Column
    private Integer candidateNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CandidateCategory candidateCategory;
}
