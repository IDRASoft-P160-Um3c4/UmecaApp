package com.umeca.service.shared;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.catalog.CrimeCatalog;
import com.umeca.model.catalog.Election;
import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.entities.reviewer.Crime;
import com.umeca.model.entities.reviewer.dto.CrimeDto;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.repository.catalog.CrimeCatalogRepository;
import com.umeca.repository.catalog.ElectionRepository;
import com.umeca.repository.reviewer.CrimeRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
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

    @Autowired
    ElectionRepository electionRepository;

    @Override
    public void fillCatalogModel(ModelAndView model) {
        Gson gson = new Gson();
        List<CatalogDto> catalogDtoList = new ArrayList<>();
        List<CrimeCatalog> crimes = crimeCatalogRepository.findNotObsolete();
        for(CrimeCatalog c: crimes){
            catalogDtoList.add(new CatalogDto(c.getId(),c.getName()));
        }
        model.addObject("optionsCrime",gson.toJson(catalogDtoList));
        List<CatalogDto> catalogDtoList1 = new ArrayList<>();
        List<Election> electionList = electionRepository.findAll();
        for(Election e: electionList){
            catalogDtoList1.add(new CatalogDto(e.getId(),e.getName()));
        }
        model.addObject("listElection",gson.toJson(catalogDtoList1));
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

    @Autowired
    HearingFormatRepository hearingFormatRepository;

    @Override
    public String getListCrimeHearingformatByCase(Long idCase) {
         Long hearingFormatId = hearingFormatRepository.lastHearingFormatIdsByIdCase(idCase);
         List<Crime> crimes = crimeRepository.findListCrimeHearingFormatByIdCase(hearingFormatId);
         if(crimes.size()>0){
             Gson gson = new Gson();
             List<CrimeDto> crimeDtoList = new ArrayList<>();
             for(Crime c: crimes){
                 crimeDtoList.add(new CrimeDto().dtoCrime(c));
             }
             return gson.toJson(crimeDtoList);
         }else
          return "[]";
    }

    @Override
    public List<Crime> getListOfString(String listCrime, HearingFormat hearingFormat) {
        Gson gson = new Gson();
        List<Crime> crimes;
        crimes = gson.fromJson(listCrime, new TypeToken<List<Crime>>(){
        }.getType());
        if(crimes!=null){
            for(Crime c : crimes){
                c.setCrime(crimeCatalogRepository.findOne(c.getCrime().getId()));
                c.setFederal(electionRepository.findOne(c.getFederal().getId()));
                c.setHearingFormat(hearingFormat);
            }
        }
        return crimes;
    }

    @Override
    public String getListCrimeHearingformatByIdFormat(Long idFormat) {
        List<Crime> crimes = crimeRepository.findListCrimeHearingFormatByIdCase(idFormat);
        if(crimes.size()>0){
            Gson gson = new Gson();
            List<CrimeDto> crimeDtoList = new ArrayList<>();
            for(Crime c: crimes){
                crimeDtoList.add(new CrimeDto().dtoCrime(c));
            }
            return gson.toJson(crimeDtoList);
        }else
            return "[]";
    }
}
