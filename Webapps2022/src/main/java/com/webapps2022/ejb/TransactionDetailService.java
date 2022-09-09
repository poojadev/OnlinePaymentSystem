/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.ejb;

import com.webapps2022.entity.TransactionHistoryEntity;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
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
//This class will handle all trasanction related functionalities
@Stateless
public class TransactionDetailService {

    @PersistenceContext
    EntityManager trasactionManager;

    public TransactionDetailService() {

    }
    //getDebitTransactions() will check if loggine username is matched with senderName column which means
    //user did debit transaction
    //getCreditTransactionHistory() will check if loggine username is matched with receiverName column
    //which means user received moneyfrom other user

    //This fucntion will return Debit transaction by using senderName
    public List<TransactionHistoryEntity> getDebitTransactions(String senderName) {
        String queryName = "getDebitTransactions";
        Query query = trasactionManager.createNamedQuery(queryName);
        query.setParameter("senderName", senderName);
        List<TransactionHistoryEntity> debitTransactions = query.getResultList();
        return debitTransactions;

    }

    //This fucntion will return Debit transaction by using reciverName
    public List<TransactionHistoryEntity> getCreditTransactionHistory(String receiverName) {
        String queryName = "getCreditTransactions";
        Query query = trasactionManager.createNamedQuery(queryName);
        query.setParameter("reciverName", receiverName);
        List<TransactionHistoryEntity> creditTransactionRecords = query.getResultList();
        return creditTransactionRecords;

    }

    //Function will update user amount after transaction
    public double updateAmount(String userName, double currency) {
        Query query = trasactionManager.createQuery(
                "UPDATE SystemUserEntity s SET s.currency = ?1 "
                + " WHERE s.userName = ?2");
        query.setParameter(1, currency);
        query.setParameter(2, userName);
        long updateData = query.executeUpdate();
        System.out.println(updateData + " record updated");
        return currency;

    }

    //Function to insert transaction details
    public void insertTransactions(double amount, String message, long requesterId,
            long senderId, String senderName, String requestFrom, int trsactionType, LocalDateTime transactionDate) {
        try {
            TransactionHistoryEntity transactionHistoryEntity;
            transactionHistoryEntity = new TransactionHistoryEntity(amount, message,
                    requesterId, senderId, senderName, requestFrom, trsactionType, transactionDate);
            trasactionManager.persist(transactionHistoryEntity);

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
