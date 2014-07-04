package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Degree;
import com.umeca.model.entities.reviewer.dto.GroupMessageMeetingDto;
import com.umeca.model.entities.reviewer.dto.TerminateMeetingMessageDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="school")
public class School {

    @Id
    @GeneratedValue
    @Column(name="id_school")
    private Long id;

    @Column(name="name", length = 200, nullable = false)
    private String name;

    @Column(name="phone", length = 25, nullable = true)
    private String phone;

    @Column(name="address", length = 255, nullable = true)
    private String address;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_grade", nullable = false)
    private Degree degree;

    @OneToMany(mappedBy="school", cascade={CascadeType.ALL})
    private List<Schedule> schedule;

    @OneToOne(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void validateMeeting(TerminateMeetingMessageDto t) {
        List<String> r = new ArrayList<>();
        String e = "entity";
        if(name==null ||(name!=null && name.trim().equals(""))){
            r.add(t.template.replace(e,"La escuela"));
        }
        if(phone==null || (phone!=null && phone.trim().equals(""))){
            r.add(t.template.replace(e,"El teléfono"));
        }
        if(address==null || (address!=null && address.trim().equals(""))){
            r.add(t.template.replace(e,"La dirección"));
        }
        if(degree==null){
            r.add(t.template.replace(e,"El grado escolar"));
        }
        t.getGroupMessage().add(new GroupMessageMeetingDto("school",r));
    }
}
