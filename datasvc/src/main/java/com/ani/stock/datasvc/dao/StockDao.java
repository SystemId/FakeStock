package com.ani.stock.datasvc.dao;

import java.util.List;

public interface StockDao {

	public void insertStock(); 

	public  List<String> grabFromSandPFromDatabase();
	
	public List<String> grabFromNewsFromDatabase();

}
