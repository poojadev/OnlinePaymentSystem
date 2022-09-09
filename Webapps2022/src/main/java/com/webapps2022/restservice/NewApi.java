///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.webapps2022.restservice;
//
//import javax.inject.Singleton;
//import javax.ws.rs.Path;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Invocation;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//
///**
// *
// * @author pooja
// */
//@Singleton
//@Path("currencyconvert")
//public class NewApi {
//    
//      private CurrencyClass getCurrentFx(String currency1, String currency2, double amount) {
//        Client client = ClientBuilder.newClient();
//        WebTarget conversionResource = client.target("http://localhost::8080/onlinepaymentsystem/")
//                .path("{currency1}")
//                .resolveTemplate("currency1", currency1)
//                .path("{currency2}")
//                .resolveTemplate("currency2", currency2)
//                .path("{amount_of_currency1}")
//                .resolveTemplate("amount_of_currency1", String.valueOf(amount));
//        Invocation.Builder builder = conversionResource.request(MediaType.APPLICATION_JSON);
//        CurrencyClass response = builder.get(CurrencyClass.class);
//        client.close();
//        return response;
//    }
//    
//}
