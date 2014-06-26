package com.umeca.model.entities.supervisor;

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

    @Column(name = "address", nullable = false)
    private String addressTxt;

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
        this.nameTxt = nameTxt;
    }

    public String getPhoneTxt() {
        return phoneTxt;
    }

    public void setPhoneTxt(String phoneTxt) {
        this.phoneTxt = phoneTxt;
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
}
