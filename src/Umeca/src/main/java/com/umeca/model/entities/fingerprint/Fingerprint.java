package com.umeca.model.entities.fingerprint;

import com.umeca.model.catalog.MaritalStatus;
import com.umeca.model.entities.reviewer.Imputed;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * Created by dcortesr on 30/09/14.
 */
@Entity
@Table(name = "fingerprint")
public class Fingerprint {
    @Id
    @GeneratedValue
    @Column(name = "id_fingerprint")
    private Long id;

    @Column(name = "finger", nullable = false)
    private short finger;

    @Column(name = "fingerprint", nullable = false, length = 4096)
    private byte[] fingerpring;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imputed", nullable = true)
    private Imputed imputed;
}
