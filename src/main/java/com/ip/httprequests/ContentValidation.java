package com.ip.httprequests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ContentValidation {

    @Test
    public  void content() throws IOException {

        String URL = "https://reqres.in/api/users?page=2"; // initialising the url

        HttpGet httpGet = new HttpGet(URL); // passing url
        HttpClient httpClient = HttpClientBuilder.create().build(); // creating httpclient instance
        HttpResponse httpResponse = httpClient.execute(httpGet); // hitting url

        String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(responseBody);

        String link = jsonNode.get("data").get(5).get("avatar").asText();

        String email  = jsonNode.get("data").get(1).get("email").asText();

        Assert.assertEquals(link, "https://s3.amazonaws.com/uifaces/faces/twitter/hebertialmeida/128.jpg");
        Assert.assertEquals(email, "lindsay.ferguson@reqres.in");

        System.out.println(link);
        System.out.println(email);
    }
}
