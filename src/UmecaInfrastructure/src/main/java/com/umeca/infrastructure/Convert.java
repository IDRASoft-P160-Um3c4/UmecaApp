package com.umeca.infrastructure;

public class Convert {

	public static Long ToLong(String value) {
		
		try {
		
			return Long.parseLong(value);
			
		} catch (Exception e) {
			return null;
		}
		
	}

	public static String convertToValidString(String s){
		s = s.replace("�","&aacute;");
		s = s.replace("�","&eacute;");
		s = s.replace("�","&iacute;");
		s = s.replace("�","&oacute;");
		s = s.replace("�","&uacute;");
		s = s.replace("�","&Aacute;");
		s = s.replace("�","&Eacute;");
		s = s.replace("�","&Iacute;");
		s = s.replace("�","&Oacute;");
		s = s.replace("�","&Uacute;");
		s = s.replace("�","&ntilde;");
		s = s.replace("�","&Ntilde;");
		s = s.replace("�","&iquest;");
		return s;
	}

}
