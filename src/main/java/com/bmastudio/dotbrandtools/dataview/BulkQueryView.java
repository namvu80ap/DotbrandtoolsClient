/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 11, 2012
 */
package com.bmastudio.dotbrandtools.dataview;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vu Hoai Nam
 *
 * Create date: Sep 11, 2012
 * <p>The result object of the query function. Base on a word and a profileId.
 * <p>This object can generate to JSON or XML when used in RESTFull web services
 * @see WordQueryView
 */
@XmlRootElement(name="BulkQueryView")
public class BulkQueryView {
	
	private List<WordQueryView> matchItems;
	
	private String word;
	
	private String profileName;
	
	private Integer profileId;

	public List<WordQueryView> getMatchItems() {
		return matchItems;
	}

	public void setMatchItems(List<WordQueryView> matchItems) {
		this.matchItems = matchItems;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	@Override
	public String toString() {
		return "BulkQueryView [matchItems=" + matchItems + ", word=" + word
				+ ", profileName=" + profileName + ", profileId=" + profileId
				+ "]";
	}
	
	
}
