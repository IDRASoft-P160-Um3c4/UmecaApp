package com.umeca.model.entities.humanReources;

import com.umeca.model.catalog.District;
import com.umeca.model.dto.humanResources.EmployeeDto;
import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "id_employee")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name_p")
    private String lastNameP;

    @Column(name = "last_name_m")
    private String lastNameM;

    @Column(name = "post")
    private String post;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "is_obsolete")
    private Boolean isObsolete;

    @Column(name = "date_obsolete")
    private Date dateObsolete;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_obsolete")
    private User userObsolete;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_district")
    private District district;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_general_data")
    private EmployeeGeneralData employeeGeneralData;

    public Employee() {

    }

    public Employee(EmployeeDto employeeDto) {
        this.name = employeeDto.getName();
        this.lastNameP = employeeDto.getLastNameP();
        this.lastNameM = employeeDto.getLastNameM();
        this.gender = employeeDto.getGender();
        this.post = employeeDto.getPost();
        this.birthDate = employeeDto.getBirthDate();
        District d = new District();
        d.setId(employeeDto.getDistrictId());
        this.district = d;
        this.createDate = new Date();
        this.isObsolete = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public Date getDateObsolete() {
        return dateObsolete;
    }

    public void setDateObsolete(Date dateObsolete) {
        this.dateObsolete = dateObsolete;
    }

    public User getUserObsolete() {
        return userObsolete;
    }

    public void setUserObsolete(User userObsolete) {
        this.userObsolete = userObsolete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeGeneralData getEmployeeGeneralData() {
        return employeeGeneralData;
    }

    public void setEmployeeGeneralData(EmployeeGeneralData employeeGeneralData) {
        this.employeeGeneralData = employeeGeneralData;
    }
}
