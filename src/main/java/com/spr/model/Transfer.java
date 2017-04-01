package com.spr.model;

import javax.persistence.*;

/**
 * Created by Andreea ADM on 3/30/2017.
 */

@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "senderID")
    private Integer senderID;

    @Column(name = "receiverID")
    private Integer receiverID;

    @Column(name = "value")
    private Double value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderID() {
        return senderID;
    }

    public Integer getReceiverID() {
        return receiverID;
    }

    public Double getValue() {
        return value;
    }

    public void setSenderID(Integer senderID) {
        this.senderID = senderID;
    }

    public void setReceiverID(Integer receiverID) {
        this.receiverID = receiverID;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
