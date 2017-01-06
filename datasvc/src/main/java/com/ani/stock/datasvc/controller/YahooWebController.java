package com.ani.stock.datasvc.controller;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
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
	
	static WebDriver driver;
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
	
	private void createDriver(){
		String driverlocation = "/Users/minimac/apps/development/geckodriver";
		System.setProperty("webdriver.gecko.driver", driverlocation);
		driver = new MarionetteDriver();
	}
	
	
	//This method is designed to scrap Yahoo finance page
	@RequestMapping(value = "/daily-tick-new/{ticker}")
	@ResponseBody
	public String scrapeStockMarketData( @PathVariable String ticker) throws IOException  {
		webScraper.scrape(ticker);
		return "true";
	}
	
	
	@RequestMapping(value = "/daily-options/{ticker}")
	@ResponseBody
	public String scrapeStockMarketOptions( @PathVariable String ticker) throws IOException  {
		optionsScraper.scrape(ticker, driver);
		return "true";
	}
	
	@RequestMapping(value ="/grab-all-sp-ticker/{startList}")
	@ResponseBody
	public String grabAllSPTicker(@PathVariable int startList) throws IOException{
	
		
		List<String> tickers = stockSvc.grabFromSandPFromDatabase();
		tickers.subList(0, startList).clear();;
		for(String ticker: tickers){
			optionsScraper.scrape(ticker, driver);           
			System.out.println(ticker);
		}
		return "true";
		
	}
	
	@RequestMapping(value ="/grab-all-sp-ticker/{startList}/{stopList}")
	@ResponseBody
	public String grabSPRange(@PathVariable int startList, @PathVariable int stopList) throws IOException{
		scrapeSPRange(startList, stopList);
		return "true";
		
	}


	public void scrapeSPRange(int startList, int stopList) throws IOException {
		this.createDriver();
		List<String> tickers = stockSvc.grabLimitFromSandPFromDatabase(startList, stopList);
		for(String ticker: tickers){
			optionsScraper.scrape(ticker, driver);           
			System.out.println(ticker);
		}
		driver.close();
	}
	
	@RequestMapping(value ="/grab-all-new-ticker/")
	@ResponseBody
	public String grabAllNewsTicker(){
		List<String> tickers = stockSvc.grabFromNewsFromDatabase();
		return "true";
		
	}

}
