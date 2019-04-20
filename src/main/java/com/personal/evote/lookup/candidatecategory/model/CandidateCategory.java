package com.personal.evote.lookup.candidatecategory.model;

import com.personal.evote.common.base.model.AuditModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "schema_lookup")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateCategory extends AuditModel {
    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Boolean isActive = true;
}
