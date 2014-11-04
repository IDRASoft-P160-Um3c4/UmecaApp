package com.umeca.service.shared;

import org.springframework.web.servlet.ModelAndView;

public interface CrimeService {

    void fillCatalogModel(ModelAndView model);

    void getListCrimeLegalByCase(Long id, ModelAndView model);
}
