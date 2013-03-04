/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Aug 30, 2012
 */
package com.bmastudio.dotbrandtools.dataview;

import javax.xml.bind.annotation.XmlRootElement;

import com.bmastudio.BMAUtils.Utils.DotBrandToolsConst;

/**
 * @author Vu Hoai Nam
 *
 * Create date: Aug 30, 2012
 * <p>Description: The view object of WordQuery
 * <p>This object can generate to JSON or XML when used in RESTFull web services
 */
@XmlRootElement(name="WordQueryView")
public class WordQueryView implements Comparable<WordQueryView> {
	
	private String itemName;
	
	private String itemDescription;
	
	private String folderName;
	
	private String folderDescription;
	
	private String folderRules;
	
	private String similiarType;
	
	private Integer score;
	
	private String property;
	
	

	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * @return the similiarType
	 */
	public String getSimiliarType() {
		return similiarType;
	}

	/**
	 * @return the folderDescription
	 */
	public String getFolderDescription() {
		return folderDescription;
	}

	/**
	 * @param folderDescription the folderDescription to set
	 */
	public void setFolderDescription(String folderDescription) {
		this.folderDescription = folderDescription;
	}

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * @param similiarType the similiarType to set
	 */
	public void setSimiliarType(String similiarType) {
		this.similiarType = similiarType;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}

	/**
	 * @param folderName the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	/**
	 * @return the folderRules
	 */
	public String getFolderRules() {
		return folderRules;
	}

	/**
	 * @param folderRules the folderRules to set
	 */
	public void setFolderRules(String folderRules) {
		this.folderRules = folderRules;
	}
	
	public int compareTo(WordQueryView wordQueryView) {
		int val1 = 0;
		if( wordQueryView.property.equals( DotBrandToolsConst.BLOCK ) ) {
			val1 = 2;
		}else if ( wordQueryView.property.equals( DotBrandToolsConst.RESERVED ) ){
			val1 = 1;
		}
		
		int val2 = 0;
		if( property.equals( DotBrandToolsConst.BLOCK ) ) {
			val2 = 2;
		}else if ( property.equals( DotBrandToolsConst.RESERVED ) ){
			val2 = 1;
		}
		
		return ( val1 -val2 );
	}
	
}


