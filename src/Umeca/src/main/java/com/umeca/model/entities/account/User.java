package com.umeca.model.entities.account;

import com.umeca.model.entities.shared.EntityGrid;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Israel
 * Date: 4/24/14
 * Time: 10:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="user")
public class User implements EntityGrid {

    @Id
    @GeneratedValue
    @Column(name="id_user")
    private Long id;

    @Column(name="username", unique = true, length = 200, nullable = false)
    private String username;

    @Column(name="password", length = 1000, nullable = false)
    private String password;

    @Column(name="enabled", nullable = false)
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="user_role", joinColumns ={@JoinColumn(name = "id_user")}, inverseJoinColumns = {@JoinColumn(name = "id_role")})
    private List<Role> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
