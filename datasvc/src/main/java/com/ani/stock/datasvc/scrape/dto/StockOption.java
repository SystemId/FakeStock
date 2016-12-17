package com.ani.stock.datasvc.scrape.dto;

public class StockOption {
	
	private String strike; 
	private String name;
	private String lastPrice;
	private String big;
	private String ask;
	private String change;
	private String changePercent;
	private String volume;
	private String openInterest;
	private String implVolatility;
	public String getStrike() {
		return strike;
	}
	public void setStrike(String strike) {
		this.strike = strike;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}
	public String getBig() {
		return big;
	}
	public void setBig(String big) {
		this.big = big;
	}
	public String getAsk() {
		return ask;
	}
	public void setAsk(String ask) {
		this.ask = ask;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getChangePercent() {
		return changePercent;
	}
	public void setChangePercent(String changePercent) {
		this.changePercent = changePercent;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getOpenInterest() {
		return openInterest;
	}
	public void setOpenInterest(String openInterest) {
		this.openInterest = openInterest;
	}
	public String getImplVolatility() {
		return implVolatility;
	}
	public void setImplVolatility(String implVolatility) {
		this.implVolatility = implVolatility;
	}

}
