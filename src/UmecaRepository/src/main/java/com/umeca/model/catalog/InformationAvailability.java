package com.umeca.model.catalog;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="cat_information_availability")
public class InformationAvailability {
    @Id
    @Column(name="information_availability")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="is_obsolete")
    private Boolean isObsolete;

    @Column(name="specification")
    private Boolean specification;

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
}
