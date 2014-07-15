package com.umeca.model.entities.reviewer.View;

import com.umeca.model.entities.reviewer.FieldMeetingSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 11/07/14
 * Time: 01:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChoiceView {
    Long id;
    String status;
    String nameSource;
    Boolean isFinal;
    List<String> values;

    public ChoiceView choiceDto(List<FieldMeetingSource> list){
        FieldMeetingSource template = list.get(0);
        id = template.getId();
        status = template.getStatusFieldVerification().getName();
        if(template.getSourceVerification()!=null)
            nameSource = template.getSourceVerification().getFullName();
        values = new ArrayList<>();
        isFinal = template.getFinal();
        for(FieldMeetingSource fms : list){
           values.add(fms.getFieldVerification().getFieldName() +": "+fms.getValue());
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameSource() {
        return nameSource;
    }

    public void setNameSource(String nameSource) {
        this.nameSource = nameSource;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
