package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.RegisterType;
import com.umeca.model.shared.EntityGrid;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 * */

@Entity
@Table(name="job")
public class Job implements EntityGrid{

    public Job() {
    }

    public Job(Long id,String post, String nameHead, String company, String phone, String registerTypeString, Long registerTypeId) {
        this.id= id;
        this.post = post;
        this.nameHead = nameHead;
        this.company = company;
        this.phone = phone;
        this.registerTypeString = registerTypeString;
        this.registerTypeId = registerTypeId;
    }

    @Id
    @GeneratedValue
    @Column(name="id_job")
    private Long id;

    @Column(name="post", length = 50, nullable = false)
    private String post;

    @Column(name="name_head", length = 150, nullable = false)
    private String nameHead;

    @Column(name="company", length = 150, nullable = false)
    private String company;

    @Column(name = "phone", nullable = true, length = 30)
    private String phone;

    @Column(name="startPrev")
    private Date startPrev;

    @Column(name ="start")
    private Date start;

    @Column(name = "salary_week", nullable = true)
    private Float salaryWeek;

    @Column(name="end", nullable = true)
    private Date end;

    @Column(name="reason_change", nullable = true, length = 1000)
    private String reasonChange;

     @Column(name="address", nullable = true, length = 1000)
    private String address;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_register_type", nullable = false)
    private RegisterType registerType;

    @OneToMany(mappedBy="job", cascade={CascadeType.ALL})
    private List<Schedule> schedule;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

    @Transient
    private String registerTypeString;

    @Transient
    private Long registerTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNameHead() {
        return nameHead;
    }

    public void setNameHead(String nameHead) {
        this.nameHead = nameHead;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Float getSalaryWeek() {
        return salaryWeek;
    }

    public void setSalaryWeek(Float salaryWeek) {
        this.salaryWeek = salaryWeek;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getReasonChange() {
        return reasonChange;
    }

    public void setReasonChange(String reasonChange) {
        this.reasonChange = reasonChange;
    }

    public RegisterType getRegisterType() {
        return registerType;
    }

    public void setRegisterType(RegisterType registerType) {
        this.registerType = registerType;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public String getRegisterTypeString() {
        return registerTypeString;
    }

    public void setRegisterTypeString(String registerTypeString) {
        this.registerTypeString = registerTypeString;
    }

    public Long getRegisterTypeId() {
        return registerTypeId;
    }

    public void setRegisterTypeId(Long registerTypeId) {
        this.registerTypeId = registerTypeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartPrev() {
        return startPrev;
    }

    public void setStartPrev(Date startPrev) {
        this.startPrev = startPrev;
    }
}
