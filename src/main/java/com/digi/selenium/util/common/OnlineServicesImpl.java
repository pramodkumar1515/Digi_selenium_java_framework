package com.digi.selenium.util.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class OnlineServicesImpl {

	
	private  Logger log = Logger.getLogger(OnlineServicesImpl.class);

	/**
	 * serviceMethod added String[] parameters
	 * 
	 * @param url
	 * @param httpClient1
	 * @param headersForServices
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String requestID() {

		String mdcRequestIdLocal = UUID.randomUUID().toString();

		if (mdcRequestIdLocal.length() >= 15) {
			mdcRequestIdLocal = UUID.randomUUID().toString();
			mdcRequestIdLocal = mdcRequestIdLocal.substring(0, 14)
					+ new Random().nextInt(10000);
		}
		log.info(" longString " + mdcRequestIdLocal + "  lenght :"
				+ mdcRequestIdLocal.length());

		/*
		 * UUID uuid = UUID.randomUUID(); long l =
		 * ByteBuffer.wrap(uuid.toString().getBytes()).getLong(); String
		 * longString = Long.toString(l, );
		 */

		return mdcRequestIdLocal;
	}

	protected String serviceMethod(String url, DefaultHttpClient httpClient1,
			String[] headersForServices) throws ClientProtocolException,
			IOException {
		int code = 0;
		HttpResponse response;
		log.info(" URL .. " + url);

		HttpClient httpClient = httpClient1;

		StringBuffer stringbuff = null;
		String statusmsg = null;
		try {
			HttpUriRequest httpGet = new HttpGet(url);
			httpGet.addHeader("Accept", "application/json");
			String mdcRequestId = requestID();
			log.info("mdcRequestId ::" + mdcRequestId);
			httpGet.setHeader("mdcRequestId", mdcRequestId);
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

	protected String serviceMethodPdf(String url,
			DefaultHttpClient httpClient1, String[] headersForServices)
			throws ClientProtocolException, IOException {
		int code = 0;
		HttpResponse response;
		log.info(" URL .. " + url);

		HttpClient httpClient = httpClient1;

		StringBuffer stringbuff = null;
		String statusmsg = null;
		try {
			HttpUriRequest httpGet = new HttpGet(url);
			httpGet.addHeader("Accept", "application/pdf");
			String mdcRequestId = requestID();
			log.info("mdcRequestId ::" + mdcRequestId);
			httpGet.setHeader("mdcRequestId", mdcRequestId);
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

	/**
	 * Service Method for PUT
	 * 
	 * @param inparameters
	 * @param puthttpClient
	 * @param mapHeadersForServices
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */

	public String putServiceMethod(Map<String, String> inparameters,
			DefaultHttpClient puthttpClient,
			Map<String, String> mapHeadersForServices, String url)
			throws JsonGenerationException, JsonMappingException, IOException {
		int code = 0;
		HttpResponse response;
		HttpPut httpput = null;
		StringBuffer stringbuff = null;
		String statusmsg = null;
		try {
			httpput = new HttpPut(url);

			Set<Map.Entry<String, String>> paramKeySet = inparameters
					.entrySet();

			StringBuffer buildRequest = new StringBuffer();
			boolean firstElement = true;
			for (Map.Entry<String, String> me : paramKeySet) {
				String key = (String) me.getKey();
				String value = (String) me.getValue();
				if (!firstElement)
					buildRequest.append("&");
				buildRequest.append(key).append("=").append(value);
				firstElement = false;

			}

			String mdcRequestId = requestID();
			log.info("mdcRequestId ::" + mdcRequestId);
			log.info("httpPut Params :: " + buildRequest.toString());

			HttpEntity entity = new StringEntity(buildRequest.toString());
			log.info(" URL .. " + url);

			httpput.setHeader("Content-Type",
					"application/x-www-form-urlencoded");
			httpput.addHeader("Accept", "application/json");
			httpput.setHeader("bearer", mapHeadersForServices.get("tokenId"));
			httpput.setHeader("x-up-calling-line-id",
					mapHeadersForServices.get("x-up-calling-line-id"));
			httpput.setHeader("mdcRequestId", mdcRequestId);

			httpput.setEntity(entity);

			response = puthttpClient.execute(httpput);

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

	public String putServiceMethodJSON(Map<String, String> inparameters,
			DefaultHttpClient puthttpClient,
			Map<String, String> mapHeadersForServices, String url)
			throws JsonGenerationException, JsonMappingException, IOException {
		int code = 0;
		HttpResponse response;
		HttpPut httpput = null;
		StringBuffer stringbuff = null;
		String statusmsg = null;
		try {
			httpput = new HttpPut(url);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(inparameters);
			json = json.replace("\\", "");
			json = json.replace("\"[", "[");
			json = json.replace("]\"", "]");

			log.info("put request body json ::" + json);
			String mdcRequestId = requestID();

			log.info("mdcRequestId ::" + mdcRequestId);
			log.info("httpPut Params :: " + json.toString());

			HttpEntity entity = new StringEntity(json);

			log.info(" URL .. " + url);

			httpput.setHeader("Content-Type", "application/json");
			httpput.addHeader("Accept", "application/json");
			httpput.setHeader("bearer", mapHeadersForServices.get("tokenId"));
			httpput.setHeader("x-up-calling-line-id",
					mapHeadersForServices.get("x-up-calling-line-id"));
			httpput.setHeader("mdcRequestId", mdcRequestId);

			httpput.setEntity(entity);

			response = puthttpClient.execute(httpput);

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

	/**
	 * Get Method
	 * 
	 * @param url
	 * @param httpClient1
	 * @param mapHeaderParams
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	protected String getServiceMethod(String url,
			DefaultHttpClient httpClient1, Map<String, String> mapHeaderParams)
			throws ClientProtocolException, IOException {
		int code = 0;
		HttpResponse response;
		log.info(" URL .. " + url);
		HttpClient httpClient = httpClient1;
		String mdcRequestId = requestID();

		log.info("mdcRequestId ::" + mdcRequestId);

		StringBuffer stringbuff = null;
		String statusmsg = null;
		try {
			HttpUriRequest httpGet = new HttpGet(url);
			httpGet.addHeader("Accept", "application/json");
			httpGet.addHeader("Bearer", (String) mapHeaderParams.get("tokenId"));
			httpGet.addHeader("x-up-calling-line-id",
					(String) mapHeaderParams.get("x-up-calling-line-id"));
			httpGet.setHeader("mdcRequestId", mdcRequestId);
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

	/**
	 * POST method
	 * 
	 * @param inparameters
	 * @param puthttpClient
	 * @param headersForServices
	 * @param url
	 * @param stringEntity
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public String postServiceMethod(Map<String, String> inparameters,
			DefaultHttpClient puthttpClient,
			Map<String, String> headersForServices, String url,
			String stringEntity) throws JsonGenerationException,
			JsonMappingException, IOException {
		int code = 0;
		HttpResponse response;
		HttpPost httpPost = null;
		StringBuffer stringbuff = null;
		String statusmsg = null;
		try {
			String mdcRequestId = requestID();
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

	public String ErrorHandlingMessage(Map<String, String> errorMap)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(errorMap);
		return json;

	}

}
