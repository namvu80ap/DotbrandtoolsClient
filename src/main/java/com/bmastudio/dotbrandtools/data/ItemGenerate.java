/**
 * This project @copyright by Lucas Valls
 * Create by user
 * Create date: Aug 23, 2012
 */
package com.bmastudio.dotbrandtools.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Vu Hoai Nam
 *<p>Description: The Data Object mapping with ItemGenerate
 */
@Document
@CompoundIndexes({
	@CompoundIndex( name = "item_generate_idx" , def = "{ 'item' : 1, 'generated' : 1, 'similarType' : 1 }" )
})
public class ItemGenerate {
	
	/**
	 * Collection name itemGenerate
	 */
	public static final String DOCUMENT_NAME = "itemGenerate" ;
	
	/*
	 * DECLARE THE FIELDS NAME OF DOCUMENT OBJECT
	 */
	/**
	 * Document field: item
	 */
	public static final String ITEM = "item" ;
	/**
	 * Document field: generated
	 */
	public static final String GENERATED = "generated" ;
	/**
	 * Document field: similarType
	 */
	public static final String SIMILARTYPE = "similarType" ;
	
	@Id
	private String id;
	
	@Indexed
	private String item;
	
	@Indexed
	private String generated;
	
	@Indexed
	private String similarType;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * @return the generated
	 */
	public String getGenerated() {
		return generated;
	}

	/**
	 * @param generated the generated to set
	 */
	public void setGenerated(String generated) {
		this.generated = generated;
	}

	/**
	 * @return the similarType
	 */
	public String getSimilarType() {
		return similarType;
	}

	/**
	 * @param similarType the similarType to set
	 */
	public void setSimilarType(String similarType) {
		this.similarType = similarType;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemGenerate [item=" + item + ", generated=" + generated
				+ ", similarType=" + similarType + "]";
	}

	/**
	 * @param item
	 * @param generated
	 * @param similarType
	 */
	public ItemGenerate(String item, String generated, String similarType) {
		super();
		this.item = item;
		this.generated = generated;
		this.similarType = similarType;
	}
	
	
	
}
