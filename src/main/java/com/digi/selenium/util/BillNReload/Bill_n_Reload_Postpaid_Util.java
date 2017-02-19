package com.digi.selenium.util.BillNReload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



import com.digi.selenium.util.common.AppConstants;
import com.digi.selenium.util.common.PageNavigation;

public class Bill_n_Reload_Postpaid_Util extends PageNavigation{
	
	//Target object[UI] - tooltip data on graph
	private  List<String> targetTootips = new ArrayList<String>();	
	//Target object[UI] - tabular data from table
	private HashMap<String, List<String>> targetTabularData = new HashMap<String, List<String>>();
	
	private  String actualPlanName = new String();
	
	private String actualPlanId = new String();
	
	private String actualAccountId = new String();
	
	private String actualCreditLimit = new String();
	
	private String actualDueDate = new String();
	
	private String actualOutstandingAmount = new String();
	
	private String actualstatus="";
	private List<String> headerlist=new ArrayList<String>();
	private List<WebElement> tablelist= new ArrayList<WebElement>();
	private  Properties sourceStaticDataAccounts = new Properties();
	//Store the BillCycle ,Graph - color , tooltip data - 60 days
	private Map<String,Map> targetBCGraphData= new HashMap();

	private List<WebElement> dropdownmonths=new ArrayList<WebElement>();
	
	//logger class
	private  Logger log = Logger.getLogger(Bill_n_Reload_Postpaid_Util.class);
	
	//static or dynamic data flag
	private  Properties sourceDataStatus = new Properties();

	//static data 
	private  Properties sourceStaticData = new Properties();
	
	private String rmvalue="";
	
	private String orderid="";
	private Map<String,List<String>> dropDownTableHeaderMap=new HashMap<String, List<String>>();
	
