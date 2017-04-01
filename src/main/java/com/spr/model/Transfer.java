package com.spr.model;

/**
 * Created by Andreea ADM on 3/30/2017.
 */
public class Transfer {

    private Integer senderID;
    private Integer receiverID;
    private Double value;

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
