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

    @Column(name = "clientID")
    private Integer clientID;

    @Column(name = "typeAccount")
    private String typeAccount;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "createData")
    private String createData;

    public Integer getId() {
        return id;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCreateData() {
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

    public void setCreateData(String createData) {
        this.createData = createData;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getClientID() {

        return clientID;
    }
}
