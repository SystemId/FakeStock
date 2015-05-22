package com.ani.stock.datasvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ani.stock.datasvc.dao.StockDao;
import com.ani.stock.datasvc.service.StockService;

public class StockServiceImpl implements StockService {
	
	@Autowired
	StockDao stockDao;

	public void handleStockEvent() {
		stockDao.insertStock();
		
	}
	

}
