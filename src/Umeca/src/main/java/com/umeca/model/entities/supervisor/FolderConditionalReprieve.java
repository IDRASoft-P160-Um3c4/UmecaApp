package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;

/**
 * Created by Vmware on 20/06/2014.
 */

@Entity
@Table(name="folder_conditional_reprieve")
public class FolderConditionalReprieve {

    @Id
    @GeneratedValue
    @Column(name="id_folder_conditional_reprieve")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_case", nullable = true)
    private Case caseDetention;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }
}
