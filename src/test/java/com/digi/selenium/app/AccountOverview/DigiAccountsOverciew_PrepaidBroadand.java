package com.digi.selenium.app.AccountOverview;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.AccountOverview.DigiAccountsOverview_PrepaidBroadband_Util;


public class DigiAccountsOverciew_PrepaidBroadand extends DigiAccountsOverview_PrepaidBroadband_Util {

	@BeforeClass
	public void initDriver() throws Exception {
		//setup the driver
		System.out.println("initDriver : Digi_Login_Page");
		setUp();
	}

	
	@Test(priority=1)
	//Test Account No
	public void validateAccountNo() throws InterruptedException{
		targetaccountno();
        Assert.assertEquals(getActualaccountno(), getSourceData(AppConstants.ACCOUNT_No));
	}
	
	@Test(priority=2)       
    //Test Account Name 
	public void validateAccountName() throws InterruptedException{
		targetaccountname();
        Assert.assertEquals(getActualaccountname(), getSourceData(AppConstants.ACCOUNT_NAME));
	}
	
	@Test(priority=3)
	public void validateBalance() throws InterruptedException {
		targetbalance();
        Assert.assertEquals(getActualbalance(), getSourceData(AppConstants.BALANCE));
	}
	
	@Test(priority=4)
	public void validateExpiry() throws InterruptedException {
		targetexpiry();
        Assert.assertEquals(getActualexpiry(), getSourceData(AppConstants.EXPIRY));
	}
	
	@Test(priority=5)
	public void validatePlanName() throws InterruptedException {
		targetplanname();
        Assert.assertEquals(getActualplanname(), getSourceData(AppConstants.ACCOUNTPLAN_NAME));        
	}
	
	@Test(priority=6)
	public void validateMsisdn() throws InterruptedException {
		targetmsisdn();
	 	Assert.assertEquals(getActualmsisdn(), getSourceData(AppConstants.MSISDN));	 	
	}
	
	@Test(priority=7) 
	public void validateStatus() 	throws InterruptedException {
		targetstatus();
	 	Assert.assertEquals(getActualstatus(), getSourceData(AppConstants.STATUS));
	}	
	
	@Test(priority=8)
	//Check Social links
	public void ValidateExtra() throws InterruptedException {
		socialLinks();
	}
	
	// To test reload of amount 50
	
	@Test(priority=9)
	public void validateReloadButton4() throws InterruptedException {
		reloadBroadband();
	}
	@Test(priority=10) 
	public void validateSelectPaymentRadio50() throws InterruptedException {
		clickOnRadio50();
	}
	@Test(priority=11)
	public void validateMsisdnForPayment50() {
	targetMsisdnForPaytment();
	// 010 923 6134
	// 0109236134
	StringBuffer temp=new StringBuffer();
	for(String t:getSourceData(AppConstants.MSISDN).split(" "))
	temp.append(t);
	Assert.assertEquals(getActualmsisdn(), temp.toString().trim());
	}
	@Test(priority=12) 
	public void validateAirTimeAmtForPayment50() {
		targetAirTimeAmtForPayment();
		Assert.assertEquals(getAirtimeamt(), getSourceData(AppConstants.AIRTIMEAMOUNT50));
	}
	@Test(priority=13)
	public void validateTotalAmtForPayment50() {
		targetTotalAmtForPayment();
		Assert.assertEquals(getTotalamt(), getSourceData(AppConstants.TOTALAMOUNTPAYMENT50));
	}
	@Test(priority=14)
	public void validatePayButton4() {
		clickOnPay();
	}
	@Test(priority=15)
	public void validateFinalAmt50() {
		targetFinalAmt();
		Assert.assertEquals(getFinalamt(), getSourceData(AppConstants.TOTALAMOUNTFINAL50));
	}
	@Test(priority=16)
	public void validateCancelAndReturn4() throws InterruptedException {
		clickCancel();
	}
	@Test(priority=17)
	public void validateTransactionNo4() {
		targetValidateTransactionNo();
		Assert.assertEquals(getTransactionno().length()>0, true);
	}
	@Test(priority=18)
	public void validateReturnAccountsAndPaymentPage4() throws InterruptedException {
		backToAccountsAndPaymentsPagePrepaidBroadband();
	}
	// end of test reload of amount 50
	
