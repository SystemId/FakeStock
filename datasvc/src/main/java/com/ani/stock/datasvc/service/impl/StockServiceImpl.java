package com.ani.stock.datasvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ani.stock.datasvc.dao.StockDao;
import com.ani.stock.datasvc.service.StockService;

@Component("stockService")
public class StockServiceImpl implements StockService {
	
	@Autowired
	StockDao stockDao;

	public void handleStockEvent() {
		stockDao.insertStock();
		
	}

	public StockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	

}
