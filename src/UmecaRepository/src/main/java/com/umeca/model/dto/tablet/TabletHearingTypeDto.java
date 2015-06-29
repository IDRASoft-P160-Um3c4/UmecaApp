package com.umeca.model.dto.tablet;


public class TabletHearingTypeDto {

    public TabletHearingTypeDto() {
    }

    public TabletHearingTypeDto(Long id, String description, Boolean isObsolete, Boolean lock, Boolean specification) {
        this.id = id;
        this.description = description;
        this.isObsolete = isObsolete;
        this.lock = lock;
        this.specification = specification;
    }

    private Long id;
    private String description;
    private Boolean isObsolete;
    private Boolean lock;
    private Boolean specification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public Boolean getSpecification() {
        return specification;
    }

    public void setSpecification(Boolean specification) {
        this.specification = specification;
    }
}
