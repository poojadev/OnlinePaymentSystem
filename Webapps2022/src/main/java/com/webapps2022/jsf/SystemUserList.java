/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.jsf;

import com.webapps2022.ejb.UserDetailsService;
import com.webapps2022.entity.SystemUserEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author pooja
 */
@Named
@RequestScoped
public class SystemUserList {

    @EJB
    UserDetailsService user_deatils_service;

    String input;
    long id;
    String firstName;
    String lastName;
    private FacesContext facesContext;

    double updatedBalance;

    public double getUpdatedBalance() {
        return updatedBalance;
    }

    public void setUpdatedBalance(double updatedBalance) {

        this.updatedBalance = updatedBalance;
    }
    String userName;
    String currencyType;

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }
    SystemUserEntity es;

    public UserDetailsService getUser_deatils_service() {
        return user_deatils_service;
    }

    public String getInput() {
        return input;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setUser_deatils_service(UserDetailsService user_deatils_service) {
        this.user_deatils_service = user_deatils_service;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setId(long id) {
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

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public double getCurrency() {
        return currency;
    }
    double currency;

    public String showResult() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params
                = fc.getExternalContext().getRequestParameterMap();
        userName = params.get("userName");
        return "/usertransactionform.xhtml?faces-redirect=true;includeViewParams=true";
    }

    public List<String> getUsers() {

        return user_deatils_service.getUsers();
    }

    public void changeDepartment(String inputs) {
        inputs = userName;

    }

    public List<String> getCommentList() {

        return user_deatils_service.getUserList();
    }

    //long userId;
    public String navigateToTransactionRequestPage() {
        return "/usertransactionrequest.xhtml?faces-redirect=true;includeViewParams=true;&requestFrom=" + getUserName();
    }

    public Object navigateToNotificationPage() {

        System.out.println("com.webapps2022.jsf.UserRegistrationBean.navigateToExamPage()" + getId());
        return "users/notificationlist.xhtml?faces-redirect=true;includeViewParams=true;&requestFrom=" + getUserName();

    }

    @PostConstruct
    public void postConstruct() {
        facesContext = FacesContext.getCurrentInstance();

    }

}
