/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Aug 23, 2012
 */
package com.bmastudio.BMAUtils.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.bmastudio.dotbrandtools.data.DataObject;
import com.bmastudio.dotbrandtools.data.Item;
import com.mongodb.DB;
import com.mongodb.DBRef;

/**
 * @author Vu Hoai Nam
 * <p>Description: The utilities class handle some specify action on data of dotbrandtools
 */
public class BmaDataUtils {
	
	/**
	 * Create by user
	 * Create date: Aug 23, 2012
	 * @param db
	 * @param objectName : The name of data object passed
	 * @param list
	 * @return
	 * <p>Description: get list of object referent from list of documents
	 */
	public List<DBRef> getListDbRef( DB db, String objectName , List<? extends DataObject > list ){
		
		List<DBRef> returnList = new ArrayList<DBRef>();
		
		for (DataObject dataObject : list) {
			DBRef dbRef = new DBRef( db, objectName , new ObjectId( dataObject.getId() ) );
			returnList.add(dbRef);
		}
		
		return returnList;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Aug 24, 2012
	 * @return
	 * <p>Description: check whether the string is integer
	 */
	public static boolean isInt( String str ){
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Aug 29, 2012
	 * @return
	 * <p>Description: Checking whether the Item is get
	 */
	public static boolean checkItemAllow( Item item, String type ){
		
		if( DotBrandToolsConst.SIMILAR70.equals(type) && item.getSimilar70().equals(DotBrandToolsConst.ALLOW) ) 
			return true;
		else if ( DotBrandToolsConst.SIMILAR85.equals(type) && item.getSimilar85().equals(DotBrandToolsConst.ALLOW) )
			return true;
		else if ( DotBrandToolsConst.TYPOS.equals(type) && item.getTypos().equals(DotBrandToolsConst.ALLOW) )
			return true;
		else if ( DotBrandToolsConst.IDENTICAL.equals(type) && item.getIdentical().equals(DotBrandToolsConst.ALLOW) )
			return true;
		else if ( DotBrandToolsConst.WILDCARD.equals(type) && item.getWildcard().equals(DotBrandToolsConst.ALLOW) )
			return true;
		
		return false;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Aug 29, 2012
	 * @return
	 * <p>Description: Checking whether the Item allow all similar case
	 */
	public static boolean checkItemAllowALL( Item item){
		
		if( item.getSimilar70().equals(DotBrandToolsConst.ALLOW) 
				&& item.getSimilar85().equals(DotBrandToolsConst.ALLOW)
				&& item.getTypos().equals(DotBrandToolsConst.ALLOW) 
				&& item.getIdentical().equals(DotBrandToolsConst.ALLOW) 
				&& item.getWildcard().equals(DotBrandToolsConst.ALLOW) 
				&& item.getStartWith().equals(DotBrandToolsConst.ALLOW) 
				&& item.getEndWith().equals(DotBrandToolsConst.ALLOW) )
			return false;
		
		return true;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * Create date: Aug 30, 2012
	 * @param word
	 * @param str
	 * @return
	 * <p>Description: Checking whether the str is the wildcard of word
	 */
	public static boolean isWildCard( String word , String str ){
		
		if( word != null && str != null && !str.equals(word)){
			return ( str.indexOf(word) != -1) ? true: false ;
		}
		return false;
	}
	

}

