package com.umeca.model.entities.humanReources;

import com.umeca.model.entities.director.minutes.Agreement;
import com.umeca.model.entities.shared.UploadFileGeneric;

import javax.persistence.*;

@Entity
@Table(name = "rel_document_generic_file")
public class RelDocumentUploadGenericFile{

    @Id
    @GeneratedValue
    @Column(name = "id_agreement_file_rel")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_document")
    private Document document;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_upload_file_generic")
    private UploadFileGeneric uploadFileGeneric;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public UploadFileGeneric getUploadFileGeneric() {
        return uploadFileGeneric;
    }

    public void setUploadFileGeneric(UploadFileGeneric uploadFileGeneric) {
        this.uploadFileGeneric = uploadFileGeneric;
    }
}
