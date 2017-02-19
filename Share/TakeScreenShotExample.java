package com.digi.selenium.app;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class TakeScreenShotExample extends TakeScreenShotExampleUtil{
	
	@BeforeClass
	public void initDriver()  {
		setUp();
	}
	
	 @Test(priority=1)
     public void openBrowser() 
 
     {
		 readTargetData();
     }
	 
	 @Test(priority=2)
     public void dummyBrowser() 
 
     {
		 String actual = "Sample";
		 String expected = "Sample";
		 Assert.assertEquals(actual, expected);
     }

	
	@AfterClass
	public void terminateBrowser(){
		shutDown();
	}
}
