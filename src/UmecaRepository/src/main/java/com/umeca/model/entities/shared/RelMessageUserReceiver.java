package com.umeca.model.entities.shared;

import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="rel_message_user_receiver")
public class RelMessageUserReceiver {
    @Id
    @GeneratedValue
    @Column(name="id_rel_message_user_receiver")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_message", nullable = false)
    private Message message;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_user", nullable = false)
    private User user;

    @Column(name="is_obsolete", nullable = false)
    private Boolean isObsolete;

    @Column(name="timestamp_obsolete", nullable = true)
    private Calendar timestampObsolete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getTimestampObsolete() {
        return timestampObsolete;
    }

    public void setTimestampObsolete(Calendar timestampObsolete) {
        this.timestampObsolete = timestampObsolete;
    }
}
