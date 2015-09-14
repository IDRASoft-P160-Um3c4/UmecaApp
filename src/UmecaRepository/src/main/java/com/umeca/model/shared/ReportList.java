package com.umeca.model.shared;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;


public class ReportList implements EntityGrid{
    private Long id;
    private String name;
    private String description;
    private Long x;
    private Long y;
    private String user;


    public ReportList() {
    }

    public ReportList(Long id, Long y, String code){
        this.id = id;
        this.y = y;
        this.description= code;
        if (description.equals(Constants.EVENT_INTERVIEW_DECLINED))
            this.name = "Negación";
        else if (description.equals(Constants.EVENT_CASE_REPORT))
            this.name = "Informe";
        else if (description.equals(Constants.EVENT_CASE_OPINION))
            this.name = "Opinión";
        else if (description.equals(Constants.EVENT_ONLY_INTERVIEW))
            this.name = "Solo entrevista";
        else
            this.name = description;
    }

    public ReportList(Long id, Long y, String code, String user, Long x){
        this.id = id;
        this.y = y;
        this.description= code;
        this.user = user;
        this.x = x;
        if (description.equals(Constants.EVENT_INTERVIEW_DECLINED))
            this.name = "Negación";
        else if (description.equals(Constants.EVENT_CASE_REPORT))
            this.name = "Informe";
        else if (description.equals(Constants.EVENT_CASE_OPINION))
            this.name = "Opinión";
        else if (description.equals(Constants.EVENT_ONLY_INTERVIEW))
            this.name = "Solo entrevista";
        else
            this.name = description;
    }


    @Override
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.name = description;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
