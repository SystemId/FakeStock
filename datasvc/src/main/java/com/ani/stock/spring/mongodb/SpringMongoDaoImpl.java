package com.ani.stock.spring.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.ani.stock.datasvc.entity.Yahoo;

@Component("springMongoDao")
public class SpringMongoDaoImpl implements SpringMongoDao {
	

	@Autowired
	MongoTemplate mongoTemplate;
	
	public void insertYahoo(Yahoo yahoo) {
		mongoTemplate.save(yahoo);
	}
	

}
