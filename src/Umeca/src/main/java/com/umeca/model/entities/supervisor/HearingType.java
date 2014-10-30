package com.umeca.model.entities.supervisor;

import javax.persistence.*;

@Entity
@Table(name = "cat_hearing_type")
public class HearingType {

    @Id
    @GeneratedValue
    @Column(name = "id_hearing_type")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "is_obsolete")
    private Boolean isObsolete;

    @Column(name = "lock_arrangements")
    private Boolean lock;

    @Column(name = "specification")
    private Boolean specification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public Boolean getSpecification() {
        return specification;
    }

    public void setSpecification(Boolean specification) {
        this.specification = specification;
    }
}
