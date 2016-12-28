package com.ani.stock.datasvc.dao.impl;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ani.stock.datasvc.dao.StockDao;


public class StockDaoImpl extends JdbcDaoSupport implements StockDao {

	public void insertStock() {
		String sql = "INSERT INTO stock_price( " + 
				" ticker, price, daily_high, daily_low, projeccted_value) " +
				" VALUES (?, ?, ?, ?, ?) " ;
		this.getJdbcTemplate().update(sql, "52.00", "100.00", "234.23", "234", "657" );
	}
	
	public List<String> grabFromSandPFromDatabase(){
		String sql = "Select * from SP_Stock";
		List<String> stockList = this.getJdbcTemplate().query(sql, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet result, int rowNum) throws SQLException {
				return result.getString("ticker");
			}
		});
		return stockList;
	}
	
	public List<String> grabLimitFromSandPFromDatabase(int limit, int offset){
		String sql = "Select * from SP_Stock limit ? offset ?";
		
		List<String> stockList = this.getJdbcTemplate().query(sql, new Object[] {limit, offset}, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet result, int rowNum) throws SQLException {
				return result.getString("ticker");
			}
		});
		return stockList;
	}
	
	public List<String> grabFromNewsFromDatabase(){
		String sql = "Select * from new_stock";
		List<String> stockList = this.getJdbcTemplate().query(sql, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet result, int rowNum) throws SQLException {
				return result.getString("ticker");
			}
		});
		return stockList;
	}
}
