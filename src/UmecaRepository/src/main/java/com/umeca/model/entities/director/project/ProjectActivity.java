package com.umeca.model.entities.director.project;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.UploadFileGeneric;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "project_activity")
public class ProjectActivity {

    @Id
    @GeneratedValue
    @Column(name = "id_project_activity", nullable = false)
    private Long id;

    @Column(name="creation_date", nullable = false)
    private Calendar creationDate;

    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @Column(name="description", length = 500, nullable = false)
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creator_user", nullable = false)
    private User creatorUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project", nullable = false)
    private Project project;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_upload_file_generic", nullable = true)
    UploadFileGeneric uploadFileGeneric;

    @Column(name="is_obsolete", nullable = false)
    private Boolean isObsolete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public UploadFileGeneric getUploadFileGeneric() {
        return uploadFileGeneric;
    }

    public void setUploadFileGeneric(UploadFileGeneric uploadFileGeneric) {
        this.uploadFileGeneric = uploadFileGeneric;
    }
}
