package com.umeca.model.entities.reviewer.View;

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

    public PersonSocialNetworkView() {
    }

    public PersonSocialNetworkView(Long id, String name, String relName, Integer age, String phone) {
        this.id = id;
        this.name = name;
        this.relName = relName;
        this.age = age;
        this.phone = phone;
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
}
