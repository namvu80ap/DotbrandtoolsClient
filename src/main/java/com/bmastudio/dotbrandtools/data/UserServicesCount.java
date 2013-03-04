/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 19, 2012
 */
package com.bmastudio.dotbrandtools.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Vu Hoai Nam
 *
 * Create date: Sep 19, 2012
 * <p>Description: The Data Object mapping to UserServicesCount 
 */
@Document
public class UserServicesCount {
	
	/**
	 * Collection name is userServicesCount
	 */
	public static final String DOCUMENT_NAME = "userServicesCount" ;
	
	/*
	 * DECLARE THE FIELDS NAME OF DOCUMENT OBJECT
	 */
	/**
	 * Document field: userId
	 */
	public static final String USER_ID = "userId" ;
	/**
	 * Document field: serviceName
	 */
	public static final String SERVICE_NAME = "serviceName" ;
	/**
	 * Document field: count
	 */
	public static final String COUNT = "count" ;
	/**
	 * Document field: successCount
	 */
	public static final String SUCCESS_COUNT = "successCount" ;
	/**
	 * Document field: failureCount
	 */
	public static final String FAILURE_COUNT = "failureCount" ;
	
	
	@Id
	private String id;
	
	private int userId;
	
	private String serviceName;
	
	private double count;
	
	private double successCount;
	
	private double failureCount;

	
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
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the count
	 */
	public double getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(double count) {
		this.count = count;
	}

	/**
	 * @return the successCount
	 */
	public double getSuccessCount() {
		return successCount;
	}

	/**
	 * @param successCount the successCount to set
	 */
	public void setSuccessCount(double successCount) {
		this.successCount = successCount;
	}

	/**
	 * @return the failureCount
	 */
	public double getFailureCount() {
		return failureCount;
	}

	/**
	 * @param failureCount the failureCount to set
	 */
	public void setFailureCount(double failureCount) {
		this.failureCount = failureCount;
	}
	
	
}
