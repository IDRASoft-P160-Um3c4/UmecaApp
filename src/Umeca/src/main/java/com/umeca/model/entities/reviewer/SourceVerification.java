package com.umeca.model.entities.reviewer;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 06:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="source_verification")
public class SourceVerification {

    @Id
    @GeneratedValue
    @Column(name = "id_source_verification")
    private Long id;

    @Column(name="name", length = 150, nullable = false)
    private String name;

    //FIXME VERIFICAR SI ESTE CAMPO PUEDE SER CATÁLOGO
    @Column(name = "type_source", length = 50, nullable = false)
    private String typeSource;

    @OneToMany(mappedBy="sourceVerification", cascade={CascadeType.ALL})
    private List<DetailVerification> detailVerifications;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_verification", nullable = false)
    private Verification verification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeSource() {
        return typeSource;
    }

    public void setTypeSource(String typeSource) {
        this.typeSource = typeSource;
    }

    public List<DetailVerification> getDetailVerifications() {
        return detailVerifications;
    }

    public void setDetailVerifications(List<DetailVerification> detailVerifications) {
        this.detailVerifications = detailVerifications;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }
}
