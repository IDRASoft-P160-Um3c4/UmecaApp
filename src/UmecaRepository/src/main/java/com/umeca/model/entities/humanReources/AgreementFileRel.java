package com.umeca.model.entities.humanReources;

import com.umeca.model.entities.director.minutes.Agreement;
import com.umeca.model.entities.shared.UploadFileGeneric;

import javax.persistence.*;

@Entity
@Table(name = "agreement_file_rel")
public class AgreementFileRel{

    @Id
    @GeneratedValue
    @Column(name = "id_agreement_file_rel")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agreement")
    private Agreement agreement;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_upload_file_generic")
    private UploadFileGeneric uploadFileGeneric;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

    public UploadFileGeneric getUploadFileGeneric() {
        return uploadFileGeneric;
    }

    public void setUploadFileGeneric(UploadFileGeneric uploadFileGeneric) {
        this.uploadFileGeneric = uploadFileGeneric;
    }
}
