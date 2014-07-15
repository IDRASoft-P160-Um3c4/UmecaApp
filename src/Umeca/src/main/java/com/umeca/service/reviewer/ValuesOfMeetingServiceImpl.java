package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.*;
import com.umeca.model.catalog.dto.*;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.ChoiceView;
import com.umeca.model.entities.reviewer.View.SearchToChoiceIds;
import com.umeca.model.entities.reviewer.dto.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.FieldMeetingSourceRepository;
import com.umeca.repository.reviewer.SourceVerificationRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 18/06/14
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("valuesOfMeetingService")
public class ValuesOfMeetingServiceImpl implements ValuesOfMeetingService {

    @Autowired
    ScheduleService scheduleService;

    @Override
    public List<FieldMeetingSource> getValueOfMeetingByCode(String code, Meeting m, FieldMeetingSource template) {
        List<FieldMeetingSource> listFMS = new ArrayList<>();
        String[] name = code.split("\\.");
        Gson gson = new Gson();
        switch (name[0]){
            case "imputed":
                Imputed imputed = m.getImputed();
                CatalogDto cdto = new CatalogDto();
                switch (name[1]){
                    case "name":
                        listFMS.add(new FieldMeetingSource(imputed.getName(), imputed.getName()));
                        break;
                    case "lastNameP":
                        listFMS.add(new FieldMeetingSource(imputed.getLastNameP(),imputed.getLastNameP()));
                        break;
                    case "lastNameM":
                        listFMS.add(new FieldMeetingSource(imputed.getLastNameM(), imputed.getLastNameM()));
                        break;
                    case "birthDate":
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        listFMS.add(new FieldMeetingSource(formatter.format(imputed.getBirthDate()),String.valueOf(imputed.getBirthDate())));
                        break;
                    case "gender":
                        Boolean gender =imputed.getGender();
                        String genderString;
                        if (gender.equals(Constants.GENDER_FEMALE))
                            genderString = "Femenino";
                        else
                            genderString = "Masculino";
                        listFMS.add(new FieldMeetingSource(genderString, m.getImputed().getGender().toString()));
                        break;
                    case "celPhone":
                        listFMS.add(new FieldMeetingSource(imputed.getCelPhone(),imputed.getCelPhone()));
                        break;
                    case "maritalStatus":
                        cdto.setName(imputed.getMaritalStatus().getName());
                        cdto.setId(imputed.getMaritalStatus().getId());
                        listFMS.add(new FieldMeetingSource(cdto.getName(), gson.toJson(cdto)));
                        break;
                    case "yearsMaritalStatus":
                        listFMS.add(new FieldMeetingSource(imputed.getYearsMaritalStatus().toString(), imputed.getYearsMaritalStatus().toString()));
                        break;
                    case "boys":
                        listFMS.add(new FieldMeetingSource(imputed.getBoys().toString(), imputed.getBoys().toString()));
                        break;
                    case "dependentBoys":
                        listFMS.add(new FieldMeetingSource(imputed.getDependentBoys().toString(), imputed.getDependentBoys().toString()));
                        break;
                    case "birthCountry":
                        cdto.setName(imputed.getBirthCountry().getName());
                        cdto.setId(imputed.getBirthCountry().getId());
                        listFMS.add(new FieldMeetingSource(cdto.getName(), gson.toJson(cdto)));
                        break;
                    case "birthState":
                        listFMS.add(new FieldMeetingSource(imputed.getBirthState(), imputed.getBirthState()));
                        break;
                    case "birthMunicipality":
                        listFMS.add(new FieldMeetingSource(imputed.getBirthMunicipality(), imputed.getBirthMunicipality()));
                        break;
                    case "birthLocation":
                        listFMS.add(new FieldMeetingSource(imputed.getBirthLocation(), imputed.getBirthLocation()));
                        break;
                }
                break;
            case "socialEnvironment":
                SocialEnvironment se = m.getSocialEnvironment();
                switch (name[1]){
                    case "physicalCondition":
                        listFMS.add(new FieldMeetingSource(se.getPhysicalCondition(), se.getPhysicalCondition()));
                        break;
                    case "comment":
                        if(se.getComment()!=null && !se.getComment().equals(""))
                            listFMS.add(new FieldMeetingSource(se.getComment(), se.getComment()));
                        break;
                }
                break;
            case "imputedHomes":
                for(ImputedHome ih: m.getImputedHomes()){
                    CatalogDto cDto = new CatalogDto();
                    switch(name[1]){
                        case "address":
                            AddressDto adDto = new AddressDto();
                            adDto.addressDto(ih.getAddress());
                            listFMS.add(new FieldMeetingSource(ih.getAddress().getAddressString(), gson.toJson(adDto),ih.getId()));
                            break;
                        case "belong":
                            cDto.setId(ih.getBelong().getId());
                            cDto.setName(ih.getBelong().getName());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto),ih.getId()));
                            break;
                        case "registerType":
                            cDto.setId(ih.getRegisterType().getId());
                            cDto.setName(ih.getRegisterType().getName());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto),ih.getId()));
                            break;
                        case "timeLive":
                            listFMS.add(new FieldMeetingSource(ih.getTimeLive(), ih.getTimeLive(),ih.getId()));
                            break;
                        case "reasonChange":
                            if(ih.getReasonChange()!=null && !ih.getReasonChange().equals(""))
                                listFMS.add(new FieldMeetingSource(ih.getReasonChange(), ih.getReasonChange(),ih.getId()));
                            break;
                        case "description":
                            if(ih.getDescription()!=null && !ih.getDescription().equals(""))
                                listFMS.add(new FieldMeetingSource(ih.getDescription(), ih.getDescription(),ih.getId()));
                            break;
                        case "schedule":
                            String s = (String)scheduleService.getSchedules(ih.getId(), ImputedHome.class);
                            if(!s.equals("[]")){
                                listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(ih.getId(),ImputedHome.class),s,ih.getId()));
                            }
                            break;
                    }
                }
                break;
            case "socialNetwork":
                for(PersonSocialNetwork psn: m.getSocialNetwork().getPeopleSocialNetwork()){
                    CatalogDto cDto=  new CatalogDto();
                    switch (name[1]){
                        case "name":
                            listFMS.add(new FieldMeetingSource(psn.getName(), psn.getName(),psn.getId()));
                            break;
                        case "relationship":
                            cDto.setName(psn.getRelationship().getName());
                            cDto.setId(psn.getRelationship().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),psn.getId()));
                            break;
                        case "phone":
                            listFMS.add(new FieldMeetingSource(psn.getPhone(), psn.getPhone(),psn.getId()));
                            break;
                        case "documentType":
                            cDto.setName(psn.getDocumentType().getName());
                            cDto.setId(psn.getDocumentType().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto),psn.getId()));
                            break;
                        case "specification":
                            if(psn.getSpecification()!= null && !psn.getSpecification().equals("")){
                                listFMS.add(new FieldMeetingSource(psn.getSpecification(), psn.getSpecification(),psn.getId()));
                            }
                            break;
                        case "age":
                            listFMS.add(new FieldMeetingSource(psn.getAge().toString(), psn.getAge().toString(),psn.getId()));
                            break;
                        case "dependent":
                            cDto.setName(psn.getDependent().getName());
                            cDto.setId(psn.getDependent().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),psn.getId()));
                            break;
                        case "livingWith":
                            cDto.setName(psn.getLivingWith().getName());
                            cDto.setId(psn.getLivingWith().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),psn.getId()));
                            break;
                        case "address":
                            listFMS.add(new FieldMeetingSource(psn.getAddress(), psn.getAddress(),psn.getId()));
                            break;
                    }
                }
                break;
            case "references":
                for(Reference r: m.getReferences()){
                    CatalogDto cDto=  new CatalogDto();
                    switch (name[1]){
                        case "fullName":
                            listFMS.add(new FieldMeetingSource(r.getFullName(), r.getFullName(),r.getId()));
                            break;
                        case "relationship":
                            cDto.setName(r.getRelationship().getName());
                            cDto.setId(r.getRelationship().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),r.getId()));
                            break;
                        case "phone":
                            listFMS.add(new FieldMeetingSource(r.getPhone(), r.getPhone(),r.getId()));
                            break;
                        case "documentType":
                            cDto.setName(r.getDocumentType().getName());
                            cDto.setId(r.getDocumentType().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto),r.getId()));
                            break;
                        case "specification":
                            if(r.getSpecification()!= null && !r.getSpecification().equals("")){
                                listFMS.add(new FieldMeetingSource(r.getSpecification(), r.getSpecification(),r.getId()));
                            }
                            break;
                        case "age":
                            listFMS.add(new FieldMeetingSource(r.getAge().toString(), r.getAge().toString(),r.getId()));
                            break;
                        case "address":
                            listFMS.add(new FieldMeetingSource(r.getAddress(), r.getAddress(),r.getId()));
                            break;
                    }
                }
                break;
            case "jobs":
                for(Job j: m.getJobs()){
                    CatalogDto cDto=  new CatalogDto();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    switch (name[1]){
                        case "company":
                            listFMS.add(new FieldMeetingSource(j.getCompany(),j.getCompany(),j.getId()));
                            break;
                        case "post":
                            listFMS.add(new FieldMeetingSource(j.getPost(), j.getPost(),j.getId()));
                            break;
                        case "nameHead":
                            listFMS.add(new FieldMeetingSource(j.getNameHead(), j.getNameHead(),j.getId()));
                            break;
                        case "phone":
                            listFMS.add(new FieldMeetingSource(j.getPhone(), j.getPhone(),j.getId()));
                            break;
                        case "address":
                            listFMS.add(new FieldMeetingSource(j.getAddress(), j.getAddress(),j.getId()));
                            break;
                        case "registerType":
                            cDto.setId(j.getRegisterType().getId());
                            cDto.setName(j.getRegisterType().getName());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),j.getId()));
                            break;
                        case "start":
                            if(!j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(formatter.format(j.getStart()),String.valueOf(j.getStart()),j.getId()));
                            break;
                        case "salaryWeek":
                            if(!j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(j.getSalaryWeek().toString(), j.getSalaryWeek().toString(),j.getId()));
                            break;
                        case "startPrev":
                            if(j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(formatter.format(j.getStartPrev()),String.valueOf(j.getStartPrev()),j.getId()));
                            break;
                        case "end":
                            if(j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(formatter.format(j.getEnd()),String.valueOf(j.getEnd()),j.getId()));
                            break;
                        case "reasonChange":
                            if(j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(j.getReasonChange(), j.getReasonChange(),j.getId()));
                            break;
                        case "schedule":
                            String s = (String)scheduleService.getSchedules(j.getId(), Job.class);
                            if(!s.equals("[]")){
                                listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(j.getId(),Job.class),s,j.getId()));
                            }
                            break;
                    }
                }
                break;
            case "school":
                School s = m.getSchool();
                switch (name[1]){
                    case "name":
                        listFMS.add(new FieldMeetingSource(s.getName(), s.getName()));
                        break;
                    case "phone":
                        listFMS.add(new FieldMeetingSource(s.getPhone(), s.getPhone()));
                        break;
                    case "address":
                        listFMS.add(new FieldMeetingSource(s.getAddress(), s.getAddress()));
                        break;
                    case "degree":
                        String level = "Nivel: "+s.getDegree().getAcademicLevel().getName()+" Grado: "+s.getDegree().getName();
                        listFMS.add(new FieldMeetingSource(level, gson.toJson(new DegreeDto().dtoGrade(s.getDegree()))));
                        break;
                    case "schedule":
                        String sc = (String)scheduleService.getSchedules(s.getId(), School.class);
                        if(!s.equals("[]")){
                            listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(s.getId(),School.class),sc));
                        }
                        break;
                }
                break;
            case "drugs":
                for(Drug d: m.getDrugs()){
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    CatalogDto cdtod = new CatalogDto();
                    switch (name[1]){
                        case "drugType":
                            cdtod.setName(d.getDrugType().getName());
                            cdtod.setId(d.getDrugType().getId());
                            listFMS.add(new FieldMeetingSource(cdtod.getName(), gson.toJson(cdtod),d.getId()));
                            break;
                        case "specificationType":
                            if(d.getSpecificationType()!=null && !d.getSpecificationType().equals(""))
                                listFMS.add(new FieldMeetingSource(d.getSpecificationType(), d.getSpecificationType(),d.getId()));
                            break;
                        case "periodicity":
                            cdtod.setName(d.getPeriodicity().getName());
                            cdtod.setId(d.getPeriodicity().getId());
                            listFMS.add(new FieldMeetingSource(cdtod.getName(), gson.toJson(cdtod),d.getId()));
                            break;
                        case "specificationPeriodicity":
                            if(d.getSpecificationPeriodicity()!=null && !d.getSpecificationPeriodicity().equals("")){
                                listFMS.add(new FieldMeetingSource(d.getSpecificationPeriodicity(), d.getSpecificationPeriodicity(),d.getId()));
                            }
                            break;
                        case "quantity":
                            listFMS.add(new FieldMeetingSource(d.getQuantity(),d.getQuantity(),d.getId()));
                            break;
                        case "lastUse":
                            listFMS.add(new FieldMeetingSource(formatter.format(d.getLastUse()),String.valueOf(d.getLastUse()),d.getId()));
                            break;
                    }
                }
                break;
            case "leaveCountry":
                LeaveCountry l=m.getLeaveCountry();
                CatalogDto cdtol = new CatalogDto();
                switch (name[1]){
                    case "officialDocumentation":
                        cdtol.setName(l.getOfficialDocumentation().getName());
                        cdtol.setId(l.getOfficialDocumentation().getId());
                        listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        break;
                    case "livedCountry":
                        cdtol.setName(l.getLivedCountry().getName());
                        cdtol.setId(l.getLivedCountry().getId());
                        listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        break;
                    case "country":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            cdtol.setId(l.getCountry().getId());
                            cdtol.setName(l.getCountry().getName());
                            listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        }
                        break;
                    case "state":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            listFMS.add(new FieldMeetingSource(l.getState(), l.getState()));
                        }
                        break;
                    case "timeAgo":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            listFMS.add(new FieldMeetingSource(l.getTimeAgo(), l.getTimeAgo()));
                        }
                        break;
                    case "reason":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            listFMS.add(new FieldMeetingSource(l.getReason(), l.getReason()));
                        }
                        break;
                    case "address":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            listFMS.add(new FieldMeetingSource(l.getAddress(),l.getAddress()));
                        }
                        break;
                    case "familyAnotherCountry":
                        cdtol.setName(l.getFamilyAnotherCountry().getName());
                        cdtol.setId(l.getFamilyAnotherCountry().getId());
                        listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        break;
                    case "communicationFamily":
                        if(l.getFamilyAnotherCountry().getId().equals(Constants.ELECTION_YES)){
                            cdtol.setName(l.getCommunicationFamily().getName());
                            cdtol.setId(l.getCommunicationFamily().getId());
                            listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        }
                        break;
                    case "media":
                        if(l.getMedia()!=null && !l.getMedia().equals("")){
                            listFMS.add(new FieldMeetingSource(l.getMedia(),l.getMedia()));
                        }
                        break;
                }
                break;
        }
        for(FieldMeetingSource fms: listFMS){
            fms.setFieldVerification(template.getFieldVerification());
            fms.setSourceVerification(template.getSourceVerification());
            fms.setStatusFieldVerification(template.getStatusFieldVerification());
            fms.setFinal(false);
        }
        return listFMS;
    }

    @Override
    public List<FieldMeetingSource> getValueByCode(String code, Meeting m, FieldMeetingSource template, Long idList) {

        List<FieldMeetingSource> listFMS = new ArrayList<>();
        String[] name = code.split("\\.");
        Gson gson = new Gson();
        switch (name[0]){
            case "imputed":
                Imputed imputed = m.getImputed();
                CatalogDto cdto = new CatalogDto();
                switch (name[1]){
                    case "name":
                        listFMS.add(new FieldMeetingSource(imputed.getName(), imputed.getName()));
                        break;
                    case "lastNameP":
                        listFMS.add(new FieldMeetingSource(imputed.getLastNameP(),imputed.getLastNameP()));
                        break;
                    case "lastNameM":
                        listFMS.add(new FieldMeetingSource(imputed.getLastNameM(), imputed.getLastNameM()));
                        break;
                    case "birthDate":
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        listFMS.add(new FieldMeetingSource(formatter.format(imputed.getBirthDate()),String.valueOf(imputed.getBirthDate())));
                        break;
                    case "gender":
                        Boolean gender =imputed.getGender();
                        String genderString;
                        if (gender.equals(Constants.GENDER_FEMALE))
                            genderString = "Femenino";
                        else
                            genderString = "Masculino";
                        listFMS.add(new FieldMeetingSource(genderString, m.getImputed().getGender().toString()));
                        break;
                    case "celPhone":
                        listFMS.add(new FieldMeetingSource(imputed.getCelPhone(),imputed.getCelPhone()));
                        break;
                    case "maritalStatus":
                        cdto.setName(imputed.getMaritalStatus().getName());
                        cdto.setId(imputed.getMaritalStatus().getId());
                        listFMS.add(new FieldMeetingSource(cdto.getName(), gson.toJson(cdto)));
                        break;
                    case "yearsMartialStatus":
                        listFMS.add(new FieldMeetingSource(imputed.getYearsMaritalStatus().toString(), imputed.getYearsMaritalStatus().toString()));
                        break;
                    case "boys":
                        listFMS.add(new FieldMeetingSource(imputed.getBoys().toString(), imputed.getBoys().toString()));
                        break;
                    case "dependentBoys":
                        listFMS.add(new FieldMeetingSource(imputed.getDependentBoys().toString(), imputed.getDependentBoys().toString()));
                        break;
                    case "birthCountry":
                        cdto.setName(imputed.getBirthCountry().getName());
                        cdto.setId(imputed.getBirthCountry().getId());
                        listFMS.add(new FieldMeetingSource(cdto.getName(), gson.toJson(cdto)));
                        break;
                    case "birthState":
                        listFMS.add(new FieldMeetingSource(imputed.getBirthState(), imputed.getBirthState()));
                        break;
                    case "birthMunicipality":
                        listFMS.add(new FieldMeetingSource(imputed.getBirthMunicipality(), imputed.getBirthMunicipality()));
                        break;
                    case "birthLocation":
                        listFMS.add(new FieldMeetingSource(imputed.getBirthLocation(), imputed.getBirthLocation()));
                        break;
                }
                break;
            case "socialEnvironment":
                SocialEnvironment se = m.getSocialEnvironment();
                switch (name[1]){
                    case "physicalCondition":
                        listFMS.add(new FieldMeetingSource(se.getPhysicalCondition(), se.getPhysicalCondition()));
                        break;
                    case "comment":
                        if(se.getComment()!=null && !se.getComment().equals(""))
                            listFMS.add(new FieldMeetingSource(se.getComment(), se.getComment()));
                        break;
                }
                break;
            case "imputedHomes":
                for(ImputedHome ih: m.getImputedHomes()){
                    CatalogDto cDto = new CatalogDto();
                    if(ih.getId().equals(idList)){
                    switch(name[1]){
                        case "address":
                            AddressDto adDto = new AddressDto();
                            adDto.addressDto(ih.getAddress());
                            listFMS.add(new FieldMeetingSource(ih.getAddress().getAddressString(), gson.toJson(adDto),ih.getId()));
                            break;
                        case "belong":
                            cDto.setId(ih.getBelong().getId());
                            cDto.setName(ih.getBelong().getName());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto),ih.getId()));
                            break;
                        case "registerType":
                            cDto.setId(ih.getRegisterType().getId());
                            cDto.setName(ih.getRegisterType().getName());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto),ih.getId()));
                            break;
                        case "timeLive":
                            listFMS.add(new FieldMeetingSource(ih.getTimeLive(), ih.getTimeLive(),ih.getId()));
                            break;
                        case "reasonChange":
                            if(ih.getReasonChange()!=null && !ih.getReasonChange().equals(""))
                                listFMS.add(new FieldMeetingSource(ih.getReasonChange(), ih.getReasonChange(),ih.getId()));
                            break;
                        case "description":
                            if(ih.getDescription()!=null && !ih.getDescription().equals(""))
                                listFMS.add(new FieldMeetingSource(ih.getDescription(), ih.getDescription(),ih.getId()));
                            break;
                        case "schedule":
                            String s = (String)scheduleService.getSchedules(ih.getId(), ImputedHome.class);
                            if(!s.equals("[]")){
                                listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(ih.getId(),ImputedHome.class),s,ih.getId()));
                            }
                            break;
                    }
                        break;
                    }

                }
                break;
            case "socialNetwork":
                for(PersonSocialNetwork psn: m.getSocialNetwork().getPeopleSocialNetwork()){
                    if(psn.getId().equals(idList)){
                    CatalogDto cDto=  new CatalogDto();
                    switch (name[1]){
                        case "name":
                            listFMS.add(new FieldMeetingSource(psn.getName(), psn.getName(),psn.getId()));
                            break;
                        case "relationship":
                            cDto.setName(psn.getRelationship().getName());
                            cDto.setId(psn.getRelationship().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),psn.getId()));
                            break;
                        case "phone":
                            listFMS.add(new FieldMeetingSource(psn.getPhone(), psn.getPhone(),psn.getId()));
                            break;
                        case "documentType":
                            cDto.setName(psn.getDocumentType().getName());
                            cDto.setId(psn.getDocumentType().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto),psn.getId()));
                            break;
                        case "specification":
                            if(psn.getSpecification()!= null && !psn.getSpecification().equals("")){
                                listFMS.add(new FieldMeetingSource(psn.getSpecification(), psn.getSpecification(),psn.getId()));
                            }
                            break;
                        case "age":
                            listFMS.add(new FieldMeetingSource(psn.getAge().toString(), psn.getAge().toString(),psn.getId()));
                            break;
                        case "dependent":
                            cDto.setName(psn.getDependent().getName());
                            cDto.setId(psn.getDependent().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),psn.getId()));
                            break;
                        case "livingWith":
                            cDto.setName(psn.getLivingWith().getName());
                            cDto.setId(psn.getLivingWith().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),psn.getId()));
                            break;
                        case "address":
                            listFMS.add(new FieldMeetingSource(psn.getAddress(), psn.getAddress(),psn.getId()));
                            break;
                    }
                        break;
                    }

                }
                break;
            case "references":
                for(Reference r: m.getReferences()){
                    if(r.getId().equals(idList)){
                    CatalogDto cDto=  new CatalogDto();
                    switch (name[1]){
                        case "fullName":
                            listFMS.add(new FieldMeetingSource(r.getFullName(), r.getFullName(),r.getId()));
                            break;
                        case "relationship":
                            cDto.setName(r.getRelationship().getName());
                            cDto.setId(r.getRelationship().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),r.getId()));
                            break;
                        case "phone":
                            listFMS.add(new FieldMeetingSource(r.getPhone(), r.getPhone(),r.getId()));
                            break;
                        case "documentType":
                            cDto.setName(r.getDocumentType().getName());
                            cDto.setId(r.getDocumentType().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto),r.getId()));
                            break;
                        case "specification":
                            if(r.getSpecification()!= null && !r.getSpecification().equals("")){
                                listFMS.add(new FieldMeetingSource(r.getSpecification(), r.getSpecification(),r.getId()));
                            }
                            break;
                        case "age":
                            listFMS.add(new FieldMeetingSource(r.getAge().toString(), r.getAge().toString(),r.getId()));
                            break;
                        case "address":
                            listFMS.add(new FieldMeetingSource(r.getAddress(), r.getAddress(),r.getId()));
                            break;
                    }
                        break;
                    }
                }
                break;
            case "jobs":
                for(Job j: m.getJobs()){
                    if(j.getId().equals(idList)){
                    CatalogDto cDto=  new CatalogDto();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    switch (name[1]){
                        case "company":
                            listFMS.add(new FieldMeetingSource(j.getCompany(),j.getCompany(),j.getId()));
                            break;
                        case "post":
                            listFMS.add(new FieldMeetingSource(j.getPost(), j.getPost(),j.getId()));
                            break;
                        case "nameHead":
                            listFMS.add(new FieldMeetingSource(j.getNameHead(), j.getNameHead(),j.getId()));
                            break;
                        case "phone":
                            listFMS.add(new FieldMeetingSource(j.getPhone(), j.getPhone(),j.getId()));
                            break;
                        case "address":
                            listFMS.add(new FieldMeetingSource(j.getAddress(), j.getAddress(),j.getId()));
                            break;
                        case "registerType":
                            cDto.setId(j.getRegisterType().getId());
                            cDto.setName(j.getRegisterType().getName());
                            listFMS.add(new FieldMeetingSource(cDto.getName(),gson.toJson(cDto),j.getId()));
                            break;
                        case "start":
                            if(!j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(formatter.format(j.getStart()),String.valueOf(j.getStart()),j.getId()));
                            break;
                        case "salaryWeek":
                            if(!j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(j.getSalaryWeek().toString(), j.getSalaryWeek().toString(),j.getId()));
                            break;
                        case "startPrev":
                            if(j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(formatter.format(j.getStartPrev()),String.valueOf(j.getStartPrev()),j.getId()));
                            break;
                        case "end":
                            if(j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(formatter.format(j.getEnd()),String.valueOf(j.getEnd()),j.getId()));
                            break;
                        case "reasonChange":
                            if(j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(j.getReasonChange(), j.getReasonChange(),j.getId()));
                            break;
                        case "schedule":
                            String s = (String)scheduleService.getSchedules(j.getId(), Job.class);
                            if(!s.equals("[]")){
                                listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(j.getId(),Job.class),s,j.getId()));
                            }
                            break;
                    }
                        break;
                    }
                }
                break;
            case "school":
                School s = m.getSchool();
                switch (name[1]){
                    case "name":
                        listFMS.add(new FieldMeetingSource(s.getName(), s.getName()));
                        break;
                    case "phone":
                        listFMS.add(new FieldMeetingSource(s.getPhone(), s.getPhone()));
                        break;
                    case "address":
                        listFMS.add(new FieldMeetingSource(s.getAddress(), s.getAddress()));
                        break;
                    case "degree":
                        String level = "Nivel: "+s.getDegree().getAcademicLevel().getName()+" Grado: "+s.getDegree().getName();
                        listFMS.add(new FieldMeetingSource(level, gson.toJson(new DegreeDto().dtoGrade(s.getDegree()))));
                        break;
                    case "schedule":
                        String sc = (String)scheduleService.getSchedules(s.getId(), School.class);
                        if(!s.equals("[]")){
                            listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(s.getId(),School.class),sc));
                        }
                        break;
                }
                break;
            case "drugs":
                for(Drug d: m.getDrugs()){
                    if(d.getId().equals(idList)){
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    CatalogDto cdtod = new CatalogDto();
                    switch (name[1]){
                        case "drugType":
                            cdtod.setName(d.getDrugType().getName());
                            cdtod.setId(d.getDrugType().getId());
                            listFMS.add(new FieldMeetingSource(cdtod.getName(), gson.toJson(cdtod),d.getId()));
                            break;
                        case "specificationType":
                            if(d.getSpecificationType()!=null && !d.getSpecificationType().equals(""))
                                listFMS.add(new FieldMeetingSource(d.getSpecificationType(), d.getSpecificationType(),d.getId()));
                            break;
                        case "periodicity":
                            cdtod.setName(d.getPeriodicity().getName());
                            cdtod.setId(d.getPeriodicity().getId());
                            listFMS.add(new FieldMeetingSource(cdtod.getName(), gson.toJson(cdtod),d.getId()));
                            break;
                        case "specificationPeriodicity":
                            if(d.getSpecificationPeriodicity()!=null && !d.getSpecificationPeriodicity().equals("")){
                                listFMS.add(new FieldMeetingSource(d.getSpecificationPeriodicity(), d.getSpecificationPeriodicity(),d.getId()));
                            }
                            break;
                        case "quantity":
                            listFMS.add(new FieldMeetingSource(d.getQuantity(),d.getQuantity(),d.getId()));
                            break;
                        case "lastUse":
                            listFMS.add(new FieldMeetingSource(formatter.format(d.getLastUse()),String.valueOf(d.getLastUse()),d.getId()));
                            break;
                    }
                        break;
                    }
                }
                break;
            case "leaveCountry":
                LeaveCountry l=m.getLeaveCountry();
                CatalogDto cdtol = new CatalogDto();
                switch (name[1]){
                    case "officialDocumentation":
                        cdtol.setName(l.getOfficialDocumentation().getName());
                        cdtol.setId(l.getOfficialDocumentation().getId());
                        listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        break;
                    case "livedCountry":
                        cdtol.setName(l.getLivedCountry().getName());
                        cdtol.setId(l.getLivedCountry().getId());
                        listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        break;
                    case "country":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            cdtol.setId(l.getCountry().getId());
                            cdtol.setName(l.getCountry().getName());
                            listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        }
                        break;
                    case "state":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            listFMS.add(new FieldMeetingSource(l.getState(), l.getState()));
                        }
                        break;
                    case "timeAgo":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            listFMS.add(new FieldMeetingSource(l.getTimeAgo(), l.getTimeAgo()));
                        }
                        break;
                    case "reason":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            listFMS.add(new FieldMeetingSource(l.getReason(), l.getReason()));
                        }
                        break;
                    case "address":
                        if(l.getLivedCountry().getId().equals(Constants.ELECTION_YES)){
                            listFMS.add(new FieldMeetingSource(l.getAddress(),l.getAddress()));
                        }
                        break;
                    case "familyAnotherCountry":
                        cdtol.setName(l.getFamilyAnotherCountry().getName());
                        cdtol.setId(l.getFamilyAnotherCountry().getId());
                        listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        break;
                    case "communicationFamily":
                        if(l.getFamilyAnotherCountry().getId().equals(Constants.ELECTION_YES)){
                            cdtol.setName(l.getCommunicationFamily().getName());
                            cdtol.setId(l.getCommunicationFamily().getId());
                            listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        }
                        break;
                    case "media":
                        if(l.getMedia()!=null && !l.getMedia().equals("")){
                            listFMS.add(new FieldMeetingSource(l.getMedia(),l.getMedia()));
                        }
                        break;
                }
                break;
        }
        for(FieldMeetingSource fms: listFMS){
            fms.setFieldVerification(template.getFieldVerification());
            fms.setSourceVerification(template.getSourceVerification());
            fms.setStatusFieldVerification(template.getStatusFieldVerification());
            fms.setFinal(false);
        }
        return listFMS;
    }
}
