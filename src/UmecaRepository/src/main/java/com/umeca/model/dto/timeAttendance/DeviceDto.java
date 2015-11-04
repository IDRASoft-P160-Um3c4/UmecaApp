package com.umeca.model.dto.timeAttendance;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

public class DeviceDto implements EntityGrid {

    private Long id;
    private String name;
    private String ip;
    private int port;
    private boolean isObsolete;

    public DeviceDto(){}

    public DeviceDto(Long id, String name, String ip, int port, boolean isObsolete){
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.isObsolete = isObsolete;
    }




    public boolean isObsolete() {
        return isObsolete;
    }

    public void setObsolete(boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
