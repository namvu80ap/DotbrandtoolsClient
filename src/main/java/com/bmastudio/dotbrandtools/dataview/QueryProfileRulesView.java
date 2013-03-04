/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 10, 2012
 */
package com.bmastudio.dotbrandtools.dataview;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vu Hoai Nam
 *
 * Create date: Sep 10, 2012
 * <p>Description: The view object of QueryProfileRules
 * <p>This object can generate to JSON or XML when used in RESTFull web services
 */
@XmlRootElement(name="QueryProfileRulesView")
public class QueryProfileRulesView {
	
	private boolean invalidStartWith;
	
	private String[] mustStartWith;
	
	private boolean invalidEndWith;
	
	private String[] mustEndWith;
	
	private boolean invalidLength;
	
	private Integer mustLengt;

	private String invalidCondition;
	
	
	
	public String getInvalidCondition() {
		return invalidCondition;
	}

	public void setInvalidCondition(String invalidCondition) {
		this.invalidCondition = invalidCondition;
	}

	public boolean isInvalidLength() {
		return invalidLength;
	}

	public void setInvalidLength(boolean invalidLength) {
		this.invalidLength = invalidLength;
	}

	public boolean isInvalidStartWith() {
		return invalidStartWith;
	}

	public void setInvalidStartWith(boolean invalidStartWith) {
		this.invalidStartWith = invalidStartWith;
	}

	public String[] getMustStartWith() {
		return mustStartWith;
	}

	public void setMustStartWith(String[] mustStartWith) {
		this.mustStartWith = mustStartWith;
	}

	public boolean isInvalidEndWith() {
		return invalidEndWith;
	}

	public void setInvalidEndWith(boolean invalidEndWith) {
		this.invalidEndWith = invalidEndWith;
	}

	public String[] getMustEndWith() {
		return mustEndWith;
	}

	public void setMustEndWith(String[] mustEndWith) {
		this.mustEndWith = mustEndWith;
	}

	public Integer getMustLengt() {
		return mustLengt;
	}

	public void setMustLengt(Integer mustLengt) {
		this.mustLengt = mustLengt;
	}
	
	
}
