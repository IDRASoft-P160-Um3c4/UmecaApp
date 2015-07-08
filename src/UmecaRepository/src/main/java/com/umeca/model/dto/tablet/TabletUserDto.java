package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletDocumentTypeDto;
import com.umeca.model.dto.tablet.catalog.TabletElectionDto;
import com.umeca.model.dto.tablet.catalog.TabletRelationshipDto;

public class TabletUserDto {

    private Long id;
    private String fullname;
    private String hPassword;
    private String roleCode;
    private String guid;

    public TabletUserDto() {

    }

    public TabletUserDto(Long id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    public TabletUserDto(Long id, String fullname, String hPassword) {
        this.id = id;
        this.fullname = fullname;
        this.hPassword = hPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String gethPassword() {
        return hPassword;
    }

    public void sethPassword(String hPassword) {
        this.hPassword = hPassword;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
