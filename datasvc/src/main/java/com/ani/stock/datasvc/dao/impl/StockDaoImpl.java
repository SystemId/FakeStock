package com.ani.stock.datasvc.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ani.stock.datasvc.dao.StockDao;


public class StockDaoImpl extends JdbcDaoSupport implements StockDao {

	public void insertStock() {
		String sql = "INSERT INTO stock_price( " + 
				" ticker, price, daily_high, daily_low, projeccted_value) " +
				" VALUES (?, ?, ?, ?, ?) " ;
		this.getJdbcTemplate().update(sql, "52.00", "100.00", "234.23", "234", "657" );


	}

}
