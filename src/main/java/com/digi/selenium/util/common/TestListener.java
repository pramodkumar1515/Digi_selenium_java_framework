package com.digi.selenium.util.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.preventers.AppContextLeakPreventer;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestListener extends GroupRunnerListner implements ITestListener  {

	//logger 
	private  Logger log = Logger.getLogger(TestListener.class);
	
	/**
	 * Method is invoked when error occurs
	 */
    public void onTestFailure(ITestResult result) {

    	String methodName=result.getName().toString().trim();
    	//System.out.println("methodName :" +methodName );
    	 
    	//take the screen shot
        int testResult = Reporter.getCurrentTestResult().getStatus();
        Reporter.log("<b align=\"left\">Status:</b> " + "<font face='verdana' color='red'>FAILED</font>"+"<br />");
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
			      
			     String errMessage = getErrorMessage(result, fileName);
			     //System.out.println("Message :" + errMessage);
			     
			     
			      
			      Reporter.log("<a href=\"" + absoluteFilePath + "\"><b align=\"left\">Error screenshot at " + new Date()+ "</b>");
			      Reporter.log("<p><img width=\"100\" src=\"" + absoluteFilePath  + "\" alt=\"screenshot at " + new Date()+ "\"/></p></a><br />");
			      log.error(errMessage);
			      
				
			} catch (IOException e) {
				log.error(e);
		  }
    }
    
    //generate the error message
    private String getErrorMessage(ITestResult result, String fileName){

    	String returnErrMsg = fileName.concat(AppConstants.COLON);
    	 StackTraceElement[] arrSTE = result.getThrowable().getStackTrace();
	      int arrSize = arrSTE.length;
	      
	      
	      for (int i=0; i<arrSize; i++){
	    	  
	    	  String errElMsg = arrSTE[i].toString();
	    	  
	    	  returnErrMsg = returnErrMsg + AppConstants.NEW_LINE + AppConstants.GREATER_SIGN + errElMsg;
	      }
	      
	      
	      
	      return returnErrMsg;
	      
    	
    }
	
    public void onFinish(ITestContext context) {}
  
    public void onTestStart(ITestResult result) {
    	
		Test test = result.getMethod().getMethod().getAnnotation(Test.class);
		Set groups = new HashSet();
		for (String group : test.groups()) {
			groups.add(group);
		}
		   
			Reporter.log("<b align=\"left\">Method Name:</b>" + result.getMethod().getMethodName().toString()+"<br />");
			Reporter.log("<b align=\"left\">Test Class:</b> " + result.getMethod().getTestClass().getName().toString()+"<br />");
	    	Reporter.log("<b align=\"left\">Groups Associated:</b> " + groups.toString()+"<br />");
    }
  
    public void onTestSuccess(ITestResult result) {
    	
        int testResult = Reporter.getCurrentTestResult().getStatus();
        if(ITestResult.FAILURE == testResult){
        	Reporter.log("<b align=\"left\">Status:</b> " + "<font face='verdana' color='red'>FAILED</font>"+"<br />");
        }else if(ITestResult.SUCCESS == testResult){
        	Reporter.log("<b align=\"left\">Status:</b> " + "<font face='verdana' color='green'>SUCCESS</font>"+"<br />");       
        }
    }

    public void onTestSkipped(ITestResult result) {   }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) {   }

}  