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


import java.util.Map;

/**
 * @author Vu Hoai Nam
 * <p>Description: POJO object for StringType
 */
public class StringType implements Map.Entry<String, String> {
	private String word;
	private String type;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	public StringType() {
		super();
	}

	public StringType(String word, String type) {
		super();
		this.word = word;
		this.type = type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	public String getKey() {
		return this.word;
	}

	public String getValue() {
		return this.type;
	}

	public String setValue(String value) {
		return type;
	} 

	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != getClass()) { 
	        return false;
	    }
		final StringType other = (StringType)o;

		return ( other.word.equals(word) && other.type.equals(type) );
	}
}
