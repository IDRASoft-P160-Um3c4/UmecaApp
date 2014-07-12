package com.umeca.model.entities.supervisor;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name="cat_framing_risk")
public class FramingRisk {

    @Id
    @GeneratedValue
    @Column(name="id_framing_risk")
    private Long id;

    @Column(name="description")
    private String description;

    @Column(name="risk_index")
    private Long index;

    @Column(name="is_obsolete")
    private Boolean isObsolete;

    @Transient
    public static final Comparator<FramingRisk> framingRiskComparator= new Comparator<FramingRisk>() {
        @Override
        public int compare(FramingRisk h1, FramingRisk h2) {
            return  h1.getIndex().compareTo(h2.getIndex());
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

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}
