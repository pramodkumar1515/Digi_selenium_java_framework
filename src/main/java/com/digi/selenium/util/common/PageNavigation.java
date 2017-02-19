package com.digi.selenium.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class PageNavigation {
	
	public static Properties prop = new Properties();
	
	private  Wait<WebDriver> wait = null;
	
	private  WebDriver driver = null;
	
	private  Logger log = Logger.getLogger(PageNavigation.class);
	
	private static File setupConfigFile = null;
	
	private static FileInputStream fileInput = null;
	
	private  String DRIVER_CLASS = null;
	
	private Properties fileData = new Properties();
	
	private OnlineServices onlineServices = new OnlineServices();
	
	private static WebDriver ssWebSriver= null;
	
	protected  void setUp(){
		loadSetUpConfig();
		setUpDriver();
	}
	//Load the SetupConfig.properties File
	protected   void loadSetUpConfig(){
		
		if(setupConfigFile == null || fileInput == null){
			setupConfigFile = new File(AppConstants.SETUP_CONFIGFILE_LOCATION);
			  
			try {
				fileInput = new FileInputStream(setupConfigFile);
				System.out.println("Info : loading SetupConfig File ");
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
				log.error("Fail: loadSetUpConfig(): Config file not found to initialize the Selenium Framework");
				System.exit(0);
			}
		}else{
			System.out.println("Info : SetupConfig File is already loaded");
			    log.info("Info : SetupConfig File is already loaded");
		}
		//Properties prop = new Properties();
		
		//load properties file
		try {
			if(prop.isEmpty()){
				prop.load(fileInput);
				System.out.println("SetUp Data initialized" );
			}
				loadLogger();
			log.info("SetUp Data initialized: " + prop);
			//System.out.println("SetUp Data : " + prop); //TBD comment or removed
		} catch (IOException e) {
			//e.printStackTrace();
			//System.out.println("Fail : Cann't Load the  SetuUp Config file");
			log.error("Fail : Cann't Load the  SetuUp Config file...exiting the program");
			//e.printStackTrace();
			System.exit(0);
		}

	}
	//load the logger
	private  void  loadLogger(){
		Properties logProperties = new Properties();
		
		try
	    {
	      // load our log4j properties / configuration file
		  String logfile = prop.getProperty("LOG_FILE") ;
	      logProperties.load(new FileInputStream(prop.getProperty("LOG_FILE")));
	      PropertyConfigurator.configure(logProperties);
	      log.info("Logging initialized.");
	    }
	    catch(IOException e)
	    {
	      //throw new RuntimeException("Unable to load logging File " + prop.getProperty("LOG_FILE"));
	      log.error("Unable to load logging File " + prop.getProperty("LOG_FILE"));
	    }
	}
	
	protected  void setUpDriver(){

			//load the configuration file
			loadSetUpConfig();
			
			log.info("DriverClass : "+DRIVER_CLASS);

			DRIVER_CLASS = prop.getProperty("DRIVER_CLASS");
			String DRIVER_LOC = prop.getProperty("DRIVER_LOCATION");
			//try{
				System.setProperty(DRIVER_CLASS, DRIVER_LOC);
				
				/*System.out.println("DRIVER_CLASS : " +DRIVER_CLASS );
				System.out.println("DRIVER_LOC : " +DRIVER_LOC );
				*/
				
				driver = new ChromeDriver();
				
				// Put a Implicit wait, this means that any search for elements on the page
				waitForPageLoad(30);
				//maximize the browser window
				driver.manage().window().maximize();
				
				//set the driver for Listerner class - captures the ScreenShot
				setSsWebSriver(getDriver());
				
				log.info("Success : Driver Initialized Successfully");
			}
			/*catch (Exception e) {
				//e.printStackTrace();
				//System.out.println("Fail : Driver Initialization Failed");
				log.error("Fail : Driver to Initialize the browser is Failed...exiting the program"); 
				//e.printStackTrace();
				System.exit(0);
			}*/
	//}
	
	/**
	 * Test the Page URL
	 * @param pageURL
	 * @throws Exception 
	 */
	protected  void loadPage(String pageURL) {
		//try{
			getDriver().get(pageURL);
		log.info("Success : Page '"+ prop.getProperty(pageURL) + "' loaded successfully");
		/*}catch(Exception e) {
			//System.out.println("Fail : Load to Page '"+prop.getProperty("PAGE_URL") +"' Failed");
			log.error("Fail : Load to Page '"+prop.getProperty("PAGE_URL") +"' Failed ....exiting the program");
			//e.printStackTrace();
			//System.exit(0);
			throw(e);
		}*/
	}
	
	/**
	 * load the property file
	 * @param String filename
	 * @return property file 
	 * @throws Exception 
	 */
	protected Properties loadFileData(String  fileName) {
		Properties propertyFile = new Properties();
		
		   File sourceDataFile = null;
    	   FileInputStream sourceDataFileInput = null;

    	   if(sourceDataFile == null  ){
    		   sourceDataFile = new File(fileName);
   			try {
   				sourceDataFileInput = new FileInputStream(sourceDataFile);
   				propertyFile.load(sourceDataFileInput);
   				log.info("Success : Source data file  " + propertyFile + " loaded");
   				System.out.println("Success : Source data file  " + propertyFile + " loaded");
   				} catch (Exception e) {
	    				//e.printStackTrace();
   					log.error("Fail : Cann't Load the  Source Property file "+fileName +" ...exiting the program");
	    			shutDown();
   				}
   			} 

   		return propertyFile;
	}
	
	/**
	 * Close the driver
	 */
	protected  void shutDown(){
		try{
		//Thread.sleep(20);
		getDriver().quit();
		//getSsWebSriver().quit(); //quit the listener driver
		//System.clearProperty(DRIVER_CLASS);
		log.info("Info : Browser Driver closed successfully ");
		}catch(Exception e) {
			log.error("Fail : Browser Driver Didn't close properly ....exiting the program");
			//e.printStackTrace();
			System.exit(0);
		}
	}
	
	protected void paymentPostpaidBroadband() throws InterruptedException{
		// try{
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		jse.executeScript("window.scrollBy(0,-500)", "");
		//*[@id="planDetails"]/div[2]/div[1]/div[6]/button
		WebElement pay = tryToClickElementByXPath("//*[@id='planDetails']/div[2]/div[1]/div[6]/button", 5);
		pay.click();
		waitForPageLoad(30);
		log.info("Success: Make A Bill Payment button is clicked");
		Thread.sleep(2000);
		JavascriptExecutor jse1 = (JavascriptExecutor)getDriver();
		jse1.executeScript("window.scrollBy(0,300)", "");
		//*[@id="rp-form-field-payamount"]
		driver.findElement(By.id("rp-form-field-payamount")).clear();
		handleInputField_ByID("rp-form-field-payamount", AppConstants.RMVALUE);
		
		//Thread.sleep(500);
		//*[@id="proceed-button"]/span[1]
		WebElement payment = tryToClickElementByXPath("//*[@id='proceed-button']/span[1]", 5);
		payment.click();
		waitForPageLoad(20);
		Thread.sleep(2500);
		//WebElement payment2 = tryToClickElementByXPath("//*[@id='proceed-button']/span[1]", 5);
		payment.click();
		waitForPageLoad(30);
		Thread.sleep(2500);
				/*}
				catch(Exception e) {
					log.error("Fail : Failed to initialize Payment");
					e.printStackTrace();
					System.exit(0);
				}*/
	}

	protected void paymentPostpaid() throws InterruptedException{
		// try{
			JavascriptExecutor jse = (JavascriptExecutor)getDriver();
			jse.executeScript("window.scrollBy(0,-500)", "");
			//*[@id="planDetails"]/div[2]/div[1]/div[6]/button
			WebElement pay = tryToClickElementByXPath("//*[@id='planDetails']/div[2]/div[1]/div[6]/button", 5);
			pay.click();
			waitForPageLoad(30);
			log.info("Success: Make A Bill Payment button is clicked");
			Thread.sleep(2000);
			JavascriptExecutor jse1 = (JavascriptExecutor)getDriver();
			jse1.executeScript("window.scrollBy(0,300)", "");
			//*[@id="rp-form-field-payamount"]
			driver.findElement(By.id("rp-form-field-payamount")).clear();
			handleInputField_ByID("rp-form-field-payamount", AppConstants.RMVALUE);
			
			//Thread.sleep(500);
			//*[@id="proceed-button"]/span[1]
			WebElement payment = tryToClickElementByXPath("//*[@id='proceed-button']/span[1]", 5);
			payment.click();
			waitForPageLoad(20);
			Thread.sleep(2500);
			//WebElement payment2 = tryToClickElementByXPath("//*[@id='proceed-button']/span[1]", 5);
			payment.click();
			waitForPageLoad(30);
			Thread.sleep(2500);
		/*}
		catch(Exception e) {
			log.error("Fail : Failed to initialize Payment");
			e.printStackTrace();
			System.exit(0);
		}*/
		
	}
	
	
	protected void cancelPaymentAndReturn() {
		// try {			
			//Back to Accounts & Plans
			JavascriptExecutor jse1 = (JavascriptExecutor)getDriver();
			jse1.executeScript("window.scrollBy(0,500)", "");
			WebElement cancel = tryToClickElementByXPath("//*[@id='pay-getaway-foot']/div[2]/button[3]",5);
			cancel.click();
			waitForPageLoad(5);
			getDriver().switchTo().alert().accept();
			waitForPageLoad(30);
	
	}
	
	protected void returnAccountsPage() throws InterruptedException {
		tryToClickElementByXPath("//*[@id='menu-section']/div/ul/li[5]/a",5).click(); 
        waitForPageLoad(100);
    	Thread.sleep(500);
        waitForPageLoad(60);
       Thread.sleep(500);
   	    log.info("Success : Returned back on accounts and payments page ");   
   	    waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div[13]/div/div"))); 
 	    getDriver().findElement(By.xpath("//*[@id='content']/div[13]/div/div/div[2]/button[1]")).click();
 	    waitForPageLoad(30);
	}

	protected void clickOnRadio10() throws InterruptedException {
		// try {
					WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
					System.out.println(emailtxt.isEnabled());
					if(emailtxt.isEnabled()) {
					handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
					//Thread.sleep(500);
					}
					JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
					js1.executeScript("window.scrollBy(0,400)", "");
					
					 
		             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/ul/li[2]/label/div",5).click(); 
		        	 log.info("Success : Radio Button of amonut 10 is clicked");          
		                	
		             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/label/div",5).click(); 
		        	 log.info("Success : checkbox is clicked");  
		                   
		        	 tryToClickElementByXPath("//*[@id='proceedBtn']",5).click(); 
		        	 log.info("Success : Proceed Button is clicked");     	
		     
					waitForPageLoad(100);
					Thread.sleep(500);
					JavascriptExecutor js = (JavascriptExecutor)getDriver();
					js.executeScript("window.scrollBy(0,500)", "");
				/*}catch(Exception e) {
					log.error("Fail : Failed to process reload of amount 10.");
					shutDown();
					
				}*/
	}
	
	protected void clickOnRadio30() throws InterruptedException {
		// try {
					WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
					System.out.println(emailtxt.isEnabled());
					if(emailtxt.isEnabled()) {
					handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
					//Thread.sleep(500);
					}
					JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
					js1.executeScript("window.scrollBy(0,400)", "");
					
					 
		             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/ul/li[3]/label/div",5).click(); 
		        	 log.info("Success : Radio Button of amonut 30 is clicked");          
		                	
		             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/label/div",5).click(); 
		        	 log.info("Success : checkbox is clicked");  
		                   
		        	 tryToClickElementByXPath("//*[@id='proceedBtn']",5).click(); 
		        	 log.info("Success : Proceed Button is clicked");     	
		           
					waitForPageLoad(100);
					Thread.sleep(500);
					JavascriptExecutor js = (JavascriptExecutor)getDriver();
					js.executeScript("window.scrollBy(0,500)", "");
				/*}catch(Exception e) {
					log.error("Fail : Failed to process reload of amount 30.");
					shutDown();
					
				}*/
	}
	
	protected void clickOnRadio50() throws InterruptedException {
		// try {
					WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
					System.out.println(emailtxt.isEnabled());
					if(emailtxt.isEnabled()) {
					handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
					//Thread.sleep(500);
					}
					JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
					js1.executeScript("window.scrollBy(0,400)", "");
					
					 
		             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/ul/li[4]/label/div",5).click(); 
		        	 log.info("Success : Radio Button of amonut 30 is clicked");          
		                	
		             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/label/div",5).click(); 
		        	 log.info("Success : checkbox is clicked");  
		                   
		        	 tryToClickElementByXPath("//*[@id='proceedBtn']",5).click(); 
		        	 log.info("Success : Proceed Button is clicked");     	
		           
					waitForPageLoad(100);
					Thread.sleep(500);
					JavascriptExecutor js = (JavascriptExecutor)getDriver();
					js.executeScript("window.scrollBy(0,500)", "");
				/*}catch(Exception e) {
					log.error("Fail : Failed to process reload of amount 30.");
					shutDown();
					
				}*/	
	}
	
	protected void clickOnRadio100() throws InterruptedException {
		// try {
					WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
					System.out.println(emailtxt.isEnabled());
					if(emailtxt.isEnabled()) {
					handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
					//Thread.sleep(500);
					}
					JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
					js1.executeScript("window.scrollBy(0,400)", "");
					
					 
		             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/ul/li[1]/label/div",5).click(); 
		        	 log.info("Success : Radio Button of amonut 100 is clicked");              	
		             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/label/div",5).click(); 
		        	 log.info("Success : checkbox is clicked");  
		                   
		        	 tryToClickElementByXPath("//*[@id='proceedBtn']",5).click(); 
		        	 log.info("Success : Proceed Button is clicked");     	
		            /*    	
					WebElement proceed=(new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceedBtn']")));
					proceed.click();*/
					waitForPageLoad(100);
					Thread.sleep(500);
					JavascriptExecutor js = (JavascriptExecutor)getDriver();
					js.executeScript("window.scrollBy(0,500)", "");
				/*}catch(Exception e) {
					log.error("Fail : Failed to process reload of amount 100.");
					shutDown();
					
				}*/
	}
	
	// To reload button in case of prepaid broad band
	
	protected void reloadBroadband() throws InterruptedException {
		WebElement reload=tryToClickElementByXPath("//*[@id='planDetails']/div[2]/div[1]/div[5]/a",5);
		reload.click();
		waitForPageLoad(30);
		log.info("Success: Reload button is clicked");
		Thread.sleep(2000);
 	}
	
	// To handle Reload Button in case of prepaid
	
	protected void reload1() throws InterruptedException {
		//*[@id="planDetails"]/div[2]/div[1]/div[4]/a
		//*[@id="planDetails"]/div[2]/div[1]/div[5]/a
			WebElement reload=tryToClickElementByXPath("//*[@id='planDetails']/div[2]/div[1]/div[5]/a",5);
			reload.click();
			waitForPageLoad(30);
			log.info("Success: Reload button is clicked");
			Thread.sleep(2000);
	}
	
	protected void clickOnRadioRM10() throws InterruptedException {
		// try {
			WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
			System.out.println(emailtxt.isEnabled());
			if(emailtxt.isEnabled()) {
			handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
			//Thread.sleep(500);
			}
			JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
			js1.executeScript("window.scrollBy(0,400)", "");
			
			 
             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/ul/li[2]/label/div",5).click(); 
        	 log.info("Success : Radio Button of amonut 10 is clicked");          
                	
             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/label/div",5).click(); 
        	 log.info("Success : checkbox is clicked");  
                   
        	 tryToClickElementByXPath("//*[@id='proceedBtn']",5).click(); 
        	 log.info("Success : Proceed Button is clicked");     	
     
			waitForPageLoad(100);
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor)getDriver();
			js.executeScript("window.scrollBy(0,500)", "");
		/*}catch(Exception e) {
			log.error("Fail : Failed to process reload of amount 10.");
			shutDown();
			
		}*/
               
            
	}
	
	protected void clickOnPay() {
		
        WebElement pay=tryToClickElementByXPath("//*[@id='payBtn']",5); 
        Actions move_action=new Actions(getDriver());
		move_action.moveToElement(pay).click().perform();
        waitForPageLoad(30);
    	log.info("Success : Pay Button is clicked");  
    	//*[@id="payBtn"]   
		/*try {
			//click on PAY Button
			//*[@id="payBtn"]
			// WebElement elementpay=getDriver().findElement(By.xpath("//*[@id='payBtn']"));
			WebElement pay=getDriver().findElement(By.xpath("//*[@id='payBtn']"));
			Actions move_action=new Actions(getDriver());
			move_action.moveToElement(pay).click().perform();
			//System.out.println(pay.isEnabled());
			pay.click();
			waitForPageLoad(100);
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
			log.error("Fail : Failed to Reload amount.");
			System.exit(0);
		}*/
		
	}
	
	protected void clickCancel() throws InterruptedException{
		
               	tryToClickElementByXPath("//*[@id='pay-getaway-foot']/div[2]/form/button[3]",5).click(); 
               	Thread.sleep(500);
               	driver.switchTo().alert().accept();
               	waitForPageLoad(60);
               	Thread.sleep(500);
   	            log.info("Success : Payment is cancelled ");  
       
   	            List<WebElement> element=getDriver().findElements(By.linkText("Back"));
   	            
			//boolean isPresent=getDriver().findElements(By.linkText("Back")).size()>0;
			if(element.size()>0) {
				WebElement cancelpopup=element.get(0);
			cancelpopup.click();
			}
			//driver.switchTo().window(parent);
			waitForPageLoad(30);
			Thread.sleep(500);
	}
	
	protected void backToAccountsAndPaymentsPage()  throws InterruptedException{
	        tryToClickElementByXPath("//*[@id='menu-section']/div/ul/li[5]/a",5).click(); 
	        waitForPageLoad(100);
	    	Thread.sleep(500);
	        waitForPageLoad(60);
	       Thread.sleep(500);
	   	    log.info("Success : Returned back on accounts and payments page ");   
	   	    waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div[13]/div/div"))); 
	 	    getDriver().findElement(By.xpath("//*[@id='content']/div[13]/div/div/div[2]/button[1]")).click();
	 	  //*[@id="telenor-cancel-btn"]
	 	    waitForPageLoad(30);
	 	    getDriver().findElement(By.xpath("//*[@id='telenor-cancel-btn']")).click();
	 	    waitForPageLoad(30);
	}
	
	protected void backToAccountsAndPaymentsPagePrepaidBroadband()  throws InterruptedException{
        tryToClickElementByXPath("//*[@id='menu-section']/div/ul/li[5]/a",5).click(); 
        waitForPageLoad(100);
    	Thread.sleep(500);
        waitForPageLoad(60);
       Thread.sleep(500);
   	    log.info("Success : Returned back on accounts and payments page ");   
   	    waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div[13]/div/div"))); 
 	    getDriver().findElement(By.xpath("//*[@id='content']/div[13]/div/div/div[2]/button[1]")).click();
 	  //*[@id="telenor-cancel-btn"]
 	    waitForPageLoad(30);
}
	
	
	
	protected void clickOnRadioRM30() throws InterruptedException {
		// try {
			WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
			System.out.println(emailtxt.isEnabled());
			if(emailtxt.isEnabled()) {
			handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
			//Thread.sleep(500);
			}
			JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
			js1.executeScript("window.scrollBy(0,400)", "");
			
			 
             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/ul/li[3]/label/div",5).click(); 
        	 log.info("Success : Radio Button of amonut 30 is clicked");          
                	
             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/label/div",5).click(); 
        	 log.info("Success : checkbox is clicked");  
                   
        	 tryToClickElementByXPath("//*[@id='proceedBtn']",5).click(); 
        	 log.info("Success : Proceed Button is clicked");     	
           
			waitForPageLoad(100);
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor)getDriver();
			js.executeScript("window.scrollBy(0,500)", "");
		/*}catch(Exception e) {
			log.error("Fail : Failed to process reload of amount 30.");
			shutDown();
			
		}*/
	}
	
	protected void clickOnRadioRM50() throws InterruptedException {
		// try {
		WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
		System.out.println(emailtxt.isEnabled());
		if(emailtxt.isEnabled()) {
		handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
		//Thread.sleep(500);
		}
		JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
		js1.executeScript("window.scrollBy(0,400)", "");
		
		 
         tryToGetElementByXPath("//*[@id='prepaidReloadForm']/ul/li[4]/label/div",5).click(); 
    	 log.info("Success : Radio Button of amonut 30 is clicked");          
            	
         tryToGetElementByXPath("//*[@id='prepaidReloadForm']/label/div",5).click(); 
    	 log.info("Success : checkbox is clicked");  
               
    	 tryToClickElementByXPath("//*[@id='proceedBtn']",5).click(); 
    	 log.info("Success : Proceed Button is clicked");     	
       
		waitForPageLoad(100);
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		js.executeScript("window.scrollBy(0,500)", "");
	/*}catch(Exception e) {
		log.error("Fail : Failed to process reload of amount 30.");
		shutDown();
		
	}*/	
	}
	
	protected void clickOnRadioRM100()throws InterruptedException {
		// try {
			WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
			System.out.println(emailtxt.isEnabled());
			if(emailtxt.isEnabled()) {
			handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
			//Thread.sleep(500);
			}
			JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
			js1.executeScript("window.scrollBy(0,400)", "");
			
			 
             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/ul/li[1]/label/div",5).click(); 
        	 log.info("Success : Radio Button of amonut 100 is clicked");              	
             tryToGetElementByXPath("//*[@id='prepaidReloadForm']/label/div",5).click(); 
        	 log.info("Success : checkbox is clicked");  
                   
        	 tryToClickElementByXPath("//*[@id='proceedBtn']",5).click(); 
        	 log.info("Success : Proceed Button is clicked");     	
            /*    	
			WebElement proceed=(new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceedBtn']")));
			proceed.click();*/
			waitForPageLoad(100);
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor)getDriver();
			js.executeScript("window.scrollBy(0,500)", "");
		/*}catch(Exception e) {
			log.error("Fail : Failed to process reload of amount 100.");
			shutDown();
			
		}*/

		
	}
	
	protected void clickOnRadioRM25Broadband() {
		try {
			
			WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
			if(emailtxt.isDisplayed()) {
			handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
			Thread.sleep(500);
			}
			JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
			js1.executeScript("window.scrollBy(0,400)", "");
			WebElement radio=getDriver().findElement(By.xpath("//*[@id='prepaidReloadForm']/ul/li[5]/label/div"));
			radio.click();
			// now select check box to accept license agreement
			WebElement checkbox=getDriver().findElement(By.xpath("//*[@id='prepaidReloadForm']/label/div"));
			checkbox.click();
			// click on proceed button after selecting radio button and check box
			// WebElement elementproceed=getDriver().findElement(By.xpath("//*[@id='proceedBtn']"));
			//*[@id="proceedBtn"]
			WebElement proceed=(new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceedBtn']")));
			proceed.click();
			waitForPageLoad(100);
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor)getDriver();
			js.executeScript("window.scrollBy(0,500)", "");
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
			log.error("Fail : Failed to Reload amount.");
			System.exit(0);
		}
		
	}
	
	protected void socialLinks() {
		
            try{
                     
                   JavascriptExecutor jse = (JavascriptExecutor)getDriver();
                   jse.executeScript("window.scrollBy(0,1200)", "");
//                 WebElement fb = (new WebDriverWait(fbdriver, 5000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main']/section/div/ul/li[1]/a")));
//           fb.click();
                   //*[@id="main"]/section/div/ul/li[1]/a
             for(int i=1;i<=3;i++){
             WebElement socialbtn =  getDriver().findElement(By.xpath("//*[@id='main']/section/div/ul/li["+i+"]/a"));
             Actions newTab1 = new Actions(getDriver());
             newTab1.keyDown(Keys.SHIFT).click(socialbtn).keyUp(Keys.SHIFT).build().perform();
             Thread.sleep(500);
              
             //handle windows change
             String base1 = getDriver().getWindowHandle();
             Set<String> set1 = getDriver().getWindowHandles();
              
             set1.remove(base1);
             assert set1.size() == 1;
             getDriver().switchTo().window((String) set1.toArray()[0]);
             waitForPageLoad(500);
             //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='blueBarNAXAnchor']/div/div/div/div[1]/h1/a/i")));
             //close the window
             getDriver().close();
             getDriver().switchTo().window(base1);
              
             // handle windows change and switch back to the main window
             Thread.sleep(500);
             for (String winHandle1 : getDriver().getWindowHandles()) {
                   getDriver().switchTo().window(winHandle1);
             
             System.out.println("Link Validation for :"+socialbtn.getText()+" --> Passed" );
             log.info("Link Validation for :"+socialbtn.getText()+" --> Passed");
             Thread.sleep(500);
             }
             }
             JavascriptExecutor jse1 = (JavascriptExecutor)getDriver();
             jse1.executeScript("window.scrollBy(0,-1200)", "");
            // jse1.executeScript("window.scrollTo(0,document.body.scrollHeight", "");
             Thread.sleep(500);
            }
             
            catch(Exception e){
                   log.error("Failed to verify Social Media Links shutting down");
                   shutDown();
            }
           

	}
	
	/**
	 * Page wait for specific time
	 * @param long wait time 
	 */
	protected  void waitForPageLoad(long waitTime){
		// try{
			getDriver().manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		/*}catch(Exception e) {
			//System.out.println("Fail : waitForPageLoad()  failed");
			log.error("Fail : waitForPageLoad()  failed");
		}*/
	}
	
	protected  Wait<WebDriver> waitgetForPageLoad(long waitTime){
		// try{
			wait = new WebDriverWait(driver, waitTime);
		/*}catch(Exception e) {
			//System.out.println("Fail : waitForPageLoad()  failed");
			log.error("Fail : waitgetForPageLoad()  failed");
		}*/
		return wait;
	}
	
	/**
	 * Handle input Textfield['Text'] on page by element ID
	 * pass the input data 
	 * @param Wedriver localWebDeriver  
	 * @param String elementID
	 * @param String inputData
	 * @throws Exception 
	 */
	
	protected  void handleClearInputField_ByName(String elementID) {
		 // try{
			
				getDriver().findElement(By.name(elementID)).clear();
				
		/*}catch(Exception e) {
			//System.out.println("Fail : Element ID '" + elementID + " Not Found");
			log.error("Fail : Element ID '" + elementID + " Not Found");
			throw(e);
		}*/
	}
	/**
	 * Handle input Textfield['Text'] on page by element ID
	 * pass the input data 
	 * @param Wedriver localWebDeriver  
	 * @param String elementID
	 * @param String inputData
	 */
	
	protected  void handleInputField_ByID(String elementID, String inputData){
		try{
			getDriver().findElement(By.id(elementID)).sendKeys(inputData);
		}catch(Exception e) {
			//System.out.println("Fail : Element ID '" + elementID + " Not Found");
			log.error("Fail : Element ID '" + elementID + " Not Found");
			//e.printStackTrace();
			//System.exit(0);
		}
	}
	
	/**
	 * Handle input Textfield['Text'] on page by element Name
	 * pass the input data 
	 * @param Wedriver localWebDeriver  
	 * @param String elementID
	 * @param String inputData
	 * @throws Exception 
	 */
	
	protected  void handleInputField_ByName(String elementName, String inputData) {
		// try{
			getDriver().findElement(By.name(elementName)).sendKeys(inputData);
		/*}catch(Exception e) {
			//System.out.println("Fail : Element '" + elementName + " Not Found");
			log.error("Fail : Element '" + elementName + " Not Found");
			throw(e);
			//e.printStackTrace();
			//System.exit(0);
		}*/
	}
	
	/**
	 * Handle the click action on button by button ID
	 * @param Wedriver webDeriver  
	 * @param String elementName
	 * @throws Exception 
	 * 
	 */
	
	protected  void handleButtonClick_ByID(String elementId) {
		// try{
			getDriver().findElement(By.id(elementId)).click();
		/*}catch(Exception e) {
			//System.out.println("Fail : Element ID '" + elementId + "' Click action Failed");
			log.error("Fail : Element ID '" + elementId + "' Click action Failed");
			throw(e);
			//e.printStackTrace();
			//System.exit(0);
		}*/
	}
	
	/**
	 * Handle the click action on button by button Name
	 * @param Wedriver webDeriver  
	 * @param String elementName
	 * 
	 */
	
	protected  void handleButtonClick_ByName(String elementName){
		try{
			getDriver().findElement(By.name(elementName)).click();
		}catch(Exception e) {
			//System.out.println("Fail : Element Name'" + elementName + "' Click action Failed");
			log.error("Fail : Element Name'" + elementName + "' Click action Failed");
		}
	}
	

	/**
	 * Handle hyperlink  on page by link text
	 * pass the input data 
	 * @param Wedriver localWebDeriver  
	 * @param String elementID
	 * @param String inputData
	 * @throws Exception 
	 */
	
	protected  void handleLink_ByText(String linkText) {
		// try{
			getDriver().findElement(By.linkText(linkText)).click();
		/*}
		catch(Exception e) {
			//System.out.println("Fail : Link Element '" + linkText + "' Click action Failed");
			log.error("Fail : Link Element '" + linkText + "' Click action Failed");
			throw(e);
		}*/
	}
	
	/**
	 * Get element on page by xpath  
	 * pass the input data 
	 * @param Wedriver localWebDeriver  
	 * @param String elementID
	 * @param String inputData
	 */
	
	protected  WebElement getWebElement_By_xpath(String xpathText){
		WebElement localWebElement = null;
		try{
			localWebElement = getDriver().findElement(By.xpath(xpathText));
		}catch(Exception e) {
			//System.out.println("Fail : Link Element '" + linkText + "' Click action Failed");
			log.error("Fail : Element Not found for xpath '" + xpathText );
		}
		return localWebElement;
	}
	
	protected void signInWithMSSIDN(String countryPrefix, String mssidn, String Password) 
	{
		// try{
			loadPage(AppConstants.DIGI_LOGIN_PAGE_PROD);
			waitForPageLoad(30);
			
			WebElement loginElement = getDriver().findElement(By.id("signIn"));
			loginElement.sendKeys(Keys.ENTER);
			waitForPageLoad(60);
			handleLink_ByText(AppConstants.DIGI_LOGIN_WITHPHONENUM);
			handleClearInputField_ByName("cc");
			handleInputField_ByName("cc", countryPrefix);
			handleInputField_ByName("number", mssidn);
			handleInputField_ByName("password", Password);
		    handleButtonClick_ByID("inst_button_msisdn-login_form_login");
		
		/*} catch(Exception e){
			log.error("Fail : "+e.toString());
			throw(e);
		}*/
	}
	
	protected void signInWithMSSIDN_stage(String countryPrefix, String mssidn, String Password) 
	{
		// try{
			loadPage(AppConstants.DIGI_LOGIN_PAGE_STAGE);
			waitForPageLoad(30);
			
			WebElement loginElement = getDriver().findElement(By.id("signIn"));
			loginElement.sendKeys(Keys.ENTER);
			waitForPageLoad(60);
			
			handleLink_ByText(AppConstants.DIGI_LOGIN_WITHPHONENUM);
			handleClearInputField_ByName("cc");
			handleInputField_ByName("cc", countryPrefix);
			handleInputField_ByName("number", mssidn);
			handleInputField_ByName("password", Password);
		    handleButtonClick_ByID("inst_button_msisdn-login_form_login");
		/*} catch(Exception e){
			log.error("Fail : "+e.toString());
			throw(e);
		}*/
	}
		
	/**
	 * Parse the JSON Result , search the finder data 
	 * @param jsonData  InputData  - json data
	 * @param keyData String - finder data
	 * @return
	 */
	protected  Object  searchInJSONResult(String jsonData, String keyData){
		  Object resultFound = null;
		  JSONParser parser = new JSONParser(); 
		  KeyFinder finder = new KeyFinder();  
		  finder.setMatchKey(keyData); 
		  try{   
			  while(!finder.isEnd()){  
				  parser.parse(jsonData, finder, true);  
				  if(finder.isFound()){        
					  finder.setFound(false);  
					  //System.out.println("found id:");   
					  //System.out.println(finder.getValue());  
					  resultFound = finder.getValue();
					  
					  return resultFound;
					  }   
				  }       
			  }  catch(ParseException pe){  
				  log.error("Fail : JSON Parse '" + jsonData + " ' finder Data => "+ keyData + "' ");
				  }
		  return resultFound;
	}
	
	/**
	 * get the webdriver
	 * @return
	 */
	protected  WebDriver getDriver() {
		return driver;
	}
	
	
	
	/**
	 * Get the Property file data
	 * @return Properties
	 */
	
	protected Properties getFileData() {
		return fileData;
	}
	
	
	/**
	 * Set the onlineservice
	 * @return
	 */
	
	protected OnlineServices getOnlineServices() {
		return onlineServices;
	}
	
	// method to check an element is displayed or not
	
	/*protected boolean isElementPresent(By by) {
		try {
	        driver.findElement(by);
	        return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}
	
	protected boolean checkIfDisplayed(By by) {
	    if (isElementPresent(by)) {
	    if (driver.findElement(by).isDisplayed()) {
	        return true;
	    } else
	        return false;
	    } else
	        return false;
	}*/
	
	protected WebElement tryToGetElementByXPath(String xpath,int maxtry) {
	 Wait<WebDriver> waittable= new WebDriverWait(getDriver(), 30); 
	 WebElement element=null;
	 int trycount=1;
 	boolean breakit;
        	while(trycount!=maxtry){
                breakit=true;
            try{
            element=waittable.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))); 
        }
       catch (Exception e) {
              breakit=false;
              trycount++;
    	   
        } 
            if(breakit==true)
            	break;
     }
        	return element;
	}
	
	protected WebElement tryToClickElementByXPath(String xpath,int maxtry) {
		 Wait<WebDriver> waittable= new WebDriverWait(getDriver(), 30); 
		 WebElement element=null;
		 int trycount=1;
	 	boolean breakit;
	        	while(trycount!=maxtry){
	                breakit=true;
	            try{
	            element=waittable.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))); 
	        }
	       catch (Exception e) {
	              breakit=false;
	              trycount++;
	    	   
	        } 
	            if(breakit==true)
	            	break;
	     }
	        	return element;
		}
	
	public static WebDriver getSsWebSriver() {
		return ssWebSriver;
	}
	public static void setSsWebSriver(WebDriver ssWebSriver) {
		PageNavigation.ssWebSriver = ssWebSriver;
	}
	
//	http://mdsstg.digi.com.my/digionline/services/retrievesubscriber/msisdn/0000

	public static void main(String[] args) {
	PageNavigation pu = new PageNavigation();
	//pu.setUpDriver();
	//Properties propload = pu.loadFileData(AppConstants.BILLSNRELOAD_DATASTATUSFILE_LOCATION);
	//System.out.println("Loaded Property File :"+ propload);
		//PageNavigation pu = new PageNavigation();
		OnlineServices onlineServices = pu.getOnlineServices();
		String keyData = "SubscriberId";
		 try {
			String jsonResult =  onlineServices.execute(
											 "RETRIEVE_SUBSCRIBER_MSISDN",
											AppConstants.STAGEING_URL, new Object[] {
											 "60109235782", "1"},new String[]{"","60109235782"});
			
			System.out.println("jsonResult :-->"+ jsonResult);
			Object resultFound = pu.searchInJSONResult(jsonResult, keyData);
			
			
			System.out.println("Result Found : "+ resultFound);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
