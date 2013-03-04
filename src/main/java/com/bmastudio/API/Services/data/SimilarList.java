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
 * Description: List XML Object View use for GenerateSimilarService
 */
@XmlRootElement(name="similarList")
public class SimilarList {
	
	private List<SimilarWord> similar;
	
	private List<SimilarWord> typos;

	/**
	 * Get list SimilarWord
	 * @see SimilarWord
	 * @return the similar
	 */
	public List<SimilarWord> getSimilar() {
		return similar;
	}

	/**
	 * @param similar the similar to set
	 */
	public void setSimilar(List<SimilarWord> similar) {
		this.similar = similar;
	}

	/**
	 * Get list SimilarWord in Typos
	 * @see SimilarWord
	 * @return the typos
	 */
	public List<SimilarWord> getTypos() {
		return typos;
	}

	/**
	 * @param typos the typos to set
	 */
	public void setTypos(List<SimilarWord> typos) {
		this.typos = typos;
	}
	
	
}
