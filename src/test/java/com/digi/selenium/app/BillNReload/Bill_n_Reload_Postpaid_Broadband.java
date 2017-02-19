package com.digi.selenium.app.BillNReload;

import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.BillNReload.Bill_n_Reload_Postpaid_Broadband_Util;

public class Bill_n_Reload_Postpaid_Broadband extends Bill_n_Reload_Postpaid_Broadband_Util {

	//logger 
		private  Logger log = Logger.getLogger(Bill_n_Reload_Postpaid_Broadband.class);
		
		@BeforeClass
		public void initDriver() throws Exception {
			setUp();
		}
		
		@Test(priority=1,enabled=true)
		public void digiBillsnReload() throws InterruptedException {
			
			Thread.sleep(1000);		
			
					
			//Test the Plan Name
			  targetplanName();
			  Assert.assertEquals(getActualPlanName(), getSourceData(AppConstants.PLAN_NAME));
			  		
			//Test the PlanID 
			  targetplanId();
			  Assert.assertEquals(getActualPlanId(), getSourceData(AppConstants.PLAN_ID));
			  
			//Test the AccoutIdName
			  targetAccountIdName();
			  Assert.assertEquals(getActualAccountId(), getSourceData(AppConstants.ACCOUNT_NAME));
			  
			//Test the Credit Limit
			  targetCreditLimit();
			 Assert.assertEquals(getActualCreditLimit(), getSourceData(AppConstants.CREDIT_LIMIT));
			  
			//Test the Due Date
			 /* targetDueDate();
			  Assert.assertEquals(getActualDueDate(), getSourceData(AppConstants.DUE_DATE));*/
			  
			//Test the Outstanding Amount
			  targetOutstandingAmount();
			  Assert.assertEquals(getActualOutstandingAmount(),getSourceData(AppConstants.OUTSTANDING_AMOUNT));
			  
		}
		@Test(priority=2,enabled=true)
		public void validateGraphData(){
			targetGraphData();

			Iterator graphKeysIter = getTargetBCGraphData().keySet().iterator();
			while(graphKeysIter.hasNext()){
				String  targetBillCycle = (String) graphKeysIter.next();
				Map<String,String> targetGraphBCKeyData = getTargetBCGraphData().get(targetBillCycle);
				//System.out.println("targetBillCycle :  " + targetBillCycle);
				
				//validate BillCycle data
					String billCycleKey = getBILL_CYCLE_KEY(targetBillCycle.trim());
					//System.out.println("billcyclekey: " + billCycleKey );
					String sourceBillCycle = getSourceData(billCycleKey);
					//System.out.println("sourceBillCycleData :-->" +sourceBillCycle);
					//System.out.println("TargetBillCycleData :-->" +targetBillCycle);
				    Assert.assertEquals(targetBillCycle,sourceBillCycle);
				
				//validate Color data
					String colorKey = getCOLOR_KEY(targetBillCycle);
					String targetColor = targetGraphBCKeyData.get(colorKey);
					String sourceColor = getSourceData(colorKey);
					//System.out.println("sourceColorData :-->"+sourceColor);
					//System.out.println("targetColor :  " + targetColor);
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
						Assert.assertEquals(targetToolTipValue,sourceToolTipValue);
						
					}	}		
			}//while loop ends

		@Test(priority=3,enabled=true)
		public void validate_Yaxisdata(){
			targetYAxisData();
			  int eleListSize = getListyaxischild().size(); 
		      System.out.println("===========Y-AxisData========");
	           for(int i=0; i<eleListSize;i++){
	        	   String yaxisdata=getSourceData(getY_AXIS_KEY(Integer.toString(i+1)));
	        	   System.out.println("TargetYAxisData-->"+getListyaxischild().get(i).getText());
	        	   System.out.println("SourceData-->"+yaxisdata);
	        	   Assert.assertEquals(getListyaxischild().get(i).getText(), yaxisdata);	
		}
	
		}
	
		 @Test(priority=4,enabled=true)
			public void validateTableData(){
			 targetTableData();
			 for(int i=0;i<getHeaderlist().size();i++){
				String cellKey=getCELL_KEY(Integer.toString(i+1));
				String cellData=getSourceData(cellKey);
				System.out.println(getHeaderlist().get(i));
				System.out.println(cellData);
				Assert.assertEquals(getHeaderlist().get(i), cellData);
				 }
			 boolean download;
			 int hdatasize=getHeaderlist().size();
			 for(int j=hdatasize;j<getTableDataList().size();j++){
				String cellKey=getCELL_KEY(Integer.toString(j+1));
				String cellData=getSourceData(cellKey);
				System.out.println("Targetdata-->"+getTableDataList().get(j));
				System.out.println("SourceData-->"+cellData);
				if(j%7==0){
			    download =getTableDataList().get(j).isEnabled();
			    Assert.assertEquals(download, true);
			  if(download)
				System.out.println("Download key is enabled");
				}
				Assert.assertEquals(cellData, getTableDataList().get(j).getText());
				 }
				 
				}
			
	
        @Test(priority=5,enabled=true)
			
			public void validateTableByEvent() throws InterruptedException{
				targetTableEventData();
				String keys[]=new String [7];
				String values[]=new String [7];
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
		
	@Test(priority=6,enabled=false)
		 public void validateSocialLinks(){
			 extra();
		 }
    @Test(priority=9)
    public void makePayment(){
  	 paymentPostpaid_BillsnReloads();
  	 }

  @Test(priority=10) 
  // Click on Proceed
    public void validateAmountOnPaymentPage() {
  	 targetRMValueonPaymentPage();
  	 Assert.assertEquals(getRmvalue(), "MYR "+AppConstants.RMVALUE);
  }
  @Test(priority=11)
	// Cancel Payment and return
	public void validateCancelAndReturnFromPaymentPage() {
		cancelPaymentAndReturn();
	}
  @Test(priority=12) 
	// Verify order id for Payment Failed
	public void validatOrderId() {
		targetOrderId();
		Assert.assertEquals(getOrderid().length()>0,true);
	}
  @Test(priority=13)
	// Return on Accounts page
	public void validateReturnOnBillsnReloadsPage() {
		returnBillsnReloadPage();
	}
	

		@AfterClass
		public void terminateBrowser(){
			log.info("ShutDown : Digi_Login_Page");
			shutDown();
		}
}
