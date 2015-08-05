package com.umeca.model.entities.managereval;

import com.umeca.model.catalog.EvaluationActivity;
import com.umeca.model.entities.humanReources.Document;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.entities.supervisorManager.RolActivity;

import javax.persistence.*;

@Entity
@Table(name = "rel_activity_evaluation_activity")
public class RelRolActivityEvaluationActivity {

    @Id
    @GeneratedValue
    @Column(name = "id_rel_activity_evaluation_activity")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol_activity")
    private RolActivity rolActivity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluation_activity", unique = false)
    private EvaluationActivity evaluationActivity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RolActivity getRolActivity() {
        return rolActivity;
    }

    public void setRolActivity(RolActivity rolActivity) {
        this.rolActivity = rolActivity;
    }

    public EvaluationActivity getEvaluationActivity() {
        return evaluationActivity;
    }

    public void setEvaluationActivity(EvaluationActivity evaluationActivity) {
        this.evaluationActivity = evaluationActivity;
    }
}
