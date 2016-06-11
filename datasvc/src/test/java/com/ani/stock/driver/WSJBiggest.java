package com.ani.stock.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ani.stock.datasvc.entity.ExDividendStock;
import com.ani.stock.datasvc.entity.StockMovers;

public class WSJBiggest {
	
	public static final String NYSE_WSJ_GAINER ="http://www.wsj.com/mdc/public/page/2_3021-gainnyse-gainer.html";
	public static final String NASDAQ_WSJ_GAINER= "http://www.wsj.com/mdc/public/page/2_3021-gainnnm-gainer.html";
	public static final String ARCA_WSJ_GAINER = "http://www.wsj.com/mdc/public/page/2_3021-gainarca-gainer.html";
	
	public static final String NYSE_WSJ_LOSER ="http://www.wsj.com/mdc/public/page/2_3021-losenyse-loser.html";
	public static final String NASDAQ_WSJ_LOSER= "http://www.wsj.com/mdc/public/page/2_3021-losennm-loser.html";
	public static final String ARCA_WSJ_LOSER = "http://www.wsj.com/mdc/public/page/2_3021-losearca-loser.html";
	
	private static final int STOPPING_NUMBER = 10;
	
	
	public static void main(String[] args) throws IOException {
		 storeSNPIndextoDatabase();
	}
	
	
	public static void storeSNPIndextoDatabase() throws IOException {
		List<StockMovers> stockList = scrapeStocksIndex();
//		for(ExDividendStock stock: stockList){
//			hitServer(stock.getStockSymbol());
//		}
		System.out.println(stockList);
	}
	public static List<StockMovers> scrapeStocksIndex() throws IOException {
		int i = 0;
		List<StockMovers> listofTickers = new ArrayList<StockMovers>();
		Document doc = Jsoup.connect(NYSE_WSJ_GAINER).get();
		Element table = doc.select("table.mdcTable").first();
		for(Element element: table.select("tr")){
			
			if(i == STOPPING_NUMBER){
				break;
			}
			Elements elementsByAttribute = element.select("td");
			
			if (elementsByAttribute.size() > 5) {
				if(!elementsByAttribute.get(2).text().equals("Price")){
					StockMovers sec = new StockMovers();
					sec.setPostionforTheday(elementsByAttribute.get(0).text());
					sec.setCompany(elementsByAttribute.get(1).text());
					sec.setPrice(elementsByAttribute.get(2).text());
					sec.setPointChange(elementsByAttribute.get(3).text());
					sec.setPercentChange(elementsByAttribute.get(4).text());
					sec.setVolume(elementsByAttribute.get(5).text());
					i++;
					listofTickers.add(sec);
				}
			}
		}
		return listofTickers;
		
	}
	
	public static String hitServer(String ticker){
		String POST_URL = "http://127.0.0.1:8080/svc/daily-tick/";
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("feye",headers);
		ResponseEntity<String> result = template.getForEntity(POST_URL + ticker, String.class);
		String resultbody = result.getBody();
		return resultbody;
	}
	
	

}
