package com.ani.stock.datasvc.scrape.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="optionsDTO")
public class OptionsDTO {
	
	@Id
	private String id;
	

	private String ticker;
	private List<StockOption> callOptions = new ArrayList<StockOption>();
	private List<StockOption> putOptions = new ArrayList<StockOption>();
	private String today;
	private String expirationDate;
	

	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getToday() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, 1);
		SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
		String format = format1.format(now.getTime());
		return format;
	}
	
	public void setToday(String today) {
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


}
