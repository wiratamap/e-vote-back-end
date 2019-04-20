package com.personal.evote.lookup.appuser.model;

import com.personal.evote.common.base.model.AuditModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "schema_lookup")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser extends AuditModel {
    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;
}
