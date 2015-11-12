package com.umeca.infrastructure;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReaderFile {

	public static List<String[]> readFile(String fileName, String delimiter, int len){
		
		List<String[]> lstData = new ArrayList<String[]>();
	    
		try {
			FileInputStream fstream = new FileInputStream(fileName);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		        BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		    String strLine;
		    //Read File Line By Line
		    long iCount = 0;
		    
		    
		    while ((strLine = br.readLine()) != null)   {

		    	iCount ++;
		    	System.out.println("Lee: " + iCount);
				
		    	//System.out.println (strLine);
		      
		    	if(iCount <= 1L)
		    		continue;
		    	  
		    	String[] data = strLine.split(delimiter);
		    	
		    	if(data.length != len)
		    		continue;
		      
		    	lstData.add(data);
		    	
		    }
		    
		    //Close the input stream
		    in.close();			

		    
		} catch (Exception e) {
			System.out.println (e.getMessage());
		}

	    return lstData;
	}
}
