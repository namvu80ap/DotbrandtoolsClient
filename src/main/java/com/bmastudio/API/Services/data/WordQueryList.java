/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 19, 2012
 */
package com.bmastudio.API.Services.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.bmastudio.dotbrandtools.dataview.QueryProfileRulesView;
import com.bmastudio.dotbrandtools.dataview.WordQueryView;

/**
 * @author Vu Hoai Nam
 *
 * Create date: Sep 19, 2012
 * <p>Description: List XML Object View use for GenerateSimilarService
 * Properties are:
 * <li> InvalidRule is the warning of invalid rules by Profile 
 * <li> QueryResult is the list of waring setup by Profile Words
 */
@XmlRootElement(name="WordQueryViews")
public class WordQueryList {
	
	private QueryProfileRulesView invalidRule;
	
	private List<WordQueryView> queryResult;

	
	/**
	 * @return the invalidRule
	 */
	public QueryProfileRulesView getInvalidRule() {
		return invalidRule;
	}

	/**
	 * @param invalidRule the invalidRule to set
	 */
	public void setInvalidRule(QueryProfileRulesView invalidRule) {
		this.invalidRule = invalidRule;
	}

	/**
	 * @return the queryResult
	 */
	public List<WordQueryView> getQueryResult() {
		return queryResult;
	}

	/**
	 * @param queryResult the queryResult to set
	 */
	public void setQueryResult(List<WordQueryView> queryResult) {
		this.queryResult = queryResult;
	}
	
}
