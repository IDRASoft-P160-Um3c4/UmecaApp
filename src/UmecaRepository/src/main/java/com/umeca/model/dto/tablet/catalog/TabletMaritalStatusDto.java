package com.umeca.model.dto.tablet.catalog;

public class TabletMaritalStatusDto {

    private Long id;
    private String name;

    public TabletMaritalStatusDto() {}

    public TabletMaritalStatusDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
