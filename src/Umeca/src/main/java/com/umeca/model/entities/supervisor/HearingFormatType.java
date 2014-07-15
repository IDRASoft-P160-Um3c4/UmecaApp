package com.umeca.model.entities.supervisor;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name="cat_hearing_format_type")
public class HearingFormatType {
    @Id
    @GeneratedValue
    @Column(name = "id_hearing_format_type")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1500, nullable = false)
    private String description;

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
}
