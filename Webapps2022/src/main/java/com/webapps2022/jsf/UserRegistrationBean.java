/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.jsf;

import com.webapps2022.ejb.UserDetailsService;
import com.webapps2022.entity.SystemUserEntity;
import java.util.ArrayList;
import java.util.List;
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

public class UserRegistrationBean {

    @EJB
    UserDetailsService user_deatils_service;
    String input;
    long id;
    String firstName;
    String lastName;
    String currencyName;

    SystemUserEntity systemUserEntity;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
    private List<String> currencyType;

    public List<String> getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(List<String> currencyType) {
        this.currencyType = currencyType;
    }
    // no-arg constructor

    public UserRegistrationBean() {
        currencyType = new ArrayList<String>();
        currencyType.add("GBP");
        currencyType.add("USD");
        currencyType.add("EURO");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    String userName;
    String userpassword;
    double currency;
    double updatedBalance;

    public double getUpdatedBalance() {
        return updatedBalance;
    }

    public void setUpdatedBalance(double updatedBalance) {
        this.updatedBalance = updatedBalance;
    }
    private List<String> userList;

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public List<String> getUserList() {
        return userList;
    }

    private FacesContext facesContext;

    public long getIds() {
        return ids;
    }

    public void setUser_deatils_service(UserDetailsService user_deatils_service) {
        this.user_deatils_service = user_deatils_service;
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

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public UserDetailsService getUser_deatils_service() {
        return user_deatils_service;
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

    public String getUserpassword() {
        return userpassword;
    }

    public double getCurrency() {
        return currency;
    }

    //call the injected EJBzxc
    public String registerUser() {

//        
        long recordLenght = user_deatils_service.checkIfUserExist(userName);

        if (recordLenght == 0) {
            user_deatils_service.registerSystemUser(firstName, lastName, userName, userpassword, currency, currencyName, updatedBalance, 1);
            return "login";
        } else {

            facesContext.addMessage(null, new FacesMessage("User name already taken!!"
                    + "Please choose diffrenet user name"));

        }
        return "useregistration";

    }

    public String sendUser(String userName) {

        //System.out.println("com.webapps2022.jsf.UserRegistrationBean.sendUser()" +firstName);
        facesContext.addMessage(userName, new FacesMessage("The item number you entered is invalid."));

        return "/usertransactionform.xhtml?faces-redirect=true;includeViewParams=true";
    }

    public String checkItem() {

        facesContext.addMessage(null, new FacesMessage("The item number you entered is invalid."));
        return "usertransactionform";
    }

    public List<String> getCommentList() {

        return user_deatils_service.getUserList();
    }

    long ids;

    public void changeDepartment(String inputs) {
        inputs = userName;

    }
    String adminUserName;

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String registerAdmin() {
        user_deatils_service.registerSubAdminUser(firstName, lastName, adminUserName, userpassword, 3);
        return "login.xhtml?faces-redirect=true;includeViewParams=true;";

    }

    //long userId;
    public Object navigateToTransactionPage() {

        return "usertransactionrequest.xhtml?faces-redirect=true;includeViewParams=true;&rrequestFrom=" + getUserName();

        //return "usertransactionform.xhtml?faces-redirect=true;includeViewParams=true;&userName="+getUserName();
    }


    public Object navigateToNotificationPage() {

        System.out.println("com.webapps2022.jsf.UserRegistrationBean.navigateToExamPage()" + getId());
        return "notificationlist.xhtml?faces-redirect=true;includeViewParams=true;&requestFrom=" + getUserName();

        //return "usertransactionform.xhtml?faces-redirect=true;includeViewParams=true;&userName="+getUserName();
    }

    @PostConstruct
    public void postConstruct() {
        facesContext = FacesContext.getCurrentInstance();

    }

}
