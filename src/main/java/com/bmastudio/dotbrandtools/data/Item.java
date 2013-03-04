/**
 * This project @copyright by Lucas Valls
 * Create by user
 * Create date: Aug 23, 2012
 */
package com.bmastudio.dotbrandtools.data;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Vu Hoai Nam
 *<p>Description: The Data Object mapping with Item
 */
@Document
public class Item extends DataObject {
	
	/**
	 * Collection name item
	 */
	public static final String DOCUMENT_NAME = "item" ;
	
	/*
	 * DECLARE THE FIELDS NAME OF DOCUMENT OBJECT
	 */
	/**
	 * Document field: itemId
	 */
	public static final String ITEM_ID = "itemId" ;
	/**
	 * Document field: name
	 */
	public static final String NAME = "name" ;
	/**
	 * Document field: description
	 */
	public static final String DESCRIPTION = "description" ;
	/**
	 * Document field: folderId
	 */
	public static final String FOLDER_ID = "folderId" ;
	/**
	 * Document field: folder
	 */
	public static final String FOLDER = "folder" ;
	/**
	 * Document field: identical
	 */
	public static final String ITEM_IDENTICAL = "identical" ;
	/**
	 * Document field: typos
	 */
	public static final String ITEM_TYPOS = "typos" ;
	/**
	 * Document field: wildcard
	 */
	public static final String ITEM_WILDCARD = "wildcard" ;
	/**
	 * Document field: startWith
	 */
	public static final String ITEM_STARTWITH = "startWith" ;
	/**
	 * Document field: endWith
	 */
	public static final String ITEM_ENDWITH = "endWith" ;
	/**
	 * Document field: similar70
	 */
	public static final String ITEM_SIMILAR70 = "similar70" ;
	/**
	 * Document field: similar85
	 */
	public static final String ITEM_SIMILAR85 = "similar85" ;
	
	@Id
	private String id;
	
	@DBRef
	private com.mongodb.DBRef folder;

	private int folderId;
	
	private String name;
	
	private String description;
	
	private String identical;
	
	private String wildcard;
	
	private String startWith;
	
	private String endWith;
	
	private String typos;
	
	private String similar70;
	
	private String similar85;

	private int creUserId;
	
	private Date creDate;
	
	private int updUserId;
	
	private Date updDate;
	
	private boolean deleteFlg;

	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startWith
	 */
	public String getStartWith() {
		return startWith;
	}

	/**
	 * @param startWith the startWith to set
	 */
	public void setStartWith(String startWith) {
		this.startWith = startWith;
	}

	/**
	 * @return the endWith
	 */
	public String getEndWith() {
		return endWith;
	}

	/**
	 * @param endWith the endWith to set
	 */
	public void setEndWith(String endWith) {
		this.endWith = endWith;
	}

	/**
	 * @return the identical
	 */
	public String getIdentical() {
		return identical;
	}

	/**
	 * @param identical the identical to set
	 */
	public void setIdentical(String identical) {
		this.identical = identical;
	}

	/**
	 * @return the wildcard
	 */
	public String getWildcard() {
		return wildcard;
	}

	/**
	 * @param wildcard the wildcard to set
	 */
	public void setWildcard(String wildcard) {
		this.wildcard = wildcard;
	}

	/**
	 * @return the typos
	 */
	public String getTypos() {
		return typos;
	}

	/**
	 * @param typos the typos to set
	 */
	public void setTypos(String typos) {
		this.typos = typos;
	}

	/**
	 * @return the similar70
	 */
	public String getSimilar70() {
		return similar70;
	}

	/**
	 * @param similar70 the similar70 to set
	 */
	public void setSimilar70(String similar70) {
		this.similar70 = similar70;
	}

	/**
	 * @return the similar85
	 */
	public String getSimilar85() {
		return similar85;
	}

	/**
	 * @param similar85 the similar85 to set
	 */
	public void setSimilar85(String similar85) {
		this.similar85 = similar85;
	}
	
	/**
	 * @return the folder
	 */
	public com.mongodb.DBRef getFolder() {
		return folder;
	}

	/**
	 * @param folder the folder to set
	 */
	public void setFolder(com.mongodb.DBRef folder) {
		this.folder = folder;
	}
	
	/**
	 * @return the folderId
	 */
	public int getFolderId() {
		return folderId;
	}

	/**
	 * @param folderId the folderId to set
	 */
	public void setFolderId(int folderId) {
		this.folderId = folderId;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

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
		return "Item [folder=" + folder + ", folderId=" + folderId + ", name="
				+ name + ", description=" + description + ", identical="
				+ identical + ", wildcard=" + wildcard + ", startWith="
				+ startWith + ", endWith=" + endWith + ", typos=" + typos
				+ ", similar70=" + similar70 + ", similar85=" + similar85
				+ ", deleteFlg=" + deleteFlg + "]";
	}

	/**
	 * @param folder
	 * @param folderId
	 * @param name
	 * @param identical
	 * @param wildcard
	 * @param typos
	 * @param similar70
	 * @param similar85
	 * @param creUserId
	 * @param creDate
	 * @param updUserId
	 * @param updDate
	 */
	public Item(com.mongodb.DBRef folder, int folderId, String name, String description,
			String identical, String wildcard, String startWith, String endWith,
			String typos, String similar70,	String similar85, int creUserId, Date creDate, int updUserId,
			Date updDate) {
		super();
		this.folder = folder;
		this.folderId = folderId;
		this.name = name;
		this.description = description;
		this.identical = identical;
		this.wildcard = wildcard;
		this.startWith = startWith;
		this.endWith = endWith;
		this.typos = typos;
		this.similar70 = similar70;
		this.similar85 = similar85;
		this.creUserId = creUserId;
		this.creDate = creDate;
		this.updUserId = updUserId;
		this.updDate = updDate;
	}
	
	public Item() {
		super();
	}
}
