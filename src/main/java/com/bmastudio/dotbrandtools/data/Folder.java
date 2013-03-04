/**
 * This project @copyright by Lucas Valls
 * Create by user
 * Create date: Aug 23, 2012
 */
package com.bmastudio.dotbrandtools.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.bmastudio.BMAUtils.Utils.BmaDataUtils;
import com.bmastudio.dotbrandtools.dao.CommonDAO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * @author Vu Hoai Nam
 *<p>Description: The Data Object mapping with Folder
 */
@Document
public class Folder extends DataObject {
	
	/**
	 * Collection name folder
	 */
	public static final String DOCUMENT_NAME = "folder" ;
	
	/*
	 * DECLARE THE FIELDS NAME OF DOCUMENT OBJECT
	 */
	/**
	 * Document field: folderId
	 */
	public static final String FOLDER_ID = "folderId" ;
	/**
	 * Document field: name
	 */
	public static final String NAME = "name" ;
	/**
	 * Document field: profileId
	 */
	public static final String PROFILE_ID = "profileId" ;
	/**
	 * Document field: description
	 */
	public static final String DESCRIPTION = "description" ;
	/**
	 * Document field: rules
	 */
	public static final String RULES = "rules";
	/**
	 * Document field: items
	 */
	public static final String ITEMS = "items" ;
	/**
	 * Document field: updDate
	 */
	public static final String UPD_DATE = "updDate" ;
	/**
	 * Document field: updUserId
	 */
	public static final String UPD_USERID = "updUserId" ;
	/**
	 * Document field: deleteFlg
	 */
	public static final String DELETE_FLAG = "deleteFlg";
	
	@Id
	private String id;
	
	@Indexed
	private int folderId;
	
	private int profileId;
	
	private String name;
	
	private String description;
	
	private String rules;
	
	private int creUserId;
	
	private Date creDate;
	
	private int updUserId;
	
	private Date updDate;
	
	@DBRef
	private List<com.mongodb.DBRef> items;
	
	private List<Item> listItems;
	
	private boolean deleteFlg;
	
	/**
	 * @return the listItems
	 */
	public List<Item> getListItems() {
		List<Item> list = new ArrayList<Item>();
		CommonDAO dao = null;
		MongoConverter converter = null;
		try {
			dao = new CommonDAO();
			DBCollection coll = dao.getMongoTemplate().getDb().getCollection( Item.DOCUMENT_NAME );
			
			converter = dao.getMongoTemplate().getConverter();
			if( items != null ){
				for (com.mongodb.DBRef itemRef : items) {
					DBObject dbobject = coll.findOne( new BasicDBObject("_id", itemRef.getId() ) );
					Item item = converter.read( Item.class , dbobject );
					if( BmaDataUtils.checkItemAllowALL(item) )
						list.add( item );
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dao.closeConnection();
		}
		
		listItems = list;
		return listItems;
	}

	/**
	 * @param listItems the listItems to set
	 */
	public void setListItems(List<Item> listItems) {
		this.listItems = listItems;
	}

	/**
	 * @return the rules
	 */
	public String getRules() {
		return rules;
	}

	/**
	 * @param rules the rules to set
	 */
	public void setRules(String rules) {
		this.rules = rules;
	}

	/**
	 * @return the items
	 */
	public List<com.mongodb.DBRef> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<com.mongodb.DBRef> items) {
		this.items = items;
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
	 * @param profileId
	 * @param name
	 * @param description
	 * @param creUserId
	 * @param creDate
	 * @param updUserId
	 * @param updDate
	 */
	public Folder(int profileId, String name, String description, String rules,
			int creUserId, Date creDate, int updUserId, Date updDate) {
		super();
		this.profileId = profileId;
		this.name = name;
		this.description = description;
		this.rules = rules;
		this.creUserId = creUserId;
		this.creDate = creDate;
		this.updUserId = updUserId;
		this.updDate = updDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Folder [folderId=" + folderId + ", name=" + name
				+ ", updUserId=" + updUserId + ", updDate=" + updDate + "]";
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

	/**
	 * @return the profileId
	 */
	public int getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	
	
}
