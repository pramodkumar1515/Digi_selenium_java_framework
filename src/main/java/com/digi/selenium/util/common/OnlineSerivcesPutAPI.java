package com.digi.selenium.util.common;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.DefaultHttpClient;

public enum OnlineSerivcesPutAPI {
	UPDATE_BAR_UNBAR_INFO {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				final Map<String, String> mapHeadersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/planSettings/barUnbar/";
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.putServiceMethod(inparameters, puthttpClient,
					mapHeadersForServices, url);

		}
	},
	UPDATE_IR_STATUS {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				final Map<String, String> mapHeadersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/planSettings/updateIR/";
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.putServiceMethod(inparameters, puthttpClient,
					mapHeadersForServices, url);

		}
	},
	UPDATE_CLIR_STATUS {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				final Map<String, String> mapHeadersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl + "services/planSettings/updateCLIR/";
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.putServiceMethod(inparameters, puthttpClient,
					mapHeadersForServices, url);

		}
	},

	// PlanAddOnService
	SUBSCRIBE_ADD_ON {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				final Map<String, String> mapHeadersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl
					+ "services/planAddOnService/subscribeAddOn/";
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.putServiceMethodJSON(inparameters, puthttpClient,
					mapHeadersForServices, url);

		}
	},UN_SUBSCRIBE_ADDON {
		@Override
		public String execute(final Map<String, String> inparameters,
				final DefaultHttpClient puthttpClient, final String serviceUrl,
				final Map<String, String> mapHeadersForServices)
				throws ClientProtocolException, IOException {

			String url = serviceUrl
					+ "services/planAddOnService/unSubscribeAddOn/";
			OnlineServicesImpl services = new OnlineServicesImpl();
			return services.putServiceMethodJSON(inparameters, puthttpClient,
					mapHeadersForServices, url);

		}
	};

	public abstract String execute(Map<String, String> inparameters,
			DefaultHttpClient puthttpClient, String serviceUrl,
			Map<String, String> mapHeadersForServices)
			throws ClientProtocolException, IOException;

	{
	}

}
