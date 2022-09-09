/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.jsf;

import com.webapps2022.ejb.AdminDetailService;
import com.webapps2022.ejb.UserDetailsService;
import com.webapps2022.entity.SystemUserEntity;
import com.webapps2022.entity.SystemUserGroupEntity;
import com.webapps2022.entity.TransactionHistoryEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author pooja
 */
//
@Named
@RequestScoped
public class AdminDetailsBean implements Serializable {

    @PersistenceContext
    EntityManager adminEntityManager;

    @EJB
    AdminDetailService adminDetailService;

    SystemUserEntity systemUserEntity;

    @Inject
    UserDetailsService userDetailsService;

    private FacesContext facesContext;

    String firstName;

    String lastName;

    @ManagedProperty(value = "#{param.userName}")
    String userName;

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

    String userPassword;

    String adminAccess;

//    
// 
    public List<TransactionHistoryEntity> getAllTransactionRecods() {
        return adminDetailService.getAllTransaction();
    }

    public List<SystemUserEntity> getAllUserList() {
        return adminDetailService.getAllUsers();
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            //this method will disassociate the principal from the session (effectively logging him/her out)

            request.logout();

            return "/welcome.xhtml?faces-redirect=true;";

        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
        return "/welcome.xhtml?faces-redirect=true;";

    }

}
