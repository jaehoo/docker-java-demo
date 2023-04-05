package com.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import lombok.extern.java.Log;

@Log
public class DummyServer {

    private static HttpServer server;


    public static void main(String[] args) throws Exception {
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
            String response = "<h1>This is the response =)</h1>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
}
