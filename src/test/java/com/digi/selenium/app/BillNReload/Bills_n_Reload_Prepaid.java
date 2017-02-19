package com.digi.selenium.app.BillNReload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log.Priority;
import org.apache.log4j.Logger;
import org.apache.xalan.xsltc.compiler.util.VoidType;
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

import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.BillNReload.Bill_n_Reload_Prepaid_Util;
import com.digi.selenium.util.BillNReload.Bill_n_Reload_Postpaid_Util;
import com.digi.selenium.util.common.PageNavigation;

import net.sourceforge.htmlunit.corejs.javascript.ast.CatchClause;

public class Bills_n_Reload_Prepaid extends Bill_n_Reload_Prepaid_Util{
	
	//logger 
	private  Logger log = Logger.getLogger(Bills_n_Reload_Prepaid.class);
	
		//setup the driver
	
		@BeforeClass
		public void initDriver() throws Exception {
			setUp();
		}

		//Test the AccountIdName
		
		@Test(priority=1, enabled = false)
		public void validateAccountName()  {
			targetAccountIdName();
			Assert.assertEquals(getActualAccountIdName(), getSourceData(AppConstants.PREPAID_ACCOUNT_NAME));
		}
	
		//Test the PlanName
		
		@Test(priority=2, enabled = false)
		public void validatePlanName()  {
			targetplanName();
			Assert.assertEquals(getActualPlanName(), getSourceData(AppConstants.PREPAID_PLAN_NAME));
		}
	
		//Test the PlanId
		
		@Test(priority=3, enabled = false)
		public void validatePlanID()  {
			targetplanId();
			Assert.assertEquals(getActualPlanId(),getSourceData(AppConstants.PREPAID_PLAN_ID));
		}	
			
		//Test the TotalReload
		
		@Test(priority=4, enabled = false)
		public void validateTotalReload()  {
			targetTotalReload();
			Assert.assertEquals(getActualTotalReload(),getSourceData(AppConstants.TOTAL_RELOAD));
		}		

		@Test(priority=6, enabled = false)
		public void validateGraphData_60Days(){
			targetGraphData_60Days();
            System.out.println("getTargetBCGraphData_60Days()"+ getTargetBCGraphData_60Days()); 
			Iterator graphKeysIter = getTargetBCGraphData_60Days().keySet().iterator();
			while(graphKeysIter.hasNext()){
				String  targetBillCycle = (String) graphKeysIter.next();
				Map<String,String> targetGraphBCKeyData = getTargetBCGraphData_60Days().get(targetBillCycle);				
				//System.out.println("targetBillCycle :  " + targetBillCycle);				
				//validate BillCycle data
					String billCycleKey = getBILL_CYCLE_KEY(targetBillCycle);
					String sourceBillCycle = getSourceData(billCycleKey);
					//System.out.println("sourceBillCycleData :-->" +sourceBillCycle);					
					Assert.assertEquals(sourceBillCycle,targetBillCycle);				
				//validate Color data
					String colorKey = getCOLOR_KEY(targetBillCycle);					
					String targetColor = targetGraphBCKeyData.get(colorKey);		
					String sourceColor = getSourceData(colorKey);					
					Assert.assertEquals(sourceColor,targetColor);				
				//validate ToolTip  data
					String targetToolTipValue = null;
					String sourceToolTipValue = null;
					//String tooltipKey = getTOOL_TIP_KEY(targetBillCycle);
					if (targetGraphBCKeyData.keySet().contains(getTOOL_TIP_KEY(targetBillCycle)) ){
						targetToolTipValue = targetGraphBCKeyData.get(getTOOL_TIP_KEY(targetBillCycle));
						sourceToolTipValue = getSourceData(getTOOL_TIP_KEY(targetBillCycle));
						
						System.out.println("targetToolTipValue :" + targetToolTipValue);
						System.out.println("sourceToolTipValue :" + sourceToolTipValue);
						Assert.assertEquals(targetToolTipValue, sourceToolTipValue);
						
					}			
			}//while loop ends

		}
		
