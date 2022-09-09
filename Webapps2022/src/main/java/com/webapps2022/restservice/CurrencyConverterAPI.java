/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.restservice;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author pooja
 */
@Singleton
@Path("currency")
public class CurrencyConverterAPI {

public CurrencyConverter currencyConverter = new CurrencyConverter();    

@GET
@Path("{senderCurrency}/{receiverCurrency}/{amount}")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Double getConvertedAmount(
 @PathParam("senderCurrency") String senderCurrency,
 @PathParam("receiverCurrency") String receiverCurrency,
 @PathParam("amount") Double amount ) {
        return 
                
                
                
                currencyConverter.Convert(senderCurrency, receiverCurrency, amount);
    }
    
    
    

}
