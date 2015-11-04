package com.umeca.model.entities.reviewer;

import com.umeca.model.entities.supervisor.FramingMeeting;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 04:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="social_network")
public class SocialNetwork {

    @Id
    @GeneratedValue
    @Column(name="id_social_network")
    private Long id;

    @Column(name="comment")
    private String comment;

    @OneToMany(mappedBy="socialNetwork", cascade={CascadeType.ALL})
    private List<PersonSocialNetwork> peopleSocialNetwork;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false, unique = true)
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "id_framing_meeting")
    FramingMeeting framingMeeting;


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

    public List<PersonSocialNetwork> getPeopleSocialNetwork() {
        return peopleSocialNetwork;
    }

    public void setPeopleSocialNetwork(List<PersonSocialNetwork> peopleSocialNetwork) {
        this.peopleSocialNetwork = peopleSocialNetwork;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}
