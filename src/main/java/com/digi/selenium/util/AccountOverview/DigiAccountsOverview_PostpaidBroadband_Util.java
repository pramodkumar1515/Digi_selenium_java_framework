package com.digi.selenium.util.AccountOverview;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.common.PageNavigation;

public class DigiAccountsOverview_PostpaidBroadband_Util extends PageNavigation {
	
	protected  Logger log = Logger.getLogger(DigiAccountsOverview_PostpaidBroadband_Util.class);
	
	//static or dynamic data flag
	private  Properties sourceDataStatusAccounts = new Properties();

	//static data 
	private  Properties sourceStaticDataAccounts = new Properties();
	
	private String  actualaccountno = new String();
	private String  actualaccountname = new String();
	private String  actualunbilledamount = new String();
	private String  actualbilledamount = new String();
	private String  actualplanname = new String();
	private String  actualmsisdn = new String();
	private String  actualstatus = new String();
	private String  actualbilledvalue = new String();
	private String  actualcredit = new String();
	private String  actualtotal = new String();
	private String  actualduration = new String();
	private String rmvalue="";
	private String orderid="";
	

	protected  void setUp(){
		loadSetUpConfig();
		setUpDriver();
		signInWithMSSIDN_stage(PageNavigation.prop.getProperty(AppConstants.COUNTRY_PREFIX),
						 PageNavigation.prop.getProperty(AppConstants.AccountsOverview_POSTPAIDBROADBAND_MSISDN),
						 PageNavigation.prop.getProperty(AppConstants.AccountsOverview_POSTPAIDBROADBAND_PASS));
		movetoAccountsOverviewPostpaidBroadband();
		readSourceDataFiles();
	}

	protected void movetoAccountsOverviewPostpaidBroadband()
	{
	    waitForPageLoad(60);
	    handleLink_ByText("MyDigi");
	    waitForPageLoad(30);
		//handleLink_ByText("Accounts & Plans");
		waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div[13]/div/div"))); 
	    getDriver().findElement(By.xpath("//*[@id='content']/div[13]/div/div/div[2]/button[1]")).click();
	    waitForPageLoad(30);
	}
	
