/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.faces.bean.ManagedProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pooja
 */
@NamedQueries({
    //change this query 
    @NamedQuery(name = "getUserList", query = "SELECT c FROM SystemUserEntity c"),
    @NamedQuery(name = "getAllUsersByUserName",
            query = "SELECT u FROM SystemUserEntity u ORDER BY u.userName"),

    @NamedQuery(name = "getUserGroup", query = "SELECT c FROM SystemUserGroupEntity c WHERE c.groupname = :groupname"),

    @NamedQuery(name = "SystemUserEntity.findByUserName",
            query = "SELECT c FROM SystemUserEntity c WHERE c.userName = :userName"),})
@Entity
public class SystemUserEntity implements Serializable {

    public SystemUserEntity() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.firstName);
        hash = 79 * hash + Objects.hashCode(this.lastName);
        hash = 79 * hash + Objects.hashCode(this.userName);
        hash = 79 * hash + Objects.hashCode(this.userPassword);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.currency) ^ (Double.doubleToLongBits(this.currency) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUserEntity other = (SystemUserEntity) obj;
        if (Double.doubleToLongBits(this.currency) != Double.doubleToLongBits(other.currency)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.userPassword, other.userPassword)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManagedProperty(value = "#{param.id}")

    private Long id;
    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    @ManagedProperty(value = "#{param.userName}")
    String userName;

    @NotNull
    String userPassword;

    @NotNull
    double currency;

    /*
       currecyType to store user currency type like GBP,USD
     */
    String currencyType;

    /*
      below userFlag will diffrentiate user group type in database 
      flag 1= normal users
      flag 2= super admin/main admin
      flag 3= restricted access admins added by main admin 
     */
    int userFlag;

    public String getCurrencyName() {
        return currencyType;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyType = currencyName;
    }

    double updatedBalance;

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public double getUpdatedBalance() {
        return updatedBalance;
    }

    public void setUpdatedBalance(double updatedBalance) {
        this.updatedBalance = updatedBalance;
    }

    public SystemUserEntity(String firstName, String lastName, String userName, String userPassword, int userFlag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userFlag = userFlag;

    }

    public int getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(int userFlag) {
        this.userFlag = userFlag;
    }

    public SystemUserEntity(String firstName, String lastName, String userName, String userPassword, double currency, String currencyName, double updatedBalance, int userFlag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.currency = currency;
        this.currencyType = currencyName;
        this.updatedBalance = updatedBalance;
        this.userFlag = userFlag;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public double getCurrency() {
        return currency;
    }

}
