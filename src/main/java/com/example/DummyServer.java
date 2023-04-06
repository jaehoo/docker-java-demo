package com.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.util.ExecutionPath;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;

import lombok.extern.java.Log;

@Log
public class DummyServer {

    private static HttpServer server;
    private static String LOGS_DIR = "LOGS_DIR";
    

    public static void main(String[] args) throws Exception {

        configLogger();

        log.info(new ExecutionPath().getDirectory());

        if(args == null || args.length == 0 ){
            log.info("Not args provided, use: [start|stop] option");
            
        }else if("start".equals(args[0])){
            server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/test", new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        }else if(server != null && "stop".equals(args[0])){
            log.info("bye bye! =)");
            server.stop(3);
        }
    }


    static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            
            Date time= Calendar.getInstance().getTime();
            log.info("sending response... "+ time);

            StringBuilder response = new StringBuilder();
            response.append("<h1>This is the response =) </h1>");
            response.append("<p>").append(time).append("</p>");
            t.sendResponseHeaders(200, response.length());

            try(OutputStream os = t.getResponseBody()){
                os.write(response.toString().getBytes());
                os.close();
            }
            
        }
    }

    private static void configLogger(){

        // Crate log dir if don't exists
        File logDir = new File(new ExecutionPath().getDirectory()+File.separator+"log");
        if(!logDir.exists()){
            logDir.mkdirs();
        }

        // Read config file
        try (InputStream configStream = DummyServer.class.getResourceAsStream("/logging.properties")) {
            LogManager.getLogManager().readConfiguration(configStream);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error in loading configuration", e);
        }
 
    }
}
