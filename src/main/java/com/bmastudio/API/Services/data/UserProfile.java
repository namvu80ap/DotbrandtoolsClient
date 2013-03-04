/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 20, 2012
 */
package com.bmastudio.API.Services.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vu Hoai Nam
 *
 * Create date: Sep 20, 2012
 * <p>Description: XML Object View use for UserProfileService
 * <p>Properties are:
 * <li>profileId is the words profile of user authenticated
 * <li>name is the name of words profile
 * <li>description about the profile  
 * <li>hasRoleRead is whether the user have Reading Role in the system.
 * <li>hasRoleWrite is whether the user have Write Role in the system.
 * <li>hasRoleDelete is whether the user have Delete Role in the system.
 */
@XmlRootElement(name="UserProfile")
public class UserProfile {

	private int profileId;
	
	private String name;
	
	private String description;
	
	private boolean hasRoleRead = false;
	
	private boolean hasRoleWrite = false;
	
	private boolean hasRoleDelete = false;

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
	 * @return the hasRoleRead
	 */
	public boolean isHasRoleRead() {
		return hasRoleRead;
	}

	/**
	 * @param hasRoleRead the hasRoleRead to set
	 */
	public void setHasRoleRead(boolean hasRoleRead) {
		this.hasRoleRead = hasRoleRead;
	}

	/**
	 * @return the hasRoleWrite
	 */
	public boolean isHasRoleWrite() {
		return hasRoleWrite;
	}

	/**
	 * @param hasRoleWrite the hasRoleWrite to set
	 */
	public void setHasRoleWrite(boolean hasRoleWrite) {
		this.hasRoleWrite = hasRoleWrite;
	}

	/**
	 * @return the hasRoleDelete
	 */
	public boolean isHasRoleDelete() {
		return hasRoleDelete;
	}

	/**
	 * @param hasRoleDelete the hasRoleDelete to set
	 */
	public void setHasRoleDelete(boolean hasRoleDelete) {
		this.hasRoleDelete = hasRoleDelete;
	}
	
	
}
