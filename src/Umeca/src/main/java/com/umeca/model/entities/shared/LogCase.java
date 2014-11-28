package com.umeca.model.entities.shared;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.ActivityGoal;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 25/11/14
 * Time: 05:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="log_case")
public class LogCase {

    @Id
    @GeneratedValue
    @Column(name = "id_log_case", nullable = false)
    private Long id;

    @Column(name="date")
    private Calendar date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_activity_goal", nullable = false)
    private ActivityGoal activity;

    @Column(name="resume", length = 1500)
    private String resume;

    @Column(name="title", length = 500)
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_case", nullable = false)
    private Case caseDetention;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public ActivityGoal getActivity() {
        return activity;
    }

    public void setActivity(ActivityGoal activity) {
        this.activity = activity;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }
}
