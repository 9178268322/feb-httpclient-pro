package com.ip.httprequests;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class RequestTest {
    @Test
    public void getRequest() throws IOException {

        String URL = "https://reqres.in/api/users?page=2"; // initialising the url

        HttpGet httpGet = new HttpGet(URL); // passing url
        HttpClient httpClient = HttpClientBuilder.create().build(); // creating httpclient instance
        HttpResponse httpResponse = httpClient.execute(httpGet); // hitting url

        int statusCode = httpResponse.getStatusLine().getStatusCode();

        Assert.assertEquals(statusCode, 200);
        System.out.println(statusCode);

        String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        System.out.println();
        System.out.println(responseBody);

        Header[] allHeaders = httpResponse.getAllHeaders();

        HashMap<String, String> headers = new HashMap<String, String>();

        for (Header h : allHeaders) {
            headers.put(h.getName(), h.getValue());
        }

        System.out.println();
        System.out.println(headers);




    }
}
