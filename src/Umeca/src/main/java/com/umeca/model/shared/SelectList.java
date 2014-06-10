package com.umeca.model.shared;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/4/14
 * Time: 9:11 PM
 */
public class SelectList {
    private Long id;
    private String name;
    private String description;

    public SelectList(Long id, String name){
        this.id = id;
        this.name = name;
    }


    public SelectList(Long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

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
        this.description = description;
    }
}
