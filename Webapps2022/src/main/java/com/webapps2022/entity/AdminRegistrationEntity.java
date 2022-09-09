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
@Entity
@NamedQueries({
    @NamedQuery(name = "getUserList", query = "SELECT c FROM SystemUserEntity c"),
    @NamedQuery(name = "getAllTransactions", query = "SELECT c FROM TransactionHistoryEntity c")

})

public class AdminRegistrationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManagedProperty(value = "#{param.id}")

    private Long id;
//ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//Map<String, String[]> paramValues = ec.getRequestParameterValuesMap();
    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    @ManagedProperty(value = "#{param.userName}")
    String userName;

    @NotNull
    String userPassword;

    String adminAccess;

    public AdminRegistrationEntity() {
    }

    public AdminRegistrationEntity(String firstName, String lastName, String userName, String userPassword, String adminAccess) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.adminAccess = adminAccess;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.firstName);
        hash = 53 * hash + Objects.hashCode(this.lastName);
        hash = 53 * hash + Objects.hashCode(this.userName);
        hash = 53 * hash + Objects.hashCode(this.userPassword);
        hash = 53 * hash + Objects.hashCode(this.adminAccess);
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
        final AdminRegistrationEntity other = (AdminRegistrationEntity) obj;
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
        if (!Objects.equals(this.adminAccess, other.adminAccess)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getAdminAccess() {
        return adminAccess;
    }

    public void setAdminAccess(String adminAccess) {
        this.adminAccess = adminAccess;
    }

}
