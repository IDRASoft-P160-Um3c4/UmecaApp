package com.umeca.model.shared;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.repository.shared.ReportExcelRepository;


public class ReportList implements EntityGrid{
    private Long id;
    private String name;
    private String description;
    private Long x;
    private Long y;
    private String user;


    private Long aux;
    private Double  value;
    private String subName;


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
        else if(description.equals("Cocaína en piedra"))
            this.name = "CocaínaP";
        else if(description.equals("Metanfetaminas"))
            this.name = "Meta";
        else if(description.equals("PBC(Pasta básica de cocaína)"))
            this.name = "PBC";
        else
            this.name = description;
    }


    public ReportList(Long id, Long aux, Double value){
        this.id = id;
        this.aux = aux;
        this.value = value;
        if(this.id.equals(1L)){
            this.name = "Enero" + aux;
            this.subName = "Ene" + aux;
        }
        else if(this.id.equals(2L)){
            this.name = "Febrero"  + aux;
            this.subName = "Feb" + aux;
        }
        else if(this.id.equals(3L)){
            this.name = "Marzo"  + aux;
            this.subName = "Mar" + aux;
        }
        else if(this.id.equals(4L)){
            this.name = "Abril"  + aux;
            this.subName = "Abr" + aux;
        }
        else if(this.id.equals(5L)){
            this.name = "Mayo"  + aux;
            this.subName = "May" + aux;
        }
        else if(this.id.equals(6L)){
            this.name = "Junio"  + aux;
            this.subName = "Jun" + aux;
        }
        else if(this.id.equals(7L)){
            this.name = "Julio"  + aux;
            this.subName = "Jul" + aux;
        }
        else if(this.id.equals(8L)){
            this.name = "Agosto"  + aux;
            this.subName = "Ago"  + aux;
        }
        else if(this.id.equals(9L)){
            this.name = "Septiembre"  + aux;
            this.subName = "Sep"  + aux;
        }
        else if(this.id.equals(10L)){
            this.name = "Octubre"  + aux;
            this.subName = "Oct"  + aux;
        }
        else if(this.id.equals(11L)){
            this.name = "Noviembre"  + aux;
            this.subName = "Nov"  + aux;
        }
        else if(this.id.equals(12L)){
            this.name = "Diciembre"  + aux;
            this.subName = "Dic"  + aux;
        }

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

    public Long getAux() {
        return aux;
    }

    public void setAux(Long aux) {
        this.aux = aux;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
