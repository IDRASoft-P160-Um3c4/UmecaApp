package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Activity;
import com.umeca.model.entities.reviewer.SocialEnvironment;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 18/06/14
 * Time: 06:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="rel_framing_meeting_activity")
public class RelFramingMeetingActivity {

    @Id
    @GeneratedValue
    @Column(name="id_rel")
    private Long relId;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_activity", nullable = false)
    private Activity activity;

    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_framing_meeting", nullable = false)
    private FramingMeeting framingMeeting;

    @Column(name="specification", length = 255, nullable = true)
    private String specification;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public FramingMeeting getFramingMeeting() {
        return framingMeeting;
    }

    public void setFramingMeeting(FramingMeeting framingMeeting) {
        this.framingMeeting = framingMeeting;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }
}
