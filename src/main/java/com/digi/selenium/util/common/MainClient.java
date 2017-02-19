package com.digi.selenium.util.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class MainClient extends PageNavigation{

	public static void main(String[] args) {

		/*
		 * AccountCode": 1100000041582 AccountId": 4000020044471
		 * AccountOwnerMSISDN": 607000056009 SubscriberId":" 2000020038027
		 * CustomerId":" 1000020043430"
		 */
		try {

		 OnlineServices onlineServices = new OnlineServices();

		 /*System.out.println( onlineServices.execute(
				 "ITEMIZED_BY_DATE_USING_SUBNO_AND_DATE",
				"http://mdsstg.digi.com.my/digionline/", new Object[] {
				 "60109235529", ""},new String[]{"","60109235529"}));*/
		 
		 //OK - Staging data
		 String keyData = "ICCID";
		 String jsonResult =  onlineServices.execute(
										 "RETRIEVE_SUBSCRIBER_MSISDN",
										"http://mdsstg.digi.com.my/digionline/", new Object[] {
										 "60109235782", "1"},new String[]{"","60109235782"});
		 System.out.println("jsonResult :-->"+ jsonResult);
		 
		Object resultFound =  parseJSONResult(jsonResult, keyData);
		 
		// Object resultFound = searchInJSONResult(jsonResult, keyData);
		
		System.out.println("Result Found : "+ resultFound);
		 //OK
		 
		/* System.out.println( onlineServices.execute(
				 "RETRIEVE_SUBSCRIBER_MSISDN",
				"http://mdsstg.digi.com.my/digionline/", new Object[] {
				 "60102315465", "1"},new String[]{"","60102315465"}));*/
		 
		 

			/* System.out.println( onlineServices.execute(
					 "accountOverviewService",
					"http://mdsstg.digi.com.my/digionline/", new Object[] {
					 "60109235529","0000"},new String[]{"","60109235529"}));*/

		 
		} catch (Exception ex) {
			System.out.println("OnlineServicesException System Error:" + ex);
		} 
	
	// http://localhost:8080/digionline/services/retrieveDataDashboard/data/msisdn/4000020033609
	// http://localhost:8080/digionline/services/retrieveDataDashboard/data/msisdn/4000020033609
	}
	
	
	private static Object  parseJSONResult(String jsonData, String keyData){
		  Object resultFound = null;
		  JSONParser parser = new JSONParser(); 
		  KeyFinder finder = new KeyFinder();  
		  finder.setMatchKey(keyData); 
		  try{   
			  while(!finder.isEnd()){  
				  parser.parse(jsonData, finder, true);  
				  if(finder.isFound()){        
					  finder.setFound(false);  
					  //System.out.println("found id:");   
					  //System.out.println(finder.getValue());  
					  resultFound = finder.getValue();
					  
					  return resultFound;
					  }   
				  }       
			  }  catch(ParseException pe){  
				  pe.printStackTrace();  
				  }
		  return resultFound;
	}
	
	
	private static void parseJSONResult_old(String jsonData, String keyData){
		  JSONParser parser = new JSONParser(); 
		  ContainerFactory containerFactory = new ContainerFactory(){   
				public List creatArrayContainer() {      
					return new LinkedList();   
					}   
				public Map createObjectContainer() {    
					return new LinkedHashMap();  
					}                         
				};                 

				Map<String, Object> testMap = new HashMap<String, Object>();
				try{   
					Map json = (Map)parser.parse(jsonData, containerFactory);   
					Iterator iter = json.entrySet().iterator();    
					System.out.println("==iterate result==");  
					while(iter.hasNext()){    
						Map.Entry entry = (Map.Entry)iter.next();  
						System.out.println(entry.getKey() + "=>" + entry.getValue());   
						testMap.put(entry.getKey().toString(), entry.getValue());
						if(keyData.equalsIgnoreCase(entry.getKey().toString())){
							System.out.println("Match Data ==> Key --"+ entry.getKey().toString() + "  Value --"+ entry.getValue());
						}
					}         
					
					   System.out.println("testMap :==>" + testMap);
						System.out.println("==toJSONString()==");   
						System.out.println(JSONValue.toJSONString(json)); 
					}  catch(ParseException pe){   
						System.out.println(pe);  
					}
	}
}