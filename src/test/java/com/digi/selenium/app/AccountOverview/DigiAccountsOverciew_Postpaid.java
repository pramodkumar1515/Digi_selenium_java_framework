package com.digi.selenium.app.AccountOverview;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.AccountOverview.DigiAccountsOverview_Postpaid_Util;

public class DigiAccountsOverciew_Postpaid extends DigiAccountsOverview_Postpaid_Util {

	@BeforeClass
	public void initDriver() throws Exception {
		//setup the driver
		setUp();
	}

	@Test(priority=1)
	    	//Test Account No
	public void ValidateAccountNo() throws InterruptedException{
	    	targetaccountno();
	    	Assert.assertEquals(getActualaccountno(), getSourceData(AppConstants.ACCOUNT_No));
	    	
	}
	@Test(priority=2)       
	        //Test Account Name 
	public void ValidateAccountName() throws InterruptedException{
	        targetaccountname();
	        Assert.assertEquals(getActualaccountname(), getSourceData(AppConstants.ACCOUNT_NAME));
    
	}
	@Test(priority=3)            	        
	        //Test Unbilled Value
	public void ValidateUnbilledAmount() throws InterruptedException{
	        targetunbilledamount();
	        Assert.assertEquals(getActualunbilledamount(), getSourceData(AppConstants.UNBILLED_AMOUNT));
	}      
	@Test(priority=4)     	
	        //Test billed Value
	public void ValidateBilledAmount() throws InterruptedException{
		targetbilledamount();
		Assert.assertEquals(getActualbilledamount(), getSourceData(AppConstants.BILLED_AMOUNT));
}      
	@Test(priority=5)
	        //Test Plan Name
	public void ValidatePlanName() throws InterruptedException{
		targetplanname();
		Assert.assertEquals(getActualplanname(), getSourceData(AppConstants.ACCOUNTPLAN_NAME));
}
	@Test(priority=6)
	 //Test MSISDN
	public void ValidateMSISDN() throws InterruptedException{
		targetmsisdn();
		Assert.assertEquals(getActualmsisdn(), getSourceData(AppConstants.MSISDN));
}     
	@Test(priority=7)
	//Test Status
		public void ValidateStatus() throws InterruptedException{
			targetstatus();
			 Assert.assertEquals(getActualstatus(), getSourceData(AppConstants.STATUS));
	}        
	@Test(priority=8) 	
		 	//Test Unbilled Amount
		public void ValidateUnbilledValue() throws InterruptedException{
			targetbilledvalue();
			Assert.assertEquals(getActualbilledvalue(), getSourceData(AppConstants.UNBILLED));
	}      
	@Test(priority=9) 		 	
		 	//Test Credit Limit
		public void ValidateCredit() throws InterruptedException{
			targetcredit();
			Assert.assertEquals(getActualcredit(), getSourceData(AppConstants.CREDIT));
	}   
	@Test(priority=10)
		 	//Test Total Value
		public void ValidateTotal() throws InterruptedException{
			targettotal();
			 Assert.assertEquals(getActualtotal(), getSourceData(AppConstants.TOTAL_AMOUNT));
	}  
	@Test(priority=11)
			//Test Duration
		public void ValidateDuration() throws InterruptedException{
			targetduration();
			Assert.assertEquals(getActualduration(), getSourceData(AppConstants.DURATION));
		
	} 
	@Test(priority=12)
	//Check Extra links
	public void ValidateExtra() throws InterruptedException{
		socialLinks();
	}
	
	@Test(priority=13)
	//Check Extra links
	public void validatePayment() throws InterruptedException {
		paymentPostpaid();
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
		shutDown();
	}

}

