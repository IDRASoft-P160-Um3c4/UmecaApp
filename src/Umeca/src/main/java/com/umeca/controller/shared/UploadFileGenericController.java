package com.umeca.controller.shared;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.model.shared.Constants;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.shared.UpDwFileGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class UploadFileGenericController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    private SharedUserService userService;

    @Autowired
    UpDwFileGenericService upDwFileGenericService;

    @RequestMapping(value = "/shared/uploadFileGeneric/doUploadFileGeneric", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUploadFileGeneric(@ModelAttribute UploadFileRequest uploadRequest,
                                        MultipartHttpServletRequest request) {

        ResponseMessage resMsg = new ResponseMessage();
        try {

            Long userId = sharedUserService.GetLoggedUserId();
            if (userService.isUserInRoles(userId, new ArrayList<String>() {{
                    add(Constants.ROLE_SUPERVISOR_MANAGER);
                    add(Constants.ROLE_EVALUATION_MANAGER);
                    add(Constants.ROLE_HUMAN_RESOURCES);
                    add(Constants.ROLE_DIRECTOR);
                    add(Constants.ROLE_CHANNELING_MANAGER);
                }}) == false) {
                resMsg.setHasError(true);
                resMsg.setMessage("Usted no tiene permisos para realizar esta acción.");
                return resMsg;
            }

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

            if (upDwFileGenericService.hasAvailability(file, resMsg, userId) == false)
                return resMsg;

            String path = request.getSession().getServletContext().getRealPath("");
            path = new File(path, file.getPath()).toString();

            if (upDwFileGenericService.saveOnDiskUploadFile(mpf, path, file, resMsg, logException, sharedUserService) == false)
                return resMsg;

            upDwFileGenericService.save(file);

            resMsg.setMessage("El archivo " + file.getFileName() + " fue subido de forma correcta. Por favor presione el botón guardar para finalizar el proceso.");
            resMsg.setHasError(false);
            if (uploadRequest.getCloseUploadFile() != null && uploadRequest.getCloseUploadFile()) {

                resMsg.setUrlToGo("close");
                resMsg.setReturnData(file.getPath() + "/" + file.getRealFileName());
            } else {
                resMsg.setReturnData(file.getId());
            }

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUploadFileGeneric", sharedUserService);
            resMsg.setHasError(true);
            resMsg.setMessage("Se present&oacute; un error inesperado. Por favor revise la informaci&oacute;n e intente de nuevo");
        }

        return resMsg;
    }

    @RequestMapping(value = "/shared/uploadFileGeneric/downloadFile", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getFile(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) {

        try {
            UploadFileGeneric realFile = upDwFileGenericService.getPathAndFilename(id);

            File finalFile = upDwFileGenericService.createDownloadableFileFromUploadedFile(realFile.getFileName(), request);
            FileOutputStream fos = new FileOutputStream(finalFile);

            byte[] buffer = new byte[1024];

            String path = request.getSession().getServletContext().getRealPath("");
            File fileIn = new File(path, realFile.getPath());
            FileInputStream in = new FileInputStream(new File(fileIn, realFile.getRealFileName()));

            int len;
            while ((len = in.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            in.close();
            fos.close();

            response.setContentType("application/force-download");
            response.setContentLength((int) finalFile.length());
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + realFile.getFileName() + "\"");//fileName);

            finalFile.deleteOnExit();
            return new FileSystemResource(finalFile);
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "downloadFileByCase", sharedUserService);
            try {
                File file = upDwFileGenericService.createDownloadableFile("errorDescarga", ".doc", request);
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("<html><body><h3>Ocurrió un error al momento de descargar el documento. Por favor intente de nuevo o contacte a soporte técnico.</h3></body></html>");
                writer.flush();
                writer.close();
                file.deleteOnExit();
                return new FileSystemResource(file);
            } catch (IOException ex) {
                logException.Write(ex, this.getClass(), "getFile", sharedUserService);
                return null;
            }
        }
    }
}
