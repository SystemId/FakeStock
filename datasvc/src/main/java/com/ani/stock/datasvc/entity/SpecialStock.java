package com.ani.stock.datasvc.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="specialTickers")
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

	@Override
	public String toString() {
		return "SpecialStock [id=" + id + ", stock=" + stock + ", addedOn="
				+ addedOn + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}

}
