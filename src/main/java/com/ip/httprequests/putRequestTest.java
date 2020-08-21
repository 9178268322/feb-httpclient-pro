package com.ip.httprequests;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class putRequestTest {

    @Test
    public  void putRequestTest() throws IOException {

        String URL = "https://reqres.in/api/users/2";

        HttpPut httpPutput = new HttpPut(URL);
        HttpClient httpClient = HttpClientBuilder.create().build();

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Allen");
        jsonObject.put("job", "Developer");

        StringEntity stringEntity = new StringEntity(jsonObject.toJSONString());

        httpPutput.setEntity(stringEntity);

        // for all header
        for (Map.Entry<String, String> entrys : hashMap.entrySet()) {
            httpPutput.addHeader(entrys.getKey(), entrys.getValue());
        }

        HttpResponse httpResponse = httpClient.execute(httpPutput);

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
