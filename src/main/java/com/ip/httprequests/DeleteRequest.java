package com.ip.httprequests;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteRequest {

    @Test
    public void deleteRequestTest() throws IOException {

        String URL = "https://reqres.in/api/users/2";

        HttpDelete httpDelete = new HttpDelete(URL);
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpResponse httpResponse = httpClient.execute(httpDelete);

        int statusCode = httpResponse.getStatusLine().getStatusCode();

        Assert.assertEquals(statusCode, 204);

        System.out.println(statusCode);

        String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        System.out.println();
        System.out.println(responseBody);
    }
}
