package com.umeca.model.entities.account;

import com.umeca.model.shared.EntityGrid;

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

    private String fullname;

    private String email;

    private String role;


    public UserView(Long id, String username, String fullname, String email, Boolean enabled, String role){
        this.id = id;
        this.username = username;
        this.enabled = enabled;
        this.fullname = fullname;
        this.email = email;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
