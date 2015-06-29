package com.umeca.model.dto.tablet;

public class TabletVerificationMethodDto {

    public TabletVerificationMethodDto() {
    }

    public TabletVerificationMethodDto(Long id, String name, Boolean isObsolete) {
        this.id = id;
        this.name = name;
        this.isObsolete = isObsolete;
    }

    private Long id;
    private String name;
    private Boolean isObsolete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}
