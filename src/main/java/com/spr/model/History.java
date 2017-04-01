package com.spr.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Andreea ADM on 3/29/2017.
 */

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "userID")
    private Integer userID;

    @Column(name = "action")
    private String action;

    @Column(name = "actionData")
    private Date actionData;

    public Integer getId() {
        return id;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getAction() {
        return action;
    }

    public Date getActionData() {
        return actionData;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setActionData(Date actionDate) {
        this.actionData = actionData;
    }
}