		@Test(priority=7, enabled = false)
		public void validateGraphData_90Days(){
				targetGraphData_90Days();
             //  System.out.println("targetGraphData_90Days() : "+getTargetBCGraphData_90Days()); 
				Iterator graphKeysIter = getTargetBCGraphData_90Days().keySet().iterator();
				while(graphKeysIter.hasNext()){
					String  targetBillCycle = (String) graphKeysIter.next();
					Map<String,String> targetGraphBCKeyData = getTargetBCGraphData_90Days().get(targetBillCycle);
					
					//validate BillCycle data
						//String billCycleKey = getBILL_CYCLE_KEY(targetBillCycle);
						String sourceBillCycle = getSourceData(getBILL_CYCLE_KEY(targetBillCycle));
						
						//System.out.println("sourceBillCycleData :-->" +sourceBillCycle);
						//System.out.println("targetBillCycleData :-->" +targetBillCycle);
						Assert.assertEquals(sourceBillCycle,targetBillCycle);
					
					//validate Color data
					//	String colorKey = getCOLOR_KEY(targetBillCycle);
						
						String targetColor = targetGraphBCKeyData.get(getCOLOR_KEY(targetBillCycle));
			
						String sourceColor = getSourceData(getCOLOR_KEY(targetBillCycle));
						
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
							Assert.assertEquals(targetToolTipValue, sourceToolTipValue);
						}			
				}//while loop ends 
		}
		
		//Test Graph Data for 30days - color
		
		@Test(priority=5, enabled = false)
		public void validateGraphData_30Days_Color(){
			targetGraphData_30Days();
			//System.out.println("getTargetBCGraphData_30Days"+ getTargetBCGraphData_30Days());
			Iterator graphKeysIter = getTargetBCGraphData_30Days().keySet().iterator();	 
			while(graphKeysIter.hasNext()){
				String  targetColorkey = (String) graphKeysIter.next();
	
				String targetcolor = getTargetBCGraphData_30Days().get(targetColorkey);
				
				String sourceColorValue = getSourceData(getCOLOR_KEY(targetColorkey));
				
				Assert.assertEquals(targetcolor ,sourceColorValue);
			}//while loop ends	
			}

		//Test Graph Data for 30days - billCycle 
		
		@Test(priority=8, enabled = false)
		public void validateGraphData_30Days_BillCycle(){
			targetGraphData_30Days();
			System.out.println("getTargetGraphBillCycleDate_30Days()"+getTargetGraphBillCycleDate_30Days()); 
			int bcSize = getTargetGraphBillCycleDate_30Days().size();
			
			for(int i=0;i< bcSize;i++)
			{
				Assert.assertEquals(getTargetGraphBillCycleDate_30Days().get(i), 
									getSourceData(getBILL_CYCLE_KEY(Integer.toString(i+1))));
			}	
			
		}
		

		@Test(priority=9, enabled = false)
		
			public void validateTableData(){
				// Grab the table
			    targetTableData();	
			for(int i=0;i<getHeaderList().size();i++){
				String cellKey=getCELL_KEY(Integer.toString(i+1));
				String cellData=getSourceData(cellKey);
				System.out.println(getHeaderList().get(i));
				System.out.println(cellData);
				Assert.assertEquals(getHeaderList().get(i), cellData);
				 }
				int x=getHeaderList().size();
       		for(int i=0;i<getTableDataList().size();i++){
				String cellKey=getCELL_KEY(Integer.toString(x).concat(Integer.toString(i+1)));
				String cellData=getSourceData(cellKey);
				System.out.println("Targetdata-->"+getHeaderList().get(i));
				System.out.println("SourceData-->"+cellData);
				Assert.assertEquals(cellData, getHeaderList().get(i));
				x++;
				}
				}

		@Test(priority=10 , enabled = false)
		public void validate_Yaxisdata(){
			
			for(int j=1;j<=3;j++){
				getYAxisData(j);
			 for(int i=0; i<getListyaxischild().size();i++){
	        	  // WebElement targetdata= getDriver().findElement(By.xpath(targetdatapath));
	        	   String yaxiskey=getY_AXIS_KEY(Integer.toString(j).concat(Integer.toString(i+1)));
	        	   String yaxisdata=getSourceData(yaxiskey);
               System.out.println("TargetYAxisData-->"+getListyaxischild().get(i).getText());
               System.out.println("SourceData-->"+yaxisdata);
               Assert.assertEquals(getListyaxischild().get(i).getText(), yaxisdata);	
		}
	
		}}
		
		@Test(priority=11,enabled=false)
		
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
				
     @Test(priority=12,enabled=false )
		
		public void validateTableDropdownEvent() throws InterruptedException{
			targetEventDropdown();
		}//end of method
		
    @Test(priority=13,enabled=true )
    
        public void verifyDownloadWithFileName(){
    	 targetdropdownmonths();
    	 String downloadpath=AppConstants.DOWNLOADS_PATH;
    	 JavascriptExecutor jse = (JavascriptExecutor)getDriver();
    	 for(int i=0;i<getDropdownmonths().size();i++){
    		 downloadMonthHistory(i);
    	if(i==0)
	    Assert.assertEquals(isFileDownloaded(downloadpath,"60109236092_042015.pdf"),true);
    	else if(i==1)
    		Assert.assertEquals(isFileDownloaded(downloadpath,"60109236092_052015.pdf"),true);
    	else if(i==2)
    		Assert.assertEquals(isFileDownloaded(downloadpath,"60109236092_062015.pdf"),true);
    	else
    		Assert.assertEquals(isFileDownloaded(downloadpath,"60109236092_072015.pdf"),true);
	    getDriver().findElement(By.xpath("//*[@id='showFilters']")).click();
	    
	   }
    	 
}
    @Test(priority=14,enabled=true)
    
	 public void validateSocialLinks(){
		 socialLinks();
	 }
    
   
    @Test(priority=16,enabled=true)
	public void validateReloadButton1() {
		target_reloadbtn();
	}
	@Test(priority=17,enabled=true) 
	public void validateSelectPaymentRadio50() {
		clickOnRadioRM50();
	}
	@Test(priority=18,enabled=true)
	public void validateMsisdnForPayment10() {
		targetMsisdnForPaytment();
		// 010 923 6134
		// 0109236134
		StringBuffer temp=new StringBuffer();
		 for(String t:getSourceData(AppConstants.MSISDN).split(" "))
			 temp.append(t);
		Assert.assertEquals(getActualmsisdn(), temp.toString());
	}
	@Test(priority=19,enabled=true) 
	public void validateAirTimeAmtForPayment10() {
		targetAirTimeAmtForPayment();
		Assert.assertEquals(getAirtimeamt(), getSourceData(AppConstants.AIRTIMEAMOUNT10));
	}
	@Test(priority=20,enabled=true)
	public void validateTotalAmtForPayment10() {
		targetTotalAmtForPayment();
		Assert.assertEquals(getAirtimeamt(), getSourceData(AppConstants.TOTALAMOUNTPAYMENT10));
	}
	@Test(priority=21,enabled=true)
	public void validatePayButton1() {
		clickOnPay();
	}
	@Test(priority=22,enabled=true)
	public void validateFinalAmt10() {
		targetFinalAmt();
		Assert.assertEquals(getFinalamt(), getSourceData(AppConstants.TOTALAMOUNTFINAL10));
	}
	@Test(priority=23,enabled=true)
	public void validateCancelAndReturn() throws InterruptedException{
		clickCancel();
	}
	@Test(priority=24,enabled=true)
	public void validateTransactionNo() {
		targetValidateTransactionNo();
		Assert.assertEquals(getTransactionno().length()>0, true);
	}
	@Test(priority=25,enabled=true)
	public void validateReturnAccountsAndPaymentPage() throws InterruptedException{
		backToAccountsAndPaymentsPage();
	}
	
		@AfterClass
		public void terminateBrowser(){
			log.info("ShutDown : Digi_Login_Page");
			shutDown();
		}
}//end of class
