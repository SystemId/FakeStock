package com.ani.stock.spring.mongodb;

import java.util.List;

import com.ani.stock.datasvc.entity.Yahoo;

public interface SpringMongoDao {
	

	
	public void insertYahoo(Yahoo yahoo);
	
	public void insertIndexedYahoo(Yahoo yahoo);
		
	public Yahoo getYahoo(String ticker);
	
	public Yahoo getTickerDataById(String id);
	
	public List<Yahoo> getAllYahoo();
	
	public Yahoo getByTickerCount();
	
	


}
