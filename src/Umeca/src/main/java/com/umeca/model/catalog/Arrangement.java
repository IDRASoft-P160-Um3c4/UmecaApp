package com.umeca.model.catalog;

import javax.persistence.*;

@Entity
@Table(name="arrangement")
public class Arrangement {
    @Id
    @GeneratedValue
    @Column(name = "id_arrangement")
    private Long id;

    @Column(name = "description", length = 1500, nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    private Integer type;

    @Column(name = "arrangement_index", nullable = false)
    private Integer index;

    @Column(name = "is_obsolete", nullable = false)
    private Boolean isObsolete;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}
