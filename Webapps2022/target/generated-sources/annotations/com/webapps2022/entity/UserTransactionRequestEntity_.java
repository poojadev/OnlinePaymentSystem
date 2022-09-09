package com.webapps2022.entity;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-04-30T13:34:14")
@StaticMetamodel(UserTransactionRequestEntity.class)
public class UserTransactionRequestEntity_ { 

    public static volatile SingularAttribute<UserTransactionRequestEntity, String> currecyType;
    public static volatile SingularAttribute<UserTransactionRequestEntity, String> requestTo;
    public static volatile SingularAttribute<UserTransactionRequestEntity, Long> requestToId;
    public static volatile SingularAttribute<UserTransactionRequestEntity, Double> requestedAmount;
    public static volatile SingularAttribute<UserTransactionRequestEntity, Long> id;
    public static volatile SingularAttribute<UserTransactionRequestEntity, String> message;
    public static volatile SingularAttribute<UserTransactionRequestEntity, Long> requestFromId;
    public static volatile SingularAttribute<UserTransactionRequestEntity, String> requestFrom;
    public static volatile SingularAttribute<UserTransactionRequestEntity, LocalDateTime> notificationDate;
    public static volatile SingularAttribute<UserTransactionRequestEntity, Integer> status;

}