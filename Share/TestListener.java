package com.digi.selenium.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {

	//logger 
	private  Logger log = Logger.getLogger(TestListener.class);
	
	/**
	 * Method is invoked when error occurs
	 */
    public void onTestFailure(ITestResult result) {

    	String methodName=result.getName().toString().trim();
    	//System.out.println("methodName :" +methodName );
    	
    	//take the screen shot
    	takeScreenShot(methodName, result);

    }
    
    /**
     * Take the screenshot  
     * @param methodName - where screenshot is required
     * @param result
     */
    public void takeScreenShot(String methodName, ITestResult result) {
    	File ssFile = null;
    	WebDriver ssDriver= PageNavigation.getSsWebSriver();
    	 File scrFile = ((TakesScreenshot)ssDriver).getScreenshotAs(OutputType.FILE);
    	 Properties appProp = PageNavigation.prop;
 
            try {
            	String filePath = appProp.getProperty(AppConstants.ERROR_SCREENSHOT_FILE_PATH);
            	
            	String className=result.getTestClass().getRealClass().getName();
            	
            	String fileName = className.concat(AppConstants.DOT).concat(methodName);

            	String ssFilePath=  appProp.getProperty(AppConstants.ERROR_SCREENSHOT_FILE_PREFIX).
            						concat(AppConstants.UNDER_SCORE).
	            					concat(fileName).
	            					concat(AppConstants.UNDER_SCORE).
	           	                    concat((new SimpleDateFormat(appProp.getProperty(AppConstants.ERROR_SCREENSHOT_FILE_DATEFORMAT)).format(new Date()))).
	           	                    concat(AppConstants.DOT).
	           	                    concat(appProp.getProperty(AppConstants.ERROR_SCREENSHOT_FILE_EXTN)) ;

            	ssFile = new File(filePath, ssFilePath);
            	
            	//copy the file at destination 
				FileUtils.copyFile(scrFile, ssFile);
				
				//Set the system property
				System.setProperty("org.uncommons.reportng.escape-output", "false");
				  
			      String absoluteFilePath = ssFile.getAbsolutePath();

			      Reporter.log("<a href=\"" + absoluteFilePath + "\"><p align=\"left\">Error screenshot at " + new Date()+ "</p>");
			      Reporter.log("<p><img width=\"100\" src=\"" + absoluteFilePath  + "\" alt=\"screenshot at " + new Date()+ "\"/></p></a><br />");
				
			} catch (IOException e) {
				log.error(e);
		  }
    }
	
    public void onFinish(ITestContext context) {}
  
    public void onTestStart(ITestResult result) {   }
  
    public void onTestSuccess(ITestResult result) {   }

    public void onTestSkipped(ITestResult result) {   }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) {   }

}  