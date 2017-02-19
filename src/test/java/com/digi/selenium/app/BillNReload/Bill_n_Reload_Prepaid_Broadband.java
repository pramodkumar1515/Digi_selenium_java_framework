package com.digi.selenium.app.BillNReload;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
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
import org.testng.internal.ExtraOutput;

import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.BillNReload.Bill_n_Reload_Prepaid_Broadband_Util;

public class Bill_n_Reload_Prepaid_Broadband extends Bill_n_Reload_Prepaid_Broadband_Util {

	
	//logger 
			private  Logger log = Logger.getLogger(Bill_n_Reload_Prepaid_Broadband.class);
			
		
		@BeforeClass
		public void initDriver() throws Exception {
			//setup the driver
			setUp();
		}
		
		@Test(priority=1,enabled=false )
		public void digiBillsnReload() throws InterruptedException {
			
			Thread.sleep(1000);		
			//waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='reloadBalance']/a")));
			
			//Test the AccountIdName
			targetAccountIdName();
			Assert.assertEquals(getActualAccountIdName(), getSourceData(AppConstants.PREPAID_ACCOUNT_NAME));
			
			//Test the PlanName
			targetplanName();
			Assert.assertEquals(getActualPlanName(), getSourceData(AppConstants.PREPAID_PLAN_NAME));
			
			//Test the PlanId
			targetplanId();
			Assert.assertEquals(getActualPlanId(),getSourceData(AppConstants.PREPAID_PLAN_ID));
			
			//Test the TotalReload
			targetTotalReload();
			Assert.assertEquals(getActualTotalReload(),getSourceData(AppConstants.TOTAL_RELOAD));
			
		}
	
		//Test 30days data
		
		@Test(priority=2, enabled = true)
		public void validateGraphData_30Days(){
			targetGraphData_30Days();
			System.out.println("getTargetBCGraphData_30Days() :" + getTargetBCGraphData_30Days());
			//graphToolTipData_30Days
			System.out.println("getGraphToolTipData_30Days() :" + getGraphToolTipData_30Days());
			System.out.println("getGraphBillCycleData_30Days() :" + getGraphBillCycleDate_30Days());
			//System.out.println("getGraphToolTipData_30Days() :" + getGraphToolTipData_30Days());
			Iterator graphKeysIter = getTargetBCGraphData_30Days().keySet().iterator(); 
			Iterator<String> graphDateItr=getGraphBillCycleDate_30Days().iterator();
			while(graphKeysIter.hasNext()){
				String  targetColorkey = (String) graphKeysIter.next();
				String targetcolor = getTargetBCGraphData_30Days().get(targetColorkey);
				
				//validate Color data
					String colorKey = getCOLOR_KEY(targetColorkey);
					String sourceColorValue = getSourceData(colorKey); 
					System.out.println("targetcolorValue :"+ targetcolor);
					System.out.println("sourceColorValue :"+ sourceColorValue);
					Assert.assertEquals(sourceColorValue,targetcolor);
			}//while loop ends
			
			List<String> billCycleData=getGraphBillCycleDate_30Days();
			for(int i=0;i< billCycleData.size();i++)
			{
				String billCycleKey=getBILL_CYCLE_KEY(Integer.toString(i+1));
				String sourceBillCycleData = getSourceData(billCycleKey);
				System.out.println("targetBillCycleData :-->" +billCycleData.get(i));
				System.out.println("sourceBillCycleData :-->" +sourceBillCycleData);
				Assert.assertEquals(sourceBillCycleData,billCycleData.get(i));
			}
			for(int j=0;j<getGraphToolTipData_30Days().size();j++){
				String toolTipKey=getTOOL_TIP_KEY(Integer.toString(j+1));
				String sourceToolTipData = getSourceData(toolTipKey);
				System.out.println("targettoolTipData :-->" +getGraphToolTipData_30Days().get(j));
				System.out.println("sourcetoolTipData :-->" +sourceToolTipData);
				Assert.assertEquals(sourceToolTipData,getGraphToolTipData_30Days().get(j).replace("\n", ""));
			}
		}

		//Test 60days data
		
