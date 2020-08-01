package com.edifice.lora.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edifice.lora.service.TempratureService;
import com.google.gson.JsonObject;




/****
 * 
 * @author prashantj
 * entry point data to be push from the temprature sensor or gateway box. 
 * we can have multiple controller each for sensore or a module . 
 */
@RestController
@RequestMapping("/temprature")
public class TempratureController {

	@Resource
	private TempratureService tempratureService  ;

	
	/***
	 * @desc : Method to get the tempature from the sensor/gateway and then add to promethues with our business logic
	 * @param program
	 * @param parentProgram
	 * @return
	 */
	@PostMapping(value="/addTemprature")
	public Map addProgram(@RequestParam("temprature") String temprature,@RequestParam("gatewayId") String gatewayId,
			@RequestParam("vendor") String vendor,@RequestParam("sensor") String sensorName,HttpServletRequest request)
	{
		HashMap<String,Object> mp =new HashMap();
		
		/**
		 * do authorization check and passed to service layer for processing
		 */
		
		return tempratureService.addTemprature(sensorName, Float.parseFloat(temprature), vendor,gatewayId) ;
	}
	
	@PostMapping(value="/getAllDevices")
	public Map getAllDevices(HttpServletRequest request)
	{
		HashMap<String,Object> mp =new HashMap();
		
		/**
		 * do authorization check and passed to service layer for processing
		 */
		
		return tempratureService.testTemprature("") ;
	}

	
	/**
	 * @desc : the endpoint is called with and endpoint key
	 *   	endpoint key is an identification key of the device we can use jjwt ot generate endpoint key based on  number of parameter
	 * @param divisionId
	 * @return
	 */
	@GetMapping(value = "/addTemprature/{endpointKey}/{reading}/")
	public Map addTemprature(@PathVariable(value = "endpointKey") String endpointKey,@PathVariable(value = "reading") String reading) 
	{
		/**
		 * do authorization check and passed to service layer for processing
		 */
		
		/**
		 * we do sensore identfcation in the servie layer
		 */
		//return tempratureService.addTemprature(endpointKey, Float.parseFloat(reading)) ;
		return tempratureService.addTemprature(endpointKey, Double.parseDouble(reading)) ;
	}

	/**
	 * @desc : The endpoint is used to called from the things cloud
	 * @param divisionId
	 * @return
	 */
	@PostMapping(value = "/add")
	public Map addTempratur(@RequestBody String endpointKey) 
	{
		/**
		 * do authorization check and passed to service layer for processing
		 */
		
		/**
		 * we do sensore identfcation in the servie layer
		 */
		System.out.println("some rest");
		//return tempratureService.addTemprature(endpointKey, Float.parseFloat(reading)) ;
		return tempratureService.addThingsResponse(endpointKey) ;
	}

	
}
