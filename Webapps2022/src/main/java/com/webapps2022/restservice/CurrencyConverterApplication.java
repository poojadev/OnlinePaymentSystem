/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.restservice;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author pooja
 */
@ApplicationPath("/")

public class CurrencyConverterApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        //register resource
        classes.add(CurrencyConverterAPI.class);
        classes.add(NewApi.class);
        return classes;
    }
    
    
}