package com.umeca.service.shared;

import com.umeca.model.entities.reviewer.Crime;
import com.umeca.model.entities.supervisor.HearingFormat;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface CrimeService {

    void fillCatalogModel(ModelAndView model);

    void getListCrimeLegalByCase(Long id, ModelAndView model);

    String getListCrimeHearingformatByCase(Long idCase);

    List<Crime> getListOfString(String listCrime, HearingFormat hearingFormat);

    String getListCrimeHearingformatByIdFormat(Long idFormat);

    public List<String> getListStringCrimeHFByHF(Long idH);


}
