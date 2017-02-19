package com.digi.selenium.util.BillNReload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.common.PageNavigation;

public class Bill_n_Reload_Postpaid_Broadband_Util extends PageNavigation{
	
	//logger class
		private  Logger log = Logger.getLogger(Bill_n_Reload_Postpaid_Broadband_Util.class);
		
		private  String actualPlanName = new String();
		
		private String actualPlanId = new String();
		
		private String actualAccountId = new String();
		
		private String actualCreditLimit = new String();
		
		private String actualDueDate = new String();
		
		private String actualOutstandingAmount = new String();
		
		private Map<String,Map> targetBCGraphData= new HashMap();
	
		private List<String> headerlist=new ArrayList<String>();
		private List<WebElement> tableDataList=new ArrayList<WebElement>();
		
	//static or dynamic data flag
		private  Properties sourceDataStatus = new Properties();

		//static data 
		private  Properties sourceStaticData = new Properties();

	   private List<WebElement> listyaxischild=new ArrayList<WebElement>();
	   
	   private Map<String,List<String>> dropDownTableHeaderMap=new HashMap<String, List<String>>();
		
	   private String rmvalue="";
	
	private String orderid="";


	/**
	 * Setup the data for page run
	 * 1.load config file
	 * 2.setup the driver
	 * 3.login with credentials
	 * 4.navigation to destination page
	 * 5.read the static source data
	 */
	protected  void setUp(){
		loadSetUpConfig();
		setUpDriver();
		signInWithMSSIDN_stage(PageNavigation.prop.getProperty(AppConstants.COUNTRY_PREFIX),
						 PageNavigation.prop.getProperty(AppConstants.BILLS_N_RELOAD_POSTPAID_Broadband_MSSIDN),
						 PageNavigation.prop.getProperty(AppConstants.BILLS_N_RELOAD_POSTPAID_Broadband_PASS));
		try{
		movetoBillsReloads();
		}catch(Exception e){
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
    											   .concat(AppConstants.BILLSNRELOAD_DATASTATUSFILE_DATASTATUS_POSTPAID_BROADBAND);
    	sourceDataStatus = loadFileData(statusFileName);
    	
    	String dataFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
												 .concat(AppConstants.BILLSNRELOAD_DATASTATUSFILE_DATA_POSTPAID_BROADBAND);
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
		    waitForPageLoad(60);
		    handleLink_ByText("MyDigi");
		    waitForPageLoad(30); //Thread.sleep(10000);
		    waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div[13]/div")));
		  
		    getDriver().findElement(By.xpath("//*[@id='content']/div[13]/div/div/div[2]/button[1]")).click();
		    //getDriver().findElement(By.cssSelector("#content > div.popup--overlay.popup-emailattention.text--telenor-light > div > div > div.title--small.padding-leader--large.text--center")).click();
		    
		    Thread.sleep(4000);
			handleLink_ByText("Bills & Reloads");
		}
		
		/* Description: Testing the Plan Name
		* Parameters: None
		* Return: None
		*/		
			protected void targetplanName(){
				try{
					
				waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='plan-name']")));
				//String expectedPlanName = "DIGI TELECOMMUNICATIONS SDN BHD ATTN WONG PEI HWA";
				 actualPlanName = getWebElement_By_xpath("//*[@id='plan-name']").getText();
				//System.out.println("Plan Name : "+ actualPlanName);
				//Assert.assertEquals(actualPlanName, expectedPlanName);
				//System.out.println("Test : Plan Name passed");
				}
				catch (Exception e) {
					log.error("Fail : To validate the target plan name");
					//Assert.fail();
				}
				
				
			}
			
			
		/* Description: Testing the Plan_ID
		* Parameters:  None
		* Return: None
		*/		
				protected void targetplanId(){
					
					try{
					waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='plan-name']")));
					//String expectedPlanId = "1100009023221";
					actualPlanId = getWebElement_By_xpath("//*[@id='plan-detail']").getText();

					//System.out.println("Plan ID : "+ actualPlanId);
					}
					catch (Exception e) {
						log.error("Fail : To validate the target plan ID");
					}
				}
			
		/* Description: Testing the Account_Id_Name
		* Parameters:  None
		* Return: None
		*/		
				protected void targetAccountIdName(){
					try{
					waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='plan-name']")));
					//String expectedAccountId = "DIGI TELECOMMUNICATIONS SDN BHD ATTN WONG PEI HWA 1100009023221";
					actualAccountId = getWebElement_By_xpath("//*[@id='accountIdName']").getText();
				}catch (Exception e) {
					log.error("Fail : To validate the target Account ID name");
				}
	}
				
		/* Description: Testing the CreditLimit
		* Parameters:  Not required
		* Return: No return
		*/		
				protected void targetCreditLimit(){
					try{				
					waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='plan-name']")));
					//String expectedCreditLimit = "RM 250.00";
					actualCreditLimit = getWebElement_By_xpath("//*[@id='content']/div[8]/div[2]/div[1]/div[2]/p").getText();
					}
					catch (Exception e) {
						log.error("Fail : To validate the credit limit");
					}
				}

				
		/* Description: Testing the Due Date
		* Parameters:  Not required
		* Return: No return
		*/		
				/*protected void targetDueDate(){
					try{						
					waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='plan-name']")));
					//String expectedDueDate = "30.8.2015";
				    actualDueDate = getWebElement_By_xpath("//*[@id='OutstandingDueDate']").getText();
					//Assert.assertEquals(expectedDueDate,actualDueDate);
					//System.out.println("Test : Due Date passed");
					}
					catch (Exception e) {
						log.error("Fail : To validate the target Due Date");
						Assert.fail();
					}
				}
				*/
		/* Description: Testing the Outstanding Amount
		* Parameters:  Not required
		* Return: No return
		*/		
				protected void targetOutstandingAmount(){
					try{								
					waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='plan-name']")));
					//String expectedOutstandingAmount = "149.88";
					actualOutstandingAmount = getWebElement_By_xpath("//*[@id='content']/div[8]/div[2]/div[2]/div[3]/p").getText();
					//System.out.println(actualOutstandingAmount);
					}
					catch (Exception e) {
						log.error("Fail : To validate the target Outstanding Amount");
					}
				}
				
				protected String getBILL_CYCLE_KEY(String keyData){
					String returnData = null;
					returnData = AppConstants.BILL_CYCLE
											.concat(AppConstants.BEGIN_BRACKET)
											.concat(keyData)
											.concat(AppConstants.CLOSE_BRACKET);
					
					return returnData;
				}
				
				protected String getCELL_KEY(String keyData){
					String returnData = null;
					returnData = AppConstants.CELL
											.concat(AppConstants.BEGIN_BRACKET)
											.concat(keyData)
											.concat(AppConstants.CLOSE_BRACKET);
					
					return returnData;
				}
				protected String getCOLOR_KEY(String keyData){
					String returnData = null;
					returnData = AppConstants.COLOR
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
				
				protected String getY_AXIS_KEY(String keyData){
					String returnData = null;
					returnData = AppConstants.Y
											.concat(AppConstants.BEGIN_BRACKET)
											.concat(keyData)
											.concat(AppConstants.CLOSE_BRACKET);
					
					return returnData;
				}

				
				
				protected void targetYAxisData() {
					 //getWebElement_By_xpath("//*[@id='content']/div[9]/div[1]/div[2]/ul/li["+j+"]/a").click();
					 Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);            
			          JavascriptExecutor jse = (JavascriptExecutor)getDriver();
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
					
				}
				protected void targetGraphData()
			     {
			        Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);            
			        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
			        Map<String, String> mapbill = new HashMap<String, String>();
			        jse.executeScript("window.scrollBy(0,175)", "");              
			        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][13]/*[name()='g'][1]")));       
			        //Working :get the stroke attribute to get the color value of graph
			        // getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
			      	   targetBCGraphData = new HashMap();
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
								tempBCGData.put(colorMap.replaceAll(" ", "").toUpperCase(), bcAtrribute_color);
								Actions builder=new Actions(getDriver());
			                   
			                   String childMouseHouverNode =  bcGraphRootNodeXpath.concat("/*[name()='g']")
																					.concat("[").concat(String.valueOf(n).concat("]"));
			                   builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();

								 WebElement mouseHoverNodeElement = getDriver().findElement(By.xpath("//div[8]/div[1]/div[2]/div/div/div"));
			                   
			                   if(mouseHoverNodeElement != null) {
			                  	 String toolTipText = mouseHoverNodeElement.getText();
				                    // System.out.println("toolTipText : '" + n+"' "+toolTipText );
			                  	 String toolTipTextMap = AppConstants.TOOL_TIP_TEXT.
			                  			 										    concat(AppConstants.BEGIN_BRACKET).
			                  			 										    concat(Integer.toString(n)).
			                  			 										    concat(AppConstants.CLOSE_BRACKET);
				                     tempBCGData.put(toolTipTextMap.replaceAll(" ", "").toUpperCase(), toolTipText);
				                     builder.moveToElement(getDriver().findElement(By.xpath(billchildNodePath_startDate))).perform();
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
			         //   targetBCGraphData.put(billCycleDate.replaceAll(" ", "").toUpperCase(), tempBCGData);
			            targetBCGraphData.put(Integer.toString(n), tempBCGData);
			       }//for loop ends
			     }
/**
 * Reads the table data
 */
				protected void targetTableData(){
					Wait<WebDriver> waittable= new WebDriverWait(getDriver(), 30);            				     
			     	 JavascriptExecutor jse = (JavascriptExecutor)getDriver();		     	
			     		 //jse.executeScript("window.scrollBy(0,-800)", "");
			       jse.executeScript("window.scrollBy(0,850)", "");              
			       waittable.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='details-table']")));       
			       WebElement headertable = getDriver().findElement(By.xpath("//*[@id='details-table']"));
					int i=0;
					List<WebElement> header = headertable.findElements(By.xpath("//*[@id='billhistory']/tr"));
				// And iterate over them, getting the header data
					for (WebElement node : header) {
					 List<WebElement> cells = node.findElements(By.tagName("th"));

					 for (WebElement cell : cells) {
					headerlist.add(cell.getText());
					 }
					// Now get all the TR elements from the table
					 boolean download;
					//WebElement table = getDriver().findElement(By.xpath("//*[@id='details-table']/tbody"));
					List<WebElement> rows = getDriver().findElements(By.xpath("//*[@id='details-table']/tbody/tr[1]"));
					// And iterate over them, getting the cells
					for (WebElement row : rows) {
					 List<WebElement> rowcells = row.findElements(By.tagName("td"));
					 for (WebElement cell : rowcells) {
					tableDataList.add(cell);
					}
				}
			}
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
						
			          // 	getWebElement_By_xpath("//*[@id='content']/div[9]/div[1]/div[2]/ul/li[2]/a").click();
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
				
				protected void extra(){
					try{
						  
						JavascriptExecutor jse = (JavascriptExecutor)getDriver();
						jse.executeScript("window.scrollBy(0,500)", "");
//						WebElement fb = (new WebDriverWait(fbdriver, 5000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main']/section/div/ul/li[1]/a")));
//				        fb.click();
						//*[@id="main"]/section/div/ul/li[1]/a
						for(int i=1;i<=3;i++){
				        WebElement socialbtn =  getDriver().findElement(By.xpath("//*[@id='main']/section/div/ul/li["+i+"]/a"));
				        Actions newTab1 = new Actions(getDriver());
				        newTab1.keyDown(Keys.SHIFT).click(socialbtn).keyUp(Keys.SHIFT).build().perform();
				        Thread.sleep(500);
				         
				        //handle windows change
				        String base1 = getDriver().getWindowHandle();
				        Set<String> set1 = getDriver().getWindowHandles();
				         
				        set1.remove(base1);
				        assert set1.size() == 1;
				        getDriver().switchTo().window((String) set1.toArray()[0]);
				        waitForPageLoad(500);
				        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='blueBarNAXAnchor']/div/div/div/div[1]/h1/a/i")));
				        //close the window
				        getDriver().close();
				        getDriver().switchTo().window(base1);
				         
				        // handle windows change and switch back to the main window
				        Thread.sleep(500);
				        for (String winHandle1 : getDriver().getWindowHandles()) {
				        	getDriver().switchTo().window(winHandle1);
				        
				        System.out.println("Link Validation for :"+socialbtn.getText()+" --> Passed" );
				      //  log.info("FB Link Passed");
				        Thread.sleep(500);
				        }
				        }
					}
				        
				  	catch(Exception e){
						log.error("Failed to verify Social Media Links");
					}
					}
				
				  protected void paymentPostpaid_BillsnReloads(){
						try{
							JavascriptExecutor jse = (JavascriptExecutor)getDriver();
							jse.executeScript("window.scrollBy(0,-500)", "");
							//*[@id="planDetails"]/div[2]/div[1]/div[6]/button
							WebElement pay =  getDriver().findElement(By.xpath("//*[@id='content']/div[8]/div[2]/div[3]/button"));
							pay.click();
							waitForPageLoad(100);
							
							//*[@id="rp-form-field-payamount"]
							getDriver().findElement(By.id("rp-form-field-payamount")).clear();
							handleInputField_ByID("rp-form-field-payamount", AppConstants.RMVALUE);
							JavascriptExecutor jse1 = (JavascriptExecutor)getDriver();
							jse1.executeScript("window.scrollBy(0,500)", "");
							//*[@id="proceed-button"]/span[1]
							WebElement payment = (new WebDriverWait(getDriver(), 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceed-button']/span[1]")));
							payment.click();
							waitForPageLoad(20);
							Thread.sleep(500);
							WebElement payment2 = (new WebDriverWait(getDriver(), 1000)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='proceed-button']/span[1]")));
							payment2.click();
							Thread.sleep(1000);
							waitForPageLoad(100);
						}
						catch(Exception e) {
							log.error("Fail : Failed to initialize Payment");
							e.printStackTrace();
							System.exit(0);
						}
						
					}
				  
				  protected void targetRMValueonPaymentPage() {
						boolean breakIt;
								while(true){
									try {
									breakIt=true;
								//*[@id="content"]/div/div/div[1]/div[9]/div[2]/strong
								rmvalue=getDriver().findElement(By.xpath("//*[@id='content']/div/div/div[1]/div[9]/div[2]/strong")).getText();
								System.out.println("Actual RM Value on Payment Page: "+rmvalue);
							}catch(Exception e) {
								breakIt=false;
								log.error("Fail : To validate the target RM Value on Payment Page");
							}
									if(breakIt){
										break;
									}
							}
						}
				
				  protected void targetOrderId() {
						try {
							//*[@id="content"]/div[2]/p[2]
							orderid=getDriver().findElement(By.xpath("//*[@id='content']/div[2]/p[2]")).getText();
							System.out.println("Order id :"+orderid);
						}catch(Exception e) {
							log.error("Fail : To validate the order id:");
						}
					}
				  

				  protected void returnBillsnReloadPage(){
					  try {
							WebElement back = (new WebDriverWait(getDriver(), 1000)).until(ExpectedConditions.elementToBeClickable(By.linkText("MyDigi")));
							back.click();
							waitForPageLoad(30);
						    waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div[13]/div/div")));
							getDriver().findElement(By.xpath("//*[@id='content']/div[13]/div/div/div[2]/button[1]")).click();
							Thread.sleep(4000);
							WebElement billsnReload = (new WebDriverWait(getDriver(), 1000)).until(ExpectedConditions.elementToBeClickable(By.linkText("Bills & Reloads")));
							billsnReload.click();
						}catch(Exception e) {
							log.error("Fail : Failed to initialize Payment");
							e.printStackTrace();
							System.exit(0);
						}
				  }
				  
				protected Map<String, List<String>> getDropDownTableHeaderMap() {
	            	return dropDownTableHeaderMap;
	            }

				protected String getActualPlanName() {
					return actualPlanName;
				}

				protected String getActualPlanId() {
					return actualPlanId;
				}

				protected String getActualAccountId() {
					return actualAccountId;
				}

				protected String getActualCreditLimit() {
					return actualCreditLimit;
				}

				protected String getActualDueDate() {
					return actualDueDate;
				}

				protected String getActualOutstandingAmount() {
					return actualOutstandingAmount;
				}

				protected Map<String, Map> getTargetBCGraphData() {
					return targetBCGraphData;
				}

				protected List<WebElement> getListyaxischild() {
					return listyaxischild;
				}

				protected List<String> getHeaderlist() {
					return headerlist;
				}

				protected List<WebElement> getTableDataList() {
					return tableDataList;
				}
				protected String getRmvalue() {
					return rmvalue;
				}


				protected String getOrderid() {
					return orderid;
				}
	
	
	

}
