package com.digi.selenium.util.DashBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.common.PageNavigation;

public class Dashboard_Postpaid_BroadBand_Util_Stg extends PageNavigation {

private  Logger log = Logger.getLogger(Dashboard_Postpaid_BroadBand_Util_Stg.class);
private Map<String , String> smsdonut = new HashMap<String, String>();
private Map<String , String> internetdonut = new HashMap<String, String>();
private Map<String , String> voicedonut = new HashMap<String, String>();
private Map<String , String> totalcostdonut = new HashMap<String, String>();
private Map<String, List> mapall = new HashMap<String, List>();
private Map<String, String>voice_domestic_call = new HashMap<String, String>();
private Map<String, String>voice_IDD_calls = new HashMap<String, String>();
private Map<String, String>voice_International_roaming = new HashMap<String, String>();
private Map<String, String>voice_other = new HashMap<String, String>();
private Map<String, String>voice_friend_family = new HashMap<String, String>();
private Map<String, String> voice_mapDate = new HashMap<String, String>();
private Map<String, Map> dashboard_voice = new HashMap<String, Map>();
private Map<String, Map> dashboard_sms = new HashMap<String, Map>();
private Map<String, Map> mapInternet = new HashMap<String, Map>();
private Map<String, String> mapInternetDate = new HashMap<String, String>();
private Map<String, String> totalCost_mapDate = new HashMap<String, String>();
private Map<String, Map> dashboard_total_Cost = new HashMap<String, Map>();
private Map<String, Map> dashboard_other = new HashMap<String, Map>();
private Map<String, String> other_mapDate = new HashMap<String, String>();
private Map<String , String> otherdonut = new HashMap<String, String>();
private Map<String, String> sms_mapDate = new HashMap<String, String>();
private List<WebElement> listyaxischild= new ArrayList<WebElement>();
//static or dynamic data flag
private  Properties sourceDataStatusAccounts = new Properties();

//static data 
private  Properties sourceStaticDataAccounts = new Properties();
	//protected  void setUp( String countryPrefix, String mssidn , String Password){
	protected  void setUp(){
		loadSetUpConfig();
		setUpDriver();
		//signInWithMSSIDN
		signInWithMSSIDN_stage
		(PageNavigation.prop.getProperty(AppConstants.COUNTRY_PREFIX),
						 PageNavigation.prop.getProperty(AppConstants.DASHBOARD_POSTPAID_BROADBAND_STG),
						 PageNavigation.prop.getProperty(AppConstants.DASHBOARD_POSTPAID_BROADBAND_STG_PASS));

		readSourceDataFiles();
		navigateToDashboard();
	}
	
	protected void navigateToDashboard()
	{
		waitForPageLoad(60);
	    handleLink_ByText("MyDigi");
	    waitForPageLoad(30);
		WebElement popup = new WebDriverWait(getDriver(),30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='button button--small button--accept button-default trailer--small icon-arrow-next cancelbtn']")));
		if(popup.isDisplayed())
		{
			popup.click();
		}
		handleLink_ByText("Dashboard");
	}
	
	/*protected void targetInternetTooltip() throws InterruptedException
	{
		handleLink_ByText("Internet");
		Thread.sleep(6000);


		
		//WebElement text = getWebElement_By_xpath(".//*[@id='content']/div[8]/div[2]/div/div/div/div/svg/g[6]/g/g[1]/path");
		//System.out.println(text.getText());
		
	}*/
	
	
	
	
	
	
	protected void targetInternetToolTipData()
	{
		
		List<String> targetTootips = new ArrayList<String>();
		Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);		
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		handleLink_ByText("Internet");


		//jse.executeScript("window.scrollBy(0,250)", "");		 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][6]/*[name()='g']")));	
		//Working :get the stroke attribute to get the color value of graph
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean breakIt = true;
	    String rootNodeXPath = "//*[name()='svg']/*[name()='g'][6]/*[name()='g']" ;
	    WebElement rootElement = getDriver().findElement(By.xpath(rootNodeXPath)) ;
	    //System.out.println("WebElement got :" + rootElement);
	    String childXPathRTroot = "./*[name()='g']"; 
	    List<WebElement> listChildElements = rootElement.findElements(By.xpath(childXPathRTroot));
	    //System.out.println("listChildElements.size() : " + listChildElements.size());
	    int eleListSize = listChildElements.size();
 	   	//List <String> tooltipvaluelist = new ArrayList<String>();
		
