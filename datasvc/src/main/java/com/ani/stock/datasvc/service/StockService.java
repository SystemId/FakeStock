package com.ani.stock.datasvc.service;

import org.springframework.stereotype.Component;

@Component("stockService")
public interface StockService { 
	
	
	public void handleStockEvent();

}
