/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 5, 2012
 */
package com.bmastudio.dotbrandtools.dao;

import javax.inject.Singleton;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;


/**
 * @author Vu Hoai Nam
 *
 *	<p>Create date: Sep 5, 2012
 *	<p> Description: The collect of common actions for DAO object:
 * 
 */
@Singleton
public class CommonDAO {
	
	private final MongoOperations mongoOps = SpringMongoConfig.mongoTemplate();
	
	/**
	 * @return the mongoOps
	 */
	public MongoOperations getMongoOps() {
		return mongoOps;
	}
	
	/**
	 * @return the mongoTemplate
	 */
	public MongoTemplate getMongoTemplate() {
		return ( MongoTemplate )mongoOps;
	}
	
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Create date: Sep 3, 2012
	 * <p>Description: Try to close connection after using.
	 */
	public void closeConnection(){
		try {
			getMongoTemplate().getDb().getMongo().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception 
	 * @constructor
	 */
	public CommonDAO() throws Exception {
		super();
	}
	
}


