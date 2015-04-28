package com.umeca.infrastructure;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtils {

	public static String combine (String path1, String path2)
	{
	    File file1 = new File(path1);
	    File file2 = new File(file1, path2);
	    return file2.getPath();
	}

	public static void deleteFile(String nomArchFis, String serverPath) {


		try {
		    File file = new File(serverPath);
		    File fileFs = new File(file, nomArchFis);
		    fileFs.delete(); 
		} catch (Exception e) {
		}	
	}

	public static InputStream recoverFile(CommonsMultipartFile file) {
		
		if (file.getSize() <= 0) {
			return null;
		}

		try {
			InputStream inputStream = file.getInputStream();
			 return inputStream;
		} catch (IOException e) {
		}
		return null;
	}
}
