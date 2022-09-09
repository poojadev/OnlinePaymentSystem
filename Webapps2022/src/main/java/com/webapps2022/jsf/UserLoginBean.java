package com.webapps2022.jsf;

import com.webapps2022.ejb.UserDetailsService;
import com.webapps2022.entity.SystemUserEntity;
import java.io.Serializable;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped

public class UserLoginBean implements Serializable {

    @EJB
    UserDetailsService user_deatils_service;

    private String username;
    private String userpassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        System.out.println("Username: " + username);
        System.out.println("Password: " + userpassword);
        try {
            //this method will actually check in the realm you configured in the web.xml file for the provided credentials
            request.login(this.username, this.userpassword);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed:" + e));
            return "error";
        }
        System.out.println("getRemoteUser: " + request.getRemoteUser());

        System.out.println(request.getRequestURI());

        return "/users/useraccountactivitypage.xhtml?faces-redirect=true;includeViewParams=true;";

    }

}
