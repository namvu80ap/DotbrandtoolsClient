/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 10, 2012
 */
package com.bmastudio.BMAUtils.Utils;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmastudio.dotbrandtools.dataview.ItemView;

/**
 * 
 * @author Vu Hoai Nam
 * <p>Description: The utilities class handle some specify action on String of dotbrandtools
 */
public class BmaStringUtils {
	private static Logger logger = LoggerFactory.getLogger(BmaDataUtils.class);
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Sep 10, 2012
	 * @param word
	 * @param listStr
	 * @return true = yes, false = no
	 * <p>Description: Check whether the word start with any word in listStr
	 */
	public static boolean checkStartWith( String word, String[] listStr ){
		
		for (String str : listStr) {
			if( word.startsWith(str) ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Sep 10, 2012
	 * @param word
	 * @param listStr
	 * @return
	 * <p>Description: Check whether the word end with any word in listStr
	 */
	public static boolean checkEndWith( String word, String[] listStr ){
		
		for (String str : listStr) {
			if( word.endsWith(str) ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Sep 14, 2012
	 * @param bulkWord
	 * @param limit
	 * @return
	 * @throws Exception when have wrong format line
	 * <p>Description: Split bulkWord into list of Item. Split by line then by "-"
	 */
	public static Set<ItemView> splitIntoItem( String bulkWord, int limit ) throws Exception {
		
		//Split by line
		String[] bulkWordList = bulkWord.split("\\r?\\n");
		if( bulkWordList.length > limit ){
			throw new Exception( bulkWordList[100] + ", longer than 100 line" );
		}
		
		Set<ItemView> set = new HashSet<ItemView>();
		
		
		for (String str : bulkWordList) {
			if( str != null && !str.equals("") ){
				String[] strSplit = str.split(" - ", 2);
				
				String[] checkItem = strSplit[0].split("[^a-zA-Z0-9\\-\\_]+");
				if( checkItem.length == 1 ){
					ItemView itemView = new ItemView();
					itemView.setName( strSplit[0].trim() );
					
					if( strSplit.length == 2 )
						itemView.setDescription( strSplit[1].trim() );
					else
						itemView.setDescription("");
					
					set.add(itemView);
				} else {
					logger.error("CAN NOT SPLIT BULK WORD INTO LIST ITEM :" + ArrayUtils.toString(strSplit)  );
					throw new Exception( ArrayUtils.toString(strSplit) );
				}
				
			}
		}
		
		return set;
	} 
	
	public static void main( String[] args ) throws Exception {
		 Set<ItemView> set = BmaStringUtils.splitIntoItem("cola-cola - cola", 100);
		 for (ItemView itemView : set) {
			 System.out.println( itemView.getName() );
		 }
		 
		
	}
}
