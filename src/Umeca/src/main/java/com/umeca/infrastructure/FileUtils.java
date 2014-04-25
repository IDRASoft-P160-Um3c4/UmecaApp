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
	/*
	public static ResponseMessage saveFile(SolicitudArchivo solArch, FileUpload fileUp, String serverPath, String sDir){

		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
		 
			 CommonsMultipartFile file = fileUp.getFile();
			
			if (file.getSize() > 0) {
                inputStream = file.getInputStream();

                UUID uuid = UUID.randomUUID();
                String fileNameGen = uuid.toString();
                
                fileNameGen = combine(sDir, fileNameGen);
                
                solArch.setNomArchFis(fileNameGen);
                String realfileName = combine(serverPath, fileNameGen);
                
                File filePath = new File(realfileName);
                filePath.getParentFile().mkdirs();
                
                outputStream = new FileOutputStream(realfileName);
            //    System.out.println("fileName:" + file.getOriginalFilename());

                int readBytes = 0;
                byte[] buffer = new byte[10000010];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                        outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();
        }
			
		} catch (Exception e) {
			return new ResponseMessage(false, "No fue posible almacenar el archivo en el servidor");
		}
		finally{
			if(inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					return new ResponseMessage(false, "No fue posible almacenar el archivo en el servidor entrada.");
				}

			if(outputStream != null)
				try {
					outputStream.close();
				} catch (IOException e) {
					return new ResponseMessage(false, "No fue posible almacenar el archivo en el servidor salida.");
				}
		}
		return null;
	}
    */
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
