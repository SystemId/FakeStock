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

import com.ani.stock.datasvc.entity.Security;


public class WebScraper {
	
	public static final String SITE = "http://en.wikipedia.org/wiki/List_of_S%26P_500_companies";
	
	 public static void main(String[] args) throws IOException {
		 storeSNPIndextoDatabase();
	}
	 
	public static void storeSNPIndextoDatabase() throws IOException {
		List<Security> stockList = scrapeStocksIndex();
		for(Security stock: stockList){
			hitServer(stock.getTicker());
		}
	}
	public static List<Security> scrapeStocksIndex() throws IOException {
		List<Security> listofTickers = new ArrayList<Security>();
		Document doc = Jsoup.connect(SITE).get();
		Element table = doc.select("table.wikitable.sortable").first();
		for(Element element: table.select("tr")){
			Elements elementsByAttribute = element.select("td");
			if (elementsByAttribute.size() > 0) {
				Security sec = new Security();
				sec.setCompany(elementsByAttribute.get(3).text());
				sec.setSector(elementsByAttribute.get(1).text());
				sec.setTicker(elementsByAttribute.get(0).text());
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