		@Test(priority=3 , enabled = true)
		public void validateGraphData_60Days(){
			targetGraphData_60Days();
			System.out.println("targetGraphData_60Days() -->"+ getTargetBCGraphData_60Days());
			Iterator graphKeysIter = getTargetBCGraphData_60Days().keySet().iterator();
			while(graphKeysIter.hasNext()){
				String  targetBillCycle = (String) graphKeysIter.next();
				Map<String,String> targetGraphBCKeyData = getTargetBCGraphData_60Days().get(targetBillCycle);				
				//System.out.println("targetBillCycle :  " + targetBillCycle);
				
				//validate BillCycle data
					String billCycleKey = getBILL_CYCLE_KEY(targetBillCycle);
					String sourceBillCycle = getSourceData(billCycleKey);					
					//System.out.println("sourceBillCycleData :-->" +sourceBillCycle);
					System.out.println("targetBillCycleData :-->" +targetBillCycle);
					Assert.assertEquals(sourceBillCycle,targetBillCycle);
				
				//validate Color data
					String colorKey = getCOLOR_KEY(targetBillCycle);
					String targetColor = targetGraphBCKeyData.get(colorKey);
					String sourceColor = getSourceData(colorKey);
					//System.out.println("sourceColorData :-->"+sourceColor);
					System.out.println("targetColorData :-->"+targetColor);
					Assert.assertEquals(sourceColor,targetColor);
				
				//validate ToolTip  data
					String targetToolTipValue = null;
					String sourceToolTipValue = null;
					String tooltipKey = getTOOL_TIP_KEY(targetBillCycle);
					if (targetGraphBCKeyData.keySet().contains(tooltipKey) ){
						targetToolTipValue = targetGraphBCKeyData.get(tooltipKey);
						sourceToolTipValue = getSourceData(tooltipKey);
						System.out.println("targetToolTipValue :" + targetToolTipValue);
						System.out.println("sourceToolTipValue :" + sourceToolTipValue);
						Assert.assertEquals(sourceToolTipValue,targetToolTipValue);
					}			
			}//while loop ends
		}
	

		//Test 90days data

		@Test(priority=4 , enabled = true)
		public void validateGraphData_90Days(){
			targetGraphData_90Days();
			System.out.println("targetGraphData_90Days() -->"+ getTargetBCGraphData_90Days());
			Iterator graphKeysIter = getTargetBCGraphData_90Days().keySet().iterator();
			while(graphKeysIter.hasNext()){
				String  targetBillCycle = (String) graphKeysIter.next();
				Map<String,String> targetGraphBCKeyData = getTargetBCGraphData_90Days().get(targetBillCycle);
				
				//validate BillCycle data
					String billCycleKey = getBILL_CYCLE_KEY(targetBillCycle);
					String sourceBillCycle = getSourceData(billCycleKey);
					//System.out.println("sourceBillCycleData :-->" +sourceBillCycle);
					//System.out.println("targetBillCycleData :-->" +targetBillCycle);					
					Assert.assertEquals(sourceBillCycle,targetBillCycle);
				
				//validate Color data
					String colorKey = getCOLOR_KEY(targetBillCycle);					
					String targetColor = targetGraphBCKeyData.get(colorKey);		
					String sourceColor = getSourceData(colorKey);					
					//System.out.println("sourceColorData :-->"+sourceColor);
					Assert.assertEquals(sourceColor,targetColor);
				
				//validate ToolTip  data
					String targetToolTipValue = null;
					String sourceToolTipValue = null;
					String tooltipKey = getTOOL_TIP_KEY(targetBillCycle);
					if (targetGraphBCKeyData.keySet().contains(tooltipKey) ){
						targetToolTipValue = targetGraphBCKeyData.get(tooltipKey);
						sourceToolTipValue = getSourceData(tooltipKey);					
						//System.out.println("targetToolTipValue :" + targetToolTipValue);
						//System.out.println("sourceToolTipValue :" + sourceToolTipValue);
						Assert.assertEquals(sourceToolTipValue,targetToolTipValue);
					}	}		
			}//while loop ends


	
		
		//Test table data	
	
		@Test(priority=5 , enabled = true)
		public void validateTableData(){
			// Grab the table
            	 targetTableData();
            	 int hsize=getHeaderlist().size();
            	 for(int i=0;i<hsize;i++){ 
			String cellKey=getCELL_KEY(Integer.toString(i+1));
			String cellData=getSourceData(cellKey);
			System.out.println(getHeaderlist().get(i)); 
			System.out.println(cellData);
			Assert.assertEquals(getHeaderlist().get(i), cellData); 
			 }
			 			
			// Now get all the TR elements from the table
            	 for(int j=hsize+1;j<getTabledatalist().size();j++){
				String cellKey=getCELL_KEY(Integer.toString(j));
				String cellData=getSourceData(cellKey);
				System.out.println("Targetdata-->"+getTabledatalist().get(j).trim()); 
				System.out.println("SourceData-->"+cellData);
				Assert.assertEquals(cellData, getTabledatalist().get(j)); 
			 }
		}//VALIDATETABLEDATA() ends
		
      
		
