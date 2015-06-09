package com.ani.stock.datasvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ani.stock.datasvc.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class DataSvcController {
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	StockService stockService;
	
	@RequestMapping(value = "/Ani")
	@ResponseBody
	public byte[] retreiveStockMarketData() throws JsonProcessingException  {
		stockService.handleStockEvent();
		return objectMapper.writeValueAsBytes("jackson");
		
	}

	public StockService getStockService() {
		return stockService;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}
	
	

}
