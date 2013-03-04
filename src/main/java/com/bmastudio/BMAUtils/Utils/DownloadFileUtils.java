/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 10, 2012
 */
package com.bmastudio.BMAUtils.Utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vu Hoai Nam
 *<p>Description: The utilities class handle some specify action on download file of dotbrandtools
 */
public class DownloadFileUtils {
	
	private static final String DEFAULT_FILE_TYPE = "csv";
	
	/**
	 * Download the list compare word with format type default csv 
	 * @param response
	 * @throws Exception 
	 */
	public static void downloadCompareWords( HttpServletResponse response, StringBuffer sb, String fileName ) throws Exception{
		try{
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition","attachment;filename="+ fileName +"." + DEFAULT_FILE_TYPE );

			ServletOutputStream out = response.getOutputStream();
			
			byte[] byteBuff = sb.toString().getBytes();
			
			int byteLenght = byteBuff.length;
		
			InputStream in = new ByteArrayInputStream(byteBuff);
			
			byte[] outputByte = new byte[byteLenght];
			
			while(in.read(outputByte, 0, byteLenght) != -1)
			{
				out.write(outputByte, 0, byteLenght);
			}
			
			in.close();
			out.flush();
			out.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * build the stringBuffer to write out from compare/search words with two
	 * fields ( word and score )
	 * @param listWords
	 * @return StringBuffer
	 */
	public static StringBuffer buildCSVBuffer( Map<String, Integer> listWords , String title ){
		
		StringBuffer writer = new StringBuffer();
		writer.append("List of generated words");
		writer.append('\n');
		
		for (Entry<String, Integer> entry : listWords.entrySet()) {
			writer.append(entry.getKey());	writer.append(entry.getValue());
			writer.append('\n');
		}
		
		return writer;
	}
	
	public static void writeCompareTempFile( Map<String, Integer> listWords ){
		//PrintWriter writer = new PrintWriter("");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
