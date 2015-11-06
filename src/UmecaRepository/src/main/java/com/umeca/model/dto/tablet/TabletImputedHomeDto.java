package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletHomeTypeDto;
import com.umeca.model.dto.tablet.catalog.TabletRegisterTypeDto;

import java.util.List;

public class TabletImputedHomeDto {

    public TabletImputedHomeDto(Long id, String timeLive, String reasonChange, String description, String phone, String specification, String reasonSecondary,
                                Long idA, String streetA, String outNumA, String innNumA, String latA, String lngA, String addressStringA,
                                Long idL, String nameL, String abbreviationL, String descriptionL, String zipCodeL,
                                Long idHT, String nameHT, Boolean specificationHT, Boolean obsoleteHT,
                                Long idRT, String nameRT, Boolean isHomeless) {
        this.id = id;
        this.webId = id;
        this.timeLive = timeLive;
        this.reasonChange = reasonChange;
        this.description = description;
        this.phone = phone;
        this.specification = specification;
        this.reasonSecondary = reasonSecondary;

        this.isHomeless = isHomeless;

        if (idA != null) {
            this.address = new TabletAddressDto(idA, streetA, outNumA, innNumA, latA, lngA, addressStringA, idL, nameL, abbreviationL, descriptionL, zipCodeL);
        }

        if (idHT != null) {
            this.homeType = new TabletHomeTypeDto(idHT, nameHT, specificationHT, obsoleteHT);
        }

        if (idRT != null) {
            this.registerType = new TabletRegisterTypeDto(idRT, nameRT);
        }
    }

    private Long webId;
    private Long id;
    private String timeLive;
    private String reasonChange;
    private String description;
    private String phone;
    private String specification;
    private String reasonSecondary;
    private TabletAddressDto address;
    private TabletHomeTypeDto homeType;
    private TabletRegisterTypeDto registerType;
    private List<TabletScheduleDto> schedule;

    private Boolean isHomeless;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeLive() {
        return timeLive;
    }

    public void setTimeLive(String timeLive) {
        this.timeLive = timeLive;
    }

    public String getReasonChange() {
        return reasonChange;
    }

    public void setReasonChange(String reasonChange) {
        this.reasonChange = reasonChange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getReasonSecondary() {
        return reasonSecondary;
    }

    public void setReasonSecondary(String reasonSecondary) {
        this.reasonSecondary = reasonSecondary;
    }

    public TabletAddressDto getAddress() {
        return address;
    }

    public void setAddress(TabletAddressDto address) {
        this.address = address;
    }

    public TabletHomeTypeDto getHomeType() {
        return homeType;
    }

    public void setHomeType(TabletHomeTypeDto homeType) {
        this.homeType = homeType;
    }

    public TabletRegisterTypeDto getRegisterType() {
        return registerType;
    }

    public void setRegisterType(TabletRegisterTypeDto registerType) {
        this.registerType = registerType;
    }

    public List<TabletScheduleDto> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<TabletScheduleDto> schedule) {
        this.schedule = schedule;
    }

    public Long getWebId() {
        return webId;
    }

    public void setWebId(Long webId) {
        this.webId = webId;
    }

    public Boolean getIsHomeless() {
        return isHomeless;
    }

    public void setIsHomeless(Boolean isHomeless) {
        this.isHomeless = isHomeless;
    }
}
