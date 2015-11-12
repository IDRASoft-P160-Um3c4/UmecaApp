package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.HomeType;
import com.umeca.model.catalog.RegisterType;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Schedule;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "framing_address")
public class FramingAddress {

    @Id
    @GeneratedValue
    @Column(name = "id_framing_address")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_framing_meeting")
    private FramingMeeting framingMeeting;

    @OneToMany(mappedBy = "framingAddress", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Schedule> schedule;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;

    @Column(name = "time_ago")
    private String timeAgo;

    @Column(name = "time_live")
    private String timeLive;

    @Column(name = "address_ref")
    private String addressRef;

    @Column(name = "reason_another")
    private String reasonAnother;

    @Column(name = "phone")
    private String phone;

    @Column(name = "reason_change")
    private String reasonChange;

    @Column(name = "specification")
    private String specification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_register_type")
    private RegisterType registerType;

    @ManyToOne
    @JoinColumn(name = "id_belong")
    private HomeType homeType;

    @Column(name = "is_homeless")
    private Boolean isHomeless;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FramingMeeting getFramingMeeting() {
        return framingMeeting;
    }

    public void setFramingMeeting(FramingMeeting framingMeeting) {
        this.framingMeeting = framingMeeting;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAddressRef() {
        return addressRef;
    }

    public void setAddressRef(String addressRef) {
        this.addressRef = addressRef;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getTimeLive() {
        return timeLive;
    }

    public void setTimeLive(String timeLive) {
        this.timeLive = timeLive;
    }

    public String getReasonAnother() {
        return reasonAnother;
    }

    public void setReasonAnother(String reasonAnother) {
        this.reasonAnother = reasonAnother;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReasonChange() {
        return reasonChange;
    }

    public void setReasonChange(String reasonChange) {
        this.reasonChange = reasonChange;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public RegisterType getRegisterType() {
        return registerType;
    }

    public void setRegisterType(RegisterType registerType) {
        this.registerType = registerType;
    }

    public HomeType getHomeType() {
        return homeType;
    }

    public void setHomeType(HomeType homeType) {
        this.homeType = homeType;
    }

    public Boolean isComplete() {

        if (this.homeType == null || this.registerType == null)
            return false;

        if (this.homeType.getName().toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_OTHER) && (this.specification == null || this.specification.trim().equals("")))
            return false;

        if (this.isHomeless != null && this.isHomeless.equals(false))
            if (this.phone == null || this.phone.trim().equals(""))
                return false;

        if (this.registerType.getName().toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_ACTUAL)) {

            if (this.timeAgo == null || this.timeAgo.trim().equals(""))
                return false;

            if (this.addressRef == null || this.addressRef.trim().equals(""))
                return false;

            if (this.isHomeless != null && this.isHomeless.equals(false))
                if (this.schedule == null || !(this.schedule.size() > 0))
                    return false;

        } else if (this.registerType.getName().toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_PREV)) {

            if (this.timeLive == null || this.timeLive.trim().equals(""))
                return false;

            if (this.reasonChange == null || this.reasonChange.trim().equals(""))
                return false;

        } else if (this.registerType.getName().toLowerCase().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_SECONDARY)) {

            if (this.timeAgo == null || this.timeAgo.trim().equals(""))
                return false;

            if (this.addressRef == null || this.addressRef.trim().equals(""))
                return false;

            if (this.reasonAnother == null || this.reasonAnother.trim().equals(""))
                return false;

            if (this.schedule == null || !(this.schedule.size() > 0))
                return false;
        }

        return true;
    }

    public Boolean getIsHomeless() {
        return isHomeless;
    }

    public void setIsHomeless(Boolean isHomeless) {
        this.isHomeless = isHomeless;
    }
}

