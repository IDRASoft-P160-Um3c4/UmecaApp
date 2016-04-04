package com.umeca.model.dto.humanResources;

/**
 * Created by Administrator on 3/29/2016.
 */
public class ImputedFingerPrintDto {
    private short finger;
    private String data;
    private long id;

    public ImputedFingerPrintDto(Long id, short finger, String data){
        this.id = id;
        this.finger = finger;
        this.data = data;
    }

    public short getFinger() {
        return finger;
    }

    public void setFinger(short finger) {
        this.finger = finger;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}