package com.ani.stock.datasvc.service;

import java.util.List;

public interface StockSvc {
	
	public  List<String> grabFromSandPFromDatabase();
	
	public List<String> grabFromNewsFromDatabase();
	
	public List<String> grabLimitFromSandPFromDatabase(int limit, int offset);
		
	

}
