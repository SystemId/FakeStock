package com.ani.stock.spring.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.ani.stock.datasvc.entity.SpecialStock;
import com.ani.stock.datasvc.entity.Yahoo;
import com.ani.stock.query.QueryPreparer;

@Component("springMongoDao")
public class SpringMongoDaoImpl implements SpringMongoDao {
	

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	QueryPreparer queryPreparer;
	
	public void insertYahoo(Yahoo yahoo) {
		mongoTemplate.save(yahoo);
	}
	
	public void insertIndexedYahoo(Yahoo yahoo){
		mongoTemplate.insert(yahoo, "SNPQuote");
	}
	
	public void insertSpecialStock(SpecialStock ticker){
		/*mongoTemplate.insert(ticker, "specialTickers");*/
		Query query = new Query();
		query.addCriteria(Criteria.where("stock").is(ticker.getStock()));
		Update update = new Update().set("stock", ticker.getStock()).set("addedOn", ticker.getAddedOn());
		mongoTemplate.upsert(query, update, SpecialStock.class);
	}
	
	public Yahoo getYahoo(String ticker){
		Query query = queryPreparer.constructMongoTickerQuery(ticker);
		Yahoo yahoo = mongoTemplate.findOne(query, Yahoo.class,  "stockQuote");
		return yahoo;
	}
	
	public List<Yahoo> getAllYahoo(){
		List<Yahoo> yahoo = mongoTemplate.findAll(Yahoo.class);
		return yahoo;
	}
	
	public Yahoo getTickerDataById(String id) { 
		Query query = queryPreparer.constructMongoQueryById(id);
		Yahoo yahoo = mongoTemplate.findOne(query, Yahoo.class,  "stockQuote");
		return yahoo;
	}
	
	
	
	public Yahoo getByTickerCount(){
		Query query = queryPreparer.grabTickerCount();
		Yahoo yahoo = mongoTemplate.findOne(query, Yahoo.class, "stockQuote");
		return yahoo;
	}
	
	public void removeSpecialTicker(String ticker){
		Query query = new Query();
		query.addCriteria(Criteria.where("stock").is(ticker));
		mongoTemplate.remove(query, "specialTickers");
	}
	
	public List<SpecialStock> retreiveAllSpecialStock(){
		List<SpecialStock> specialStocks = mongoTemplate.findAll(SpecialStock.class);
		return specialStocks;
	}
	
	

}
