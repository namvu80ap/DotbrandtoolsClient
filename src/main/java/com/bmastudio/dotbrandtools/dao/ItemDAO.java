/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Aug 24, 2012
 */
package com.bmastudio.dotbrandtools.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.bmastudio.BMASimilar.Services.CompareNIST;
import com.bmastudio.BMAUtils.Utils.BmaDataUtils;
import com.bmastudio.BMAUtils.Utils.DotBrandToolsConst;
import com.bmastudio.dotbrandtools.data.Folder;
import com.bmastudio.dotbrandtools.data.Item;
import com.bmastudio.dotbrandtools.dataview.ItemView;
import com.bmastudio.dotbrandtools.dataview.WordQueryView;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;


/**
 * @author Vu Hoai nam
 * <p> Description: The Data Access Object handle the data on Item document.
 */
public class ItemDAO extends CommonDAO {

	private static Logger logger = LoggerFactory.getLogger(ItemDAO.class);
	
	/**
	 * @construction
	 */
	public ItemDAO() throws Exception {
		super();
	}
	
	/**
	 * 
	 * Create by user
	 * Create date: Aug 24, 2012
	 * @param item
	 * @return
	 * @throws Exception
	 * <p>Description: Insert an item to database. If exists itemTypes, this function will insert the itemType too.
	 */
	public Item createItem( Item item ) throws Exception {
		
		logger.debug("DATABASE ACCESS BEGIN INSERT :" + item.toString());
		ItemDAO dao = new ItemDAO();
		try{
			
			dao.getMongoOps().insert(item);
			logger.debug("DATABASE INSERT SUCCESS :" + item.toString());
		}
		catch (Exception e) {
			logger.error("DATABASE INSERT ERROR : " + item.toString());
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}finally {
			dao.closeConnection();
		}
		
		return item;
	}
	
