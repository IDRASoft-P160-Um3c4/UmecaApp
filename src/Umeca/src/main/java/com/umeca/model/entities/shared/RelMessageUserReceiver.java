package com.umeca.model.entities.shared;

import com.umeca.model.entities.account.User;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 15/08/14
 * Time: 05:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="rel_message_user_receiver")
public class RelMessageUserReceiver {
    @Id
    @GeneratedValue
    @Column(name="id_rel")
    private Long relId;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_message", nullable = false)
    private Message message;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_user", nullable = false)
    private User user;


    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
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
}
