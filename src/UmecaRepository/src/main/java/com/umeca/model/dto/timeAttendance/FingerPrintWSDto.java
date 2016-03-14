package com.umeca.model.dto.timeAttendance;


public class FingerPrintWSDto {

    private int Finger;

    private String Data;

    public FingerPrintWSDto(){

    }

    public FingerPrintWSDto(int finger, String data){
        this.Finger = finger;
        this.Data = data;
    }

    public int getFinger() {
        return Finger;
    }

    public void setFinger(int finger) {
        Finger = finger;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }
}
