/**
 * This project @copyright by Dotbrandtool
 * Create by user
 */
package com.bmastudio.BMASimilar.Services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmastudio.dotbrandtools.dao.ItemGenerateDAO;
import com.bmastudio.dotbrandtools.data.ItemGenerate;
import com.bmastudio.dotbrandtools.dataview.ItemView;

/**
 * @author Vu Hoai Nam
 * <p>Description:
 */
public class SubItemGenerateThread extends Thread {
	private static Logger logger = LoggerFactory
	.getLogger(ItemGenerateThread.class);
	
	private ItemView item;
	
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
	 * @return the item
	 */
	public ItemView getItem() {
		return item;
	}



	/**
	 * @param item the item to set
	 */
	public void setItem(ItemView item) {
		this.item = item;
	}



	/**
	 * The default generate with listItem be set to class
	 */
	public void run(){
		logger.debug("START GENERATE ITEM TO DATABASE:" + item );
		
		try {
			List<ItemGenerate> list = new ArrayList<ItemGenerate>();
			
			//START
			list.addAll( generateItem(item.getName()) );
			
			ItemGenerateDAO dao = new ItemGenerateDAO();
			
			//Insert ALl list after generated
			dao.insertAll(list);
		
		} catch (Exception e) {
			logger.error("ERRORS: " + e.getMessage() );
		}
		logger.debug("FINISH GENERATE ITEM TO DATABASE:" + item );
	}
	
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Create date: Aug 29, 2012
	 * <p>Generate the item base on the thread property setting ( typos, similar70 or similar85 )
	 * @param item
	 * @return List<ItemGenerate> is the list generated.
	 * @throws Exception
	 */
	private List<ItemGenerate> generateItem( String item ) throws Exception{
		
		ItemGenerateDAO dao = new ItemGenerateDAO();
		List<ItemGenerate> list = new ArrayList<ItemGenerate>() ;
		//GENERATE THE SIMILARITY
		
		if( this.similar70 ){
			List<ItemGenerate> list70 = dao.generateSimilar( item , 70 );
			if( list70 != null) list.addAll( list70 );
		}
		else if( this.similar85 ){
			List<ItemGenerate> list85  = dao.generateSimilar( item , 85 );
			if( list85 != null ) list.addAll( list85  );
		}
		
		if( this.typos ){
			list.addAll( dao.generateTypos(item) );
		}
		
		return list;
	}
	
	
	
}