		for(int j=0; j<eleListSize; j++){
	    	int n = j+1;
	    	String childNodePath = rootNodeXPath.concat("/*[name()='g']").concat("[").concat(String.valueOf(n).concat("]").concat("/*[name()='path']"));
	    	String childMouseHouverNode = rootNodeXPath.concat("/*[name()='g']").concat("[").concat(String.valueOf(n).concat("]"));
	    	//System.out.println("ChidNodePath : '" +n+"' ->"+childNodePath );
	    	//System.out.println("childMouseHouverNode : '" +n+"' ->"+childMouseHouverNode );
	    	 while (true) {
	     	    breakIt = true;
	     	    try {
	     			Map<String, String> mapValue = new HashMap<String, String>();
	     	    	WebElement rootElementSub = getDriver().findElement(By.xpath(childNodePath));
	     	    	String svgAttribute = rootElementSub.getAttribute("stroke");
	     	    	String svgAttributeD = rootElementSub.findElement(By.xpath(childNodePath)).getAttribute("d");
	     	    	log.info("svgAttribute Stroke: " + svgAttribute);
	     	    	log.info("svgAttribute D: " + svgAttributeD);
	     	    	Actions builder=new Actions(getDriver());
	     	    	 //builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();
	     	    	builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();
	     	    	System.out.println("Mouse hover Performed : '"+n+"'");
	     	    	WebElement mouseHoverdateElement = getDriver().findElement(By.xpath("//div[8]/div[2]/div/div/div/div/div/div/div/div[1]"));
	     	    	WebElement mouseHoverAmountElement = getDriver().findElement(By.xpath("//div[8]/div[2]/div/div/div/div/div/div/div/div[2]"));
	     	    	log.info("MouseHover Object Found : '"+n+"'");
	     	    	 Thread.sleep(2000);
	     	    	String toolTipdate = mouseHoverdateElement.getText();
	     	    	String toolTipAmount = mouseHoverAmountElement.getText();
	     	    	if (toolTipAmount.contains("International Roaming"))
	     	    	{
	     	    		String [] internetToolTip = toolTipAmount.split("Domestic");
	     	    		internetToolTip = internetToolTip[1].split("International Roaming");
	     	    		mapValue.put(AppConstants.DOMESTIC, internetToolTip[0].trim());
	     	    		mapValue.put(AppConstants.INTERNATIONAL_ROAMING, internetToolTip[1].trim());
	     	    		
	     	    	}
	     	    	System.out.println("toolTip dateText : " +toolTipdate );
	     	    	System.out.println("==================================" );
	     	    	System.out.println("toolTip amount : " +toolTipAmount );
	     	    	System.out.println("################################" );
	     	    	mapInternet.put(toolTipdate, mapValue);
	     	    	mapInternetDate.put("INTERNET_DAY_"+n, toolTipdate);
	     	    	
	     	    	getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	     	    } catch (Exception e) {
	     	        if (e.getMessage().contains("element is not attached")) {
	     	        	//System.out.println("Error: Element 4 ");
	     	            breakIt = false;
	     	        }
	         	}
	     	    if (breakIt) {
	     	        break;
	     	    }
	    	
	    }//while loop ends
		
	  }//for loop ends
		
		
		System.out.println(mapInternet);
		System.out.println(mapInternetDate);
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	protected void targetTotalCostToolTipData()
	{
		handleLink_ByText("Total Cost");
		Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);		
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		Map<String, String> mapbill = new HashMap<String, String>();		


		
		//jse.executeScript("window.scrollBy(0,250)", "");		 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][6]/*[name()='g']")));	
		//Working :get the stroke attribute to get the color value of graph
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean breakIt = true;
	    String rootNodeXPath = "//*[name()='svg']/*[name()='g'][6]/*[name()='g']" ;
	    WebElement rootElement = getDriver().findElement(By.xpath(rootNodeXPath)) ;
	    //System.out.println("WebElement got :" + rootElement);
	    String childXPathRTroot = "./*[name()='g']"; 
	    List<WebElement> listChildElements = rootElement.findElements(By.xpath(childXPathRTroot));
	    //System.out.println("listChildElements.size() : " + listChildElements.size());
	    int eleListSize = listChildElements.size();
 	   	//List <String> tooltipvaluelist = new ArrayList<String>();
		
		for(int j=0; j<eleListSize; j++){
	    	int n = j+1;
	    	String childNodePath = rootNodeXPath.concat("/*[name()='g']").concat("[").concat(String.valueOf(n).concat("]").concat("/*[name()='path']"));
	    	String childMouseHouverNode = rootNodeXPath.concat("/*[name()='g']").concat("[").concat(String.valueOf(n).concat("]"));
	    	//System.out.println("ChidNodePath : '" +n+"' ->"+childNodePath );
	    	//System.out.println("childMouseHouverNode : '" +n+"' ->"+childMouseHouverNode );
	    	 while (true) {
	     	    breakIt = true;
	     	    try {
     	    	    Map <String, String> voice_mapall = new HashMap<String, String>();
	     	    	WebElement rootElementSub = getDriver().findElement(By.xpath(childNodePath));
	     	    	String svgAttribute = rootElementSub.getAttribute("stroke");
	     	    	String svgAttributeD = rootElementSub.findElement(By.xpath(childNodePath)).getAttribute("d");
	     	    	log.info("svgAttribute Stroke: " + svgAttribute);
	     	    	log.info("svgAttribute D: " + svgAttributeD);
	     	    	Actions builder=new Actions(getDriver());
	     	    	 //builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();
	     	    	builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();
	     	    	System.out.println("Mouse hover Performed : '"+n+"'");
	     	    	WebElement mouseHoverdateElement = getDriver().findElement(By.xpath("//div[8]/div[2]/div/div/div/div/div/div/div/div[1]"));
	     	    	WebElement mouseHoverAmountElement = getDriver().findElement(By.xpath("//div[8]/div[2]/div/div/div/div/div/div/div/div[2]"));
	     	    	log.info("MouseHover Object Found : '"+n+"'");
	     	    	 Thread.sleep(2000);
	     	    	String toolTipdate = mouseHoverdateElement.getText();
	     	    	String toolTipAmount = mouseHoverAmountElement.getText();
	     	    	totalCost_mapDate.put("TOTALCOST_DAY_"+n, toolTipdate);
	     	    	if (!toolTipAmount.isEmpty())
	     	    	{

	     	    		String [] toolTipData = toolTipAmount.split("Internet");
	     	    		toolTipData = toolTipData[1].split("Voice");
	     	    		voice_mapall.put(AppConstants.INTERNET, toolTipData[0].trim());
	     	    		toolTipData = toolTipData[1].split("SMS/MMS");
	     	    		voice_mapall.put(AppConstants.VOICE, toolTipData[0].trim());
	     	    		toolTipData = toolTipData[1].split("Other");
	     	    		voice_mapall.put(AppConstants.SMS_MMS, toolTipData[0].trim());
	     	    		voice_mapall.put(AppConstants.OTHER, toolTipData[1].trim());
	     	    		
	     	    	System.out.println("toolTip dateText : " +toolTipdate );
	     	    	System.out.println("==================================" );
	     	    	System.out.println("toolTip amount : " +toolTipAmount );
	     	    	System.out.println("################################" );
	     	    	mapbill.put(toolTipdate, toolTipAmount);
	     	    	dashboard_total_Cost.put(toolTipdate, voice_mapall);
	     	    	//getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     	    	}
	     	    } catch (Exception e) {
	     	        if (e.getMessage().contains("element is not attached")) {
	     	        	//System.out.println("Error: Element 4 ");
	     	            breakIt = false;
	     	        }
	         	}
	     	    if (breakIt) {
	     	        break;
	     	    }
	    	
	    }//while loop ends
		
	  }//for loop ends
		
		
		System.out.println(mapbill);
		System.out.println(totalCost_mapDate);
		System.out.println(dashboard_total_Cost);
		
		
	}
	
	
	
