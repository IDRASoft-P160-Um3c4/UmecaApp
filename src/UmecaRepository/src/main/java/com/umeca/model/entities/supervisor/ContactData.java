package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.model.shared.Constants;

import javax.persistence.*;

@Entity
@Table(name="contact_data")
public class ContactData {


    @Id
    @GeneratedValue
    @Column(name = "id_contact_data")
    private Long id;

    @Column(name = "name", nullable = false)
    private String nameTxt;

    @Column(name = "phone", nullable = false)
    private String phoneTxt;

    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name = "address", nullable = false)
    private String addressTxt;

    @Column(name = "live_with", nullable = false)
    private Boolean liveWith;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_hearing_format", nullable = false)
    private HearingFormat hearingFormat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(String nameTxt) {
        this.nameTxt = StringExt.substringMax(nameTxt, Constants.DEFAULT_LEN_STRING);
    }

    public String getPhoneTxt() {
        return phoneTxt;
    }

    public void setPhoneTxt(String phoneTxt) {
        this.phoneTxt = StringExt.substringMax(phoneTxt, Constants.DEFAULT_LEN_STRING);
    }

    public String getAddressTxt() {
        return addressTxt;
    }

    public void setAddressTxt(String addressTxt) {
        this.addressTxt = addressTxt;
    }

    public HearingFormat getHearingFormat() {
        return hearingFormat;
    }

    public void setHearingFormat(HearingFormat hearingFormat) {
        this.hearingFormat = hearingFormat;
    }

    public Boolean getLiveWith() {
        return liveWith;
    }

    public void setLiveWith(Boolean liveWith) {
        this.liveWith = liveWith;
    }
}
