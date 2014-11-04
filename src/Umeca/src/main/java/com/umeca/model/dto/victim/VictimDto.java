package com.umeca.model.dto.victim;

import com.umeca.model.entities.shared.Victim;
import com.umeca.model.shared.EntityGrid;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 29/10/14
 * Time: 04:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class VictimDto implements EntityGrid {

    private Long id;
    private String fullname;
    private String relName;
    private Long relId;
    private Integer age;
    private String phone;
    private String addressString;
    private String specification;

    public VictimDto() {
    }

    public VictimDto(Long id, String fullname, String relName, Integer age, String phone, String addressString, String specification) {
        this.id = id;
        this.fullname = fullname;
        this.relName = relName;
        if(specification!=null && !specification.equals(""))
            this.relName+=": "+specification;
        this.age = age;
        this.phone = phone;
        this.addressString = addressString;
        this.specification = specification;
    }

    public VictimDto dtoVictim(Victim victim){
        this.id = victim.getId();
        this.fullname =victim.getFullname();
        if(victim.getRelationship()!=null)
            this.relId = victim.getRelationship().getId();
        this.age = victim.getAge();
        this.phone = victim.getPhone();
        this.specification = victim.getSpecification();


        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
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

    public String getAddressString() {
        return addressString;
    }

    public void setAddressString(String addressString) {
        this.addressString = addressString;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
