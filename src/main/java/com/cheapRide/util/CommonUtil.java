package com.cheapRide.util;

import static org.junit.Assert.fail;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cheapRide.service.impl.UberEstmiateServiceImpl;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
/**
 * 
 * @author Amit
 * Common util class which serves common utility functionality
 */

@Component
public class CommonUtil {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	public static String makeGetReuqest(String urlRequest, Map<String, String> queryPrms, Map<String, String> headersMap){
		String responseStr = null;
		logger.debug("Start : CommonUtil => makeGetReuqest  for URL " + urlRequest);
		try {
			OkHttpClient client = new OkHttpClient();

			HttpUrl.Builder urlBuilder =HttpUrl.parse(urlRequest + Contants.Q_MARK).newBuilder();
			for(Map.Entry<String,String> entry : queryPrms.entrySet() ){
				urlBuilder.addQueryParameter(entry.getKey(),entry.getValue());
			}

			String url = urlBuilder.build().toString();

			//Request request = new Request.Builder().header("Content-Type", "application/json")
			//		.header("Accept-Language", "en_EN").url(url).build();
			
			Request.Builder builder = new Request.Builder()
				      .url(url);
			
			Headers.Builder requestHeaders = new Headers.Builder(); 
			
			Headers headers = requestHeaders.build();
			  for (int i = 0, size = headers.size(); i < size; i++) {
			    builder.addHeader(headers.name(i), headers.value(i));
			  }
			  
			  for(Map.Entry<String,String> entry : headersMap.entrySet() ){
				  builder.addHeader(entry.getKey(),entry.getValue());
				}
			
			  Request request = builder.build();

			Response response = client.newCall(request).execute();
			responseStr = new String(response.body().bytes());
			//System.out.println(responseStr);

		} catch (Exception exc) {
			fail(exc.getMessage());
			logger.error("ERROR : CommonUtil => makeGetReuqest  for URL " + exc.getMessage());
		}
		
		logger.debug("End : CommonUtil => makeGetReuqest  for Response " + responseStr);

		return responseStr;
	}

}
