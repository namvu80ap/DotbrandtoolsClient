/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 10, 2012
 */
package com.bmastudio.BMAUtils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author Vu Hoai Nam
 * <p>Description: The utilities class handle some specify action on data list of dotbrandtools
 */
public class BmaSimilarList {
	/**
	 * Divide the HashMap into many map
	 * @param map
	 * @return
	 */
	public static Map<String, List<String>> divideList( HashMap<String, Integer> map , int number ){
		Map<Integer, String> orderMap = new TreeMap<Integer, String>();
		for (Entry<String, Integer> entry : map.entrySet()) {
			orderMap.put(entry.getValue(), entry.getKey());
		}
		
		return null;
	}
	
	/**
	 * Sorting the HashMap
	 * @param passedMap
	 * @return
	 */
	public static LinkedHashMap<String, Integer> sortHashMapByValues( Map<String, Integer> passedMap) {
		   List mapKeys = new ArrayList(passedMap.keySet());
		   List mapValues = new ArrayList(passedMap.values());
		   Collections.sort(mapValues);
		   Collections.sort(mapKeys);
		   Collections.reverse(mapValues);
		   Collections.reverse(mapKeys);
		   

		   LinkedHashMap sortedMap =
		       new LinkedHashMap();

		   Iterator<String> valueIt = mapValues.iterator();
		   while (valueIt.hasNext()) {
		       Object val = valueIt.next();
		    Iterator keyIt = mapKeys.iterator();

		    while (keyIt.hasNext()) {
		        Object key = keyIt.next();
		        String comp1 = passedMap.get(key).toString();
		        String comp2 = val.toString();

		        if (comp1.equals(comp2)){
		            passedMap.remove(key);
		            mapKeys.remove(key);
		            sortedMap.put((String)key, (Integer)val);
		            break;
		        }

		    }

		}
		return sortedMap;
		}

}