	/**
	 * 
	 * Create by user
	 * Create date: Aug 24, 2012
	 * @param item
	 * @return
	 * @throws Exception
	 * <p>Description: Insert a list of Item to database. If exists itemTypes, this function will insert the itemType too.
	 */
	public boolean createListItem( Collection<Item> listItem ) throws Exception {
		
		logger.debug("DATABASE ACCESS BEGIN INSERT LIST ITEM :" + listItem );
		ItemDAO dao = new ItemDAO();
		try{
			
			dao.getMongoOps().insertAll(listItem);
			
			logger.debug("DATABASE INSERT LIST ITEM SUCCESS :" + listItem );
		}
		catch (Exception e) {
			logger.error("DATABASE INSERT LIST ITEM ERROR : " + listItem );
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}finally{
			dao.closeConnection();
		}
		
		return true ;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * Create date: Sep 4, 2012
	 * @param itemNames
	 * @param form
	 * @return
	 * @throws Exception 
	 * <p>Description: Update a list of Items
	 *
	public boolean updateListItem( Collection<String> itemNames , BulkListEditForm form ) throws Exception{
		
		
		logger.debug("DATABASE ACCESS BEGIN UDPATE LIST ITEM :" + itemNames );
		
		ItemDAO dao = new ItemDAO();
		
		try{
			Update update = new Update();
			
			
			update.set(Item.ITEM_IDENTICAL, form.getIdentical() );
			
			//WITH NO DESCRIPTION
			//update.set(Item.DESCRIPTION, form.getDescription());
			
			update.set(Item.ITEM_TYPOS, form.getTypos() );
			
			update.set(Item.ITEM_WILDCARD, form.getWildCard() );
			update.set(Item.ITEM_STARTWITH, form.getStartWith() );
			update.set(Item.ITEM_ENDWITH, form.getEndWith() );
			
			update.set(Item.ITEM_SIMILAR70, form.getSimilar70() );
			update.set(Item.ITEM_SIMILAR85, form.getSimilar85() );
			
			dao.getMongoOps().updateMulti( Query.query( Criteria.where(Item.NAME).in(itemNames).and(Item.FOLDER_ID).in(form.getFolderId()) ) 
									, update, Item.class );
			
			logger.debug("DATABASE UDPATE LIST ITEM SUCCESS :" + itemNames );
		}
		catch (Exception e) {
			logger.error("DATABASE UDPATE LIST ITEM ERROR : " + itemNames );
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}finally {
			dao.closeConnection();
		}
		
		return true ;
		
	}*/
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * Create date: Sep 4, 2012
	 * @param itemNames
	 * @param form
	 * @return
	 * @throws Exception 
	 * <p>Description: Update a list of Items
	 *
	public boolean updateListItemView( Collection<ItemView> itemViews , BulkListEditForm form ) throws Exception{
		
		
		logger.debug("DATABASE ACCESS BEGIN UDPATE LIST ITEM :" + itemViews );
		
		ItemDAO dao = new ItemDAO();
		
		try{
			
			for (ItemView itemView : itemViews) {
				
				Update update = new Update();
				update.set(Item.ITEM_IDENTICAL, form.getIdentical() );
				
				if( itemView.getDescription() != null && !itemView.getDescription().equals("") )
					update.set(Item.DESCRIPTION, itemView.getDescription());
				
				update.set(Item.ITEM_TYPOS, form.getTypos() );
				
				update.set(Item.ITEM_WILDCARD, form.getWildCard() );
				update.set(Item.ITEM_STARTWITH, form.getStartWith() );
				update.set(Item.ITEM_ENDWITH, form.getEndWith() );
				
				update.set(Item.ITEM_SIMILAR70, form.getSimilar70() );
				update.set(Item.ITEM_SIMILAR85, form.getSimilar85() );
				
				dao.getMongoOps().updateMulti( Query.query( Criteria.where(Item.NAME).is(itemView.getName()).and(Item.FOLDER_ID).in(form.getFolderId()) ) 
										, update, Item.class );
			}
			
			
			logger.debug("DATABASE UDPATE LIST ITEM SUCCESS :" + itemViews );
		}
		catch (Exception e) {
			logger.error("DATABASE UDPATE LIST ITEM ERROR : " + itemViews );
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}finally {
			dao.closeConnection();
		}
		
		return true ;
		
	}
	*/
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * Create date: Sep 4, 2012
	 * @param folderId
	 * @return
	 * @throws Exception
	 * <p>Description: remove all item in folder by folderId
	 */
	public boolean removeItemByFolder( int folderId ) throws Exception {
		
		logger.debug("DATABASE ACCESS BEGIN REMOVE ALL ITEM IN FOLDER :" + folderId );
		ItemDAO dao = new ItemDAO();
		try{
			
			dao.getMongoOps().remove( Query.query( Criteria.where(Item.FOLDER_ID).is(folderId)), Item.class );
			
			logger.debug("DATABASE REMOVE ALL ITEM IN FOLDER SUCCESS :" + folderId );
		}
		catch (Exception e) {
			logger.error("DATABASE REMOVE ALL ITEM IN FOLDER ERROR : " + folderId );
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}finally {
			dao.closeConnection();
		}
		
		return true ;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * Create date: Aug 30, 2012
	 * @param word
	 * @param profileId
	 * @return
	 * @throws Exception
	 * <p>Description: query the wildcard and identical item by word and profileId
	 */
	public List<WordQueryView> queryWildCardIdentical( String word, int profileId ) throws Exception{
		
		logger.debug("DATABASE BEGIN GET ITEM DATA BY WORD :" + word );
		List<WordQueryView> returnList = new ArrayList<WordQueryView>();
		FolderDAO folderDao = new FolderDAO();
		ItemDAO itemDao = new ItemDAO();
		
		try {
			
			DBCollection coll = folderDao.getMongoTemplate().getDb().getCollection( Folder.DOCUMENT_NAME );
			
			List<Folder> listFolder = folderDao.getFolderByProfileId(profileId);
			Set<Integer> listFolderId = new HashSet<Integer>();
			for (Folder folder : listFolder) {
				listFolderId.add(folder.getFolderId());
			}
			
			Query itemQuery = Query.query( Criteria.where(Item.FOLDER_ID).in(listFolderId) );
			itemQuery.sort().on(Item.NAME, Order.DESCENDING);
			List<Item> listItem = itemDao.getMongoOps().find( itemQuery , Item.class);
			
			//Populate the query to view object list
			//TODO remove init but still can get the right score
			CompareNIST.init();
			for (Item item : listItem) {
				
				//Check wildcard
				if( !BmaDataUtils.checkItemAllow( item, DotBrandToolsConst.WILDCARD ) 
						&& BmaDataUtils.isWildCard(item.getName(), word) 
						&& !item.getWildcard().equals(DotBrandToolsConst.ALLOW) ){
					WordQueryView queryView = new WordQueryView();
					
					MongoConverter converter = folderDao.getMongoTemplate().getConverter();
					
					DBObject dbobject = coll.findOne( new BasicDBObject("_id", item.getFolder().getId() ) );
					Folder folder = converter.read( Folder.class , dbobject ) ;
					queryView.setFolderName(folder.getName());
					queryView.setFolderDescription(folder.getDescription());
					queryView.setFolderRules(folder.getRules());
					
					queryView.setItemName(item.getName());
					queryView.setItemDescription(item.getDescription());
					
					queryView.setSimiliarType( DotBrandToolsConst.WILDCARD );
					
					queryView.setProperty(item.getWildcard());
					
					
					queryView.setScore( CompareNIST.howConfusableInt( word , item.getName() ) );
					
					returnList.add(queryView);
				}
				
				//Check Identical
				if( !BmaDataUtils.checkItemAllow( item, DotBrandToolsConst.IDENTICAL ) 
						&& item.getName().equals( word ) 
						&& !item.getIdentical().equals(DotBrandToolsConst.ALLOW) ){
					WordQueryView queryView = new WordQueryView();
					
					MongoConverter converter = folderDao.getMongoTemplate().getConverter();
					DBObject dbobject = coll.findOne( new BasicDBObject("_id", item.getFolder().getId() ) );
					Folder folder = converter.read( Folder.class , dbobject ) ;
					
					queryView.setFolderName(folder.getName());
					queryView.setFolderDescription(folder.getDescription());
					queryView.setFolderRules(folder.getRules());
					
					queryView.setItemName(item.getName());
					queryView.setItemDescription(item.getDescription());
					
					queryView.setSimiliarType( DotBrandToolsConst.IDENTICAL );
					queryView.setProperty(item.getIdentical());
						
					
					queryView.setScore(100);
					returnList.add(queryView);
				}
				
				//Check StartWith
				if( !BmaDataUtils.checkItemAllow( item, DotBrandToolsConst.STARTWITH ) 
						&& word.startsWith(item.getName() ) 
						&& !item.getStartWith().equals(DotBrandToolsConst.ALLOW) ){
					WordQueryView queryView = new WordQueryView();
					
					MongoConverter converter = folderDao.getMongoTemplate().getConverter();
					
					DBObject dbobject = coll.findOne( new BasicDBObject("_id", item.getFolder().getId() ) );
					Folder folder = converter.read( Folder.class , dbobject ) ;
					
					queryView.setFolderName(folder.getName());
					queryView.setFolderDescription(folder.getDescription());
					queryView.setFolderRules(folder.getRules());
					
					queryView.setItemName(item.getName());
					queryView.setItemDescription(item.getDescription());
					
					queryView.setSimiliarType( DotBrandToolsConst.STARTWITH );
					
					queryView.setProperty(item.getStartWith());
					
					queryView.setScore( CompareNIST.howConfusableInt( word , item.getName() ) );
					
					returnList.add(queryView);
				}
				
				//Check EndWith
				if( !BmaDataUtils.checkItemAllow( item, DotBrandToolsConst.ENDWITH ) 
						&& word.endsWith( item.getName() ) 
						&& !item.getEndWith().equals(DotBrandToolsConst.ALLOW)  ){
					WordQueryView queryView = new WordQueryView();
					
					MongoConverter converter = folderDao.getMongoTemplate().getConverter();
					
					DBObject dbobject = coll.findOne( new BasicDBObject("_id", item.getFolder().getId() ) );
					Folder folder = converter.read( Folder.class , dbobject ) ;
					
					queryView.setFolderName(folder.getName());
					queryView.setFolderDescription(folder.getDescription());
					queryView.setFolderRules(folder.getRules());
					
					queryView.setItemName(item.getName());
					queryView.setItemDescription(item.getDescription());
					
					queryView.setSimiliarType( DotBrandToolsConst.ENDWITH );
					
					queryView.setProperty(item.getEndWith());
					
					queryView.setScore( CompareNIST.howConfusableInt( word , item.getName() ) );
					
					returnList.add(queryView);
				}
			}
			
		} catch (Exception e) {
			logger.error("DATABASE ERRORS GET ITEM DATA WITH :" + word + " AND profileId" + profileId);
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		} finally {
			folderDao.closeConnection();
			itemDao.closeConnection();
		}
		
		
		return returnList;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Aug 24, 2012
	 * @param folderId
	 * @return
	 * @throws Exception
	 * <p>Description: Mapping the all Items which have same folderId to Folder
	 */
	public boolean mappingToFolder( int folderId ) throws Exception {
		
		logger.debug("DATABASE BEGIN MAPPING ITEM TO FOLDER :" + folderId );
		ItemDAO dao = new ItemDAO();
		try {
			
			double count = dao.getMongoOps().count( Query.query( Criteria.where(Item.FOLDER_ID).is(folderId) ), Item.class );
			
			List<DBRef> listDbRef = null;
			if(count >0 ){
				List<Item> listItem = dao.getMongoOps().find( Query.query( Criteria.where(Item.FOLDER_ID).is(folderId) ), Item.class );
				BmaDataUtils bmaDataUtils = new BmaDataUtils();
				listDbRef = bmaDataUtils.getListDbRef( dao.getMongoTemplate().getDb() , Item.DOCUMENT_NAME , listItem);
			}
			
			
			Update update = new Update();
			update.set( Folder.ITEMS , listDbRef);
			
			logger.debug("DATABASE PROCESS MAPPING ITEMS TO FOLDER :" + listDbRef );
			
			//Call update the folder where items will be mapped
			dao.getMongoOps().updateFirst(Query.query(Criteria.where(Folder.FOLDER_ID).is(folderId)), update, Folder.class);
			
		} catch (Exception e) {
			logger.error("DATABASE ERRORS MAPPING ITEMS TO FOLDER :" + folderId);
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}
		finally {
			dao.closeConnection();
		}
		
		
		logger.debug("DATABASE MAPPING ITEMS TO FOLDER SUCCESS :" + folderId );
		
		return true;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * Create date: Aug 24, 2012
	 * @param name
	 * @param folderId
	 * @return
	 * @throws Exception
	 * <p>Description: Check whether document have the item with name.
	 */
	public boolean isExist( String name , int folderId ) throws Exception {
		boolean returnValue;
		ItemDAO dao = new ItemDAO();
		try {
			long count = dao.getMongoOps().count( Query.query( 
										 Criteria.where(Item.NAME).is(name).and(Item.FOLDER_ID).is(folderId) ), Item.class );
			if( count >  0 )
				returnValue = true;
			else
				returnValue = false;
			
		} catch (Exception e) {
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}finally {
			dao.closeConnection();
		}
		
		return returnValue;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * Create date: Aug 24, 2012
	 * @param name
	 * @return
	 * @throws Exception
	 * <p>Description: Check whether document have the item with name.
	 */
	public Item getItemByName( String name , int folderId ) throws Exception {
		
		logger.debug("DATABASE GET THE ITEM BY NAME :" + name );
		Item item;
		ItemDAO dao = new ItemDAO();
		try {
			
			 item = dao.getMongoOps().findOne( Query.query( Criteria.where(Item.NAME).is(name).and(Item.FOLDER_ID).in(folderId) ), Item.class );
			
		} catch (Exception e) {
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}finally {
			dao.closeConnection();
		}
		
		logger.debug("DATABASE SUCCESS GET THE ITEM BY NAME :" + name );
		
		return item;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: update the new column to the document
	 * @throws Exception
	 */
	public void updateAddNewColumn() throws Exception{
		ItemDAO dao = new ItemDAO();
		try {
			
			Update update = new Update();
			update.set(Item.DESCRIPTION, "");
			update.set(Item.ITEM_STARTWITH, DotBrandToolsConst.ALLOW);
			update.set(Item.ITEM_ENDWITH, DotBrandToolsConst.ALLOW);
			
			dao.getMongoOps().updateMulti(new Query(), update, Item.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.closeConnection();
		}
	}
	
}