	protected void targetOtherToolTipData()
	{
		handleLink_ByText("Other");
		Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);		
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		Map<String, String> mapbill = new HashMap<String, String>();		


		
		//jse.executeScript("window.scrollBy(0,250)", "");		 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][6]/*[name()='g']")));	
		//Working :get the stroke attribute to get the color value of graph
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean breakIt = true;
	    String rootNodeXPath = "//*[name()='svg']/*[name()='g'][6]/*[name()='g']" ;
	    WebElement rootElement = getDriver().findElement(By.xpath(rootNodeXPath)) ;
	    //System.out.println("WebElement got :" + rootElement);
	    String childXPathRTroot = "./*[name()='g']"; 
	    List<WebElement> listChildElements = rootElement.findElements(By.xpath(childXPathRTroot));
	    //System.out.println("listChildElements.size() : " + listChildElements.size());
	    int eleListSize = listChildElements.size();
 	   	//List <String> tooltipvaluelist = new ArrayList<String>();
		
		for(int j=0; j<eleListSize; j++){
	    	int n = j+1;
	    	String childNodePath = rootNodeXPath.concat("/*[name()='g']").concat("[").concat(String.valueOf(n).concat("]").concat("/*[name()='path']"));
	    	String childMouseHouverNode = rootNodeXPath.concat("/*[name()='g']").concat("[").concat(String.valueOf(n).concat("]"));
	    	//System.out.println("ChidNodePath : '" +n+"' ->"+childNodePath );
	    	//System.out.println("childMouseHouverNode : '" +n+"' ->"+childMouseHouverNode );
	    	 while (true) {
	     	    breakIt = true;
	     	    try {
     	    	    Map <String, String> voice_mapall = new HashMap<String, String>();
	     	    	WebElement rootElementSub = getDriver().findElement(By.xpath(childNodePath));
	     	    	String svgAttribute = rootElementSub.getAttribute("stroke");
	     	    	String svgAttributeD = rootElementSub.findElement(By.xpath(childNodePath)).getAttribute("d");
	     	    	log.info("svgAttribute Stroke: " + svgAttribute);
	     	    	log.info("svgAttribute D: " + svgAttributeD);
	     	    	Actions builder=new Actions(getDriver());
	     	    	 //builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();
	     	    	builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();
	     	    	System.out.println("Mouse hover Performed : '"+n+"'");
	     	    	WebElement mouseHoverdateElement = getDriver().findElement(By.xpath("//div[8]/div[2]/div/div/div/div/div/div/div/div[1]"));
	     	    	WebElement mouseHoverAmountElement = getDriver().findElement(By.xpath("//div[8]/div[2]/div/div/div/div/div/div/div/div[2]"));
	     	    	log.info("MouseHover Object Found : '"+n+"'");
	     	    	 Thread.sleep(2000);
	     	    	String toolTipdate = mouseHoverdateElement.getText();
	     	    	String toolTipAmount = mouseHoverAmountElement.getText();
	     	    	other_mapDate.put("OTHER_DAY_"+n, toolTipdate);
	     	    	if (!toolTipAmount.isEmpty())
	     	    	{

	     	    		String [] toolTipData = toolTipAmount.split("Fixed Charges");
	     	    		toolTipData = toolTipData[1].split("One Time Fee");
	     	    		voice_mapall.put(AppConstants.FIXED_CHARGES, toolTipData[0].trim());
	     	    		toolTipData = toolTipData[1].split("Mobile Services");
	     	    		voice_mapall.put(AppConstants.ONE_TIME_FEE, toolTipData[0].trim());
	     	    		toolTipData = toolTipData[1].split("Others");
	     	    		voice_mapall.put(AppConstants.MOBILE_SERVICES, toolTipData[0].trim());
	     	    		voice_mapall.put(AppConstants.OTHERS, toolTipData[1].trim());
	     	    		
	     	    	System.out.println("toolTip dateText : " +toolTipdate );
	     	    	System.out.println("==================================" );
	     	    	System.out.println("toolTip amount : " +toolTipAmount );
	     	    	System.out.println("################################" );
	     	    	mapbill.put(toolTipdate, toolTipAmount);
	     	    	dashboard_other.put(toolTipdate, voice_mapall);
	     	    	//getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     	    	}
	     	    } catch (Exception e) {
	     	        if (e.getMessage().contains("element is not attached")) {
	     	        	//System.out.println("Error: Element 4 ");
	     	            breakIt = false;
	     	        }
	         	}
	     	    if (breakIt) {
	     	        break;
	     	    }
	    	
	    }//while loop ends
		
	  }//for loop ends
		
		
		System.out.println(mapbill);
		System.out.println(other_mapDate);
		System.out.println(dashboard_other);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected void targetVoiceToolTipData()
	{
		handleLink_ByText("Voice");
		List<String> targetTootips = new ArrayList<String>();
		Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);		
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		Map<String, String> mapbill = new HashMap<String, String>();		


		
		//jse.executeScript("window.scrollBy(0,250)", "");		 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][6]/*[name()='g']")));	
		//Working :get the stroke attribute to get the color value of graph
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean breakIt = true;
	    String rootNodeXPath = "//*[name()='svg']/*[name()='g'][6]/*[name()='g']" ;
	    WebElement rootElement = getDriver().findElement(By.xpath(rootNodeXPath)) ;
	    //System.out.println("WebElement got :" + rootElement);
	    String childXPathRTroot = "./*[name()='g']"; 
	    List<WebElement> listChildElements = rootElement.findElements(By.xpath(childXPathRTroot));
	    //System.out.println("listChildElements.size() : " + listChildElements.size());
	    int eleListSize = listChildElements.size();
 	   	//List <String> tooltipvaluelist = new ArrayList<String>();
		
		for(int j=0; j<eleListSize; j++){
	    	int n = j+1;
	    	String childNodePath = rootNodeXPath.concat("/*[name()='g']").concat("[").concat(String.valueOf(n).concat("]").concat("/*[name()='path']"));
	    	String childMouseHouverNode = rootNodeXPath.concat("/*[name()='g']").concat("[").concat(String.valueOf(n).concat("]"));
	    	//System.out.println("ChidNodePath : '" +n+"' ->"+childNodePath );
	    	//System.out.println("childMouseHouverNode : '" +n+"' ->"+childMouseHouverNode );
	    	 while (true) {
	     	    breakIt = true;
	     	    try {
     	    	    Map <String, String> voice_mapall = new HashMap<String, String>();
	     	    	WebElement rootElementSub = getDriver().findElement(By.xpath(childNodePath));
	     	    	String svgAttribute = rootElementSub.getAttribute("stroke");
	     	    	String svgAttributeD = rootElementSub.findElement(By.xpath(childNodePath)).getAttribute("d");
	     	    	log.info("svgAttribute Stroke: " + svgAttribute);
	     	    	log.info("svgAttribute D: " + svgAttributeD);
	     	    	Actions builder=new Actions(getDriver());
	     	    	 //builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();
	     	    	builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();
	     	    	System.out.println("Mouse hover Performed : '"+n+"'");
	     	    	WebElement mouseHoverdateElement = getDriver().findElement(By.xpath("//div[8]/div[2]/div/div/div/div/div/div/div/div[1]"));
	     	    	WebElement mouseHoverAmountElement = getDriver().findElement(By.xpath("//div[8]/div[2]/div/div/div/div/div/div/div/div[2]"));
	     	    	log.info("MouseHover Object Found : '"+n+"'");
	     	    	 Thread.sleep(2000);
	     	    	String toolTipdate = mouseHoverdateElement.getText();
	     	    	String toolTipAmount = mouseHoverAmountElement.getText();
	     	    	voice_mapDate.put("VOICE_DAY_"+n, toolTipdate);
	     	    	if (!toolTipAmount.isEmpty())
	     	    	{

	     	    		String [] toolTipData = toolTipAmount.split("Domestic");
	     	    		toolTipData = toolTipData[1].split("Friends & Family");
	     	    		voice_domestic_call.put(toolTipdate, toolTipData[0].trim());
	     	    		voice_mapall.put(AppConstants.DOMESTIC, toolTipData[0].trim());
	     	    		toolTipData = toolTipData[1].split("IDD Calls");
	     	    		voice_friend_family.put(toolTipdate, toolTipData[0].trim());
	     	    		voice_mapall.put(AppConstants.FRIEND_FAMILY, toolTipData[0].trim());
	     	    		toolTipData = toolTipData[1].split("International Roaming");
	     	    		voice_IDD_calls.put(toolTipdate, toolTipData[0].trim());
	     	    		voice_mapall.put(AppConstants.IDD_CALLS, toolTipData[0].trim());
	     	    		//toolTipData = toolTipData[1].split("Other");     	    		
	     	    		voice_mapall.put(AppConstants.INTERNATIONAL_ROAMING, toolTipData[1].trim());
	     	    		//voice_mapall.put(AppConstants.OTHER, toolTipData[1].trim());
	     	    		
	     	    	System.out.println("toolTip dateText : " +toolTipdate );
	     	    	System.out.println("==================================" );
	     	    	System.out.println("toolTip amount : " +toolTipAmount );
	     	    	System.out.println("################################" );
	     	    	mapbill.put(toolTipdate, toolTipAmount);
	     	    	dashboard_voice.put(toolTipdate, voice_mapall);
	     	    	//getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     	    	}
	     	    } catch (Exception e) {
	     	        if (e.getMessage().contains("element is not attached")) {
	     	        	//System.out.println("Error: Element 4 ");
	     	            breakIt = false;
	     	        }
	         	}
	     	    if (breakIt) {
	     	        break;
	     	    }
	    	
	    }//while loop ends
		
	  }//for loop ends
		
		
		System.out.println(mapbill);
		System.out.println(voice_mapDate);
		System.out.println("voice domestic"+voice_domestic_call);
		System.out.println("International Roaming"+voice_International_roaming);
		System.out.println("IDD_CALLS"+voice_IDD_calls);
		System.out.println("others"+voice_other);
		System.out.println("############################################################");
		System.out.println(dashboard_voice);
		
		
	}
	
	protected void targetSMSToolTipData()
	{
		handleLink_ByText("SMS/MMS");
		List<String> targetTootips = new ArrayList<String>();
		Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);		
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		Map<String, String> mapbill = new HashMap<String, String>();
		
		

		
		//jse.executeScript("window.scrollBy(0,250)", "");		 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][6]/*[name()='g']")));	
		//Working :get the stroke attribute to get the color value of graph
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean breakIt = true;
	    String rootNodeXPath = "//*[name()='svg']/*[name()='g'][6]/*[name()='g']" ;
	    WebElement rootElement = getDriver().findElement(By.xpath(rootNodeXPath)) ;
	    String childXPathRTroot = "./*[name()='g']"; 
	    List<WebElement> listChildElements = rootElement.findElements(By.xpath(childXPathRTroot));
	    int eleListSize = listChildElements.size();
		
		for(int j=0; j<eleListSize; j++){
	    	int n = j+1;
	    	String childNodePath = rootNodeXPath.concat("/*[name()='g']").concat("[").concat(String.valueOf(n).concat("]").concat("/*[name()='path']"));
	    	String childMouseHouverNode = rootNodeXPath.concat("/*[name()='g']").concat("[").concat(String.valueOf(n).concat("]"));
	    	 while (true) {
	     	    breakIt = true;
	     	    try {
     	    	    Map <String, String> sms_mapall = new HashMap<String, String>();
	     	    	WebElement rootElementSub = getDriver().findElement(By.xpath(childNodePath));
	     	    	String svgAttribute = rootElementSub.getAttribute("stroke");
	     	    	String svgAttributeD = rootElementSub.findElement(By.xpath(childNodePath)).getAttribute("d");
	     	    	log.info("svgAttribute Stroke: " + svgAttribute);
	     	    	log.info("svgAttribute D: " + svgAttributeD);
	     	    	Actions builder=new Actions(getDriver());
	     	    	builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();
	     	    	System.out.println("Mouse hover Performed : '"+n+"'");
	     	    	WebElement mouseHoverdateElement = getDriver().findElement(By.xpath("//div[8]/div[2]/div/div/div/div/div/div/div/div[1]"));
	     	    	WebElement mouseHoverAmountElement = getDriver().findElement(By.xpath("//div[8]/div[2]/div/div/div/div/div/div/div/div[2]"));
	     	    	log.info("MouseHover Object Found : '"+n+"'");
	     	    	 Thread.sleep(2000);
	     	    	String toolTipdate = mouseHoverdateElement.getText();
	     	    	String toolTipAmount = mouseHoverAmountElement.getText();
	     	    	sms_mapDate.put("SMS_DAY_"+n, toolTipdate);
	     	    	if (!toolTipAmount.isEmpty())
	     	    	{

	     	    		String [] toolTipData = toolTipAmount.split("Domestic SMS");
	     	    		toolTipData = toolTipData[1].split("Friends & Family SMS");
	     	    		sms_mapall.put(AppConstants.DOMESTIC, toolTipData[0].trim());
	     	    		toolTipData = toolTipData[1].split("International SMS");
	     	    		sms_mapall.put(AppConstants.FRIEND_FAMILY, toolTipData[0].trim());
	     	    		toolTipData = toolTipData[1].split("International Roaming SMS");
	     	    		sms_mapall.put(AppConstants.INTERNATIONAL_SMS, toolTipData[0].trim());
	     	    		toolTipData = toolTipData[1].split("Domestic MMS");     	    		
	     	    		sms_mapall.put(AppConstants.INTERNATIONAL_ROAMING, toolTipData[0].trim());
	     	    		toolTipData = toolTipData[1].split("International Roaming MMS");
	     	    		sms_mapall.put(AppConstants.DOMESTIC_MMS, toolTipData[0].trim());
	     	    		sms_mapall.put(AppConstants.INTERNATIONAL_ROAMING_MMS, toolTipData[1].trim());
	     	    		
	     	    	System.out.println("toolTip dateText : " +toolTipdate );
	     	    	System.out.println("==================================" );
	     	    	System.out.println("toolTip amount : " +toolTipAmount );
	     	    	System.out.println("################################" );
	     	    	mapbill.put(toolTipdate, toolTipAmount);

	     	    	dashboard_sms.put(toolTipdate, sms_mapall);
	     	    	//getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     	    	}
	     	    } catch (Exception e) {
	     	        if (e.getMessage().contains("element is not attached")) {
	     	        	//System.out.println("Error: Element 4 ");
	     	            breakIt = false;
	     	        }
	         	}
	     	    if (breakIt) {
	     	        break;
	     	    }
	    	
	    }//while loop ends
		
	  }//for loop ends
		
		
		System.out.println(mapbill);
		System.out.println(sms_mapDate);
		System.out.println("############################################################");
		System.out.println(dashboard_sms);
		
		
	}
	
	
	
	
	
	
	protected void voiceDonutValues() throws InterruptedException
	{
		//getDriver().navigate().refresh();
		handleLink_ByText("Voice");
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		//Thread.sleep(6000);
		jse.executeScript("window.scrollBy(0,950)", "");
		Thread.sleep(2000);


		List <WebElement>donutPrameter = getDriver().findElements(By.xpath(".//*[@class='amchart-small-caption']"));
		List <WebElement>donutValue = getDriver().findElements(By.xpath(".//*[@class='amchart-small-title']"));
		List <WebElement>donutScale = getDriver().findElements(By.xpath(".//*[@class='amchart-small-text']"));

		jse.executeScript("window.scrollBy(0,-950)", "");
		
		for(int i= 0; i<donutPrameter.size(); i++ )
		{		
		System.out.println(donutPrameter.get(i).getText());
		System.out.println(donutValue.get(i).getText());
		System.out.println(donutScale.get(i).getText());
		System.out.println(donutValue.get(i).getText().trim().toString().concat(" ").concat(donutScale.get(i).getText().trim()));
		//voicedonut.put(donutPrameter.get(i).getText().trim(), donutValue.get(i).getText().trim());
		voicedonut.put(donutPrameter.get(i).getText().trim(), donutValue.get(i).getText().trim().toString().concat(" ").concat(donutScale.get(i).getText().trim()));
		}
		
		System.out.println(voicedonut);
		
		
	}
	
	protected void totalCostDonutValues() throws InterruptedException
	{
		//getDriver().navigate().refresh();
		handleLink_ByText("Total Cost");
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		//Thread.sleep(6000);
		jse.executeScript("window.scrollBy(0,750)", "");
		Thread.sleep(6000);


		List <WebElement>donutPrameter = getDriver().findElements(By.xpath(".//*[@class='amchart-small-caption']"));
		List <WebElement>donutValue = getDriver().findElements(By.xpath(".//*[@class='amchart-small-title']"));
		List <WebElement>donutScale = getDriver().findElements(By.xpath(".//*[@class='amchart-small-text']"));

		jse.executeScript("window.scrollBy(0,-950)", "");	
		for(int i= 0; i<donutPrameter.size(); i++ )
		{		
		System.out.println(donutPrameter.get(i).getText());
		System.out.println(donutValue.get(i).getText());
		System.out.println(donutScale.get(i).getText());
		System.out.println(donutValue.get(i).getText().trim().toString().concat(" ").concat(donutScale.get(i).getText().trim()));
		//totalcostdonut.put(donutPrameter.get(i).getText().trim(), donutScale.get(i).getText().trim());
		totalcostdonut.put(donutPrameter.get(i).getText().trim(), donutValue.get(i).getText().trim().toString().concat(" ").concat(donutScale.get(i).getText().trim()));
		
		}
		
		System.out.println(totalcostdonut);
		
		
	}
	
	protected void smsDonutValues() throws InterruptedException
	{
		//getDriver().navigate().refresh();
		handleLink_ByText("SMS/MMS");
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();

		jse.executeScript("window.scrollBy(0,950)", "");

		Thread.sleep(2000);
		List <WebElement>donutPrameter = getDriver().findElements(By.xpath(".//*[@class='amchart-small-caption']"));
		List <WebElement>donutValue = getDriver().findElements(By.xpath(".//*[@class='amchart-small-title']"));
		List <WebElement>donutScale = getDriver().findElements(By.xpath(".//*[@class='amchart-small-text']"));

		jse.executeScript("window.scrollBy(0,-950)", "");
		for(int i= 0; i<donutPrameter.size(); i++ )
		{		
		System.out.println(donutPrameter.get(i).getText());
		System.out.println(donutValue.get(i).getText());
		System.out.println(donutScale.get(i).getText());
		//smsdonut.put(donutPrameter.get(i).getText().trim(), donutValue.get(i).getText().trim());
		System.out.println(donutValue.get(i).getText().trim().toString().concat(" ").concat(donutScale.get(i).getText().trim()));
		smsdonut.put(donutPrameter.get(i).getText().trim(), donutValue.get(i).getText().trim().toString().concat(" ").concat(donutScale.get(i).getText().trim()));
		}
		
		System.out.println(smsdonut);
	
	}
	
	protected void internetDonutValues() throws InterruptedException
	{
		//getDriver().navigate().refresh();
		handleLink_ByText("Internet");
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		//Thread.sleep(6000);
		jse.executeScript("window.scrollBy(0,950)", "");
		Thread.sleep(2000);
		//Thread.sleep(2000);		
		//handleLink_ByText("Internet");
		//Thread.sleep(2000);
		//handleLink_ByText("Voice");
		//Thread.sleep(6000);

		List <WebElement>donutPrameter = getDriver().findElements(By.xpath(".//*[@class='amchart-small-caption']"));
		List <WebElement>donutValue = getDriver().findElements(By.xpath(".//*[@class='amchart-small-title']"));
		List <WebElement>donutScale = getDriver().findElements(By.xpath(".//*[@class='amchart-small-text']"));

		jse.executeScript("window.scrollBy(0,-950)", "");
		
		for(int i= 0; i<donutPrameter.size(); i++ )
		{		
		System.out.println(donutPrameter.get(i).getText());
		System.out.println(donutValue.get(i).getText());
		System.out.println(donutScale.get(i).getText());
		//internetdonut.put(donutPrameter.get(i).getText().trim(), donutValue.get(i).getText().trim());
		System.out.println(donutValue.get(i).getText().trim().toString().concat(" ").concat(donutScale.get(i).getText().trim()));
		internetdonut.put(donutPrameter.get(i).getText().trim(), donutValue.get(i).getText().trim().toString().concat(" ").concat(donutScale.get(i).getText().trim()));
		}
		
		System.out.println(internetdonut);
		
		
	}
	
	protected void otherDonutValues() throws InterruptedException
	{
		//getDriver().navigate().refresh();
		handleLink_ByText("Other");
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		//Thread.sleep(6000);
		jse.executeScript("window.scrollBy(0,950)", "");


		List <WebElement>donutPrameter = getDriver().findElements(By.xpath(".//*[@class='amchart-small-caption']"));
		List <WebElement>donutValue = getDriver().findElements(By.xpath(".//*[@class='amchart-small-title']"));
		List <WebElement>donutScale = getDriver().findElements(By.xpath(".//*[@class='amchart-small-text']"));

		jse.executeScript("window.scrollBy(0,-950)", "");
		for(int i= 0; i<donutPrameter.size(); i++ )
		{		
		System.out.println(donutPrameter.get(i).getText());
		System.out.println(donutValue.get(i).getText());
		System.out.println(donutScale.get(i).getText());
		//otherdonut.put(donutPrameter.get(i).getText().trim(), donutScale.get(i).getText().trim());
		System.out.println(donutValue.get(i).getText().trim().toString().concat(" ").concat(donutScale.get(i).getText().trim()));
		otherdonut.put(donutPrameter.get(i).getText().trim(), donutValue.get(i).getText().trim().toString().concat(" ").concat(donutScale.get(i).getText().trim()));
		}
		
		System.out.println(otherdonut);
	
	}
	
	
	
	/**
 	 * Reads the Bills n Reload page source data
 	 * to get the dynamic/static data
 	 */
     private void readSourceDataFiles(){
    	String statusFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
    											   .concat(AppConstants.DASHBOARD_POSTPAID_BROADBAND_DATA_STATUS_STG);
    	sourceDataStatusAccounts = loadFileData(statusFileName);
    	
    	String dataFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
												 .concat(AppConstants.DASHBOARD_POSTPAID_BROADBAND_STATIC_DATA_STG);
    	sourceStaticDataAccounts =  loadFileData(dataFileName);
    	/*System.out.println("Source Data Status :"+ sourceDataStatus);
    	System.out.println("Source Static Data :"+ sourceStaticData);*/
     }
     /**
      * Get the static source data 
      */
     
     protected String getSourceData(String inputData){
    	 String returnData = null;
    	 
    	 if(sourceDataStatusAccounts.containsKey(inputData)){
    		 returnData = findSourceData(inputData);
    		 log.info("Success : Static Source data '"+ inputData + "' -->"+returnData );
    		// System.out.println("Success : Static Source data '"+ inputData + "' -->"+returnData );
    	 }else{
    		 returnData= "Fail :" + inputData + " Not found in Status File" ;
    		 log.error("Fail :" + inputData + " Not found in Status File");
    	 }
    	 
    	 return returnData;
     }
     /**
      * read the static source data 
      * @param inputData
      * @return
      */
     private String findSourceData(String inputData){
    	 String returnData = null;
    	 String status = sourceDataStatusAccounts.getProperty(inputData);
    	 if(status.equalsIgnoreCase(AppConstants.YES)){
    		 returnData = sourceStaticDataAccounts.getProperty(inputData);
    	 }
    	 return returnData;
     }

 	//Gets the Date key
		protected String getDOMESTICCALL_KEY(String keyData){
			String returnData = null;
			returnData = AppConstants.VOICE_DOMESTIC_CALL_KEY
									.concat(AppConstants.BEGIN_BRACKET)
									.concat(keyData)
									.concat(AppConstants.CLOSE_BRACKET);
			
			return returnData;
		}
   
	 	//Gets the Date key
			protected String getIDDCALLS_KEY(String keyData){
				String returnData = null;
				returnData = AppConstants.VOICE_IDD_CALLS_KEY
										.concat(AppConstants.BEGIN_BRACKET)
										.concat(keyData)
										.concat(AppConstants.CLOSE_BRACKET);
				
				return returnData;
			}
     
		 	//Gets the Date key
				protected String getINTROAMING_KEY(String keyData){
					String returnData = null;
					returnData = AppConstants.VOICE_INT_ROAMING_KEY
											.concat(AppConstants.BEGIN_BRACKET)
											.concat(keyData)
											.concat(AppConstants.CLOSE_BRACKET);
					
					return returnData;
				}
				
			 	//Gets the Date key
					protected String getFRIENDNFAMILY(String keyData){
						String returnData = null;
						returnData = AppConstants.VOICE_FRIEND_FAMILY_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
				 	//Gets the Date key
					protected String getInternetDomesticUsage(String keyData){
						String returnData = null;
						returnData = AppConstants.INTERNET_DOMESTIC_USAGE_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
				 	//Gets the Date key
					protected String getInternetInternationalRoaming(String keyData){
						String returnData = null;
						returnData = AppConstants.INTERNET_INTERNATIONAL_ROAMING_USAGE_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
				 	//Gets the Date key
					protected String getOTHER(String keyData){
						String returnData = null;
						returnData = AppConstants.VOICE_OTHER_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}	
					
					
				 	//Gets the Date key
					protected String getSMSDomestic(String keyData){
						String returnData = null;
						returnData = AppConstants.SMS_DOMESTIC_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}	
	
					
				 	//Gets the Date key
					protected String getSMSFriendNFamily(String keyData){
						String returnData = null;
						returnData = AppConstants.SMS_FRIEND_FAMILY_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}	
					
				 	//Gets the Date key
					protected String getSMSInternational(String keyData){
						String returnData = null;
						returnData = AppConstants.SMS_INTERNATIONAL_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}	
					
				 	//Gets the Date key
					protected String getSMSInternationalRoaming(String keyData){
						String returnData = null;
						returnData = AppConstants.SMS_INTERNATIONAL_ROAMING_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
				 	//Gets the Date key
					protected String getSMSOTHER(String keyData){
						String returnData = null;
						returnData = AppConstants.SMS_OTHER_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}						
					
				 	//Gets the Date key
					protected String getSMSDomesticMMS(String keyData){
						String returnData = null;
						returnData = AppConstants.SMS_DOMESTIC_MMS_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}						
					
					protected String getSMSInternationalRoamingMMS(String keyData){
						String returnData = null;
						returnData = AppConstants.SMS_INTERNATIONAL_ROAMING_MMS_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}			
					
				 	//Gets the Date key
					protected String getTotalCostInternet(String keyData){
						String returnData = null;
						returnData = AppConstants.TOTALCOST_INTERNET_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
					protected String getTotalCosSMS_MMS(String keyData){
						String returnData = null;
						returnData = AppConstants.TOTALCOST_SMS_MMS_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
					protected String getTotalCostVoice(String keyData){
						String returnData = null;
						returnData = AppConstants.TOTALCOST_VOICE_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
					protected String getTotalCostOther(String keyData){
						String returnData = null;
						returnData = AppConstants.TOTALCOST_OTHER_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
					protected String getOtherFixedCharges(String keyData){
						String returnData = null;
						returnData = AppConstants.OTHER_FIXED_CHARGES_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
					protected String getOtherOneTimeFee(String keyData){
						String returnData = null;
						returnData = AppConstants.OTHER_ONE_TIME_FEE_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
					protected String getOtherMobileServices(String keyData){
						String returnData = null;
						returnData = AppConstants.OTHER_MOBILE_SERVICES_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
					protected String getOtherOthers(String keyData){
						String returnData = null;
						returnData = AppConstants.OTHER_OTHERS_KEY
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
					protected String getYAxis(String keyData, String Prefix ){
						String returnData = null;
						returnData = Prefix
												.concat(AppConstants.BEGIN_BRACKET)
												.concat(keyData)
												.concat(AppConstants.CLOSE_BRACKET);
						
						return returnData;
					}
					
					 public void validate_YAxis(){
						 
						 listyaxischild.clear();
						 Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);            
						 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][13]/*[name()='g'][2]")));       
					      //Working :get the stroke attribute to get the color value of graph
					       boolean breakIt = true;	           
					       //Get the BillCycle Elements
					       String yaxisrootNodeXpath = "//*[name()='svg']/*[name()='g'][13]/*[name()='g'][2]" ;
					       WebElement yaxisrootElement = getDriver().findElement(By.xpath(yaxisrootNodeXpath));
					       String yaxischildXpath= "./*[name()='text']";
					        listyaxischild = yaxisrootElement.findElements(By.xpath(yaxischildXpath));				
					}
					
					
					
	protected List<WebElement> getTargetListyaxischild() {
						return listyaxischild;
					}
	protected Properties getSourceDataStatusAccounts() {
		return sourceDataStatusAccounts;
	}


	protected Properties getSourceStaticDataAccounts() {
		return sourceStaticDataAccounts;
	}



	protected Map<String, String> getTargetSMSDonut() {
		return smsdonut;
	}
	
	protected Map<String, String> getTargetVoiceDonut() {
		return voicedonut;
	}

	protected Map<String, String> getTargetInternetDonut() {
		return internetdonut;
	}
	protected Map<String, Map> getTargetVoiceGraph() {
		return dashboard_voice;
	}

	protected Map<String, String> getTargetVoicedomesticcall() {
		return voice_domestic_call;
	}

	protected Map<String, String> getTargetVoiceIDDcalls() {
		return voice_IDD_calls;
	}

	protected Map<String, String> getTargetVoiceInternationalRoaming() {
		return voice_International_roaming;
	}

	protected Map<String, String> getTargetVoiceOther() {
		return voice_other;
	}

	protected Map<String, String> getTargetVoicefriendFamily() {
		return voice_friend_family;
	}

	protected Map<String, String> getTargetVoicemapDate() {
		return voice_mapDate;
	}

	protected Map<String, Map> getTargetMapInternet() {
		return mapInternet;
	}

	protected Map<String, String> getTargetMapInternetDate() {
		return mapInternetDate;
	}

	protected Map<String, Map> getTargetDashboard_sms() {
		return dashboard_sms;
	}


	protected Map<String, String> getTargetSms_mapDate() {
		return sms_mapDate;
	}

	protected Map<String, String> getTargetTotalCost_mapDate() {
		return totalCost_mapDate;
	}


	protected Map<String, Map> getTargetDashboard_total_Cost() {
		return dashboard_total_Cost;
	}

	protected Map<String, Map> getTargetDashboard_other() {
		return dashboard_other;
	}



	protected Map<String, String> getTargetOther_mapDate() {
		return other_mapDate;
	}

	protected Map<String, String> getTargetTotalcostdonut() {
		return totalcostdonut;
	}

	protected Map<String, String> getTargetOtherdonut() {
		return otherdonut;
	}
	
}
