package com.umeca.model.entities.reviewer;

import com.umeca.model.catalogs.Activity;
import com.umeca.model.catalogs.PhysicalCondition;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 01:11 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="social_environment")
public class SocialEnvironment {

    @Id
    @GeneratedValue
    @Column(name="id_social_environment")
    private Long id;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="rel_social_environment_physical_condition", joinColumns={@JoinColumn(name="id_social_environment")}, inverseJoinColumns={@JoinColumn(name="id_physical_condition")})
    private List<PhysicalCondition> physicalConditions;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="rel_social_environment_activity", joinColumns={@JoinColumn(name="id_social_environment")}, inverseJoinColumns={@JoinColumn(name="id_activity")})
    private List<Activity> activities;

    @Column(name="comment", length = 1000, nullable = true)
    private String comment;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PhysicalCondition> getPhysicalConditions() {
        return physicalConditions;
    }

    public void setPhysicalConditions(List<PhysicalCondition> physicalConditions) {
        this.physicalConditions = physicalConditions;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}
