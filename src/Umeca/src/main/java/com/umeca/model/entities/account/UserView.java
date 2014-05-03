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
public class UserView implements EntityGrid {

    private Long id;

    private String username;

    private Boolean enabled;

    private String role;


    public UserView(Long id, String username, Boolean enabled, String role){
        this.id = id;
        this.username = username;
        this.enabled = enabled;
        this.role = role;
    }


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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
