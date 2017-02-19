package com.digi.selenium.app.Stores_Orders;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.Stores_Orders.Stores_Orders_Postpaid_Util;
import com.digi.selenium.util.Stores_Orders.Stores_Orders_Prepaid_Util;

public class Store_Orders_Postpaid extends Stores_Orders_Postpaid_Util{
	
	//logger 
	private  Logger log = Logger.getLogger(Store_Orders_Prepaid.class);
	
	@BeforeClass
	public void initDriver() throws Exception {
		setUp();
	}
	
	//added by pramod
	
	 @Test(priority=3,enabled=true,groups={"TICKET-01","Regression"})
	 public void validateStoreNordersEmail_id_Postpaid() throws InterruptedException {
		 validateParameters();
		 System.out.println("email id: "+getemailid().replace('|', ' ').trim()+ " = "+getSourceData(AppConstants.STORES_ORDERS_EMAILID) );
		 Assert.assertEquals(getemailid().replace('|', ' ').trim(), getSourceData(AppConstants.STORES_ORDERS_EMAILID));		 
	 }
	 
	 @Test(priority=3,enabled=false)
	 public void validateStoreNordersPaymentStatus_Postpaid() throws InterruptedException {
		 validatePaymentStatus();
	     for(int i=0; i<getTargetPaymentStatusList().size();i++){
	    	   String amountkey=gettKey(Integer.toString(i+1),AppConstants.STORES_ORDERS_PAYMENT_STATUS);
	    	   String amountdata=getSourceData(amountkey);
	       System.out.println("TargetAmount-->"+getTargetPaymentStatusList().get(i).getText());
	       System.out.println("SourceDataAmount-->"+amountdata);
	       Assert.assertEquals(getTargetPaymentStatusList().get(i).getText(), amountdata);	
	  } 
	 }
	 
	 

	 
	 
	 @Test(priority=1,enabled=false)
	  public void validateStoreNordersOrderDate_Postpaid() throws InterruptedException {
		 validateParameters();
		for(int j=1;j<=(getTargetStoresOrders().size()/5);j++)
		{
			String orderDateSourcekey=gettKey(Integer.toString(j),AppConstants.STORES_ORDERS_SOURCE_ORDER_DATE);
			String orderDateTargetkey=gettKey(Integer.toString(j),AppConstants.STORES_ORDERS_TARGET_ORDER_DATE);
			
				//System.out.println(getTargetStoresOrders().get("Order Date["+(j)+"]") + "==" + getSourceData("ORDER_DATE["+j+"]"));
				System.out.println(getTargetStoresOrders().get(orderDateTargetkey)+"=="+ getSourceData(orderDateSourcekey));;
				Assert.assertEquals(getTargetStoresOrders().get(orderDateTargetkey), getSourceData(orderDateSourcekey));	

			}
	 }
	

 


	 
	 
	 @Test(priority=1,enabled=false)
	  public void validateStoreNordersPaymentChannel_Postpaid() throws InterruptedException {
		 validateParameters();
		for(int j=1;j<=(getTargetStoresOrders().size()/5);j++)
		{
			String orderDateSourcekey=gettKey(Integer.toString(j),AppConstants.STORES_ORDERS_SOURCE_PAYMENT_CHANNEL);
			String orderDateTargetkey=gettKey(Integer.toString(j),AppConstants.STORES_ORDERS_TARGET_PAYMENT_CHANNEL);
			
				System.out.println(getTargetStoresOrders().get(orderDateTargetkey)+"=="+ getSourceData(orderDateSourcekey));;
				Assert.assertEquals(getTargetStoresOrders().get(orderDateTargetkey), getSourceData(orderDateSourcekey));	

			}
	 }
	 
	 @Test(priority=1,enabled=false,groups={"Sanity"})
	  public void validateStoreNordersOrderNo_Postpaid() throws InterruptedException {
		 validateParameters();
		for(int j=1;j<=(getTargetStoresOrders().size()/5);j++)
		{
			String orderDateSourcekey=gettKey(Integer.toString(j),AppConstants.STORES_ORDERS_SOURCE_ORDER_NO);
			String orderDateTargetkey=gettKey(Integer.toString(j),AppConstants.STORES_ORDERS_TARGET_ORDER_NO);
			
				System.out.println(getTargetStoresOrders().get(orderDateTargetkey)+"=="+ getSourceData(orderDateSourcekey));;
				Assert.assertEquals(getTargetStoresOrders().get(orderDateTargetkey), getSourceData(orderDateSourcekey));	

			}
	 }
	 
	 @Test(priority=1,enabled=true,groups={"Regression"})
	  public void validateStoreNordersTotalAmount_Postpaid() throws InterruptedException {
		 validateTotalNTrackDelivery();
		for(int j=1;j<=(getTargetTotalNtrack().size()/2);j++)
		{
			String totalAmountSourcekey=gettKey(Integer.toString(j),AppConstants.STORES_ORDERS_SOURCE_TOTAL_AMOUNT);
			String totalAmountTargetkey=gettKey(Integer.toString(j),AppConstants.STORES_ORDERS_TARGET_TOTAL_AMOUNT);
			
				System.out.println(getTargetTotalNtrack().get(totalAmountTargetkey)+"=="+ getSourceData(totalAmountSourcekey));;
				Assert.assertEquals(getTargetTotalNtrack().get(totalAmountTargetkey), getSourceData(totalAmountSourcekey));	

			}
	 }
	 



 
	@AfterClass
	public void terminateBrowser(){
		log.info("ShutDown : Digi_Login_Page");
		shutDown();
	}
	
	@AfterSuite
	public void terminateBrowseragain(){
		log.info("ShutDown : Digi_Login_Page");
		shutDown();
	}
	
}
