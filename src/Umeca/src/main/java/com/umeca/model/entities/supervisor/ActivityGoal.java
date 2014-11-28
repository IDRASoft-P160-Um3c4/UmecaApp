package com.umeca.model.entities.supervisor;

import javax.persistence.*;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/10/14
 * Time: 4:36 PM
 */

@Entity
@Table(name = "activity_goal")
public class ActivityGoal {

    @Id
    @GeneratedValue
    @Column(name = "id_activity_goal")
    private Long id;

    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @Column(name = "description", length = 1500, nullable = false)
    private String description;

    @Column(name = "is_obsolete", nullable = false)
    private Boolean isObsolete;

    @Column(name = "specification", nullable = false)
    private Boolean specification;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_activity_group", nullable = false)
    private ActivityGroup activityGroup;

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

    public ActivityGroup getActivityGroup() {
        return activityGroup;
    }

    public void setActivityGroup(ActivityGroup activityGroup) {
        this.activityGroup = activityGroup;
    }
}
