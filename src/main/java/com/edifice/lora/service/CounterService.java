package com.edifice.lora.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.edifice.lora.utilities.Constants;

import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;

@Service
public class CounterService 
{

	static final Counter requests = Counter.build().name("requests_total").help("Total number of requests.").register();
	// Define a histogram metric for /prometheus
	static final Histogram requestLatency = Histogram.build().name("requests_latency_seconds")
			.help("Request latency in seconds.").register();

	
	public HashMap incrementCounter()
	{
		
		HashMap<String,Object> jsnObj = new HashMap();

		Boolean flag = true ;
		
		// Increase the counter metric
		requests.inc();

		Histogram.Timer requestTimer = requestLatency.startTimer();
		
		//Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").serializeNulls().create();;

		if(flag)
		{
			  	jsnObj.put("status", Constants.SUCCESS);
			   jsnObj.put("message","counter incremented .. ") ;
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
