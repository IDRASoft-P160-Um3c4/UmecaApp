
package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.DrugType;
import com.umeca.model.catalog.Periodicity;
import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.model.shared.EntityGrid;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class Drug implements EntityGrid{

    public Drug() {
    }

    public Drug(Long id, String perName, Date lastUse, String drugName, String quantity) {
        this.id = id;
        this.perName = perName;
        this.lastUse = lastUse;
        this.drugName = drugName;
        this.quantity = quantity;
        if(lastUse!=null){
            Date date = Calendar.getInstance().getTime();
            date.setTime(lastUse.getTime());
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            this.lastUseFormat = formatter.format(date);
        }
    }

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

    @Column(name="specification_type", nullable = true, length = 100)
    private String specificationType;

    @Column(name="specification_periodicity", nullable = true, length = 100)
    private String specificationPeriodicity;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_framing_meeting")
    private FramingMeeting framingMeeting;


    @Transient
    private String perName;

    @Transient
    private String lastUseFormat;

    @Transient
    private String drugName;

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

    @JsonIgnore
    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getLastUseFormat() {
        return lastUseFormat;
    }

    public void setLastUseFormat(String lastUseFormat) {
        this.lastUseFormat = lastUseFormat;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getSpecificationType() {
        return specificationType;
    }

    public void setSpecificationType(String specificationType) {
        this.specificationType = specificationType;
    }

    public String getSpecificationPeriodicity() {
        return specificationPeriodicity;
    }

    public void setSpecificationPeriodicity(String specificationPeriodicity) {
        this.specificationPeriodicity = specificationPeriodicity;
    }
}
