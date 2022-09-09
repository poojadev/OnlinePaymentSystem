/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.jsf;

import com.webapps2022.ejb.UserDetailsService;
import com.webapps2022.ejb.UserTransactionRequestService;
import com.webapps2022.entity.SystemUserEntity;
import com.webapps2022.entity.UserTransactionRequestEntity;
import com.webapps2022.thrift.TimeStampServer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.thrift.TException;

/**
 *
 * @author pooja
 */
@Named
@ManagedBean
@RequestScoped
public class UserTransactionRequestBean {

    @EJB
    UserTransactionRequestService transaction_request_service;

    @Inject
    UserDetailsService userDetails;

    @Inject
    TimeStampServer timeStampServer;

    UserTransactionRequestEntity userTransactionRequestEntity;
    SystemUserEntity systemUserEntity;

    long id;
    double convertedamount;

    String requestFrom;

    String requestTo;
    private FacesContext facesContext;

    String message;
    long requestFromId;
    long requestToId;
    LocalDateTime notificationDate;
    int status;
    String currecyType;
    double requestedAmount;
    String userName;
    double response;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getConvertedamount() {
        return convertedamount;
    }

    public void setConvertedamount(double convertedamount) {
        this.convertedamount = convertedamount;
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

    public List<String> getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(List<String> currencyType) {
        this.currencyType = currencyType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private List<String> currencyType;

    public UserTransactionRequestBean() {
        currencyType = new ArrayList<String>();
        currencyType.add("GBP");
        currencyType.add("USD");
        currencyType.add("EURO");
    }

    //Getting parameters from pervious page/bean
    public void onLoad() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        requestFrom = params.get("requestFrom");
        String userCurrencyName = params.get("userCurrencyType");

    }

    public void changeDepartment(String inputs) {
        inputs = requestFrom;

    }

    public String updateAccpetedTransactionRequest(int status, long id) throws TException {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        requestFrom = params.get("requestFrom");
        String rid = params.get("requesterId");
        String currecyTypeName = params.get("currencyType");
        requestedAmount = Double.parseDouble(params.get("requestedAmount"));
        String transactionId = params.get("id");
        requestFromId = Long.parseLong(rid);
        systemUserEntity = userDetails.getUserRecord(userName);
        if (systemUserEntity.getCurrencyName().equals(currecyTypeName)) {
            System.out.println("No conversion required");
            setConvertedamount(requestedAmount);
        } else {

            setConvertedamount(requestedAmount);
            setRequestFrom(requestFrom);
            setRequestedAmount(requestedAmount);

        }
        notificationDate = timeStampServer.getTimeStamps();
        id = Long.parseLong(transactionId);
        transaction_request_service.updateAcceptedTrasactionstatus(1, notificationDate, id);

        return "/users/usertransactionform.xhtml?faces-redirect=true;includeViewParams=true;&requestFrom=" + requestFrom + "&currencyType=" + params.get("currencyType") + "&requestedAmount=" + requestedAmount + "&requesterId=" + requestFromId;

    }

    public String updateRejectedTransactionRequest(int status, long id, String requestFrom) {
        facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        requestFrom = params.get("requestFrom");
        String rid = params.get("requestFromId");
        String transactionId = params.get("id");
        setRequestFrom(requestFrom);
        id = Long.parseLong(transactionId);
        transaction_request_service.updateRejectedTrasactionstatus(2, id, requestFrom);
        return "/users/useraccountactivitypage.xhtml";
    }

    public List<UserTransactionRequestEntity> getRejectedTrasactionRequestRecords() {
        userName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        systemUserEntity = userDetails.getUserRecord(userName);
        return transaction_request_service.getRejectedTrasactionRequestList(userName, 2);
    }

    public List<UserTransactionRequestEntity> getTrasactionRequestRecords() {
        systemUserEntity = userDetails.getUserRecord(userName);
        requestToId = systemUserEntity.getId();
        if (requestToId == 0) {
            facesContext.addMessage(null, new FacesMessage("No new notification"));

        }
        return transaction_request_service.getTrasactionRequestList(userName, 0);
    }

    public List<String> getUsers() {

        return userDetails.getUserList();
    }

    List<String> userList;

    public String submitTransactionRequestDeatils() throws TException {
        requestTo = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        facesContext = FacesContext.getCurrentInstance();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        userList = userDetails.getUserList();

        //validation to check if typed username pressent is databse
        if (userList.contains(requestFrom)) {

            systemUserEntity = userDetails.getUserRecord(requestTo);
            SystemUserEntity requesterRecords;
            requesterRecords = userDetails.getUserRecord(requestFrom);
            requestToId = systemUserEntity.getId();
            requestFromId = requesterRecords.getId();
            notificationDate = timeStampServer.getTimeStamps();

            transaction_request_service.insertTransactionRrequest(requestTo, requestFrom, message, requestFromId,
                    requestToId,
                    notificationDate,
                    status, currecyType, requestedAmount);
            return "/users/useraccountactivitypage.xhtml";

        } else {
            facesContext.addMessage(null, new FacesMessage("User not found"));
            return null;

        }

    }

    @PostConstruct
    public void postConstruct() {
        facesContext = FacesContext.getCurrentInstance();
        userName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

    }

}
