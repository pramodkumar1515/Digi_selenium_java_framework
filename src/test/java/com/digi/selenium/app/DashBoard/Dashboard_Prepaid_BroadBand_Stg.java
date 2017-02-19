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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.digi.selenium.util.common.AppConstants;

import com.digi.selenium.util.DashBoard.Dashboard_Prepaid_BroadBand_Util_Stg;

import com.digi.selenium.util.common.PageNavigation;


public class Dashboard_Prepaid_BroadBand_Stg extends Dashboard_Prepaid_BroadBand_Util_Stg{
	
	//logger 
	private  Logger log = Logger.getLogger(Dashboard_Prepaid_BroadBand_Stg.class);
	
	@BeforeClass
	public void initDriver() throws Exception {
		setUp();
	}
	
	//added by pramod
	
 @Test(priority=1,enabled=false)
  public void verifyVoiceGraphData_Prepaid_BroadBand() throws InterruptedException {
	 try
		{
		 //to read the the tooltip data under voice tab
		 targetVoiceToolTipData();
		 //verifying the y axis
		 //validate_YAxis(AppConstants.VOICE_Y_AXIS_KEY);
		 
		 validate_YAxis(); 
	       for(int i=0; i<getTargetListyaxischild().size();i++){
	    	   String yaxiskey=getYAxis(Integer.toString(i+1),AppConstants.VOICE_Y_AXIS_KEY);
	    	   String yaxisdata=getSourceData(yaxiskey);
	       System.out.println("TargetYAxisData-->"+getTargetListyaxischild().get(i).getText());
	       System.out.println("SourceData-->"+yaxisdata);
	       Assert.assertEquals(getTargetListyaxischild().get(i).getText(), yaxisdata);	
	}	
		 
         // Validate the tool tip data
		 for (String key : getTargetVoicemapDate().keySet()) {		 
			 String keyValue= getTargetVoicemapDate().get(key);
			 //System.out.println(keyValue);
			log.info(keyValue);
		
		 // validating the  voice billing  Date
		 System.out.println("Result:"+getTargetVoicemapDate().get(key).toString().trim()+"=="+getSourceData(key).trim());
		 Assert.assertEquals(getTargetVoicemapDate().get(key).toString().trim(), getSourceData(key).trim());
		
		// validating the voice  Domestic calls 
		 System.out.println("Result:"+getTargetVoiceGraph().get(keyValue).get(AppConstants.DOMESTIC).toString().trim()+"=="+getSourceData(getDOMESTICCALL_KEY(keyValue)).trim());
		 Assert.assertEquals(getTargetVoiceGraph().get(keyValue).get(AppConstants.DOMESTIC).toString().trim(), getSourceData(getDOMESTICCALL_KEY(keyValue)).trim());
		 
		// validating the  voice International Roaming
		 Assert.assertEquals(getTargetVoiceGraph().get(keyValue).get(AppConstants.INTERNATIONAL_ROAMING).toString().trim(), getSourceData(getINTROAMING_KEY(keyValue)).trim());
		 System.out.println("Result:"+getTargetVoiceGraph().get(keyValue).get(AppConstants.INTERNATIONAL_ROAMING).toString().trim()+"=="+getSourceData(getINTROAMING_KEY(keyValue)).trim());
		 
		// validating the  voice IDD Calls
		 Assert.assertEquals(getTargetVoiceGraph().get(keyValue).get(AppConstants.IDD_CALLS).toString().trim(), getSourceData(getIDDCALLS_KEY(keyValue)).trim());
		 System.out.println("Result:"+getTargetVoiceGraph().get(keyValue).get(AppConstants.IDD_CALLS).toString().trim()+"=="+getSourceData(getIDDCALLS_KEY(keyValue)).trim());
		 
		// validating the  voice Friend & Family
		 Assert.assertEquals(getTargetVoiceGraph().get(keyValue).get(AppConstants.FRIEND_FAMILY).toString().trim(), getSourceData(getFRIENDNFAMILY(keyValue)).trim());
		 System.out.println("Result:"+getTargetVoiceGraph().get(keyValue).get(AppConstants.FRIEND_FAMILY).toString().trim()+"=="+getSourceData(getFRIENDNFAMILY(keyValue)).trim());
		 
		// validating the  voice Other Calls
		// Assert.assertEquals(getTargetVoiceGraph().get(keyValue).get(AppConstants.OTHER).toString().trim(), getSourceData(getOTHER(keyValue)).trim());
		 //System.out.println("Result:"+getTargetVoiceGraph().get(keyValue).get(AppConstants.OTHER).toString().trim()+"=="+getSourceData(getOTHER(keyValue)).trim());
		}
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	} 
 
 @Test(priority=3,enabled=false)
 public void verifyVoiceDonutValues_Prepaid_BroadBand() throws InterruptedException {
	 try
		{
		 //getting the Target Voice Donut Values
		 voiceDonutValues();

		 // validating the  value of  Donut , voice Domestic calls
		 Assert.assertEquals(getTargetVoiceDonut().get(AppConstants.DOMESTIC), getSourceData(AppConstants.VOICE_DOMESTIC_CALL));
		 // validating the  value of  Donut , voice Friend & Family calls
		 Assert.assertEquals(getTargetVoiceDonut().get(AppConstants.FRIEND_FAMILY), getSourceData(AppConstants.VOICE_FRIEND_FAMILY));
		 // validating the  value of  Donut , voice International Roaming calls
		 Assert.assertEquals(getTargetVoiceDonut().get(AppConstants.INTERNATIONAL_ROAMING), getSourceData(AppConstants.VOICE_INTERNATIONAL_ROAMING));
		 // validating the  value of  Donut , voice IDD calls
		 Assert.assertEquals(getTargetVoiceDonut().get(AppConstants.IDD_CALLS), getSourceData(AppConstants.VOICE_IDD_CALLS));
		 // validating the  value of  Donut , voice Other calls
		 //Assert.assertEquals(getTargetVoiceDonut().get(AppConstants.OTHER), getSourceData(AppConstants.VOICE_OTHER_CALLS));
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	}

 @Test(priority=2,enabled=false)
 public void verifyInternetDonutValues_Prepaid_BroadBand() throws InterruptedException {
	 try
		{
		 //getting the Target Internet Donut Values
		 internetDonutValues();
		 // validating the  value of  Donut , Internet Domestic Data
		 Assert.assertEquals(getTargetInternetDonut().get(AppConstants.DOMESTIC), getSourceData(AppConstants.DONUT_INTERNET_DOMESTIC));
	     // validating the  value of  Donut , Internet International Roaming Data
		 Assert.assertEquals(getTargetInternetDonut().get(AppConstants.INTERNATIONAL_ROAMING), getSourceData(AppConstants.DONUT_INTERNET_INTERNATIONAL_ROAMING));

		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	}

 @Test(priority=2,enabled=false)
 public void verifyInternetGraphValues_Prepaid_BroadBand() throws InterruptedException {
	 try
		{
		//to read the the tooltip data under Internet tab
		 targetInternetToolTipData();
		 
		 //verifying the y axis
		 //validate_YAxis(AppConstants.INTERNET_Y_AXIS_KEY);
		 
		 validate_YAxis(); 
	       for(int i=0; i<getTargetListyaxischild().size();i++){
	    	   String yaxiskey=getYAxis(Integer.toString(i+1),AppConstants.INTERNET_Y_AXIS_KEY);
	    	   String yaxisdata=getSourceData(yaxiskey);
	       System.out.println("TargetYAxisData-->"+getTargetListyaxischild().get(i).getText());
	       System.out.println("SourceData-->"+yaxisdata);
	       Assert.assertEquals(getTargetListyaxischild().get(i).getText(), yaxisdata);	
	}
		 
		 for (String key : getTargetMapInternetDate().keySet()) {		 
			 String keyValue= getTargetMapInternetDate().get(key);
			 //System.out.println(keyValue);
			 
			 // validating the  Internet  Billing Dates
			 System.out.println("Result:"+getTargetMapInternetDate().get(key).toString().trim()+"=="+getSourceData(key).trim());
			 Assert.assertEquals(getTargetMapInternetDate().get(key).toString().trim(), getSourceData(key).trim());
			 
			 // validating the  Internet  Domestic  Internet Data
			 System.out.println("Result:"+getTargetMapInternet().get(keyValue).get(AppConstants.DOMESTIC).toString().trim()+"=="+getSourceData(getInternetDomesticUsage(keyValue)).trim());
			 Assert.assertEquals(getTargetMapInternet().get(keyValue).get(AppConstants.DOMESTIC).toString().trim(), getSourceData(getInternetDomesticUsage(keyValue)).trim());
		 
			// validating the  Internet  Domestic  Internet  International Roaming Data
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
 
 
 
 @Test(priority=1,enabled=false)
 public void verifySMSGraphData_Prepaid_BroadBand() throws InterruptedException {
	 try
		{
		 
	 
		//to read the the tooltip data under SMS tab
		 targetSMSToolTipData();
	
		 //verifying the y axis
		 //validate_YAxis(AppConstants.SMS_Y_AXIS_KEY);
		 
		 validate_YAxis(); 
	       for(int i=0; i<getTargetListyaxischild().size();i++){
	    	   String yaxiskey=getYAxis(Integer.toString(i+1),AppConstants.SMS_Y_AXIS_KEY);
	    	   String yaxisdata=getSourceData(yaxiskey);
	       System.out.println("TargetYAxisData-->"+getTargetListyaxischild().get(i).getText());
	       System.out.println("SourceData-->"+yaxisdata);
	       Assert.assertEquals(getTargetListyaxischild().get(i).getText(), yaxisdata);	
	}

		 
		 for (String key : getTargetSms_mapDate().keySet()) {		 
			 String keyValue= getTargetSms_mapDate().get(key);
			 System.out.println(keyValue);
			 //Log.info(keyValue);
			 
			// validating the  SMS  Billing Dates
			 System.out.println("Result:"+getTargetSms_mapDate().get(key).toString().trim()+"=="+getSourceData(key).trim());
			 Assert.assertEquals(getTargetSms_mapDate().get(key).toString().trim(), getSourceData(key).trim());
			 
			// validating the  SMS  Domestic SMS
			 System.out.println("Result:"+getTargetDashboard_sms().get(keyValue).get(AppConstants.DOMESTIC).toString().trim()+"=="+getSourceData(getSMSDomestic(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_sms().get(keyValue).get(AppConstants.DOMESTIC).toString().trim(), getSourceData(getSMSDomestic(keyValue)).trim());
		
			// validating the  SMS  International Roaming SMS
			 System.out.println("Result:"+getTargetDashboard_sms().get(keyValue).get(AppConstants.INTERNATIONAL_ROAMING).toString().trim()+"=="+getSourceData(getSMSInternationalRoaming(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_sms().get(keyValue).get(AppConstants.INTERNATIONAL_ROAMING).toString().trim(), getSourceData(getSMSInternationalRoaming(keyValue)).trim());
		
			// validating the  SMS  Friend & Family SMS
			 System.out.println("Result:"+getTargetDashboard_sms().get(keyValue).get(AppConstants.FRIEND_FAMILY).toString().trim()+"=="+getSourceData(getSMSFriendNFamily(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_sms().get(keyValue).get(AppConstants.FRIEND_FAMILY).toString().trim(), getSourceData(getSMSFriendNFamily(keyValue)).trim());

		     // validating the  SMS  International SMS
			 System.out.println("Result:"+getTargetDashboard_sms().get(keyValue).get(AppConstants.INTERNATIONAL_SMS).toString().trim()+"=="+getSourceData(getSMSInternational(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_sms().get(keyValue).get(AppConstants.INTERNATIONAL_SMS).toString().trim(), getSourceData(getSMSInternational(keyValue)).trim());
	
		     // validating the  SMS  Other SMS
			 System.out.println("Result:"+getTargetDashboard_sms().get(keyValue).get(AppConstants.DOMESTIC_MMS).toString().trim()+"=="+getSourceData(getSMSDomesticMMS(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_sms().get(keyValue).get(AppConstants.DOMESTIC_MMS).toString().trim(), getSourceData(getSMSDomesticMMS(keyValue)).trim());
			 
		     // validating the  SMS  Other SMS
			 System.out.println("Result:"+getTargetDashboard_sms().get(keyValue).get(AppConstants.INTERNATIONAL_ROAMING_MMS).toString().trim()+"=="+getSourceData(getSMSInternationalRoamingMMS(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_sms().get(keyValue).get(AppConstants.INTERNATIONAL_ROAMING_MMS).toString().trim(), getSourceData(getSMSInternationalRoamingMMS(keyValue)).trim());
		
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	}
 
 @Test(priority=4,enabled=false)
 public void verifySMSDonutValues_Prepaid_BroadBand() throws InterruptedException {
	 try
		{
		//getting the Target SMS Donut Values
		 smsDonutValues();
		 // validating the  value of  Donut , Domestic SMS
		 Assert.assertEquals(getTargetSMSDonut().get(AppConstants.DOMESTIC_SMS), getSourceData(AppConstants.SMS_DOMESTIC));
		 // validating the  value of  Donut , Friend & Family SMS
		 Assert.assertEquals(getTargetSMSDonut().get(AppConstants.FRIEND_FAMILY_SMS), getSourceData(AppConstants.SMS_FRIEND_FAMILY));
		 // validating the  value of  Donut , International Roaming SMS
		 Assert.assertEquals(getTargetSMSDonut().get(AppConstants.INTERNATIONAL_ROAMING_SMS), getSourceData(AppConstants.SMS_INTERNATIONAL_ROAMING));
		 // validating the  value of  Donut , International  SMS
		 Assert.assertEquals(getTargetSMSDonut().get(AppConstants.INTERNATIONAL_SMS), getSourceData(AppConstants.SMS_INTERNATIONAL));
		// validating the  value of  Donut , Other SMS
		 Assert.assertEquals(getTargetSMSDonut().get(AppConstants.SMS_MMS_DOMESTIC_MMS), getSourceData(AppConstants.SMS_DOMESTIC_MMS));
		 
			// validating the  value of  Donut , Other SMS
		 Assert.assertEquals(getTargetSMSDonut().get(AppConstants.SMS_MMS_INTERNATIONAL_ROAMING_MMS), getSourceData(AppConstants.SMS_INTERNATIONAL_ROAMING_MMS));

		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	}

 @Test(priority=1,enabled=false)
 public void verifyTotalCostDonutValues_Prepaid_BroadBand() throws InterruptedException {
	 try
		{
		//getting the Target SMS Donut Values
		 totalCostDonutValues();
		 // validating the  value of  Donut , 
		 Assert.assertEquals(getTargetTotalcostdonut().get(AppConstants.TOTAL_COST), getSourceData(AppConstants.TOTALCOST_TOTAL_COST));
		 // validating the  value of  Donut , 
		 Assert.assertEquals(getTargetTotalcostdonut().get(AppConstants.INTERNET), getSourceData(AppConstants.TOTALCOST_INTERNET));
		 // validating the  value of  Donut , 
		 Assert.assertEquals(getTargetTotalcostdonut().get(AppConstants.TOTAL_VOICE_CHARGES), getSourceData(AppConstants.TOTALCOST_VOICE_CHARGES));
		 // validating the  value of  Donut , 
		 Assert.assertEquals(getTargetTotalcostdonut().get(AppConstants.TOTAL_SMS_MMS_CHARGES), getSourceData(AppConstants.TOTALCOST_SMS_MMS_CHARGES));
		// validating the  value of  Donut , 
		 Assert.assertEquals(getTargetTotalcostdonut().get(AppConstants.TOTAL_OTHER_SERVICES), getSourceData(AppConstants.TOTALCOST_OTHER_SERVICES));

		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	}
 
 @Test(priority=1,enabled=true)
 public void verifyTotalCostGraphData_Prepaid_BroadBand() throws InterruptedException {
	 try
		{
		 
		 handleLink_ByText("Internet");
		 handleLink_ByText("Total Cost");
		 
		 //verifying the y axis
		// validate_YAxis(AppConstants.TOTALCOST_Y_AXIS_KEY);
		 
		 validate_YAxis(); 
	       for(int i=0; i<getTargetListyaxischild().size();i++){
	    	   String yaxiskey=getYAxis(Integer.toString(i+1),AppConstants.TOTALCOST_Y_AXIS_KEY);
	    	   String yaxisdata=getSourceData(yaxiskey);
	       System.out.println("TargetYAxisData-->"+getTargetListyaxischild().get(i).getText());
	       System.out.println("SourceData-->"+yaxisdata);
	       Assert.assertEquals(getTargetListyaxischild().get(i).getText(), yaxisdata);	
	}
		 
		//to read the the tooltip data under SMS tab
		 targetTotalCostToolTipData();
		 

		 
		 //verifying the tooltip data
		 for (String key : getTargetTotalCost_mapDate().keySet()) {		 
			 String keyValue= getTargetTotalCost_mapDate().get(key);
			 //System.out.println(keyValue);
			 //Log.info(keyValue);
			 System.out.println(keyValue);
			 
			// validating the  total cost  Billing Dates
			 System.out.println("Result:"+getTargetTotalCost_mapDate().get(key).toString().trim()+"=="+getSourceData(key).trim());
			 Assert.assertEquals(getTargetTotalCost_mapDate().get(key).toString().trim(), getSourceData(key).trim());
			 
			// validating the  TOTALCOST   Internet 
			 System.out.println("Result:"+getTargetDashboard_total_Cost().get(keyValue).get(AppConstants.INTERNET).toString().trim()+"=="+getSourceData(getTotalCostInternet(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_total_Cost().get(keyValue).get(AppConstants.INTERNET).toString().trim(), getSourceData(getTotalCostInternet(keyValue)).trim());
		
			// validating the  TOTALCOST   SMS/MMS
			 System.out.println("Result:"+getTargetDashboard_total_Cost().get(keyValue).get(AppConstants.SMS_MMS).toString().trim()+"=="+getSourceData(getTotalCosSMS_MMS(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_total_Cost().get(keyValue).get(AppConstants.SMS_MMS).toString().trim(), getSourceData(getTotalCosSMS_MMS(keyValue)).trim());
		
			// validating the  TOTALCOST  Voice
			 System.out.println("Result:"+getTargetDashboard_total_Cost().get(keyValue).get(AppConstants.VOICE).toString().trim()+"=="+getSourceData(getTotalCostVoice(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_total_Cost().get(keyValue).get(AppConstants.VOICE).toString().trim(), getSourceData(getTotalCostVoice(keyValue)).trim());

	
		     // validating the  TOTALCOST  other
			 System.out.println("Result:"+getTargetDashboard_total_Cost().get(keyValue).get(AppConstants.OTHER).toString().trim()+"=="+getSourceData(getTotalCostOther(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_total_Cost().get(keyValue).get(AppConstants.OTHER).toString().trim(), getSourceData(getTotalCostOther(keyValue)).trim());
		
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	}
 
 
 @Test(priority=1,enabled=false)
 public void verifyOtherGraphData_Prepaid_BroadBand() throws InterruptedException {
	 try
		{
		//to read the the tooltip data under Internet tab
		 targetOtherToolTipData();
		 
		 //verifying the y axis
		 //validate_YAxis(AppConstants.OTHER_Y_AXIS_KEY);
		 
		 validate_YAxis(); 
	       for(int i=0; i<getTargetListyaxischild().size();i++){
	    	   String yaxiskey=getYAxis(Integer.toString(i+1),AppConstants.OTHER_Y_AXIS_KEY);
	    	   String yaxisdata=getSourceData(yaxiskey);
	       System.out.println("TargetYAxisData-->"+getTargetListyaxischild().get(i).getText());
	       System.out.println("SourceData-->"+yaxisdata);
	       Assert.assertEquals(getTargetListyaxischild().get(i).getText(), yaxisdata);	
	}
		 
		 for (String key : getTargetOther_mapDate().keySet()) {		 
			 String keyValue= getTargetOther_mapDate().get(key);
			 System.out.println(keyValue);
			 //Log.info(keyValue);
			 
			 // validating the  other  Billing Dates
			 System.out.println("Result:"+getTargetOther_mapDate().get(key).toString().trim()+"=="+getSourceData(key).trim());
			 Assert.assertEquals(getTargetOther_mapDate().get(key).toString().trim(), getSourceData(key).trim());
			 
			 // validating the  Internet  Domestic  Internet Data
			 System.out.println("Result:"+getTargetDashboard_other().get(keyValue).get(AppConstants.FIXED_CHARGES).toString().trim()+"=="+getSourceData(getOtherFixedCharges(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_other().get(keyValue).get(AppConstants.FIXED_CHARGES).toString().trim(), getSourceData(getOtherFixedCharges(keyValue)).trim());
		 
			// validating the  Internet  Domestic  Internet  International Roaming Data
			 System.out.println("Result:"+getTargetDashboard_other().get(keyValue).get(AppConstants.ONE_TIME_FEE).toString().trim()+"=="+getSourceData(getOtherOneTimeFee(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_other().get(keyValue).get(AppConstants.ONE_TIME_FEE).toString().trim(), getSourceData(getOtherOneTimeFee(keyValue)).trim());	 
		
			 System.out.println("Result:"+getTargetDashboard_other().get(keyValue).get(AppConstants.MOBILE_SERVICES).toString().trim()+"=="+getSourceData(getOtherMobileServices(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_other().get(keyValue).get(AppConstants.MOBILE_SERVICES).toString().trim(), getSourceData(getOtherMobileServices(keyValue)).trim());
			 
			 System.out.println("Result:"+getTargetDashboard_other().get(keyValue).get(AppConstants.OTHERS).toString().trim()+"=="+getSourceData(getOtherOthers(keyValue)).trim());
			 Assert.assertEquals(getTargetDashboard_other().get(keyValue).get(AppConstants.OTHERS).toString().trim(), getSourceData(getOtherOthers(keyValue)).trim());
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error("Fail : ");
			Assert.fail();
		}
	}
 
 @Test(priority=5,enabled=false)
 public void verifyOtherDonutValues_Prepaid_BroadBand() throws InterruptedException {
	 try
		{
		//getting the Target SMS Donut Values
		 otherDonutValues();
		 // validating the  value of  Donut , 
		 Assert.assertEquals(getTargetOtherdonut().get(AppConstants.FIXED_CHARGES), getSourceData(AppConstants.OTHER_FIXED_CHARGES));
		 // validating the  value of  Donut , 
		 Assert.assertEquals(getTargetOtherdonut().get(AppConstants.ONE_TIME_FEE), getSourceData(AppConstants.OTHER_ONE_TIME_FEE));
		 // validating the  value of  Donut , 
		 Assert.assertEquals(getTargetOtherdonut().get(AppConstants.MOBILE_SERVICES), getSourceData(AppConstants.OTHER_MOBILE_SERVICES));
		 // validating the  value of  Donut , 
		 Assert.assertEquals(getTargetOtherdonut().get(AppConstants.OTHERS), getSourceData(AppConstants.OTHER_OTHERS));
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
	
}
