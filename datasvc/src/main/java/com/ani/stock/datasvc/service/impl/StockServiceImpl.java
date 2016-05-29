package com.ani.stock.datasvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ani.stock.datasvc.dao.StockDao;
import com.ani.stock.datasvc.entity.Yahoo;
import com.ani.stock.datasvc.service.StockService;
import com.ani.stock.spring.mongodb.SpringMongoDao;

@Component("stockService")
public class StockServiceImpl implements StockService {
	
	@Autowired
	StockDao stockDao;
	
	@Autowired
	SpringMongoDao springMongoDao;

	public void handleStockEvent(Yahoo yahooCall) {
			stockDao.insertStock();
		springMongoDao.insertYahoo(yahooCall);
		
		
	}

	public StockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}
	

}
