package com.umeca.service.shared;

import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.CatFileType;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.shared.SystemSetting;
import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.model.shared.Constants;
import com.umeca.repository.shared.CatFileTypeRepository;
import com.umeca.repository.shared.UploadFileRepository;
import com.umeca.service.account.SharedUserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Service
public class UpDwFileServiceImpl implements UpDwFileService{

    @Autowired
    UploadFileRepository uploadFileRepository;

    @Override
    public boolean isValidRequestFile(Iterator<String> itr, ResponseMessage resMsg) {
        if(itr.hasNext() == false){
            resMsg.setHasError(true);
            resMsg.setMessage("No existe un archivo en la solicitud, por favor revise e intente de nuevo");
            return false;
        }
        return true;
    }


    @Autowired
    SystemSettingService systemSettingsService;

    @Override
    public boolean hasAvailability(UploadFile file, ResponseMessage resMsg) {
        List<SystemSetting> lstSystemSettings = systemSettingsService.findAllOfGroup(Constants.SYSTEM_SETTINGS_ARCHIVE);
        Long caseId = file.getCaseDetention().getId();
        for(SystemSetting systemSetting : lstSystemSettings){
            switch (systemSetting.getKey()){
                case Constants.SYSTEM_SETTINGS_ARCHIVE_MAX_NUMBER_FILES:
                    Long maxNumFiles = Long.parseLong(systemSetting.getValue());
                    Long numFiles = uploadFileRepository.getNumberOfFilesByCase(caseId);
                    if(numFiles + 1 > maxNumFiles){
                        resMsg.setHasError(true);
                        resMsg.setMessage("Ha excedido el número de archivos permitidos por caso");
                        return false;
                    }

                    break;
                case Constants.SYSTEM_SETTINGS_ARCHIVE_MAX_SIZE_FILES:
                    Long maxSizeFiles = Long.parseLong(systemSetting.getValue());
                    Long sizeFilesAlreadyIn = uploadFileRepository.getSizeFilesAlreadyIn(caseId);

                    if(sizeFilesAlreadyIn == null) sizeFilesAlreadyIn = 0l;

                    if(file.getSize() + sizeFilesAlreadyIn > maxSizeFiles){
                        resMsg.setHasError(true);
                        resMsg.setMessage("Con este tamaño de archivo se sobrepasa la capacidad permitida por caso");
                        return false;
                    }
                    break;
                case Constants.SYSTEM_SETTINGS_ARCHIVE_PATH_TO_SAVE:
                    file.setPath(new File(systemSetting.getValue(), caseId.toString()).toString());
                    break;
            }
        }

        //Validar archivos con el mismo nombre
        if(uploadFileRepository.alreadyExistFileByCase(caseId, file.getFileName().toLowerCase()) > 0){
            resMsg.setHasError(true);
            resMsg.setMessage("Ya existe un archivo con ese nombre para el caso seleccionado");
            return false;
        }

        return true;
    }

    @Override
    public void fillUploadFile(MultipartFile mpf, UploadFile file, UploadFileRequest uploadRequest, User user) {
        Case caseDetention = new Case();
        caseDetention.setId(uploadRequest.getCaseId());
        file.setFileName(mpf.getOriginalFilename());
        file.setSize(mpf.getSize());
        file.setObsolete(false);
        file.setDescription(uploadRequest.getDescription());
        file.setCreationTime(Calendar.getInstance());
        file.setCaseDetention(caseDetention);
        file.setCreationUser(user);
        //Crear y guardar el nombre del archivo real
        file.setRealFileName(UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(mpf.getOriginalFilename()));
    }


    @Autowired
    CatFileTypeRepository catFileTypeRepository;

    @Override
    public boolean isValidExtension(MultipartFile mpf, UploadFile file, ResponseMessage resMsg) {
        String filename = mpf.getOriginalFilename();
        String extension = FilenameUtils.getExtension(filename);

        if(extension == null || extension.isEmpty()){
            resMsg.setMessage("El archivo no tiene extensión y no puede ser almacendo");
            resMsg.setHasError(true);
            return false;
        }

        extension = extension.toLowerCase();
        final Long fileTypeId = catFileTypeRepository.findByExtension(extension);

        if(fileTypeId == null || fileTypeId <= 0){
            resMsg.setMessage("Tipo de archivo no permitido");
            resMsg.setHasError(true);
            return false;
        }

        file.setFileType(new CatFileType(){{setId(fileTypeId);}});

        return true;
    }

    @Override
    public boolean saveOnDiskUploadFile(MultipartFile mpf, String path, UploadFile uploadFile, ResponseMessage resMsg, SharedLogExceptionService logException, SharedUserService sharedUserService) {
        try{
            File file = new File(path);
            if(!file.exists()){
                if(file.mkdirs() == false){
                    resMsg.setHasError(true);
                    resMsg.setMessage("Se presentó un problema con la ruta del archivo, por favor intente de nuevo o contacte a soporte técnico");
                    return false;
                }
            }

            File fileReal = new File(file, uploadFile.getRealFileName());
            FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(fileReal));

        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "saveOnDiskUploadFile", sharedUserService);
            resMsg.setHasError(true);
            resMsg.setMessage("Se presentó un problema al momento de guardar el archivo, por favor intente de nuevo o contacte a soporte técnico");
            return false;
        }

        return true;
    }

    @Override
    public void save(UploadFile uploadFile) {
        uploadFileRepository.save(uploadFile);
        uploadFileRepository.flush();
    }

    @Override
    public UploadFile getValidUploadFileById(Long caseId, Long uploadFileId) {
        return uploadFileRepository.getValidUploadFileById(caseId, uploadFileId);
    }

    @Override
    public void deleteFile(String path, UploadFile uploadFile, User user) {
        File file = new File(path, uploadFile.getPath());
        file = new File(file, uploadFile.getRealFileName());
        file.delete();
        uploadFile.setDeleteTime(Calendar.getInstance());
        uploadFile.setDeleteUser(user);
        uploadFile.setObsolete(true);
        uploadFileRepository.save(uploadFile);
        uploadFileRepository.flush();
    }

    @Override
    public UploadFile getPathAndFilename(Long id) {
        UploadFile file = uploadFileRepository.getPathAndFilename(id);
        return file;
    }

    @Override
    public List<UploadFile> getUploadFilesByCaseId(Long id) {
        return uploadFileRepository.getUploadFilesByCaseId(id);
    }
}
