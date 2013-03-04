/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 10, 2012
 */
package com.bmastudio.BMAUtils.Utils;

import java.util.HashSet;

import com.bmastudio.BMASimilar.Services.CompareNIST;

/**
 * 
 * @author Vu Hoai Nam
 * <p>Description: The implement hashset using for similar generator.
 */
public class BmaSimilarHashSet<T> extends HashSet<T> {

	@Override
	public boolean add(T e) {
	  return super.add(e);
	}


	/**
	 * add String to HashSet base on the threshold of difference char
	 * @param original
	 * @param input
	 * @param threshold
	 * @return
	 */
	public boolean add(T original, String input , double threshold) {
	  String str = (String)original;
	  if( checkDiff( str, input) >= threshold )
		  return add(original);
	  else
		  return false;
	}

	/**
	 * Check the
	 * @param original
	 * @param input
	 * @return
	 */
	public double checkDiff(String original, String input) {

		String ss = new String("dd");
		char[] tmp1 = original.toCharArray();
		char[] tmp2 = input.toCharArray();

		int count = 0;
		double score = 0;
		for (int i = 0; i < tmp1.length; i++) {
			if (tmp1[i] != tmp2[i]) {
				count++;
				score += CompareNIST.characterSimilarity(String.valueOf(tmp1[i]), String.valueOf(tmp2[i]));
			}
		}

		return score;
	}



}
