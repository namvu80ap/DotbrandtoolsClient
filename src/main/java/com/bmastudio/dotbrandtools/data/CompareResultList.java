/**
 * Copyright (C) 2012 BMA Group, Inc.
 * 
 * This file is part of the DotBrandTools project, hosted at
 * http://DotBrandTools.com/
 * 
 * DotBrandTools is software: .......
 *
 * You should have received a copy of the BMA License
 *  If not, please contact a http://DotBrandTools.com/.
 */
package com.bmastudio.dotbrandtools.data;

import java.util.ArrayList;


/**
 * @author Vu Hoai Nam
 * <p>Description: The Data Object contain many type of List CompareResult
 */
public class CompareResultList {
	
	
	private ArrayList<CompareResult> before;
	private ArrayList<CompareResult> exitant;
	private ArrayList<CompareResult> reserved;
	private ArrayList<CompareResult> after;
	
	/**
	 * @return the after
	 */
	public ArrayList<CompareResult> getAfter() {
		return after;
	}

	/**
	 * @param after the after to set
	 */
	public void setAfter(ArrayList<CompareResult> after) {
		this.after = after;
	}

	/**
	 * @return the exitant
	 */
	public ArrayList<CompareResult> getExitant() {
		return exitant;
	}

	/**
	 * @param exitant the exitant to set
	 */
	public void setExitant(ArrayList<CompareResult> exitant) {
		this.exitant = exitant;
	}

	
	/**
	 * @return the reserved
	 */
	public ArrayList<CompareResult> getReserved() {
		return reserved;
	}

	/**
	 * @param reserved the reserved to set
	 */
	public void setReserved(ArrayList<CompareResult> reserved) {
		this.reserved = reserved;
	}

	private String strType;
	/**
	 * @return the strType
	 */
	public String getStrType() {
		return strType;
	}

	/**
	 * @param strType the strType to set
	 */
	public void setStrType(String strType) {
		this.strType = strType;
	}

	/**
	 * @return the before
	 */
	public ArrayList<CompareResult> getBefore() {
		return before;
	}

	/**
	 * @param before the before to set
	 */
	public void setBefore(ArrayList<CompareResult> before) {
		this.before = before;
	}

	
}