		//Test YAxis data
		@Test(priority=6, enabled = false)
		public void validate_Yaxisdata(){
			for(int j=1;j<=3;j++){
				targetYAxisData(j);  
			int eleListsize=getListyaxischild().size();
	        for(int i=0; i<eleListsize;i++){
	        // WebElement targetdata= getDriver().findElement(By.xpath(targetdatapath));
	        	   String yaxiskey=getY_AXIS_KEY(Integer.toString(j).concat(Integer.toString(i+1)));
	        	   String yaxisdata=getSourceData(yaxiskey);
	               System.out.println("TargetYAxisData-->"+getListyaxischild().get(i).getText());
	               System.out.println("SourceData-->"+yaxisdata);
	               Assert.assertEquals(getListyaxischild().get(i).getText(), yaxisdata);	
	           }//for loop
	
			}//outer for loop
		 }//validate_Yaxisdata()

@Test(priority=7,enabled= false)
		
		public void validateTableByEvent() throws InterruptedException{
			targetTableEventData();
			String keys[]=new String [6];
			String values[]=new String [6];
			Iterator it=getDropDownTableHeaderMap().entrySet().iterator();
			while(it.hasNext()){
			Map.Entry pair=(Map.Entry)it.next();
				keys=pair.getKey().toString().split(",");
				values=pair.getValue().toString().split(","); 
				for(int i=0;i<keys.length;i++){
					for(int j=0;j<values.length;j++){
						if(keys[i]==values[j])
							Assert.assertEquals("Not Equal", "Equal");
					}
			}
			}
		}//end of method
				
    @Test(priority=8,enabled=false )
		
		public void validateTableDropdownEvent() throws InterruptedException{
			targetEventDropdown();
		}//end of method
		
   @Test(priority=9,enabled=false)
        public void verifyDownloadWithFileName(){
    	   try{
    	 targetdropdownmonths();
    	 JavascriptExecutor jse = (JavascriptExecutor)getDriver();
    	 for(int i=0;i<getDropdownmonths().size();i++){
    		 downloadMonthHistory(i);
    	     String downloadpath=AppConstants.DOWNLOADS_PATH;
	         if(i==0)
	        	 Assert.assertEquals(isFileDownloaded(downloadpath,"601116206657_042015.pdf"),true);
	         else if(i==1)
	        	 Assert.assertEquals(isFileDownloaded(downloadpath,"601116206657_052015.pdf"),true);
	         else if(i==2)
    		Assert.assertEquals(isFileDownloaded(downloadpath,"601116206657_062015.pdf"),true);
	         else
    		Assert.assertEquals(isFileDownloaded(downloadpath,"601116206657_072015.pdf"),true);
	         getDriver().findElement(By.xpath("//*[@id='showFilters']")).click();  
	   }
    	 }catch(Exception e){
    		 e.printStackTrace(); 
    	 }
   }
     @Test(priority=10,enabled=false)
	 public void validateSocialLinks(){
		 socialLinks();
	 }
       
    @Test(priority=16)
   	public void validateReloadButton1() {
   		target_reloadbtn();
   	}
   	@Test(priority=17) 
   	public void validateSelectPaymentRadio50() {
   		clickOnRadioRM50();
   	}
   	@Test(priority=18)
   	public void validateMsisdnForPayment10() {
   		targetMsisdnForPaytment();
   		// 010 923 6134
   		// 0109236134
   		StringBuffer temp=new StringBuffer();
   		 for(String t:getSourceData(AppConstants.MSISDN).split(" "))
   			 temp.append(t);
   		Assert.assertEquals(getActualmsisdn(), temp.toString());
   	}
   	@Test(priority=19) 
   	public void validateAirTimeAmtForPayment10() {
   		targetAirTimeAmtForPayment();
   		Assert.assertEquals(getAirtimeamt(), getSourceData(AppConstants.AIRTIMEAMOUNT10));
   	}
   	@Test(priority=20)
   	public void validateTotalAmtForPayment10() {
   		targetTotalAmtForPayment();
   		Assert.assertEquals(getAirtimeamt(), getSourceData(AppConstants.TOTALAMOUNTPAYMENT10));
   	}
   	@Test(priority=21)
   	public void validatePayButton1() {
   		clickOnPay();
   	}
   	@Test(priority=22)
   	public void validateFinalAmt10() {
   		targetFinalAmt();
   		Assert.assertEquals(getFinalamt(), getSourceData(AppConstants.TOTALAMOUNTFINAL10));
   	}
   	@Test(priority=23)
   	public void validateCancelAndReturn() throws InterruptedException{
   		clickCancel();
   	}
   	@Test(priority=24)
   	public void validateTransactionNo() {
   		targetValidateTransactionNo();
   		Assert.assertEquals(getTransactionno().length()>0, true);
   	}
   	@Test(priority=25)
   	public void validateReturnAccountsAndPaymentPage() throws InterruptedException{
   		backToAccountsAndPaymentsPage();
   	}
		@AfterClass
		public void terminateBrowser(){
			log.info("ShutDown : Digi_Login_Page");
			shutDown();
		}
	
}
		
	
	
	
	
	
	
	
	
	

