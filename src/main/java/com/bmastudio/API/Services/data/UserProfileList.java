/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 20, 2012
 */
package com.bmastudio.API.Services.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vu Hoai Nam
 *
 * Create date: Sep 20, 2012
 * <p>Description: List XML Object View use for UserProfileService
 */
@XmlRootElement(name="UserProfileList")
public class UserProfileList {
	
	private List<UserProfile> userProfiles;

	/**
	 * Get list of UserProfile
	 * @see UserProfile
	 * @return the userProfiles
	 */
	public List<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	/**
	 * @param userProfiles the userProfiles to set
	 */
	public void setUserProfiles(List<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}
	
}
