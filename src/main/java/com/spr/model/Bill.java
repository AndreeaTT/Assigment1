package com.spr.model;

import javax.persistence.*;

/**
 * Created by Andreea ADM on 4/3/2017.
 */

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "companyName",  nullable = false)
    private String companyName;

    @Column(name="value")
    private Double value;

    @Column(name = "startDate")
    private String startDate;

    @Column(name = "dueDate")
    private String dueDate;

    @Column(name = "clientID")
    private Integer clientID;

    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Double getValue() {
        return value;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(String duedDate) {
        this.dueDate = duedDate;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }
}
