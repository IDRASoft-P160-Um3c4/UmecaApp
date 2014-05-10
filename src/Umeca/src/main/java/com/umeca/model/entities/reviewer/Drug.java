
package com.umeca.model.entities.reviewer;

import com.umeca.model.Catalog.DrugType;
import com.umeca.model.Catalog.Periodicity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="drug")
public class Drug {

    @Id
    @GeneratedValue
    @Column(name="id_drug")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_drug_type", nullable = false)
    private DrugType drugType;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_periodicity", nullable = false)
    private Periodicity periodicity;

    @Column(name="quantity", length = 25, nullable = false)
    private String quantity;

    @Column(name="last_use", nullable = false)
    private Date lastUse;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DrugType getDrugType() {
        return drugType;
    }

    public void setDrugType(DrugType drugType) {
        this.drugType = drugType;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Date getLastUse() {
        return lastUse;
    }

    public void setLastUse(Date lastUse) {
        this.lastUse = lastUse;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}
