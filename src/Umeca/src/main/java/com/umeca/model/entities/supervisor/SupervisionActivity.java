package com.umeca.model.entities.supervisor;

import javax.persistence.*;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/10/14
 * Time: 4:36 PM
 */

@Entity
@Table(name = "supervision_activity")
public class SupervisionActivity {

    @Id
    @GeneratedValue
    @Column(name = "id_supervision_activity")
    private Long id;

    @Column(name = "name", length = 1500, nullable = false)
    private String name;

    @Column(name = "is_obsolete", nullable = false)
    private Boolean isObsolete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getObsolete() {
        return isObsolete;
    }

    public void setObsolete(Boolean obsolete) {
        isObsolete = obsolete;
    }
}
