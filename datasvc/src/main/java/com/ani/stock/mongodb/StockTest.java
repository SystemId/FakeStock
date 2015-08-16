package com.ani.stock.mongodb;

import java.io.IOException;

import com.ani.stock.datasvc.entity.Quote;
import com.ani.stock.datasvc.service.StockRestCall;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class StockTest {
	
	public static void main (String  args[]) throws JsonParseException, JsonMappingException, IOException{
		testCallYahooWebSerice(); 
	}
	
	public static void testCallYahooWebSerice() throws JsonParseException, JsonMappingException, IOException {
		
		StockRestCall restCall = new StockRestCall();
		String buildURL = restCall.buildURL("2014-02-11", "2014-02-18", "YHOO", true);
		restCall.callYahooWebSerice(buildURL);
	}

}
