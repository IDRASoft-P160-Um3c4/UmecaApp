package com.umeca.service.humanResources;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.DocumentDto;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface DocumentService {
    ResponseMessage upsertDocument(DocumentDto documentDto);

    DocumentDto getDocumentByDocumentId(Long id);

    ResponseMessage documentDoObsolete(Long id);

    ResponseMessage doUpsertAttachment(UploadFileRequest uploadRequest, MultipartHttpServletRequest request, SharedLogExceptionService logExceptionService);

    public void obsoleteAttachment(Long id,String path);
}
