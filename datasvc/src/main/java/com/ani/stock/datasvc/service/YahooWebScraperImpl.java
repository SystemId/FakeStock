package com.ani.stock.datasvc.service;

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


public class YahooWebScraperImpl {
	
	static WebDriver driver;

	YahooHistoricalQuote quote;
	
	public void init(){
		String driverlocation = "/Users/minimac/apps/development/geckodriver";
		System.setProperty("webdriver.gecko.driver", driverlocation);
		driver = new MarionetteDriver();
	}


	public static final String OPENSITE = "https://finance.yahoo.com/quote/"; 
	public static final String HISTORY = "/history?p=";
	
	  public void scrape() throws IOException {
	        
	        seleniumGrab();
	      //  scraper.scrapePage("feye");
	  }  
	
	public String scrapePage(String ticker) throws IOException {
		String text = null;
		try{
			text = connectToHTML(ticker);
		} catch(Exception e){
			System.out.println(e);
		}
	
		
		return text;
	}

	private String connectToHTML(String ticker)
			throws IOException {
		String url = OPENSITE + ticker + HISTORY + ticker;
		Document doc = Jsoup.connect(url).timeout(15*1000).get();
		String text = null;
		
		Element div = doc.select("div#main-content").first();
		for(Element element: div.select("h3")){
			text = "\"" + element.text() + "\"";
			break;
		}
		
		return text;
	}
	

	public static void seleniumGrab(){
		WebDriver drive = new FirefoxDriver();
		driver.get("https://pubchem.ncbi.nlm.nih.gov/search/#collection=compounds&query_type=text&concise_view=true&filters=false&query=24631-29-6");
		WebElement findElement = drive.findElement(By.id("seach-app"));
	}
	

	

}
