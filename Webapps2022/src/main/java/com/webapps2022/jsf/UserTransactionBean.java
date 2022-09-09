/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.jsf;

import com.webapps2022.ejb.TransactionDetailService;
import com.webapps2022.ejb.UserDetailsService;
import com.webapps2022.entity.SystemUserEntity;
import com.webapps2022.entity.TransactionHistoryEntity;
import com.webapps2022.thrift.TimeStampServer;
import java.time.LocalDateTime;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.apache.thrift.TException;

/**
 *
 * @author pooja
 */
@Named
@ManagedBean
@RequestScoped
public class UserTransactionBean {

    public UserTransactionBean() {

    }

    private FacesContext facesContext;
    String input;
    String senderName;
    SystemUserEntity systemUserEntity;

    @Inject
    TimeStampServer timeStampServer;

    LocalDateTime transactionDate;

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    String userCurrencyType;

    public String getUserCurrencyType() {
        return userCurrencyType;
    }

    public void setUserCurrencyType(String userCurrencyType) {

        this.userCurrencyType = userCurrencyType;
    }

    @PostConstruct
    public void postConstruct() {
        facesContext = FacesContext.getCurrentInstance();
        userName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        systemUserEntity = userDetailsService.getUserRecord(userName);
        setUpdatedBalance(systemUserEntity.getCurrency());
        FacesContext fc = FacesContext.getCurrentInstance();
        params = fc.getExternalContext().getRequestParameterMap();
        userCurrencyType = systemUserEntity.getCurrencyType();
        setUserCurrencyType(userCurrencyType);
        System.out.println("getCurrencyTypewebapps2022.webapps2022.jsf.UserTransactionBean.setUserCurrencyType()" + systemUserEntity.getCurrencyType());

        String currencyTypes = params.get("currencyType");
        setCurrencyType(currencyTypes);

    }
    @EJB
    TransactionDetailService transaction_deatils_service;
    @Inject
    UserDetailsService userDetailsService;

    String requestTo;
    String message;
    double convertedCurrency;
    double updatedBalance;

    public double getUpdatedBalance() {
        return updatedBalance;
    }

    public void setUpdatedBalance(double updatedBalance) {

        this.updatedBalance = updatedBalance;
    }

    public double getConvertedCurrency() {
        return convertedCurrency;
    }

    public void setConvertedCurrency(double convertedCurrency) {

        if (currencyType == null) {

        } else if (systemUserEntity.getCurrencyType().equals(currencyType)) {

            convertedCurrency = amount;
            this.convertedCurrency = convertedCurrency;

        } else {

            convertedCurrency = getCurrentFx(systemUserEntity.getCurrencyType(), currencyType, amount);
            System.out.println("in not  com.webapps2022.jsf.UserTransactionBean.setConvertedCurrency()" + convertedCurrency);

        }
        this.convertedCurrency = convertedCurrency;
    }
    int status;
    double amount;
    long requesterId;
    long senderId;
    String currencyType;
    String requestFrom;
    Double response;

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public void setTransaction_deatils_service(TransactionDetailService transaction_deatils_service) {
        this.transaction_deatils_service = transaction_deatils_service;
    }
    Map<String, String> params
            = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String idString = params.get("userName");

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setAmount(double amount) {

        this.amount = amount;
    }

    public void setRequesterId(long requesterId) {
        this.requesterId = requesterId;
    }

    public void setSenderId(long senderId) {

        this.senderId = senderId;
    }

    public TransactionDetailService getTransaction_deatils_service() {
        return transaction_deatils_service;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public String getRequestTo() {
        return requestTo;
    }

    public String getMessage() {
        return message;
    }

    public int isStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    public long getRequesterId() {
        return requesterId;
    }

    public long getSenderId() {
        return senderId;
    }

    public List<TransactionHistoryEntity> getDebitTransactions() {
        userName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        SystemUserEntity systemUserEntity;
        systemUserEntity = userDetailsService.getUserRecord(userName);
        // updatedBalance= systemUserEntity.getUpdatedBalance();
        //setAmount(updatedBalance);
        setAmount(systemUserEntity.getCurrency());
        return transaction_deatils_service.getDebitTransactions(userName);
    }

    public List<TransactionHistoryEntity> getCreditTransaction() {
        userName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        return transaction_deatils_service.getCreditTransactionHistory(userName);
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            //this method will disassociate the principal from the session (effectively logging him/her out)

            request.logout();

            if (request.isUserInRole("users")) {
                return "/welcome.xhtml?faces-redirect=true;";
            }

        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
        return "/welcome.xhtml?faces-redirect=true;";

    }

    double userAmount;

    public String submitTransactionDeatils() throws TException {
        senderName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        systemUserEntity = userDetailsService.getUserRecord(senderName);

        if (systemUserEntity.getCurrency() < convertedCurrency) {
            System.out.println(" Inside declined" + systemUserEntity.getCurrency());
            facesContext.addMessage(null, new FacesMessage("Transaction declined! You dont have enought money for the requested amount"));
            return null;
        } else {

            senderId = systemUserEntity.getId();

            double updateSenderBalance = systemUserEntity.getCurrency() - convertedCurrency;

            SystemUserEntity requesterInfo = userDetailsService.getUserRecord(requestFrom);
            double updateReciverBalance = requesterInfo.getCurrency() + convertedCurrency;

            //update receiver balance
            transaction_deatils_service.updateAmount(requestFrom, updateReciverBalance);

            //update sender balance
            transaction_deatils_service.updateAmount(senderName, updateSenderBalance);

            transactionDate = timeStampServer.getTimeStamps();
            transaction_deatils_service.insertTransactions(convertedCurrency, message, requesterId, senderId, userName, requestFrom, 1, transactionDate);
            return "/users/useraccountactivitypage.xhtml?faces-redirect=true;";

        }
    }

    public String updateAmount(String userName, double currency) {
        transaction_deatils_service.updateAmount(userName, currency);
        return "newuserlist";
    }

    public String checkItem() {

        facesContext.addMessage(null, new FacesMessage("The item number you entered is invalid."));
        return "usertransactionform";
    }

    private String userName;
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Map<String, String> params;
    public Double getCurrentFx(String currency1, String currency2, double amount) {
        Client client = ClientBuilder.newClient();
        WebTarget conversionResource = client.target("http://localhost:8080/webapps2022/currency/")
                .path("{senderCurrency}")
                .resolveTemplate("senderCurrency", currency1)
                .path("{reciverCurrency}")
                .resolveTemplate("reciverCurrency", currency2)
                .path("{amount}")
                .resolveTemplate("amount", String.valueOf(amount));
        Invocation.Builder builder = conversionResource.request(MediaType.APPLICATION_JSON);
        response = builder.get(Double.class);
        client.close();

        return response;
    }

    public void onLoad() {

        FacesContext fc = FacesContext.getCurrentInstance();
        params = fc.getExternalContext().getRequestParameterMap();
        String requestFrom = params.get("requestFrom");
        String ids = params.get("requesterId");
        String currencyTypes = params.get("currencyType");
        setCurrencyType(currencyTypes);
        setRequestFrom(requestFrom);

    }

    public long getId() {
        return id;
    }

}
