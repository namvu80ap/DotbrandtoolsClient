/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 5, 2012
 */
package com.bmastudio.dotbrandtools.dao;

import java.util.ArrayList;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmastudio.dotbrandtools.data.KeyUser;
import com.bmastudio.dotbrandtools.data.Keyword;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * 
 * @author Vu Hoai Nam
 * <p>Description: The Data Access Object handle the data on Keyword collection
 */
public class KeywordDAO extends MongoDataAccess {
	
	private static Logger logger = LoggerFactory.getLogger(KeywordDAO.class);
	
	private DBCollection keywordsCol;
	
	public KeywordDAO() throws Exception {
		keywordsCol = super.getColl("Keywords");
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description:Populate the list object in DBCrusor into ArrayList<Keyword>.
	 * @param cur
	 * @return
	 */
	private ArrayList<Keyword> populateKeyword( DBCursor cur ){
		ArrayList<Keyword> list = new  ArrayList<Keyword>();
		while(cur.hasNext()) {
			Keyword keyword = new Keyword();
			DBObject dbObject = cur.next();
			keyword.setId(dbObject.get("id").toString());
			keyword.setDescription(dbObject.get("description").toString());
			keyword.setPath(dbObject.get("path").toString());
			list.add(keyword);
        }
		return list;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description:Get all Keyword by list KeyUser.
	 * @param keyUser
	 * @return ArrayList<ArrayList<Keyword>>
	 */
	public ArrayList<ArrayList<Keyword>> getAllKeywordByKeyUser( ArrayList<KeyUser> keyUser ) {
		ArrayList<ArrayList<Keyword>> list = new ArrayList<ArrayList<Keyword>>();
		for (KeyUser keyuser : keyUser) {
			Pattern pathEx = Pattern.compile("^"+ keyuser.getKey() +",", Pattern.CASE_INSENSITIVE );
			BasicDBObject query = new BasicDBObject("path", pathEx);
			DBCursor cur = this.keywordsCol.find(query);
			list.add(populateKeyword(cur));
		}
		return list;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Get Keyword by Key.
	 * @param key
	 * @return ArrayList<Keyword> 
	 */
	public ArrayList<Keyword> getKeywordByKey( String key ) {
		
		Pattern pathEx = Pattern.compile("^"+ key +",", Pattern.CASE_INSENSITIVE );
		BasicDBObject query = new BasicDBObject("path", pathEx);
		DBCursor cur = this.keywordsCol.find(query);
		
		return populateKeyword(cur);
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Get Keyword by path.
	 * @param key
	 * @return ArrayList<Keyword> 
	 */
	public ArrayList<Keyword> getByPath( String path ){
		
		Pattern pathEx = Pattern.compile("^"+path+",", Pattern.CASE_INSENSITIVE );
		
		BasicDBObject query = new BasicDBObject("path", pathEx );
		
		DBCursor cur = this.keywordsCol.find(query);
		
		return populateKeyword(cur);
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
