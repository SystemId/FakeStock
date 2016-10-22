package com.ani.stock.datasvc.cron;

import org.springframework.beans.factory.annotation.Autowired;

import com.ani.stock.datasvc.service.StockService;

public class CronTaskExecuter {
	
	@Autowired
	StockService stockService;
	
	public void callStockService(){
		stockService.getTicketData("feye");
	}

}
