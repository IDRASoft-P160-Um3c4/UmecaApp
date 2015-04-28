package com.umeca.service.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.CatChannelingType;
import com.umeca.model.catalog.CatEconomicSupport;
import com.umeca.model.catalog.CatInstitutionType;
import com.umeca.model.catalog.District;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.Channeling;
import com.umeca.model.entities.supervisor.ChannelingModel;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.model.shared.SelectOptsList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.supervisor.ChannelingRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.List;

@Service("channelingService")
public class ChannelingServiceImpl implements ChannelingService {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private ChannelingRepository channelingRepository;


    @Override
    public ChannelingModel getChannelingInfoByCaseId(Long id) {
        ChannelingModel chCase = channelingRepository.getChannelingCaseViewByCaseId(id);
        return chCase;
    }

    @Override
    public ChannelingModel getChannelingInfoByCaseIdAndChannelingId(Long id, Long channelingId) {
        return null;
    }

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    EducationLevelRepository educationLevelRepository;

    @Autowired
    PreventionTypeRepository preventionTypeRepository;

    @Autowired
    EconomicSupportRepository economicSupportRepository;

    @Autowired
    InstitutionTypeRepository institutionTypeRepository;

    @Autowired
    ChannelingTypeRepository channelingTypeRepository;


    @Override
    public void getChannelingCatalogs(ModelAndView model) {
        Gson gson = new Gson();
        List<SelectList> lstGeneric = districtRepository.findNoObsolete();
        String sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstDistrict", sLstGeneric);

        lstGeneric = educationLevelRepository.findNotObsolete();
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstEducationLevel", sLstGeneric);

        lstGeneric = preventionTypeRepository.findNotObsolete();
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstPreventionType", sLstGeneric);

        lstGeneric = economicSupportRepository.findNotObsolete();
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstEconomicSupport", sLstGeneric);

        List<SelectOptsList> lstGenericI = institutionTypeRepository.findNotObsolete();
        sLstGeneric = gson.toJson(lstGenericI);
        model.addObject("lstInstitutionType", sLstGeneric);

        lstGenericI = channelingTypeRepository.findNotObsolete();
        sLstGeneric = gson.toJson(lstGenericI);
        model.addObject("lstChannelingType", sLstGeneric);

        return;
    }

    @Override
    public void doUpsert(final ChannelingModel modelNew, User user, ResponseMessage response) {

        //Validar el caso
        if(isValidCase(modelNew, response) == false)
            return;

        Long channelingId = modelNew.getChannelingId();
        final Channeling model;
        if(channelingId == null || channelingId <=0){
            //Insertar
            model = new Channeling();
            if( canSetInstitutionType(model, modelNew, response) == false)
                return;

            model.setCreatorUser(user);
            model.setCaseDetention(new Case(){{setId(modelNew.getCaseId());}});
            model.setChannelingType(new CatChannelingType(){{setId(modelNew.getChannelingTypeId());}});
            model.setIsObsolete(false);
            model.setDistrict(new District(){{setId(modelNew.getDistrictId());}});
            model.setCreationDate(Calendar.getInstance());
            model.setName(modelNew.getName());
            fillByChannelingType(model, modelNew, response);
            model.setConsecutive(calculateNextConsecutiveByCaseId(modelNew.getCaseId()));
        }
        else{ //Modificar
            model = channelingRepository.findOne(modelNew.getChannelingId());
            if(model == null || model.getIsObsolete() == true){
                response.setHasError(true);
                response.setMessage("El registro que desea modificar no se encuentra o ya no existe");
                return;
            }

            //Validar si no existe una actividad en el plan de monitoreo que tenga una canalización asignada


            if( canSetInstitutionType(model, modelNew, response) == false)
                return;

        }

        channelingRepository.save(model);
        response.setHasError(false);
    }

    private boolean canSetInstitutionType(Channeling model, ChannelingModel modelNew, ResponseMessage response) {
        CatInstitutionType institutionType = institutionTypeRepository.findOne(modelNew.getInstitutionTypeId());
        model.setInstitutionType(institutionType);

        if(institutionType.getHasSpec()){
            String specOther = modelNew.getSpecOther();

            if(StringExt.isNullOrWhiteSpace(specOther)){
                response.setHasError(true);
                response.setMessage("No se ha definido el campo de especificaciones");
                return false;
            }

            model.setSpecOther(specOther);
        }
        else{
            model.setSpecOther(null);
        }
        return true;
    }

    private Long calculateNextConsecutiveByCaseId(Long caseId) {
        List<Long> lastConsecutive = channelingRepository.getLastConsecutiveByCaseId(caseId, new PageRequest(0, 1));

        if(lastConsecutive == null || lastConsecutive.size() <= 0)
            return 1l;

        return lastConsecutive.get(0) + 1;
    }



    private void fillByChannelingType(final Channeling model, final ChannelingModel modelNew, ResponseMessage response) {
        String code = channelingTypeRepository.getCodeById(modelNew.getChannelingTypeId());

        model.setInstitutionName(modelNew.getInstitutionName());

        switch (code){
            case Constants.CHANNELING_TYPE_ECONOMIC_SUPPORT:
                if(isCatalogValid(modelNew.getEconomicSupportId(), response) == false)
                    return;

                model.setEconomicSupport(new CatEconomicSupport(){{setId(modelNew.getEconomicSupportId());}});
                break;
        }
    }

    private boolean isCatalogValid(Long catalogId, ResponseMessage response) {

        if(catalogId == null || catalogId <= 0)
        {
            response.setHasError(true);
            response.setMessage("Faltan campos por seleccionar");
            return false;
        }

        return true;
    }

    private boolean isValidCase(ChannelingModel modelNew, ResponseMessage response) {
        Long count = caseRepository.isReadyForChanneling(modelNew.getCaseId());
        if( count > 0 )
            return  true;

        response.setHasError(true);
        response.setMessage("No es posible agregar canalizaciones al caso debido a que no tiene entrevista de encuadre o el caso se encuentra cerrado");
        return false;
    }
}
