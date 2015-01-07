package com.umeca.model.entities.shared;

import javax.persistence.*;

@Entity
@Table(name = "system_setting")
public class SystemSetting {
    @Id
    @GeneratedValue
    @Column(name = "id_system_setting")
    private Long id;

    @Column(name = "group_setting", length = 100, nullable = false)
    private String group;

    @Column(name = "key_setting", length = 100, nullable = false)
    private String key;

    @Column(name = "value_setting", length = 2000, nullable = false)
    private String value;

    @Column(name = "description", length = 2000, nullable = false)
    private String description;

    public SystemSetting() {
    }

    public SystemSetting(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}