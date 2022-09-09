/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps2022.thrift;

import org.apache.thrift.TException;

/**
 *
 * @author pooja
 */
public class TimestampHandler implements TimeStamp.Iface {

     @Override
    public long getTimeStamp() throws TException {
   long time = System.currentTimeMillis();
      System.out.println("time() called: " + time);
        return time;
    }

   

   
}
