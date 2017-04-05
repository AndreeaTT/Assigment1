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
    private String senderID;

    @Column(name = "receiverID")
    private String receiverID;

    @Column(name = "value")
    private Double value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenderID() {
        return senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public Double getValue() {
        return value;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
