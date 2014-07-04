package com.umeca.model.catalog;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name="cat_arrangement")
public class Arrangement {
    @Id
    @GeneratedValue
    @Column(name = "id_arrangement")
    private Long id;

    @Column(name = "description", length = 1500, nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    private Integer type;

    @Column(name = "is_national", nullable = false)
    private Boolean isNational;

    @Column(name = "arrangement_index", nullable = false)
    private Integer index;

    @Column(name = "is_obsolete", nullable = false)
    private Boolean isObsolete;

    @Transient
    public static final Comparator<Arrangement> arrangementComparator = new Comparator<Arrangement>() {
        @Override
        public int compare(Arrangement q1, Arrangement q2) {
            return  q1.getIndex().compareTo(q2.getIndex());
        }
    };

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

    public Boolean getIsNational() {
        return isNational;
    }

    public void setIsNational(Boolean isNational) {
        this.isNational = isNational;
    }
}
