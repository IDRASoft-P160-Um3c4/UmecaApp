package com.umeca.infrastructure;

public class Convert {

	public static Long ToLong(String value) {
		
		try {
		
			return Long.parseLong(value);
			
		} catch (Exception e) {
			return null;
		}
		
	}

}
