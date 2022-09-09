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
@NamedQueries({
    @NamedQuery(name = "getTransactionRequestList",
            query = "SELECT c FROM UserTransactionRequestEntity c WHERE  c.status = :status AND c.requestTo = :requestTo"),
    @NamedQuery(name = "getRejectedTransactionRequestList",
            query = "SELECT c FROM UserTransactionRequestEntity c WHERE  c.status = :status AND c.requestFrom = :requestFrom")
})

//Query getTransactionRequestList will fetch request send to users by using status flag
//status flag 0 =reuest inserted 
//status flag 1= request accpetd
//status f;ag 3= request rejected
@Entity
public class UserTransactionRequestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String requestFrom;  //Request coming from user name
    String requestTo;    // requesting to username
    String message;
    long requestFromId;
    long requestToId;
    LocalDateTime notificationDate;
    int status;
    String currecyType;
    double requestedAmount;

    public UserTransactionRequestEntity(String requestFrom, String requestTo, String message, long requesteFromId, long requesteToId, LocalDateTime notificationDate, int status, String currecyType, double requestedAmount) {
        this.requestFrom = requestFrom;
        this.requestTo = requestTo;
        this.message = message;
        this.requestFromId = requesteFromId;
        this.requestToId = requesteToId;
        this.notificationDate = notificationDate;
        this.status = status;
        this.currecyType = currecyType;
        this.requestedAmount = requestedAmount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.requestFrom);
        hash = 97 * hash + Objects.hashCode(this.requestTo);
        hash = 97 * hash + Objects.hashCode(this.message);
        hash = 97 * hash + (int) (this.requestFromId ^ (this.requestFromId >>> 32));
        hash = 97 * hash + (int) (this.requestToId ^ (this.requestToId >>> 32));
        hash = 97 * hash + Objects.hashCode(this.notificationDate);
        hash = 97 * hash + this.status;
        hash = 97 * hash + Objects.hashCode(this.currecyType);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.requestedAmount) ^ (Double.doubleToLongBits(this.requestedAmount) >>> 32));
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
        final UserTransactionRequestEntity other = (UserTransactionRequestEntity) obj;
        if (this.requestFromId != other.requestFromId) {
            return false;
        }
        if (this.requestToId != other.requestToId) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (Double.doubleToLongBits(this.requestedAmount) != Double.doubleToLongBits(other.requestedAmount)) {
            return false;
        }
        if (!Objects.equals(this.requestFrom, other.requestFrom)) {
            return false;
        }
        if (!Objects.equals(this.requestTo, other.requestTo)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.currecyType, other.currecyType)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.notificationDate, other.notificationDate)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getRequesteFromId() {
        return requestFromId;
    }

    public void setRequesteFromId(long requesteFromId) {
        this.requestFromId = requesteFromId;
    }

    public long getRequesteToId() {
        return requestToId;
    }

    public void setRequesteToId(long requesteToId) {
        this.requestToId = requesteToId;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCurrecyType() {
        return currecyType;
    }

    public void setCurrecyType(String currecyType) {
        this.currecyType = currecyType;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public UserTransactionRequestEntity() {
    }

}
