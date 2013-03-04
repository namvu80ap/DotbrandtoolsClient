/**
 * This project @copyright by Lucas Vall
 * Create by Vu Hoai Nam
 * Create date: Aug 24, 2012
 */
package com.bmastudio.dotbrandtools.dao;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.bmastudio.BMAUtils.Utils.BmaDataUtils;
import com.bmastudio.dotbrandtools.data.Folder;
import com.bmastudio.dotbrandtools.data.Profile;
import com.mongodb.DBRef;

/**
 * @author Vu Hoai Nam
 * <p>Description: The Data Access Object handle the data on Folder document.
 */
public class FolderDAO extends CommonDAO{

	private static Logger logger = LoggerFactory.getLogger(FolderDAO.class);
	
	/**
	 * @construction
	 */
	public FolderDAO() throws Exception {
		super();
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Create date: Aug 24, 2012
	 * @param folder
	 * @return
	 * @throws Exception
	 * <p>Description: insert a folder to database
	 * <p>If the folder include some Items which did not save, this function will save the Items too.
	 */
	public Folder createFolder( Folder folder ) throws Exception {
		
		logger.debug("DATABASE ACCESS BEGIN INSERT :" + folder.toString());
		
		FolderDAO dao = new FolderDAO();
		
		try{
			
			//Get last folder with max id
			Query query = new Query();
			query.sort().on( Folder.FOLDER_ID , Order.DESCENDING);
			query.limit(1);
			Folder lastFolder = dao.getMongoOps().findOne( query ,Folder.class);
			
			if( lastFolder != null && lastFolder.getFolderId() > 0 )
				folder.setFolderId( lastFolder.getFolderId() + 1 );
			else
				folder.setFolderId( 1 );
			
			
			//Insert the Items mapping to folder
			if( folder.getItems() != null ){
				logger.debug("DATABASE PROCCESSING INSERT LIST FOLDER'S ITEMS :" + folder.getItems().toString() );
				dao.getMongoOps().insert( folder.getItems() , Folder.class );
			}
			
			dao.getMongoOps().insert(folder);
			//THEN Mapping the new folder to profile
			mappingToProfile(folder.getProfileId() );
			logger.debug("DATABASE INSERT SUCCESS :" + folder.toString());
		}
		catch (Exception e) {
			logger.error("DATABASE INSERT ERROR : " + folder.toString());
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}
		finally {
			dao.closeConnection();
		}
		
		return folder;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Create date: Aug 28, 2012
	 * @param folder
	 * @return
	 * @throws Exception
	 * <p>Description: Update a Folder
	 */
	public Folder updateFolder( Folder folder ) throws Exception {
		
		logger.debug("DATABASE ACCESS BEGIN UPDATE :" + folder.toString());
		
		FolderDAO dao = new FolderDAO();
		
		try{
			
			if( folder.getId() != null ){
				logger.debug("DATABASE PROCCESSING UDPATE :" +  folder.toString() );
				Update update = new Update();
				update.set(Folder.NAME, folder.getName());
				update.set(Folder.DESCRIPTION, folder.getDescription());
				update.set(Folder.RULES, folder.getRules() );
				update.set(Folder.UPD_DATE, new Date() );
				//TODO HARD CODE UPDATE USER 
				update.set(Folder.UPD_USERID, 1);
				
				dao.getMongoOps().updateFirst(Query.query(Criteria.where(Folder.FOLDER_ID).is( folder.getFolderId()) ),
									 update, Folder.class);
				
			}
			
			logger.debug("DATABASE UPDATE SUCCESS :" + folder.toString());
		}
		catch (Exception e) {
			logger.error("DATABASE UPDATE ERROR : " + folder.toString());
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}
		finally {
			dao.closeConnection();
		}
		
		return folder;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Create date: Aug 28, 2012
	 * @param folderId
	 * @return
	 * @throws Exception
	 * <p>Description: Instead delete a Folder by folderId, we keep the data then just update the deleteFlag
	 */
	public boolean deleteFolder( int folderId ) throws Exception {
		
		logger.debug("DATABASE ACCESS BEGIN DELETE FOLDER :" + folderId );
		
		FolderDAO dao = new FolderDAO();
		try{
			
			if( folderId != 0 ){
					Update update = new Update();
					update.set(Folder.DELETE_FLAG, true );
					//remove the folder document
					//getMongoOps().remove(folder);
					dao.getMongoOps().updateFirst( Query.query(Criteria.where(Folder.FOLDER_ID).is(folderId)), update, Folder.class);
					//Remove the reference of Folder to Profile
					
					logger.debug("DATABASE REMOVE THE REFERENCE OF FOLDER TO PROFILE : " + folderId );					
				
			}
			
			logger.debug("DATABASE DELETE FOLDER SUCCESS :" + folderId);
		}
		catch (Exception e) {
			logger.error("DATABASE DELETE FOLDER ERROR : " + folderId);
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}
		finally {
			dao.closeConnection();
		}
		
		return true;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Create date: Aug 24, 2012
	 * @param profileId
	 * @return
	 * @throws Exception
	 * <p>Description: Mapping a all the folders have same referent to profileId to Profile
	 */
	public boolean mappingToProfile( int profileId ) throws Exception {
		
		logger.debug("DATABASE BEGIN MAPPING FOLDER TO PROFILE :" + profileId );
		
		FolderDAO dao = new FolderDAO();
		
		try {
			
			List<Folder> listFolder = dao.getMongoOps().find( Query.query( Criteria.where(Folder.PROFILE_ID).is(profileId) ), Folder.class );
			
			
			BmaDataUtils bmaDataUtils = new BmaDataUtils();
			List<DBRef> listDbRef = bmaDataUtils.getListDbRef( dao.getMongoTemplate().getDb() , Folder.DOCUMENT_NAME , listFolder);
			
			Update update = new Update();
			update.set( Profile.FOLDERS , listDbRef);
			
			logger.debug("DATABASE PROCESS MAPPING FOLDER TO PROFILE :" + listDbRef.toString() );
			
			//Call update the profile where folders will mapped
			dao.getMongoOps().updateFirst(Query.query(Criteria.where(Profile.PROFILE_ID).is(profileId)), update, Profile.class);
			
		} catch (Exception e) {
			logger.error("DATABASE MAPPING FOLDER TO PROFILE SUCCESS :" + profileId);
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}
		finally {
			dao.closeConnection();
		}
		logger.debug("DATABASE MAPPING FOLDER TO PROFILE SUCCESS :" + profileId );
		
		return true;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Create date: Aug 24, 2012
	 * @param folderId
	 * @return
	 * @throws Exception 
	 * <p>Description: Get the folder by folder id ( folderId is not the document id )
	 */
	public Folder getFolderByFolderId( int folderId ) throws Exception {
		//TODO WRITE LOGG
		FolderDAO dao = new FolderDAO();
		
		Folder folder = dao.getMongoOps().findOne(Query.query( Criteria.where(Folder.FOLDER_ID).is(folderId).and(Folder.DELETE_FLAG).is(false) )
													, Folder.class);
		
		dao.closeConnection();
		
		return folder;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Create date: Sep 10, 2012
	 * @param profileId
	 * @return
	 * @throws Exception
	 * <p>Description: List of Folder base on ProfileId 
	 */
	public List<Folder> getFolderByProfileId( int profileId ) throws Exception {
		//TODO WRITE LOGG
		FolderDAO dao = new FolderDAO();
		List<Folder> listFolder = dao.getMongoOps().find(Query.query( Criteria.where(Folder.PROFILE_ID).is(profileId).and(Folder.DELETE_FLAG).in(false) ), 
															Folder.class);
		dao.closeConnection();
		return listFolder;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Create date: Sep 5, 2012
	 * @return
	 * @throws Exception 
	 * <p>Description: Clean un mapping items
	 */
	public boolean cleanItemsMapping() throws Exception{
		FolderDAO dao = new FolderDAO();
		List<Folder> folders = dao.getMongoOps().findAll(Folder.class);
		ItemDAO itemDao = new ItemDAO();
		for (Folder folder : folders) {
			itemDao.mappingToFolder(folder.getFolderId());
		}
		
		itemDao.closeConnection();
		
		return true;
	}
	
}


