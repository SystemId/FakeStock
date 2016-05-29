//package com.ani.stock.mongodb;
//
//import javax.annotation.PostConstruct;
//
//import org.bson.Document;
//import org.springframework.stereotype.Component;
//
//import com.mongodb.MongoClient;
//import com.mongodb.WriteConcern;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//
//@Component("mongoDB")
//public class MongoDbImpl implements MongoDB {
//	
//	 MongoClient mongoClient;
//	
//	@PostConstruct
//	public void createConnection() {
//		mongoClient = new MongoClient("localhost", 27017);
//		mongoClient.setWriteConcern(WriteConcern.JOURNAL_SAFE);
//		
//	}
//	
////	public void getStockQuotes(){
////		MongoDatabase database = mongoClient.getDatabase("stockQuote");
////		MongoCollection<Document> collection = database.getCollection("stockTicker");
////		//collection.insert(document);
////		
////	}
//
//}
