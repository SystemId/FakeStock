package com.ani.stock.datasvc.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
	

	
	private List<Quote> quote = new ArrayList<Quote>();

	public List<Quote> getQuote() {
		return quote;
	}

	public void setQuote(List<Quote> quote) {
		this.quote = quote;
	}

	



}
