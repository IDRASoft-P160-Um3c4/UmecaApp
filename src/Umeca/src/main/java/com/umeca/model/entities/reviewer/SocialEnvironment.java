package com.umeca.model.entities.reviewer;

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

    @Column(name="physical_condition", nullable=true, length = 500)
    private String physicalCondition;

    @OneToMany (mappedBy = "socialEnvironment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RelSocialEnvironmentActivity> relSocialEnvironmentActivities;

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

    public String getPhysicalCondition() {
        return physicalCondition;
    }

    public void setPhysicalCondition(String physicalCondition) {
        this.physicalCondition = physicalCondition;
    }

    public List<RelSocialEnvironmentActivity> getRelSocialEnvironmentActivities() {
        return relSocialEnvironmentActivities;
    }

    public void setRelSocialEnvironmentActivities(List<RelSocialEnvironmentActivity> relSocialEnvironmentActivities) {
        this.relSocialEnvironmentActivities = relSocialEnvironmentActivities;
    }
}
