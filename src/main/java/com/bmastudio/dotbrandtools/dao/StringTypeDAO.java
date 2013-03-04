/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 10, 2012
 */
package com.bmastudio.dotbrandtools.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmastudio.dotbrandtools.data.StringType;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * @author Vu Hoai Nam
 * <p>Description: The Data Access Object handle the data on StringType collection
 */
public class StringTypeDAO extends MongoDataAccess {
	
	private static Logger logger = LoggerFactory.getLogger(StringTypeDAO.class);
	
	private DBCollection strTypeCol;
	
	public StringTypeDAO() throws Exception {
		strTypeCol = super.getColl("StringType");
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Description: get the list of String by type.
	 * @param type
	 * @return List<String>
	 * @throws MongoException
	 * @throws UnknownHostException
	 */
	public List<String> getStringType(String type) throws MongoException, UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.put("type", type);
		DBCursor cur = this.strTypeCol.find(query).sort(new BasicDBObject("word",-1) );
		
		List<String> list = new  ArrayList<String>();
		while(cur.hasNext()) {
			DBObject dbObject = cur.next();
			String word = dbObject.get("word").toString();
			list.add(word);
        }
		
	return list;
	}

	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Get all StringType
	 * @return HashMap<Entry<String, String>,String> 
	 * @throws MongoException
	 * @throws UnknownHostException
	 */
	public HashMap<Entry<String, String>,String> getStringTypeAll() throws MongoException, UnknownHostException {
		
		BasicDBObject query = new BasicDBObject();
		query.put("type", new BasicDBObject("$nin", new String[]{"before"}));
		
		DBCursor cur = this.strTypeCol.find(query).sort(new BasicDBObject("word",-1) );
		
		HashMap<Entry<String, String>,String> list = new  HashMap<Entry<String, String>,String>();
		while(cur.hasNext()) {
			DBObject dbObject = cur.next();
			StringType strType = new StringType();
			strType.setWord(dbObject.get("word").toString());
			strType.setType(dbObject.get("type").toString());
			list.put( strType , dbObject.get("word").toString() );
        }
		
	   return list;
	}
	
	@Override
	protected void finalize() throws Throwable {
		try{
			closeConnection();
	        logger.debug("CLOSE CONNECTION");
        }
        catch(Exception e){
        	logger.error("CAN NOT CLOSE THE CONNECTION");
        }
        finally{
            super.finalize();
        }   
	}
	
}
