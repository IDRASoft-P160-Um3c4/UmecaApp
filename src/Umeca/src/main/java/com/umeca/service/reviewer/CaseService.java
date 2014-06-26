package com.umeca.service.reviewer;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;

import javax.xml.ws.Response;

public interface CaseService {

    Case findByIdFolder(String idFolder);

    Case generateNewCase(Imputed imputed, Integer type);

    Case save(Case caseDet);
    Boolean validateStatus(Long idCase, String statusCase);
    Boolean validateStatus(Long idCase, String statusCase, Class entityCase, String statusEntity);

    ResponseMessage saveConditionaReprieveCase(Case caseDet);
}
