package com.geektask.week02_2_2;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Hello world!
 *
 */
public class TestHttpClient {
	
	private static final String CHARSET = "UTF-8";
	
    public static void main(String[] args) {
    	
		HttpGet httpGet = new HttpGet("http://localhost:8801");		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				CloseableHttpResponse response = httpClient.execute(httpGet)) {
			
			System.out.println("状态:" + response.getStatusLine());
			HttpEntity responseEntity = response.getEntity();
			
			if (responseEntity != null) {
				System.out.println("内容:" + EntityUtils.toString(responseEntity, CHARSET));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }
}
