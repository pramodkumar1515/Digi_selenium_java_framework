package com.digi.selenium.util.common;


import org.testng.IExecutionListener;

public class ExecutionListener extends emailReport implements IExecutionListener  {
	private long startTime;

	//@Override
	public void onExecutionStart() {
		startTime = System.currentTimeMillis();
		System.out.println("TestNG is going to start");		
	}

	//@Override
	public void onExecutionFinish() {
		System.out.println("TestNG has finished, took around " + (System.currentTimeMillis() - startTime) + "ms");
		//sendEmail();
	}
}