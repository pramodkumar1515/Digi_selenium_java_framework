package com.digi.selenium.util.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.log4j.Logger;


public class OnlineServices {
	
	private  Logger log = Logger.getLogger(OnlineServices.class);
	/**
	 * For Get Request NEW modified added parameter String[] 07-05-2015
	 * 
	 * @param apiName
	 * @param serviceUrl
	 * @param parameters
	 * @return
	 * @throws IOException
	 * @throws OnlineServicesException
	 * @throws ClientProtocolException
	 */
	public String execute(String apiName, String serviceUrl,
			Object[] parameters, String[] headersForServices)
			throws ClientProtocolException, IOException {
		log.info(("  new     >>>>>>>>>  API  PASSING x-up-calling-line-id"));
		OnlineServicesAPI api = OnlineServicesAPI.valueOf(apiName);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		return api.execute(parameters, httpClient, serviceUrl,
				headersForServices);
	}

	/**  For Get Request OLD
	 * 
	 * @param apiName
	 * @param serviceUrl
	 * @param parameters
	 * @param tokenId
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	
	public String execute(String apiName, String serviceUrl,
			Object[] parameters, String tokenId)
			throws ClientProtocolException, IOException {
		log.info(("  OLD     >>>>>>>>>  API   NOT PASSING x-up-calling-line-id"));
		String[] headersForServices=new String[]{tokenId};
		OnlineServicesAPI api = OnlineServicesAPI.valueOf(apiName);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		return api.execute(parameters, httpClient, serviceUrl, headersForServices);
	}

	/**
	 * FOR PUT Request...
	 * 
	 * 
	 * @param inparameters
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String execute(Map<String, String> inparameters)
			throws ClientProtocolException, IOException {

		Map<String, String> mapHeadersForServices = new HashMap<String, String>();
		String apiName = inparameters.get("apiName");
		inparameters.remove("apiName");
		String serviceUrl = inparameters.get("baseUrl");
		inparameters.remove("baseUrl");

		mapHeadersForServices.put("tokenId", inparameters.get("tokenId"));
		inparameters.remove("tokenId");
		mapHeadersForServices.put("x-up-calling-line-id",
				inparameters.get("x-up-calling-line-id"));
		inparameters.remove("x-up-calling-line-id");

		OnlineSerivcesPutAPI api = OnlineSerivcesPutAPI.valueOf(apiName);
		DefaultHttpClient puthttpClient = new DefaultHttpClient();
		return api.execute(inparameters, puthttpClient, serviceUrl,
				mapHeadersForServices);
	}
	
	
	public String executePost(Map<String, String> inparameters)
			throws ClientProtocolException, IOException {

		Map<String, String> mapHeadersForServices = new HashMap<String, String>();
		String apiName = inparameters.get("apiName");
		inparameters.remove("apiName");
		String serviceUrl = inparameters.get("baseUrl");
		inparameters.remove("baseUrl");

		mapHeadersForServices.put("tokenId", inparameters.get("tokenId"));
		inparameters.remove("tokenId");
		mapHeadersForServices.put("x-up-calling-line-id",
				inparameters.get("x-up-calling-line-id"));
		inparameters.remove("x-up-calling-line-id");
		

		OnlineSerivcesPostAPI api = OnlineSerivcesPostAPI.valueOf(apiName);
		DefaultHttpClient puthttpClient = new DefaultHttpClient();
		
		
		return api.execute(inparameters, puthttpClient, serviceUrl,
				mapHeadersForServices);
	}
}
