package com.umeca.model.entities.reviewer.View;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 19/05/14
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class ImputedHomeView implements EntityGrid {

    public ImputedHomeView() {
    }

    public ImputedHomeView(Long id, String domicile, String registerType, String timeLive, String belong) {
        this.id = id;
        this.domicile = domicile;
        this.registerType = registerType;
        this.timeLive = timeLive;
        this.belong = belong;
    }

    private Long id;

    private String domicile;

    private String registerType;

    private String timeLive;

    private String belong;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getTimeLive() {
        return timeLive;
    }

    public void setTimeLive(String timeLive) {
        this.timeLive = timeLive;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }
}
