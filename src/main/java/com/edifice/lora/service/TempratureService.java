package com.edifice.lora.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.edifice.lora.utilities.Constants;



/***
 * @desc:The class will have the business logic and data processing here
 * @author prashantj
 *
 */
@Service
public class TempratureService {
	
	//private Gauge gauge;
	  //@Autowired private MeterRegistry meterRegistry;

 
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

}
