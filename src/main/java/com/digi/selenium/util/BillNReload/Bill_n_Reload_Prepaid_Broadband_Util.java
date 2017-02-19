package com.digi.selenium.util.BillNReload;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.common.PageNavigation;

public class Bill_n_Reload_Prepaid_Broadband_Util extends PageNavigation{
	
	//logger class
			private  Logger log = Logger.getLogger(Bill_n_Reload_Prepaid_Broadband_Util.class);
			
			
			private String actualAccountIdName = new String();
			
			private String actualPlanName = new String();
			
			private String actualPlanId = new String();
			
			private String actualTotalReload = new String();
			
			//static or dynamic data flag
			private  Properties sourceDataStatus = new Properties();

			//static data 3
			private  Properties sourceStaticData = new Properties();
			
			//Store the BillCycle ,Graph - color , tooltip data - 90 days
			private Map<String,Map> targetBCGraphData_90Days= new HashMap();
			
			//Store the BillCycle ,Graph - color , tooltip data - 60 days
			private Map<String,Map> targetBCGraphData_60Days= new HashMap();
			private List<String> graphToolTipData_30Days = new ArrayList<String>();

			private List<String> graphBillCycleDate_30Days=new ArrayList<String>();
			
			private Map<String,String> targetBCGraphData_30Days = new HashMap();
			
			private Map<String,List<String>> dropDownTableHeaderMap= new HashMap<String, List<String>>();
			
			private List<WebElement> dropdownmonths=new ArrayList<WebElement>();
			
			private List<WebElement> listyaxischild=new ArrayList<WebElement>();
			
			private List<String> headerlist=new ArrayList<String>();
			private List<String> tabledatalist=new ArrayList<String>();

			private String airtimeamt="";
			
			private String actualmsisdn="";
			
			private String finalamt="";
			
			private String transactionno="";
			
			private String totalamt="";
			
