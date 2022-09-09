/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.ejb;

import com.webapps2022.entity.SystemUserEntity;
import com.webapps2022.entity.SystemUserGroupEntity;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pooja
 */
@Stateless

public class UserDetailsService {

    @PersistenceContext
    EntityManager userDetailManager;

    public UserDetailsService() {
    }

    ArrayList allUserList = new ArrayList();

    //function will reutn all users presnet in hte databse
    public List<String> getUserList() {
        List<SystemUserEntity> comments = userDetailManager.createNamedQuery("getAllUsersByUserName").getResultList();
        allUserList = new ArrayList<String>();
        String userName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        SystemUserEntity systemUserEntity = getUserRecord(userName);
        for (int i = 0; i < comments.size(); i++) {
            allUserList.add(comments.get(i).getUserName());
            userName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

            //remove current user and admin from userlist which will be used to send money/
            // or trasaction request
            if (allUserList.contains(systemUserEntity.getUserName())) {
                allUserList.remove(systemUserEntity.getUserName());
            }
            if (allUserList.contains("admin1")) {
                allUserList.remove(systemUserEntity.getUserName());
            }
        }

        return allUserList;
    }

    public List<String> getUsers() {
        List<String> users = userDetailManager.createNamedQuery("getAllUsersByUserName").getResultList();
        return users;
    }

    //function to check if username already exist using result count
    long count;

    public long checkIfUserExist(String userName) {

        String queryName = "SystemUserEntity.findByUserName";
        Query query = userDetailManager.createNamedQuery(queryName);
        query.setParameter("userName", userName);
        SystemUserEntity user = (SystemUserEntity) query.getResultStream().findFirst().orElse(null);
        if (user != null) {
            System.out.println("com.webapps2022.ejb.UserDetailsService.checkIfUserExist()" + user.getUserName());
            count = 1;

        } else {
            count = 0;
        }
        return count;

    }

    //Functon will return user details by using username
    public SystemUserEntity getUserRecord(String name) {
        String queryName = "SystemUserEntity.findByUserName";
        Query query = userDetailManager.createNamedQuery(queryName);
        query.setParameter("userName", name);
        SystemUserEntity user = (SystemUserEntity) query.getResultList().stream().findFirst().orElse(null);
        return user;

    }

    //Function to insert nomral user in databse
    public void registerSystemUser(String firstNname, String lastName, String userName,
            String userpassword, double currency, String currencyName, double updatedBalance, int userFlag) {
        try {
            SystemUserEntity system_user_entity;
            SystemUserGroupEntity sys_user_group_entity;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = userpassword;
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String paswdToStoreInDB = bigInt.toString(16);

            system_user_entity = new SystemUserEntity(firstNname, lastName, userName,
                    paswdToStoreInDB, currency, currencyName, updatedBalance, userFlag);
            sys_user_group_entity = new SystemUserGroupEntity(userName, "users");

            userDetailManager.persist(system_user_entity);
            userDetailManager.persist(sys_user_group_entity);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDetailsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Function to insert nomral admin in database
    public void registerMainAdminUser(String firstNname, String lastName, String userName,
            String userpassword, int userFlag) {
        try {
            SystemUserEntity adminRegistrationEntity;
            SystemUserGroupEntity sys_user_group_entity;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = userpassword;
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);

            String paswdToStoreInDB = bigInt.toString(16);
            System.out.println("INSIDE   com.webapps2022.ejb.UserDetailsService.registerAdminUser()" + userName);

            adminRegistrationEntity = new SystemUserEntity(firstNname, lastName, userName, paswdToStoreInDB,
                    userFlag);
            sys_user_group_entity = new SystemUserGroupEntity(userName, "admins");

            userDetailManager.persist(adminRegistrationEntity);
            userDetailManager.persist(sys_user_group_entity);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDetailsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Function to insert subadmins  in databse
    public void registerSubAdminUser(String firstNname, String lastName, String userName,
            String userpassword, int userFlag) {
        try {
            SystemUserEntity adminRegistrationEntity;
            SystemUserGroupEntity sys_user_group_entity;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = userpassword;
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String paswdToStoreInDB = bigInt.toString(16);
            adminRegistrationEntity = new SystemUserEntity(firstNname, lastName, userName, paswdToStoreInDB,
                    userFlag);
            sys_user_group_entity = new SystemUserGroupEntity(userName, "subadmin");

            userDetailManager.persist(adminRegistrationEntity);
            userDetailManager.persist(sys_user_group_entity);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDetailsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
