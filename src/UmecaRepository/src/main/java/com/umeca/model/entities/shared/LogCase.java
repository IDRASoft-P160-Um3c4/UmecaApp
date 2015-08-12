package com.umeca.model.entities.shared;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 25/11/14
 * Time: 05:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "log_case")
public class LogCase {

    public LogCase() {
    }

    public LogCase(Calendar date, Calendar dateCompletion, String activity, String userName, String title, String resume) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        this.date = date;
        this.dateString = format1.format(date.getTime());
        this.dateCompletionString = dateCompletion == null ? null : format1.format(dateCompletion.getTime());
        this.activity = activity;
        this.userName = userName;
        this.title = title;
        this.resume = resume;
    }

    @Id
    @GeneratedValue
    @Column(name = "id_log_case", nullable = false)
    private Long id;

    @Column(name = "date")
    private Calendar date;

    @Column(name = "completion_date")
    private Calendar completionDate;

    @JoinColumn(name = "activity", nullable = false)
    private String activity;

    @Column(name = "resume", length = 1500)
    private String resume;

    @Column(name = "title", length = 500)
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_case", nullable = false)
    private Case caseDetention;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Transient
    private String activityString;

    @Transient
    private String userName;

    @Transient
    private String dateString;

    @Transient
    private String dateCompletionString;

    @Transient
    private String timeString;

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

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
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

    public String getActivityString() {
        return activityString;
    }

    public void setActivityString(String activityString) {
        this.activityString = activityString;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public Calendar getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Calendar completionDate) {
        this.completionDate = completionDate;
    }

    public String getDateCompletionString() {
        return dateCompletionString;
    }

    public void setDateCompletionString(String dateCompletionString) {
        this.dateCompletionString = dateCompletionString;
    }
}
