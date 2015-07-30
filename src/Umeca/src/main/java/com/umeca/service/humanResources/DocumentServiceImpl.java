package com.umeca.service.humanResources;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.DocumentDto;
import com.umeca.model.entities.humanReources.Document;
import com.umeca.repository.humanResources.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Transactional
    public ResponseMessage upsertDocument(DocumentDto documentDto) {
        ResponseMessage resp;

        Document d = new Document();

        if (documentDto.getId() != null) {
            d = documentRepository.findOne(documentDto.getId());
        }

        d.setDocumentDate(CalendarExt.stringToCalendar(documentDto.getDocumentDate(), "yyyy/MM/dd"));
        d.setNumberDocument(documentDto.getNumberDocument());
        d.setSender(documentDto.getSender());
        d.setReceiver(documentDto.getReceiver());
        d.setCriminalCause(documentDto.getCriminalCause());
        d.setSubject(documentDto.getSubject());
        d.setTurnedOver(documentDto.getTurnedOver());
        d.setTurnedOver(documentDto.getTurnedOver());
        d.setFinalAction(documentDto.getFinalAction());
        d.setIsObsolete(false);

        documentRepository.save(d);

        resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("El empleado ha sido registrado con éxito.");
        return resp;
    }

    public DocumentDto getDocumentByDocumentId(Long id) {
        return documentRepository.getDocumentByDocumentId(id);
    }

    @Transactional
    public ResponseMessage documentDoObsolete(Long id) {
        ResponseMessage resp;

        Document d = documentRepository.findOne(id);
        d.setIsObsolete(true);
        documentRepository.save(d);

        resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("El empleado ha sido registrado con éxito.");
        return resp;
    }
}
