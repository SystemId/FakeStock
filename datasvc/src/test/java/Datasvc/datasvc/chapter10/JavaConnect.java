package Datasvc.datasvc.chapter10;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
public class JavaConnect {
  public static void main(String[] args) {
    try {
      MongoClient mongoClient = new MongoClient("localhost", 27017);
      mongoClient.setWriteConcern(WriteConcern.JOURNAL_SAFE);
      @SuppressWarnings("deprecation")
	DB db = mongoClient.getDB("words");
      DBCollection collection = db.getCollection("word_stats");
      System.out.println("Number of Documents: " + 
        new Long(collection.count()).toString());
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}