/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author pooja
 */
@Entity

@NamedQueries({
    @NamedQuery(name = "getDebitTransactions", query = "SELECT c FROM TransactionHistoryEntity c WHERE c.senderName = :senderName"),
    @NamedQuery(name = "getCreditTransactions", query = "SELECT c FROM TransactionHistoryEntity c WHERE c.reciverName = :reciverName"),})

public class TransactionHistoryEntity implements Serializable {

    public TransactionHistoryEntity() {
    }

    public TransactionHistoryEntity(double amount, String message, long requesterId, long senderId, String senderName, String reciverName, int transactionType, LocalDateTime transactionDate) {

        this.amount = amount;
        this.message = message;
        this.requesterId = requesterId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.reciverName = reciverName;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.amount) ^ (Double.doubleToLongBits(this.amount) >>> 32));
        hash = 29 * hash + Objects.hashCode(this.message);
        hash = 29 * hash + this.status;
        hash = 29 * hash + (int) (this.requesterId ^ (this.requesterId >>> 32));
        hash = 29 * hash + (int) (this.senderId ^ (this.senderId >>> 32));
        hash = 29 * hash + Objects.hashCode(this.senderName);
        hash = 29 * hash + Objects.hashCode(this.reciverName);
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
        final TransactionHistoryEntity other = (TransactionHistoryEntity) obj;
        if (Double.doubleToLongBits(this.amount) != Double.doubleToLongBits(other.amount)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.requesterId != other.requesterId) {
            return false;
        }
        if (this.senderId != other.senderId) {
            return false;
        }

        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.senderName, other.senderName)) {
            return false;
        }
        if (!Objects.equals(this.reciverName, other.reciverName)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReciverName() {
        return reciverName;
    }

    public void setReciverName(String reciverName) {
        this.reciverName = reciverName;
    }

    double amount;

    // @NotNull
    String message;
    // @NotNull
    int status;
    // @NotNull
    long requesterId;
    //   @NotNull
    long senderId;

    String senderName;
    String reciverName;
    int transactionType;

    LocalDateTime transactionDate;

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(long requesterId) {
        this.requesterId = requesterId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

}
