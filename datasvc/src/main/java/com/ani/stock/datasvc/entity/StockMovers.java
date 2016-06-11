package com.ani.stock.datasvc.entity;

import org.apache.commons.lang3.StringUtils;



public class StockMovers {
	
	
	private String price;
	private String pointChange;
	private String percentChange;
	private String volume;
	private boolean loser;
	private String postionforTheday;
	private String ticker;
	private String company;
	
	public boolean isLoser() {
		return loser;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
		String[] split = company.split("\\(");
		ticker = StringUtils.remove(split[1], ")"); 
	}
	public void setLoser(boolean loser) {
		this.loser = loser;
	}
	public String getPostionforTheday() {
		return postionforTheday;
	}
	public void setPostionforTheday(String postionforTheday) {
		this.postionforTheday = postionforTheday;
	}

	

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPointChange() {
		return pointChange;
	}
	public void setPointChange(String pointChange) {
		this.pointChange = pointChange;
	}
	public String getPercentChange() {
		return percentChange;
	}
	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	

}
