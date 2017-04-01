package com.spr.model;

import javax.persistence.*;

/**
 * Created by Andreea ADM on 3/26/2017.
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "rights")
    private String rights;

    public Integer getId() {
        return id;
    }

    public String getPassword() {

        return password;
    }

    public String getRights() {
        return rights;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

}
