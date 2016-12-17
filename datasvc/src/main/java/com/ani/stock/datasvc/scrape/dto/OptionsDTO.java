package com.ani.stock.datasvc.scrape.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OptionsDTO {
	
	private String ticker;
	private List<StockOption> callOptions = new ArrayList<StockOption>();
	private List<StockOption> putOptions = new ArrayList<StockOption>();
	private Calendar today = Calendar.getInstance();
	
	public Calendar getToday() {
		return today;
	}
	public void setToday(Calendar today) {
		this.today = today;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public List<StockOption> getCallOptions() {
		return callOptions;
	}
	public void setCallOptions(List<StockOption> callOptions) {
		this.callOptions = callOptions;
	}
	public List<StockOption> getPutOptions() {
		return putOptions;
	}
	public void setPutOptions(List<StockOption> putOptions) {
		this.putOptions = putOptions;
	}


}
