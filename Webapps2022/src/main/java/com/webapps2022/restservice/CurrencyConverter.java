/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.restservice;

/**
 *
 * @author pooja
 */
public class CurrencyConverter {
    
    Double convertedAmount = 0.0;  

    public CurrencyConverter() {}

    public Double Convert(String senderCurrency, String receiverCurrency, Double amount){

      if(senderCurrency.equals("GBP")&&receiverCurrency.equals("USD")){
         convertedAmount =  amount*1.31;
      return convertedAmount;

}else if (senderCurrency.equals("GBP")&&receiverCurrency.equals("EURO")){
        convertedAmount =  amount*1.21;
      return convertedAmount;

}else if (senderCurrency.equals("USD")&&receiverCurrency.equals("GBP")){
        convertedAmount =  amount*0.76;
          System.out.println("com.webapps2022.restservice.CurrencyConverter.Convert()" +convertedAmount);
      return convertedAmount;
} else if (senderCurrency.equals("USD")&&receiverCurrency.equals("EURO")){
       convertedAmount =  amount*0.92;
      return convertedAmount;
} else if (senderCurrency.equals("EURO")&&receiverCurrency.equals("GBP")){
       convertedAmount =  amount*0.83;
      return convertedAmount;
} else if (senderCurrency.equals("EURO")&&receiverCurrency.equals("USD")) {
     convertedAmount =  amount*1.08;
      return convertedAmount;
}
return 0.0;
}
    
}
