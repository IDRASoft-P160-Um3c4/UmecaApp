package com.umeca.service.reviewer;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;

import javax.xml.ws.Response;

public interface CaseService {

    Case findByIdFolder(String idFolder);
    Case generateNewCase(Imputed imputed);
    Case save(Case caseDet);

}
