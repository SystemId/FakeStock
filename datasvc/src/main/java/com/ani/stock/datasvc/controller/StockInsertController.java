package com.ani.stock.datasvc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ani.stock.datasvc.email.SendEmail;
import com.ani.stock.datasvc.entity.SpecialStock;
import com.ani.stock.datasvc.entity.Yahoo;
import com.ani.stock.datasvc.service.StockRestCall;
import com.ani.stock.datasvc.service.StockService;
import com.ani.stock.datasvc.util.DataSvcUtil;
import com.ani.stock.datasvc.yahoo.intraday.YahooIntraday;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class StockInsertController {
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	StockService stockService;
	
	@Autowired
	StockRestCall stockRestCall;
	
	@Autowired
	DataSvcUtil dataSvcUtil;
	
	@Autowired
	SendEmail sendEmail;
	
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
	
	@RequestMapping(value = "/daily-tick/{ticker}")
	@ResponseBody
	public String fetchdailyStockMarketData( @PathVariable String ticker) throws IOException  {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dataSvcUtil.adjustCalendarDatesForYahoo(cal, dateFormat);
		String yesterday = dateFormat.format(cal.getTime());
		Yahoo yahooCall = stockRestCall.callYahooWebSericeHistoricalQuotes(yesterday, today, ticker, true);
		yahooCall.getQuery().getResults().getQuote().subList(1, yahooCall.getQuery().getResults().getQuote().size()).clear();
		stockService.handleIndexStockEvent(yahooCall);
		return "true";
	}
	
	@RequestMapping(value ="/intraday/{ticker}")
	@ResponseBody
	public byte[] fetchIntradayinformation(@PathVariable String ticker) throws IOException {
		YahooIntraday callIntradayQuote = stockRestCall.callIntradayQuote(ticker, true);
		return objectMapper.writeValueAsBytes(callIntradayQuote);
	}
	
	@RequestMapping(value = "/insert-special/{ticker}")
	@ResponseBody
	public String insertSpecialStock(@PathVariable String ticker) throws IOException {
		SpecialStock stock = new SpecialStock(ticker);
		stockService.insertSpecicalStock(stock);
		return ticker;
	}
	
	@RequestMapping(value = "/send-email/")
	@ResponseBody
	public String sendEmail() throws IOException, MessagingException {
		List<String> toAddress = new ArrayList<String>();
		toAddress.add("anirbaneroy@gmail.com");
		sendEmail.executeDelivery("genappsltd@gmail.com", toAddress, null, null, "test", "test", null);
		return "ok";
		
	}
	
	@RequestMapping(value = "/send-text/")
	@ResponseBody
	public String sendText() throws IOException, MessagingException {
		List<String> toAddress = new ArrayList<String>();
		toAddress.add("6109442027@txt.att.net");
		sendEmail.executeDelivery("genappsltd@gmail.com", toAddress, null, null, "test", "test", null);
		return "ok";
		
	}
	


	public StockService getStockService() {
		return stockService;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}
	
	

}
