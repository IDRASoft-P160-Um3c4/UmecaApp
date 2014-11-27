package com.umeca.model.entities.supervisor;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 25/11/14
 * Time: 06:22 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="cat_activity_group")
public class ActivityGroup {
    @Id
    @GeneratedValue
    @Column(name = "id_activity_goal")
    private Long id;

    @Column(name = "code", length = 100, nullable = false)
    private String code;

    @Column(name="description", length = 255)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
