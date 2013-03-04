/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Aug 24, 2012
 */
package com.bmastudio.dotbrandtools.data;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * @author Vu Hoai Nam
 *
 */
public class ActionHistory {
	
	@Id
	private String id;
	
	/**
	 * The action of user on the document ( such as: insert, update/modify, delete, query something ... )
	 */
	private String action;
	
	private String username;
	
	/**
	 * The id of any document (such as: Item, Folder, Profile ....)
	 */
	private String documentId;
	
	
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
	 * @param action
	 * @param documentId
	 * @param documentName
	 * @param creUserId
	 * @param creDate
	 * @throws Exception 
	 */
	public ActionHistory(String action, String documentId, String documentName,
			int creUserId, Date creDate) throws Exception {
		super();		
		
		//Validate the documentId
		if( !ObjectId.isValid(documentId) ){
			throw new Exception("DocumentId is invalid");
		}
		
		this.action = action;
		this.documentId = documentId;
		this.documentName = documentName;
		this.creUserId = creUserId;
		
		if(creDate == null) creDate = new Date();	
		this.creDate = creDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActionHistory [action=" + action + ", username=" + username
				+ ", documentId=" + documentId + ", documentName="
				+ documentName + "]";
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
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the documentId
	 */
	public String getDocumentId() {
		return documentId;
	}

	/**
	 * @param documentId the documentId to set
	 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
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

	private String documentName;
	
	private int creUserId;
	
	private Date creDate;
}


