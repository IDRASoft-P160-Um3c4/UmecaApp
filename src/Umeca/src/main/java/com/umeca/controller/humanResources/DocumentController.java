package com.umeca.controller.humanResources;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.dto.humanResources.DocumentDto;
import com.umeca.model.entities.humanReources.Document;
import com.umeca.model.entities.humanReources.RelDocumentUploadGenericFile;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.model.shared.SelectList;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.humanResources.DocumentService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DocumentController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private SharedLogExceptionService logException;
    @Autowired
    private SharedUserService sharedUserService;

    @RequestMapping(value = "/humanResources/document/index", method = RequestMethod.GET)
    public String index() {
        return "/humanResources/document/index";
    }

    @ResponseBody
    @RequestMapping(value = "/humanResources/document/list", method = RequestMethod.POST)
    public JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", false, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("documentDate"));
                    add(r.get("numberDocument"));
                    add(r.get("sender"));
                    add(r.get("receiver"));
                    add(r.get("criminalCause"));
                    add(r.get("subject"));
                    add(r.get("turnedOver"));
                    add(r.get("finalAction"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {

                if (field.equals("documentDate"))
                    return r.get("documentDate");
                else if (field.equals("numberDocument"))
                    return r.get("numberDocument");
                else if (field.equals("sender"))
                    return r.get("sender");
                else if (field.equals("receiver"))
                    return r.get("receiver");
                else if (field.equals("criminalCause"))
                    return r.get("criminalCause");
                else if (field.equals("subject"))
                    return r.get("subject");
                else if (field.equals("turnedOver"))
                    return r.get("turnedOver");
                else if (field.equals("isObsolete"))
                    return r.get("isObsolete");
                else
                    return null;
            }
        }, Document.class, DocumentDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/document/upsertDocument", method = RequestMethod.POST)
    public ModelAndView showUpsertJob(@RequestParam(required = false) Long id) {

        ModelAndView model = new ModelAndView("/humanResources/document/upsertDocument");
        Gson gson = new Gson();
        DocumentDto d = new DocumentDto();

        if (id != null)
            d = documentService.getDocumentByDocumentId(id);

        model.addObject("document", gson.toJson(d));

        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/humanResources/document/doUpsertDocument", method = RequestMethod.POST)
    public ResponseMessage doUpsertDocument(@ModelAttribute DocumentDto documentDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = documentService.upsertDocument(documentDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertDocument", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/humanResources/document/documentDoObsolete", method = RequestMethod.POST)
    public ResponseMessage documentDoObsolete(@RequestParam(required = true) Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = documentService.documentDoObsolete(id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "documentDoObsolete", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/document/upsertAttachment", method = RequestMethod.POST)
    public ModelAndView uploadAgreementFile(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/humanResources/document/upsertAttachment");
        model.addObject("documentId", id);
        return model;
    }

    @RequestMapping(value = "/humanResources/document/doUpsertAttachment", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUploadFileAgreement(@ModelAttribute UploadFileRequest uploadRequest,
                                          MultipartHttpServletRequest request) {
        ResponseMessage resMsg = new ResponseMessage();
        try {
            resMsg = documentService.doUpsertAttachment(uploadRequest, request, logException);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUploadFileAgreement", sharedUserService);
            resMsg.setHasError(true);
            resMsg.setMessage("Se present&oacute; un error inesperado. Por favor revise la informaci&oacute;n e intente de nuevo");
        }

        return resMsg;
    }

    @RequestMapping(value = "/humanResources/document/listAttachment", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listAgreementFiles(@RequestParam(required = true) final Long id, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel documentIdFilter = new JqGridRulesModel("documentId", id.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(documentIdFilter);
        JqGridRulesModel obsoleteFilter = new JqGridRulesModel("isObsolete",false, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(obsoleteFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<RelDocumentUploadGenericFile, UploadFileGeneric> joinUF = r.join("uploadFileGeneric");

                return new ArrayList<Selection<?>>() {{
                    add(joinUF.get("id"));
                    add(joinUF.get("fileName"));
                        add(joinUF.get("description"));
                    add(joinUF.get("creationTime"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("documentId"))
                    return r.join("document").get("id");
                if (field.equals("isObsolete"))
                    return r.join("uploadFileGeneric").get("isObsolete");
                return null;
            }
        }, RelDocumentUploadGenericFile.class, SelectList.class);

        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/humanResources/document/attachmentObsolete", method = RequestMethod.POST)
    public ResponseMessage attachmentObsolete(@RequestParam(required = true) Long id, HttpServletRequest request) {
        ResponseMessage response = new ResponseMessage();
        try {
                String path = request.getSession().getServletContext().getRealPath("");
                documentService.obsoleteAttachment(id, path);

            response.setHasError(false);
            response.setMessage("Se ha eliminado el archivo con éxito.");
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "documentDoObsolete", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }




}

