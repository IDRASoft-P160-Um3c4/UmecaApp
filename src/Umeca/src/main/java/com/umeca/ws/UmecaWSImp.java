package com.umeca.ws;

import com.google.gson.Gson;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.CryptoRfc2898;
import com.umeca.model.dto.tablet.TabletCaseDto;
import com.umeca.model.dto.tablet.TabletUserDto;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UmecaWSImp implements UmecaWS{

    @Autowired
    SharedUserService sharedUserService;

    public UmecaWSImp() {
    }

    public String confirmLoginData(String user,String encodedPass){

        Gson g = new Gson();

        if(user == null || user.length()==0 || encodedPass == null || encodedPass.length()==0)
            return g.toJson(new ResponseMessage(true, "El usuario y/o password son incorrectos. Favor de verificar los datos e intente nuevamente"));

        String bdEncodedPass = sharedUserService.getCodedPassByUsername(user);
        if(bdEncodedPass == null || bdEncodedPass.length()==0)
            return g.toJson(new ResponseMessage(true, "El usuario y/o password son incorrectos. Favor de verificar los datos e intente nuevamente"));

        CryptoRfc2898 rfc2898 = new CryptoRfc2898();

        if(rfc2898.ByteArraysEqual(encodedPass.getBytes(),bdEncodedPass.getBytes())){
            ResponseMessage r = new ResponseMessage(false, "Acceso correcto");
            TabletUserDto info = new TabletUserDto();

            SelectList sl = sharedUserService.getIdAndFullnameByUsername(user);
            String roleCode = sharedUserService.getRoleByUsername(user);

            info.setId(sl.getId());
            info.setFullname(sl.getName());
            info.sethPassword(sl.getDescription());
            info.setRoleCode(roleCode);
            r.setReturnData(g.toJson(info));
            return g.toJson(r);
        }

        return g.toJson(new ResponseMessage(true, "El usuario y/o password son incorrectos. Favor de verificar los datos e intente nuevamente"));
    }

//    public ResponseMessage downloadInfoTablet(String user,String codePass){
//
//        if(user == null || user.length()==0 || codePass == null || codePass.length()==0)
//            return new ResponseMessage(true, "El usuario y/o password son incorrectos. Favor de verificar los datos e intente nuevamente");
//
////        String bdCodePass = sharedUserService.
////                CryptoRfc2898 c = new CryptoRfc2898();
//    }
//
//    public ResponseMessage synchronizeInfoTablet(){
//        ResponseMessage responseMessage = new ResponseMessage();
//        return responseMessage;
//    }

}
