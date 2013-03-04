
/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: June 5, 2012
 */
package com.bmastudio.dotbrandtools.dao;


import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * 
 * @author Vu Hoai Nam
 * <p>Description: This clas handle the connection of DAO in Stage1 without Spring Data Mapping for Mongodb
 */
@Singleton
public class MongoDataAccess extends CommonDAO {

	private DB db;
	private DBCollection coll;
	
	private static Logger logger = LoggerFactory.getLogger(MongoDataAccess.class);

	public DBCollection getColl( String collection ) {
		DBCollection coll = db.getCollection(collection);
		return coll;
	}

	public MongoDataAccess() throws Exception {
		super();
		db = getMongoTemplate().getDb() ;
	}
	

}
