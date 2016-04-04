package com.umeca.model.dto.timeAttendance;


public class FingerPrintWSDto {

    private short Finger;

    private String Data;

    public FingerPrintWSDto(){

    }

    public FingerPrintWSDto(short finger, String data){
        this.Finger = finger;
        this.Data = data;
    }

    public short getFinger() {
        return Finger;
    }

    public void setFinger(short finger) {
        Finger = finger;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
