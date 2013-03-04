/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Aug 28, 2012
 */
package com.bmastudio.dotbrandtools.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmastudio.dotbrandtools.data.CompareResult;
import com.bmastudio.dotbrandtools.data.CompareResultList;
import com.bmastudio.dotbrandtools.data.StringType;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

/**
 * @author Vu Hoai Nam
 * <p>Description: The Data Access Object handle the data on CompareResult document.
 */
public class CompareResultDAO extends MongoDataAccess {
	
	private static Logger logger = LoggerFactory.getLogger(CompareResultDAO.class);
	
	DBCollection compareResult;
	
	public CompareResultDAO() throws Exception {
		compareResult = super.getColl("CompareResult");
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: populate object to ArrayList<CompareResult> 
	 * @param bdObject
	 * @return CompareResult
	 */
	private ArrayList<CompareResult> populateResult( DBCursor cur ){
		ArrayList<CompareResult> list = new  ArrayList<CompareResult>();
		while(cur.hasNext()) {
			CompareResult result = new CompareResult();
			DBObject dbObject = cur.next();
			result.setLeftWord(dbObject.get("left_word").toString());
			result.setRightWord(dbObject.get("right_word").toString());
			result.setResult(dbObject.get("result").toString());
			if( dbObject.get("result") != null && !dbObject.get("result").toString().equalsIgnoreCase(""))
				result.setResultInt( Integer.parseInt( dbObject.get("result").toString()) );
			list.add(result);
        }
		return list;
	}
	
	private CompareResultList populateResultList( ArrayList<CompareResult> compareResultList , HashMap<Entry<String, String>, String> lstStrType){
		CompareResultList compareList = new CompareResultList();
		
		ArrayList<CompareResult> listBefore = new  ArrayList<CompareResult>();
		ArrayList<CompareResult> listAfter = new  ArrayList<CompareResult>();
		ArrayList<CompareResult> listExitant = new  ArrayList<CompareResult>();
		ArrayList<CompareResult> listReserved = new  ArrayList<CompareResult>();
		
		for (CompareResult compareResult : compareResultList) {
			//Add to suitable list
			if(lstStrType.containsKey(new StringType(compareResult.getRightWord(),"after")) )
				listAfter.add(compareResult);
			
			if(lstStrType.containsKey(new StringType(compareResult.getRightWord(),"before")) )
				listBefore.add(compareResult);
			
			if(lstStrType.containsKey(new StringType(compareResult.getRightWord(),"existant")))
				listExitant.add(compareResult);
			
			if(lstStrType.containsKey(new StringType(compareResult.getRightWord(),"reserved")))
				listReserved.add(compareResult);
			
        }
		
		compareList.setBefore(listBefore);
		compareList.setAfter(listAfter);
		compareList.setExitant(listExitant);
		compareList.setReserved(listReserved);
		
		
		return compareList;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: populate object to CompareResult 
	 * @param bdObject
	 * @return CompareResult
	 */
	private CompareResult populateResult( DBObject bdObject ){
		
		CompareResult result = new CompareResult();
		if(bdObject !=null ){
			result.setLeftWord(bdObject.get("left_word").toString());
			result.setRightWord(bdObject.get("right_word").toString());
			result.setResult(bdObject.get("result").toString());
        }
	
		return result;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Description: populate the Result Right word reserved
	 * @param cur
	 * @return
	 */
	private ArrayList<CompareResult> populateResultRightWordRever( DBCursor cur ){
		ArrayList<CompareResult> list = new  ArrayList<CompareResult>();
		while(cur.hasNext()) {
			CompareResult result = new CompareResult();
			DBObject dbObject = cur.next();
			result.setLeftWord(dbObject.get("right_word").toString());
			result.setRightWord(dbObject.get("left_word").toString());
			result.setResult(dbObject.get("result").toString());
			if( dbObject.get("result") != null && !dbObject.get("result").toString().equalsIgnoreCase(""))
				result.setResultInt( Integer.parseInt( dbObject.get("result").toString()) );
			list.add(result);
        }
		return list;
	}
	
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Get Compare Result by left word.
	 * @param leftWord
	 * @return
	 * @throws Exception
	 */
	public ArrayList<CompareResult> compareResultByLeftWord(String leftWord ) throws Exception{
		BasicDBObject query = new BasicDBObject();
		query.put("left_word", leftWord);
		
		//$or listStrType query 
		List<BasicDBObject> orQueries = new ArrayList<BasicDBObject>();
		
		//List StringType: before, after, exit
		HashMap<Entry<String, String>,String> listStrType = new StringTypeDAO().getStringTypeAll();
		
		orQueries.add(new BasicDBObject("left_word", new BasicDBObject("$in", listStrType)));
		orQueries.add(new BasicDBObject("right_word", new BasicDBObject("$in", listStrType)));
		query.put("$or", orQueries);
		
		DBCursor cur = this.compareResult.find(query);
		return populateResult(cur);
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Compare the Result by right word
	 * @param rightWord
	 * @return
	 * @throws Exception
	 */
	public ArrayList<CompareResult> compareResultByRightWord(String rightWord ) throws Exception{
		BasicDBObject query = new BasicDBObject();
		query.put("right_word", rightWord);
		
		//$or listStrType query 
		List<BasicDBObject> orQueries = new ArrayList<BasicDBObject>();
		
		//List StringType: before, after, exit
		HashMap<Entry<String, String>,String> listStrType = new StringTypeDAO().getStringTypeAll();
		
		orQueries.add(new BasicDBObject("left_word", new BasicDBObject("$in", listStrType)));
		orQueries.add(new BasicDBObject("right_word", new BasicDBObject("$in", listStrType)));
		query.put("$or", orQueries);
		
		DBCursor cur = this.compareResult.find(query);
		return populateResultRightWordRever(cur);
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Get the Compare Result by Left Word.
	 * @param leftWord
	 * @param from
	 * @param to
	 * @param strType
	 * @return ArrayList<CompareResult>
	 * @throws Exception
	 */
	public ArrayList<CompareResult> getByResult(String leftWord, int from , int to , String strType ) throws Exception{
		BasicDBObject query = new BasicDBObject();
		query.put("left_word", leftWord);
		query.put("resultInt", new BasicDBObject("$gte", from).append("$lte", to));
		
		//$or listStrType query 
		List<BasicDBObject> orQueries = new ArrayList<BasicDBObject>();
		//List StringType: before, after, exit
		List<String> listStrType = new StringTypeDAO().getStringType(strType);
		orQueries.add(new BasicDBObject("left_word", new BasicDBObject("$in", listStrType)));
		orQueries.add(new BasicDBObject("right_word", new BasicDBObject("$in", listStrType)));
		
		//logger.info( this.getClass().toString() + ": getByResult()" );
		
		
		DBCursor cur = this.compareResult.find(query).sort(new BasicDBObject("resultInt",-1) );
		
		//logger.debug( cur.getQuery().toString() );
		
		return populateResult(cur);
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Get the Compare Result by Left Word.
	 * @param leftWord
	 * @param from
	 * @param to
	 * @return
	 * @throws MongoException
	 * @throws UnknownHostException
	 */
	public ArrayList<CompareResult> getByResultLeftWord(String leftWord, int from , int to, HashMap<Entry<String, String>,String> listStrType ) throws MongoException, UnknownHostException{
		BasicDBObject query = new BasicDBObject();
		query.put("left_word", leftWord);
		query.put("resultInt", new BasicDBObject("$gte", from).append("$lte", to));
		
		//$or query
		List<BasicDBObject> orQueries = new ArrayList<BasicDBObject>();
		
		orQueries.add(new BasicDBObject("left_word", new BasicDBObject("$in", listStrType.values())));
		orQueries.add(new BasicDBObject("right_word", new BasicDBObject("$in", listStrType.values())));
		
		query.put("$or", orQueries);
		
		DBCursor cur = this.compareResult.find(query).sort(new BasicDBObject("resultInt",-1) );
		
		//Log the query
		//logger.debug("Query:" + query.toString() );
		
		return populateResult(cur);
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Get the Compare Result by Right Word.
	 * @param rightWord
	 * @param from
	 * @param to
	 * @param listStrType
	 * @return
	 * @throws MongoException
	 * @throws UnknownHostException
	 */
	public ArrayList<CompareResult> getByResultRightWord(String rightWord, int from , int to, HashMap<Entry<String, String>,String> listStrType ) throws MongoException, UnknownHostException{
		BasicDBObject query = new BasicDBObject();
		query.put("right_word", rightWord);
		query.put("resultInt", new BasicDBObject("$gte", from).append("$lte", to));
		
		//$or query
		List<BasicDBObject> orQueries = new ArrayList<BasicDBObject>();
		
		orQueries.add(new BasicDBObject("left_word", new BasicDBObject("$in", listStrType.values())));
		orQueries.add(new BasicDBObject("right_word", new BasicDBObject("$in", listStrType.values())));
		
		query.put("$or", orQueries);
		
		DBCursor cur = this.compareResult.find(query).sort(new BasicDBObject("resultInt",-1) );
		
		//Log the query
		//logger.debug("Query:" + query.toString() );
		
		return populateResultRightWordRever(cur);
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Description: Get compareResult List by Result
	 * @param leftWord
	 * @param from percentage
	 * @param to percentage
	 * @return CompareResultList
	 * @throws Exception
	 */
	public CompareResultList getByResult(String leftWord, int from , int to ) throws Exception{
		
		//List StringType: before, after, exit
		HashMap<Entry<String, String>,String> listStrType = new StringTypeDAO().getStringTypeAll();
		
		ArrayList<CompareResult> compareList = new ArrayList<CompareResult>();
		
		compareList.addAll(getByResultLeftWord(leftWord, from, to, listStrType));
		if(to == 100) to--;
		compareList.addAll(getByResultRightWord(leftWord, from, to, listStrType));
		
		Collections.sort(compareList);
		
		return populateResultList(compareList, listStrType);
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: get the Identical ( the same word ) from CompareResult.
	 * @param leftWord
	 * @return
	 */
	public CompareResult getIdentical(String leftWord ){
		BasicDBObject query = new BasicDBObject();
		query.put("left_word", leftWord);
		query.put("result", "100");
		DBObject compareResult = this.compareResult.findOne(query);
		return populateResult(compareResult);
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Description: Get the list of CompareResult for download.
	 * @param keyword
	 * @return ArrayList<CompareResult>
	 * @throws Exception
	 */
	public ArrayList<CompareResult> downloadCompareResult(String keyword ) throws Exception{
		
		ArrayList<CompareResult> downloadList = compareResultByLeftWord(keyword);
		
		downloadList.addAll(0,compareResultByRightWord(keyword)); 
		
		Collections.sort(downloadList);
		
		downloadList.remove(0);
		
		return downloadList;
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
