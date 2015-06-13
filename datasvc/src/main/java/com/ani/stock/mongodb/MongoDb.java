package com.ani.stock.mongodb;

import com.mongodb.MongoClient;

public class MongoDb {
	
	public void createConnection() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		
	}

}
