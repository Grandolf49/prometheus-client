package com.edifice.lora.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edifice.lora.service.CounterService;


@RestController
@RequestMapping("/counter")
public class CounterController 
{

	@Resource
	private CounterService counterService  ;


	@GetMapping(value="/count")
	public Map incrementCounter(HttpServletRequest request)
	{
		HashMap<String,Object> mp =new HashMap();
		
		/**
		 * do authorization check and passed to service layer for processing
		 */
		return counterService.incrementCounter();
		
	}

	
}
