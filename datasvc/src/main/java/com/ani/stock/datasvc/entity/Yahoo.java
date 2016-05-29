package com.ani.stock.datasvc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="stockQuote")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Yahoo {
	
	
	@Id
	private String id;
	
	private Query query;
	
	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}


}
