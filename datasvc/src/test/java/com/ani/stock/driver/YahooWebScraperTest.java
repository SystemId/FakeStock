package com.ani.stock.driver;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.springframework.stereotype.Component;

import com.ani.stock.datasvc.scrape.dto.YahooHistoricalQuote;


public class YahooWebScraperTest {
	
	static WebDriver driver;

	
	
	public YahooWebScraperTest(){
		String driverlocation = "/Users/minimac/apps/development/geckodriver";
		System.setProperty("webdriver.gecko.driver", driverlocation);
		driver = new MarionetteDriver();
	}


	public static final String OPENSITE = "https://finance.yahoo.com/quote/"; 
	public static final String HISTORY = "/history?p=";
	
	public static void main(String[] args) throws IOException {
		YahooWebScraperTest scraper = new YahooWebScraperTest();
		scraper.scrape();
	}
	
	
	  public void scrape() throws IOException {
	        //seleniumGrab();
	        scrapePage("feye");
	  }  
	
	public YahooHistoricalQuote scrapePage(String ticker) throws IOException {
		YahooHistoricalQuote text = null;
		try{
			text = connectToHTML(ticker);
		} catch(Exception e){
			System.out.println(e);
		}
	
		
		return text;
	}

	private YahooHistoricalQuote connectToHTML(String ticker)
			throws IOException {
		String url = OPENSITE + ticker + HISTORY + ticker;
		driver.get(url);
		String pageSource = driver.getPageSource();
		Document doc = Jsoup.parse(pageSource);
		
		
		YahooHistoricalQuote quote = null;
		Element div = doc.select("table[data-test=historical-prices]").first();
		for(Element tr: div.select("tr")){
			int i = 0;
			for(Element td :tr.select("td")){
				if(quote == null){
					quote = new YahooHistoricalQuote();
					quote.setTicker(ticker);
				}
				String scrapeText = td.text();
				if(i==0){
					quote.setDate(scrapeText);
				}else if(i==1){
					quote.setOpen(scrapeText);
				}else if(i==2){
					quote.setHigh(scrapeText);
				}else if(i==3){
					quote.setLow(scrapeText);
				}else if(i==4){
					quote.setClose(scrapeText);
				}else if(i==5){
					quote.setAdjClose(scrapeText);
				}else if(i==6){
					quote.setVolume(scrapeText);
				}
				System.out.println(scrapeText);
				i++;
			}
			if(quote != null){
				break;
			}
		}
		
		
		return quote;
	}
	

//	public static void seleniumGrab(){
//	
//		driver.get("https://pubchem.ncbi.nlm.nih.gov/search/#collection=compounds&query_type=text&concise_view=true&filters=false&query=24631-29-6");
//		WebElement findElement = drive.findElement(By.id("search-app"));
//	}
	

	

}
