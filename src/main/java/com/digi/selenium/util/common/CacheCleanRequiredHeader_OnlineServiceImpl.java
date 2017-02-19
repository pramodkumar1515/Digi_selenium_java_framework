package com.digi.selenium.util.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class CacheCleanRequiredHeader_OnlineServiceImpl {
	
	StringBuffer stringbuff = null;
	
	private  Logger log = Logger.getLogger(CacheCleanRequiredHeader_OnlineServiceImpl.class);
	OnlineServicesImpl OnlineServicesImplrequestId=new OnlineServicesImpl();
	
	
	
	public String postServiceMethod(Map<String, String> inparameters,
			DefaultHttpClient puthttpClient,
			Map<String, String> headersForServices, String url,
			String stringEntity) throws JsonGenerationException,
			JsonMappingException, IOException {
		int code = 0;
		HttpResponse response;
		HttpPost httpPost = null;
		
		String statusmsg = null;
		try {
			String mdcRequestId = OnlineServicesImplrequestId.requestID();
			log.info("mdcRequestId ::" + mdcRequestId);

			httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(stringEntity);
			log.info(" URL .. " + url);

			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("bearer", headersForServices.get("tokenId"));
			httpPost.setHeader("x-up-calling-line-id",
					headersForServices.get("x-up-calling-line-id"));
			httpPost.setHeader("mdcRequestId", mdcRequestId);
			httpPost.setHeader("cachecleanrequired", "true");
			log.info("post request body json ::" + entity);
			httpPost.setEntity(entity);
			response = puthttpClient.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			code = statusLine.getStatusCode();
			statusmsg = statusLine.getReasonPhrase();
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			stringbuff = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				stringbuff.append(line);
			}
			return stringbuff.toString();
		} catch (Exception e) {

			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("code", "" + code);
			errorMap.put("message", statusmsg);
			errorMap.put("Exception", "" + e);

			log.error("OnlineServicesImpl..PUT Request.  code:: " + code
					+ "    message::" + statusmsg + "  Exception ::" + e);
			return ErrorHandlingMessage(errorMap);

		}
	}
	protected String serviceMethod(String url, DefaultHttpClient httpClient1,
			String[] headersForServices) throws ClientProtocolException,
			IOException {
		int code = 0;
		HttpResponse response;
		log.info(" URL .. " + url);
		OnlineServicesImpl OnlineServicesImplrequestId=new OnlineServicesImpl();
		HttpClient httpClient = httpClient1;

		StringBuffer stringbuff = null;
		String statusmsg = null;
		try {
			HttpUriRequest httpGet = new HttpGet(url);
			httpGet.addHeader("Accept", "application/json");
			String mdcRequestId = OnlineServicesImplrequestId.requestID();
			log.info("mdcRequestId ::" + mdcRequestId);
			httpGet.setHeader("mdcRequestId", mdcRequestId);
			httpGet.setHeader("cachecleanrequired", "true");
			
			log.info("mdcRequestId.. " + mdcRequestId);
			if (headersForServices.length != 0 && headersForServices != null) {
				httpGet.addHeader("Bearer", headersForServices[0]);
				log.info(" Bearer.. " + headersForServices[0]);
				if (headersForServices.length == 2) {
					httpGet.addHeader("x-up-calling-line-id",
							headersForServices[1]);
					log.info(" x-up-calling-line-id.. "
							+ headersForServices[1]);
				}
			} else {

				log.error(" OnlineServicesImpl serviceMethod(-)  headersForServices not Passing..........");
			}
			response = httpClient.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			code = statusLine.getStatusCode();
			statusmsg = statusLine.getReasonPhrase();
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			stringbuff = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				stringbuff.append(line);
			}
			return stringbuff.toString();
		} catch (Exception e) {

			Map<String, String> errorMap = new HashMap<String, String>();
			errorMap.put("code", "" + code);
			errorMap.put("message", statusmsg);
			errorMap.put("Exception", "" + e);

			log.error("OnlineServicesImpl..PUT Request.  code:: " + code
					+ "    message::" + statusmsg + "  Exception ::" + e);
			return ErrorHandlingMessage(errorMap);
		}

	}

	public String ErrorHandlingMessage(Map<String, String> errorMap)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(errorMap);
		return json;

	}
}
