package com.edifice.lora.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edifice.lora.dao.DeviceDaoImpl;
import com.edifice.lora.dao.HistoryDaoImpl;
import com.edifice.lora.dao.ReadingsDaoImpl;
import com.edifice.lora.entity.Device;
import com.edifice.lora.entity.GroupDevices;
import com.edifice.lora.entity.Log;
import com.edifice.lora.entity.Readings;
import com.edifice.lora.entity.SolutionGroup;
import com.edifice.lora.utilities.Constants;
import com.edifice.lora.utilities.Utilities;
import com.edifice.lora.utilities.WebToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.jsonwebtoken.Claims;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;



/***
 * @desc:The class will have the business logic and data processing here
 * @author prashantj
 *
 */
@Service
public class TempratureService {
	
	//private Gauge gauge;
	  //@Autowired private MeterRegistry meterRegistry;

	  @Value("${lora.promethues.labels}")
	  private String[] loraLabels;

	@Resource
	DeviceDaoImpl deviceDaoImpl ; 
	
	@Resource
	ReadingsDaoImpl readingsDao ; 
	
	@Resource
	HistoryDaoImpl historyDao ; 
 
	
	// Define a histogram metric for /prometheus
	private Gauge  requestLatency = null ;
	
  
  @PostConstruct
  public void init() 
  {

		requestLatency = Gauge.build().namespace("java").name("edifice_tempreture").labelNames(loraLabels).help("Request latency in seconds.").register();

  }
  
	public HashMap addTemprature(String sensorName,Float tempreture , String vendorId,String gateway)
	{
		HashMap<String,Object> jsnObj = new HashMap();

		Boolean flag = true ;
		
		/***
		 * get the mapping for the device from deviceId
		 */
		/***
		 * business ologinc of exporteing
		 */
		
		//Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").serializeNulls().create();;

		if(flag)
		{
			  	jsnObj.put("status", Constants.SUCCESS);
			   jsnObj.put("message","tempreture added successfully ") ;
			  // jsnObj.put("portfolios",gson.toJson(portfolios));
		}
		else
		{
			jsnObj.put("status", Constants.ERROR);
			jsnObj.put("message","Some Error Message .") ;

		}
		return jsnObj;
	}

	public HashMap addTemprature(String accessToken,Double tempreture)
	{
		HashMap<String,Object> jsnObj = new HashMap();

		Boolean flag = true ;
		
		/***
		 * get the mapping for the device from deviceId from another table
		 * can use jjwt to get the mapping 
		 * 
		 * 2) get DeviceId and GroupId from the access token ,
		 * 3) valdate with our mapping to undurstand if its correct access token 
		 * 4) insert
		 */
		

		List<GroupDevices> deviceData = deviceDaoImpl.getInfoByDeviceToken(accessToken) ;

		//need a way to find the solution form multiple grps 
		//List<SolutionGroup> solutionGrp = deviceDaoImpl
		
		/***
		 * add to the DB
		 */
		
		if(deviceData.size() == 0)
		{
			jsnObj.put("status", Constants.ERROR);
			jsnObj.put("message","Wrng access token .") ;

			return jsnObj;
		}
		
		Readings reading = new Readings();
		reading.setValues(tempreture);
		reading.setDeviceId(deviceData.get(0).getDeviceId());
		reading.setEndpointKey(deviceData.get(0).getEndpointKey());
		reading.setGroupId(deviceData.get(0).getGroup().getId());
		//reading.setLatitude(latitude);
		reading.getLongitude();
		
		reading.setReadingDate(Utilities.getCurrentDate());
		
		readingsDao.addTelemetry(reading);
		
		/***
		 * business ologinc of exporteing
		 * can use histogram 
		 */
		
		requestLatency.labels("edifice","buildgn1",deviceData.get(0).getSolution().getName(),deviceData.get(0).getGroup().getGroupName(),deviceData.get(0).getDevice().getName()).set(tempreture);
		
		
		/**
		 * rules
		 */
		
		//Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").serializeNulls().create();;

		if(flag)
		{
			  	jsnObj.put("status", Constants.SUCCESS);
			   jsnObj.put("message","tempreture added successfully ") ;
			  // jsnObj.put("portfolios",gson.toJson(portfolios));
		}
		else
		{
			jsnObj.put("status", Constants.ERROR);
			jsnObj.put("message","Some Error Message .") ;

		}
		return jsnObj;
	}

	
	/****
	 * test method to show retrival of data from DB 
	 * @param sensorName
	 * @param tempreture
	 * @param vendorId
	 * @param gateway
	 * @return
	 */
	public HashMap testTemprature(String deviceId )
	{
		HashMap<String,Object> jsnObj = new HashMap();

		Boolean flag = true ;
		
		
		//List<Device> deviceData = deviceDaoImpl.getDeviceByDeviceId(deviceId) ;
		
		List<Device> deviceData = deviceDaoImpl.findAll();
		
		Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").serializeNulls().create();;

		if(flag)
		{
			  	jsnObj.put("status", Constants.SUCCESS);
			   jsnObj.put("message","temprature retrived successfully ") ;
			   jsnObj.put("devices",gson.toJson(deviceData));
		}
		else
		{
			jsnObj.put("status", Constants.ERROR);
			jsnObj.put("message","Some Error Message .") ;

		}
		return jsnObj;
	}

	
	public boolean isDeviceValid(String token)
	{
		String err ="";
		Claims claim= null ;
		try
		{
			WebToken webToken = new WebToken();
			if(!webToken.validateRequest(token))
			{
				err="Invalid API Key provided ,please re-login again" ;
				return false ;	
			}
			
			claim = webToken.getClaims();
		}
		catch(Exception ex)
		{
			err=ex.getMessage();
			//System.out.println("Stackstrace = "+ex.getStackTrace());
			return false;
		}
		
		return  true ;
	}
	

