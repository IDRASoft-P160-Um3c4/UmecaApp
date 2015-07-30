package com.umeca.service.humanResources;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.DocumentDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.minutes.Agreement;
import com.umeca.model.entities.humanReources.AgreementFileRel;
import com.umeca.model.entities.humanReources.Document;
import com.umeca.model.entities.humanReources.RelDocumentUploadGenericFile;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.humanResources.AgreementFileRelRepository;
import com.umeca.repository.humanResources.DocumentRepository;
import com.umeca.repository.humanResources.RelDocumentUploadGenericFileRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.shared.UpDwFileGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.Iterator;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UpDwFileGenericService upDwFileGenericService;
    @Autowired
    private RelDocumentUploadGenericFileRepository relDocumentUploadGenericFileRepository;
    @Autowired
    private SharedUserService sharedUserService;
    @Autowired
    private UserRepository userRepository;

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

    @Override
    @Transactional
    public ResponseMessage doUpsertAttachment(UploadFileRequest uploadRequest,
                                                 MultipartHttpServletRequest request, SharedLogExceptionService logExceptionService) {
        ResponseMessage resMsg = new ResponseMessage();

        Long userId = sharedUserService.GetLoggedUserId();

        Iterator<String> itr = request.getFileNames();

        if (upDwFileGenericService.isValidRequestFile(itr, resMsg) == false) {
            return resMsg;
        }

        UploadFileGeneric file = new UploadFileGeneric();

        MultipartFile mpf = request.getFile(itr.next());
        if (upDwFileGenericService.isValidExtension(mpf, file, resMsg) == false)
            return resMsg;

        User user = new User();
        user.setId(userId);
        upDwFileGenericService.fillUploadFileGeneric(mpf, file, uploadRequest, user);

        //valida nombre archivo
        if (upDwFileGenericService.hasAvailability(file, resMsg, userId) == false)
            return resMsg;

        String path = request.getSession().getServletContext().getRealPath("");
        path = new File(path, file.getPath()).toString();

        if (upDwFileGenericService.saveOnDiskUploadFile(mpf, path, file, resMsg, logExceptionService, sharedUserService) == false)
            return resMsg;


        Document d = new Document();
        d.setId(uploadRequest.getDocumentId());
        RelDocumentUploadGenericFile rel = new RelDocumentUploadGenericFile();
        rel.setDocument(d);
        file.setObsolete(false);
        upDwFileGenericService.save(file);
        rel.setUploadFileGeneric(file);
        relDocumentUploadGenericFileRepository.save(rel);

        resMsg.setMessage("El archivo " + file.getFileName() + " fue subido de forma correcta. Por favor presione el botón guardar para finalizar el proceso.");
        resMsg.setHasError(false);
        if (uploadRequest.getCloseUploadFile() != null && uploadRequest.getCloseUploadFile()) {

            resMsg.setUrlToGo("close");
            resMsg.setReturnData(file.getPath() + "/" + file.getRealFileName());
        } else {
            resMsg.setReturnData(file.getId());
        }

        return resMsg;
    }

    @Transactional
    public void obsoleteAttachment(Long id, String path){
        UploadFileGeneric f = upDwFileGenericService.findOne(id);
        if (f != null) {
            upDwFileGenericService.deleteFile(path, f, userRepository.findOne(sharedUserService.GetLoggedUserId()));
        }
    }

}
