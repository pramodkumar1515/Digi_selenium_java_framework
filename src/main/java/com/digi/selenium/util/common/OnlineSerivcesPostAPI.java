package com.digi.selenium.util.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public enum OnlineSerivcesPostAPI {

	// AccountPlanSettingsAggregatorService

	/**
	 * isPlanSuspended , selectedMsisdn
	 */
	UPDATE_BAR_UNBAR_INFO_POST {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				Map<String, String> headersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/planSettings/barUnbarPost/";
			OnlineServicesImpl services = new OnlineServicesImpl();

			// Map<String,String> validatedMap=validate(inparameters);
			String stringEntity = makeJsonObject(inparameters);

			return services.postServiceMethod(inparameters, puthttpClient,
					headersForServices, url, stringEntity);
		}
	},
	/**
	 * selectedMsisdn, orderId ,offerName
	 * 
	 */
	UPDATE_IR_STATUS_POST {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				Map<String, String> headersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/planSettings/updateIRPost/";
			OnlineServicesImpl services = new OnlineServicesImpl();
			// Map<String,String> validatedMap=validate(inparameters);
			String stringEntity = makeJsonObject(inparameters);

			return services.postServiceMethod(inparameters, puthttpClient,
					headersForServices, url, stringEntity);
		}
	},
	
	FEEDBACK {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				Map<String, String> headersForServices)
				throws ClientProtocolException, IOException {
             //http://localhost:8080/digionline/services/feedback
			String url = serviceUrl + "services/feedback";
			OnlineServicesImpl services = new OnlineServicesImpl();
			// Map<String,String> validatedMap=validate(inparameters);
			String stringEntity = makeJsonObject(inparameters);

			return services.postServiceMethod(inparameters, puthttpClient,
					headersForServices, url, stringEntity);
		}
	},
	
	// updateCLIRStatusPost
	/**
	 * isBlockCallerIdEnabled ,orderId ,selectedMsisdn ,offerName
	 * 
	 */
	UPDATE_CLIR_STATUS_POST {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				Map<String, String> headersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/planSettings/updateCLIRPost/";
			OnlineServicesImpl services = new OnlineServicesImpl();

			// Map<String,String> validatedMap=validate(inparameters);
			String stringEntity = makeJsonObject(inparameters);

			return services.postServiceMethod(inparameters, puthttpClient,
					headersForServices, url, stringEntity);
		}
	},
	//****************************************************************
	
	/**    SPRINT 2   ***/
	
	//****************************************************************
	//updateAccountSettingsAndDetails/changeBillingAddress
	UPDATE_ACCOUNT_SETTINGS_AND_DETAILS_CHANGE_BILLING_ADDRESS {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				Map<String, String> headersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/updateAccountSettingsAndDetails/changeBillingAddress/";
			CacheCleanRequiredHeader_OnlineServiceImpl services = new CacheCleanRequiredHeader_OnlineServiceImpl();

			// Map<String,String> validatedMap=validate(inparameters);
			String stringEntity = makeJsonObject(inparameters);
			
			return services.postServiceMethod(inparameters, puthttpClient,
					headersForServices, url, stringEntity);
		}
	},
	UPDATE_ACCOUNT_SETTINGS_AND_DETAILS_UPDATE_PAPER_BILLMEDIUM {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				Map<String, String> headersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/updateAccountSettingsAndDetails/updatePaperBillMedium/";
			CacheCleanRequiredHeader_OnlineServiceImpl services = new CacheCleanRequiredHeader_OnlineServiceImpl();

			// Map<String,String> validatedMap=validate(inparameters);
			String stringEntity = makeJsonObject(inparameters);
			return services.postServiceMethod(inparameters, puthttpClient,
					headersForServices, url, stringEntity);
		}
	},
	UPDATE_ACCOUNT_SETTINGS_AND_DETAILS_UPDATEE_MAIL_BILLMEDIUM {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				Map<String, String> headersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/updateAccountSettingsAndDetails/updateEmailBillMedium";
			CacheCleanRequiredHeader_OnlineServiceImpl services = new CacheCleanRequiredHeader_OnlineServiceImpl();

			// Map<String,String> validatedMap=validate(inparameters);
			String stringEntity = makeJsonObject(inparameters);
			return services.postServiceMethod(inparameters, puthttpClient,
					headersForServices, url, stringEntity);
		}
	},
	UPDATE_ACCOUNT_SETTINGS_AND_DETAILS_UPDATE_SMS_NOTIFICATION {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				Map<String, String> headersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/updateAccountSettingsAndDetails/updateSmsNotification";
			CacheCleanRequiredHeader_OnlineServiceImpl services = new CacheCleanRequiredHeader_OnlineServiceImpl();

			// Map<String,String> validatedMap=validate(inparameters);
			String stringEntity = makeJsonObject(inparameters);
			return services.postServiceMethod(inparameters, puthttpClient,
					headersForServices, url, stringEntity);
		}
	},
	//17-08-2015
	UPDATE_EMAIL_SERVICE {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				Map<String, String> headersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/updateEmailService/";
			OnlineServicesImpl services = new OnlineServicesImpl();

			// Map<String,String> validatedMap=validate(inparameters);
			String stringEntity = makeJsonObject(inparameters);
			return services.postServiceMethod(inparameters, puthttpClient,
					headersForServices, url, stringEntity);
		}
	},
	//17-08-2015
	//linkaccounts
	LINKACCOUNTS_ALLACCOUNTS {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				Map<String, String> headersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/linkaccounts/allaccounts";
			OnlineServicesImpl services = new OnlineServicesImpl();

			// Map<String,String> validatedMap=validate(inparameters);
			String stringEntity = makeJsonObject(inparameters);
			return services.postServiceMethod(inparameters, puthttpClient,
					headersForServices, url, stringEntity);
		}
	},
	//18-08-2015
	//linkaccounts
	UPDATE_EMAILS_IN_CRM {
			@Override
			public String execute(final Map<String, String> inparameters,
					final DefaultHttpClient puthttpClient, final String serviceUrl,
					Map<String, String> headersForServices)
					throws ClientProtocolException, IOException {

				String url = serviceUrl + "services/linkaccounts/updateEmailsInCrm";
				OnlineServicesImpl services = new OnlineServicesImpl();

				// Map<String,String> validatedMap=validate(inparameters);
				String stringEntity = makeJsonObject(inparameters);
				return services.postServiceMethod(inparameters, puthttpClient,
						headersForServices, url, stringEntity);
			}
		},
	UPDATE_SHARED_QUOTA {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				Map<String, String> headersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/planDataSharingService/updateSharedQuota/";
			OnlineServicesImpl services = new OnlineServicesImpl();

			// Map<String,String> validatedMap=validate(inparameters);
			String stringEntity = makeJsonObject(inparameters);
			return services.postServiceMethod(inparameters, puthttpClient,
					headersForServices, url, stringEntity);
		}
	};

	public abstract String execute(Map<String, String> inparameters,
			DefaultHttpClient puthttpClient, String serviceUrl,
			Map<String, String> headersForServices)
			throws ClientProtocolException, IOException;

	{
	}

	public Map<String, String> validate(Map<String, String> inparams)
			throws JsonGenerationException, JsonMappingException, IOException {

		for (Map.Entry<String, String> entry : inparams.entrySet()) {
			if (entry.getValue().trim().equals(""))
				entry.setValue("0000");
		}
		return inparams;
	}

	public String makeJsonObject(Map<String, String> inparams)
			throws JsonGenerationException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(inparams);
		return json;

	}

}
