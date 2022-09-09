/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.ejb;

import com.webapps2022.entity.SystemUserEntity;
import com.webapps2022.entity.UserTransactionRequestEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.eclipse.persistence.jpa.jpql.Assert;

/**
 *
 * @author pooja
 */
//This Class functions related to user request money to anothe user notification 
@Stateless
public class UserTransactionRequestService {

    @PersistenceContext
    EntityManager trasanctionRequestManager;
    UserDetailsService userDetailsService;
    List<UserTransactionRequestEntity> transactionRequestList;

    //This fuction will return all transaction requests/notifications from other users
    public List<UserTransactionRequestEntity> getTrasactionRequestList(String userName, int status) {
        String queryName = "getTransactionRequestList";
        Query query = trasanctionRequestManager.createNamedQuery(queryName);
        query.setParameter("requestTo", userName);
        query.setParameter("status", 0);
        transactionRequestList = query.getResultList();
        return transactionRequestList;

    }

    //This function will return user record by using username
    public SystemUserEntity getUserRecord(String name) {
        String queryName = "SystemUserEntity.findByUserName";
        Query query = trasanctionRequestManager.createNamedQuery(queryName);
        query.setParameter("userName", name);
        SystemUserEntity user = (SystemUserEntity) query.getSingleResult();
        return user;

    }

    //this fucation will update the notification or trasaction request if the request is accepted 
    //it will set flag 1 
    public long updateAcceptedTrasactionstatus(int statusFlag, LocalDateTime notificationDate, long trasactionId) {

        Query query = trasanctionRequestManager.createQuery(
                "UPDATE UserTransactionRequestEntity t SET t.status = ?1 , t.notificationDate=?2 WHERE t.id = ?3 ");
        query.setParameter(1, 1);
        query.setParameter(2, notificationDate);
        query.setParameter(3, trasactionId);
        long updateData = query.executeUpdate();
        System.out.println(updateData + " record updated");
        return trasactionId;

    }

    //this fucation will update the notification or trasaction request if the request is rejected 
    //it will set flag 2
    public long updateRejectedTrasactionstatus(int statusFlag, long trasactionId, String requestFrom) {
        Query query = trasanctionRequestManager.createQuery(
                "UPDATE UserTransactionRequestEntity t SET t.status = ?1 , t.requestFrom= ?2 WHERE t.id = ?3 ");
        query.setParameter(1, 2);
        query.setParameter(2, requestFrom);
        query.setParameter(3, trasactionId);
        long updateData = query.executeUpdate();
        System.out.println(updateData + " record updated");
        return trasactionId;

    }

    //Function to get rejected request data by using status=2
    List<UserTransactionRequestEntity> transactionRejectedRequestList;

    public List<UserTransactionRequestEntity> getRejectedTrasactionRequestList(String requestTo, int status) {

//         int count = ((Number)em.createNamedQuery("getUserList").getSingleResult()).intValue();
        String queryName = "getRejectedTransactionRequestList";
        Query query = trasanctionRequestManager.createNamedQuery(queryName);
        query.setParameter("requestFrom", requestTo);
        query.setParameter("status", 2);

        transactionRejectedRequestList = query.getResultList();

        return transactionRejectedRequestList;

    }

    //Insert transaction/money request details
    public void insertTransactionRrequest(String requestFrom, String requestTo, String message,
            long requesteFromId, long requesteToId, LocalDateTime notificationDate, int status, String currecyType, double requestedAmoun) {

        try {
            UserTransactionRequestEntity userTransactionRequestService;

            System.out.println("TransactionEntityTransactionEntity.webapps2022.ejb.TransactionDetailService.insertTransactionDeatils()");

            // System.out.println("TransactionEntityTransactionEntity.webapps2022.ejb.TransactionDetailService.insertTransactionDeatils()" +user.getId());
            userTransactionRequestService = new UserTransactionRequestEntity(requestFrom, requestTo, message, requesteFromId, requesteToId,
                    notificationDate, status, currecyType, requestedAmoun);
            // updateAmount(senderId, amount);

            trasanctionRequestManager.persist(userTransactionRequestService);

        } catch (EJBException e) {
            @SuppressWarnings("ThrowableResultIgnored")
            Exception cause = e.getCausedByException();
            if (cause instanceof ConstraintViolationException) {
                @SuppressWarnings("ThrowableResultIgnored")
                ConstraintViolationException cve = (ConstraintViolationException) e.getCausedByException();
                for (Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<? extends Object> v = it.next();
                    System.err.println(v);
                    System.err.println("==>>" + v.getMessage());
                }
            }
            Assert.fail("ejb exception");
        }
    }

}