			//protected  void setUp( String countryPrefix, String mssidn , String Password){
			protected  void setUp(){
				loadSetUpConfig();
				setUpDriver();
				signInWithMSSIDN_stage(PageNavigation.prop.getProperty(AppConstants.COUNTRY_PREFIX),
								 PageNavigation.prop.getProperty(AppConstants.BILLS_N_RELOAD_PREPAID_BROADBAND_MSSIDN),
								 PageNavigation.prop.getProperty(AppConstants.BILLS_N_RELOAD_PREPAID_BROADBAND_PASS));
				try{
				movetoBillsReloads();
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				readSourceDataFiles();
			}

			/**
		 	 * Reads the Bills n Reload page source data
		 	 * to get the dynamic/static data
		 	 */
		     private void readSourceDataFiles(){
		    	String statusFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
		    											   .concat(AppConstants.BILLSNRELOAD_DATASTATUSFILE_DATASTATUS_PREPAID_BROADBAND);
		    	sourceDataStatus = loadFileData(statusFileName);
		    	
		    	String dataFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
														 .concat(AppConstants.BILLSNRELOAD_DATASTATUSFILE_DATA_PREPAID_BROADBAND);
		    	sourceStaticData =  loadFileData(dataFileName);
		    	/*System.out.println("Source Data Status :"+ sourceDataStatus);
		    	System.out.println("Source Static Data :"+ sourceStaticData);*/
		     }
		    
		     /**
		      * Get the static source data 
		      */
		     
		     protected String getSourceData(String inputData){
		    	 String returnData = null;
		    	 
		    	 if(sourceDataStatus.containsKey(inputData)){
		    		 returnData = findSourceData(inputData);
		    		 log.info("Success : Static Source data '"+ inputData + "' -->"+returnData );
		    		// System.out.println("Success : Static Source data '"+ inputData + "' -->"+returnData );
		    	 }else{
		    		 returnData= "Fail :" + inputData + " Not found in Status File" ;
		    		 log.error("Fail :" + inputData + " Not found in Status File");
		    	 }
		    	 
		    	 return returnData;
		     }
		    
		     /**
		      * read the static source data 
		      * @param inputData
		      * @return
		      */
		     private String findSourceData(String inputData){
		    	 String returnData = null;
		    	 String status = sourceDataStatus.getProperty(inputData);
		    	 if(status.equalsIgnoreCase(AppConstants.YES)){
		    		 returnData = sourceStaticData.getProperty(inputData);
		    	 }
		    	 return returnData;
		     }
			
						
			/* Description: Navigating to bills and reload Page
			* Parameters:  mssidn ,Password 
			* Return: No return
			*/		
				protected void movetoBillsReloads() throws InterruptedException
				{
					boolean breakIt;
					while(true){
                       breakIt=true;
						try{
				    waitForPageLoad(60);
				    handleLink_ByText("MyDigi");
				    waitForPageLoad(30);
				    waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div[13]/div/div")));
					  
				    getDriver().findElement(By.xpath("//*[@id='content']/div[13]/div/div/div[2]/button[1]")).click();
				   Thread.sleep(4000);  
				    handleLink_ByText("Bills & Reloads");
				}catch(Exception e){
					e.printStackTrace();
					breakIt=false;
				}
						if(breakIt){
							break;
						}
					}
				}
				
				//Gets the color key
				protected String getCOLOR_KEY(String keyData){
					String returnData = null;
					returnData = AppConstants.COLOR
											.concat(AppConstants.BEGIN_BRACKET)
											.concat(keyData)
											.concat(AppConstants.CLOSE_BRACKET);
					
					return returnData;
				}
				
				//Gets the tool tip text key
				protected String getBILL_CYCLE_KEY(String keyData){
					String returnData = null;
					returnData = AppConstants.BILL_CYCLE
											.concat(AppConstants.BEGIN_BRACKET)
											.concat(keyData)
											.concat(AppConstants.CLOSE_BRACKET);
					
					return returnData;
				}
				
				//Gets the tool tip text key
				protected String getTOOL_TIP_KEY(String keyData){
					String returnData = null;
					returnData = AppConstants.TOOL_TIP_TEXT
											.concat(AppConstants.BEGIN_BRACKET)
											.concat(keyData)
											.concat(AppConstants.CLOSE_BRACKET);
					
					return returnData;
				}
				
				protected String getCELL_KEY(String keyData){
					String returnData=null;
					returnData= AppConstants.CELL
							                .concat(AppConstants.BEGIN_BRACKET)
							                .concat(keyData)
							                .concat(AppConstants.CLOSE_BRACKET);
					return returnData;
							               
				}

				protected String getY_AXIS_KEY(String keyData){
					String returnData = null;
					returnData = AppConstants.Y
											.concat(AppConstants.BEGIN_BRACKET)
											.concat(keyData)
											.concat(AppConstants.CLOSE_BRACKET);
					
					return returnData;
				}

				
				protected void targetAccountIdName(){
					try{
							waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='plan-name']")));
							//String expectedAccountIdName = "Phoenix Project 413701757";
							actualAccountIdName = getWebElement_By_xpath("//*[@id='accountIdName']").getText();

							//System.out.println("Plan ID : "+ actualPlanId);
						}
						catch (Exception e) {
							log.error("Fail : To validate the target Account ID name");
						}
				}

				
					/* Description: Testing the Plan Name
					* Parameters: Not required
					* Return: No return
					*/		
				protected void targetplanName(){
					try{
								
						waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='plan-name']/strong")));
						String expectedPlanName = "Phoenix Project";
						actualPlanName = getWebElement_By_xpath("//*[@id='plan-name']/strong").getText();
						//System.out.println("Plan Name : "+ actualPlanName);
						}
						catch (Exception e) {
							log.error("Fail : To validate the target plan name");
						}
						
					}
					
				/* Description: Testing the Plan_ID
				* Parameters:  Not required
				* Return: No return
				*/		
				protected void targetplanId(){
							
					try{
						waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='plan-detail']")));
						//String expectedPlanId = "413701757";
						actualPlanId = getWebElement_By_xpath("//*[@id='plan-detail']").getText();

						//System.out.println("Plan ID : "+ actualPlanId);
						}
					catch (Exception e) {
						log.error("Fail : To validate the target plan ID");
						}
					}
				
				
				/* Description: Testing the Total Reload
				* Parameters:  Not required
				* Return: No return
				*/		
				
				protected void targetTotalReload(){
					try{
						JavascriptExecutor jse = (JavascriptExecutor)getDriver();
						jse.executeScript("window.scrollBy(0,300)", "");  
						waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div[9]/div[2]/div/div[1]/div[2]/p")));
						//String expectedTotalReload = "RM 0.00";
						//*[@id="content"]/div[9]/div[2]/div/div[1]/div[2]/p
						actualTotalReload = getWebElement_By_xpath("//*[@id='content']/div[9]/div[2]/div/div[1]/div[2]/p").getText();
						System.out.println("actualTotalReload : "+ actualTotalReload);
						jse.executeScript("window.scrollBy(0,-300)", "");  
						}
						catch (Exception e) {
							log.error("Fail : To validate the target Total Reload");
						}
						
					}
				
				protected void target_reloadbtn() {
					JavascriptExecutor jse=(JavascriptExecutor)getDriver();
					  jse.executeScript("window.scrollBy(0,-500)", "");
					  boolean breakIt;
					  jse.executeScript("window.scrollBy(0,400)", "");
					while(true){
						   breakIt=true;
					try {
						//*[@id="planDetails"]/div[2]/div[1]/div[4]/a
						WebElement reload=getDriver().findElement(By.linkText("Reload balance"));
						reload.click();
						log.info("Success: Reload button is clicked");
						waitForPageLoad(100);
					}catch(Exception e) {
						log.error("Fail : Failed to click on reload button");
						breakIt=false;
						//System.out.println(e);
						//e.printStackTrace();
						//e.printStackTrace();
						//System.exit(0);
					}
					if(breakIt){
						break;
					}
				}
				}
				protected void targetAirTimeAmtForPayment() {
			 		try {
			 			WebElement airtime=getDriver().findElement(By.xpath("//*[@id='mainContent']/div/div/div/div/form/table/tbody/tr[2]/td[2]"));
			 			airtimeamt=airtime.getText();
			 		}catch(Exception e) {
			 			log.error("Fail : To validate the targetAir time amount on Payment Page");
			 		}
			 	}
				protected void targetTotalAmtForPayment() {
			 		try {
			 			WebElement total=getDriver().findElement(By.xpath("//*[@id='mainContent']/div/div/div/div/form/table/tbody/tr[3]/td[2]"));
			 			totalamt=total.getText();
			 		}catch(Exception e) {
			 			log.error("Fail : To validate the targetAir time amount on Payment Page");
			 		}
			 	}

				protected void targetMsisdnForPaytment() {
			 		try {
			 			WebElement msisdn=getDriver().findElement(By.xpath("//*[@id='mainContent']/div/div/div/div/form/table/tbody/tr[1]/td[2]/span"));
			 			actualmsisdn=msisdn.getText();
			 		}catch(Exception e) {
			 			log.error("Fail : To validate the target MSISDN on Payment Page");
			 		}
			 	}
				protected void targetFinalAmt() {
			 		try {
			 			//*[@id='content']/div/div/div[1]/div[9]/div[2]/strong
			 			WebDriverWait wait=new WebDriverWait(getDriver(), 20);
			 			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div/div/div[1]/div[9]/div[2]/strong")));
			 			WebElement amt=getDriver().findElement(By.xpath("//*[@id='content']/div/div/div[1]/div[9]/div[2]/strong"));
			 			finalamt=amt.getText();
			 		}catch(Exception e) {
			 			log.error("Fail : To validate the targetAir time amount on Payment Page");
			 		}
			 	}

				protected void targetValidateTransactionNo() {
					try {
						//*[@id='mainContent']/div/div/div/div/div[1]/strong
						WebElement tno=getDriver().findElement(By.xpath("//*[@id='mainContent']/div/div/div/div/div[1]/strong"));
						transactionno=tno.getText();
					}catch(Exception e) {
						log.error("Fail : To validate the Transaction Number");
					}
				}
				protected void clickOnRadioRM50() {
					boolean breakIt;
					while(true){
						breakIt=true;
					try {
						
					// Put email id on text field as it can not be blank
						//*[@id="reloadForm__email"]
						WebElement emailtxt=getDriver().findElement(By.id("reloadForm__email"));
						System.out.println(emailtxt.isEnabled());
						if(emailtxt.isEnabled()) {
						handleInputField_ByID("reloadForm__email", AppConstants.EMAIL);
						Thread.sleep(500);
						}
					JavascriptExecutor js1 = (JavascriptExecutor)getDriver();
					//js1.executeScript("window.scrollBy(0,400)", "");
					//*[@id="prepaidReloadForm"]/ul/li[2]/label/div
					WebElement radio=getDriver().findElement(By.xpath("//*[@id='prepaidReloadForm']/ul/li[4]/label/div"));
					radio.click();
					// now select check box to accept license agreement
					WebElement checkbox=getDriver().findElement(By.xpath("//*[@id='prepaidReloadForm']/label/div"));
					checkbox.click();
					// click on proceed button after selecting radio button and check box
					// WebElement elementproceed=getDriver().findElement(By.xpath("//*[@id='proceedBtn']"));
					//*[@id="proceedBtn"]
					WebElement proceed=(new WebDriverWait(getDriver(), 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceedBtn']")));
					proceed.click();
					waitForPageLoad(100);
					Thread.sleep(500);
					JavascriptExecutor js = (JavascriptExecutor)getDriver();
					js.executeScript("window.scrollBy(0,500)", "");
					}catch(Exception e) {
						System.out.println(e);
						breakIt=false;
						e.printStackTrace();
						log.error("Fail : Failed to Reload amount.");
						System.exit(0);
					}
					if(breakIt){
						break;
					}
					}//while ends
				}//method ends
				
				protected void targetYAxisData(int index){
					
				JavascriptExecutor jse = (JavascriptExecutor)getDriver();
				jse.executeScript("window.scrollBy(0,-800)", "");
				
					 getWebElement_By_xpath("//*[@id='content']/div[9]/div[1]/div[2]/ul/li["+index+"]/a").click();
					 Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);            

					if(index==1)
					 jse.executeScript("window.scrollBy(0,210)", "");
					 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][13]/*[name()='g'][2]")));       
			          //Working :get the stroke attribute to get the color value of graph
			           getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			           boolean breakIt = true;
			           
			           //Get the BillCycle Elements
			           String yaxisrootNodeXpath = "//*[name()='svg']/*[name()='g'][13]/*[name()='g'][2]" ;
			           WebElement yaxisrootElement = getDriver().findElement(By.xpath(yaxisrootNodeXpath));
			           String yaxischildXpath= "./*[name()='text']";
			           listyaxischild = yaxisrootElement.findElements(By.xpath(yaxischildXpath));
			          int eleListSize = listyaxischild.size(); 
			          // String targetrootdatapath= "//*[@id='content']/div[9]/div[1]/div[1]/div[2]/div/div/svg/g[13]/g[2]";
			          if(index==1)
							System.out.println("===========30 Days Y-AxisData========");
						else{
							if(index==2)
							System.out.println("===========60 Days Y-AxisData========");
							else
								System.out.println("===========90 Days Y-AxisData========");
						}
				}	
			    protected void targetTableData() {
			    	Wait<WebDriver> waittable= new WebDriverWait(getDriver(), 30);
				 JavascriptExecutor jse = (JavascriptExecutor)getDriver();
            	 //if(index>0)
            		 jse.executeScript("window.scrollBy(0,-800)", ""); 
            	  getWebElement_By_xpath("//*[@id='content']/div[9]/div[1]/div[2]/ul/li[1]").click();
	          
	          jse.executeScript("window.scrollBy(0,800)", "");              
	          waittable.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='details-table']")));       
	         
			WebElement headertable = getDriver().findElement(By.xpath("//*[@id='details-table']"));
			List<WebElement> header = headertable.findElements(By.xpath("//*[@id='billhistory']/tr"));
			System.out.println("===========Table Data========");
			 
		// And iterate over them, getting the header data
				for (WebElement node : header) {
				 List<WebElement> cells = node.findElements(By.tagName("th"));
				
				 for (WebElement cell : cells) 
					headerlist.add(cell.getText());
				}
			// Now get all the TR elements from the table
						WebElement table = getDriver().findElement(By.xpath("//*[@id='details-table']/tbody"));
						List<WebElement> rows = table.findElements(By.tagName("tr"));
						// And iterate over them, getting the cells
						for (WebElement row : rows) {
						 List<WebElement> rowcells = row.findElements(By.tagName("td"));
						 
						 for (WebElement cell : rowcells) 
							 tabledatalist.add(cell.getText()); 
			    }
			    }
				

				/**
				 * Read the 90Days Graph data
				 */
				protected void targetGraphData_90Days()
			       {			
					Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);            
			          JavascriptExecutor jse = (JavascriptExecutor)getDriver();
			                 
			          Map<String, String> mapbill = new HashMap<String, String>();
			          jse.executeScript("window.scrollBy(0,-500)", "");
			          jse.executeScript("window.scrollBy(0,225)", ""); 
			          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][13]/*[name()='g'][1]")));       
			          handleLink_ByText(AppConstants.DAYS_90); 
			          //Working :get the stroke attribute to get the color value of graph
			           getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			           boolean breakIt = true;
			           
			           //Get the BillCycle Elements
			           String billcyclerootNodeXpath = "//*[name()='svg']/*[name()='g'][13]/*[name()='g'][1]" ;
			           WebElement billcyclerootElement = getDriver().findElement(By.xpath(billcyclerootNodeXpath));
			           
			           String billcyclechildXpath = "./*[name()='text']"; 

			           List<WebElement> listbillcyclechild = billcyclerootElement.findElements(By.xpath(billcyclechildXpath));
			           
			           int eleListSize = listbillcyclechild.size();
			          // System.out.println("listChildElements.size() : " + listbillcyclechild.size());
			           
			           //Get the BillCycle Graph , tooltip Elements
			           String bcGraphRootNodeXpath = "//*[name()='svg']/*[name()='g'][6]/*[name()='g']" ;
			           WebElement bcGraphRootElement = getDriver().findElement(By.xpath(bcGraphRootNodeXpath));
			           
			           String bcGraphChildXpath = "./*[name()='g']"; 

			           List<WebElement> listbcGraphChild = bcGraphRootElement.findElements(By.xpath(bcGraphChildXpath));
			           int bcGraphListSize = listbcGraphChild.size();
			           if (bcGraphListSize >0){
			        	   targetBCGraphData_90Days = new HashMap();
			           }
			          // System.out.println("bcGraphListSize.size() : " + listbcGraphChild.size());
			           
		               for(int j=0; j<eleListSize; j++){
			              int n = j+1;
			              Map<String,String> tempBCGData = new HashMap();
			              String billCycleDate = null;
			              String bcAtrribute_color = null;
			              while (true) {
			                  breakIt = true;
			                  try {
			                	  
			                	 //read the billcycle data from nodes 
			                	String billchildNodePath_startDate = billcyclerootNodeXpath.concat("/*[name()='text']")
			                			 																		.concat("[").concat(String.valueOf(n).concat("]")
			                			 																		.concat("/*[name()='tspan'][1]")
			                			 																				);        
			                     WebElement billelement_startDate = getDriver().findElement(By.xpath(billchildNodePath_startDate));
			                     //Thread.sleep(2000);
			                     String billAtrribute_startDate =billelement_startDate.getText();
			                     
			                     String billchildNodePath_endDate = billcyclerootNodeXpath.concat("/*[name()='text']")
																												.concat("[").concat(String.valueOf(n).concat("]")
																												.concat("/*[name()='tspan'][2]")
																														);        
								WebElement billelement_endDate = getDriver().findElement(By.xpath(billchildNodePath_endDate));
								
								String billAtrribute_endDate =billelement_endDate.getText();
			                    
								billCycleDate = billAtrribute_startDate .concat("_").concat(billAtrribute_endDate);
			                    //System.out.println("Bill Cycle : '" + n+"' -->"+  billCycleDate);

			                    
			                  //read the billcycle graph data - color, tooltip from nodes
			                	 String billchildNodePath_color = bcGraphRootNodeXpath.concat("/*[name()='g']")
																											.concat("[").concat(String.valueOf(n).concat("]")
																											.concat("/*[name()='path']"));
			                	//System.out.println("Node billchildNodePath_color : " + billchildNodePath_color);
								WebElement billelement_color = getDriver().findElement(By.xpath(billchildNodePath_color));

								bcAtrribute_color =billelement_color.getAttribute("stroke");
								String colorMap = AppConstants.COLOR
																	.concat(AppConstants.BEGIN_BRACKET)
																	.concat(billCycleDate)
																	.concat(AppConstants.CLOSE_BRACKET);
								tempBCGData.put(colorMap, bcAtrribute_color);
								Actions builder=new Actions(getDriver());
			                     
			                     String childMouseHouverNode =  bcGraphRootNodeXpath.concat("/*[name()='g']")
																					.concat("[").concat(String.valueOf(n).concat("]"));
			                     builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();

								 WebElement mouseHoverNodeElement = getDriver().findElement(By.xpath("//div[9]/div[1]/div[1]/div[2]/div/div/div"));
			                     
			                     if(mouseHoverNodeElement != null) {
			                    	 String toolTipText = mouseHoverNodeElement.getText();
				                     //System.out.println("toolTipText : '" + n+"' "+toolTipText );
			                    	 String toolTipTextMap = AppConstants.TOOL_TIP_TEXT.
			                    			 										    concat(AppConstants.BEGIN_BRACKET).
			                    			 										    concat(billCycleDate).
			                    			 										    concat(AppConstants.CLOSE_BRACKET);
				                     //tempBCGData.put(toolTipTextMap, toolTipText.replaceAll("\n", ""));
			                    	 tempBCGData.put(toolTipTextMap, toolTipText);
				                     builder.moveToElement(getDriver().findElement(By.xpath(billchildNodePath_startDate))).click().perform();
			                     }
			                     

			                  } catch (Exception e) {
			                      if (e.getMessage().contains("element is not attached")) {
			                            //System.out.println("Error: Element 4 ");
			                          breakIt = false;
			                      }
			                     }
			                  if (breakIt) {
			                      break;
			                  }
			              
			           }//while loop ends
			              targetBCGraphData_90Days.put(billCycleDate, tempBCGData);
			         }//for loop ends
			       }

				protected void targetGraphData_60Days()
			       {
					JavascriptExecutor jse = (JavascriptExecutor)getDriver();
			          Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);            
			          jse.executeScript("window.scrollBy(0,-500)", ""); 
			          jse.executeScript("window.scrollBy(0,225)", ""); 
			          handleLink_ByText(AppConstants.DAYS_60); 
			          Map<String, String> mapbill = new HashMap<String, String>();
			                  
			          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][13]/*[name()='g'][1]")));       
			          //Working :get the stroke attribute to get the color value of graph
			           getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			           boolean breakIt = true;
			           
			           //Get the BillCycle Elements
			           String billcyclerootNodeXpath = "//*[name()='svg']/*[name()='g'][13]/*[name()='g'][1]" ;
			           WebElement billcyclerootElement = getDriver().findElement(By.xpath(billcyclerootNodeXpath));
			           
			           String billcyclechildXpath = "./*[name()='text']"; 

			           List<WebElement> listbillcyclechild = billcyclerootElement.findElements(By.xpath(billcyclechildXpath));
			           
			           int eleListSize = listbillcyclechild.size();
			          // System.out.println("listChildElements.size() : " + listbillcyclechild.size());
			           
			           //Get the BillCycle Graph , tooltip Elements
			           String bcGraphRootNodeXpath = "//*[name()='svg']/*[name()='g'][6]/*[name()='g']" ;
			           WebElement bcGraphRootElement = getDriver().findElement(By.xpath(bcGraphRootNodeXpath));
			           
			           String bcGraphChildXpath = "./*[name()='g']"; 

			           List<WebElement> listbcGraphChild = bcGraphRootElement.findElements(By.xpath(bcGraphChildXpath));
			           int bcGraphListSize = listbcGraphChild.size();
			           if (bcGraphListSize >0){
			        	   targetBCGraphData_60Days = new HashMap();
			           }
			          // System.out.println("bcGraphListSize.size() : " + listbcGraphChild.size());
			           
		               for(int j=0; j<eleListSize; j++){
			              int n = j+1;
			              Map<String,String> tempBCGData = new HashMap();
			              String billCycleDate = null;
			              String bcAtrribute_color = null;
			              while (true) {
			                  breakIt = true;
			                  try {
			                	  
			                	 //read the billcycle data from nodes 
			                	String billchildNodePath_startDate = billcyclerootNodeXpath.concat("/*[name()='text']")
			                			 																		.concat("[").concat(String.valueOf(n).concat("]")
			                			 																		.concat("/*[name()='tspan'][1]")
			                			 																				);        
			                     WebElement billelement_startDate = getDriver().findElement(By.xpath(billchildNodePath_startDate));
			                     //Thread.sleep(2000);
			                     String billAtrribute_startDate =billelement_startDate.getText();
			                     
			                     String billchildNodePath_endDate = billcyclerootNodeXpath.concat("/*[name()='text']")
																												.concat("[").concat(String.valueOf(n).concat("]")
																												.concat("/*[name()='tspan'][2]")
																														);        
								WebElement billelement_endDate = getDriver().findElement(By.xpath(billchildNodePath_endDate));
								
								String billAtrribute_endDate =billelement_endDate.getText();
			                    
								billCycleDate = billAtrribute_startDate .concat("_").concat(billAtrribute_endDate);
			                    //System.out.println("Bill Cycle : '" + n+"' -->"+  billCycleDate);

			                    
			                  //read the billcycle graph data - color, tooltip from nodes
			                	 String billchildNodePath_color = bcGraphRootNodeXpath.concat("/*[name()='g']")
																											.concat("[").concat(String.valueOf(n).concat("]")
																											.concat("/*[name()='path']"));
			                	//System.out.println("Node billchildNodePath_color : " + billchildNodePath_color);
								WebElement billelement_color = getDriver().findElement(By.xpath(billchildNodePath_color));

								bcAtrribute_color =billelement_color.getAttribute("stroke");
								String colorMap = AppConstants.COLOR
																	.concat(AppConstants.BEGIN_BRACKET)
																	.concat(billCycleDate)
																	.concat(AppConstants.CLOSE_BRACKET);
								tempBCGData.put(colorMap, bcAtrribute_color);
								Actions builder=new Actions(getDriver());
			                     
			                     String childMouseHouverNode =  bcGraphRootNodeXpath.concat("/*[name()='g']")
																					.concat("[").concat(String.valueOf(n).concat("]"));
			                     builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();

								 WebElement mouseHoverNodeElement = getDriver().findElement(By.xpath("//div[9]/div[1]/div[1]/div[2]/div/div/div"));
			                     
			                     if(mouseHoverNodeElement != null) {
			                    	 String toolTipText = mouseHoverNodeElement.getText();
				                     //System.out.println("toolTipText : '" + n+"' "+toolTipText );
			                    	 String toolTipTextMap = AppConstants.TOOL_TIP_TEXT.
			                    			 										    concat(AppConstants.BEGIN_BRACKET).
			                    			 										    concat(billCycleDate).
			                    			 										    concat(AppConstants.CLOSE_BRACKET);
				                     tempBCGData.put(toolTipTextMap, toolTipText);
				                     builder.moveToElement(getDriver().findElement(By.xpath(billchildNodePath_startDate))).click().perform();
			                     }
			                     

			                  } catch (Exception e) {
			                      if (e.getMessage().contains("element is not attached")) {
			                            //System.out.println("Error: Element 4 ");
			                          breakIt = false;
			                      }
			                     }
			                  if (breakIt) {
			                      break;
			                  }
			              
			           }//while loop ends
			              targetBCGraphData_60Days.put(billCycleDate, tempBCGData);
			         }//for loop ends
			       }

				/**
				 * Read the 30Days Graph data
				 */
				protected void targetGraphData_30Days()
			       {
					JavascriptExecutor jse = (JavascriptExecutor)getDriver();
					
			          Wait<WebDriver> wait= new WebDriverWait(getDriver(), 15);            
			        
			          Map<String, String> mapbill = new HashMap<String, String>();
			          jse.executeScript("window.scrollBy(0,-500)", ""); 
			          jse.executeScript("window.scrollBy(0,225)", ""); 
			          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][13]/*[name()='g'][1]")));       
			          handleLink_ByText(AppConstants.DAYS_30);

			          boolean breakIt = true;
			           
			           //Get the BillCycle Elements
			           String billcyclerootNodeXpath = "//*[name()='svg']/*[name()='g'][13]/*[name()='g'][1]" ;
			           WebElement billcyclerootElement = getDriver().findElement(By.xpath(billcyclerootNodeXpath));
			           
			           String billcyclechildXpath = "./*[name()='text']"; 

			           List<WebElement> listbillcyclechild = billcyclerootElement.findElements(By.xpath(billcyclechildXpath));
			           
			           int eleListSize = listbillcyclechild.size();
			            System.out.println("listChildElements.size() : " + listbillcyclechild.size());
			           
			           //Get the BillCycle Graph , tooltip Elements
			           String bcGraphRootNodeXpath = "//*[name()='svg']/*[name()='g'][6]/*[name()='g']" ;
			           WebElement bcGraphRootElement = getDriver().findElement(By.xpath(bcGraphRootNodeXpath));
			           
			           String bcGraphChildXpath = "./*[name()='g']"; 

			           List<WebElement> listbcGraphChild = bcGraphRootElement.findElements(By.xpath(bcGraphChildXpath));
			           int bcGraphListSize = listbcGraphChild.size();
			           if (bcGraphListSize >0){
			        	   targetBCGraphData_30Days = new HashMap();
			           }
			          // System.out.println("bcGraphListSize.size() : " + listbcGraphChild.size());
			           
		               for(int j=0; j<eleListSize; j++){
			              int n = j+1;
			              String billCycleDate = null;
			              String bcAtrribute_color = null;
			              while (true) {
			                  breakIt = true;
			                  try {
			                	  
			                	 //read the billcycle data from nodes 
			                	String billchildNodePath_startDate = billcyclerootNodeXpath.concat("/*[name()='text']")
	        			 																   .concat("[").concat(String.valueOf(n).concat("]")
			                			 												   .concat("/*[name()='tspan']")
			                			 																				);        
			                     WebElement billelement_Date = getDriver().findElement(By.xpath(billchildNodePath_startDate));

			                     billCycleDate =billelement_Date.getText();
			                    	 
			                    	 if ((billCycleDate.trim().length()>0) && (!(graphBillCycleDate_30Days.contains(billCycleDate))))  {
				                    	 graphBillCycleDate_30Days.add(billCycleDate);
				                    	 System.out.println("Bill Cycle : '" + n+"' -->"+  billCycleDate);
			                    	 }

			                    
			                  //read the billcycle graph data - color  from nodes
			                	 String billchildNodePath_color = bcGraphRootNodeXpath.concat("/*[name()='g']")
																					  .concat("[").concat(String.valueOf(n).concat("]")
																					  .concat("/*[name()='path']"));
			                	//System.out.println("Node billchildNodePath_color : " + billchildNodePath_color);
								WebElement billelement_color = getDriver().findElement(By.xpath(billchildNodePath_color));

								bcAtrribute_color =billelement_color.getAttribute("stroke");
								
								Actions builder=new Actions(getDriver());
			                     
			                     String childMouseHouverNode =  bcGraphRootNodeXpath.concat("/*[name()='g']")
																					.concat("[").concat(String.valueOf(n).concat("]"));
			                     builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();

								 WebElement mouseHoverNodeElement = getDriver().findElement(By.xpath("//div[9]/div[1]/div[1]/div[2]/div/div/div"));
								 
			                     if(mouseHoverNodeElement != null) {
			                    	 String toolTipText = mouseHoverNodeElement.getText();
			                    	 
				                     System.out.println("toolTipText : '" + n+"' "+toolTipText );
			                    	 String toolTipTextMap = AppConstants.TOOL_TIP_TEXT.
			                    			 										    concat(AppConstants.BEGIN_BRACKET).
			                    			 										    concat(Integer.toString(n)).
			                    			 										    concat(AppConstants.CLOSE_BRACKET);
			                    	 if ((toolTipText.trim().length()>0) && (!(graphToolTipData_30Days.contains(toolTipText)))){
			                    		 graphToolTipData_30Days.add(toolTipText);
			                    		 builder.moveToElement(getDriver().findElement(By.xpath(billcyclerootNodeXpath))).click().build().perform();
			                    		 Thread.sleep(3000);
			                    	 }//end if - tooltip text
			                      }//end if - mouseHoverNodeElement
			                     

			                  } catch (Exception e) {
			                      if (e.getMessage().contains("element is not attached")) {
			                          breakIt = false;
			                      }
			                     }
			                  if (breakIt) {
			                      break;
			                  }
			              
			           }//while loop ends
			              targetBCGraphData_30Days.put(Integer.toString(n), bcAtrribute_color);
			         }//for loop ends
			       }
				
				
				/** Reads the header data after selecting
				 * different options from dropdown list
				 **/
				protected void targetTableEventData() throws InterruptedException{
					Wait<WebDriver> waittable= new WebDriverWait(getDriver(), 30);            
		          	JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		          	List<WebElement> speechbubble = null;
		          	 
					boolean breakit;
					 while(true){
				           breakit=true;
				       try{
						
			           	getWebElement_By_xpath("//*[@id='content']/div[9]/div[1]/div[2]/ul/li[2]/a").click();
				          jse.executeScript("window.scrollBy(0,240)", "");              
				        waittable.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='details-table']")));       
					
				           getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
				           speechbubble=getDriver().findElements(By.xpath("//*[@id='column-filter-1']/ul/li"));
				           System.out.println("Number of ChildNodes :"+speechbubble.size());
					 }catch(Exception e){
							//e.printStackTrace();
							breakit=false;
						}
			           if(breakit==true){
			 		      break; }
	        		}
			           //jse.executeScript("window.scrollBy(0,100)", "");    

			           
					// Map<String,List<String>> testHeaderMap = new HashMap <String,List<String>>();
			       //List<String> lsheaderTableKey = new ArrayList<String>() ; 
			       List<String> lsheaderdata= null;
			       int speechbubbleSize = speechbubble.size(); 
			       String arHeaderKey[] = new String[speechbubbleSize];
			       
			       String headerKeyStr = "";
			       for(int k=0;k<speechbubbleSize;k++){
			    	  while(true){
			           breakit=true;
					           try{
					    	   jse.executeScript("window.scrollBy(0,50)", ""); 
					    	   
					    	   //click the dropdown control
					    	   waittable.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='column-filter-sb']/span[2]")));
					    	   getDriver().findElement(By.xpath("//*[@id='column-filter-sb']/span[2]")).click();
					    	   
					    	   int j = k+1;
					    	   
					    	   String nodepath = "//*[@id='column-filter-1']/ul/li" +"["+j+"]"+"/label";
					    	   waittable.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nodepath)));
					    	   WebElement childElementSub = getDriver().findElement(By.xpath(nodepath));
					   		   System.out.println("Id :'" +j+"' "+ childElementSub.getText());
					   		   String headertableKey = childElementSub.getText();
					   		   //lsheaderTableKey.add(headertableKey);
					   		   headerKeyStr = headerKeyStr + headertableKey + AppConstants.COMMA;
					   		   childElementSub.click(); //click the checkbox
					   		   
					   		   //click the update button
					    	   waittable.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='speechBubble']/div[3]/button")));
					    	   getDriver().findElement(By.xpath("//*[@id='speechBubble']/div[3]/button")).click();
					    	   getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
					    	   
					    	   //System.out.println("Id :'" +j+"' Update Button Clicked" );
					    	   
					   		   //Read the bill history table header data - begin
				   		   		waittable.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='details-table']")));
						   		WebElement headertable = getDriver().findElement(By.xpath("//*[@id='details-table']"));
								int i=0;
								waittable.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='billhistory']/tr")));
								List<WebElement> header = headertable.findElements(By.xpath("//*[@id='billhistory']/tr"));
							
								// And iterate over them, getting the header data
								for (WebElement node : header) {
								 List<WebElement> cells = node.findElements(By.tagName("th"));
								 lsheaderdata=new ArrayList<String>();
								 for (WebElement cell : cells) {
									 //System.out.println("header"+cell.getText());
									 String headerData = cell.getText();
									 lsheaderdata.add(headerData);
								  }
								}

				   		   
				   		   //Read the bill history table header data - ends
					 
					    	   jse.executeScript("window.scrollBy(0,-50)", "");
					        }catch(Exception e){
								//e.printStackTrace();
								breakit=false;
							}
				           if(breakit==true) {
				        	   int indexPos = headerKeyStr.lastIndexOf(AppConstants.COMMA);
				        	   String data = headerKeyStr.substring(0, indexPos);
				        	   arHeaderKey[k]=data;
				        	   System.out.println("lsheaderTableKey : "+data);
				        	   dropDownTableHeaderMap.put(arHeaderKey[k], lsheaderdata) ;
				 		      break;
				           }
		           		}//end  while
					}//end for
			       
			       System.out.println("DropDown Control Data : "+dropDownTableHeaderMap);

				}
				/**
				 * Reads the filter description dropdown
				 */
				
				 protected void targetEventDropdown(){            
			            
		          	 JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		          	jse.executeScript("window.scrollBy(0,175)", "");
		          	 handleLink_ByText("90 Days");
		      //    	waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("90 Days")));
		          		 jse.executeScript("window.scrollBy(0,-800)", ""); 
		          	
			          jse.executeScript("window.scrollBy(0,1000)", "");
			          waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='example-daterange']"))); 
			          getDriver().findElement(By.xpath("//*[@id='example-daterange']")).click();
			          waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div"))); 
			          getDriver().findElement(By.xpath("/html/body/div[3]/div/div[3]/button")).click();
			          boolean breakIt;
		        
		        	 while (true) {
		                 breakIt = true;
		                 try {
		        	waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tableview']/div/form/div/div[2]")));
		            Select dropdown=new Select(getDriver().findElement(By.id("filterDescription"))); 
		            String dropdownxpath= "//*[@id='filterDescription']/option";
		            List<WebElement> dropdownelements= getDriver().findElements(By.xpath(dropdownxpath));
		            System.out.println(dropdownelements.size()); 
		            
		            for(int i=0;i<dropdownelements.size();i++){
		            	waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='filterDescription']/option["+(i+1)+"]")));    	
		            	dropdown.selectByIndex(i); 
		            	System.out.println(dropdown.getOptions().get(i).getText());
		            	Thread.sleep(5000); 
		            }
		        	
		                 }
		        	 catch(Exception e){
		        		 breakIt=false;
		        		 e.printStackTrace();
		        	 }
		                 if (breakIt) {
		                     break;
		                 }   
		        	 }
		        }
				 /**
				  * Selects show filter dropdown values
				  */
				  
				  protected void targetdropdownmonths(){
					  boolean breakIt =true;
					  while(breakIt){
					  try{
					   Wait<WebDriver> waittable= new WebDriverWait(getDriver(), 30);
				 	   JavascriptExecutor jse = (JavascriptExecutor)getDriver();
				 	  jse.executeScript("window.scrollBy(0,-800)","");
				 	   jse.executeScript("window.scrollBy(0,850)","");
				 	   waittable.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='showFilters']")));
				 	   getDriver().findElement(By.xpath("//*[@id='showFilters']")).click();
				 	   dropdownmonths = getDriver().findElements(By.xpath("//*[@id='month-selector']/option"));
				 	  
				        }
					  catch(Exception e){
				        	breakIt=false;
				        }
					  if(breakIt){
						  break;
					  } 
				 }//while loop ends
			 }//method ends
				  
				protected void downloadMonthHistory(int index) {
					 getDropdownmonths().get(index).click();
		    		 waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='downloadPrepaidReport']")));
		    		 getDriver().findElement(By.xpath("//*[@id='downloadPrepaidReport']")).click(); 
		    	 System.out.println("********downloaded**********"); 
		    	   getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				}
				   /**
				    * determines whether file is present in the downloads
				    */
				   
				  protected boolean isFileDownloaded(String downloadpath,String filename){
					    
			        	boolean flag=false;
			            File dir=new File(downloadpath);
			            String[] dir_list=dir.list();
			            for(int i=0;i<dir_list.length;i++){
			            	if(dir_list[i].equals(filename)){
			            		return flag=true;
			            	}
			            }
			            return flag;
			        }

				
				protected List<WebElement> getDropdownmonths() {
					return dropdownmonths;
				}

				protected Map<String, List<String>> getDropDownTableHeaderMap() {
					return dropDownTableHeaderMap;
				}

				protected Map<String, Map> getTargetBCGraphData_90Days() {
					return targetBCGraphData_90Days;
				}
					
	
				protected String getActualAccountIdName() {
					return actualAccountIdName;
				}

				protected String getActualPlanName() {
					return actualPlanName;
				}

				protected String getActualPlanId() {
					return actualPlanId;
				}

				protected String getActualTotalReload() {
					return actualTotalReload;
				}
				
				protected Map<String, Map> getTargetBCGraphData_60Days() {
					return targetBCGraphData_60Days;
				}

				protected List<String> getGraphBillCycleDate_30Days() {
					return graphBillCycleDate_30Days;
				}

				protected Map<String, String> getTargetBCGraphData_30Days() {
					return targetBCGraphData_30Days;
				}

				protected List<String> getGraphToolTipData_30Days() {
					return graphToolTipData_30Days;
				}
				protected String getAirtimeamt() {
					return airtimeamt;
				}

				protected String getActualmsisdn() {
					return actualmsisdn;
				}

				protected String getFinalamt() {
					return finalamt;
				}

				protected String getTransactionno() {
					return transactionno;
				}

				protected String getTotalamt() {
					return totalamt;
				}
				
				protected List<WebElement> getListyaxischild() {
					return listyaxischild;
				}

				protected List<String> getHeaderlist() {
					return headerlist;
				}

				protected List<String> getTabledatalist() {
					return tabledatalist;
				}
				
}
