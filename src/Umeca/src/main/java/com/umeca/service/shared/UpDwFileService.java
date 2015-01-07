package com.umeca.service.shared;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.service.account.SharedUserService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;

public interface UpDwFileService {
    boolean isValidRequestFile(Iterator<String> itr, ResponseMessage resMsg);
    boolean hasAvailability(UploadFile itr, ResponseMessage resMsg);
    void fillUploadFile(MultipartFile mpf, UploadFile uploadFile, UploadFileRequest uploadRequest, User user);
    boolean isValidExtension(MultipartFile mpf, UploadFile file, ResponseMessage resMsg, Long typeNameId);
    boolean saveOnDiskUploadFile(MultipartFile mpf, String path, UploadFile uploadFile, ResponseMessage resMsg, SharedLogExceptionService logException, SharedUserService sharedUserService);
    void save(UploadFile uploadFile);
    UploadFile getValidUploadFileById(Long caseId, Long uploadFileId);
    void deleteFile(String path, UploadFile uploadFile, User user);
    UploadFile getPathAndFilename(Long id);
    List<UploadFile> getUploadFilesByCaseId(Long id);
    Long validateNotExistIfOnlyFile(Long typeId, Long idCase);
    Long getIdFileByCodeType(String code, Long id);
    UploadFile findOne(Long fileId);

}
