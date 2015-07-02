package com.umeca.model.entities.supervisor;

import javax.persistence.*;

@Entity
@Table(name = "supervision_activity")
public class SupervisionActivity {

    @Id
    @GeneratedValue
    @Column(name = "id_supervision_activity")
    private Long id;

    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @Column(name = "description", length = 1500, nullable = false)
    private String description;

    @Column(name = "is_obsolete", nullable = false)
    private Boolean isObsolete;

    @Column(name = "specification", nullable = false)
    private Boolean specification;

    @Column(name = "code", length = 20, nullable = false)
    private String code;

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

    public Boolean getSpecification() {
        return specification;
    }

    public void setSpecification(Boolean specification) {
        this.specification = specification;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
