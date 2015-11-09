package com.gcs.aol.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

public class KeyWord {

	public String[] keyword = {};
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	
	public KeyWord(HttpServletRequest request) {
		this.keyword = readKeyWord(request);
	}

	public static String[] readKeyWord(HttpServletRequest request){
		 String path = "/WEB-INF/keyword/keyword.txt";
		 InputStreamReader inputStreamReader = null;  
		 try {
				 InputStream inputStream =request.getSession().getServletContext().getResourceAsStream(path);
				 inputStreamReader = new InputStreamReader(inputStream, "UTF-8"); 
				 BufferedReader reader = new BufferedReader(inputStreamReader);  
				 StringBuffer sb = new StringBuffer("");  
				 String line;
				 while ((line = reader.readLine()) != null) {  
			            sb.append(line);  
		        } 
				return sb.toString().split(",");
			 } catch (Exception e) {
			  System.out.println("读取文件内容出错");
			  e.printStackTrace();
			 }
		return null;
		
		  
	}
}
