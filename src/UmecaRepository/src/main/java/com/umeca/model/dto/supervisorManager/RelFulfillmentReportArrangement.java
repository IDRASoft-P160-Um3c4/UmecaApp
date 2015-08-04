package com.umeca.model.dto.supervisorManager;

import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.supervisor.FulfillmentReport;

import javax.persistence.*;

@Entity
@Table(name="rel_fulfillment_report_arrangement")
public class RelFulfillmentReportArrangement {

    @Id
    @GeneratedValue
    @Column(name="id_rel_fulfillment_report_arrangement")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_arrangement", nullable = false)
    private Arrangement arrangement;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_fulfillment_report", nullable = false)
    private FulfillmentReport fulfillmentReport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }

    public FulfillmentReport getFulfillmentReport() {
        return fulfillmentReport;
    }

    public void setFulfillmentReport(FulfillmentReport fulfillmentReport) {
        this.fulfillmentReport = fulfillmentReport;
    }
}
