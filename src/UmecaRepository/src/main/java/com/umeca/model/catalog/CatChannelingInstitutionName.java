package com.umeca.model.catalog;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="cat_channeling_institution_name")
public class CatChannelingInstitutionName {
    @Id
    @Column(name = "id_cat_channeling_institution_name")
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cat_channeling_type", nullable = false)
    private CatChannelingType channelingType;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CatChannelingType getChannelingType() {
        return channelingType;
    }

    public void setChannelingType(CatChannelingType channelingType) {
        this.channelingType = channelingType;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

}
