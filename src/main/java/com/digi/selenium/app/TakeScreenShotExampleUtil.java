package com.digi.selenium.app;

import org.openqa.selenium.By;


import com.digi.selenium.util.common.PageNavigation;

public class TakeScreenShotExampleUtil extends PageNavigation{
	
	protected  void setUp(){
		loadSetUpConfig();
		setUpDriver();
	}
	
	protected void readTargetData(){
		getDriver().get("http://www.google.com");
        getDriver().findElement(By.id("testing")).sendKeys("test");
	}

}
