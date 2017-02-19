package com.digi.selenium.util;

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
				log.error("Fail : Config file not found to initialize the Selenium Framework");
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
			
			log.info("DriverClass : ");

			DRIVER_CLASS = prop.getProperty("DRIVER_CLASS");
			String DRIVER_LOC = prop.getProperty("DRIVER_LOCATION");
			//try{
				System.setProperty(DRIVER_CLASS, DRIVER_LOC);
				
				/*System.out.println("DRIVER_CLASS : " +DRIVER_CLASS );
				System.out.println("DRIVER_LOC : " +DRIVER_LOC );
				*/
				
				driver = new ChromeDriver();
				
				// Put a Implicit wait, this means that any search for elements on the page
				waitForPageLoad(60);
				
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
	 */
	protected  void loadPage(String pageURL){
		try{
			getDriver().get(pageURL);
		log.info("Success : Page '"+ prop.getProperty(pageURL) + "' loaded successfully");
		}catch(Exception e) {
			//System.out.println("Fail : Load to Page '"+prop.getProperty("PAGE_URL") +"' Failed");
			log.error("Fail : Load to Page '"+prop.getProperty("PAGE_URL") +"' Failed ....exiting the program");
			//e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * load the property file
	 * @param String filename
	 * @return property file 
	 */
	protected Properties loadFileData(String  fileName){
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
		getDriver().quit();
		//getSsWebSriver().quit(); //quit the listener driver
		//System.clearProperty(DRIVER_CLASS);
		log.info("Info : Browser Driver closed successfully ");
		}catch(Exception e) {
			log.error("Fail : Browser Driver Didn't close properly ....exiting the program");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	protected void paymentPostpaidBroadband(){
		try{
			JavascriptExecutor jse = (JavascriptExecutor)getDriver();
			jse.executeScript("window.scrollBy(0,-500)", "");
			//*[@id="planDetails"]/div[2]/div[1]/div[6]/button
			WebElement pay =  getDriver().findElement(By.xpath("//*[@id='planDetails']/div[2]/div[1]/div[6]/button"));
			pay.click();
			waitForPageLoad(100);
			
			//*[@id="rp-form-field-payamount"]
			driver.findElement(By.id("rp-form-field-payamount")).clear();
			handleInputField_ByID("rp-form-field-payamount", AppConstants.RMVALUE);
			JavascriptExecutor jse1 = (JavascriptExecutor)getDriver();
			jse1.executeScript("window.scrollBy(0,500)", "");
			//*[@id="proceed-button"]/span[1]
			WebElement payment = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceed-button']/span[1]")));
			payment.click();
			waitForPageLoad(20);
			Thread.sleep(500);
			WebElement payment2 = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceed-button']/span[1]")));
			payment2.click();
			waitForPageLoad(100);
		}
		catch(Exception e) {
			log.error("Fail : Failed to initialize Payment");
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	protected void cancelPaymentPageandReturn() {
		try {			
			//Back to Accounts & Plans
			JavascriptExecutor jse1 = (JavascriptExecutor)getDriver();
			jse1.executeScript("window.scrollBy(0,500)", "");
			//*[@id="pay-getaway-foot"]/div[2]/button[3]
			WebElement cancel = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='pay-getaway-foot']/div[2]/button[3]")));
			cancel.click();
			Thread.sleep(500);
			getDriver().switchTo().alert().accept();
			waitForPageLoad(100);
		}catch(Exception e) {
			log.error("Fail : Failed to initialize Payment");
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	protected void returnOnAccountsPage() {
		try {
			WebElement back = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='menu-section']/div/ul/li[5]/a")));
			back.click();
		}catch(Exception e) {
			log.error("Fail : Failed to initialize Payment");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	
	protected void paymentPostpaid(){
		try{
			JavascriptExecutor jse = (JavascriptExecutor)getDriver();
			jse.executeScript("window.scrollBy(0,-500)", "");
			//*[@id="planDetails"]/div[2]/div[1]/div[6]/button
			WebElement pay =  getDriver().findElement(By.xpath("//*[@id='planDetails']/div[2]/div[1]/div[6]/button"));
			pay.click();
			waitForPageLoad(100);
			
			//*[@id="rp-form-field-payamount"]
			driver.findElement(By.id("rp-form-field-payamount")).clear();
			handleInputField_ByID("rp-form-field-payamount", AppConstants.RMVALUE);
			JavascriptExecutor jse1 = (JavascriptExecutor)getDriver();
			jse1.executeScript("window.scrollBy(0,500)", "");
			//*[@id="proceed-button"]/span[1]
			WebElement payment = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceed-button']/span[1]")));
			payment.click();
			waitForPageLoad(20);
			Thread.sleep(500);
			WebElement payment2 = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceed-button']/span[1]")));
			payment2.click();
			Thread.sleep(1000);
			waitForPageLoad(100);
		}
		catch(Exception e) {
			log.error("Fail : Failed to initialize Payment");
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	protected void cancelPaymentAndReturn() {
		try {			
			//Back to Accounts & Plans
			JavascriptExecutor jse1 = (JavascriptExecutor)getDriver();
			jse1.executeScript("window.scrollBy(0,500)", "");
			//*[@id="pay-getaway-foot"]/div[2]/button[3]
			WebElement cancel = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='pay-getaway-foot']/div[2]/button[3]")));
			cancel.click();
			Thread.sleep(500);
			getDriver().switchTo().alert().accept();
			waitForPageLoad(100);
		}catch(Exception e) {
			log.error("Fail : Failed to initialize Payment");
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	protected void returnAccountsPage() {
		try {
			WebElement back = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='menu-section']/div/ul/li[5]/a")));
			back.click();
		}catch(Exception e) {
			log.error("Fail : Failed to initialize Payment");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/*protected void payment(){
		try{
			JavascriptExecutor jse = (JavascriptExecutor)getDriver();
			jse.executeScript("window.scrollBy(0,-500)", "");
			//*[@id="planDetails"]/div[2]/div[1]/div[6]/button
			WebElement pay =  getDriver().findElement(By.xpath("//*[@id='planDetails']/div[2]/div[1]/div[5]/button"));
			pay.click();
			waitForPageLoad(100);
						
			driver.findElement(By.id("rp-form-field-payamount")).clear();
			handleInputField_ByID("rp-form-field-payamount", AppConstants.RMVALUE);
			JavascriptExecutor jse1 = (JavascriptExecutor)getDriver();
			jse1.executeScript("window.scrollBy(0,500)", "");
			WebElement payment = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceed-button']/span[1]/span")));
			payment.click();
//			System.out.println("Clicked 1");
			Thread.sleep(1000);
			WebElement payment2 = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceed-button']/span[1]")));
			payment2.click();
//			System.out.println("Clicked 2");
			waitForPageLoad(100);
			String rmvalue= driver.findElement(By.xpath("//*[@id='paymentform']/li[5]/div/span")).getText();
			System.out.println(rmvalue);
			String rm="MYR "+ AppConstants.RMVALUE;
			Assert.assertEquals(rm, rmvalue);
			System.out.println(rm+ " and exact value it "+rmvalue);
			
			//Back to Accounts & Plans
			WebElement cancel = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='CC']/fieldset/ol/center/input[3]")));
			cancel.click();
			
			getDriver().switchTo().alert().accept();
						
			String OrderId=driver.findElement(By.xpath("//*[@id='content']/div[2]/p[2]")).getText();
			System.out.println(OrderId);
			
			waitForPageLoad(100);
			WebElement back = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='menu-section']/div/ul/li[5]/a")));
			back.click();
			
			
			
		}
		catch(Exception e) {
			log.error("Fail : Failed to initialize Payment");
			//e.printStackTrace();
			System.exit(0);
		}
		
	}*/
	
	// To handle Reload Button in case of Prepaid Broadband
	
	protected void reloadPrepaid_Broadband() {
		
		try{
			JavascriptExecutor js = (JavascriptExecutor)getDriver();
			js.executeScript("window.scrollBy(0,-500)", "");
			WebElement reload=null;
			//*[@id="planDetails"]/div[2]/div[1]/div[4]/a
			//*[@id='planDetails']/div[3]/div[1]/div[4]/a
			// Test radio button of amount 10
			reload =  getDriver().findElement(By.xpath("//*[@id='planDetails']/div[2]/div[1]/div[4]/a"));
			reload.click();
			waitForPageLoad(100);
			clickOnRadio10();
			waitForPageLoad(100);
			// Test radio button of amount 30
			reload = getDriver().findElement(By.xpath("//*[@id='planDetails']/div[2]/div[1]/div[4]/a"));
			reload.click();
			waitForPageLoad(100);
			clickOnRadio30();
			waitForPageLoad(100);
			// Test radio button of amount 50
			reload=getDriver().findElement(By.xpath("//*[@id='planDetails']/div[2]/div[1]/div[4]/a"));
			reload.click();
			waitForPageLoad(100);
			clickOnRadio50();
			waitForPageLoad(100);
			// Test radio button of amount 100
		    reload=getDriver().findElement(By.xpath("//*[@id='planDetails']/div[2]/div[1]/div[4]/a"));
			reload.click();
			waitForPageLoad(100);
			clickOnRadio100();
			waitForPageLoad(100);
		}
		catch(Exception e) {
			log.error("Fail : Failed to initialize Payment");
			System.out.println(e);
			e.printStackTrace();
			//e.printStackTrace();
			System.exit(0);
		}
	}
	
	
	protected void clickOnRadio10() {
			try {
				// Put email id on text field as it can not be blank
					//*[@id="reloadForm__email"]
					WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
					System.out.println(emailtxt.isEnabled());
					if(emailtxt.isEnabled()) {
					handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
					Thread.sleep(500);
					}
				JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
				js1.executeScript("window.scrollBy(0,400)", "");
				//*[@id="prepaidReloadForm"]/ul/li[2]/label/div
				WebElement radio=getDriver().findElement(By.xpath("//*[@id='prepaidReloadForm']/ul/li[2]/label/div"));
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
	
	protected void clickOnRadio30() {
		
		try {
			WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
			if(emailtxt.isDisplayed()) {
			handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
			Thread.sleep(500);
			}
			JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
			js1.executeScript("window.scrollBy(0,400)", "");
			WebElement radio=getDriver().findElement(By.xpath("//*[@id='prepaidReloadForm']/ul/li[3]/label/div"));
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
	
	protected void clickOnRadio50() {
		try {
			WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
			if(emailtxt.isDisplayed()) {
			handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
			Thread.sleep(500);
			}
			JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
			js1.executeScript("window.scrollBy(0,400)", "");
			WebElement radio=getDriver().findElement(By.xpath("//*[@id='prepaidReloadForm']/ul/li[4]/label/div"));
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
	
	protected void clickOnRadio100() {
		try {
			WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
			if(emailtxt.isDisplayed()) {
			handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
			Thread.sleep(500);
			}
			JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
			js1.executeScript("window.scrollBy(0,400)", "");
			WebElement radio=getDriver().findElement(By.xpath("//*[@id='prepaidReloadForm']/ul/li[1]/label/div"));
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
	
	// To reload button in case of prepaid broad band
	
	protected void reloadBroadband() {
		try {
		WebElement reload=getDriver().findElement(By.xpath("//*[@id='planDetails']/div[2]/div[1]/div[4]/a"));
		reload.click();
		waitForPageLoad(100);
		}catch(Exception e) {
			log.error("Fail : Failed to initialize Payment");
			System.out.println(e);
			e.printStackTrace();
			//e.printStackTrace();
			System.exit(0);
		}
 	}
	
	// To handle Reload Button in case of prepaid
	
	protected void reload1() {
		try {
			//*[@id="planDetails"]/div[2]/div[1]/div[4]/a
			WebElement reload=getDriver().findElement(By.xpath("//*[@id='planDetails']/div[2]/div[1]/div[4]/a"));
			reload.click();
			log.info("Success: Reload button is clicked");
			waitForPageLoad(100);
		}catch(Exception e) {
			log.error("Fail : Failed to click on reload button");
			//System.out.println(e);
			//e.printStackTrace();
			//e.printStackTrace();
			//System.exit(0);
		}
		finally {
			shutDown();
		}
	}
	
	protected void clickOnRadioRM10() {
		try {
			
		// Put email id on text field as it can not be blank
			//*[@id="reloadForm__email"]
			WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
			System.out.println(emailtxt.isEnabled());
			if(emailtxt.isEnabled()) {
			handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
			Thread.sleep(500);
			}
		JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
		js1.executeScript("window.scrollBy(0,400)", "");
		//*[@id="prepaidReloadForm"]/ul/li[2]/label/div
		WebElement radio=getDriver().findElement(By.xpath("//*[@id='prepaidReloadForm']/ul/li[2]/label/div"));
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
	
	protected void clickOnPay() {
		try {
			//click on PAY Button
			//*[@id="payBtn"]
			// WebElement elementpay=getDriver().findElement(By.xpath("//*[@id='payBtn']"));
			WebElement pay=getDriver().findElement(By.xpath("//*[@id='payBtn']"));
			/*Actions move_action=new Actions(getDriver());
			move_action.moveToElement(pay).click().perform();*/
			//System.out.println(pay.isEnabled());
			pay.click();
			waitForPageLoad(100);
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
			log.error("Fail : Failed to Reload amount.");
			System.exit(0);
		}
		
	}
	
	protected void clickCancel() {
		try {
			//*[@id='pay-getaway-foot']/div[2]/form/button[3]
			WebElement cancel = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='pay-getaway-foot']/div[2]/form/button[3]")));
			cancel.click();
			Thread.sleep(500);
			//System.out.println("Cancel is clicked now accepting alert box");
			driver.switchTo().alert().accept();
			
			waitForPageLoad(100);
			Thread.sleep(500);
			Set<String> windows=getDriver().getWindowHandles();
			Iterator<String> winitr=windows.iterator();
			String mainwinid=winitr.next();
			String popupwinid=winitr.next();
			driver.switchTo().window(popupwinid);
			WebElement cancelpopup=getDriver().findElement(By.xpath("//*[@id='dsy_frameholder']/a"));
			cancelpopup.click();
			waitForPageLoad(100);
			Thread.sleep(500);
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
			log.error("Fail : Failed to Reload amount.");
			System.exit(0);
		}
	}
	
	protected void backToAccountsAndPaymentsPage() {
		try {
			//*[@id="menu-section"]/div/ul/li[5]/a
			WebElement back = (new WebDriverWait(driver, 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='menu-section']/div/ul/li[5]/a")));
			back.click();
			waitForPageLoad(100);
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
			log.error("Fail : Failed to Reload amount.");
			System.exit(0);
		}
	}
	
	
	
	protected void clickOnRadioRM30() {
		
		try {
			WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
			if(emailtxt.isDisplayed()) {
			handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
			Thread.sleep(500);
			}
			JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
			js1.executeScript("window.scrollBy(0,400)", "");
			WebElement radio=getDriver().findElement(By.xpath("//*[@id='prepaidReloadForm']/ul/li[3]/label/div"));
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
	
	protected void clickOnRadioRM50() {
		try {
			WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
			if(emailtxt.isDisplayed()) {
			handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
			Thread.sleep(500);
			}
			JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
			js1.executeScript("window.scrollBy(0,400)", "");
			WebElement radio=getDriver().findElement(By.xpath("//*[@id='prepaidReloadForm']/ul/li[4]/label/div"));
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
	
	protected void clickOnRadioRM100() {
		try {
			WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
			if(emailtxt.isDisplayed()) {
			handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
			Thread.sleep(500);
			}
			JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
			js1.executeScript("window.scrollBy(0,400)", "");
			WebElement radio=getDriver().findElement(By.xpath("//*[@id='prepaidReloadForm']/ul/li[1]/label/div"));
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
	

	protected void extra(){
		try{
			  
			JavascriptExecutor jse = (JavascriptExecutor)getDriver();
			jse.executeScript("window.scrollBy(0,500)", "");
//			WebElement fb = (new WebDriverWait(fbdriver, 5000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main']/section/div/ul/li[1]/a")));
//	        fb.click();
			//*[@id="main"]/section/div/ul/li[1]/a
	        WebElement fb =  getDriver().findElement(By.xpath("//*[@id='main']/section/div/ul/li[1]/a"));
	        Actions newTab1 = new Actions(getDriver());
	        newTab1.keyDown(Keys.SHIFT).click(fb).keyUp(Keys.SHIFT).build().perform();
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
	        
	       // System.out.println("extra : FB Passed" );
	        log.info("FB Link Passed");
	        Thread.sleep(500);
	        
	      //*[@id="main"]/section/div/ul/li[2]/a
	        WebElement tw =  getDriver().findElement(By.xpath("//*[@id='main']/section/div/ul/li[2]/a"));
	        Actions newTab2 = new Actions(getDriver());
	        newTab2.keyDown(Keys.SHIFT).click(tw).keyUp(Keys.SHIFT).build().perform();
	        Thread.sleep(500);
	         
	        //handle windows change
	        String base2 = getDriver().getWindowHandle();
	        Set<String> set2 = getDriver().getWindowHandles();
	         
	        set2.remove(base2);
	        assert set2.size() == 1;
	        getDriver().switchTo().window((String) set2.toArray()[0]);
	        waitForPageLoad(500);
	        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='page-container']/div[2]/div/div/div[1]/div/div/div/div[1]/h1/a")));
	        //close the window
	        getDriver().close();
	        getDriver().switchTo().window(base2);
	         
	        // handle windows change and switch back to the main window
	        Thread.sleep(2500);
	        for (String winHandle2 : getDriver().getWindowHandles()) {
	        getDriver().switchTo().window(winHandle2);
	        
	       // System.out.println("extra : Twitter Passed" );
	        log.info("Twitter Link Passed");
	        Thread.sleep(5000);
	        
	      //*[@id="main"]/section/div/ul/li[3]/a
	        WebElement com =  getDriver().findElement(By.xpath("//*[@id='main']/section/div/ul/li[3]/a"));
	        Actions newTab3 = new Actions(getDriver());
	        newTab3.keyDown(Keys.SHIFT).click(com).keyUp(Keys.SHIFT).build().perform();
	        Thread.sleep(5000);
	         
	        //handle windows change
	        String base3 = getDriver().getWindowHandle();
	        Set<String> set3 = getDriver().getWindowHandles();
	         
	        set3.remove(base3);
	        assert set3.size() == 1;
	        getDriver().switchTo().window((String) set3.toArray()[0]);
	        waitForPageLoad(5000);
	        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mainNav']/div[1]/div[1]/a")));
	        //close the window
	        getDriver().close();
	        getDriver().switchTo().window(base3);
	         
	        // handle windows change and switch back to the main window
	        Thread.sleep(2500);
	        for (String winHandle3 : getDriver().getWindowHandles()) {
	        getDriver().switchTo().window(winHandle3);
	        
	        // System.out.println("extra : Community Passed" );
	        log.info("Community Link Passed");
	        Thread.sleep(500);
	        JavascriptExecutor jse1 = (JavascriptExecutor)getDriver();
			jse1.executeScript("window.scrollBy(0,-500)", "");
	        }			
		}
	        }
		}
		
		catch(Exception e){
			log.error("Failed to verify Social Media Links");
		}
		}
		
	
	/**
	 * Page wait for specific time
	 * @param long wait time 
	 */
	protected  void waitForPageLoad(long waitTime){
		try{
			getDriver().manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		}catch(Exception e) {
			//System.out.println("Fail : waitForPageLoad()  failed");
			log.error("Fail : waitForPageLoad()  failed");
		}
	}
	
	protected  Wait<WebDriver> waitgetForPageLoad(long waitTime){
		try{
			wait = new WebDriverWait(driver, waitTime);
		}catch(Exception e) {
			//System.out.println("Fail : waitForPageLoad()  failed");
			log.error("Fail : waitgetForPageLoad()  failed");
		}
		return wait;
	}
	
	/**
	 * Handle input Textfield['Text'] on page by element ID
	 * pass the input data 
	 * @param Wedriver localWebDeriver  
	 * @param String elementID
	 * @param String inputData
	 */
	
	protected  void handleClearInputField_ByName(String elementID){
		try{
			getDriver().findElement(By.name(elementID)).clear();
		}catch(Exception e) {
			//System.out.println("Fail : Element ID '" + elementID + " Not Found");
			log.error("Fail : Element ID '" + elementID + " Not Found");
			//e.printStackTrace();
			//System.exit(0);
		}
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
	 */
	
	protected  void handleInputField_ByName(String elementName, String inputData){
		try{
			getDriver().findElement(By.name(elementName)).sendKeys(inputData);
		}catch(Exception e) {
			//System.out.println("Fail : Element '" + elementName + " Not Found");
			log.error("Fail : Element '" + elementName + " Not Found");
			//e.printStackTrace();
			//System.exit(0);
		}
	}
	
	/**
	 * Handle the click action on button by button ID
	 * @param Wedriver webDeriver  
	 * @param String elementName
	 * 
	 */
	
	protected  void handleButtonClick_ByID(String elementId){
		try{
			getDriver().findElement(By.id(elementId)).click();
		}catch(Exception e) {
			//System.out.println("Fail : Element ID '" + elementId + "' Click action Failed");
			log.error("Fail : Element ID '" + elementId + "' Click action Failed");
			//e.printStackTrace();
			//System.exit(0);
		}
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
	 */
	
	protected  void handleLink_ByText(String linkText){
		try{
			getDriver().findElement(By.linkText(linkText)).click();
		}catch(Exception e) {
			//System.out.println("Fail : Link Element '" + linkText + "' Click action Failed");
			log.error("Fail : Link Element '" + linkText + "' Click action Failed");
		}
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
		try{
			loadPage(AppConstants.DIGI_LOGIN_PAGE_PROD);
			waitForPageLoad(30);
			handleButtonClick_ByID("signIn");
			waitForPageLoad(60);
			handleLink_ByText(AppConstants.DIGI_LOGIN_WITHPHONENUM);
			handleClearInputField_ByName("cc");
			handleInputField_ByName("cc", countryPrefix);
			handleInputField_ByName("number", mssidn);
			handleInputField_ByName("password", Password);
		    handleButtonClick_ByID("inst_button_msisdn-login_form_login");
		}catch(Exception e){
			log.error("Fail : Login Failur for MSSIDN :"+mssidn);
			shutDown();
		}
	}
	
	protected void signInWithMSSIDN_stage(String countryPrefix, String mssidn, String Password)
	{
		try{
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
		}catch(Exception e){
			log.error("Fail : Login Failur for MSSIDN  :"+mssidn);
			shutDown();
		}
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
	
	public static WebDriver getSsWebSriver() {
		return ssWebSriver;
	}
	public static void setSsWebSriver(WebDriver ssWebSriver) {
		PageNavigation.ssWebSriver = ssWebSriver;
	}
	
	
	
	
	
	/*public static void main(String[] args) {
	PageNavigation pu = new PageNavigation();
	pu.setUpDriver();
	Properties propload = pu.loadFileData(AppConstants.BILLSNRELOAD_DATASTATUSFILE_LOCATION);
	System.out.println("Loaded Property File :"+ propload);
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
	}*/
}
