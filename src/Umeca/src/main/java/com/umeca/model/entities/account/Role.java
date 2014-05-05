package com.umeca.model.entities.account;

import com.umeca.model.shared.EntityGrid;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Israel
 * Date: 4/24/14
 * Time: 10:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="role")
public class Role implements EntityGrid {

    @Id
    @GeneratedValue
    @Column(name="id_role")
    private Long id;


    @Column(name="role", unique = true, length = 100, nullable = false)
    private String role;


    @Column(name="description", length = 1024, nullable = false)
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
