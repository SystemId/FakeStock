package com.ani.stock.query;

import org.springframework.data.mongodb.core.query.Query;

public interface QueryPreparer {
	
	public Query constructMongoTickerQuery(String ticker);
	
	public Query grabTickerCount();
	
	public Query constructMongoQueryById(String id);

}
