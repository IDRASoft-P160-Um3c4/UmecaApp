package com.umeca.model.catalog;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="cat_channeling_type")
public class CatChannelingType {
    @Id
    @Column(name = "id_cat_channeling_type")
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Column(name = "institutions_types_ids", length = 500, nullable = false)
    private String institutionsTypesIds;

    @Column(name = "code", length = 10, nullable = false)
    private String code;

    @ManyToMany
    @JoinTable(name="rel_channelint_type_institution_type",
                    joinColumns={ @JoinColumn(name="id_cat_channeling_type", referencedColumnName="id_cat_channeling_type", unique=false) },
                    inverseJoinColumns={ @JoinColumn(name="id_cat_institution_type", referencedColumnName="id_cat_institution_type", unique=false) }
            )
    private List<CatInstitutionType> lstInstitutionType;


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

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public String getInstitutionsTypesIds() {
        return institutionsTypesIds;
    }

    public void setInstitutionsTypesIds(String institutionsTypesIds) {
        this.institutionsTypesIds = institutionsTypesIds;
    }

    public List<CatInstitutionType> getLstInstitutionType() {
        return lstInstitutionType;
    }

    public void setLstInstitutionType(List<CatInstitutionType> lstInstitutionType) {
        this.lstInstitutionType = lstInstitutionType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}