package com.digi.selenium.util.common;

import java.io.IOException;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.omg.Dynamic.Parameter;


public enum OnlineServicesAPI {
	
	

	// AccountOverviewService
	RETRIEVE_ACCOUNT_OVERVIEW_DETAILS {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 2);

			String msisdn = (String) parameters[0];
			String email = (String) parameters[1];
			String url = serviceUrl + "services/accountOverviewService/msisdn/"
					+ msisdn + "/email/" + email;
System.out.println(url);
			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	xyz {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 2);

			String msisdn = (String) parameters[0];
			String email = (String) parameters[1];
			String url = serviceUrl + "services/accountOverviewService/msisdn/"
					+ msisdn + "/email/" + email;
System.out.println(url);
			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	RETRIEVE_ACCOUNT_OVERVIEW_DETAILS_USING_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl + "services/accountOverviewService/msisdn/"
					+ msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// AccountPlanSettingsAggregatorService
	// retrievePlanSettings
	RETRIEVE_PLANSETTINGS {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl + "services/planSettings/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// DashboardAggregatorService
	// 1 retrieveDataDashboard
	RETRIEVE_DATA_DASHBOARD_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrieveDataDashboard/data/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveTotalCostDashboard
	RETRIEVE_TOTAL_COST_DASHBOARD_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrieveDataDashboard/totalCost/msisdn/"
					+ msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 3 retrieveVoiceDashboard
	RETRIEVE_VOICE_DASHBOARD_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrieveDataDashboard/voice/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 4 retrieveSmsDashboard
	RETRIEVE_SMS_DASHBOARD_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrieveDataDashboard/sms/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 5 retrieveOtherDashboard
	RETRIEVE_OTHER_DASHBOARD_OTHER_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrieveDataDashboard/other/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 6 retrieveAllDashboard
	RETRIEVE_ALL_DASHBOARD_DATA_OLD {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrieveDataDashboard/allData/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
//DashboardInfoAggregatorService 
	RETRIEVE_ALL_DASHBOARD_DATA {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrieveDashboardInfo/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// ***** HomePageQuickBarAggregatorService ****/
	RETRIEVE_HOME_PAGE_QUICK_BAR_INFO_USING_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl
					+ "services/homePageQuickBarInfo/main/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// ***** HomePageQuickBarNewAggregatorService ****/
	RETRIEVE_NEW_HOME_PAGE_QUICKBAR_INFO_USING_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl
					+ "services/homePageQuickBarInfoNew/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// ***** ItemizedService ****/
	// 1 itemizedByDateUsingSubNoAndDate ITEMIZED_BY_DATE_USING_SUBNO_AND_DATE
	ITEMIZED_BY_DATE_USING_SUBNO_AND_DATE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 3);
			String msisdn = (String) parameters[0];

			if (msisdn.trim().equals(""))
				msisdn = "0000";
			String startDate = (String) parameters[1];
			String endDate = (String) parameters[2];
			String url = serviceUrl + "services/itemizedService/msisdn/"
					+ msisdn + "/startDate/" + startDate + "/endDate/"
					+ endDate;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// 2 itemizedUsageUsingSubscriberId ITEMIZED_USAGE_USING_MSISDN
	ITEMIZED_USAGE_USING_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl + "services/itemizedService/msisdn/"
					+ msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// MyDiGiAccountService
	MYDIGI_ACCOUNT_SERVICE_ACCOUNT_OVERVIEW_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];
			String url = serviceUrl
					+ "services/myDiGiAccountService/accountOverview/msisdn/"
					+ msisdn;
			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// MyDiGiPlanService
	RETRIEVE_PLAN_DETAILS_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];
			String url = serviceUrl
					+ "services/myDiGiPlanService/planDetails/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// PlanDetailsService
	PLAN_DETAILS_SERVICE_USING_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];
			String url = serviceUrl + "services/planDetailsService/msisdn/"
					+ msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// *** QueryBillingByDateService ***/
	// 1 queryBillingByDateUsingBillTypeAndQueryTypeList
	QUERY_BILLING_BY_DATE_USING_BILL_TYPE_AND_QUERY_TYPE_LIST {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 5);

			String subscriberNo = (String) parameters[0];
			String startDate = (String) parameters[1];
			String endDate = (String) parameters[2];
			String queryType = (String) parameters[3];
			String billTypeList = (String) parameters[4];
			String url = serviceUrl
					+ "services/queryBillingByDate/subscriberNo/"
					+ subscriberNo + "/startDate/" + startDate + "/endDate/"
					+ endDate + "/queryType/" + queryType + "/billTypeList/"
					+ billTypeList;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 queryBillingByDateForTotalCost
	QUERY_BILLING_BY_DATE_FOR_TOTAL_COST {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String subscriberNo = (String) parameters[0];

			String url = serviceUrl
					+ "services/queryBillingByDate/totalCost/subscriberNo/"
					+ subscriberNo;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 3 queryBillingByDateForData
	QUERY_BILLING_BY_DATE_FOR_DATA_WITH_SUBSCRIBER_NO {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String subscriberNo = (String) parameters[0];

			String url = serviceUrl
					+ "services/queryBillingByDate/data/subscriberNo/"
					+ subscriberNo;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 4 queryBillingByDateForVoice
	QUERY_BILLING_BY_DATE_FOR_VOICE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String subscriberNo = (String) parameters[0];

			String url = serviceUrl
					+ "services/queryBillingByDate/voice/subscriberNo/"
					+ subscriberNo;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 5 queryBillingByDateForSms
	QUERY_BILLING_BY_DATE_FOR_SMS {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String subscriberNo = (String) parameters[0];

			String url = serviceUrl
					+ "services/queryBillingByDate/sms/subscriberNo/"
					+ subscriberNo;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 6 queryBillingByDateForOthers
	QUERY_BILLING_BY_DATE_FOR_OTHERS {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String subscriberNo = (String) parameters[0];

			String url = serviceUrl
					+ "services/queryBillingByDate/others/subscriberNo/"
					+ subscriberNo;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// PlanAddOnService
	RETRIEVE_ADD_ONS {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl
					+ "services/planAddOnService/retrieveAddOns/msisdn/"
					+ msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	RETRIEVE_QTUS {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl
					+ "services/planAddOnService/retrieveQtus/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	CONFIRM_SUBSCRIBE_ADDONS {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 2);
			String msisdn = (String) parameters[0];
			String offerId = (String) parameters[1];
			String url = serviceUrl
					+ "services/planAddOnService/confirmSubscribeAddOns/msisdn/"
					+ msisdn + "/offerId/" + offerId;

			OnlineServicesImpl services = new OnlineServicesImpl();

			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// **** RetrieveAccountBalanceService **/
	// 1 retrieveAccountBalanceByMsisdn
	RETRIEVE_ACCOUNT_BALANCE_BY_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];
			String url = serviceUrl + "services/retrieveAccountBalance/msisdn/"
					+ msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveAccountBalanceBySubscriberId
	RETRIEVE_ACCOUNT_BALANCE_BY_SUBSCRIBERID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 1);
			String subscriberId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveAccountBalance/subscriberId/"
					+ subscriberId;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// *** PdfGeneratorService **/
	DOWNLOAD_BILL_PDF {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 2);
			String accountId = (String) parameters[0];
			String billDate = (String) parameters[1];
			String url = serviceUrl
					+ "services/pdfGeneratorService/downloadBillPdf/accountId/"
					+ accountId + "/billDate/" + billDate;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethodPdf(url, httpclient, headersForServices);

		}
	},

	// *** RetrieveAccountService **/
	// 1 retrieveAccountUsingCommonSearchAccountCode
	RETRIEVE_ACCOUNT_USING_COMMON_SEARCH_ACCOUNT_CODE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountCode = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveAccount/searchCriteria/commonSearch/accountCode/"
					+ accountCode;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveAccountUsingIndividualSearchCustomerId
	RETRIEVE_ACCOUNT_USING_INDIVIDUAL_SEARCH_CUSTOMER_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String customerId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveAccount/searchCriteria/individualSearch/customerId/"
					+ customerId;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 3 retrieveAccountUsingIndividualSearchAccountId
	RETRIEVE_ACCOUNT_USING_INDIVIDUAL_SEARCH_ACCOUNT_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String customerId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveAccount/searchCriteria/individualSearch/accountId/"
					+ customerId;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 4 retrieveAccountUsingIndividualSearchSubscriberId
	RETRIEVE_ACCOUNT_USING_INDIVIDUAL_SEARCH_SUBSCRIBER_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 1);
			String subscriberId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveAccount/searchCriteria/individualSearch/subscriberId/"
					+ subscriberId;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 5 retrieveAccountUsingIndividualSearchMSISDN
	RETRIEVE_ACCOUNT_USING_INDIVIDUAL_SEARCH_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveAccount/searchCriteria/individualSearch/msisdn/"
					+ msisdn;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 6 retrieveAccountUsingCorporateSearchCustomerId
	RETRIEVE_ACCOUNT_USING_CORPORATE_SEARCH_CUSTOMERID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String customerId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveAccount/searchCriteria/corporateSearch/customerId/"
					+ customerId;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 7 retrieveAccountUsingCorporateSearchAccountId
	RETRIEVE_ACCOUNT_USING_CORPORATE_SEARCH_ACCOUNTID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 1);
			String accountId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveAccount/searchCriteria/corporateSearch/accountId/"
					+ accountId;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// *** RetrieveBEIDService ***/
	// 1 retrieveBEIDByMsisdn
	RETRIEVE_BEID_BY_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];
			String url = serviceUrl + "services/retrieveBEID/msisdn/" + msisdn;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// *** RetrieveBillingAndReloadInfoService ***/
	// 1 retrieveMainBillingAndReloadInfoUsingMSISDN
	RETRIEVE_MAIN_BILLING_AND_RELOAD_INFO_USING__MAIN_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveBillingAndReloadInfo/main/msisdn/"
					+ accountId;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveDefaultBillingAndReloadInfoUsingMSISDN
	RETRIEVE_DEFAULT_BILLING_AND_RELOAD_INFO_USING_DEFAULT_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveBillingAndReloadInfo/default/msisdn/"
					+ accountId;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 3 retrieveDefaultBillingAndReloadInfoUsingAccountId default
	RETRIEVE_DEFAULT_BILLING_AND_RELOAD_INFO_USING_DEFAULT_ACCOUNTID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveBillingAndReloadInfo/default/accountId/"
					+ accountId;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 4 retrieveDefaultBillingAndReloadInfoUsingAccountId main
	RETRIEVE_DEFAULT_BILLING_AND_RELOAD_INFO_USING_MAIN_ACCOUNT_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveBillingAndReloadInfo/main/accountId/"
					+ accountId;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 5 retrieveDefaultBillingAndReloadInfoUsingAccountCode default
	RETRIEVE_DEFAULT_BILLING_AND_RELOAD_INFO_USING_DEFAULT_ACCOUNT_CODE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountCode = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveBillingAndReloadInfo/default/accountCode/"
					+ accountCode;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 6 retrieveMainBillingAndReloadInfoUsingAccountCode main
	RETRIEVE_DEFAULT_BILLING_AND_RELOAD_INFO_USING__MAIN_ACCOUNT_CODE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountCode = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveBillingAndReloadInfo/main/accountCode/"
					+ accountCode;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 7 retrieveBillingAndReloadInfoUsingCustomerId
	RETRIEVE_BILLING_AND_RELOAD_INFO_USING_CUSTOMERID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String customerId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveBillingAndReloadInfo/customerId/"
					+ customerId;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// RetrieveBillingInfoService
	// 1 retrieveBillingInfoUsingMSISDN
	RETRIEVE_BILLING_INFO_USING_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			//Object[] parameters = validation(inparameters, 1);
			//String msisdn = (String) parameters[0];
			String msisdn = (String) inparameters[0];
			String url = serviceUrl + "services/retrieveBillingInfo/msisdn/"
					+ msisdn;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveBillingInfoUsingAccountCode
	RETRIEVE_BILLING_INFO_USING_ACCOUNTCODE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountCode = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveBillingInfo/accountCode/" + accountCode;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// RetrieveCorporateGroupMembersService
	RETRIEVE_CORPORATE_GROUP_MEMBERS_SERVICE_SEARCHCRITERIA_CORPORATEGROUPID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String corporateGroupId = (String) parameters[0];
			String url = serviceUrl
					+ "services/searchCriteria/corporateGroupId/"
					+ corporateGroupId;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// **** RetrieveCorporateGroupsService ***/
	// 1 retrieveCorporateGroupUsingCustomerId
	RETRIEVE_CORPORATE_GROUP_USING_CUSTOMER_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String customerId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveCorporateGroups/searchCriteria/customerId/"
					+ customerId;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveCorporateGroupUsingGroupId
	RETRIEVE_CORPORATE_GROUP_USING_GROUP_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String groupId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveCorporateGroups/searchCriteria/groupId/"
					+ groupId;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 3 retrieveCorporateGroupUsingGroupCode
	RETRIEVE_CORPORATE_GROUP_USING_GROUP_CODE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String groupCode = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveCorporateGroups/searchCriteria/groupCode/"
					+ groupCode;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 4 retrieveCorporateGroupUsingMSISDN
	RETRIEVE_CORPORATE_GROUP_USING_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveCorporateGroups/searchCriteria/msisdn/"
					+ msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 5 retrieveCorporateGroupUsingAccountId
	RETRIEVE_CORPORATE_GROUP_USING_ACCOUNT_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveCorporateGroups/searchCriteria/accountId/"
					+ accountId;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// ******* RetrieveCustomerService ********/
	// 1 retrieveCustomerUsingCommonSearchCustomerId
	RETRIEVE_CUSTOMER_USING_COMMON_SEARCH_CUSTOMER_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String customerid = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrievecustomer/searchCriteria/commonSearch/customerid/"
					+ customerid;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveCustomerUsingCommonSearchAccountCode
	RETRIEVE_CUSTOMER_USING_COMMON_SEARCH_ACCOUNT_CODE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountCode = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrievecustomer/searchCriteria/commonSearch/accountCode/"
					+ accountCode;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 3 retrieveCustomerUsingIndividualIdSearchIdType
	RETRIEVE_CUSTOMER_USING_INDIVIDUAL_ID_SEARCH_IDTYPE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 2);
			String idType = (String) parameters[0];
			String idNumber = (String) parameters[1];
			String url = serviceUrl
					+ "services/retrievecustomer/searchCriteria/individualSearch/idSearch/idType/"
					+ idType + "/idNumber/" + idNumber;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 4 retrieveCustomerUsingIndividualIdSearchIdTypeNationality
	RETRIEVE_CUSTOMER_USING_INDIVIDUAL_ID_SEARCH_IDTYPE_NATIONALITY {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 3);
			String idType = (String) parameters[0];
			String idNumber = (String) parameters[1];
			String nationality = (String) parameters[2];
			String url = serviceUrl
					+ "services/retrievecustomer/searchCriteria/individualSearch/idSearch/idType/"
					+ idType + "/idNumber/" + idNumber + "/nationality/"
					+ nationality;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 5 retrieveCustomerUsingIndividualMsisdnSearchMSISDN
	RETRIEVE_CUSTOMER_USING_INDIVIDUAL_SEARCH_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrievecustomer/searchCriteria/individualSearch/msisdnSearch/msisdn/"
					+ msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 6 retrieveCustomerUsingCorporateSearchCorporateId
	RETRIEVE_CUSTOMER_USING_CORPORATE_SEARCH_CORPORATE_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String corporateId = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrievecustomer/searchCriteria/corporateSearch/corporateId/"
					+ corporateId;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 7 retrieveCustomerUsingCorporateSearchBusinessRegNo
	RETRIEVE_CUSTOMER_USING_CORPORATE_SEARCH_BUSINESS_REGNO {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String businessRegNum = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrievecustomer/searchCriteria/corporateSearch/businessRegNum/"
					+ businessRegNum;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 8 retrieveCustomerUsingCorporateSearchCorporateGroupCode
	RETRIEVE_CUSTOMER_USING_CORPORATE_SEARCH_CORPORATE_GROUP_CODE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String corporateGroupCode = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrievecustomer/searchCriteria/corporateSearch/corporateGroupCode/"
					+ corporateGroupCode;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 9 retrieveCustomerUsingCorporateSearchPicIdNumber
	RETRIEVE_CUSTOMER_USING_CORPORATE_SEARCH_PIC_ID_NUMBER {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String picIdNumber = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrievecustomer/searchCriteria/corporateSearch/picIdNumber/"
					+ picIdNumber;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 10 retrieveCustomerUsingCorporateSearchMemberMSISDN
	RETRIEVE_CUSTOMER_USING_CORPORATE_SEARCH_MEMBER_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String memberMSISDN = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrievecustomer/searchCriteria/corporateSearch/memberMSISDN/"
					+ memberMSISDN;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// **** RetrieveDataUsageService ***/
	// retrieveDataUsageUsingMSISDN
	RETRIEVE_DATA_USAGE_USING_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl + "services/retrieveDataUsage/msisdn/"
					+ msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// RetrieveFamilyGroupMembersService
	// 1 retrieveFamilyGroupMembersUsingMSISDN
	RETRIEVE_FAMILY_GROUP_MEMBERS_USING_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrieveFamilyGroupMembers/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveFamilyGroupMembersUsingGroupID
	RETRIEVE_FAMILY_GROUP_MEMBERS_USING_GROUP_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String groupid = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrieveFamilyGroupMembers/groupid/" + groupid;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// *** RetrieveFamilyGroupsService ***/
	// 1 retrieveByFamilyGroupId
	RETRIEVE_BY_FAMILY_GROUP_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String familyGroupId = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrievefamilygroups/familyGroupId/"
					+ familyGroupId;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveByfamilyGroupName
	RETRIEVE_BY_FAMILY_GROUP_NAME {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String familyGroupName = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrievefamilygroups/familyGroupName/"
					+ familyGroupName;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 3 retrieveByFamilyMemberMSISDN
	RETRIEVE_BY_FAMILY_MEMBER_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String familyMemberMSISDN = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrievefamilygroups/familyMemberMSISDN/"
					+ familyMemberMSISDN;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// ******** RetrieveFnFFeesService *********/
	// 1 retrieveFnFFeesUsingSubscriberId
	RETRIEVE_FNF_FEES_USING_SUBSCRIBER_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 4);
			String subscriberId = (String) parameters[0];
			String operationType = (String) parameters[1];
			String fnFMSISDN = (String) parameters[2];
			String fnFGroupId = (String) parameters[3];

			String url = serviceUrl + "services/retrieveFnFFees/subscriberId/"
					+ subscriberId + "/operationType/" + operationType
					+ "/fnFMSISDN/" + fnFMSISDN + "/fnFGroupId/" + fnFGroupId;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveFnFFeesUsingSubscriberId
	RETRIEVE_FNF_FEES_USING_SUBSCRIBER_ID_FNF_CATEGORY {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 5);

			String subscriberId = (String) parameters[0];
			String operationType = (String) parameters[1];
			String fnFMSISDN = (String) parameters[2];
			String fnFGroupId = (String) parameters[3];
			String fnFCategory = (String) parameters[4];

			String url = serviceUrl + "services/retrieveFnFFees/subscriberId/"
					+ subscriberId + "/operationType/" + operationType
					+ "/fnFMSISDN/" + fnFMSISDN + "/fnFGroupId/" + fnFGroupId
					+ "/fnFCategory/" + fnFCategory;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// **** RetrieveFnFNumberService ***/
	// 1 retrieveFnFNumberUsingSubscriberId
	RETRIEVE_FNF_NUMBER_USING_SUBSCRIBER_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 1);
			String subscriberId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveFnFNumber/subscriberId/" + subscriberId;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveFnFNumberUsingSubscriberIdFnFMSISDN
	RETRIEVE_FNF_NUMBER_USING_SUBSCRIBER_ID_FNF_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 2);
			String subscriberId = (String) parameters[0];
			String fnFMSISDN = (String) parameters[1];
			String url = serviceUrl
					+ "services/retrieveFnFNumber/subscriberId/" + subscriberId
					+ "/fnFMSISDN/" + fnFMSISDN;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// *** RetrieveInvoicePaymentService **/
	// 1 retrieveInvoicePaymentUsingInvoiceId
	RETRIEVE_INVOICE_PAYMENT_USING_INVOICE_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String invoiceId = (String) parameters[0];

			String url = serviceUrl
					+ "services/retrieveInvoicePayment/invoiceId/" + invoiceId;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveInvoicePaymentUsingAccountDetailsAccountCode
	RETRIEVE_INVOICE_PAYMENT_USING_ACCOUNT_DETAILS_ACCOUNT_CODE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 3);
			String accountCode = (String) parameters[0];
			String startDate = (String) parameters[1];
			String endDate = (String) parameters[2];

			String url = serviceUrl
					+ "services/retrieveInvoicePayment/accountDetails/accountCode/"
					+ accountCode + "/startDate/" + startDate + "/endDate/"
					+ endDate;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 3 retrieveInvoicePaymentUsingAccountDetailsAccountId
	RETRIEVE_INVOICE_PAYMENT_USING_ACCOUNT_DETAILS_ACCOUNT_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 3);
			String accountId = (String) parameters[0];
			String startDate = (String) parameters[1];
			String endDate = (String) parameters[2];

			String url = serviceUrl
					+ "services/retrieveInvoicePayment/accountDetails/accountId/"
					+ accountId + "/startDate/" + startDate + "/endDate/"
					+ endDate;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// **** RetrieveInvoiceService ***/
	// 1 retrieveInvoiceUsingSearchDetailsMSISDN
	RETRIEVE_INVOICE_USING_SEARCH_DETAILS_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveInvoice/searchDetails/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveInvoiceUsingSearchDetailsAccountCode
	RETRIEVE_INVOICE_USING_SEARCH_DETAILS_ACCOUNT_CODE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountCode = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveInvoice/searchDetails/accountCode/"
					+ accountCode;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 3 retrieveInvoiceUsingSearchDetailsAccountId
	RETRIEVE_INVOICE_USING_SEARCH_DETAILS_ACCOUNT_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveInvoice/searchDetails/accountId/"
					+ accountId;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// **** RetrieveQuotasAndServicesService ***//
	// 1 retrieveQuotasAndServicesUsingMSISDN
	RETRIEVE_QUOTAS_AND_SERVICES_USING_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 2);
			String infoType = (String) parameters[0];
			String msisdn = (String) parameters[1];
			String url = serviceUrl
					+ "services/retrieveQuotasAndServices/infoType/" + infoType
					+ "/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveQuotasAndServicesUsingInfoType
	RETRIEVE_QUOTAS_AND_SERVICES_USING_INFOTYPE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String infoType = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveQuotasAndServices/infoType/" + infoType;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 3 retrieveQuotasAndServicesServiceName
	RETRIEVE_QUOTAS_AND_SERVICES_SERVICE_NAME {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String serviceName = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveQuotasAndServices/serviceName/"
					+ serviceName;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 4 retrieveQuotasAndServicesUsingQuotaName
	RETRIEVE_QUOTAS_AND_SERVICES_USING_QUOTANAME {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String quotaName = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrieveQuotasAndServices/quotaName/"
					+ quotaName;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// *** RetrieveReloadHistoryService ****/
	// 1 retrieveReloadHistoryUsingSubscriberId
	RETRIEVE_RELOAD_HISTORY_USING_SUBSCRIBER_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 3);
			String subscriberid = (String) parameters[0];
			String startdate = (String) parameters[1];
			String enddate = (String) parameters[2];
			String url = serviceUrl + "services/reloadhistory/subscriberid/"
					+ subscriberid + "/startdate/" + startdate + "/enddate/"
					+ enddate;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveReloadHistoryUsingMSISDN
	RETRIEVE_RELOAD_HISTORY_USING_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 3);
			String msisdn = (String) parameters[0];
			String startdate = (String) parameters[1];
			String enddate = (String) parameters[2];
			String url = serviceUrl + "services/reloadhistory/msisdn/" + msisdn
					+ "/startdate/" + startdate + "/enddate/" + enddate;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// *** RetrieveSimService **/
	// 1 retrieveSIMByMsisdn
	RETRIEVE_SIM_BY_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl + "services/retrieveSim/msisdn/" + msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 retrieveSIMByIccid
	RETRIEVE_SIM_BY_ICCID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String iccid = (String) parameters[0];
			String url = serviceUrl + "services/iccid/" + iccid;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	
	
	// *** RetrieveSubscriberService **/
	// 1 retrieveSubscriberMSISDN
	
	RETRIEVE_SUBSCRIBER_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 2);
			String msisdn = (String) parameters[0];
			String url = serviceUrl + "services/retrievesubscriber/msisdn/"
					+ msisdn+"/returnFlag/"+parameters[1];

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	
	// *** RetrieveSubscriberService **/
		// 1 retrieveSubscriberMSISDN http://172.29.38.28:5555/digionline/services/accountOverviewService/msisdn/0000/email/0000
	accountOverviewService {
			@Override
			public String execute(Object[] inparameters,
					DefaultHttpClient httpclient, String serviceUrl,
					String[] headersForServices) throws ClientProtocolException,
					IOException {
				Object[] parameters = validation(inparameters, 2);
				String msisdn = (String) parameters[0];
				String email = (String) parameters[1];
				String url = serviceUrl + "services/accountOverviewService/msisdn/"
						+ msisdn+"/email/"+email;

				OnlineServicesImpl services = new OnlineServicesImpl();
				return services.serviceMethod(url, httpclient, headersForServices);

			}
		},

	// 2 retrieveSubscriberByAccountId
	RETRIEVE_SUBSCRIBER_BY_ACCOUNT_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountId = (String) parameters[0];
			String url = serviceUrl + "services/retrievesubscriber/accountId/"
					+ accountId;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 3 retrieveSubscriberByAccountCode
	RETRIEVE_SUBSCRIBER_BY_ACCOUNT_CODE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String accountCode = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrievesubscriber/accountCode/" + accountCode;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 4 retrieveSubscriberBySubscriberId
	RETRIEVE_SUBSCRIBER_BY_SUBSCRIBER_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String subscriberId = (String) parameters[0];
			String url = serviceUrl
					+ "services/retrievesubscriber/subscriberId/"
					+ subscriberId;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 5 retrieveSubscriberBySubscriberIdWithReturnFlag
	RETRIEVE_SUBSCRIBER_BY_SUBSCRIBER_ID_WITH_RETURN_FLAG {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 2);
			String subscriberId = (String) parameters[0];
			String returnFlag = (String) parameters[1];
			String url = serviceUrl
					+ "services/retrievesubscriber/subscriberId/"
					+ subscriberId + "/returnFlag/" + returnFlag;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 6 retrieveSubscriberByIMSI
	RETRIEVE_SUBSCRIBER_BY_IMSI {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String imsi = (String) parameters[0];
			String url = serviceUrl + "services/retrievesubscriber/imsi/"
					+ imsi;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 7 retrieveSubscriberByICCID
	RETRIEVE_SUBSCRIBER_BY_ICCID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String iccid = (String) parameters[0];
			String url = serviceUrl + "services/retrievesubscriber/iccid/"
					+ iccid;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 8 retrieveSubscriberByMSISDnWithOffersFilterFlag
	RETRIEVE_SUBSCRIBER_BY_MSISDN_WITH_OFFERSFILTERFLAG {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 2);
			String msisdn = (String) parameters[0];
			String offersFilterFlag = (String) parameters[1];
			String url = serviceUrl + "services/retrievesubscriber/msisdn/"
					+ msisdn + "/offersFilterFlag/" + offersFilterFlag;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 9 retrieveSubscriberByMSISDnWithReturnFlag
	RETRIEVE_SUBSCRIBER_BY_MSISDN_WITH_RETURN_FLAG {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 2);
			String msisdn = (String) parameters[0];
			String returnFlag = (String) parameters[1];
			String url = serviceUrl + "services/retrievesubscriber/msisdn/"
					+ msisdn + "/returnFlag/" + returnFlag;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 10 retrieveSubscriberByAccountCodeWithReturnFlag
	RETRIEVE_SUBSCRIBER_BY_ACCOUNT_CODE_WITH_RETURN_FLAG {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 2);
			String accountCode = (String) parameters[0];
			String returnFlag = (String) parameters[1];
			String url = serviceUrl
					+ "services/retrievesubscriber/accountCode/" + accountCode
					+ "/returnFlag/" + returnFlag;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// SearchPreOrPostService
	// 1 searchPreOrPost
	SEARCH_PRE_OR_POST_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];

			String url = serviceUrl + "services/searchpreorpost/msisdn/"
					+ msisdn;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},

	// SubRchLogQueryService
	// 1 subRchLogQueryUsingSubscriberNo
	SUBRCH_LOG_QUERY_USING_SUBSCRIBER_NO {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
			Object[] parameters = validation(inparameters, 1);
			String subscriberNo = (String) parameters[0];

			String url = serviceUrl + "services/subRchLogQuery/subscriberNo/"
					+ subscriberNo;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	// 2 subRchLogQueryUsingSubId
	SUBRCH_LOG_QUERY_USING_SUB_ID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 2);
			String subId = (String) parameters[0];
			String startTime = (String) parameters[1];

			String url = serviceUrl + "services/subRchLogQuery/subId/" + subId
					+ "/startTime/" + startTime;

			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},// 2 subRchLogQueryUsingSubId      retrievePlanDataSharing
	USER_PROFILE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];
			String url = serviceUrl + "services/userProfile/msisdn/"+msisdn;
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	//**************************************************************************
	/**       SPRINT 2 **/
	//*****************************************************************************
	
	//planDataSharingService/retrievePlanDataSharing/msisdn
		RETRIEVE_PLAN_DATA_SHARING {
			@Override
			public String execute(Object[] inparameters,
					DefaultHttpClient httpclient, String serviceUrl,
					String[] headersForServices) throws ClientProtocolException,
					IOException {

				Object[] parameters = validation(inparameters, 1);
				String msisdn = (String) parameters[0];
				String url = serviceUrl + "services/planDataSharingService/retrievePlanDataSharing/msisdn/"+msisdn;
				OnlineServicesImpl services = new OnlineServicesImpl();
				return services.serviceMethod(url, httpclient, headersForServices);

			}
		},
	
	
	
	//updateAccountSettingsAndDetails/accountId
	
	UPDATE_ACCOUNT_SETTINGS_AND_DETAILS_ACCOUNTID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 1);
			String accountId = (String) parameters[0];
			String url = serviceUrl + "services/updateAccountSettingsAndDetails/accountId/"+accountId.trim();
			System.out.println(url);
			CacheCleanRequiredHeader_OnlineServiceImpl services = new CacheCleanRequiredHeader_OnlineServiceImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	//updateAccountSettingsAndDetails/accountCode
	UPDATE_ACCOUNT_SETTINGS_AND_DETAILS_ACCOUNTCODE {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 1);
			String accountCode = (String) parameters[0];
			String url = serviceUrl + "services/updateAccountSettingsAndDetails/accountId/"+accountCode.trim();
			System.out.println(url);
			CacheCleanRequiredHeader_OnlineServiceImpl services = new CacheCleanRequiredHeader_OnlineServiceImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	//19-08-2015
	//linkaccounts/checkNumber/msisdn
	LINKACCOUNTS_CHECKNUMBER_MSISDN {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
	
			Object[] parameters = validation(inparameters, 1);
			String msisdn = (String) parameters[0];
			String url = serviceUrl + "services/linkaccounts/checkNumber/msisdn/"+msisdn.trim();
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	
//	updateAccountSettingsAndDetails/updatedEmail/accountId
	//*******************
	//linkaccounts/accountInfo 17-08-2015
	LINK_ACCOUNTS_ACCOUNT_INFO {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {
	
			/*Object[] parameters = validation(inparameters, 1);
			String accountId = (String) parameters[0];*/
			String url = serviceUrl + "services/linkaccounts/accountInfo/";
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	},
	UPDATE_ACCOUNT_SETTINGS_AND_DETAILS_UPDATED_EMAIL_ACCOUNTID {
		@Override
		public String execute(Object[] inparameters,
				DefaultHttpClient httpclient, String serviceUrl,
				String[] headersForServices) throws ClientProtocolException,
				IOException {

			Object[] parameters = validation(inparameters, 1);
			String accountId = (String) parameters[0];
			String url = serviceUrl + "services/updateAccountSettingsAndDetails/updatedEmail/accountId/"+accountId;
			CacheCleanRequiredHeader_OnlineServiceImpl services = new CacheCleanRequiredHeader_OnlineServiceImpl();
			return services.serviceMethod(url, httpclient, headersForServices);

		}
	};

	public abstract String execute(Object[] parameters,
			DefaultHttpClient httpclient, String serviceUrl,
			String[] headersForServices) throws ClientProtocolException,
			IOException;

	{
	}

		public Object[] validation(Object parameters[], int numberOfParameters) {
		//final Logger LOGGER = LoggerFactory.getLogger(OnlineServicesAPI.class);
		final  Logger log = Logger.getLogger(OnlineServices.class);
		try {

			int count = 0;
			if (parameters == null || parameters.length == 0) {

				parameters = new Object[numberOfParameters];
				for (int i = 0; i <= numberOfParameters - 1; i++) {
					parameters[i] = "0000";
				}
			} else if (numberOfParameters == parameters.length) {
				for (Object s1 : parameters) {
					if (s1.toString().trim().equals(""))
						parameters[count] = "0000";
					count++;
				}
			} else if (numberOfParameters != parameters.length) {

				int paramlenth = parameters.length;

				Object[] newParams = new Object[numberOfParameters];

				for (int y = 0; y <= numberOfParameters - 1; y++) {

					if (y <= parameters.length-1){
						if(parameters[y].toString().trim().equals("")){
							newParams[y] =  "0000";
						}else{
							newParams[y] = parameters[y];
						}
					}
					else{
						newParams[y] = "0000";
					}
				}

				return newParams;
			}

			return parameters;
		} catch (Exception e) {
			log.error("OnlineServicesAPI... exception" + e);
		}
		return parameters;

	}
}
