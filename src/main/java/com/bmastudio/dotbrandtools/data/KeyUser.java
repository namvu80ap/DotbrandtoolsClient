/**
 * This project @copyright by Lucas Valls
 * Create by user
 * Create date: Aug 23, 2012
 */
package com.bmastudio.dotbrandtools.data;

import java.util.ArrayList;

/**
 * 
 * @author Vu Hoai Nam
 * <p>Description: POJO object user for collection KeyUser
 */
public class KeyUser {
	private String Key;
	private String UserAccess;
	
	private ArrayList<Keyword> userKeywords ;
	
	public String getUserAccess() {
		return UserAccess;
	}
	public void setUserAccess(String userAccess) {
		UserAccess = userAccess;
	}
	public ArrayList<Keyword> getUserKeywords() {
		return userKeywords;
	}
	public void setUserKeywords(ArrayList<Keyword> userKeywords) {
		this.userKeywords = userKeywords;
	}
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}
	
	
}
