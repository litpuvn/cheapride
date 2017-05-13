package com.cheapRide.util;

import static org.junit.Assert.fail;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
/**
 * 
 * @author Amit
 * Common util class which serves common utility functionality
 */



@Component
public class CommonUtil {
	
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	 public static final MediaType MEDIA_TYPE_JSON
     = MediaType.parse("application/json; charset=utf-8");

	public static String makeGetReuqest(String urlRequest, Map<String, String> queryPrms, Map<String, String> headersMap){
		String responseStr = null;
		logger.debug("Start : CommonUtil => makeGetReuqest  for URL " + urlRequest);
		try {
			OkHttpClient client = new OkHttpClient();

			HttpUrl.Builder urlBuilder =HttpUrl.parse(urlRequest + Contants.Q_MARK).newBuilder();
			if(queryPrms !=null){
				for(Map.Entry<String,String> entry : queryPrms.entrySet() ){
					urlBuilder.addQueryParameter(entry.getKey(),entry.getValue());
				}
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
	
	public static String makePostReuqest(String urlRequest, String bodyStr, Map<String, String> headersMap){
		String responseStr = null;
		logger.debug("Start : CommonUtil => makePostReuqest  for URL " + urlRequest);
		try {
			OkHttpClient client = new OkHttpClient();

			HttpUrl.Builder urlBuilder =HttpUrl.parse(urlRequest ).newBuilder();
			
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
			  
			  String postBody = bodyStr;

			  Request request = builder.post(RequestBody.create(MEDIA_TYPE_JSON, postBody)).build();

			  Response response = client.newCall(request).execute();
			  responseStr = response.body().string();

		} catch (Exception exc) {
			fail(exc.getMessage());
			logger.error("ERROR : CommonUtil => makePostReuqest  for URL " + exc.getMessage());
		}
		
		logger.debug("End : CommonUtil => makePostReuqest  for Response " + responseStr);

		return responseStr;
	}
	
	public static String makePutReuqest(String urlRequest, String bodyStr, Map<String, String> headersMap){
		String responseStr = null;
		logger.debug("Start : CommonUtil => makePutReuqest  for URL " + urlRequest);
		try {
			OkHttpClient client = new OkHttpClient();

			HttpUrl.Builder urlBuilder =HttpUrl.parse(urlRequest ).newBuilder();
			
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
			  
			  String postBody = bodyStr;

			  Request request = builder.put(RequestBody.create(MEDIA_TYPE_JSON, postBody)).build();

			  Response response = client.newCall(request).execute();
			  responseStr = response.body().string();

		} catch (Exception exc) {
			fail(exc.getMessage());
			logger.error("ERROR : CommonUtil => makePutReuqest  for URL " + exc.getMessage());
		}
		
		logger.debug("End : CommonUtil => makePutReuqest  for Response " + responseStr);

		return responseStr;
	}

}
