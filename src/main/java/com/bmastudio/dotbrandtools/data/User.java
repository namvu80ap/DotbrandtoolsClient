/**
 * Copyright (C) 2012 BMA Group, Inc.
 * 
 * This file is part of the DotBrandTools project, hosted at
 * http://DotBrandTools.com/
 * 
 * DotBrandTools is software: .......
 *
 * You should have received a copy of the BMA License
 *  If not, please contact a http://DotBrandTools.com/.
 */
package com.bmastudio.dotbrandtools.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Vu Hoai Nam
 * <p>Description: The Data Object mapping with User
 */
@Document
public class User {
	
	/**
	 * Collection name is user
	 */
	public static final String DOCUMENT_NAME = "user" ;
	
	/*
	 * DECLARE THE FIELDS NAME OF DOCUMENT OBJECT
	 */
	/**
	 * Document field: userId
	 */
	public static final String USER_ID = "userId" ;
	/**
	 * Document field: username
	 */
	public static final String USERNAME = "username" ;
	/**
	 * Document field: password
	 */
	public static final String PASSWORD = "password" ;
	/**
	 * Document field: roles
	 */
	public static final String ROLES = "roles" ;
	
	@Id
	private String id;
	
	@Indexed
	private int userId;
	
	@Indexed(unique = true )
	private String username;
	
	private String password;
	
	private String[] roles;
	
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the roles
	 */
	public String[] getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String[] roles) {
		this.roles = roles;
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
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @param userId
	 * @param username
	 * @param password
	 * @param roles
	 */
	public User(int userId, String username, String password, String[] roles) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	/**
	 * 
	 */
	public User() {
		super();
	}
}
