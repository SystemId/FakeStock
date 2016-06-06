package com.ani.stock.datasvc.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ani.stock.datasvc.entity.Yahoo;
import com.ani.stock.datasvc.service.StockRestCall;
import com.ani.stock.datasvc.service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class StockInsertController {
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	StockService stockService;
	
	@Autowired
	StockRestCall stockRestCall;
	
//	@Autowired
//	MongoDB mongoDB;
//	
	
	//Rest Service used to grab a multi day spread of prices from the s & p
	@RequestMapping(value = "/Ani/{startDate}/{endDate}/{ticker}/{Json}")
	@ResponseBody
	public byte[] fetchStockMarketData(@PathVariable String startDate,@PathVariable String endDate, @PathVariable String ticker, @PathVariable boolean Json) throws IOException  {
		Yahoo yahooCall = stockRestCall.callYahooWebSericeHistoricalQuotes(startDate, endDate, ticker, Json);
		stockService.handleStockEvent(yahooCall);
	
		return objectMapper.writeValueAsBytes(yahooCall);
		
	}
	
	
	
	
	
//	@RequestMapping(value = "/Ani/{startDate}/{endDate}/{ticker}/{Json}", method = RequestMethod.POST)
//	@ResponseBody
//	public byte[] retreiveStockMarketData(@PathVariable String startdate,@PathVariable String endDate, @PathVariable String ticker, @RequestBody byte[] jsonData) throws IOException  {
//		stockRestCall.callYahooWebSerice(startdate, endDate, ticker, Json);
//		stockService.handleStockEvent();
//		return objectMapper.writeValueAsBytes("jackson");
//		
//	}

	public StockService getStockService() {
		return stockService;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}
	
	

}
