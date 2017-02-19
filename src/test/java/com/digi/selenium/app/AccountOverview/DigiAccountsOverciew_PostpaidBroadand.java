package com.digi.selenium.app.AccountOverview;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.AccountOverview.DigiAccountsOverview_PostpaidBroadband_Util;


public class DigiAccountsOverciew_PostpaidBroadand extends DigiAccountsOverview_PostpaidBroadband_Util {

	@BeforeClass
	public void initDriver() throws Exception {
		//setup the driver
		System.out.println("initDriver : Digi_Login_Page");
		setUp();
	}
	
	@Test(priority=1)
	//Test Account No
	public void validateAccountNo() throws InterruptedException {
		targetaccountno();
        Assert.assertEquals(getActualaccountno(), getSourceData(AppConstants.ACCOUNT_No));
	}
	@Test(priority=2)       
    //Test Account Name 
	public void validateAccountName() throws InterruptedException {
		targetaccountname();
        Assert.assertEquals(getActualaccountname(), getSourceData(AppConstants.ACCOUNT_NAME));
	}
	@Test(priority=3)            	        
    //Test Unbilled Value
	public void validateUnbilledAmount() throws InterruptedException {
		targetunbilledamount();
        Assert.assertEquals(getActualunbilledamount(), getSourceData(AppConstants.UNBILLED_AMOUNT));
	}      
	@Test(priority=4)     	
    //Test billed Value
	public void validateBilledAmount() throws InterruptedException {
		targetbilledamount();
        Assert.assertEquals(getActualbilledamount(), getSourceData(AppConstants.BILLED_AMOUNT));
	}      
	@Test(priority=5)
    //Test Plan Name
	public void validatePlanName() throws InterruptedException {
		targetplanname();
        Assert.assertEquals(getActualplanname(), getSourceData(AppConstants.ACCOUNTPLAN_NAME));
	}
	@Test(priority=6)
	//Test MSISDN
	public void validateMSISDN() throws InterruptedException {
		targetmsisdn();
	 	Assert.assertEquals(getActualmsisdn(), getSourceData(AppConstants.MSISDN));
	}     
	@Test(priority=7)
	//Test Status
	public void validateStatus() throws InterruptedException {
	targetstatus();
	 Assert.assertEquals(getActualstatus(), getSourceData(AppConstants.STATUS));
	}        
	@Test(priority=8) 	
 	//Test Unbilled Amount
	public void validateUnbilledValue() throws InterruptedException {
		targetbilledvalue();
	 	Assert.assertEquals(getActualbilledvalue(), getSourceData(AppConstants.UNBILLED));
	}      
	@Test(priority=9) 		 	
 	//Test Credit Limit
	public void validateCredit() throws InterruptedException {
		targetcredit();
	 	Assert.assertEquals(getActualcredit(), getSourceData(AppConstants.CREDIT));
	}   
	@Test(priority=10)
 	//Test Total Value
	public void validateTotal() throws InterruptedException {
		targettotal();
	 	Assert.assertEquals(getActualtotal(), getSourceData(AppConstants.TOTAL_AMOUNT));
	}  
	@Test(priority=11)
	//Test Duration
	public void validateDuration() throws InterruptedException {
		targetduration();
	 	Assert.assertEquals(getActualduration(), getSourceData(AppConstants.DURATION));

	} 
	@Test(priority=12)
	//Check Extra links
	public void validateExtra() throws InterruptedException {
		socialLinks();
	}

	@Test(priority=13)
	//Check Extra links
	public void validatePayment() throws InterruptedException {
		paymentPostpaidBroadband();
	}
	
	@Test(priority=14) 
	// Click on Proceed
	public void validateAmountOnPaymentPage() {
		targetRMValueonPaymentPage();
		Assert.assertEquals(getRmvalue(), "MYR "+AppConstants.RMVALUE);
	}
	
	@Test(priority=15)
	// Cancel Payment and return
	public void validateCancelAndReturnFromPaymentPage() throws InterruptedException {
		cancelPaymentAndReturn();
	}
	
	@Test(priority=16) 
	// Verify order id for Payment Failed
	public void validatOrderId() {
		targetOrderId();
		Assert.assertEquals(getOrderid().length()>0,true);
	}
	
	@Test(priority=17)
	// Return on Accounts page
	public void validateReturnOnAccountsPage() throws InterruptedException {
		returnAccountsPage();
	}
	
	

	
		
	@AfterClass
	public void terminateBrowser(){
		System.out.println("ShutDown : Digi_Login_Page");
		shutDown();
	}

}

