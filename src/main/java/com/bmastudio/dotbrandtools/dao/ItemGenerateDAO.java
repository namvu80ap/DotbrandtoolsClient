/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Aug 23, 2012
 */
package com.bmastudio.dotbrandtools.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import com.bmastudio.BMASimilar.Services.CompareNIST;
import com.bmastudio.BMASimilar.Services.SimilarGenrator;
import com.bmastudio.BMASimilar.Services.TypoGenerator;
import com.bmastudio.BMAUtils.Utils.DotBrandToolsConst;
import com.bmastudio.dotbrandtools.data.Folder;
import com.bmastudio.dotbrandtools.data.Item;
import com.bmastudio.dotbrandtools.data.ItemGenerate;
import com.bmastudio.dotbrandtools.dataview.WordQueryView;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * @author Vu Hoai Nam
 * <p>Description: The Data Access Object handle the data on ItemGenerate collection.
 */
public class ItemGenerateDAO extends CommonDAO {

	private static Logger logger = LoggerFactory.getLogger(ItemGenerateDAO.class);
	
	/**
	 * @constructor
	 */
	public ItemGenerateDAO() throws Exception {
		super();
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Description: Query a word base on the generated words in The Document.
	 * @param word
	 * @param profileId
	 * @return List<WordQueryView>
	 * @throws Exception
	 */
	//TODO Write log
	public List<WordQueryView> queryByGenerateWord( String word , int profileId ) throws Exception{
		
		List<WordQueryView> returnList = new ArrayList<WordQueryView>();
		
		FolderDAO folderDao = new FolderDAO();
		ItemGenerateDAO dao = new ItemGenerateDAO();
		
		try{
			
			Query query = Query.query( Criteria.where(ItemGenerate.GENERATED).is(word) );
			query.sort().on(ItemGenerate.ITEM, Order.DESCENDING);
			List<ItemGenerate> listGen = dao.getMongoOps().find( query , ItemGenerate.class );
			
			Set<String> listStr = new HashSet<String>();
			for (ItemGenerate itemGenerate : listGen) {
				listStr.add(itemGenerate.getItem());
			}
			
			List<Folder> listFolder = folderDao.getFolderByProfileId(profileId);
			Set<Integer> listFolderId = new HashSet<Integer>();
			for (Folder folder : listFolder) {
				listFolderId.add(folder.getFolderId());
			}
			
			Query itemQuery = Query.query( Criteria.where( Item.NAME ).in(listStr).and(Item.FOLDER_ID).in(listFolderId) );
			itemQuery.sort().on(Item.NAME, Order.DESCENDING);
			
			List<Item> listItem = dao.getMongoOps().find( itemQuery , Item.class);
			
			//Get Folder by DBRef
			MongoConverter converter = dao.getMongoTemplate().getConverter();
			DBCollection coll = dao.getMongoTemplate().getDb().getCollection( Folder.DOCUMENT_NAME );
			
			//Populate the query to view object list
			//TODO remove init but still can get the right score
			CompareNIST.init();
			for (Item item : listItem) {
				if( !item.getTypos().equals(DotBrandToolsConst.ALLOW) 
						|| !item.getSimilar70().equals(DotBrandToolsConst.ALLOW)
						|| !item.getSimilar85().equals(DotBrandToolsConst.ALLOW) 
						)
					for (ItemGenerate itemGen : listGen) {
						if( item.getName().equals( itemGen.getItem() ) ){
							WordQueryView queryView = new WordQueryView();
							
							DBObject dbobject = coll.findOne( new BasicDBObject("_id", item.getFolder().getId() ) );
							Folder folder = converter.read( Folder.class , dbobject ) ;
							
							
							queryView.setFolderName(folder.getName());
							queryView.setFolderDescription(folder.getDescription());
							queryView.setFolderRules(folder.getRules());
							
							queryView.setItemName(item.getName());
							queryView.setItemDescription(item.getDescription());
							
							queryView.setSimiliarType( itemGen.getSimilarType() );
							
							//Check the similar Type of itemGen with item
							if( itemGen.getSimilarType().equals(DotBrandToolsConst.TYPOS) )
								queryView.setProperty(item.getTypos());
							
							else if ( itemGen.getSimilarType().equals(DotBrandToolsConst.SIMILAR70) )
								queryView.setProperty(item.getSimilar70());
							
							else if ( itemGen.getSimilarType().equals(DotBrandToolsConst.SIMILAR85) )
								queryView.setProperty(item.getSimilar85());
							
							queryView.setScore( CompareNIST.howConfusableInt( word , itemGen.getItem() ) );
							
							returnList.add(queryView);
						}
					}
				
			}
			
		}
		catch( Exception e ){
			logger.debug(e.getMessage());
			throw e;
		}finally {
			folderDao.closeConnection();
			dao.closeConnection();
		}
		
		return returnList ;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Create date: Aug 29, 2012
	 * <p>Description:
	 * @param word
	 * @return
	 * @throws Exception 
	 */
	public List<ItemGenerate> generateSimilar( String word, int thresold ) throws Exception{
		
		if( isExist( word, DotBrandToolsConst.SIMILAR70 ) ){
			logger.debug("THE GENERATE TYPOS IS ALREADY IN DATABSE :" + word );
			return null;
		}
		
		logger.debug("DATABASE ACCESS BEGIN INSERT THE GENERATE SIMILAR :" + word );
		
		//TO DO remove
		CompareNIST.init();
		HashMap<String, Integer> listSimiliar = CompareNIST.generateStr( word , thresold );
		
		Map<String, Map<String, Integer>> listAllNoMapping = SimilarGenrator.getAll( word , thresold , false );
		
		listSimiliar.putAll(listAllNoMapping.get("changeOneChar")) ;
		listSimiliar.putAll(listAllNoMapping.get("addOneChar"));
		listSimiliar.putAll(listAllNoMapping.get("subtractOneChar"));
		listSimiliar.putAll(listAllNoMapping.get("addTwoChar"));
		listSimiliar.putAll(listAllNoMapping.get("subtractTwo"));
		
		
		
		List<ItemGenerate> listGen70 = new ArrayList<ItemGenerate>();
		List<ItemGenerate> listGen85 = new ArrayList<ItemGenerate>();
		
		try {
			
			for ( Entry<String, Integer> entry : listSimiliar.entrySet() ) {
				
				if( entry.getValue() >= 85 ){ 
					ItemGenerate itemGenerate = new ItemGenerate( word, entry.getKey(), DotBrandToolsConst.SIMILAR85 );
					listGen85.add(itemGenerate);
				}
				else{
					ItemGenerate itemGenerate = new ItemGenerate( word, entry.getKey(), DotBrandToolsConst.SIMILAR70 );
					listGen70.add(itemGenerate);
				}
					
			}
			
			if( !isExist( word, DotBrandToolsConst.SIMILAR85 )){
				listGen70.addAll(listGen85);
			}
			
			
		} catch (Exception e) {
			logger.error("DATABASE INSERT LIST ITEM GENERATE ERROR " + listGen70 );
			logger.error("DATABASE INSERT LIST ITEM GENERATE ERROR " + listGen85 );
			logger.error("ERRORS: " + e.getMessage() );
			throw e ;
		}
		
		return listGen70;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Create date: Aug 29, 2012
	 * <p>Description: Insert the typos of word then insert to collection
	 * @param word
	 * @return List<ItemGenerate> the typos generated list
	 * @throws Exception 
	 */
	public List<ItemGenerate> generateTypos( String word ) throws Exception{
		
		if( isExist(word, DotBrandToolsConst.TYPOS ) ){
			logger.debug("THE GENERATE TYPOS IS ALREADY IN DATABSE :" + word );
			return new ArrayList<ItemGenerate>();
		}
		
		logger.debug("DATABASE ACCESS BEGIN INSERT THE GENERATE TYPOS :" + word );
		
		Map<String, Integer> listTypo = new TypoGenerator().getAllToOne( word );
		
		List<ItemGenerate> listGen = new ArrayList<ItemGenerate>();
		
		try {
			
			for ( Entry<String, Integer> entry : listTypo.entrySet() ) {
				
				ItemGenerate itemGenerate = new ItemGenerate( word, entry.getKey(), DotBrandToolsConst.TYPOS );
				listGen.add(itemGenerate);
				
			}
			
			//getMongoOps().insertAll( listGen );
			
		} catch (Exception e) {
			logger.error("DATABASE INSERT LIST ITEM GENERATE ERROR " + listGen );
			logger.error("ERRORS: " + e.getMessage() );
			throw e ;
		}finally {
			//closeConnection();
		}
		
		return listGen ;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Insert all the list of ItemGenerate into colleciton
	 * @param list
	 * @return true/false
	 * @throws Exception
	 */
	public boolean insertAll( List<ItemGenerate> list ) throws Exception{
		
		logger.debug("DATABASE ACCESS BEGIN INSERT THE GENERATE ALL " );
		
		ItemGenerateDAO dao = new ItemGenerateDAO();
		
		try {
			
			dao.getMongoOps().insertAll( list );
			
		} catch (Exception e) {
			logger.error("DATABASE INSERT LIST ITEM GENERATE ERROR " + list );
			logger.error("ERRORS: " + e.getMessage() );
			throw e ;
		}finally {
			dao.closeConnection();
		}
		
		return true ;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Create date: Aug 24, 2012
	 * @param name
	 * @param folderId
	 * @return
	 * @throws Exception
	 * <p>Description: Check whether document have the item with name.
	 */
	public boolean isExist( String word , String type ) throws Exception {
		boolean returnValue;
		ItemGenerateDAO dao = new ItemGenerateDAO();
		try {
			
			long count = dao.getMongoOps().count( Query.query( 
										 Criteria.where(ItemGenerate.ITEM).is(word).and(ItemGenerate.SIMILARTYPE).is(type) ), ItemGenerate.class );
			if( count >  0 )
				returnValue = true;
			else
				returnValue = false;
			
		} catch (Exception e) {
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}finally{
			dao.closeConnection();
		}
		
		return returnValue;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Create date: Sep 5, 2012
	 * @return
	 * @throws Exception
	 * <p>Description: Clean the item generate
	 * <p>Such as : item = generated
	 */
	public boolean cleanItemGenerate() throws Exception{
		
		ItemGenerateDAO dao = new ItemGenerateDAO();
		
		List<ItemGenerate> itemList = dao.getMongoOps().findAll( ItemGenerate.class );
		
		List<ItemGenerate> list = new ArrayList<ItemGenerate>();
		for (ItemGenerate itemGenerate : itemList) {
			
			if( itemGenerate.getItem().equals(itemGenerate.getGenerated()) ){
				dao.getMongoOps().remove(itemGenerate);
				list.add(itemGenerate);
			}
		}
		
		dao.closeConnection();
		
		return true;
	}
}
