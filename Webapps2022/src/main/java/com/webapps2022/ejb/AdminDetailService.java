/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.ejb;

import com.webapps2022.entity.AdminRegistrationEntity;
import com.webapps2022.entity.SystemUserEntity;
import com.webapps2022.entity.SystemUserGroupEntity;
import com.webapps2022.entity.TransactionHistoryEntity;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.Lock;
import static javax.ejb.LockType.WRITE;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import javax.faces.context.FacesContext;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pooja
 */
@Stateless
//@DeclareRoles({"admins"}) 

//this class includes admin functions
public class AdminDetailService {

    @PersistenceContext
    EntityManager adminEntityManager;

    public AdminDetailService() {
    }

    //it will retunrn all transactions present in database
    public List<TransactionHistoryEntity> getAllTransaction() {
        List<TransactionHistoryEntity> usTransactionHistoryEntitys = adminEntityManager.createNamedQuery("getAllTransactions").getResultList();
        return usTransactionHistoryEntitys;

    }

    //It will return all users present in databse
    public List<SystemUserEntity> getAllUsers() {
        String queryName = "getUserList";
        Query query = adminEntityManager.createNamedQuery(queryName);
        List<SystemUserEntity> systemUserEntitys = (List<SystemUserEntity>) query.getResultList();
        return systemUserEntitys;

    }

}
