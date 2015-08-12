package com.umeca.model.entities.supervisor;

public class ChannelingFulfilledModel {
    private Long channelingId;
    private Boolean isFulfilled;

    public Boolean getIsFulfilled() {
        return isFulfilled;
    }

    public void setIsFulfilled(Boolean isFulfilled) {
        this.isFulfilled = isFulfilled;
    }

    public Long getChannelingId() {
        return channelingId;
    }

    public void setChannelingId(Long channelingId) {
        this.channelingId = channelingId;
    }
}
