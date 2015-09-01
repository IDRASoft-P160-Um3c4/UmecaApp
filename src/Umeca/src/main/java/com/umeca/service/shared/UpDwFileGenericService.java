package com.umeca.service.shared;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.agenda.ActivityAgendaNotice;
import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.service.account.SharedUserService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;

public interface UpDwFileGenericService {
    boolean isValidRequestFile(Iterator<String> itr, ResponseMessage resMsg);

    boolean hasAvailability(UploadFileGeneric itr, ResponseMessage resMsg, Long userId);

    void fillUploadFileGeneric(MultipartFile mpf, UploadFileGeneric uploadFile, UploadFileRequest uploadRequest, User user);

    boolean isValidExtension(MultipartFile mpf, UploadFileGeneric file, ResponseMessage resMsg);

    boolean saveOnDiskUploadFile(MultipartFile mpf, String path, UploadFileGeneric uploadFile, ResponseMessage resMsg, SharedLogExceptionService logException, SharedUserService sharedUserService);

    void save(UploadFileGeneric uploadFile);

    UploadFileGeneric getValidUploadFileGenericByIdAndUserId(Long userId, Long uploadFileGenericId);

    void deleteFile(String path, UploadFileGeneric uploadFile, User user);

    UploadFileGeneric getPathAndFilename(Long id);

    List<UploadFileGeneric> getUploadFilesByUserId(Long id);

    UploadFileGeneric findOne(Long fileId);

    boolean isValidExtensionByCode(MultipartFile mpf, UploadFileGeneric file, ResponseMessage resMsg, String code);

    UploadFileGeneric getPathAndFilenamePhotoByIdEmployee(Long id);

    boolean hasPhotoAvailability(UploadFileGeneric file, ResponseMessage resMsg, Long userId, Long employeeId);

    boolean doTemporalPhoto(String temporalPath, String contextPath, UploadFileGeneric originalPhoto,
                            SharedLogExceptionService logException, SharedUserService sharedUserService);
}
