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
 * <p>Description: The view object of BulkQuery
 * <p>This object can generate to JSON or XML when used in RESTFull web services
 */
@XmlRootElement(name="ListBulkQueryView")
public class ListBulkQueryView {
	
	private String word;
	
	private List<BulkQueryView> list;
	
	private QueryProfileRulesView invalidRules;
	
	
	public QueryProfileRulesView getInvalidRules() {
		return invalidRules;
	}

	public void setInvalidRules(QueryProfileRulesView invalidRules) {
		this.invalidRules = invalidRules;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<BulkQueryView> getList() {
		return list;
	}

	public void setList(List<BulkQueryView> list) {
		this.list = list;
	}
	
}
