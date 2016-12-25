package com.ani.stock.datasvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ani.stock.datasvc.dao.StockDao;
import com.ani.stock.datasvc.service.StockSvc;

public class StockSvcImpl implements StockSvc {
	
	@Autowired
	StockDao stockDao;

	@Override
	public List<String> grabFromSandPFromDatabase() {
		return this.stockDao.grabFromSandPFromDatabase();
		
	}
	

}
