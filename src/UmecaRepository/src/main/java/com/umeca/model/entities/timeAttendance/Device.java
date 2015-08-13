package com.umeca.model.entities.timeAttendance;

import javax.persistence.*;

/**
 * Created by Administrator on 4/10/2015.
 */
@Entity
@Table(name = "cat_device")
public class Device {
    @Id
    @GeneratedValue
    @Column(name="id_device")
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "ip", length = 15, nullable = false)
    private String ip;

    @Column(name = "port", nullable = false)
    private int port;

    @Column(name="is_obsolete", nullable = false)
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Boolean isObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}
