package com.ani.stock.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.springframework.stereotype.Component;

import com.ani.stock.datasvc.scrape.dto.OptionsDTO;
import com.ani.stock.datasvc.scrape.dto.StockOption;
import com.ani.stock.datasvc.scrape.dto.YahooHistoricalQuote;


public class OptionsWebScraperTest {
	
	static WebDriver driver;


	
	public OptionsWebScraperTest(){
		String driverlocation = "/Users/minimac/apps/development/geckodriver";
		System.setProperty("webdriver.gecko.driver", driverlocation);
		driver = new MarionetteDriver();
	}


	public static final String OPENSITE = "https://finance.yahoo.com/quote/"; 
	public static final String HISTORY = "/options?p=";
	
	public static void main(String[] args) throws IOException {
		OptionsWebScraperTest scraper = new OptionsWebScraperTest();
		scraper.scrape();
	}
	

	  public void scrape() throws IOException {
	        //seleniumGrab();
	        scrapePage("feye");
	  }  
	
	public OptionsDTO scrapePage(String ticker) throws IOException {
		OptionsDTO text = null;
		try{
			text = connectToHTML(ticker);
		} catch(Exception e){
			System.out.println(e);
		}
	
		
		return text;
	}

	private OptionsDTO connectToHTML(String ticker)
			throws IOException {
		String url = OPENSITE + ticker + HISTORY + ticker;
		driver.get(url);
		String pageSource = driver.getPageSource();
		Document doc = Jsoup.parse(pageSource);
		OptionsDTO quote = new OptionsDTO();
		String date = doc.select("span[class=Fz(s) Mend(10px)]").get(1).text();
		quote.setTicker(ticker);
		quote.setExpirationDate(date);
		List<String> optionDates = this.grabAllOptionsDates(doc);
		OptionsDTO optionsDTO = grabPagedCallOptions(ticker, doc, quote);
		OptionsDTO optionsDTO2 = grabPagedPutOptions(ticker, doc, optionsDTO);
		return optionsDTO2;
	}


	private OptionsDTO grabPagedCallOptions(String ticker, Document doc, OptionsDTO quote) {
		
		StockOption option = null;
	
		Element div = doc.select("table[class=calls table-bordered W(100%) Pos(r) Bd(0) Pt(0) list-options]").first();
		for(Element tr: div.select("tr")){
			int i = 0;
			for(Element td :tr.select("td")){
				
				if(option == null){
					option = new StockOption();
				}
				String scrapeText = td.text();
				if(i==0){
					option.setStrike(scrapeText);
				}else if(i==1){
					option.setName(scrapeText);
				}else if(i==2){
					option.setLastPrice(scrapeText);
				}else if(i==3){
					option.setBid(scrapeText);
				}else if(i==4){
					option.setAsk(scrapeText);
				}else if(i==5){
					option.setChange(scrapeText);
				}else if(i==6){
					option.setChangePercent(scrapeText);
				}else if(i==7){
					option.setVolume(scrapeText);
				}else if(i==8){
					option.setOpenInterest(scrapeText);
				}else if(i==9){
					option.setImplVolatility(scrapeText);
					quote.getCallOptions().add(option);
					option = null;
				}
				System.out.println(scrapeText);
				i++;
			}
		}
		return quote;
	}

	private OptionsDTO grabPagedPutOptions(String ticker, Document doc,  OptionsDTO quote) {
		
		StockOption option = null;
		
		Element div = doc.select("table[class=puts table-bordered W(100%) Pos(r) list-options]").first();
		for(Element tr: div.select("tr")){
			int i = 0;
			for(Element td :tr.select("td")){
				
				if(option == null){
					option = new StockOption();
				}
				String scrapeText = td.text();
				if(i==0){
					option.setStrike(scrapeText);
				}else if(i==1){
					option.setName(scrapeText);
				}else if(i==2){
					option.setLastPrice(scrapeText);
				}else if(i==3){
					option.setBid(scrapeText);
				}else if(i==4){
					option.setAsk(scrapeText);
				}else if(i==5){
					option.setChange(scrapeText);
				}else if(i==6){
					option.setChangePercent(scrapeText);
				}else if(i==7){
					option.setVolume(scrapeText);
				}else if(i==8){
					option.setOpenInterest(scrapeText);
				}else if(i==9){
					option.setImplVolatility(scrapeText);
					quote.getPutOptions().add(option);
					option = null;
				}
				System.out.println(scrapeText);
				i++;
			}
		}
		return quote;
	}

	private List<String> grabAllOptionsDates(Document doc) {
		List<String> optionValues = new ArrayList<String>();
		Element div = doc.select("div[class=Fl(start) Pend(18px) option-contract-control drop-down-selector]").first();
		for(Element option: div.select("option")){
			String attr = option.attr("value");
			optionValues.add(attr);
		}
		return optionValues;
	}
	

//	public static void seleniumGrab(){
//	
//		driver.get("https://pubchem.ncbi.nlm.nih.gov/search/#collection=compounds&query_type=text&concise_view=true&filters=false&query=24631-29-6");
//		WebElement findElement = drive.findElement(By.id("search-app"));
//	}
	

	

}
