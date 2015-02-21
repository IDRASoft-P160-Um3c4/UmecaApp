package com.umeca.model.entities.shared;

import com.google.gson.Gson;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "log_change_data")
public class LogChangeData {
    @Id
    @GeneratedValue
    @Column(name = "id_log_change_data")
    private Long id;

    @Column(name = "class_name", length = 500, nullable = false)
    private String className;

    @Lob
    @Column(name = "data_old", nullable = false)
    private String dataOld;

    @Lob
    @Column(name = "data_new", nullable = false)
    private String dataNew;

    @Column(name = "username", length = 250, nullable = true)
    private String username;

    @Column(name = "extra_a", length = 1000, nullable = true)
    private String extraA;

    @Column(name = "extra_b", length = 1000, nullable = true)
    private String extraB;

    @Column(name = "extra_c", length = 1000, nullable = true)
    private String extraC;

    @Column(name = "extra_d", length = 1000, nullable = true)
    private String extraD;

    @Column(name = "timestamp", nullable = false)
    private Calendar timestamp;

    @Column(name = "id_case", nullable = true)
    private Long idCase;

    public LogChangeData(String className, Object dataOld, Object dataNew, String username, Long idCase){
        this(className, dataOld, dataNew, username);
        this.idCase = idCase;
    }

    public LogChangeData(String className, Object dataOld, Object dataNew, String username, Long idCase, String extraA){
        this(className, dataOld, dataNew, username, idCase);
        this.extraA = extraA;
    }

    public LogChangeData(String className, Object dataOld, Object dataNew, String username, Long idCase, String extraA, String extraB){
        this(className, dataOld, dataNew, username, idCase);
        this.extraA = extraA;
        this.extraB = extraB;
    }

    public LogChangeData(String className, Object dataOld, Object dataNew, String username, Long idCase, String extraA, String extraB, String extraC){
        this(className, dataOld, dataNew, username, idCase);
        this.extraA = extraA;
        this.extraB = extraB;
        this.extraC = extraC;
    }

    public LogChangeData(String className, Object dataOld, Object dataNew, String username, Long idCase, String extraA, String extraB, String extraC, String extraD){
        this(className, dataOld, dataNew, username, idCase);
        this.extraA = extraA;
        this.extraB = extraB;
        this.extraC = extraC;
        this.extraD = extraD;
    }

    public LogChangeData(String className, Object dataOld, Object dataNew, String username) {
        Gson gson = new Gson();
        this.className = className;
        this.dataOld = gson.toJson(dataOld);
        this.dataNew = gson.toJson(dataNew);
        this.username = username;
        this.timestamp = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDataOld() {
        return dataOld;
    }

    public void setDataOld(String dataOld) {
        this.dataOld = dataOld;
    }

    public String getDataNew() {
        return dataNew;
    }

    public void setDataNew(String dataNew) {
        this.dataNew = dataNew;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getExtraA() {
        return extraA;
    }

    public void setExtraA(String extraA) {
        this.extraA = extraA;
    }

    public String getExtraB() {
        return extraB;
    }

    public void setExtraB(String extraB) {
        this.extraB = extraB;
    }

    public String getExtraC() {
        return extraC;
    }

    public void setExtraC(String extraC) {
        this.extraC = extraC;
    }

    public String getExtraD() {
        return extraD;
    }

    public void setExtraD(String extraD) {
        this.extraD = extraD;
    }
}
