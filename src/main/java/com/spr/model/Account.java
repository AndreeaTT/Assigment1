package com.spr.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Andreea ADM on 3/26/2017.
 */

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer clientID;

    private String typeAccount;

    private Double amount;

    private Date createData;

    public Integer getId() {
        return id;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getCreateData() {
        return createData;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCreateData(Date createData) {
        this.createData = createData;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getClientID() {

        return clientID;
    }
}
