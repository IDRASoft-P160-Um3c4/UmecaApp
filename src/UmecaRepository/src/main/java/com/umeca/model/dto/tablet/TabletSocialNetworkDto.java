package com.umeca.model.dto.tablet;

import java.util.List;

public class TabletSocialNetworkDto {

    public TabletSocialNetworkDto() {
    }

    public TabletSocialNetworkDto(Long id, String comment) {
        this.id = id;
        this.webId = id;
        this.comment = comment;
    }

    private Long webId;
    private Long id;
    private String comment;
    private List<TabletPersonSocialNetworkDto> peopleSocialNetwork;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<TabletPersonSocialNetworkDto> getPeopleSocialNetwork() {
        return peopleSocialNetwork;
    }

    public void setPeopleSocialNetwork(List<TabletPersonSocialNetworkDto> peopleSocialNetwork) {
        this.peopleSocialNetwork = peopleSocialNetwork;
    }

    public Long getWebId() {
        return webId;
    }

    public void setWebId(Long webId) {
        this.webId = webId;
    }
}
