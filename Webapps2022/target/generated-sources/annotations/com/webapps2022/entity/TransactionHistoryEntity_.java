package com.webapps2022.entity;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-04-30T13:34:14")
@StaticMetamodel(TransactionHistoryEntity.class)
public class TransactionHistoryEntity_ { 

    public static volatile SingularAttribute<TransactionHistoryEntity, Integer> transactionType;
    public static volatile SingularAttribute<TransactionHistoryEntity, Double> amount;
    public static volatile SingularAttribute<TransactionHistoryEntity, Long> senderId;
    public static volatile SingularAttribute<TransactionHistoryEntity, String> senderName;
    public static volatile SingularAttribute<TransactionHistoryEntity, String> reciverName;
    public static volatile SingularAttribute<TransactionHistoryEntity, Long> requesterId;
    public static volatile SingularAttribute<TransactionHistoryEntity, Long> id;
    public static volatile SingularAttribute<TransactionHistoryEntity, String> message;
    public static volatile SingularAttribute<TransactionHistoryEntity, LocalDateTime> transactionDate;
    public static volatile SingularAttribute<TransactionHistoryEntity, Integer> status;

}