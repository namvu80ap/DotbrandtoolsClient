/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Aug 24, 2012
 */
package com.bmastudio.dotbrandtools.data;

/**
 * @author Vu Hoai Nam
 * <p>Description: The Data Object mapping with CompareResult collection
 */
public class CompareResult implements Comparable<CompareResult> {
	
	private String leftWord;
	private String rightWord;
	private String result;
	private String creDate;
	private String uddDate;
	public int getResultInt() {
		return resultInt;
	}
	public void setResultInt(int resultInt) {
		this.resultInt = resultInt;
	}
	private int resultInt;
	
	public String getLeftWord() {
		return leftWord;
	}
	public void setLeftWord(String leftWord) {
		this.leftWord = leftWord;
	}
	public String getRightWord() {
		return rightWord;
	}
	public void setRightWord(String rightWord) {
		this.rightWord = rightWord;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = creDate;
	}
	public String getUddDate() {
		return uddDate;
	}
	public void setUddDate(String uddDate) {
		this.uddDate = uddDate;
	}
	public int compareTo(CompareResult compareResult) {
		int diff = compareResult.resultInt - resultInt;
		return diff;
	}
	  
}
