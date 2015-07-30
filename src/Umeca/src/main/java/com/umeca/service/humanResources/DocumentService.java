package com.umeca.service.humanResources;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.DocumentDto;

public interface DocumentService {
    ResponseMessage upsertDocument(DocumentDto documentDto);
    DocumentDto getDocumentByDocumentId(Long id);
    ResponseMessage documentDoObsolete(Long id);
}
