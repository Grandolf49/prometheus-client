package com.edifice.lora.service;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edifice.lora.utilities.Constants;

import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;

@Service("counterrService")
public class CounterrService  
{

	  //@Autowired 
	  //private MeterRegistry meterRegistry;

	  @Value("${lora.promethues.labels}")
	  private String[] loraLabels;

	//static final Counter requests = Counter.build().name("edifice_counter").help("Total number of requests.").labelNames("edifice","testPrj","tstGrp","tstModule","tstSensorCnt").register();
	private Counter requests ;//= Counter.build().name("edifice_counter").help("Total number of requests.").register();
	// Define a histogram metric for /prometheus
	static final Histogram requestLatency = Histogram.build().name("requests_latency_seconds")
			.help("Request latency in seconds.").register();

	private io.micrometer.core.instrument.Counter lightOrderCounter ;

	  
	  @PostConstruct
	  public void init() {
	
		  requests = Counter.build().namespace("java").name("edifice_counter").labelNames(loraLabels).help("Total number of requests.").register();
		 // lightOrderCounter = this.meterRegistry.counter("beer.orders", "type", "light"); // 1 - create a counter

	    //gauge =
	     //   Gauge.builder("stock.size", this, StockManager::getNumberOfItems)
	      //      .description("Number of items in stocks")
	       //     .register(meterRegistry);
	  }

	  //public CounterrService(MeterRegistry meterRegistry) 
	  //{
	   //     this.meterRegistry = meterRegistry;
	   // }
	
	public HashMap incrementCounter()
	{
		
		HashMap<String,Object> jsnObj = new HashMap();

		Boolean flag = true ;
		
		// Increase the counter metric
		//try
		//{
		//requests.inc(); //increment counter with no labesl
		
		requests.labels("edifice","testPrj","tstSolution","tstGrp","tstSensor").inc();

					//	lightOrderCounter.increment();
	
			//Histogram.Timer requestTimer = requestLatency.startTimer();
		//}
		//catch(Exception ex)
		//{
			//System.out.println("Exception is "+ex.getMessage());
		//}
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
