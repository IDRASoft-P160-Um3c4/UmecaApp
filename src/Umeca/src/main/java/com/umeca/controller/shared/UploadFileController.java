package com.umeca.controller.shared;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.catalog.TypeNameFile;
import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.dto.CaseInfo;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.model.entities.shared.UploadFileView;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.TypeNameFileRepository;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.CaseService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.shared.UpDwFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class UploadFileController {

    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    private CaseRepository caseRepository;

    @RequestMapping(value = "/shared/uploadFile/index", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView index(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/shared/uploadFile/index");
        model.addObject("caseId", id);
        CaseInfo caseInfo = caseRepository.getInfoById(id);

        String[] arrProp = new String[]{"folderId", "personName"};
        caseInfo = (CaseInfo) StringEscape.escapeAttrs(caseInfo, arrProp);

        Long userId = userService.GetLoggedUserId();
        if (userService.isUserInRoles(userId, new ArrayList<String>() {{
            add(Constants.ROLE_SUPERVISOR);
            add(Constants.ROLE_REVIEWER);
        }}) == false) {
            model.addObject("readOnly", 1);
        } else {
            model.addObject("readOnly", 0);
        }

        if (caseInfo == null)
            return model;

        model.addObject("mpId", caseInfo.getMpId());
        model.addObject("folderId", caseInfo.getFolderId());
        model.addObject("fullname", caseInfo.getPersonName());
        return model;
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private SharedUserService userService;


    @RequestMapping(value = "/shared/uploadFile/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long caseId) {

        Long userId = userService.GetLoggedUserId();

        if (userId == null)
            return null;

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("caseId", caseId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("isObsolete", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("fileName"));
                    add(r.get("description"));
                    add(r.get("size"));
                    add(r.join("creationUser").get("fullname"));
                    add(r.get("creationTime"));
                    add(r.join("caseDetention").get("id"));
                    add(r.join("typeNameFile").get("name").alias("typeName"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("fullname"))
                    return r.join("creationUser").get("fullname");
                if (field.equals("caseId"))
                    return r.join("caseDetention").get("id");
                if (field.equals("typeName"))
                    return r.join("typeNameFile").get("name");
                return null;
            }
        }, UploadFile.class, UploadFileView.class);
        return result;
    }

    @Autowired
    TypeNameFileRepository typeNameFileRepository;

    @RequestMapping(value = "/shared/uploadFile/uploadFile", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView uploadFile(@RequestParam Long id, @RequestParam(required = false) String type) {
        ModelAndView model = new ModelAndView("/shared/uploadFile/uploadFile");
        model.addObject("caseId", id);
        CaseInfo caseInfo = caseRepository.getInfoById(id);


        String[] arrProp = new String[]{"folderId", "personName"};
        caseInfo = (CaseInfo) StringEscape.escapeAttrs(caseInfo, arrProp);

        if (caseInfo == null)
            return model;

        model.addObject("mpId", caseInfo.getMpId());
        model.addObject("folderId", caseInfo.getFolderId());
        model.addObject("fullname", caseInfo.getPersonName());
        Gson gson = new Gson();
        if (type == null) {
            List<TypeNameFile> typeNameFiles = typeNameFileRepository.findNotExistByIdCase(id, userService.GetLoggedUserId());
            List<CatalogDto> catalogDtoList = new ArrayList<>();
            for (TypeNameFile tmf : typeNameFiles) {
                catalogDtoList.add(new CatalogDto(tmf.getId(), tmf.getName(), tmf.getIsOnly(), tmf.getCode()));
            }
            model.addObject("listTypeName", gson.toJson(catalogDtoList));
        } else if (type.equals("PHOTO")) {
            TypeNameFile typePhoto = typeNameFileRepository.findByCode(Constants.CODE_FILE_IMPUTED_PHOTO);
            CatalogDto cPhoto = new CatalogDto(typePhoto.getId(), typePhoto.getName());
            model.addObject("defaultType", gson.toJson(cPhoto));
            model.addObject("closeUploadFile", true);
        }
        return model;
    }

    @Autowired
    UpDwFileService upDwFileService;

    @Autowired
    CaseService caseService;

    @RequestMapping(value = "/shared/uploadFile/doUploadFile", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUploadFile(@ModelAttribute UploadFileRequest uploadRequest,
                                 MultipartHttpServletRequest request) {
        ResponseMessage resMsg = new ResponseMessage();

        try {

            Long userId = sharedUserService.GetLoggedUserId();
            if (userService.isUserInRoles(userId, new ArrayList<String>() {{
                add(Constants.ROLE_SUPERVISOR);
                add(Constants.ROLE_REVIEWER);
            }}) == false) {
                resMsg.setHasError(true);
                resMsg.setMessage("Usted no tiene permisos para realizar esta acción.");
                return resMsg;
            }

            Iterator<String> itr = request.getFileNames();

            if (upDwFileService.isValidRequestFile(itr, resMsg) == false) {
                return resMsg;
            }

            if (caseService.isValidCase(uploadRequest.getCaseId()) == false) {
                resMsg.setHasError(true);
                resMsg.setMessage("No existe el caso al que desea subir el archivo o ya está terminado el caso.");
                return resMsg;
            }
            if (uploadRequest.getTypeId() == null) {
                resMsg.setHasError(true);
                resMsg.setMessage("Debe seleccionar un tipo de archivo para continuar");
                return resMsg;
            }


            UploadFile uploadFile = new UploadFile();

            MultipartFile mpf = request.getFile(itr.next());
            if (upDwFileService.isValidExtension(mpf, uploadFile, resMsg, uploadRequest.getTypeId()) == false)
                return resMsg;

            User user = new User();
            user.setId(userId);
            upDwFileService.fillUploadFile(mpf, uploadFile, uploadRequest, user);

            if (upDwFileService.hasAvailability(uploadFile, resMsg) == false)
                return resMsg;

            Long fileId = upDwFileService.validateNotExistIfOnlyFile(uploadRequest.getTypeId(), uploadRequest.getCaseId());
            if (fileId != null) {
                if (uploadRequest.getCloseUploadFile() != null && uploadRequest.getCloseUploadFile()) {
                    String path = request.getSession().getServletContext().getRealPath("");
                    UploadFile uf = upDwFileService.findOne(fileId);
                    upDwFileService.deleteFile(path, uf, user);
                } else {
                    resMsg.setHasError(true);
                    resMsg.setMessage("S&oacute;lo se puede agregar un archivo de este tipo,primero debe eliminar el archivo existente si desea reemplazarlo.");
                    return resMsg;
                }
            }


            String path = request.getSession().getServletContext().getRealPath("");
            path = new File(path, uploadFile.getPath()).toString();

            if (upDwFileService.saveOnDiskUploadFile(mpf, path, uploadFile, resMsg, logException, sharedUserService) == false)
                return resMsg;

            upDwFileService.save(uploadFile);

            resMsg.setMessage("El archivo " + uploadFile.getFileName() + "fue subido de forma correcta, usted puede continuar subiendo archivos, sin cerrar la ventana");
            resMsg.setHasError(false);
            if (uploadRequest.getCloseUploadFile() != null && uploadRequest.getCloseUploadFile()) {

                resMsg.setUrlToGo("close");
                resMsg.setReturnData(uploadFile.getPath() + "/" + uploadFile.getRealFileName());
            }
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUploadFile", sharedUserService);
            resMsg.setHasError(true);
            resMsg.setMessage("Se present&oacute; un error inesperado. Por favor revise la informaci&oacute;n e intente de nuevo");
        }

        return resMsg;
    }


    @RequestMapping(value = "/shared/uploadFile/deleteFile", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage deleteFile(@RequestParam String id, HttpServletRequest request) {
        ResponseMessage resMsg = new ResponseMessage();
        try {
            Long userId = sharedUserService.GetLoggedUserId();

            if (userService.isUserInRoles(userId, new ArrayList<String>() {{
                add(Constants.ROLE_SUPERVISOR);
                add(Constants.ROLE_REVIEWER);
            }}) == false) {
                resMsg.setHasError(true);
                resMsg.setMessage("Usted no tiene permisos para realizar esta acción.");
                return resMsg;
            }

            String[] arrIds = id.split("\\|");
            Long caseId = Long.parseLong(arrIds[0]);
            Long uploadFileId = Long.parseLong(arrIds[1]);

            if (caseService.isValidCase(caseId) == false) {
                resMsg.setHasError(true);
                resMsg.setMessage("No existe el caso al que desea subir el archivo o ya está terminado el caso.");
                return resMsg;
            }

            User user = new User();
            user.setId(userId);

            UploadFile uploadFile = upDwFileService.getValidUploadFileById(caseId, uploadFileId);

            if (uploadFile == null) {
                resMsg.setHasError(true);
                resMsg.setMessage("El archivo no existe o ya fue eliminado.");
                return resMsg;
            }

            String path = request.getSession().getServletContext().getRealPath("");
            upDwFileService.deleteFile(path, uploadFile, user);

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "deleteFile", sharedUserService);
            resMsg.setHasError(true);
            resMsg.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        }
        return resMsg;
    }

    @RequestMapping(value = "/shared/uploadFile/downloadFile", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getFile(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) {
        UploadFile file = upDwFileService.getPathAndFilename(id);
        String path = new File(file.getPath(), file.getRealFileName()).toString();
        File finalFile = new File(request.getSession().getServletContext().getRealPath(""), path);

        response.setContentType("application/force-download");
        response.setContentLength((int) finalFile.length());
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getFileName() + "\"");

        return new FileSystemResource(finalFile);
    }

    @RequestMapping(value = "/shared/uploadFile/downloadFileByCase", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource downloadFileByCase(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<UploadFile> lstUpFiles = upDwFileService.getUploadFilesByCaseId(id);
            if (lstUpFiles == null || lstUpFiles.size() == 0) {
                File file = new File(UUID.randomUUID().toString());
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("<html><body><h3>No existen archivos para generar el expediente.</h3></body></html>");
                writer.flush();
                return new FileSystemResource(file);
            }

            CaseInfo caseInfo = caseRepository.getInfoById(id);
            File fileOut = new File("Expediente - " + caseInfo.getPersonName() + ".zip");

            FileOutputStream fos = new FileOutputStream(fileOut);
            ZipOutputStream zos = new ZipOutputStream(fos);
            byte[] buffer = new byte[1024];

            for (UploadFile file : lstUpFiles) {

                ZipEntry ze = new ZipEntry(file.getFileName());
                zos.putNextEntry(ze);

                String path = request.getSession().getServletContext().getRealPath("");
                File fileIn = new File(path, file.getPath());
                FileInputStream in = new FileInputStream(new File(fileIn, file.getRealFileName()));

                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
            }
            zos.closeEntry();
            zos.close();

            response.setContentType("application/force-download");
            response.setContentLength((int) fileOut.length());
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileOut.getName() + "\"");

            return new FileSystemResource(fileOut);

        } catch (IOException e) {
            logException.Write(e, this.getClass(), "downloadFileByCase", sharedUserService);
            try {
                File file = new File(UUID.randomUUID().toString());
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("<html><body><h3>Ocurrió un error al momento de generar el expediente. Por favor intente de nuevo o contacte a soporte técnico.</h3></body></html>");
                writer.flush();
                return new FileSystemResource(file);
            } catch (IOException ex) {
                logException.Write(ex, this.getClass(), "downloadFileByCase", sharedUserService);
                return null;
            }
        }
    }
}
