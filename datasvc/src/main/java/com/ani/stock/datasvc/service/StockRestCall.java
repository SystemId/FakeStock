package com.ani.stock.datasvc.service;

import java.io.IOException;

import com.ani.stock.datasvc.entity.Yahoo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface StockRestCall {

	public Yahoo callYahooWebSericeHistoricalQuotes(String startdate, String endDate,
			String ticker, boolean Json) throws JsonParseException,
			JsonMappingException, IOException;

}