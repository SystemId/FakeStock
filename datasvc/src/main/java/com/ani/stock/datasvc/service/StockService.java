package com.ani.stock.datasvc.service;

import org.springframework.stereotype.Component;

import com.ani.stock.datasvc.entity.Yahoo;

@Component("stockService")
public interface StockService { 
	
	
	public void handleStockEvent(Yahoo yahooCall);

}
