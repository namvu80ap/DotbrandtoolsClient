/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Aug 29, 2012
 */
package com.bmastudio.BMASimilar.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bmastudio.dotbrandtools.dao.ItemGenerateDAO;
import com.bmastudio.dotbrandtools.data.ItemGenerate;
import com.bmastudio.dotbrandtools.dataview.ItemView;


/**
 * @author Vu Hoai Nam
 *
 * Create date: Aug 29, 2012
 * <p> Description: This class create a thread run in background of server 
 * to genereate the similar word or typos ( base on the setting )
 * when User register new Item (word) to Profile' folder.
 * <p> Only one thread created for one request to generate many words.
 * <p> Properties including:
 * <li> ListItem is the new Items(word) must generate the Similar/Typos
 * <li> Typos is asking to do generate typos yes or no
 * <li> Similar70 is asking to do generate Similar70 yes or no
 * <li> Similar85 is asking to do generate Similar85 yes or no
 */
public class ItemGenerateThread {
	
	private Set<ItemView> listItem;
	
	private boolean typos;
	
	private boolean similar70;
	
	private boolean similar85;
	
	/**
	 * @return the typos
	 */
	public boolean isTypos() {
		return typos;
	}

	/**
	 * @param typos the typos to set
	 */
	public void setTypos(boolean typos) {
		this.typos = typos;
	}

	/**
	 * @return the similar70
	 */
	public boolean isSimilar70() {
		return similar70;
	}

	/**
	 * @param similar70 the similar70 to set
	 */
	public void setSimilar70(boolean similar70) {
		this.similar70 = similar70;
	}

	/**
	 * @return the similar85
	 */
	public boolean isSimilar85() {
		return similar85;
	}

	/**
	 * @param similar85 the similar85 to set
	 */
	public void setSimilar85(boolean similar85) {
		this.similar85 = similar85;
	}

	/**
	 * @return the listItem
	 */
	public Set<ItemView> getListItem() {
		return listItem;
	}

	/**
	 * @param listItem the listItem to set
	 */
	public void setListItem(Set<ItemView> listItem) {
		this.listItem = listItem;
	}

	private static Logger logger = LoggerFactory
			.getLogger(ItemGenerateThread.class);
	
	/**
	 * The default generate with listItem be set to class
	 */
	public void run(){
		logger.debug("START GENERATE ITEM TO DATABASE:" + listItem );
		
		try {
			if( listItem != null && listItem.size() > 0 ){
				List<ItemGenerate> list = new ArrayList<ItemGenerate>();
				for (ItemView item : listItem) {
					SubItemGenerateThread thread = new SubItemGenerateThread();
					
					thread.setItem(item);
					
					thread.setTypos(typos);
				
					thread.setSimilar70(similar70);
				
					thread.setSimilar85(similar85);
					
					thread.start();
					System.out.println("RUN GENERATE " + item.getName());
				}
				
			}
		} catch (Exception e) {
			logger.error("ERRORS: " + e.getMessage() );
		}
		logger.debug("FINISH GENERATE ITEM TO DATABASE:" + listItem );
	}
	
	
	
}


