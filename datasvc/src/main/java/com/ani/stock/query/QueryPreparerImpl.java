package com.ani.stock.query;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component("queryPreparer")
public class QueryPreparerImpl implements QueryPreparer { 

	public Query constructMongoTickerQuery(String ticker){
		Query q = new Query();
		q.addCriteria(Criteria.where("query.results.quote.Symbol").is(ticker));
		return q;
	}

	//dummy method used to figure out monogbd nested object
	public Query grabTickerCount(){
		Query q = new Query();
		q.addCriteria(Criteria.where("query.count").is("16"));
		return q;
	}


	//useable test id 574b6b7c0364f98133c47605
	public Query constructMongoQueryById(String id){
		Query q = new Query();
		q.addCriteria(Criteria.where("_id").is(id));
		return q;
	}

}
