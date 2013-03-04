/**
 * 
 */
package com.bmastudio.BMASimilar.Services;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import com.bmastudio.BMAUtils.Utils.BmaSimilarList;

/**
 * @author Vu Hoai Nam
 * Description: This class Write/Read the generated words to CSV file to server for reuse next similar request.
 * <p>
 */
public class WriteAllGeneratorThread {
	
	//The word will generate
	private String word;
	private String serverPath = "/resources/csv/" ;
	private String filePath ;
	
	public WriteAllGeneratorThread( String word, String contextPath, String threshold ){
		this.word = word;
		this.filePath = contextPath + serverPath + "AllGenerated" + "_" + word + "_" + threshold +".csv" ;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>This function check whether the generated similar file of same word exists.
	 * <p>If exists just read the list word in file to the map.
	 * <p>If not exists, the function with create a csv file to server for reuse when having same generate request. 
	 * @param model
	 * @param threshold
	 * @return
	 * @throws FileNotFoundException
	 */
	public Map createCsvFile( Map model, int threshold ) throws FileNotFoundException {
		
		File file = new File( filePath );
		if( !file.isFile() ){
			CompareNIST.init();
			HashMap<String, Integer> listSimiliar = CompareNIST.generateStr( word , threshold );
			
			Map<String, Map<String, Integer>> listTypo = new TypoGenerator().getAllTypos( word );
			
			//GET ALL LIST GENERATOR NOT MAPPING
			//There are 7 lists : changeOneChar, addOneChar, subtractOneChar, changeTwoChar, addOneChangeOne, addTwoChar, subtractTwo
			Map<String, Map<String, Integer>> listAllNoMapping = SimilarGenrator.getAll( word , threshold , false );
			
			try {
				PrintWriter writer = new PrintWriter(file);
				
				writer.println("LIST SIMILAR GENERATOR");
				for (Entry<String, Integer> entry : listSimiliar.entrySet()) {
					writer.println(entry.getKey() + "," + entry.getValue() );
				}
				
				//WRITE OUT THE TYPOS CHAR
				writer.println("LIST TYPOS GENERATOR");
				writer.println("TYPOS WRONG KEY");
				for (Entry<String, Integer> entry : listTypo.get("lstWrongKeyTypos").entrySet()) {
					writer.println(entry.getKey() + "," + entry.getValue() );
				}
				writer.println("TYPOS MISSED CHAR");
				for (Entry<String, Integer> entry : listTypo.get("lstMissedCharTypos").entrySet()) {
					writer.println(entry.getKey() + "," + entry.getValue() );
				}
				writer.println("TYPOS TRANSPOSED CHAR");
				for (Entry<String, Integer> entry : listTypo.get("lstTransposedTypos").entrySet()) {
					writer.println(entry.getKey() + "," + entry.getValue() );
				}
				writer.println("TYPOS DOUBLE CHAR");
				for (Entry<String, Integer> entry : listTypo.get("lstDoubleCharTypos").entrySet()) {
					writer.println(entry.getKey() + "," + entry.getValue() );
				}
				
				//WRITE OUT THE ALL GENERATE
				writer.println("LIST ALL CHANGE GENERATOR");
				writer.println("CHANGE ONE CHAR");
				for (Entry<String, Integer> entry : listAllNoMapping.get("changeOneChar").entrySet()) {
					writer.println(entry.getKey() + "," + entry.getValue() );
				}
				writer.println("ADD ONE CHAR");
				for (Entry<String, Integer> entry : listAllNoMapping.get("addOneChar").entrySet()) {
					writer.println(entry.getKey() + "," + entry.getValue() );
				}
				writer.println("SUBTRACK ONE CHAR");
				for (Entry<String, Integer> entry : listAllNoMapping.get("subtractOneChar").entrySet()) {
					writer.println(entry.getKey() + "," + entry.getValue() );
				}
				writer.println("ADD TWO CHAR");
				for (Entry<String, Integer> entry : listAllNoMapping.get("addTwoChar").entrySet()) {
					writer.println(entry.getKey() + "," + entry.getValue() );
				}
				writer.println("SUBTRACK TWO CHAR");
				for (Entry<String, Integer> entry : listAllNoMapping.get("subtractTwo").entrySet()) {
					writer.println(entry.getKey() + "," + entry.getValue() );
				}
				writer.println("CHANGE TWO CHAR");
				for (Entry<String, Integer> entry : listAllNoMapping.get("changeTwoChar").entrySet()) {
					writer.println(entry.getKey() + "," + entry.getValue() );
				}
				writer.println("ADD ONE CHANGE ONE CHAR");
				for (Entry<String, Integer> entry : listAllNoMapping.get("addOneChangeOne").entrySet()) {
					writer.println(entry.getKey() + "," + entry.getValue() );
				}
				writer.flush();
				
			} catch (FileNotFoundException e) {
				// TODO - WRITE TO LOG FILE
				e.printStackTrace();
				throw e;
			}	
		}
		
		model = readCSVFile(model, file);
		
		return model;
    }
	
	/**
	 * Read CSV generate file to map
	 * @param model
	 * @param file
	 * @return Map list words with score
	 * @throws FileNotFoundException
	 */
	private Map readCSVFile( Map model, File file ) throws FileNotFoundException{
		
		Scanner sc = new Scanner(file);
		String similarGenerator = "LIST SIMILAR GENERATOR";
		HashMap<String, Integer> listSimiliar = new HashMap<String, Integer>();
		
		//Match pattern for typos
		String typoWrongKey = "TYPOS WRONG KEY";
		Map<String, Integer> typosWrongKeyList = new HashMap<String, Integer>();
		
		String typoMissChar = "TYPOS MISSED CHAR";
		Map<String, Integer> lstMissedCharTypos = new HashMap<String, Integer>();
		
		String typoTransposedChar = "TYPOS TRANSPOSED CHAR";
		Map<String, Integer> lstTransposedTypos = new HashMap<String, Integer>();
		
		String typoDoubleChar = "TYPOS DOUBLE CHAR";
		Map<String, Integer> lstDoubleCharTypos = new HashMap<String, Integer>();
		
		//Match pattern for all change char generate
		String changeOneChar = "CHANGE ONE CHAR";
		Map<String, Integer> changeOneCharList = new HashMap<String, Integer>();
		String addOneChar = "ADD ONE CHAR";
		Map<String, Integer> addOneCharList = new HashMap<String, Integer>();
		String subtractOneChar = "SUBTRACK ONE CHAR";
		Map<String, Integer> subtractOneCharList = new HashMap<String, Integer>();
		String addTwoChar = "ADD TWO CHAR";
		Map<String, Integer> addTwoCharList = new HashMap<String, Integer>();
		String subtractTwo = "SUBTRACK TWO CHAR";
		Map<String, Integer> subtractTwoList = new HashMap<String, Integer>();
		String changeTwoChar = "CHANGE TWO CHAR";
		Map<String, Integer> changeTwoCharList = new HashMap<String, Integer>();
		String addOneChangeOne = "ADD ONE CHANGE ONE CHAR";
		Map<String, Integer> addOneChangeOneList = new HashMap<String, Integer>();
		
		
		String dataType = "";
		
		int countChangeTwoChar = 0; 
		int countAddOneChangeOne = 0;
		
		while( sc.hasNextLine() ) {
			String line = sc.nextLine();
			boolean isDataLine = line.contains(",");
			
			if( isDataLine){
				String[] data = line.split(",");
				if( dataType.equals("similarGenerator") ){
					String strSimilar = data[0];
					Integer scoreSimilar = Integer.parseInt(data[1]);
					listSimiliar.put(strSimilar, scoreSimilar);
				}
				
				else if( dataType.equals("typoWrongKey") ){
					String str = data[0];
					Integer score = Integer.parseInt(data[1]);
					typosWrongKeyList.put(str, score);
				}
				else if( dataType.equals("typoMissChar") ){
					String str = data[0];
					Integer score = Integer.parseInt(data[1]);
					lstMissedCharTypos.put(str, score);
				}
				else if( dataType.equals("typoTransposedChar") ){
					String str = data[0];
					Integer score = Integer.parseInt(data[1]);
					lstTransposedTypos.put(str, score);
				}
				else if( dataType.equals("typoDoubleChar") ){
					String str = data[0];
					Integer score = Integer.parseInt(data[1]);
					lstDoubleCharTypos.put(str, score);
				}
				else if( dataType.equals("changeOneChar") ){
					String str = data[0];
					Integer score = Integer.parseInt(data[1]);
					changeOneCharList.put(str, score);
				}
				else if( dataType.equals("addOneChar") ){
					String str = data[0];
					Integer score = Integer.parseInt(data[1]);
					addOneCharList.put(str, score);
				}
				else if( dataType.equals("subtractOneChar") ){
					String str = data[0];
					Integer score = Integer.parseInt(data[1]);
					subtractOneCharList.put(str, score);
				}
				else if( dataType.equals("addTwoChar") ){
					String str = data[0];
					Integer score = Integer.parseInt(data[1]);
					addTwoCharList.put(str, score);
				}
				else if( dataType.equals("subtractTwo") ){
					String str = data[0];
					Integer score = Integer.parseInt(data[1]);
					subtractTwoList.put(str, score);
				}
				else if( dataType.equals("changeTwoChar") && countChangeTwoChar <= 100 ){
					String str = data[0];
					Integer score = Integer.parseInt(data[1]);
					changeTwoCharList.put(str, score);
					countChangeTwoChar++;
				}
				else if( dataType.equals("addOneChangeOne") && countAddOneChangeOne <= 100 ){
					String str = data[0];
					Integer score = Integer.parseInt(data[1]);
					addOneChangeOneList.put(str, score);
					countAddOneChangeOne++;
				}
				
			}
			else {
				if( similarGenerator.equals(line) ) dataType = "similarGenerator";
				//TYPOS
				else if ( typoWrongKey.equals(line) ) dataType = "typoWrongKey";
				else if ( typoMissChar.equals(line) ) dataType = "typoMissChar";
				else if ( typoTransposedChar.equals(line) ) dataType = "typoTransposedChar";
				else if ( typoDoubleChar.equals(line) ) dataType = "typoDoubleChar";
			
				//CHANGE CHAR
				else if ( changeOneChar.equals(line) ) dataType = "changeOneChar";
				else if ( addOneChar.equals(line) ) dataType = "addOneChar";
				else if ( subtractOneChar.equals(line) ) dataType = "subtractOneChar";
				else if ( addTwoChar.equals(line) ) dataType = "addTwoChar";
				else if ( subtractTwo.equals(line) ) dataType = "subtractTwo";
				else if ( changeTwoChar.equals(line) ) dataType = "changeTwoChar";
				else if ( addOneChangeOne.equals(line) ) dataType = "addOneChangeOne";	
			}
			
		}
		
		model.put("listResult", BmaSimilarList.sortHashMapByValues( listSimiliar ));
		
		Map<String, Map<String, Integer>> listTypo = new HashMap<String, Map<String,Integer>>();
		typosWrongKeyList = BmaSimilarList.sortHashMapByValues( typosWrongKeyList );
		listTypo.put( "lstWrongKeyTypos", typosWrongKeyList );
		lstMissedCharTypos = BmaSimilarList.sortHashMapByValues(lstMissedCharTypos);
		listTypo.put( "lstMissedCharTypos", lstMissedCharTypos );
		lstTransposedTypos = BmaSimilarList.sortHashMapByValues(lstTransposedTypos);
		listTypo.put( "lstTransposedTypos", lstTransposedTypos );
		lstDoubleCharTypos = BmaSimilarList.sortHashMapByValues(lstDoubleCharTypos);
		listTypo.put( "lstDoubleCharTypos", lstDoubleCharTypos );
		model.put("listTypo", listTypo);
		
		Map<String, Map<String, Integer>> listAllNoMapping = new HashMap<String, Map<String,Integer>>();
		
		changeOneCharList = BmaSimilarList.sortHashMapByValues(changeOneCharList);
		listAllNoMapping.put( "changeOneChar", changeOneCharList );
		
		addOneCharList = BmaSimilarList.sortHashMapByValues( addOneCharList );
		listAllNoMapping.put( "addOneChar", addOneCharList );
		
		subtractOneCharList = BmaSimilarList.sortHashMapByValues(subtractOneCharList);
		listAllNoMapping.put( "subtractOneChar", subtractOneCharList );
		
		addTwoCharList = BmaSimilarList.sortHashMapByValues(addTwoCharList);
		listAllNoMapping.put( "addTwoChar", addTwoCharList );
		
		subtractTwoList = BmaSimilarList.sortHashMapByValues(subtractTwoList);
		listAllNoMapping.put( "subtractTwo", subtractTwoList );
		
		changeTwoCharList = BmaSimilarList.sortHashMapByValues(changeTwoCharList);
		listAllNoMapping.put( "changeTwoChar", changeTwoCharList );
		
		addOneChangeOneList = BmaSimilarList.sortHashMapByValues(addOneChangeOneList);
		listAllNoMapping.put( "addOneChangeOne", addOneChangeOneList );
		
		model.put("listAllNoMapping", listAllNoMapping);
		
		return model;
	}
	
	/** 
	 * @param filePath the name of the file to open. Not sure if it can accept URLs or just filenames. Path handling could be better, and buffer sizes are hardcoded
	*/ 
	public StringBuffer readFileAsStringBuffer( int threshold ) throws java.io.IOException{
		if( !new File( filePath ).isFile() ){
			createCsvFile( new HashMap() , threshold );
		}
		
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
   
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        
        reader.close();
        return fileData; 
    }
	
	/**
	 * readfile into string
	 * @param filePath
	 * @return
	 * @throws java.io.IOException
	 */
	public String readFileAsString() throws java.io.IOException{ 
		byte[] buffer = new byte[(int) new File(filePath).length()]; 
		BufferedInputStream f = null; 
		try { 
			f = new BufferedInputStream(new FileInputStream(filePath)); 
			f.read(buffer); 
		} 
		finally { 
			if (f != null) try { f.close(); 
		} 
		catch (IOException ignored) {
			
		} 
		} return new String(buffer); 
	}
	
}
