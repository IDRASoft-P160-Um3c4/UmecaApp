package com.umeca.model.entities.director.minutes;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.model.entities.humanReources.RequestMinute;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "minute")
public class Minute {

    @Id
    @GeneratedValue
    @Column(name = "id_minute")
    private Long id;

    @Column(name = "title",length = 500)
    private String title;

    @Column(name = "agenda",length = 750)
    private String agenda;

    @Column(name = "minute_date")
    private Date minuteDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee attendant;

    @Column(name = "place", length = 500)
    private String place;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(name = "finish_date")
    private Date finishDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_finish_user")
    private User finishUser;

    @Column(name = "finish_comment",length = 750)
    private String finishComment;

    @OneToMany(mappedBy = "minute", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assistant> assistants;

    @OneToMany(mappedBy = "minute", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agreement> agreements;

    @OneToMany(mappedBy = "minute", cascade = CascadeType.ALL)
    private List<RequestMinute> requestMinutes;

    @Column(name = "st_code")
    private String stCode;

    public Minute() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public Date getMinuteDate() {
        return minuteDate;
    }

    public void setMinuteDate(Date minuteDate) {
        this.minuteDate = minuteDate;
    }

    public Employee getAttendant() {
        return attendant;
    }

    public void setAttendant(Employee attendant) {
        this.attendant = attendant;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Assistant> getAssistants() {
        return assistants;
    }

    public void setAssistants(List<Assistant> assistants) {
        this.assistants = assistants;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public List<Agreement> getAgreements() {
        return agreements;
    }

    public void setAgreements(List<Agreement> agreements) {
        this.agreements = agreements;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public User getFinishUser() {
        return finishUser;
    }

    public void setFinishUser(User finishUser) {
        this.finishUser = finishUser;
    }

    public List<RequestMinute> getRequestMinutes() {
        return requestMinutes;
    }

    public void setRequestMinutes(List<RequestMinute> requestMinutes) {
        this.requestMinutes = requestMinutes;
    }

    public String getStCode() {
        return stCode;
    }

    public void setStCode(String stCode) {
        this.stCode = stCode;
    }

    public String getFinishComment() {
        return finishComment;
    }

    public void setFinishComment(String finishComment) {
        this.finishComment = finishComment;
    }


}