	/**
 	 * Reads the Bills n Reload page source data
 	 * to get the dynamic/static data
 	 */
     private void readSourceDataFiles(){
    	String statusFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
    											   .concat(AppConstants.DIGIACCOUNTSOVERVIEW_POSTPAIDBROADBAND_DATASTATUSFILE_LOCATION);
    	sourceDataStatusAccounts = loadFileData(statusFileName);
    	
    	String dataFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
												 .concat(AppConstants.DIGIACCOUNTSOVERVIEW_POSTPAIDBROADBAND_STATICDATAFILE_LOCATION);
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
    	 }else{
    		 String tempData = sourceStaticDataAccounts.getProperty(inputData);
    		 int separatorIndex = tempData.trim().indexOf("|");
    		 String mappedData = tempData.substring(0, separatorIndex);
    		 String urlDataInFile = tempData.substring(separatorIndex +1);
    		 //String keyData = "CustomerId";
    		 try {
    			//String urlData =  AppConstants.STAGEING_URL.concat(urlDataInFile);
    			String msisdnNo =  prop.getProperty(AppConstants.COUNTRY_PREFIX).concat(
    								prop.getProperty(AppConstants.AccountsOverview_POSTPAIDBROADBAND_MSISDN)
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
	
	/**
	 * Description: To read tooltip value from each bar
	 * Parameters: Not required
	 * Return: A list of string having  tooltip amounts 
	 */	
     /* Description: Testing the Account No
      * Parameters: None
      * Return: None
      */            
             protected void targetaccountno(){
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
    	        System.out.println("actualaccountname : "+actualaccountname);             
            }

	public String getActualaccountname() {
		return actualaccountname;
	}

	/* Description: Testing the Unbilled Amount
     * Parameters: None
     * Return: None
     */            
            protected void targetunbilledamount(){
            	WebElement unbilledamount=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[1]/div[3]/p[1]/strong", 5);
            	actualunbilledamount=unbilledamount.getText();  
    	        log.info("Success : To validate the target Unbilled Amount: "+actualunbilledamount);
    	        System.out.println("actualunbilledamount : "+actualunbilledamount  );              
            }

	public String getActualunbilledamount() {
		return actualunbilledamount;
	}
	
	/* Description: Testing the Billed Amount
     * Parameters: None
     * Return: None
     */            
            protected void targetbilledamount(){
            	WebElement billedamount=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[1]/div[3]/p[2]/strong", 5);
            	actualbilledamount=billedamount.getText();  
    	        log.info("Success : To validate the target Billed Amount: "+actualbilledamount);
    	        System.out.println("actualbilledamount : "+actualbilledamount  );             
            }

	public String getActualbilledamount() {
		return actualbilledamount;
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
	
	/* Description: Testing the Billed Value
     * Parameters: None
     * Return: None
     */            
            protected void targetbilledvalue(){
            	 WebElement billedvalue=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[2]/div/div[3]/div[1]/p", 5);
            	 actualbilledvalue=billedvalue.getText();    
            	        log.info("Success : To validate the target Billed Value: "+actualbilledvalue);
            	        System.out.println("actualbilledvalue : "+actualbilledvalue );               
            }

	public String getActualbilledvalue() {
		return actualbilledvalue;
	}
	
	/* Description: Testing the Credit Limit
     * Parameters: None
     * Return: None
     */            
            protected void targetcredit(){
            	WebElement credit=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[2]/div/div[3]/div[2]/p", 5);
           	 actualcredit=credit.getText();    
           	        log.info("Success : To validate the target Credit: "+actualcredit);
           	        System.out.println("actualcredit : "+actualcredit );       
            }

	public String getActualcredit() {
		return actualcredit;
	}
	
	/* Description: Testing the Total Value
     * Parameters: None
     * Return: None
     */            
            protected void targettotal(){
            	 WebElement total=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[1]/div[4]/p[2]/strong", 5);
            	 actualtotal=total.getText();    
            	        log.info("Success : To validate the target Total: "+actualtotal);
            	        System.out.println("actualtotal : "+actualtotal );  
            }

	public String getActualtotal() {
		return actualtotal;
	}
	
	/* Description: Testing the Duration
     * Parameters: None
     * Return: None
     */            
            protected void targetduration(){
            	WebElement duration=tryToGetElementByXPath("//*[@id='planDetails']/div[2]/div[2]/div/div[3]/div[5]/p/span", 5);
           	 actualduration=duration.getText();    
           	        log.info("Success : To validate the target Duration: "+actualduration);
           	        System.out.println("actualduration : "+actualduration );     
            }

	public String getActualduration() {
		return actualduration;
	}
     
	/* Description: Testing the RM Value of Payment page
     * Parameters: None
     * Return: None
     */  
	
	protected void targetRMValueonPaymentPage() {
		//*[@id="content"]/div/div/div[1]/div[9]/div[2]/strong
		rmvalue=tryToGetElementByXPath("//*[@id='content']/div/div/div[1]/div[9]/div[2]/strong", 5).getText();
		log.info("Success : To validate the target RM Amount on Payment Page: "+rmvalue);
		System.out.println("Actual RM Value on Payment Page: "+rmvalue);
	}

	public String getRmvalue() {
		return rmvalue;
	}
	
	/* Description: Testing the order id not to be blank when payment is failed
     * Parameters: None
     * Return: None
     */ 
	
	protected void targetOrderId() {
		// try {
					//*[@id="content"]/div[2]/p[2]
					orderid=tryToGetElementByXPath("//*[@id='content']/div[2]/p[2]", 5).getText();
					log.info("Success : To validate the target Order ID: "+rmvalue);
					System.out.println("Order id :"+orderid);
				/*}catch(Exception e) {
					log.error("Fail : To validate the order id:");
				}*/
	}

	public String getOrderid() {
		return orderid;
	}
	
	
}