	/*@Test(priority=9)
		public void validateReloadButton1() throws InterruptedException {
			reloadBroadband();
		}
		@Test(priority=10) 
		public void validateSelectPaymentRadio100() throws InterruptedException {
			clickOnRadio100();
		}
		
		@Test(priority=11)
		public void validateMsisdnForPayment100() {
			targetMsisdnForPaytment();
			// 010 923 6134
			// 0109236134
			StringBuffer temp=new StringBuffer();
			 for(String t:getSourceData(AppConstants.MSISDN).split(" "))
				 temp.append(t);
			Assert.assertEquals(getActualmsisdn(), temp.toString());
		}
		
		@Test(priority=12) 
		public void validateAirTimeAmtForPayment100() {
			targetAirTimeAmtForPayment();
			Assert.assertEquals(getAirtimeamt(), getSourceData(AppConstants.AIRTIMEAMOUNT100));
		}
		@Test(priority=13)
		public void validateTotalAmtForPayment100() {
			targetTotalAmtForPayment();
			Assert.assertEquals(getAirtimeamt(), getSourceData(AppConstants.TOTALAMOUNTPAYMENT100));
		}
		@Test(priority=14)
		public void validatePayButton1() {
			clickOnPay();
		}
		@Test(priority=15)
		public void validateFinalAmt100() {
			targetFinalAmt();
			Assert.assertEquals(getFinalamt(), getSourceData(AppConstants.TOTALAMOUNTFINAL100));
		}
		@Test(priority=16)
		public void validateCancelAndReturn() throws InterruptedException {
			clickCancel();
		}
		@Test(priority=17)
		public void validateTransactionNo() {
			targetValidateTransactionNo();
			Assert.assertEquals(getTransactionno().length()>0, true);
		}
		@Test(priority=18)
		public void validateReturnAccountsAndPaymentPage() throws InterruptedException {
			backToAccountsAndPaymentsPage();
		}
		
		// end of test reload of amount 100
		
		// To test reload of amount 10// To test reload of amount 100
		
		
				@Test(priority=19)
				public void validateReloadButton2() throws InterruptedException {
					reloadBroadband();
				}
				@Test(priority=20) 
				public void validateSelectPaymentRadio10() throws InterruptedException {
					clickOnRadio10();
				}
				@Test(priority=21)
				public void validateMsisdnForPayment10() {
					targetMsisdnForPaytment();
					Assert.assertEquals(getActualmsisdn(), getSourceData(AppConstants.MSISDN));
				}
				@Test(priority=22) 
				public void validateAirTimeAmtForPayment10() {
					targetAirTimeAmtForPayment();
					Assert.assertEquals(getAirtimeamt(), getSourceData(AppConstants.AIRTIMEAMOUNT10));
				}
				@Test(priority=23)
				public void validateTotalAmtForPayment10() {
					targetTotalAmtForPayment();
					Assert.assertEquals(getAirtimeamt(), getSourceData(AppConstants.TOTALAMOUNTPAYMENT10));
				}
				@Test(priority=24)
				public void validatePayButton2() {
					clickOnPay();
				}
				@Test(priority=25)
				public void validateFinalAmt10() {
					targetFinalAmt();
					Assert.assertEquals(getFinalamt(), getSourceData(AppConstants.TOTALAMOUNTFINAL10));
				}
				@Test(priority=26)
				public void validateCancelAndReturn2() throws InterruptedException {
					clickCancel();
				}
				@Test(priority=27)
				public void validateTransactionNo2() {
					targetValidateTransactionNo();
					Assert.assertEquals(getTransactionno().length()>0, true);
				}
				@Test(priority=28)
				public void validateReturnAccountsAndPaymentPage2() throws InterruptedException {
					backToAccountsAndPaymentsPage();
				}
				// end of test reload of amount 10
		
				// To test reload of amount 30
				
				@Test(priority=29)
				public void validateReloadButton3() throws InterruptedException {
					reloadBroadband();
				}
				@Test(priority=30) 
				public void validateSelectPaymentRadio30() throws InterruptedException {
					clickOnRadio30();
				}
				@Test(priority=31)
				public void validateMsisdnForPayment30() {
					targetMsisdnForPaytment();
					Assert.assertEquals(getActualmsisdn(), getSourceData(AppConstants.MSISDN));
				}
				@Test(priority=32) 
				public void validateAirTimeAmtForPayment30() {
					targetAirTimeAmtForPayment();
					Assert.assertEquals(getAirtimeamt(), getSourceData(AppConstants.AIRTIMEAMOUNT30));
				}
				@Test(priority=33)
				public void validateTotalAmtForPayment30() {
					targetTotalAmtForPayment();
					Assert.assertEquals(getAirtimeamt(), getSourceData(AppConstants.TOTALAMOUNTPAYMENT30));
				}
				@Test(priority=34)
				public void validatePayButton3() {
					clickOnPay();
				}
				@Test(priority=35)
				public void validateFinalAmt30() {
					targetFinalAmt();
					Assert.assertEquals(getFinalamt(), getSourceData(AppConstants.TOTALAMOUNTFINAL30));
				}
				@Test(priority=36)
				public void validateCancelAndReturn3() throws InterruptedException {
					clickCancel();
				}
				@Test(priority=37)
				public void validateTransactionNo3() {
					targetValidateTransactionNo();
					Assert.assertEquals(getTransactionno().length()>0, true);
				}
				@Test(priority=38)
				public void validateReturnAccountsAndPaymentPage3() throws InterruptedException {
					backToAccountsAndPaymentsPage();
				}
				// end of test reload of amount 30
*/				
	
	@AfterClass
	public void terminateBrowser(){
		shutDown();
	}
}

