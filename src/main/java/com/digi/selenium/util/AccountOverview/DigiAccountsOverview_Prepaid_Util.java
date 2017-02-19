package com.digi.selenium.util.AccountOverview;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.common.PageNavigation;

public class DigiAccountsOverview_Prepaid_Util extends PageNavigation {
	
	protected  Logger log = Logger.getLogger(DigiAccountsOverview_Prepaid_Util.class);
	
	//static or dynamic data flag
	private  Properties sourceDataStatusAccounts = new Properties();

	//static data 
	private  Properties sourceStaticDataAccounts = new Properties();
	
	private String  actualaccountno = new String();
	private String  actualaccountname = new String();
	private String  actualbalance = new String();
	private String  actualexpiry = new String();
	private String  actualplanname = new String();
	private String  actualmsisdn = new String();
	private String  actualstatus = new String();
	private String airtimeamt="";
	private String totalamt="";
	private String finalamt="";
	private String transactionno="";
	private String errormsg="";
	
	protected  void setUp(){ 
		//try {
		loadSetUpConfig();
		setUpDriver();
		signInWithMSSIDN_stage(PageNavigation.prop.getProperty(AppConstants.COUNTRY_PREFIX),
						 PageNavigation.prop.getProperty(AppConstants.AccountsOverview_PREPAID_MSISDN),
						 PageNavigation.prop.getProperty(AppConstants.AccountsOverview_PREPAID_PASS));
		movetoAccountsOverviewPrepaid();
		readSourceDataFiles();
		/*}catch(Exception e) {
			shutDown();
		}*/
	}

