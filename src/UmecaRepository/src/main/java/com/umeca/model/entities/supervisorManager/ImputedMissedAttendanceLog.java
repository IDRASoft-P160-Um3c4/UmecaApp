package com.umeca.model.entities.supervisorManager;

import com.umeca.model.entities.reviewer.Imputed;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "imputed_missed_attendance_log")
public class ImputedMissedAttendanceLog {

    @Id
    @GeneratedValue
    @Column(name = "id_imputed_missed_attendance_log", nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_imputed", nullable = false)
    private Imputed imputed;

    @Column(name = "date", nullable = false)
    private Date date;


    @Column(name="is_obsolete", nullable = false)
    private Boolean isObsolete;

    public ImputedMissedAttendanceLog() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Imputed getImputed() {
        return imputed;
    }

    public void setImputed(Imputed imputed) {
        this.imputed = imputed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}
