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
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bmastudio.dotbrandtools.dao.ProfileDAO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * @author Vu Hoai Nam
 *<p>Description: The Data Object mapping with Profile
 */
@Document
public class Profile extends DataObject implements Comparable<Profile> {
	
	/**
	 * Collection name profile
	 */
	public static final String DOCUMENT_NAME = "profile" ;
	
	/*
	 * DECLARE THE FIELDS NAME OF DOCUMENT OBJECT
	 */
	/**
	 * Document field: profileId
	 */
	public static final String PROFILE_ID = "profileId" ;
	/**
	 * Document field: name
	 */
	public static final String NAME = "name" ;
	/**
	 * Document field: description
	 */
	public static final String DESCRIPTION = "description" ;
	/**
	 * Document field: imageUrl
	 */
	public static final String IMAGE_URL = "imageUrl";
	/**
	 * Document field: folders
	 */
	public static final String FOLDERS = "folders" ;
	/**
	 * Document field: updUserId
	 */
	public static final String UPD_USERID = "updUserId" ;
	/**
	 * Document field: updDate
	 */
	public static final String UPD_DATE = "updDate" ;
	/**
	 * Document field: deleteFlg
	 */
	public static final String DELETE_FLG = "deleteFlg" ;
	
	
	@Id
	private String id;
	
	@Indexed
	private int profileId;
	
	private String name;
	
	private String userId;
	
	private String description;
	
	private String imageUrl;

	private int creUserId;
	
	private Date creDate;
	
	private int updUserId;
	
	private Date updDate;
	
	@DBRef
	private List<com.mongodb.DBRef> folders;
	
	private List<Folder> listFolders;
	
	private boolean deleteFlg;

	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the listFolders
	 */
	public List<Folder> getListFolders() {
		
		List<Folder> list = new ArrayList<Folder>();
		ProfileDAO profileDao = null;
		MongoConverter converter = null;
		try {
			profileDao = new ProfileDAO();
			converter = profileDao.getMongoTemplate().getConverter();
			DBCollection coll = profileDao.getMongoTemplate().getDb().getCollection( Folder.DOCUMENT_NAME );
			
			//populate folders Ref to object
			if( folders != null ){
				for (com.mongodb.DBRef folderRef : folders) {
					DBObject dbobject = coll.findOne( new BasicDBObject("_id", folderRef.getId() ) );
					Folder folder =  converter.read( Folder.class , dbobject );
					if( !folder.isDeleteFlg() )
						list.add( folder );
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			profileDao.closeConnection();
		}
		
		listFolders = list;
		return listFolders;
	}

	/**
	 * @param listFolders the listFolders to set
	 */
	public void setListFolders(List<Folder> listFolders) {
		this.listFolders = listFolders;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Profile [profileId=" + profileId + ", name=" + name + ", creUserId="
				+ creUserId + "]";
	}
	
	@PersistenceConstructor
	public Profile(int profileId, String name, String description, String imageUrl,
			int creUserId, Date creDate, int updUserId, Date updDate) {
		super();
		this.profileId = profileId;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.creUserId = creUserId;
		this.creDate = creDate;
		this.updUserId = updUserId;
		this.updDate = updDate;
	}
	
	
	public Profile() {
		super();
	}
	
	
	

	/**
	 * @return the folders
	 */
	public List<com.mongodb.DBRef> getFolders() {
		return folders;
	}

	/**
	 * @param folders the folders to set
	 */
	public void setFolders(List<com.mongodb.DBRef> folders) {
		this.folders = folders;
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

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Profile obj ) {
		//Sort by name alphabet
		return getName().toString().compareTo( obj.getName().toString());
	}

}
