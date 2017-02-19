package com.digi.selenium.util.Stores_Orders;

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

public class Stores_Orders_Prepaid_Util extends PageNavigation {

private  Logger log = Logger.getLogger(Stores_Orders_Prepaid_Util.class);

private Map<String , String> storesOrders = new HashMap<String, String>();
private Map<String , String> totalNtrack = new HashMap<String, String>();
private List<WebElement> rechargeAmountList= new ArrayList<WebElement>();
private List<WebElement> trackDeliveryList= new ArrayList<WebElement>();
private List<WebElement> paymentStatusList= new ArrayList<WebElement>();


private  Properties sourceDataStatusAccounts = new Properties();

//static data 
private  Properties sourceStaticDataAccounts = new Properties();
	//protected  void setUp( String countryPrefix, String mssidn , String Password){
	protected  void setUp() {
		loadSetUpConfig();
		setUpDriver();
		//signInWithMSSIDN
		signInWithMSSIDN_stage
		(PageNavigation.prop.getProperty(AppConstants.COUNTRY_PREFIX),
						 PageNavigation.prop.getProperty(AppConstants.DASHBOARD_PREPAID_STG),
						 PageNavigation.prop.getProperty(AppConstants.DASHBOARD_PREPAID_STG_PASS));

		readSourceDataFiles();
		navigateToStoresOrders();
	}
	
	protected void navigateToStoresOrders() 
	{
		waitForPageLoad(60);
	    handleLink_ByText("MyDigi");
	    waitForPageLoad(30);
	    /*
		WebElement popup = new WebDriverWait(getDriver(),30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='button button--small button--accept button-default trailer--small icon-arrow-next cancelbtn']")));
		if(popup.isDisplayed())
		{
			popup.click();
		}*/
		handleLink_ByText("Store & Orders");
	}
	

	
	
		
	protected void targetInternetToolTipData()
	{}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	protected void targetTotalCostToolTipData()
	{}
	
	
	
	protected void targetOtherToolTipData()
	{}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected void targetVoiceToolTipData()
	{}
	
	protected void targetSMSToolTipData()
	{}
	
	
	
	
	
	
	protected void voiceDonutValues() throws InterruptedException
	{}
	
	protected void totalCostDonutValues() throws InterruptedException
	{}
	
	protected void smsDonutValues() throws InterruptedException
	{}
	
	protected void internetDonutValues() throws InterruptedException
	{}
	
	protected void otherDonutValues() throws InterruptedException
	{}
	
	
	
	/**
 	 * Reads the Bills n Reload page source data
 	 * to get the dynamic/static data
 	 */
     private void readSourceDataFiles(){
    	String statusFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
    											   .concat(AppConstants.STORES_ORDERS_PREPAID_DATA_STATUS);
    	sourceDataStatusAccounts = loadFileData(statusFileName);
    	
    	String dataFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
												 .concat(AppConstants.STORES_ORDERS_PREPAID_STATIC_DATA);
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
   
		
		 public void validateParameters(){
			 
			 Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);
			 String parameters=".//tr[@class='row--text-grey']/td[1]";
			 String parametersValue=".//tr[@class='row--text-grey']/td[2]";
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(parameters)));
			 List <WebElement>xpathsParameters=getDriver().findElements(By.xpath(parameters));
			 List <WebElement>XpathsParametersValue=getDriver().findElements(By.xpath(parametersValue));
			 int i=0;
			for(int j=1; i<xpathsParameters.size();j++)
			{
				for (int k=0; k<5; k=k+1)
				{
					
					String key = getStoresOrdersKey(xpathsParameters.get(i).getText().toString().trim(),Integer.toString(j));
					String value = XpathsParametersValue.get(i).getText().toString();
					storesOrders.put(key, value);
					i++;
				}
				}
			
			System.out.println(storesOrders);
		 	}
		 
		 public void validateRechargeAmount(){
			 
			 rechargeAmountList.clear();
			 Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);
			 String amountxpath=".//*[@class='order-summary__top-alpha']";
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(amountxpath)));       
		        rechargeAmountList = getDriver().findElements(By.xpath(amountxpath));				
		}
		 public void validateTrackDeliveryLink(){
			 

			 Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);
			 String trackDeliveryXpath=".//*[@class='font-bold']/td[1]/span/a";
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(trackDeliveryXpath)));       
			 trackDeliveryList = getDriver().findElements(By.xpath(trackDeliveryXpath));				
		}
		 
		 public void validateTotalNTrackDelivery(){
			 
			 Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);
			 String parameters=".//tr[@class='font-bold']/td[1]";
			 String parametersValue=".//tr[@class='font-bold']/td[2]";
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(parameters)));
			 List <WebElement>xpathsParameters=getDriver().findElements(By.xpath(parameters));
			 List <WebElement>XpathsParametersValue=getDriver().findElements(By.xpath(parametersValue));
			 int i=0;
			for(int j=1; i<xpathsParameters.size();j++)
			{
				for (int k=0; k<2; k=k+1)
				{
					
					String key = getStoresOrdersKey(xpathsParameters.get(i).getText().toString().trim(),Integer.toString(j));
					String value = XpathsParametersValue.get(i).getText().toString();
					totalNtrack.put(key, value);
					i++;
				}
				}
			System.out.println(totalNtrack);
		}
		 
		 public void validatePaymentStatus(){
			 
			 paymentStatusList.clear();
			 Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);
			 String paymentStatusXpath=".//*[@class='order-summary__top-gamma']/strong";
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(paymentStatusXpath)));       
			 paymentStatusList = getDriver().findElements(By.xpath(paymentStatusXpath));				
		}

					
					 public String getemailid(){
						 
		
						 Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);
						 String webelementxpath=".//*[@class='access-info']";
						 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(webelementxpath)));       
					        return getDriver().findElement(By.xpath(webelementxpath)).getText();				
					}
					 
						protected String gettKey(String keyData, String Prefix ){
							String returnData = null;
							returnData = Prefix
													.concat(AppConstants.BEGIN_BRACKET)
													.concat(keyData)
													.concat(AppConstants.CLOSE_BRACKET);
							
							return returnData;
						}
						
						protected String getStoresOrdersKey(String keyData , String i ){
							String returnData = null;
							returnData = keyData
													.concat(AppConstants.BEGIN_BRACKET)
													.concat(i)
													.concat(AppConstants.CLOSE_BRACKET);
							
							return returnData;
						}
						
						
	protected List<WebElement> getTargetrechargeAmountList() {
							return rechargeAmountList;
						}				
					
					

	protected List<WebElement> getTragetTrackDeliveryList() {
		return trackDeliveryList;
	}



	protected Map<String, String> getTargetTotalNtrack() {
		return totalNtrack;
	}


	protected List<WebElement> getTargetPaymentStatusList() {
		return paymentStatusList;
	}
	protected Properties getSourceDataStatusAccounts() {
		return sourceDataStatusAccounts;
	}


	protected Properties getSourceStaticDataAccounts() {
		return sourceStaticDataAccounts;
	}

	protected Map<String, String> getTargetStoresOrders() {
		return storesOrders;
	}



	
}
