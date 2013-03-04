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
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @author Vu Hoai Nam
 * <p>Description: The Data Access Object handle the data on KeyUser collection
 */
public class KeyUserDAO extends MongoDataAccess {
	
	private static Logger logger = LoggerFactory.getLogger(KeyUserDAO.class);
	
	private DBCollection keyUserCol;
	
	public KeyUserDAO() throws Exception {
		keyUserCol = super.getColl("KeyUser");
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Description: Populate list object in DbCursor into list KeyUser
	 * @param cur
	 * @return ArrayList<KeyUser>
	 * @throws Exception
	 */
	private ArrayList<KeyUser> populateKeyuser( DBCursor cur ) throws Exception {
		ArrayList<KeyUser> list = new  ArrayList<KeyUser>();
		while(cur.hasNext()) {
			KeyUser keyuser = new KeyUser();
			DBObject dbObject = cur.next();
			keyuser.setKey(dbObject.get("Key").toString());
			keyuser.setUserAccess(dbObject.get("UserAccess").toString());
			list.add(keyuser);
        }
		return list;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Populate a Dbojbect into a KeyUser
	 * @param dbObject
	 * @return KeyUser
	 * @throws Exception
	 */
	private KeyUser populate( DBObject dbObject ) throws Exception {
			
			if(dbObject == null)
			return null;
			
			KeyUser keyuser = new KeyUser();
			keyuser.setKey(dbObject.get("Key").toString());
			keyuser.setUserAccess(dbObject.get("UserAccess").toString());
			//GetKeyword by KeyUser
			KeywordDAO keywordDao = new KeywordDAO();
			keyuser.setUserKeywords(keywordDao.getKeywordByKey(keyuser.getKey()));
			
		return keyuser;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: check whether the username have access on key 
	 * @param username
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public KeyUser isUserAccessKey(String username, String key) throws Exception {
		BasicDBObject query = new BasicDBObject("UserAccess",java.util.regex.Pattern.compile(username) ); 
		query.put("Key", key);
		DBObject cur = this.keyUserCol.findOne(query);
		
		return populate(cur);	
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Get all KeyUser base on username.
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public ArrayList<KeyUser> getAllKeyUser(String username) throws Exception {
		
		//TODO - LOGGING
		Pattern pathEx = Pattern.compile("^"+ username +",", Pattern.CASE_INSENSITIVE );
		BasicDBObject query = new BasicDBObject("UserAccess", java.util.regex.Pattern.compile(username));
		DBCursor cur = this.keyUserCol.find(query).sort(new BasicDBObject("Key", 1) );
		
		return populateKeyuser(cur);	
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
