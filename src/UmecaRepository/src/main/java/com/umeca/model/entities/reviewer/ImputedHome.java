package com.umeca.model.entities.reviewer;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.model.catalog.Election;
import com.umeca.model.catalog.HomeType;
import com.umeca.model.catalog.RegisterType;
import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name="imputed_home")
public class ImputedHome implements EntityGrid{

    public ImputedHome() {
    }

    public ImputedHome(Long id, String addressString, String timeLive, String registerTypeString, String belongString) {
        this.id=id;
        this.addressString = addressString;
        this.timeLive = timeLive;
        this.registerTypeString = registerTypeString;
        this.belongString = belongString;
    }

    @Id
    @GeneratedValue
    @Column(name="id_imputed_home")
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_address", nullable = false)
    private Address address;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_register_type", nullable = false)
    private RegisterType registerType;

    @Column(name="time_live", nullable = true, length = 30)
    private String timeLive;

    @ManyToOne
    @JoinColumn(name="id_belong", nullable = false)
    private HomeType homeType;

    @Column(name="reason_change", length = 500, nullable = true)
    private String reasonChange;

    @Column(name="description", length = 500, nullable = true)
    private String description;

    @OneToMany(mappedBy="imputedHome", cascade={CascadeType.ALL})
    private List<Schedule> schedule;

    @Column(name="phone", length = 200)
    private String phone;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

    @Column(name="specification", length = 50)
    private String specification;

    @Column(name="reasonSecondary", length = 500)
    private String reasonSecondary;

    @Column(name="is_homeless")
    private Boolean isHomeless;

    @Transient
    private String addressString;

    @ManyToOne
    @JoinColumn(name = "id_framing_meeting")
    FramingMeeting framingMeeting;

    @Transient
    private String registerTypeString;

    @Transient
    private String belongString;

    @Transient
    private Long idAux;

    @Transient
    public static final Comparator<ImputedHome> imputedHomeComparator= new Comparator<ImputedHome>() {
        @Override
        public int compare(ImputedHome h1, ImputedHome h2) {
            return  h1.getRegisterType().getName().compareTo(h2.getRegisterType().getName());
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public RegisterType getRegisterType() {
        return registerType;
    }

    public void setRegisterType(RegisterType registerType) {
        this.registerType = registerType;
    }

    public String getTimeLive() {
        return timeLive;
    }

    public void setTimeLive(String timeLive) {
        this.timeLive = timeLive;
    }

    public String getReasonChange() {
        return reasonChange;
    }

    public void setReasonChange(String reasonChange) {
        this.reasonChange = StringExt.substringMax(reasonChange,500);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = StringExt.substringMax(description,500);
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }


    public HomeType getHomeType() {

        return homeType;
    }

    public void setHomeType(HomeType homeType) {
        this.homeType = homeType;
    }

    public String getAddressString() {
        return addressString;
    }

    public void setAddressString(String addressString) {
        this.addressString = addressString;
    }

    public String getRegisterTypeString() {
        return registerTypeString;
    }

    public void setRegisterTypeString(String registerTypeString) {
        this.registerTypeString = registerTypeString;
    }

    public String getBelongString() {
        return belongString;
    }

    public void setBelongString(String belongString) {
        this.belongString = belongString;
    }

    public FramingMeeting getFramingMeeting() {
        return framingMeeting;
    }

    public void setFramingMeeting(FramingMeeting framingMeeting) {
        this.framingMeeting = framingMeeting;
    }

    public Long getIdAux() {
        return idAux;
    }

    public void setIdAux(Long idAux) {
        this.idAux = idAux;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = StringExt.substringMax(specification,50);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = StringExt.substringMax(phone,200);
    }

    public String getReasonSecondary() {
        return reasonSecondary;
    }

    public void setReasonSecondary(String reasonSecondary) {
        this.reasonSecondary = StringExt.substringMax(reasonSecondary,500);
    }

    public Boolean getIsHomeless() {
        return isHomeless;
    }

    public void setIsHomeless(Boolean isHomeless) {
        this.isHomeless = isHomeless;
    }
}
