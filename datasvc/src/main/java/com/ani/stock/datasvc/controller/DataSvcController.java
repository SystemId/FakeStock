package com.ani.stock.datasvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class DataSvcController {
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@RequestMapping(value = "/Ani")
	@ResponseBody
	public byte[] retreiveStockMarketData() throws JsonProcessingException  {
		System.out.println("asdasdsadasd");
		return objectMapper.writeValueAsBytes("jackson");
		
	}
	
	

}