	private List<WebElement> listyaxischild=new ArrayList<WebElement>();
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
						 PageNavigation.prop.getProperty(AppConstants.BILLS_N_RELOAD_POSTPAID_MSSIDN),
						 PageNavigation.prop.getProperty(AppConstants.BILLS_N_RELOAD_POSTPAID_PASS));
		movetoBillsReloads();
		readSourceDataFiles();
	}
	
 	/**
 	 * Reads the Bills n Reload page source data
 	 * to get the dynamic/static data
 	 */
     private void readSourceDataFiles(){
    	String statusFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
    											   .concat(AppConstants.BILLSNRELOAD_DATASTATUSFILE_DATASTATUS_POSTPAID);
    	sourceDataStatus = loadFileData(statusFileName);
    	
    	String dataFileName = PageNavigation.prop.getProperty(AppConstants.CONFIG_FILES_LOCATION)
												 .concat(AppConstants.BILLSNRELOAD_DATASTATUSFILE_DATA_POSTPAID);
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
    		// System.out.println("getSourceData:Contains value");
    		 returnData = findSourceData(inputData);
    		 log.info("Success : Static Source data '"+ inputData + "' -->"+returnData );
    		// System.out.println("Success : Static Source data '"+ inputData + "' -->"+returnData );
    	 }else{
    		 returnData= "Fail :" + inputData + " Not found in Status File : getSourceData " ;
    		 log.error("Fail :" + inputData + " Not found in Status File ");
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
    	 //System.out.println("findSourceData :" + status);
    	 if(status.equalsIgnoreCase(AppConstants.YES)){
    		 returnData = sourceStaticData.getProperty(inputData);
    		// System.out.println("findSourceData::ReturnData : " + returnData);
    	 }else{
    		 String tempData = sourceStaticData.getProperty(inputData);
    		 int separatorIndex = tempData.trim().indexOf("|");
    		 String mappedData = tempData.substring(0, separatorIndex);
    		 String urlDataInFile = tempData.substring(separatorIndex +1);
    		 //String keyData = "CustomerId";
    		 try {
    			//String urlData =  AppConstants.STAGEING_URL.concat(urlDataInFile);
    			String msisdnNo =  prop.getProperty(AppConstants.COUNTRY_PREFIX).concat(
    								prop.getProperty(AppConstants.BILLS_N_RELOAD_POSTPAID_MSSIDN)
    								);
    			//System.out.println("msisdnNo :" +msisdnNo );
    			
    			
    			// services/retrievesubscriber/msisdn/
    			// services/retrieveBillingInfo/msisdn/
    			// services/retrieveAccountBalance/msisdn/
    			
    			int indexofurlseprator=urlDataInFile.indexOf("/");
    			String tempkey=urlDataInFile.substring(indexofurlseprator+1);
    			String tokentohiturl=tempkey.substring(0,tempkey.indexOf("/"));
    			
    			if(tokentohiturl.equals("retrievesubscriber")) {
    				
    			String jsonResult =  getOnlineServices().execute(
    											 "RETRIEVE_SUBSCRIBER_MSISDN",
    											 AppConstants.STAGEING_URL, new Object[] {
    													 msisdnNo, "1"},new String[]{"",msisdnNo});
    			
    			System.out.println("jsonResult :-->"+ jsonResult);
    			Object resultFound = searchInJSONResult(jsonResult, mappedData);
    			
    			returnData = resultFound.toString();
    			System.out.println("Result Found : "+ returnData);
    			}else if(tokentohiturl.equals("retrieveBillingInfo")) {
    				
    				String jsonResult =  getOnlineServices().execute(
							 "RETRIEVE_BILLING_INFO_USING_MSISDN",
							 AppConstants.STAGEING_URL, new Object[] {
									 msisdnNo, "1"},new String[]{"",msisdnNo});

    				System.out.println("jsonResult :-->"+ jsonResult);
    				Object resultFound = searchInJSONResult(jsonResult, mappedData);
                   //returnData=resultFound.toString();
                   double f= Double.parseDouble(resultFound.toString());
                   returnData= String.format("%.2f", f);
                   returnData="RM".concat(returnData);
                   System.out.println("Result Found : "+ String.format("%.2f", f));
    				
    			}else {
    				String jsonResult =  getOnlineServices().execute(
							 "RETRIEVE_ACCOUNT_BALANCE_BY_MSISDN",
							 AppConstants.STAGEING_URL, new Object[] {
									 msisdnNo, "1"},new String[]{"",msisdnNo});

   				System.out.println("jsonResult :-->"+ jsonResult);
   				Object resultFound = searchInJSONResult(jsonResult, mappedData);

   				returnData = resultFound.toString();
   				System.out.println("Result Found : "+ returnData);
    			}
    		} catch (ClientProtocolException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
 
    	 }
    	 
    	 return returnData;
     }
	
     
     
	/**
	 * Description: Read the Graph data on Page 
	 * @return none
	 *  
	 */	
     
    
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
                  			 										    concat(billCycleDate).
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
            targetBCGraphData.put(billCycleDate.replaceAll(" ", "").toUpperCase(), tempBCGData);
       }//for loop ends
     }

     
     protected void targetYAxisData(){
    	 //getWebElement_By_xpath("//*[@id='content']/div[9]/div[1]/div[2]/ul/li["+j+"]/a").click();
		 Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);            
       JavascriptExecutor jse = (JavascriptExecutor)getDriver();
       jse.executeScript("window.scrollBy(0,-500)", "");
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
       
       // String targetrootdatapath= "//*[@id='content']/div[9]/div[1]/div[1]/div[2]/div/div/svg/g[13]/g[2]";
      
				System.out.println("===========Y-AxisData========");
     }
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
		 }
		//get the data of table
		List<WebElement> rows = getDriver().findElements(By.xpath("//*[@id='details-table']/tbody/tr[1]"));
		// And iterate over them, getting the cells
		
		for (WebElement row : rows) {
		 List<WebElement> rowcells = row.findElements(By.tagName("td"));
		 
		 for (WebElement cell : rowcells) {
			tablelist.add(cell);
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
					WebDriverWait wait = new WebDriverWait(getDriver(),20);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/div[2]/p[2]")));
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
					WebElement billsnReload = (new WebDriverWait(getDriver(), 1000)).until(ExpectedConditions.elementToBeClickable(By.linkText("Bills & Reloads")));
					billsnReload.click();
				}catch(Exception e) {
					log.error("Fail : Failed to initialize Payment");
					e.printStackTrace();
					System.exit(0);
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
			
	/*protected void targetGraphData()
	{
		Wait<WebDriver> wait= new WebDriverWait(getDriver(), 30);		
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		Map<String, String> mapbill = new HashMap<String, String>();
		jse.executeScript("window.scrollBy(0,250)", "");		 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg']/*[name()='g'][6]/*[name()='g']")));	
		//Working :get the stroke attribute to get the color value of graph
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		boolean breakIt = true;
	    String rootNodeXPath = "//*[name()='svg']/*[name()='g'][6]/*[name()='g']" ;
	    WebElement rootElement = getDriver().findElement(By.xpath(rootNodeXPath)) ;
	    //System.out.println("WebElement got :" + rootElement);
	    String childXPathRTroot = "./*[name()='g']"; 
	    List<WebElement> listChildElements = rootElement.findElements(By.xpath(childXPathRTroot));
	    //System.out.println("listChildElements.size() : " + listChildElements.size());
	    int eleListSize = listChildElements.size();
 	   	//List <String> tooltipvaluelist = new ArrayList<String>();
		
		for(int j=0; j<eleListSize; j++){
	    	int n = j+1;
	    	String childNodePath = rootNodeXPath.concat("/*[name()='g']").concat("[").concat(String.valueOf(n).concat("]").concat("/*[name()='path']"));
	    	String childMouseHouverNode = rootNodeXPath.concat("/*[name()='g']").concat("[").concat(String.valueOf(n).concat("]"));
	    	//System.out.println("ChidNodePath : '" +n+"' ->"+childNodePath );
	    	//System.out.println("childMouseHouverNode : '" +n+"' ->"+childMouseHouverNode );
	    	//String billCycleNodePath = "//*[@id='content']/div[8]/div[1]/div[2]/div/div/svg/g[13]/g[1]/text["+n+"]";
	    	String billCycleNodePath = "//*[@id='content']/div[8]/div[1]/div[2]/div/div/svg/g[13]/g[1]/text["+n+"]" ;
	    	 while (true) {
	     	    breakIt = true;
	     	    try {
	     	        // write your code here
	     	    	WebElement rootElementSub = getDriver().findElement(By.xpath(childNodePath));
	     	    	String colorAttribute = rootElementSub.getAttribute("stroke");
	     	    	String svgAttributeD = rootElementSub.findElement(By.xpath(childNodePath)).getAttribute("d");
	     	    	System.out.println("Color Code :'" +n+"'" + colorAttribute );
	     	    	log.info("colorAttribute Stroke: " + colorAttribute);
	     	    	log.info("svgAttribute D: " + svgAttributeD);
	     	    	//Praval
	     	    	System.out.println("billCycleNodePath :"+billCycleNodePath);
	     	    	WebElement billCycleElement = getWebElement_By_xpath(billCycleNodePath);
	     	    	String billCycle = billCycleElement.getText();
	     	    	System.out.println("billCycle :" +billCycle );
	     	    	//Praval
	     	    	Actions builder=new Actions(getDriver());
	     	    	 builder.moveToElement(getDriver().findElement(By.xpath(childMouseHouverNode))).perform();
	     	    	log.info("Mouse hover Performed : '"+n+"'");
	     	    	WebElement mouseHoverNodeElement = getDriver().findElement(By.xpath("//div[8]/div[1]/div[2]/div/div/div"));
	     	    	log.info("MouseHover Object Found : '"+n+"'");
	     	    	 
	     	    	String toolTipText = mouseHoverNodeElement.getText();
	     	    	System.out.println("toolTipText :" +toolTipText );
	     	    	log.info("toolTipText : " +toolTipText );
	     	    	mapbill.put("tooltip"+n, toolTipText);
	     	    	
	     	    	targetTootips.add(toolTipText);
	     	 	if(toolTipText.contains("Amount Paid"))
	     	    	{
	     	    	String[] tooltipvalue = (mapbill.get("tooltip"+n)).split(":");
	     	    	//System.out.println(tooltipvalue[1].trim());
	     	    	targetTootips.add(tooltipvalue[1].trim());
	     	    	}
	     	    	else
	     	    	{
		     	    	String[] tooltipvalue = (mapbill.get("tooltip"+n)).split("Amount Due:");
		     	    	//System.out.println(tooltipvalue[1].substring(0, 5));
		     	    	targetTootips.add(tooltipvalue[1].trim());     	    		
	     	    		
	     	    	}
	     	   	    

	     	    	getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     	        

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
		
	  }//for loop ends
		
	}

     */

	/* Description: To read tabular value on bills and reloads page
 * Parameters: Not required
 * Return: Hashmap of <string , list of strings> having  all the values in table
 */	
	protected void  targetTabularData() 
	{

		//HashMap<String, List<String>> getValues = new HashMap<String, List<String>>();
		List<String> billCyclelist = new ArrayList<String>();
		List<String> billAmountlist = new ArrayList<String>();
		List<String> amountPaidlist = new ArrayList<String>();
		WebElement svg = getDriver().findElement(By.cssSelector("svg"));
		List<WebElement> billingCycle = getDriver().findElements(By.cssSelector(".tablet-eight-twelfths.mobile-eleven-twelfths"));
		List<WebElement> outertext = svg.findElements(By.cssSelector("text"));		
        List<String> graphperiod = new ArrayList<String>();
		for(WebElement texts : outertext)
		                    {
		                        String textcollection = texts.getText();
		                        graphperiod.add(textcollection);
		                       
		                    }
    log.info("No of graphs shown are : " + billingCycle.size());
    for (int j=billingCycle.size(), i = ((j*2)-1) ; j>=1;i=i-2,j--)
    	
    {
    	//fetching values from the table
    	String billCycle = getDriver().findElement(By.xpath("//*[@id='details-table']/tbody/"+"tr["+i+"]/td[1]")).getText();
    	String billAmount = getDriver().findElement(By.xpath("//*[@id='details-table']/tbody/"+"tr["+i+"]/td[5]")).getText();
    	String amountPaid = getDriver().findElement(By.xpath("//*[@id='details-table']/tbody/"+"tr["+i+"]/td[6]")).getText();
    	//System.out.println("bill amount : "+billAmount + " ,amountPaid :" + amountPaid + " ,for billing cycle :" +billCycle );
    	
		// create list  and store values
    	billCyclelist.add(billCycle);
    	billAmountlist.add(billAmount);   			
    	amountPaidlist.add(amountPaid);
		     	
    }
	// put values into map
    targetTabularData.put("billCycle", billCyclelist); 
    targetTabularData.put("billAmount", billAmountlist);
    targetTabularData.put("AmountPaid", amountPaidlist); 	
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
		
	/** Description: Testing the Account_Id_Name
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
			
	/** Description: Testing the CreditLimit
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

			
	/** Description: Testing the Due Date
	* Parameters:  Not required
	* Return: No return
	*/		
			protected void targetDueDate(){
				try{						
				waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='plan-name']")));
				//String expectedDueDate = "30.8.2015";
			    actualDueDate = getWebElement_By_xpath("//*[@id='OutstandingDueDate']").getText();
				}
				catch (Exception e) {
					log.error("Fail : To validate the target Due Date");
				}
			}
			
	/** Description: Testing the Outstanding Amount
	* Parameters:  Not required
	* Return: No return
	*/		
			protected void targetOutstandingAmount(){
				try{		
					JavascriptExecutor jse =(JavascriptExecutor)getDriver();
					jse.executeScript("window.scroll(0,250)", "");
				waitgetForPageLoad(30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='plan-name']")));
				//String expectedOutstandingAmount = "149.88";
				//actualOutstandingAmount = getWebElement_By_xpath("//*[@id='content']/div[8]/div[2]/div[2]/div[3]/p").getText();
				actualOutstandingAmount = getWebElement_By_xpath("//*[@class='clear text--large text--large pull-right totalOutstandingLimit totalOutstanding--red']").getText();
				}
				catch (Exception e) {
					log.error("Fail : To validate the target Outstanding Amount");
				}
			}


	
/* Description: Navigating to bills and reload Page
* Parameters:  mssidn ,Password 
* Return: No return
*/		
	protected void movetoBillsReloads()
	{
	    waitForPageLoad(60);
	    handleLink_ByText("MyDigi");
	    waitForPageLoad(30);
		handleLink_ByText("Bills & Reloads");
	}
	
/** Get the TabularData for for TargetObject 
 * 
 * @return HashMap<String, List<String>>
 */
	protected HashMap<String, List<String>> getTargetTabularData() {
		return targetTabularData;
	}

/** Get the tooltip data for TargetObject
* 
* @return List<String> 
*/
	protected List<String> getTargetTootips() {
		return targetTootips;
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
			protected String getCELL_KEY(String keyData){
				String returnData = null;
				returnData = AppConstants.CELL
										.concat(AppConstants.BEGIN_BRACKET)
										.concat(keyData)
										.concat(AppConstants.CLOSE_BRACKET);
				
				return returnData;
			}
			//Get Y Axis data key
			protected String getY_AXIS_KEY(String keyData){
				String returnData = null;
				returnData = AppConstants.Y
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

protected Map<String, List<String>> getDropDownTableHeaderMap() {
	return dropDownTableHeaderMap;
}
protected List<WebElement> getDropdownmonths() {
	return dropdownmonths;
}

protected String getRmvalue() {
	return rmvalue;
}

protected String getOrderid() {
	return orderid;
}
protected List<WebElement> getListyaxischild() {
	return listyaxischild;
}
protected List<String> getHeaderlist() {
	return headerlist;
}

protected List<WebElement> getTablelist() {
	return tablelist;
}
}


	
	
	

