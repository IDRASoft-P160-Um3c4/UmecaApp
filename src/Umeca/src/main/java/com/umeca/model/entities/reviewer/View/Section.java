package com.umeca.model.entities.reviewer.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 16/07/14
 * Time: 06:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Section {
    private String name;
    private List<String> values;

    public Section(String name) {
        this.name = name;
        this.values=new ArrayList<>();
    }

    public Section() {
        values = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
