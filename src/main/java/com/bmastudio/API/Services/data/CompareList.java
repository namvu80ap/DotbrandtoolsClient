/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 19, 2012
 */
package com.bmastudio.API.Services.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Vu Hoai Nam
 *
 * Create date: Sep 19, 2012
 * Description: XML Object View use for BulkCompareService
 */
@XmlRootElement(name="CompareList")
public class CompareList {
	
	private List<SimilarWord> listResult;

	/**
	 * Get list SimilarWord
	 * @see SimilarWord
	 * @return the listResult
	 */
	public List<SimilarWord> getListResult() {
		return listResult;
	}

	/**
	 * @param listResult the listResult to set
	 */
	public void setListResult(List<SimilarWord> listResult) {
		this.listResult = listResult;
	}
	
}
