package com.spr.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Created by Andreea ADM on 3/26/2017.
 */

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "cardNumber", nullable = false)
    private String cardNumber;

    @Column(name = "numericCode", unique = true, nullable = false)
    private String numericCode;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNumericCode() {
        return this.numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
