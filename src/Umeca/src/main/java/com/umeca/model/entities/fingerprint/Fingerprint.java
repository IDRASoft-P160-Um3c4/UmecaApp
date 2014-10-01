package com.umeca.model.entities.fingerprint;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Imputed;
import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imputed", nullable = false)
    private Imputed imputed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = true)
    private User user;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Imputed getImputed() {
        return imputed;
    }

    public void setImputed(Imputed imputed) {
        this.imputed = imputed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getFinger() {
        return finger;
    }

    public void setFinger(short finger) {
        this.finger = finger;
    }

    public byte[] getFingerpring() {
        return fingerpring;
    }

    public void setFingerpring(byte[] fingerpring) {
        this.fingerpring = fingerpring;
    }
}
