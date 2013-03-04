/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Aug 31, 2012
 */
package com.bmastudio.dotbrandtools.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vu Hoai Nam
 * Description: Record the Action History of User on profile, folder ... 
 * Create date: Aug 31, 2012
 * 
 */
public class ActionHistoryDAO {

	private static Logger logger = LoggerFactory
			.getLogger(ActionHistoryDAO.class);
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * Create date: Aug 31, 2012
	 * @param folder
	 * @return
	 * @throws Exception
	 * <p>Description: Insert action history 
	 */
	/*
	public ActionHistory createActionHistory( ActionHistory history ) throws Exception {
		
		logger.debug("DATABASE ACCESS BEGIN INSERT :" + ActionHistory.toString());
		
		try{
			
			//Get last folder with max id
			Query query = new Query();
			query.sort().on( Folder.FOLDER_ID , Order.DESCENDING);
			query.limit(1);
			Folder lastFolder = mongoOps.findOne( query ,Folder.class);
			
			if( lastFolder != null && lastFolder.getFolderId() > 0 )
				folder.setFolderId( lastFolder.getFolderId() + 1 );
			else
				folder.setFolderId( 1 );
			
			
			//Insert the Items mapping to folder
			if( folder.getItems() != null ){
				logger.debug("DATABASE PROCCESSING INSERT LIST FOLDER'S ITEMS :" + folder.getItems().toString() );
				mongoOps.insert( folder.getItems() , Folder.class );
			}
			
			mongoOps.insert(folder);
			logger.debug("DATABASE INSERT SUCCESS :" + folder.toString());
		}
		catch (Exception e) {
			logger.error("DATABASE INSERT ERROR : " + folder.toString());
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}
		
		return folder;
	}
	*/

}


