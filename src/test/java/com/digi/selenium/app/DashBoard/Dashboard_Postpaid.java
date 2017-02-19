package com.digi.selenium.app.DashBoard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.digi.selenium.util.common.AppConstants;

import com.digi.selenium.util.DashBoard.Dashboard_Postpaid_Util;
import com.digi.selenium.util.common.PageNavigation;



public class Dashboard_Postpaid extends Dashboard_Postpaid_Util{
	
	//logger 
	private  Logger log = Logger.getLogger(Dashboard_Postpaid.class);
	
	@BeforeClass
	public void initDriver() throws Exception {
		setUp();
	}
	
	//added by pramod
	
 @Test(priority=1,enabled=true)
  public void verifyVoiceGraphData() throws InterruptedException {
	 try
		{
		 targetVoiceToolTipData();
		 getTargetVoicemapDate().keySet();
		 for (String key : getTargetVoicemapDate().keySet()) {		 
			 String keyValue= getTargetVoicemapDate().get(key);
			 System.out.println(keyValue);
			 
			 System.out.println("Result:"+getTargetVoicemapDate().get(key).toString().trim()+"=="+getSourceData(key).trim());
			 Assert.assertEquals(getTargetVoicemapDate().get(key).toString().trim(), getSourceData(key).trim());
			 
			 
		 System.out.println("Result:"+getTargetVoiceGraph().get(keyValue).get(AppConstants.DOMESTIC).toString().trim()+"=="+getSourceData(getDOMESTICCALL_KEY(keyValue)).trim());
		 Assert.assertEquals(getTargetVoiceGraph().get(keyValue).get(AppConstants.DOMESTIC).toString().trim(), getSourceData(getDOMESTICCALL_KEY(keyValue)).trim());
		 
		 Assert.assertEquals(getTargetVoiceGraph().get(keyValue).get(AppConstants.INTERNATIONAL_ROAMING).toString().trim(), getSourceData(getINTROAMING_KEY(keyValue)).trim());
		 System.out.println("Result:"+getTargetVoiceGraph().get(keyValue).get(AppConstants.INTERNATIONAL_ROAMING).toString().trim()+"=="+getSourceData(getINTROAMING_KEY(keyValue)).trim());
		 
		 Assert.assertEquals(getTargetVoiceGraph().get(keyValue).get(AppConstants.IDD_CALLS).toString().trim(), getSourceData(getIDDCALLS_KEY(keyValue)).trim());
		 System.out.println("Result:"+getTargetVoiceGraph().get(keyValue).get(AppConstants.IDD_CALLS).toString().trim()+"=="+getSourceData(getIDDCALLS_KEY(keyValue)).trim());
		 
		 Assert.assertEquals(getTargetVoiceGraph().get(keyValue).get(AppConstants.FRIEND_FAMILY).toString().trim(), getSourceData(getFRIENDNFAMILY(keyValue)).trim());
		 System.out.println("Result:"+getTargetVoiceGraph().get(keyValue).get(AppConstants.FRIEND_FAMILY).toString().trim()+"=="+getSourceData(getFRIENDNFAMILY(keyValue)).trim());
		 
		 Assert.assertEquals(getTargetVoiceGraph().get(keyValue).get(AppConstants.OTHER).toString().trim(), getSourceData(getOTHER(keyValue)).trim());
		 System.out.println("Result:"+getTargetVoiceGraph().get(keyValue).get(AppConstants.OTHER).toString().trim()+"=="+getSourceData(getOTHER(keyValue)).trim());
		}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	} 
 
 @Test(priority=1,enabled=true)
 public void verifyVoiceDonutValues() throws InterruptedException {
	 try
		{
		 targetInternetTooltip();
		 voiceDonutValues();
		 Assert.assertEquals(getTargetVoiceDonut().get(AppConstants.DOMESTIC), getSourceData(AppConstants.VOICE_DOMESTIC_CALL));
		 Assert.assertEquals(getTargetVoiceDonut().get(AppConstants.FRIEND_FAMILY), getSourceData(AppConstants.VOICE_FRIEND_FAMILY));
		 Assert.assertEquals(getTargetVoiceDonut().get(AppConstants.INTERNATIONAL_ROAMING), getSourceData(AppConstants.VOICE_INTERNATIONAL_ROAMING));
		 Assert.assertEquals(getTargetVoiceDonut().get(AppConstants.IDD_CALLS), getSourceData(AppConstants.VOICE_IDD_CALLS));
		 Assert.assertEquals(getTargetVoiceDonut().get(AppConstants.OTHER), getSourceData(AppConstants.VOICE_OTHER_CALLS));
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	}

 @Test(priority=1,enabled=true)
 public void verifyInternetDonutValues() throws InterruptedException {
	 try
		{

		 internetDonutValues();
		 Assert.assertEquals(getTargetInternetDonut().get(AppConstants.DOMESTIC), getSourceData(AppConstants.DONUT_INTERNET_DOMESTIC));
		 Assert.assertEquals(getTargetInternetDonut().get(AppConstants.INTERNATIONAL_ROAMING), getSourceData(AppConstants.DONUT_INTERNET_INTERNATIONAL_ROAMING));

		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	}

 @Test(priority=1,enabled=true)
 public void verifyInternetGraphValues() throws InterruptedException {
	 try
		{

		 targetToolTipData();
		 for (String key : getTargetMapInternetDate().keySet()) {		 
			 String keyValue= getTargetMapInternetDate().get(key);
			 System.out.println(keyValue);
			
			 
			 System.out.println("Result:"+getTargetMapInternetDate().get(key).toString().trim()+"=="+getSourceData(key).trim());
			 Assert.assertEquals(getTargetMapInternetDate().get(key).toString().trim(), getSourceData(key).trim());
			 
		System.out.println("Result:"+getTargetMapInternet().get(keyValue).get(AppConstants.DOMESTIC).toString().trim()+"=="+getSourceData(getInternetDomesticUsage(keyValue)).trim());
		Assert.assertEquals(getTargetMapInternet().get(keyValue).get(AppConstants.DOMESTIC).toString().trim(), getSourceData(getInternetDomesticUsage(keyValue)).trim());
		 
		 System.out.println("Result:"+getTargetMapInternet().get(keyValue).get(AppConstants.INTERNATIONAL_ROAMING).toString().trim()+"=="+getSourceData(getInternetInternationalRoaming(keyValue)).trim());
			Assert.assertEquals(getTargetMapInternet().get(keyValue).get(AppConstants.INTERNATIONAL_ROAMING).toString().trim(), getSourceData(getInternetInternationalRoaming(keyValue)).trim());
		 
		
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	}
 
 
 
 @Test(priority=1,enabled=true)
 public void verifySMSGraphData() throws InterruptedException {
	 try
		{

		 targetSMSToolTipData();
		 for (String key : getTargetSms_mapDate().keySet()) {		 
			 String keyValue= getTargetSms_mapDate().get(key);
			 System.out.println(keyValue);
			 
			 
			 System.out.println("Result:"+getTargetSms_mapDate().get(key).toString().trim()+"=="+getSourceData(key).trim());
			 Assert.assertEquals(getTargetSms_mapDate().get(key).toString().trim(), getSourceData(key).trim());
			 
		System.out.println("Result:"+getTargetDashboard_sms().get(keyValue).get(AppConstants.DOMESTIC).toString().trim()+"=="+getSourceData(getSMSDomestic(keyValue)).trim());
		Assert.assertEquals(getTargetDashboard_sms().get(keyValue).get(AppConstants.DOMESTIC).toString().trim(), getSourceData(getSMSDomestic(keyValue)).trim());
		
		System.out.println("Result:"+getTargetDashboard_sms().get(keyValue).get(AppConstants.INTERNATIONAL_ROAMING).toString().trim()+"=="+getSourceData(getSMSInternationalRoaming(keyValue)).trim());
		Assert.assertEquals(getTargetDashboard_sms().get(keyValue).get(AppConstants.INTERNATIONAL_ROAMING).toString().trim(), getSourceData(getSMSInternationalRoaming(keyValue)).trim());
		
		System.out.println("Result:"+getTargetDashboard_sms().get(keyValue).get(AppConstants.FRIEND_FAMILY).toString().trim()+"=="+getSourceData(getSMSFriendNFamily(keyValue)).trim());
		Assert.assertEquals(getTargetDashboard_sms().get(keyValue).get(AppConstants.FRIEND_FAMILY).toString().trim(), getSourceData(getSMSFriendNFamily(keyValue)).trim());

		System.out.println("Result:"+getTargetDashboard_sms().get(keyValue).get(AppConstants.INTERNATIONAL_SMS).toString().trim()+"=="+getSourceData(getSMSInternational(keyValue)).trim());
		Assert.assertEquals(getTargetDashboard_sms().get(keyValue).get(AppConstants.INTERNATIONAL_SMS).toString().trim(), getSourceData(getSMSInternational(keyValue)).trim());
		
		System.out.println("Result:"+getTargetDashboard_sms().get(keyValue).get(AppConstants.OTHER).toString().trim()+"=="+getSourceData(getSMSOTHER(keyValue)).trim());
		Assert.assertEquals(getTargetDashboard_sms().get(keyValue).get(AppConstants.OTHER).toString().trim(), getSourceData(getSMSOTHER(keyValue)).trim());
		
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	}
 
 @Test(priority=1,enabled=true)
 public void verifySMSDonutValues() throws InterruptedException {
	 try
		{
		 targetInternetTooltip();
		 smsDonutValues();
		 Assert.assertEquals(getTargetSMSDonut().get(AppConstants.DOMESTIC), getSourceData(AppConstants.SMS_DOMESTIC));
		 Assert.assertEquals(getTargetSMSDonut().get(AppConstants.FRIEND_FAMILY), getSourceData(AppConstants.SMS_FRIEND_FAMILY));
		 Assert.assertEquals(getTargetSMSDonut().get(AppConstants.INTERNATIONAL_ROAMING), getSourceData(AppConstants.SMS_INTERNATIONAL_ROAMING));
		 Assert.assertEquals(getTargetSMSDonut().get(AppConstants.INTERNATIONAL_SMS), getSourceData(AppConstants.SMS_INTERNATIONAL));
		 Assert.assertEquals(getTargetSMSDonut().get(AppConstants.OTHER), getSourceData(AppConstants.SMS_OTHER));
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	}
	@AfterClass
	public void terminateBrowser(){
		log.info("ShutDown : Digi_Login_Page");
		shutDown();
	}
	@AfterMethod(alwaysRun=true)
	public void afterMethod(ITestResult result) throws Exception {
		if (!result.isSuccess()) 
			takeScreenShoot(getDriver(), result.getMethod());
		

	}
	
}