	/***
	 * @desc:
	 *   1) devie id/aaccess specifier from the things netwok request
	 *   2) add the request string to the logs table with timestamp
	 *   2) change the 
	 * @param thingsResponse
	 * @return
	 */
	public HashMap addThingsResponse(String thingsResponse)
	{
		HashMap<String,Object> jsnObj = new HashMap();

		Boolean flag = true ;

		//convert string to json object
		Gson g = new Gson();
		//JSONObject p = g.fromJson(thingsResponse, JSONObject.class) ;
		JSONObject json = null;
	     try {
	    	json =  new JSONObject(thingsResponse) ;
	    	
	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	     Log log = new Log();
	     String accessToken = "" ; //deve_id
	     Double tempreture =(double) 0;
	     try {
			log.setDeviceId(json.getString("dev_id"));
			accessToken = json.getString("dev_id") ;
			
			//get Tempreture form the string
			String tmp = json.get("payload_fields").toString() ;
			
			JSONObject jsonTmp =  new JSONObject(tmp) ; //get tempreture value in humidty
			
			String tmpp = jsonTmp.getString("deviceValue") ;
			tempreture = Double.parseDouble(tmpp.split(",")[0]);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			log.setDeviceId("Dev id not found");
		//	e.printStackTrace();
		}
	     
	     log.setThingsRequest(thingsResponse);
	     
	     historyDao.addReqHistory(log) ;

	     
	     /***
			 * get the mapping for the device from deviceId from another table
			 * can use jjwt to get the mapping 
			 * 
			 * 2) get DeviceId and GroupId from the access token ,
			 * 3) valdate with our mapping to undurstand if its correct access token 
			 * 4) insert
			 */

	     //consided the accesstokent as the dev_id for now since its configurable

			List<GroupDevices> deviceData = deviceDaoImpl.getInfoByDeviceToken(accessToken) ;

			
			//need a way to find the solution form multiple grps 
			//List<SolutionGroup> solutionGrp = deviceDaoImpl

			
			/***
			 * add to the DB
			 */
			
			if(deviceData.size() == 0)
			{
				jsnObj.put("status", Constants.ERROR);
				jsnObj.put("message","Wrong access token .Device not found !!!") ;

				return jsnObj;
			}
			
			Readings reading = new Readings();
			reading.setValues(tempreture);
			reading.setDeviceId(deviceData.get(0).getDeviceId());
			reading.setEndpointKey(deviceData.get(0).getEndpointKey());
			reading.setGroupId(deviceData.get(0).getGroup().getId());
			//reading.setLatitude(latitude);
			reading.getLongitude();
			
			reading.setReadingDate(Utilities.getCurrentDate());
			
			readingsDao.addTelemetry(reading);
			
			/***
			 * business ologinc of exporteing
			 * can use histogram 
			 */
			
			requestLatency.labels("edifice","buildgn1",deviceData.get(0).getSolution().getName(),deviceData.get(0).getGroup().getGroupName(),deviceData.get(0).getDevice().getName()).set(tempreture);

		if(flag)
		{
			  	jsnObj.put("status", Constants.SUCCESS);
			   jsnObj.put("message","temprature Added successfully ") ;
			  
		}
		else
		{
			jsnObj.put("status", Constants.ERROR);
			jsnObj.put("message","Some Error Message .") ;

		}
		return jsnObj;

	}
}
