package com.ani.stock.datasvc.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="speicalTicker")
public class SpecialStock {
	
	@Id
	private String id;
	
	
	private String stock;
	private String addedOn;
	
	
	
	
	public SpecialStock(String stock){
		this.stock = stock;
		addedOn = dateToString(Calendar.getInstance());
	}
	
	public String dateToString(Calendar cal){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String stringedDate = dateFormat.format(cal.getTime());
		return stringedDate;
	}

}
