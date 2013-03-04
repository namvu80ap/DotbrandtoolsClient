/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 3, 2012
 */
package com.bmastudio.dotbrandtools.dataview;


import com.bmastudio.dotbrandtools.data.Profile;

/**
 * @author Vu Hoai Nam
 *
 * Create date: Sep 3, 2012
 * <p>Description: The view object of Profile
 */
public class ProfileView extends Profile {
	
	private boolean hasRoleRead = false;
	private boolean hasRoleWrite = false;
	private boolean hasRoleDelete = false;
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


