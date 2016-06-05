package com.ani.stock.datasvc.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ani.stock.datasvc.entity.Yahoo;

@Component("stockService")
public interface StockService { 
	
	
	public void handleStockEvent(Yahoo yahooCall);

	public Yahoo getTicketData(String ticker);
	
	public Yahoo getTicketDataById(String id);
	
	public List<Yahoo> getAllYahoo();

}
