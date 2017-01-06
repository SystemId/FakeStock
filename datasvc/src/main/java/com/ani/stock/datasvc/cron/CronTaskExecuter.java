package com.ani.stock.datasvc.cron;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.ani.stock.datasvc.controller.YahooWebController;
import com.ani.stock.datasvc.service.StockService;

public class CronTaskExecuter {
	
	static int x = 1;
	
	@Autowired
	YahooWebController webController;
	
	public void callStockService() throws IOException {
		if(x == 21){
			x = 1;
		}
		webController.scrapeSPRange(25 * x, 25 * (x-1));
		x++;
	}
	

}
