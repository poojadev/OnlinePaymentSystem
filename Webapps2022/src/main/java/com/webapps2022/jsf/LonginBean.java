/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.jsf;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author pooja
 */
@Named
@RequestScoped
public class LonginBean implements Serializable {

    private String username;
    private String userpassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return userpassword;
    }

    public void setPassword(String password) {
        this.userpassword = password;
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        System.out.println("Username: " + username);
        System.out.println("Password: " + userpassword);
        try {
            request.login(this.username, this.userpassword);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed:" + e));
            return "error";
        }
        System.out.println("getRemoteUser: " + request.getRemoteUser());

        System.out.println(request.getRequestURI());

        if (request.isUserInRole("admins")) {
            return "/admin/adminpage.xhtml?faces-redirect=true;";
        } else if (request.isUserInRole("subadmin")) {

            return "/subadmin/subadminpage.xhtml?faces-redirect=true;";

        } else if (request.isUserInRole("users")) {

            return "/users/useraccountactivitypage.xhtml?faces-redirect=true;includeViewParams=true;";

        } else {
            return "error";
        }

    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            //this method will disassociate the principal from the session (effectively logging him/her out)

            request.logout();
            context.addMessage(null, new FacesMessage("User is logged out"));
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
    }
}
