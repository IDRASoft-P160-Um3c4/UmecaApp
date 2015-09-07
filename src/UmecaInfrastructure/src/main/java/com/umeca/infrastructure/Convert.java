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
		s = s.replace("á","&aacute;");
		s = s.replace("é","&eacute;");
		s = s.replace("í","&iacute;");
		s = s.replace("ó","&oacute;");
		s = s.replace("ú","&uacute;");
		s = s.replace("Á","&Aacute;");
		s = s.replace("É","&Eacute;");
		s = s.replace("Í","&Iacute;");
		s = s.replace("Ó","&Oacute;");
		s = s.replace("Ú","&Uacute;");
		s = s.replace("ñ","&ntilde;");
		s = s.replace("Ñ","&Ntilde;");
		s = s.replace("¿","&iquest;");
		return s;
	}

}
