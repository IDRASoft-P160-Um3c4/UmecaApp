package com.umeca.model.entities.shared;

import com.umeca.model.catalog.CatFileType;
import com.umeca.model.catalog.catalog.TypeNameFile;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "upload_file")
public class UploadFile {
    @Id
    @GeneratedValue
    @Column(name = "id_upload_file")
    private Long id;

    @Column(name = "path", length = 2000, nullable = false)
    private String path;

    @Column(name = "file_name", length = 2000, nullable = false)
    private String fileName;

    @Column(name = "real_file_name", length = 2000, nullable = false)
    private String realFileName;

    @Column(name = "description", length = 2000, nullable = false)
    private String description;

    @Column(name = "size", nullable = false)
    private Long size;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_case_detention", nullable = false)
    private Case caseDetention;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_file_type", nullable = false)
    private CatFileType fileType;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_creation_user", nullable = false)
    private User creationUser;

    @Column(name = "creation_time", nullable = false)
    private Calendar creationTime;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_delete_user", nullable = true)
    private User deleteUser;

    @Column(name = "delete_time", nullable = true)
    private Calendar deleteTime;

    @Column(name="is_obsolete")
    private Boolean isObsolete;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_type_name", nullable = false)
    private TypeNameFile typeNameFile;

    public UploadFile() {
    }

    public UploadFile(String path, String realFileName, String fileName) {
        this.path = path;
        this.realFileName = realFileName;
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public CatFileType getFileType() {
        return fileType;
    }

    public void setFileType(CatFileType fileType) {
        this.fileType = fileType;
    }

    public User getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(User creationUser) {
        this.creationUser = creationUser;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public User getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(User deleteUser) {
        this.deleteUser = deleteUser;
    }

    public Calendar getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Calendar deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Boolean getObsolete() {
        return isObsolete;
    }

    public void setObsolete(Boolean obsolete) {
        isObsolete = obsolete;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }
}