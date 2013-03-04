/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 19, 2012
 */
package com.bmastudio.API.Services.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.bmastudio.dotbrandtools.dataview.ListBulkQueryView;

/**
 * @author Vu Hoai Nam
 *
 * Create date: Sep 19, 2012
 * Description: XML Object View use for BulkQueryService
 * @see BulkQueryService
 */
@XmlRootElement(name="BulkQueryLists")
public class BulkQueryList {
	
	private List<ListBulkQueryView> bulkViews;

	/**
	 * Get ListBulkQueryView 
	 * @see ListBulkQueryView
	 * @return the bulkViews
	 */
	public List<ListBulkQueryView> getBulkViews() {
		return bulkViews;
	}

	/**
	 * @param bulkViews the bulkViews to set
	 */
	public void setBulkViews(List<ListBulkQueryView> bulkViews) {
		this.bulkViews = bulkViews;
	}
	
	
}
