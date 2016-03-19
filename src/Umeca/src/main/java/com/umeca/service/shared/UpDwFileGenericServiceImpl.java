package com.umeca.service.shared;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.CatFileType;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.SystemSetting;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.model.shared.Constants;
import com.umeca.repository.shared.CatFileTypeRepository;
import com.umeca.repository.shared.UploadFileGenericRepository;
import com.umeca.service.account.SharedUserService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class UpDwFileGenericServiceImpl implements UpDwFileGenericService {

    @Autowired
    UploadFileGenericRepository uploadFileGenericRepository;

    @Override
    public boolean isValidRequestFile(Iterator<String> itr, ResponseMessage resMsg) {
        if (itr.hasNext() == false) {
            resMsg.setHasError(true);
            resMsg.setMessage("No existe un archivo en la solicitud, por favor revise e intente de nuevo");
            return false;
        }
        return true;
    }

    @Autowired
    SystemSettingService systemSettingsService;

    @Override
    public boolean hasAvailability(UploadFileGeneric file, ResponseMessage resMsg, Long userId) {
        List<SystemSetting> lstSystemSettings = systemSettingsService.findAllOfGroup(Constants.SYSTEM_SETTINGS_ARCHIVE);
        for (SystemSetting systemSetting : lstSystemSettings) {
            switch (systemSetting.getKey()) {
                case Constants.SYSTEM_SETTINGS_ARCHIVE_PATH_TO_SAVE:
                    file.setPath(new File(systemSetting.getValue(), Constants.FILE_PREFIX_USER + userId.toString()).toString());
                    break;
            }
        }

        //Validar archivos con el mismo nombre
        if (uploadFileGenericRepository.alreadyExistFileByUser(userId, file.getFileName().toLowerCase()).longValue() > 0L) {
            resMsg.setHasError(true);
            resMsg.setMessage("Ya existe un archivo con ese nombre");
            return false;
        }

        return true;
    }

    @Override
    public File createDownloadableFile(String fileName, String extension, HttpServletRequest request) {

        String temporalPath = request.getSession().getServletContext().getRealPath("") + Constants.SHARED_PATH_FILES;

        File tempPath = new File(temporalPath);

        //crea la carpeta donde se almacenara la imagen si no existe
        if (!tempPath.exists()) {
            if (tempPath.mkdirs() == false) {
                return null;
            }
        }

        String guidId = UUID.randomUUID().toString();

        File file = new File(temporalPath + fileName + "_" + guidId + extension);
        return file;
    }

    @Override
    public boolean hasPhotoAvailability(UploadFileGeneric file, ResponseMessage resMsg, Long userId, Long employeeId) {
        List<SystemSetting> lstSystemSettings = systemSettingsService.findAllOfGroup(Constants.SYSTEM_SETTINGS_ARCHIVE);
        for (SystemSetting systemSetting : lstSystemSettings) {
            switch (systemSetting.getKey()) {
                case Constants.SYSTEM_SETTINGS_ARCHIVE_EMPLOYEE_PHOTO_PATH_TO_SAVE:
                    file.setPath(new File(systemSetting.getValue(), Constants.FILE_PREFIX_PHOTO_EMPLOYEE + employeeId.toString()).toString());
                    break;
            }
        }

        //Validar archivos con el mismo nombre
        if (uploadFileGenericRepository.alreadyExistFileByUser(userId, file.getFileName().toLowerCase()).longValue() > 0L) {
            resMsg.setHasError(true);
            resMsg.setMessage("Ya existe un archivo con ese nombre");
            return false;
        }

        return true;
    }

    @Override
    public void fillUploadFileGeneric(MultipartFile mpf, UploadFileGeneric file, UploadFileRequest uploadRequest, User user) {
        file.setFileName(mpf.getOriginalFilename());
        file.setSize(mpf.getSize());
        file.setObsolete(true); //Se pone obsoleto mientras no esté asociado al reporte
        file.setDescription(uploadRequest.getDescription());
        file.setCreationTime(Calendar.getInstance());
        file.setCreationUser(user);
        //Crear y guardar el nombre del archivo real
        file.setRealFileName(UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(mpf.getOriginalFilename()));
    }


    @Autowired
    CatFileTypeRepository catFileTypeRepository;

    @Override
    public boolean isValidExtension(MultipartFile mpf, UploadFileGeneric file, ResponseMessage resMsg) {
        String filename = mpf.getOriginalFilename();
        String extension = FilenameUtils.getExtension(filename);

        if (extension == null || extension.isEmpty()) {
            resMsg.setMessage("El archivo no tiene extensión y no puede ser almacendo");
            resMsg.setHasError(true);
            return false;
        }

        extension = extension.toLowerCase();
        final Long fileTypeId = catFileTypeRepository.findByExtension(extension);

        if (fileTypeId == null || fileTypeId.longValue() <= 0) {
            resMsg.setMessage("Tipo de archivo no permitido");
            resMsg.setHasError(true);
            return false;
        }

        file.setFileType(new CatFileType() {{
            setId(fileTypeId);
        }});
        return true;
    }

    @Override
    public boolean isValidExtensionByCode(MultipartFile mpf, UploadFileGeneric file, ResponseMessage resMsg, String code) {
        String filename = mpf.getOriginalFilename();
        String extension = FilenameUtils.getExtension(filename);

        if (extension == null || extension.isEmpty()) {
            resMsg.setMessage("El archivo no tiene extensión y no puede ser almacendo");
            resMsg.setHasError(true);
            return false;
        }

        extension = extension.toLowerCase();
        final Long fileTypeId = catFileTypeRepository.findByExtensionCode(extension, code);

        if (fileTypeId == null || fileTypeId.longValue() <= 0) {
            resMsg.setMessage("Tipo de archivo no permitido");
            resMsg.setHasError(true);
            return false;
        }

        file.setFileType(new CatFileType() {{
            setId(fileTypeId);
        }});
        return true;
    }

    @Override
    public boolean saveOnDiskUploadFile(MultipartFile mpf, String path, UploadFileGeneric uploadFile, ResponseMessage resMsg,
                                        SharedLogExceptionService logException, SharedUserService sharedUserService) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                if (file.mkdirs() == false) {
                    resMsg.setHasError(true);
                    resMsg.setMessage("Se presentó un problema con la ruta del archivo, por favor intente de nuevo o contacte a soporte técnico");
                    return false;
                }
            }

            File fileReal = new File(file, uploadFile.getRealFileName());
            FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(fileReal));

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "saveOnDiskUploadFile", sharedUserService);
            resMsg.setHasError(true);
            resMsg.setMessage("Se presentó un problema al momento de guardar el archivo, por favor intente de nuevo o contacte a soporte técnico");
            return false;
        }

        return true;
    }

    @Override
    public boolean doTemporalPhoto(String temporalPath, String contextPath, UploadFileGeneric originalPhoto,
                                   SharedLogExceptionService logException, SharedUserService sharedUserService) {
        try {
            File tempPath = new File(temporalPath);

            //crea la carpeta donde se almacenara la imagen si no existe
            if (!tempPath.exists()) {
                if (tempPath.mkdirs() == false) {
                    return false;
                }
            }

            File temporalFile = new File(tempPath, originalPhoto.getRealFileName());
            FileInputStream originalFile = new FileInputStream(contextPath + "\\" + originalPhoto.getPath() + "\\" + originalPhoto.getRealFileName());

            FileCopyUtils.copy(IOUtils.toByteArray(originalFile), new FileOutputStream(temporalFile));

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doTemporalPhoto", sharedUserService);
            return false;
        }

        return true;
    }

    @Override
    public void save(UploadFileGeneric uploadFile) {
        uploadFileGenericRepository.save(uploadFile);
        uploadFileGenericRepository.flush();
    }

    @Override
    public UploadFileGeneric getValidUploadFileGenericByIdAndUserId(Long userId, Long uploadFileGenericId) {
        return uploadFileGenericRepository.getValidUploadFileGenericByIdAndUserId(userId, uploadFileGenericId);
    }

    @Override
    public void deleteFile(String path, UploadFileGeneric uploadFile, User user) {
        File file = new File(path, uploadFile.getPath());
        file = new File(file, uploadFile.getRealFileName());
        file.delete();
        uploadFile.setDeleteTime(Calendar.getInstance());
        uploadFile.setDeleteUser(user);
        uploadFile.setObsolete(true);
        uploadFileGenericRepository.save(uploadFile);
        uploadFileGenericRepository.flush();
    }

    @Override
    public UploadFileGeneric getPathAndFilename(Long id) {
        UploadFileGeneric file = uploadFileGenericRepository.getPathAndFilename(id);
        return file;
    }

    @Override
    public UploadFileGeneric getPathAndFilenamePhotoByIdEmployee(Long id) {
        UploadFileGeneric file = uploadFileGenericRepository.getPathAndFilenamePhotoByIdEmployee(id);
        return file;
    }

    @Override
    public List<UploadFileGeneric> getUploadFilesByUserId(Long id) {
        return uploadFileGenericRepository.getUploadFilesByUserId(id);
    }

    @Override
    public UploadFileGeneric findOne(Long fileId) {
        return uploadFileGenericRepository.findOne(fileId);
    }
}
