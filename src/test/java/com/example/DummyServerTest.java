package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.Assert;
import org.junit.Test;


import lombok.extern.java.Log;

@Log
public class DummyServerTest {

    
    @Test
    public void testMain() throws Exception {
        DummyServer.main(new String[]{"start"});

        URL url = new URL("http://localhost:8000/test");

        HttpURLConnection connection =(HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
        Assert.assertEquals(200, connection.getResponseCode());
        String response = getResponse(connection);
		log.info(response);
		Assert.assertNotNull(response);

    }

    private String getResponse(HttpURLConnection connection){
		StringBuilder response = new StringBuilder();

		// Read response
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(connection.getInputStream()))) {

			String line;

			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
		}
		catch (IOException e){
			log.severe( e.getMessage());
		}

		return response.toString();
	}
}
