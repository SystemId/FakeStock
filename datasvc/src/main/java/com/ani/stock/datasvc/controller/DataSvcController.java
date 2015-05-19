package com.ani.stock.datasvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class DataSvcController {
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@RequestMapping(value = "/svc/Ani", method = RequestMethod.POST)
	public byte[] retreiveStockMarketData() throws JsonProcessingException  {
		
		return objectMapper.writeValueAsBytes("jackson");
		
	}
	
	

}
