package com.umeca.model.entities.reviewer.View;

import com.umeca.model.shared.Constants;
import com.umeca.model.shared.EntityGrid;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 29/05/14
 * Time: 04:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersonSocialNetworkView  implements EntityGrid {

   private Long id;

   private String name;

   private String relName;

   private Integer age;

    private String phone;

    private Boolean isAccompaniment;

    private String isAccompanimentString;

    private Long dependent;

    private String dependentString;

    private String specificationRelationship;

    public PersonSocialNetworkView() {
    }

    public PersonSocialNetworkView(Long id, String name, String relName, Integer age, String phone, Boolean isAccompaniment, Long dependent, String specificationRelationship) {
        this.id = id;
        this.name = name;
        this.relName = relName;
        if(!specificationRelationship.equals("")){
            this.relName += ": "+specificationRelationship;
        }
        this.age = age;
        this.phone = phone;
        this.isAccompanimentString = isAccompaniment ? "Si":"No";
        this.dependent = dependent;
        this.dependentString = dependent.equals(Constants.ELECTION_YES)? "Si": "No";
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

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsAccompaniment() {
        return isAccompaniment;
    }

    public void setIsAccompaniment(Boolean accompaniment) {
        isAccompaniment = accompaniment;
    }

    public String getIsAccompanimentString() {
        return isAccompanimentString;
    }

    public void setIsAccompanimentString(String accompanimentString) {
        isAccompanimentString = accompanimentString;
    }

    public Long getDependent() {
        return dependent;
    }

    public void setDependent(Long dependent) {
        this.dependent = dependent;
    }

    public String getDependentString() {
        return dependentString;
    }

    public void setDependentString(String dependentString) {
        this.dependentString = dependentString;
    }

    public String getSpecificationRelationship() {
        return specificationRelationship;
    }

    public void setSpecificationRelationship(String specificationRelationship) {
        this.specificationRelationship = specificationRelationship;
    }
}
