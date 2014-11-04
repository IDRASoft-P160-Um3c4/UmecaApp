package com.umeca.service.shared;


import com.google.gson.Gson;
import com.umeca.model.catalog.CrimeCatalog;
import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.entities.reviewer.Crime;
import com.umeca.model.entities.reviewer.dto.CrimeDto;
import com.umeca.repository.catalog.CrimeCatalogRepository;
import com.umeca.repository.reviewer.CrimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrimeServiceImpl implements CrimeService {

    @Autowired
    CrimeRepository crimeRepository;

    @Autowired
    CrimeCatalogRepository crimeCatalogRepository;

    @Override
    public void fillCatalogModel(ModelAndView model) {
        Gson gson = new Gson();
        List<CatalogDto> catalogDtoList = new ArrayList<>();
        List<CrimeCatalog> crimes = crimeCatalogRepository.findNotObsolete();
        for(CrimeCatalog c: crimes){
            catalogDtoList.add(new CatalogDto(c.getId(),c.getName()));
        }
        model.addObject("optionsCrime",gson.toJson(catalogDtoList));
    }

    @Override
    public void getListCrimeLegalByCase(Long id, ModelAndView model) {
        Gson gson = new Gson();
        List<Crime> listCrime = crimeRepository.findListCrimeLegalByIdCase(id);
        List<CrimeDto> listCrimeDto = new ArrayList<>();
        if (listCrime != null && listCrime.size()>0) {
            for (Crime crime : listCrime) {
                listCrimeDto.add(new CrimeDto().dtoCrime(crime));
            }
            model.addObject("listCrime", gson.toJson(listCrimeDto));
        }
    }
}
