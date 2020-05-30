package com.edifice.lora.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edifice.lora.service.TempratureService;



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
	
}
