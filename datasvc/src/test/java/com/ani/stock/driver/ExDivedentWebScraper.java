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
import com.ani.stock.datasvc.entity.Security;


public class ExDivedentWebScraper {
	
	public static final String SITE = "http://www.dividend.com/ex-dividend-dates.php";
		
	 public static void main(String[] args) throws IOException {
		 storeSNPIndextoDatabase();
	}
	 
	public static void storeSNPIndextoDatabase() throws IOException {
		List<ExDividendStock> stockList = scrapeStocksIndex();
//		for(ExDividendStock stock: stockList){
//			hitServer(stock.getStockSymbol());
//		}
		System.out.println(stockList);
	}
	public static List<ExDividendStock> scrapeStocksIndex() throws IOException {
		List<ExDividendStock> listofTickers = new ArrayList<ExDividendStock>();
		Document doc = Jsoup.connect(SITE).get();
		Element table = doc.select("table.exdiv").first();
		for(Element element: table.select("tr")){
			Elements elementsByAttribute = element.select("td");
			if (elementsByAttribute.size() > 5) {
				ExDividendStock sec = new ExDividendStock();
				sec.setStockSymbol(elementsByAttribute.get(0).text());
				sec.setCompanyName(elementsByAttribute.get(1).text());
				sec.setExDivDate(elementsByAttribute.get(3).text());
				sec.setPayDate(elementsByAttribute.get(4).text());
				sec.setPayout(elementsByAttribute.get(5).text());
				sec.setStockPrice(elementsByAttribute.get(7).text());
				sec.setDivYield(elementsByAttribute.get(8).text());
				listofTickers.add(sec);
				
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