	protected void movetoAccountsOverviewPrepaid() 
	{
	    waitForPageLoad(30);
	    handleLink_ByText("MyDigi");
	    waitForPageLoad(30);
	    
	    waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div[13]/div/div"))); 
	    getDriver().findElement(By.xpath("//*[@id='content']/div[13]/div/div/div[2]/button[1]")).click();
	  //*[@id="telenor-cancel-btn"]
	    waitForPageLoad(30);
	    waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='telenor-cancel-btn']"))); 
	    getDriver().findElement(By.xpath("//*[@id='telenor-cancel-btn']")).click();
	    waitForPageLoad(30);
	    //Thread.sleep(4000);
	  /*  handleLink_ByText("Accounts & Plans");
	    waitForPageLoad(30);
	  //*[@id="content"]/div[11]/div/div/div[2]/button[1]
	  //*[@id="content"]/div[11]/div/div/div[2]/button[1]
	    waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div[11]/div/div"))); 
	    getDriver().findElement(By.xpath("//*[@id='content']/div[11]/div/div/div[2]/button[1]")).click();
	  //*[@id="telenor-cancel-btn"]
	    waitForPageLoad(30);
	    waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='telenor-cancel-btn']"))); 
	    getDriver().findElement(By.xpath("//*[@id='telenor-cancel-btn']")).click();
	    waitForPageLoad(30);*/
	}
	
	/**
 	 * Reads the Bills n Reload page source data
 	 * to get the dynamic/static data
	 * @throws Exception 
 	 */
     private void readSourceDataFiles() {
    	// try {
    	String statusFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
    											   .concat(AppConstants.DIGIACCOUNTSOVERVIEW_PREPAID_DATASTATUSFILE_LOCATION);
    	sourceDataStatusAccounts = loadFileData(statusFileName);
    	
    	String dataFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
												 .concat(AppConstants.DIGIACCOUNTSOVERVIEW_PREPAID_STATICDATAFILE_LOCATION);
    	sourceStaticDataAccounts =  loadFileData(dataFileName);
    	/*System.out.println("Source Data Status :"+ sourceDataStatus);
    	System.out.println("Source Static Data :"+ sourceStaticData);*/
    	log.info(AppConstants.DIGIACCOUNTSOVERVIEW_PREPAID_DATASTATUSFILE_LOCATION+" and "+AppConstants.DIGIACCOUNTSOVERVIEW_PREPAID_STATICDATAFILE_LOCATION+" config files are successfully loaded");
    	/* }catch(Exception e) {
    		 log.error("Error in loading config files: "+AppConstants.DIGIACCOUNTSOVERVIEW_PREPAID_DATASTATUSFILE_LOCATION+" and "+AppConstants.DIGIACCOUNTSOVERVIEW_PREPAID_STATICDATAFILE_LOCATION);
    		 throw(e);
    	 }*/
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
    	 }else{
    		 String tempData = sourceStaticDataAccounts.getProperty(inputData);
    		 int separatorIndex = tempData.trim().indexOf("|");
    		 String mappedData = tempData.substring(0, separatorIndex);
    		 String urlDataInFile = tempData.substring(separatorIndex +1);
    		 //String keyData = "CustomerId";
    		 try {
    			//String urlData =  AppConstants.STAGEING_URL.concat(urlDataInFile);
    			String msisdnNo =  prop.getProperty(AppConstants.COUNTRY_PREFIX).concat(
    								prop.getProperty(AppConstants.AccountsOverview_PREPAID_MSISDN)
    								);
    			System.out.println("msisdnNo :" +msisdnNo );
    			
    			// services/retrievesubscriber/msisdn/
    			// services/retrieveBillingInfo/msisdn/
    			// services/retrieveAccountBalance/msisdn/
    			
    			int indexofurlseprator=urlDataInFile.indexOf("/");
    			String tempkey=urlDataInFile.substring(indexofurlseprator+1);
    			String tokentohiturl=tempkey.substring(0,tempkey.indexOf("/"));
    			
    			if(tokentohiturl.equals("retrievesubscriber")) {
    				
    			String jsonResult =  getOnlineServices().execute(
    											 "RETRIEVE_SUBSCRIBER_MSISDN",
    											 AppConstants.STAGEING_URL, new Object[] {
    													 msisdnNo, "1"},new String[]{"",msisdnNo});
    			
    			System.out.println("jsonResult :-->"+ jsonResult);
    			Object resultFound = searchInJSONResult(jsonResult, mappedData);
    			
    			returnData = resultFound.toString();
    			System.out.println("Result Found : "+ returnData);
    			}else if(tokentohiturl.equals("retrieveBillingInfo")) {
    				
    				String jsonResult =  getOnlineServices().execute(
							 "RETRIEVE_BILLING_INFO_USING_MSISDN",
							 AppConstants.STAGEING_URL, new Object[] {
									 msisdnNo, "1"},new String[]{"",msisdnNo});

    				System.out.println("jsonResult :-->"+ jsonResult);
    				Object resultFound = searchInJSONResult(jsonResult, mappedData);

    				returnData = resultFound.toString();
    				System.out.println("Result Found : "+ returnData);
    				
    			}else {
    				String jsonResult =  getOnlineServices().execute(
							 "RETRIEVE_ACCOUNT_BALANCE_BY_MSISDN",
							 AppConstants.STAGEING_URL, new Object[] {
									 msisdnNo, "1"},new String[]{"",msisdnNo});

   				System.out.println("jsonResult :-->"+ jsonResult);
   				Object resultFound = searchInJSONResult(jsonResult, mappedData);

   				returnData = resultFound.toString();
   				System.out.println("Result Found : "+ returnData);
    			}
    		} catch (ClientProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
 
    	 }
    	 
    	 return returnData;
     }
	  
     /* Description: Testing the Account No
      * Parameters: None
      * Return: None
      */            
             protected void targetaccountno() {
            	 		//WebElement accnoelement=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[1]/div[1]/p", 5);
            	 		WebElement accnoelement=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[1]/div[1]/p", 5);
                        actualaccountno=accnoelement.getText(); 
            	        log.info("Success : To validate the target Account no: "+actualaccountno);
            	        System.out.println("actualaccountno : "+actualaccountno );  
            	        
             }

	public String getActualaccountno() {
		return actualaccountno;
	}
     
	/* Description: Testing the Account Name
      * Parameters: None
      * Return: None
      */            
             protected void targetaccountname(){
            	 		WebElement accnnameelement=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[1]/div[1]/h3", 5);
            	 		actualaccountname=accnnameelement.getText();  
            	        log.info("Success : To validate the target Account Name: "+actualaccountname);
            	        System.out.println("actualaccountname : "+actualaccountname  ); 
             }

 	public String getActualaccountname() {
 		return actualaccountname;
 	}

 	/* Description: Testing the Balance Amount
      * Parameters: None
      * Return: None
      */            
             protected void targetbalance(){
            	 WebElement balanceelement=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[1]/div[3]/p[1]/strong", 5);
            	 actualbalance=balanceelement.getText();  
            	 log.info("Success : To validate the target Balance: "+actualbalance);
            	 System.out.println("actualbalance : "+actualbalance  );  
                                     
             }

 	public String getActualbalance() {
 		return actualbalance;
 	}
 	
 	/* Description: Testing the Expiry Date
      * Parameters: None
      * Return: None
      */            
             protected void targetexpiry(){
            	 WebElement expieryelement=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[1]/div[3]/p[2]", 5);
            	 actualexpiry=expieryelement.getText();   
            	        log.info("Success : To validate the target Expiery: "+actualexpiry);
            	        System.out.println("actualexpiry : "+actualexpiry  );  
                                      
             }

 	public String getActualexpiry() {
 		return actualexpiry;
 	}
 	
 	/* Description: Testing the Plan Name
      * Parameters: None
      * Return: None
      */            
             protected void targetplanname(){
            	 WebElement plananameelement=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[2]/div/div[1]/div[1]/p", 5);
            	 actualplanname=plananameelement.getText();    
            	        log.info("Success : To validate the target Plan Name: "+actualplanname);
            	        System.out.println("actualplanname : "+actualplanname );                         
             }

 	public String getActualplanname() {
 		return actualplanname;
 	}
 	
 	/* Description: Testing the MSISDN
      * Parameters: None
      * Return: None
      */            
             protected void targetmsisdn(){
            	 WebElement msisdnelement=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[2]/div/div[1]/div[1]/h3", 5);
            	 actualmsisdn=msisdnelement.getText();   
            	        log.info("Success : To validate the target MSISDN: "+actualmsisdn);
            	        System.out.println("actualmsisdn : "+actualmsisdn );  
                            
             }

 	public String getActualmsisdn() {
 		return actualmsisdn;
 	}
 	
 	/* Description: Testing the Status
      * Parameters: None
      * Return: None
      */            
             protected void targetstatus(){
            	 WebElement statuselement=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[2]/div/div[1]/div[2]/div", 5);
            	 actualstatus=statuselement.getText();    
            	        log.info("Success : To validate the target Status: "+actualstatus);
            	        System.out.println("actualstatus : "+actualstatus );  
                                    
             }

 	public String getActualstatus() {
 		return actualstatus;
 	}
 	
 	/* Description: Testing the MSISDN on Payment Page
     * Parameters: None
     * Return: None
     */ 
 	
 	protected void targetMsisdnForPaytment() {
 		 WebElement msisdbforpaymentelement=tryToGetElementByXPath("//*[@id='mainContent']/div/div/div/div/form/table/tbody/tr[1]/td[2]/span", 5);
 		actualmsisdn=msisdbforpaymentelement.getText();   
    	        log.info("Success : To validate the MSISDN on Payment Page: "+actualmsisdn);
    	        System.out.println("actualmsisdn : "+actualmsisdn );  
                 
 	}
 	
 	/* Description: Testing the air time amount on Payment Page
     * Parameters: None
     * Return: None
     */ 
 	
 	protected void targetAirTimeAmtForPayment() {
 		 WebElement airtimeamtelement=tryToGetElementByXPath("//*[@id='mainContent']/div/div/div/div/form/table/tbody/tr[2]/td[2]", 5);
 		airtimeamt=airtimeamtelement.getText(); 
    	        log.info("Success : To validate the Air time amount on Payment Page: "+airtimeamt);
    	        System.out.println("airtimeamt : "+airtimeamt );  
             
 	}

	public String getAirtimeamt() {
		return airtimeamt;
	}
 
	/* Description: Testing the total amount on Payment Page
     * Parameters: None
     * Return: None
     */ 
 	
 	protected void targetTotalAmtForPayment() {
 		WebElement totalamtelement=tryToGetElementByXPath("//*[@id='mainContent']/div/div/div/div/form/table/tbody/tr[3]/td[2]", 5);
 		totalamt=totalamtelement.getText();  
    	        log.info("Success : To validate the Total amount on Payment Page: "+totalamt);
    	        System.out.println("totalamt : "+totalamt );  
            
 	}

	public String getTotalamt() {
		return totalamt;
	}
 	
	/* Description: Testing the final amount on final Payment Page
     * Parameters: None
     * Return: None
     */ 
 	
 	protected void targetFinalAmt() {
 		WebElement finalamtelement=tryToGetElementByXPath("//*[@id='content']/div/div/div[1]/div[9]/div[2]/strong", 5);
 		finalamt=finalamtelement.getText();   
    	        log.info("Success : To validate final amount on Payment Page: "+finalamt);
    	        System.out.println("finalamt : "+finalamt );  
               
 	}

	public String getFinalamt() {
		return finalamt;
	}

	

	/* Description: Testing the transaction number after payment is cancelled
     * Parameters: None
     * Return: None
     */ 
	
	protected void targetValidateTransactionNo() {
		WebElement transnoelement=tryToGetElementByXPath("//*[@id='mainContent']/div/div/div/div/div[1]/strong", 5);
		transactionno=transnoelement.getText();    
    	        log.info("Success : To validate Transaction Number: "+transactionno);
    	        System.out.println("transactionno : "+transactionno );  
               
	}
 	
	public String getTransactionno() {
		return transactionno;
	}
	
	/* Description: Testing the Error message in case user select RM 25 Broadband in case of prepaid non broadband account.
     * Parameters: None
     * Return: None
     */ 
	protected void targetErrorMessageForBroadbandPayment() {
		WebElement msg=tryToGetElementByXPath("//*[@id='error_message']", 5);
		errormsg=msg.getText();   
	}

	public String getErrormsg() {
		return errormsg;
	}
	
	
}
	