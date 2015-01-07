package com.umeca.model.entities.supervisorManager;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "rol_activity")
public class RolActivity {

    @Id
    @GeneratedValue
    @Column(name = "id_rol_activity", nullable = false)
    private Long id;

    @Column(name = "start", nullable = false)
    private Calendar start;

    @Column(name= "search_start", nullable = false)
    private int searchStart;

    @Column(name = "end", nullable = false)
    private Calendar end;

    @Column(name= "search_end", nullable = false)
    private int searchEnd;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_supervisor", nullable = false)
    private User supervisor;

    @Column(name = "status", length = 100, nullable = false)
    private String status;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user_create", nullable = false)
    private User userCreate;

    @Column(name = "creation_time", nullable = false)
    private Calendar creationTime;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user_modify", nullable = true)
    private User userModify;

    @Column(name = "modify_time", nullable = true)
    private Calendar modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public int getSearchStart() {
        return searchStart;
    }

    public void setSearchStart(int searchStart) {
        this.searchStart = searchStart;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public int getSearchEnd() {
        return searchEnd;
    }

    public void setSearchEnd(int searchEnd) {
        this.searchEnd = searchEnd;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(User userCreate) {
        this.userCreate = userCreate;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public User getUserModify() {
        return userModify;
    }

    public void setUserModify(User userModify) {
        this.userModify = userModify;
    }

    public Calendar getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Calendar modifyTime) {
        this.modifyTime = modifyTime;
    }
}
