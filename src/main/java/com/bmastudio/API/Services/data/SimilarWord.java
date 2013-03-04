/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 19, 2012
 */
package com.bmastudio.API.Services.data;

import javax.xml.bind.annotation.XmlRootElement;

import com.bmastudio.BMAUtils.Utils.DotBrandToolsConst;
import com.bmastudio.dotbrandtools.dataview.WordQueryView;

/**
 * @author Vu Hoai Nam
 *
 * Create date: Sep 19, 2012
 * <p>Description: XML Object View use for GenerateSimilarService
 * Properties are:
 * <li> word is similar word
 * <li> score is percentage of similar
 */
@XmlRootElement(name="similar")
public class SimilarWord implements Comparable<SimilarWord> {
	
	private String word;
	private Integer score;
	private boolean isTypos;
	
	/**
	 * @return the isTypos
	 */
	public boolean isTypos() {
		return isTypos;
	}
	/**
	 * @param isTypos the isTypos to set
	 */
	public void setTypos(boolean isTypos) {
		this.isTypos = isTypos;
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
	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	@Override
	public int compareTo(SimilarWord word) {
		//Sort Score value
		return ( word.getScore() - this.score  );
	}
	
}
