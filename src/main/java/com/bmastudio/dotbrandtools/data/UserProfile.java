/**
 * 
 */
package com.bmastudio.dotbrandtools.data;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Vu Hoai Nam
 *<p>Description: The Data Object mapping with UserProfile
 */
@Document
public class UserProfile {
	
	/**
	 * Collection name is userProfile
	 */
	public static final String DOCUMENT_NAME = "userProfile" ;
	
	/*
	 * DECLARE THE FIELDS NAME OF DOCUMENT OBJECT
	 */
	/**
	 * Document field: id
	 */
	public static final String ID = "id" ;
	/**
	 * Document field: username
	 */
	public static final String USERNAME = "username" ;
	/**
	 * Document field: userId
	 */
	public static final String USER_ID = "userId" ;
	/**
	 * Document field: profile
	 */
	public static final String PROFILE = "profile" ;
	/**
	 * Document field: accessRoles
	 */
	public static final String ACCESS_ROLES = "accessRoles" ;
	/**
	 * Document field: deleteFlg
	 */
	public static final String DELETE_FLG = "deleteFlg" ;
	
	
	
	@Id
	private String id;
	
	private String username;
	
	private Integer userId;
	
	@DBRef
	private com.mongodb.DBRef profile ;
	
	private String[] accessRoles;
	
	private int creUserId;
	
	private Date creDate;
	
	private int updUserId;
	
	private Date updDate;
	
	private boolean deleteFlg;
	

	/**
	 * @return the deleteFlg
	 */
	public boolean isDeleteFlg() {
		return deleteFlg;
	}


	/**
	 * @param deleteFlg the deleteFlg to set
	 */
	public void setDeleteFlg(boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}


	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	/**
	 * @return the profile
	 */
	public com.mongodb.DBRef getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(com.mongodb.DBRef profile) {
		this.profile = profile;
	}

	/**
	 * @return the accessRoles
	 */
	public String[] getAccessRoles() {
		return accessRoles;
	}

	/**
	 * @param accessRoles the accessRoles to set
	 */
	public void setAccessRoles(String[] accessRoles) {
		this.accessRoles = accessRoles;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	

	/**
	 * @return the creUserId
	 */
	public int getCreUserId() {
		return creUserId;
	}

	/**
	 * @param creUserId the creUserId to set
	 */
	public void setCreUserId(int creUserId) {
		this.creUserId = creUserId;
	}

	/**
	 * @return the creDate
	 */
	public Date getCreDate() {
		return creDate;
	}

	/**
	 * @param creDate the creDate to set
	 */
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	/**
	 * @return the updUserId
	 */
	public int getUpdUserId() {
		return updUserId;
	}

	/**
	 * @param updUserId the updUserId to set
	 */
	public void setUpdUserId(int updUserId) {
		this.updUserId = updUserId;
	}

	/**
	 * @return the updDate
	 */
	public Date getUpdDate() {
		return updDate;
	}

	/**
	 * @param updDate the updDate to set
	 */
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserProfile [username=" + username + ", profile=" + profile
				+"accessRols" + accessRoles + ", updUserId=" + updUserId + ", updDate=" + updDate + "]";
	}

	/**
	 * @param username
	 * @param profiles
	 * @param creUserId
	 * @param creDate
	 * @param updUserId
	 * @param updDate
	 */
	public UserProfile(String username, int userId, com.mongodb.DBRef profile, String[] accessRoles , int creUserId,
			Date creDate, int updUserId, Date updDate) {
		super();
		this.username = username;
		this.userId = userId;
		this.profile = profile;
		this.accessRoles = accessRoles;
		this.creUserId = creUserId;
		this.creDate = creDate;
		this.updUserId = updUserId;
		this.updDate = updDate;
	}
	
	public UserProfile(){
		super();
	}
	
}
