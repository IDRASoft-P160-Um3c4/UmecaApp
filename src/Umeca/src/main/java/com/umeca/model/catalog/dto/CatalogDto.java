package com.umeca.model.catalog.dto;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/06/14
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class CatalogDto {
    private Long id;
    private String name;
    private Boolean specification;
    private String code;
    private String content;

    public CatalogDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CatalogDto(Long id, String name, Boolean specification, String code) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.code = code;
    }

    public CatalogDto(String name, String content){
        this.name = name;
        this.content = content;
    }
    public CatalogDto() {
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

    public Boolean getSpecification() {
        return specification;
    }

    public void setSpecification(Boolean specification) {
        this.specification = specification;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
