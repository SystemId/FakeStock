package com.ani.stock.datasvc.service;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ani.stock.datasvc.entity.Yahoo;
import com.ani.stock.datasvc.yahoo.intraday.YahooIntraday;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//This Implementation is no longer valid as Yahoo finance api no longer works


@Component("stockRestCall")
public class YahooRestCallImpl implements StockRestCall {
	
	ObjectMapper mapper = new ObjectMapper();
	
	public static final String  BASEURL_YAHOO_FINANCE = "http://query.yahooapis.com/v1/public/yql?q=" ;
	
	public static final String YAHOO_FINANCE_JSON = "&format=json&diagnostics";
	
	public static final String YAHOO_FINANCE_JSON_CALLBACK = "alltableswithkeys&callback";
	
	//public static final String SELECT_STATEMENT_YAHOO = "select * from yahoo.finance.historicaldata where symbol = "YHOO" and startDate = "2014-02-11" and endDate = "2014-02-18"&diagnostics;
			
	public static final String YAHOO_END_URL = "=true&env=store://datatables.org/alltableswithkeys&callback";
	
	
	private String buildURL(String startdate, String endDate, String ticker, boolean Json) {
		String url = BASEURL_YAHOO_FINANCE;
		String selectStatement = buildSelectStatement( startdate,  endDate,  ticker,  Json);
		String concat = url.concat(selectStatement).concat(YAHOO_FINANCE_JSON).concat(YAHOO_END_URL);
		return concat;
		
	}


	private String buildSelectStatement(String startdate, String endDate,
			String ticker, boolean json) {
		String selectStatement=  "select * from yahoo.finance.historicaldata where symbol = "; 
		String concat = selectStatement.concat('"' +  ticker ).concat("\"  and startDate = " + "\"").concat(startdate + '"').concat(" and endDate = " + '"').concat(endDate + '"');
		return concat;		
		
	}
	
	/* (non-Javadoc)
	 * @see com.ani.stock.datasvc.service.StockRestCall#callYahooWebSerice(java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	public Yahoo callYahooWebSericeHistoricalQuotes(String startdate, String endDate, String ticker, boolean Json) throws JsonParseException, JsonMappingException, IOException {
		String url = buildURL(startdate, endDate, ticker, Json);
		
		RestTemplate restTemplate = new RestTemplate();
		Yahoo stock = restTemplate.getForObject(url, Yahoo.class);
		return stock;
	}
	
	public YahooIntraday callIntradayQuote(String ticker, boolean Json){
		
		//String selectStatement=  "select * from yahoo.finance.quotewhere symbol = "; 
		//String url = BASEURL_YAHOO_FINANCE  + selectStatement + ticker + YAHOO_FINANCE_JSON;
		
		String startURL = "http://finance.yahoo.com/webservice/v1/symbols/";
		String endURL = "/quote?format=json&view=detail";
		
		String restUrl = startURL + ticker + endURL;
		
		RestTemplate restTemplate = new RestTemplate();
		YahooIntraday stock = restTemplate.getForObject(restUrl, YahooIntraday.class);
		return stock;
	}
	

}
