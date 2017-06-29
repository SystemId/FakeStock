package com.ani.stock.datasvc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.ani.stock.datasvc.scrape.dto.OptionsDTO;
import com.ani.stock.datasvc.scrape.dto.StockOption;
import com.ani.stock.spring.mongodb.SpringMongoDao;

public class YahooWebOptionsImpl {
	

	
	@Autowired
	SpringMongoDao springMongoDao;

	public static final String OPENSITE = "https://finance.yahoo.com/quote/"; 
	public static final String HISTORY = "/options?p=";
	
	static WebDriver driver;
	
	public void init(){
		
	
	}
	

	  public void scrape(String ticker, WebDriver driver) throws IOException {
	        //seleniumGrab();
		    this.driver = driver;
	        scrapePage(ticker);
	        
	  }  
	
	public void scrapePage(String ticker) throws IOException {
		try{
			connectToHTML(ticker);
		} catch(Exception e){
			System.out.println(e);
		}
	
		
		
	}

	private void connectToHTML(String ticker)
			throws IOException {
		String url = OPENSITE + ticker + HISTORY + ticker;
		driver.get(url);
		String pageSource = driver.getPageSource();
		Document doc = Jsoup.parse(pageSource);
		List<String> optionDates = this.grabAllOptionsDates(doc);
		optionDates.remove(0);
		this.scrapStockOptions(ticker, doc);
		if(optionDates != null){
			for(String optionDate : optionDates){
				this.getLongTermOptions(optionDate, ticker, url);
			}
		}
	}


	private void getLongTermOptions(String optionDate, String ticker, String url) {
		String optionUrl = url + "&date=" + optionDate;
		driver.get(optionUrl);
		String pageSource = driver.getPageSource();
		Document doc = Jsoup.parse(pageSource);
		
		this.scrapStockOptions(ticker, doc);
		
	}


	private void scrapStockOptions(String ticker, Document doc) {
		OptionsDTO quote = new OptionsDTO();
		String date = doc.select("span[class=Fz(s) Mend(10px)]").get(1).text();
		quote.setTicker(ticker);
		quote.setExpirationDate(date);
		quote.setToday();
		OptionsDTO optionsDTO = grabPagedCallOptions(ticker, doc, quote);
		OptionsDTO optionsDTO2 = grabPagedPutOptions(ticker, doc, optionsDTO);
		springMongoDao.insertYahooOptions(optionsDTO2);
	}


	private OptionsDTO grabPagedCallOptions(String ticker, Document doc, OptionsDTO quote) {
		StockOption option = null;
		
		Element div = doc.select("table[class=calls table-bordered W(100%) Pos(r) Bd(0) Pt(0) list-options]").first();
		if(div !=null){
			option = callsForLoop(quote, option, div);
		} else {
			
			driver.navigate().refresh();
			Element div2 = doc.select("table[class=calls table-bordered W(100%) Pos(r) Bd(0) Pt(0) list-options]").first();
			if(div2 != null){
				option = callsForLoop(quote, option, div2);
			}
		}
		return quote;
	}


	private StockOption callsForLoop(OptionsDTO quote, StockOption option,
			Element div) {
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
		return option;
	}

	private OptionsDTO grabPagedPutOptions(String ticker, Document doc,  OptionsDTO quote) {
		
		StockOption option = null;
		Element div = doc.select("table[class=puts table-bordered W(100%) Pos(r) list-options]").first();
		if(div !=null){
			option = putsForLoop(quote, option, div);
		}else {
			
			driver.navigate().refresh();
			Element div2 = doc.select("table[class=puts table-bordered W(100%) Pos(r) list-options]").first();
			if(div2 != null){
				option = callsForLoop(quote, option, div2);
			}
		}
		return quote;
	}


	private StockOption putsForLoop(OptionsDTO quote, StockOption option,
			Element div) {
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
		return option;
	}

	private List<String> grabAllOptionsDates(Document doc) {
		List<String> optionValues = new ArrayList<String>();
		Element div = doc.select("div[class=Fl(start) Pend(18px) option-contract-control drop-down-selector]").first();
		if(div != null){
			for(Element option: div.select("option")){
				String attr = option.attr("value");
				optionValues.add(attr);
			}
		}
		return optionValues;
	}
	

}
