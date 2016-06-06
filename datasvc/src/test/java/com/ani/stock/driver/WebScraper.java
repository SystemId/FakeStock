package com.ani.stock.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ani.stock.datasvc.entity.Security;

public class WebScraper {
	
	public static final String SITE = "http://en.wikipedia.org/wiki/List_of_S%26P_500_companies";
	
	 public static void main(String[] args) throws IOException {
		 scrapeStocksIndex();
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

}
