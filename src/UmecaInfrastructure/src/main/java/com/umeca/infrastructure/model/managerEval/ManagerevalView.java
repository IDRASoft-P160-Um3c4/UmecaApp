package com.umeca.infrastructure.model.managerEval;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dcortesr on 30/06/14.
 */
public class ManagerevalView implements EntityGrid {
    private Long id;
    private String name;
    private String age;
    private String lastNameP;
    private String lastNameM;
    private String fullname;
    private String idFolder;
    private String crime;
    private Long detentionDateMil;
    private String initDateStr;
    private String initTimeStr;
    private Date birthDate;

    public ManagerevalView(Long id, String idFolder, String name, String lastNameP, String lastNameM, String crime) {
        this.id = id;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.idFolder = idFolder;
        if (crime == null)
            this.crime = "Sin registrar.";//"Sin acceso a la carpeta";
        else
            this.crime = crime;
        createFullname();
    }

    public ManagerevalView(Long id, String idFolder, String name, String lastNameP, String lastNameM, Date birthDate, String crime, Date detentionDate) {

        SimpleDateFormat sdfA = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdfB = new SimpleDateFormat("HH:mm:ss");
        this.id = id;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.birthDate = birthDate;

        this.age = calcAge();
        this.idFolder = idFolder;
        if (crime == null)
            this.crime = "Sin registrar.";//"Sin acceso a la carpeta";
        else
            this.crime = crime;
        createFullname();
        if (detentionDate != null) {
            this.detentionDateMil = detentionDate.getTime();
            this.initDateStr = sdfA.format(detentionDate);
            this.initTimeStr = sdfB.format(detentionDate);
        } else {
            this.detentionDateMil = -1L;
            this.initDateStr = "Sin registrar";
            this.initTimeStr = "Sin registrar";
        }
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private void createFullname() {
        this.fullname = "";
        if (this.name != null)
            this.fullname += this.name + " ";
        if (this.lastNameP != null)
            this.fullname += this.lastNameP + " ";
        if (lastNameM != null)
            this.fullname += this.lastNameM;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        createFullname();
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
        createFullname();
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
        createFullname();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getDetentionDateMil() {
        return detentionDateMil;
    }

    public void setDetentionDateMil(Long detentionDateMil) {
        this.detentionDateMil = detentionDateMil;
    }

    public String getInitDateStr() {
        return initDateStr;
    }

    public void setInitDateStr(String initDateStr) {
        this.initDateStr = initDateStr;
    }

    public String getInitTimeStr() {
        return initTimeStr;
    }

    public void setInitTimeStr(String initTimeStr) {
        this.initTimeStr = initTimeStr;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private String calcAge() {
        if (this.birthDate != null) {
            Calendar dob = Calendar.getInstance();
            dob.setTime(this.birthDate);
            Calendar today = Calendar.getInstance();
            Integer age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
                age--;
            return Integer.toString(age);
        } else {
            return "Sin registrar";
        }
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}