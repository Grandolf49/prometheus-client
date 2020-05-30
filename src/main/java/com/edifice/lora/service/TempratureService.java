package com.edifice.lora.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edifice.lora.dao.DeviceDaoImpl;
import com.edifice.lora.entity.Device;
import com.edifice.lora.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



/***
 * @desc:The class will have the business logic and data processing here
 * @author prashantj
 *
 */
@Service
public class TempratureService {
	
	//private Gauge gauge;
	  //@Autowired private MeterRegistry meterRegistry;

	@Resource
	DeviceDaoImpl deviceDaoImpl ; 
 
  public void init()
  {
      //gauge = Gauge.builder("stock.size", this, StockManager::getNumberOfItems)
      //            .description("Number of items in stocks")
      //            .register(meterRegistry);

  }
  
	public HashMap addTemprature(String sensorName,Float tempreture , String vendorId,String gateway)
	{
		HashMap<String,Object> jsnObj = new HashMap();

		Boolean flag = true ;
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

}
