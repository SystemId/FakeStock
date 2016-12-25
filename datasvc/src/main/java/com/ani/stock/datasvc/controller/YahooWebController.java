package com.ani.stock.datasvc.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ani.stock.datasvc.email.SendEmail;
import com.ani.stock.datasvc.service.StockRestCall;
import com.ani.stock.datasvc.service.StockService;
import com.ani.stock.datasvc.service.StockSvc;
import com.ani.stock.datasvc.service.YahooWebOptionsImpl;
import com.ani.stock.datasvc.service.YahooWebScraperImpl;
import com.ani.stock.datasvc.util.DataSvcUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class YahooWebController {
	
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	StockService stockService;
	
	@Autowired
	StockRestCall stockRestCall;
	
	@Autowired
	DataSvcUtil dataSvcUtil;
	
	@Autowired
	SendEmail sendEmail;
	
	@Autowired
	YahooWebScraperImpl webScraper;
	
	@Autowired
	YahooWebOptionsImpl optionsScraper;
	
	@Autowired
	StockSvc stockSvc;
	
	
	//This method is designed to scrap Yahoo finance page
	@RequestMapping(value = "/daily-tick-new/{ticker}")
	@ResponseBody
	public String scrapeStockMarketData( @PathVariable String ticker) throws IOException  {
		webScraper.scrape();
		return "true";
	}
	
	
	@RequestMapping(value = "/daily-options/{ticker}")
	@ResponseBody
	public String scrapeStockMarketOptions( @PathVariable String ticker) throws IOException  {
		optionsScraper.scrape(ticker);
		return "true";
	}
	
	@RequestMapping(value ="/grab-all-sp-ticker/")
	@ResponseBody
	public String grabAllSPTicker(){
		List<String> tickers = stockSvc.grabFromSandPFromDatabase();
		return "true";
		
	}
	
	@RequestMapping(value ="/grab-all-new-ticker/")
	@ResponseBody
	public String grabAllNewsTicker(){
		List<String> tickers = stockSvc.grabFromNewsFromDatabase();
		return "true";
		
	}

}
