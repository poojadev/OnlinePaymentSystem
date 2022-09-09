package com.webapps2022.thrift;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.inject.Singleton;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

@Startup
@Singleton
public class TimeStampServer  {
    public static TServer server;
    
    public static TimestampHandler handler;
    public static TimeStamp.Processor processor;
    public static TServerTransport serverTransport;

    @PostConstruct
    public void init() {
        System.out.println("com.webapps2022.thrift.CalculatorServer.init()");
        startServer();
    }

    private void startServer() {
        System.out.println("com.webapps2022.thrift.CalculatorServer.startServer()");
        try {
            handler = new TimestampHandler();
            processor = new TimeStamp.Processor(handler);

            Runnable serverThread = new Runnable() {
                @Override
                public void run() {
                    try {
                        simpleServer(processor);
                    } catch (TException ex) {
                        Logger.getLogger(TimeStampServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

            new Thread(serverThread).start();

        } catch (Exception x) {
            System.err.println(x);
        }
    }

    public static void simpleServer(TimeStamp.Processor processor) throws TException {
        try {
            System.out.println("com.webapps2022.thrift.CalculatorServer.simpleServer()");
            serverTransport = new TServerSocket(9191);
            server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
           
      
            System.out.println("Starting the timestamp server in Thread " + getTimeStamps());
            server.serve();
        } catch (TTransportException e) {
            System.err.println(e);
        }
    }
    
    
    public static LocalDateTime getTimeStamps() throws TTransportException, TException{
                System.out.println("Starting the timestamp server in Thread " + handler.getTimeStamp());

           
           
           LocalDateTime thriftTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(handler.getTimeStamp()), TimeZone
                    .getDefault().toZoneId()); 
                           System.out.println("triggerTimetriggerTimecom.webapps2022.thrift.CalculatorServer.simpleServer()" +thriftTime);

           return  thriftTime;

    }

    @PreDestroy
    public void StopServer(){
        server.stop();
    }
}