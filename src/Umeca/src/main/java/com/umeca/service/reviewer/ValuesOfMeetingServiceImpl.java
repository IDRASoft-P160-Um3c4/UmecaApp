package com.umeca.service.reviewer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.catalog.*;
import com.umeca.model.catalog.dto.AddressDto;
import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.catalog.dto.DegreeDto;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.FieldMeetingSourceRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    InformationAvailabilityRepository informationAvailabilityRepository;

    @Override
    public List<FieldMeetingSource> getValueOfMeetingByCode(String code, Meeting m, FieldMeetingSource template) {
        List<FieldMeetingSource> listFMS = new ArrayList<>();
        String[] name = code.split("\\.");
        Gson gson = new Gson();
        switch (name[0]) {
            case "imputed":
                Imputed imputed = m.getImputed();
                CatalogDto cdto = new CatalogDto();
                switch (name[1]) {
                    case "name":
                        listFMS.add(new FieldMeetingSource(imputed.getName(), imputed.getName()));
                        break;
                    case "lastNameP":
                        listFMS.add(new FieldMeetingSource(imputed.getLastNameP(), imputed.getLastNameP()));
                        break;
                    case "lastNameM":
                        listFMS.add(new FieldMeetingSource(imputed.getLastNameM(), imputed.getLastNameM()));
                        break;
                    case "nickname":
                        listFMS.add(new FieldMeetingSource(imputed.getNickname(), imputed.getNickname()));
                        break;
                    case "birthDate":
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        listFMS.add(new FieldMeetingSource(formatter.format(imputed.getBirthDate()), String.valueOf(imputed.getBirthDate())));
                        break;
                    case "gender":
                        Boolean gender = imputed.getGender();
                        String genderString;
                        if (gender.equals(Constants.GENDER_FEMALE))
                            genderString = "Femenino";
                        else
                            genderString = "Masculino";
                        listFMS.add(new FieldMeetingSource(genderString, m.getImputed().getGender().toString()));
                        break;
                    case "celPhone":
                        listFMS.add(new FieldMeetingSource(imputed.getCelPhone(), imputed.getCelPhone()));
                        break;
                    case "maritalStatus":
                        cdto.setName(imputed.getMaritalStatus().getName());
                        cdto.setId(imputed.getMaritalStatus().getId());
                        listFMS.add(new FieldMeetingSource(cdto.getName(), gson.toJson(cdto)));
                        break;
                    case "yearsMaritalStatus":
                        if (imputed.getYearsMaritalStatus() != null) {
                            listFMS.add(new FieldMeetingSource(imputed.getYearsMaritalStatus().toString(), imputed.getYearsMaritalStatus().toString()));
                        }
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
                    case "location":
                        Location l = imputed.getLocation();
                        if (l != null) {
                            cdto.setName(l.getName());
                            cdto.setId(l.getId());

                            listFMS.add(new FieldMeetingSource("Estado: " + l.getMunicipality().getState().getName() + ", Municipio; " + l.getMunicipality().getName() + ", Localidad: " + l.getName() + ".", gson.toJson(cdto)));
                        }
                        break;
                }
                break;
            case "socialEnvironment":
                SocialEnvironment se = m.getSocialEnvironment();
                switch (name[1]) {
                    case "physicalCondition":
                        listFMS.add(new FieldMeetingSource(se.getPhysicalCondition(), se.getPhysicalCondition()));
                        break;
                    case "comment":
                        if (se.getComment() != null && !se.getComment().equals(""))
                            listFMS.add(new FieldMeetingSource(se.getComment(), se.getComment()));
                        break;
                    case "activities":

                        try {
                            List<RelSocialEnvironmentActivity> rels = se.getRelSocialEnvironmentActivities();
                            if (rels != null && rels.size() > 0) {
                                String val = "";
                                List<RelSocialEnvironmentActivity> raux = new ArrayList<>();
                                for (RelSocialEnvironmentActivity r : rels) {
                                    RelSocialEnvironmentActivity rs = new RelSocialEnvironmentActivity();
                                    rs.setActivity(new Activity());
                                    rs.getActivity().setId(r.getActivity().getId());
                                    rs.setSpecification(r.getSpecification());
                                    raux.add(rs);
                                    val = val + r.getActivity().getName();
                                    if (r.getSpecification() != null && !r.getSpecification().equals("")) {
                                        val = val + ": " + r.getSpecification() + "; ";
                                    } else {
                                        val = val + "; ";
                                    }
                                }
                                listFMS.add(new FieldMeetingSource(val, gson.toJson(raux)));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }

                        break;
                }
                break;
            case "imputedHomes":
                for (ImputedHome ih : m.getImputedHomes()) {
                    CatalogDto cDto = new CatalogDto();
                    switch (name[1]) {
                        case "address":
                            AddressDto adDto = new AddressDto();
                            adDto.addressDto(ih.getAddress());
                            listFMS.add(new FieldMeetingSource(ih.getAddress().getAddressString(), gson.toJson(adDto), ih.getId()));
                            break;
                        case "homeType":
                            cDto.setId(ih.getHomeType().getId());
                            cDto.setName(ih.getHomeType().getName());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), ih.getId()));
                            break;
                        case "registerType":
                            cDto.setId(ih.getRegisterType().getId());
                            cDto.setName(ih.getRegisterType().getName());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), ih.getId()));
                            break;
                        case "timeLive":
                            listFMS.add(new FieldMeetingSource(ih.getTimeLive(), ih.getTimeLive(), ih.getId()));
                            break;
                        case "reasonChange":
                            if (ih.getReasonChange() != null && !ih.getReasonChange().equals(""))
                                listFMS.add(new FieldMeetingSource(ih.getReasonChange(), ih.getReasonChange(), ih.getId()));
                            break;
                        case "reasonSecondary":
                            if (ih.getReasonSecondary() != null && !ih.getReasonSecondary().equals(""))
                                listFMS.add(new FieldMeetingSource(ih.getReasonSecondary(), ih.getReasonSecondary(), ih.getId()));
                            break;
                        case "description":
                            if (ih.getDescription() != null && !ih.getDescription().equals(""))
                                listFMS.add(new FieldMeetingSource(ih.getDescription(), ih.getDescription(), ih.getId()));
                            break;
                        case "specification":
                            if (ih.getSpecification() != null && !ih.getSpecification().equals("")) {
                                listFMS.add(new FieldMeetingSource(ih.getSpecification(), ih.getSpecification(), ih.getId()));
                            }
                            break;
                        case "schedule":
                            String s = (String) scheduleService.getSchedules(ih.getId(), ImputedHome.class);
                            if (!s.equals("[]") && ih.getRegisterType() != null && !ih.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                                listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(ih.getId(), ImputedHome.class), s, ih.getId()));
                            }
                            break;
                        case "phone":
                            if (ih.getPhone() != null && !ih.getPhone().equals("")) {
                                listFMS.add(new FieldMeetingSource(ih.getPhone(), ih.getPhone(), ih.getId()));
                            }
                            break;
                    }
                }
                break;
            case "socialNetwork":
                if (name[1].equals("comment")) {
                    SocialNetwork s = m.getSocialNetwork();
                    listFMS.add(new FieldMeetingSource(s.getComment(), s.getComment()));
                } else {
                    List<PersonSocialNetwork> listp = m.getSocialNetwork().getPeopleSocialNetwork();
                    for (PersonSocialNetwork psn : listp) {
                        CatalogDto cDto = new CatalogDto();
                        switch (name[1]) {
                            case "block":
                                Boolean block = psn.getBlock();
                                listFMS.add(new FieldMeetingSource(block?"Si":"No",block?"1":"0",true, psn.getId()));
                                break;
                            case "name":
                                listFMS.add(new FieldMeetingSource(psn.getName(), psn.getName(), psn.getId()));
                                break;
                            case "relationship":
                                cDto.setName(psn.getRelationship().getName());
                                cDto.setId(psn.getRelationship().getId());
                                listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), psn.getId()));
                                break;
                            case "specificationRelationship":
                                if (psn.getSpecificationRelationship() != null && !psn.getSpecificationRelationship().equals("")) {
                                    listFMS.add(new FieldMeetingSource(psn.getSpecificationRelationship(), psn.getSpecificationRelationship(), psn.getId()));
                                }
                                break;
                            case "phone":
                                listFMS.add(new FieldMeetingSource(psn.getPhone(), psn.getPhone(), psn.getId()));
                                break;
                            case "documentType":
                                cDto.setName(psn.getDocumentType().getName());
                                cDto.setId(psn.getDocumentType().getId());
                                listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), psn.getId()));
                                break;
                            case "specification":
                                if (psn.getSpecification() != null && !psn.getSpecification().equals("")) {
                                    listFMS.add(new FieldMeetingSource(psn.getSpecification(), psn.getSpecification(), psn.getId()));
                                }
                                break;
                            case "age":
                                listFMS.add(new FieldMeetingSource(psn.getAge().toString(), psn.getAge().toString(), psn.getId()));
                                break;
                            case "dependent":
                                cDto.setName(psn.getDependent().getName());
                                cDto.setId(psn.getDependent().getId());
                                listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), psn.getId()));
                                break;
                            case "livingWith":
                                cDto.setName(psn.getLivingWith().getName());
                                cDto.setId(psn.getLivingWith().getId());
                                listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), psn.getId()));
                                break;
                            case "address":
                                listFMS.add(new FieldMeetingSource(psn.getAddress(), psn.getAddress(), psn.getId()));
                                break;
                            case "isAccompaniment":
                                String acString = psn.getIsAccompaniment() ? "Si" : "No";
                                String acValue = psn.getIsAccompaniment() ? "1" : "0";
                                FieldMeetingSource fmsAc = new FieldMeetingSource(acString, acValue, psn.getId());
                                fmsAc.setFinal(true);
                                listFMS.add(fmsAc);
                                break;
                        }
                    }
                }
                break;
            case "references":
                for (Reference r : m.getReferences()) {
                    CatalogDto cDto = new CatalogDto();
                    switch (name[1]) {
                        case "block":
                            Boolean block = r.getBlock();
                            listFMS.add(new FieldMeetingSource(block?"Si":"No",block?"1":"0",true,r.getId()));
                            break;
                        case "fullName":
                            listFMS.add(new FieldMeetingSource(r.getFullName(), r.getFullName(), r.getId()));
                            break;
                        case "relationship":
                            cDto.setName(r.getRelationship().getName());
                            cDto.setId(r.getRelationship().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), r.getId()));
                            break;
                        case "specificationRelationship":
                            if (r.getSpecificationRelationship() != null && !r.getSpecificationRelationship().equals("")) {
                                listFMS.add(new FieldMeetingSource(r.getSpecificationRelationship(), r.getSpecificationRelationship(), r.getId()));
                            }
                            break;
                        case "phone":
                            listFMS.add(new FieldMeetingSource(r.getPhone(), r.getPhone(), r.getId()));
                            break;
                        case "documentType":
                            cDto.setName(r.getDocumentType().getName());
                            cDto.setId(r.getDocumentType().getId());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), r.getId()));
                            break;
                        case "specification":
                            if (r.getSpecification() != null && !r.getSpecification().equals("")) {
                                listFMS.add(new FieldMeetingSource(r.getSpecification(), r.getSpecification(), r.getId()));
                            }
                            break;
                        case "age":
                            listFMS.add(new FieldMeetingSource(r.getAge().toString(), r.getAge().toString(), r.getId()));
                            break;
                        case "address":
                            listFMS.add(new FieldMeetingSource(r.getAddress(), r.getAddress(), r.getId()));
                            break;
                        case "isAccompaniment":
                            String acString = r.getIsAccompaniment() ? "Si" : "No";
                            String acValue = r.getIsAccompaniment() ? "1" : "0";
                            FieldMeetingSource fmsAc = new FieldMeetingSource(acString, acValue, r.getId());
                            fmsAc.setFinal(true);
                            listFMS.add(fmsAc);
                            break;
                    }
                }
                break;
            case "jobs":
                for (Job j : m.getJobs()) {
                    CatalogDto cDto = new CatalogDto();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    switch (name[1]) {
                        case "block":
                            Boolean block = j.getBlock();
                            listFMS.add(new FieldMeetingSource(block?"Si":"No",block?"1":"0",true,j.getId()));
                            break;
                        case "company":
                            listFMS.add(new FieldMeetingSource(j.getCompany(), j.getCompany(), j.getId()));
                            break;
                        case "post":
                            listFMS.add(new FieldMeetingSource(j.getPost(), j.getPost(), j.getId()));
                            break;
                        case "nameHead":
                            listFMS.add(new FieldMeetingSource(j.getNameHead(), j.getNameHead(), j.getId()));
                            break;
                        case "phone":
                            listFMS.add(new FieldMeetingSource(j.getPhone(), j.getPhone(), j.getId()));
                            break;
                        case "address":
                            listFMS.add(new FieldMeetingSource(j.getAddress(), j.getAddress(), j.getId()));
                            break;
                        case "registerType":
                            cDto.setId(j.getRegisterType().getId());
                            cDto.setName(j.getRegisterType().getName());
                            listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), j.getId()));
                            break;
                        case "start":
                            if (!j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(formatter.format(j.getStart()), String.valueOf(j.getStart()), j.getId()));
                            break;
                        case "salaryWeek":
                            if (!j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(j.getSalaryWeek().toString(), j.getSalaryWeek().toString(), j.getId()));
                            break;
                        case "startPrev":
                            if (j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(formatter.format(j.getStartPrev()), String.valueOf(j.getStartPrev()), j.getId()));
                            break;
                        case "end":
                            if (j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(formatter.format(j.getEnd()), String.valueOf(j.getEnd()), j.getId()));
                            break;
                        case "reasonChange":
                            if (j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                listFMS.add(new FieldMeetingSource(j.getReasonChange(), j.getReasonChange(), j.getId()));
                            break;
                        case "schedule":
                            String s = (String) scheduleService.getSchedules(j.getId(), Job.class);
                            if (!s.equals("[]") && j.getRegisterType() != null && !j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                                listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(j.getId(), Job.class), s, j.getId()));
                            }
                            break;
                    }
                }
                break;
            case "school":
                School s = m.getSchool();
                switch (name[1]) {
                    case "block":
                        Boolean block = s.getBlock();
                        listFMS.add(new FieldMeetingSource(block?"Si":"No",block?"1":"0",true));
                        break;
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
                        String level = "Nivel: " + s.getDegree().getAcademicLevel().getName() + " Grado: " + s.getDegree().getName();
                        listFMS.add(new FieldMeetingSource(level, gson.toJson(new DegreeDto().dtoGrade(s.getDegree()))));
                        break;
                    case "specification":
                        if (!s.getSpecification().trim().equals("")) {
                            listFMS.add(new FieldMeetingSource(s.getSpecification().trim(), s.getSpecification().trim()));
                        }
                        break;
                    case "schedule":
                        String sc = (String) scheduleService.getSchedules(s.getId(), School.class);
                        if (!sc.equals("[]")) {
                            listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(s.getId(), School.class), sc));
                        }
                        break;
                }
                break;
            case "drugs":
                for (Drug d : m.getDrugs()) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    CatalogDto cdtod = new CatalogDto();
                    switch (name[1]) {
                        case "block":
                            Boolean block = d.getBlock();
                            listFMS.add(new FieldMeetingSource(block?"Si":"No",block?"1":"0",true,d.getId()));
                            break;
                        case "drugType":
                            cdtod.setName(d.getDrugType().getName());
                            cdtod.setId(d.getDrugType().getId());
                            listFMS.add(new FieldMeetingSource(cdtod.getName(), gson.toJson(cdtod), d.getId()));
                            break;
                        case "specificationType":
                            if (d.getSpecificationType() != null && !d.getSpecificationType().equals(""))
                                listFMS.add(new FieldMeetingSource(d.getSpecificationType(), d.getSpecificationType(), d.getId()));
                            break;
                        case "periodicity":
                            cdtod.setName(d.getPeriodicity().getName());
                            cdtod.setId(d.getPeriodicity().getId());
                            listFMS.add(new FieldMeetingSource(cdtod.getName(), gson.toJson(cdtod), d.getId()));
                            break;
                        case "specificationPeriodicity":
                            if (d.getSpecificationPeriodicity() != null && !d.getSpecificationPeriodicity().equals("")) {
                                listFMS.add(new FieldMeetingSource(d.getSpecificationPeriodicity(), d.getSpecificationPeriodicity(), d.getId()));
                            }
                            break;
                        case "quantity":
                            listFMS.add(new FieldMeetingSource(d.getQuantity(), d.getQuantity(), d.getId()));
                            break;
                        case "onsetAge":
                            listFMS.add(new FieldMeetingSource(d.getOnsetAge(), d.getOnsetAge(), d.getId()));
                            break;
                        case "lastUse":
                            listFMS.add(new FieldMeetingSource(formatter.format(d.getLastUse()), String.valueOf(d.getLastUse()), d.getId()));
                            break;
                    }
                }
                break;
            case "leaveCountry":
                LeaveCountry l = m.getLeaveCountry();
                CatalogDto cdtol = new CatalogDto();
                switch (name[1]) {
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
                        if (l.getLivedCountry().getId().equals(Constants.ELECTION_YES)) {
                            cdtol.setId(l.getCountry().getId());
                            cdtol.setName(l.getCountry().getName());
                            listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        }
                        break;
                    case "state":
                        if (l.getLivedCountry().getId().equals(Constants.ELECTION_YES)) {
                            listFMS.add(new FieldMeetingSource(l.getState(), l.getState()));
                        }
                        break;
                    case "timeAgo":
                        if (l.getLivedCountry().getId().equals(Constants.ELECTION_YES)) {
                            listFMS.add(new FieldMeetingSource(l.getTimeAgo(), l.getTimeAgo()));
                        }
                        break;
                    case "reason":
                        if (l.getLivedCountry().getId().equals(Constants.ELECTION_YES)) {
                            listFMS.add(new FieldMeetingSource(l.getReason(), l.getReason()));
                        }
                        break;
                    case "address":
                        if (l.getLivedCountry().getId().equals(Constants.ELECTION_YES)) {
                            listFMS.add(new FieldMeetingSource(l.getAddress(), l.getAddress()));
                        }
                        break;
                    case "familyAnotherCountry":
                        cdtol.setName(l.getFamilyAnotherCountry().getName());
                        cdtol.setId(l.getFamilyAnotherCountry().getId());
                        listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        break;
                    case "communicationFamily":
                        if (l.getFamilyAnotherCountry().getId().equals(Constants.ELECTION_YES)) {
                            cdtol.setName(l.getCommunicationFamily().getName());
                            cdtol.setId(l.getCommunicationFamily().getId());
                            listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        }
                        break;
                    case "media":
                        if (l.getMedia() != null && !l.getMedia().equals("")) {
                            listFMS.add(new FieldMeetingSource(l.getMedia(), l.getMedia()));
                        }
                        break;
                    case "immigrationDocument":
                        ImmigrationDocument im = l.getImmigrationDocument();
                        if(im!=null){
                            cdtol.setName(im.getName());
                            cdtol.setId(im.getId());
                            listFMS.add(new FieldMeetingSource(im.getName(), gson.toJson(cdtol)));
                        }
                        break;
                    case "specficationImmigranDoc":
                        if(l.getSpecficationImmigranDoc()!=null && !l.getSpecficationImmigranDoc().trim().equals("")){
                            listFMS.add(new FieldMeetingSource(l.getSpecficationImmigranDoc(), l.getSpecficationImmigranDoc()));
                        }
                        break;
                    case "realtionship":
                        Relationship r = l.getRelationship();
                        if(r!=null &&r.getId()!=null){
                            cdtol.setName(r.getName());
                            cdtol.setId(r.getId());
                            listFMS.add(new FieldMeetingSource(r.getName(),gson.toJson(cdtol)));
                        }
                        break;
                    case "specificationRelationship":
                         if(l.getSpecificationRelationship()!=null&& !l.getSpecificationRelationship().trim().equals("")){
                             listFMS.add(new FieldMeetingSource(l.getSpecificationRelationship(), l.getSpecificationRelationship()));
                         }
                        break;
                    case "timeResidence":
                        if(l.getTimeResidence()!=null&& !l.getTimeResidence().trim().equals("")){
                            listFMS.add(new FieldMeetingSource(l.getTimeResidence(), l.getTimeResidence()));
                        }
                        break;
                }
                break;
        }
        for (FieldMeetingSource fms : listFMS) {
            fms.setFieldVerification(template.getFieldVerification());
            fms.setSourceVerification(template.getSourceVerification());
            fms.setStatusFieldVerification(template.getStatusFieldVerification());
            if (fms.getFinal() == null)
                fms.setFinal(false);
        }
        return listFMS;
    }

    @Override
    public List<FieldMeetingSource> getValueByCode(String code, Meeting m, FieldMeetingSource template, Long idList) {

        List<FieldMeetingSource> listFMS = new ArrayList<>();
        String[] name = code.split("\\.");
        Gson gson = new Gson();
        switch (name[0]) {
            case "imputed":
                Imputed imputed = m.getImputed();
                CatalogDto cdto = new CatalogDto();
                switch (name[1]) {
                    case "name":
                        listFMS.add(new FieldMeetingSource(imputed.getName(), imputed.getName()));
                        break;
                    case "lastNameP":
                        listFMS.add(new FieldMeetingSource(imputed.getLastNameP(), imputed.getLastNameP()));
                        break;
                    case "lastNameM":
                        listFMS.add(new FieldMeetingSource(imputed.getLastNameM(), imputed.getLastNameM()));
                        break;
                    case "birthDate":
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        listFMS.add(new FieldMeetingSource(formatter.format(imputed.getBirthDate()), String.valueOf(imputed.getBirthDate())));
                        break;
                    case "gender":
                        Boolean gender = imputed.getGender();
                        String genderString;
                        if (gender.equals(Constants.GENDER_FEMALE))
                            genderString = "Femenino";
                        else
                            genderString = "Masculino";
                        listFMS.add(new FieldMeetingSource(genderString, m.getImputed().getGender().toString()));
                        break;
                    case "celPhone":
                        listFMS.add(new FieldMeetingSource(imputed.getCelPhone(), imputed.getCelPhone()));
                        break;
                    case "nickname":
                        listFMS.add(new FieldMeetingSource(imputed.getNickname(), imputed.getNickname()));
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
                    case "location":
                        Location l = imputed.getLocation();
                        if (l != null) {
                            cdto.setName(l.getName());
                            cdto.setId(l.getId());
                            listFMS.add(new FieldMeetingSource("Estado: " + l.getMunicipality().getState().getName() + ", Municipio; " + l.getMunicipality().getName() + ", Localidad: " + l.getName() + ".", gson.toJson(cdto)));
                        }
                        break;
                }
                break;
            case "socialEnvironment":
                SocialEnvironment se = m.getSocialEnvironment();
                switch (name[1]) {
                    case "physicalCondition":
                        listFMS.add(new FieldMeetingSource(se.getPhysicalCondition(), se.getPhysicalCondition()));
                        break;
                    case "comment":
                        if (se.getComment() != null && !se.getComment().equals(""))
                            listFMS.add(new FieldMeetingSource(se.getComment(), se.getComment()));
                        break;
                    case "activities":

                        try {
                            List<RelSocialEnvironmentActivity> rels = se.getRelSocialEnvironmentActivities();
                            if (rels != null && rels.size() > 0) {
                                String val = "";
                                List<RelSocialEnvironmentActivity> raux = new ArrayList<>();
                                for (RelSocialEnvironmentActivity r : rels) {
                                    RelSocialEnvironmentActivity rs = new RelSocialEnvironmentActivity();
                                    rs.setActivity(new Activity());
                                    rs.getActivity().setId(r.getActivity().getId());
                                    rs.setSpecification(r.getSpecification());
                                    raux.add(rs);
                                    val = val + r.getActivity().getName();
                                    if (r.getSpecification() != null && !r.getSpecification().equals("")) {
                                        val = val + ": " + r.getSpecification() + "; ";
                                    } else {
                                        val = val + "; ";
                                    }
                                }
                                listFMS.add(new FieldMeetingSource(val, gson.toJson(raux)));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }

                        break;
                }
                break;
            case "imputedHomes":
                for (ImputedHome ih : m.getImputedHomes()) {
                    CatalogDto cDto = new CatalogDto();
                    if (ih.getId().equals(idList)) {
                        switch (name[1]) {
                            case "address":
                                AddressDto adDto = new AddressDto();
                                adDto.addressDto(ih.getAddress());
                                listFMS.add(new FieldMeetingSource(ih.getAddress().getAddressString(), gson.toJson(adDto), ih.getId()));
                                break;
                            case "homeType":
                                cDto.setId(ih.getHomeType().getId());
                                cDto.setName(ih.getHomeType().getName());
                                listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), ih.getId()));
                                break;
                            case "registerType":
                                cDto.setId(ih.getRegisterType().getId());
                                cDto.setName(ih.getRegisterType().getName());
                                listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), ih.getId()));
                                break;
                            case "timeLive":
                                listFMS.add(new FieldMeetingSource(ih.getTimeLive(), ih.getTimeLive(), ih.getId()));
                                break;
                            case "reasonChange":
                                if (ih.getReasonChange() != null && !ih.getReasonChange().equals(""))
                                    listFMS.add(new FieldMeetingSource(ih.getReasonChange(), ih.getReasonChange(), ih.getId()));
                                break;
                            case "reasonSecondary":
                                if (ih.getReasonSecondary() != null && !ih.getReasonSecondary().equals(""))
                                    listFMS.add(new FieldMeetingSource(ih.getReasonSecondary(), ih.getReasonSecondary(), ih.getId()));
                                break;
                            case "description":
                                if (ih.getDescription() != null && !ih.getDescription().equals(""))
                                    listFMS.add(new FieldMeetingSource(ih.getDescription(), ih.getDescription(), ih.getId()));
                                break;
                            case "specification":
                                if (ih.getSpecification() != null && !ih.getSpecification().equals("")) {
                                    listFMS.add(new FieldMeetingSource(ih.getSpecification(), ih.getSpecification(), ih.getId()));
                                }
                                break;
                            case "schedule":
                                String s = (String) scheduleService.getSchedules(ih.getId(), ImputedHome.class);
                                if (!s.equals("[]") && ih.getRegisterType() != null && !ih.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                                    listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(ih.getId(), ImputedHome.class), s, ih.getId()));
                                }
                                break;
                            case "phone":
                                if (ih.getPhone() != null && !ih.getPhone().equals("")) {
                                    listFMS.add(new FieldMeetingSource(ih.getPhone(), ih.getPhone(), ih.getId()));
                                }
                                break;
                        }
                        break;
                    }

                }
                break;
            case "socialNetwork":
                if (name[1].equals("comment")) {
                    SocialNetwork s = m.getSocialNetwork();
                    listFMS.add(new FieldMeetingSource(s.getComment(), s.getComment()));
                } else {
                    List<PersonSocialNetwork> listp = m.getSocialNetwork().getPeopleSocialNetwork();
                    for (PersonSocialNetwork psn : listp) {
                        if (psn.getId().equals(idList)) {
                            CatalogDto cDto = new CatalogDto();
                            switch (name[1]) {
                                case "block":
                                    Boolean block = psn.getBlock();
                                    listFMS.add(new FieldMeetingSource(block?"Si":"No",block?"1":"0",true,psn.getId()));
                                    break;
                                case "name":
                                    listFMS.add(new FieldMeetingSource(psn.getName(), psn.getName(), psn.getId()));
                                    break;
                                case "relationship":
                                    cDto.setName(psn.getRelationship().getName());
                                    cDto.setId(psn.getRelationship().getId());
                                    listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), psn.getId()));
                                    break;
                                case "specificationRelationship":
                                    if (psn.getSpecificationRelationship() != null && !psn.getSpecificationRelationship().equals("")) {
                                        listFMS.add(new FieldMeetingSource(psn.getSpecificationRelationship(), psn.getSpecificationRelationship(), psn.getId()));
                                    }
                                    break;
                                case "phone":
                                    listFMS.add(new FieldMeetingSource(psn.getPhone(), psn.getPhone(), psn.getId()));
                                    break;
                                case "documentType":
                                    cDto.setName(psn.getDocumentType().getName());
                                    cDto.setId(psn.getDocumentType().getId());
                                    listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), psn.getId()));
                                    break;
                                case "specification":
                                    if (psn.getSpecification() != null && !psn.getSpecification().equals("")) {
                                        listFMS.add(new FieldMeetingSource(psn.getSpecification(), psn.getSpecification(), psn.getId()));
                                    }
                                    break;
                                case "age":
                                    listFMS.add(new FieldMeetingSource(psn.getAge().toString(), psn.getAge().toString(), psn.getId()));
                                    break;
                                case "dependent":
                                    cDto.setName(psn.getDependent().getName());
                                    cDto.setId(psn.getDependent().getId());
                                    listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), psn.getId()));
                                    break;
                                case "livingWith":
                                    cDto.setName(psn.getLivingWith().getName());
                                    cDto.setId(psn.getLivingWith().getId());
                                    listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), psn.getId()));
                                    break;
                                case "address":
                                    listFMS.add(new FieldMeetingSource(psn.getAddress(), psn.getAddress(), psn.getId()));
                                    break;
                                case "isAccompaniment":
                                    String acString = psn.getIsAccompaniment() ? "Si" : "No";
                                    String acValue = psn.getIsAccompaniment() ? "1" : "0";
                                    listFMS.add(new FieldMeetingSource(acString, acValue, psn.getId()));
                                    break;
                            }
                            break;
                        }

                    }
                }
                break;
            case "references":
                for (Reference r : m.getReferences()) {
                    if (r.getId().equals(idList)) {
                        CatalogDto cDto = new CatalogDto();
                        switch (name[1]) {
                            case "block":
                                Boolean block = r.getBlock();
                                listFMS.add(new FieldMeetingSource(block?"Si":"No",block?"1":"0",true,r.getId()));
                                break;
                            case "fullName":
                                listFMS.add(new FieldMeetingSource(r.getFullName(), r.getFullName(), r.getId()));
                                break;
                            case "relationship":
                                cDto.setName(r.getRelationship().getName());
                                cDto.setId(r.getRelationship().getId());
                                listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), r.getId()));
                                break;
                            case "specificationRelationship":
                                if (r.getSpecificationRelationship() != null && !r.getSpecificationRelationship().equals("")) {
                                    listFMS.add(new FieldMeetingSource(r.getSpecificationRelationship(), r.getSpecificationRelationship(), r.getId()));
                                }
                                break;
                            case "phone":
                                listFMS.add(new FieldMeetingSource(r.getPhone(), r.getPhone(), r.getId()));
                                break;
                            case "documentType":
                                cDto.setName(r.getDocumentType().getName());
                                cDto.setId(r.getDocumentType().getId());
                                listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), r.getId()));
                                break;
                            case "specification":
                                if (r.getSpecification() != null && !r.getSpecification().equals("")) {
                                    listFMS.add(new FieldMeetingSource(r.getSpecification(), r.getSpecification(), r.getId()));
                                }
                                break;
                            case "age":
                                listFMS.add(new FieldMeetingSource(r.getAge().toString(), r.getAge().toString(), r.getId()));
                                break;
                            case "address":
                                listFMS.add(new FieldMeetingSource(r.getAddress(), r.getAddress(), r.getId()));
                                break;
                            case "isAccompaniment":
                                String acString = r.getIsAccompaniment() ? "Si" : "No";
                                String acValue = r.getIsAccompaniment() ? "1" : "0";
                                listFMS.add(new FieldMeetingSource(acString, acValue, r.getId()));
                                break;
                        }
                        break;
                    }
                }
                break;
            case "jobs":
                for (Job j : m.getJobs()) {
                    if (j.getId().equals(idList)) {
                        CatalogDto cDto = new CatalogDto();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        switch (name[1]) {
                            case "block":
                                Boolean block = j.getBlock();
                                listFMS.add(new FieldMeetingSource(block?"Si":"No",block?"1":"0",true,j.getId()));
                                break;
                            case "company":
                                listFMS.add(new FieldMeetingSource(j.getCompany(), j.getCompany(), j.getId()));
                                break;
                            case "post":
                                listFMS.add(new FieldMeetingSource(j.getPost(), j.getPost(), j.getId()));
                                break;
                            case "nameHead":
                                listFMS.add(new FieldMeetingSource(j.getNameHead(), j.getNameHead(), j.getId()));
                                break;
                            case "phone":
                                listFMS.add(new FieldMeetingSource(j.getPhone(), j.getPhone(), j.getId()));
                                break;
                            case "address":
                                listFMS.add(new FieldMeetingSource(j.getAddress(), j.getAddress(), j.getId()));
                                break;
                            case "registerType":
                                cDto.setId(j.getRegisterType().getId());
                                cDto.setName(j.getRegisterType().getName());
                                listFMS.add(new FieldMeetingSource(cDto.getName(), gson.toJson(cDto), j.getId()));
                                break;
                            case "start":
                                if (!j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                    listFMS.add(new FieldMeetingSource(formatter.format(j.getStart()), String.valueOf(j.getStart()), j.getId()));
                                break;
                            case "salaryWeek":
                                if (!j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                    listFMS.add(new FieldMeetingSource(j.getSalaryWeek().toString(), j.getSalaryWeek().toString(), j.getId()));
                                break;
                            case "startPrev":
                                if (j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                    listFMS.add(new FieldMeetingSource(formatter.format(j.getStartPrev()), String.valueOf(j.getStartPrev()), j.getId()));
                                break;
                            case "end":
                                if (j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                    listFMS.add(new FieldMeetingSource(formatter.format(j.getEnd()), String.valueOf(j.getEnd()), j.getId()));
                                break;
                            case "reasonChange":
                                if (j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS))
                                    listFMS.add(new FieldMeetingSource(j.getReasonChange(), j.getReasonChange(), j.getId()));
                                break;
                            case "schedule":
                                String s = (String) scheduleService.getSchedules(j.getId(), Job.class);
                                if (!s.equals("[]") && j.getRegisterType() != null && !j.getRegisterType().getId().equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                                    listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(j.getId(), Job.class), s, j.getId()));
                                }
                                break;
                        }
                        break;
                    }
                }
                break;
            case "school":
                School s = m.getSchool();
                switch (name[1]) {
                    case "block":
                        Boolean block = s.getBlock();
                        listFMS.add(new FieldMeetingSource(block?"Si":"No",block?"1":"0",true));
                        break;
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
                        String level = "Nivel: " + s.getDegree().getAcademicLevel().getName() + " Grado: " + s.getDegree().getName();
                        listFMS.add(new FieldMeetingSource(level, gson.toJson(new DegreeDto().dtoGrade(s.getDegree()))));
                        break;
                    case "specification":
                        if (!s.getSpecification().trim().equals("")) {
                            listFMS.add(new FieldMeetingSource(s.getSpecification().trim(), s.getSpecification().trim()));
                        }
                        break;
                    case "schedule":
                        String sc = (String) scheduleService.getSchedules(s.getId(), School.class);
                        if (!sc.equals("[]")) {
                            listFMS.add(new FieldMeetingSource(scheduleService.getSchedulesVerificationValue(s.getId(), School.class), sc));
                        }
                        break;
                }
                break;
            case "drugs":
                for (Drug d : m.getDrugs()) {
                    if (d.getId().equals(idList)) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        CatalogDto cdtod = new CatalogDto();
                        switch (name[1]) {
                            case "block":
                                Boolean block = d.getBlock();
                                listFMS.add(new FieldMeetingSource(block?"Si":"No",block?"1":"0",true,d.getId()));
                                break;
                            case "drugType":
                                cdtod.setName(d.getDrugType().getName());
                                cdtod.setId(d.getDrugType().getId());
                                listFMS.add(new FieldMeetingSource(cdtod.getName(), gson.toJson(cdtod), d.getId()));
                                break;
                            case "specificationType":
                                if (d.getSpecificationType() != null && !d.getSpecificationType().equals(""))
                                    listFMS.add(new FieldMeetingSource(d.getSpecificationType(), d.getSpecificationType(), d.getId()));
                                break;
                            case "periodicity":
                                cdtod.setName(d.getPeriodicity().getName());
                                cdtod.setId(d.getPeriodicity().getId());
                                listFMS.add(new FieldMeetingSource(cdtod.getName(), gson.toJson(cdtod), d.getId()));
                                break;
                            case "specificationPeriodicity":
                                if (d.getSpecificationPeriodicity() != null && !d.getSpecificationPeriodicity().equals("")) {
                                    listFMS.add(new FieldMeetingSource(d.getSpecificationPeriodicity(), d.getSpecificationPeriodicity(), d.getId()));
                                }
                                break;
                            case "quantity":
                                listFMS.add(new FieldMeetingSource(d.getQuantity(), d.getQuantity(), d.getId()));
                                break;
                            case "onsetAge":
                                listFMS.add(new FieldMeetingSource(d.getOnsetAge(), d.getOnsetAge(), d.getId()));
                                break;
                            case "lastUse":
                                listFMS.add(new FieldMeetingSource(formatter.format(d.getLastUse()), String.valueOf(d.getLastUse()), d.getId()));
                                break;
                        }
                        break;
                    }
                }
                break;
            case "leaveCountry":
                LeaveCountry l = m.getLeaveCountry();
                CatalogDto cdtol = new CatalogDto();
                switch (name[1]) {
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
                        if (l.getLivedCountry().getId().equals(Constants.ELECTION_YES)) {
                            cdtol.setId(l.getCountry().getId());
                            cdtol.setName(l.getCountry().getName());
                            listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        }
                        break;
                    case "state":
                        if (l.getLivedCountry().getId().equals(Constants.ELECTION_YES)) {
                            listFMS.add(new FieldMeetingSource(l.getState(), l.getState()));
                        }
                        break;
                    case "timeAgo":
                        if (l.getLivedCountry().getId().equals(Constants.ELECTION_YES)) {
                            listFMS.add(new FieldMeetingSource(l.getTimeAgo(), l.getTimeAgo()));
                        }
                        break;
                    case "reason":
                        if (l.getLivedCountry().getId().equals(Constants.ELECTION_YES)) {
                            listFMS.add(new FieldMeetingSource(l.getReason(), l.getReason()));
                        }
                        break;
                    case "address":
                        if (l.getLivedCountry().getId().equals(Constants.ELECTION_YES)) {
                            listFMS.add(new FieldMeetingSource(l.getAddress(), l.getAddress()));
                        }
                        break;
                    case "familyAnotherCountry":
                        cdtol.setName(l.getFamilyAnotherCountry().getName());
                        cdtol.setId(l.getFamilyAnotherCountry().getId());
                        listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        break;
                    case "communicationFamily":
                        if (l.getFamilyAnotherCountry().getId().equals(Constants.ELECTION_YES)) {
                            cdtol.setName(l.getCommunicationFamily().getName());
                            cdtol.setId(l.getCommunicationFamily().getId());
                            listFMS.add(new FieldMeetingSource(cdtol.getName(), gson.toJson(cdtol)));
                        }
                        break;
                    case "media":
                        if (l.getMedia() != null && !l.getMedia().equals("")) {
                            listFMS.add(new FieldMeetingSource(l.getMedia(), l.getMedia()));
                        }
                        break;
                    case "immigrationDocument":
                        ImmigrationDocument im = l.getImmigrationDocument();
                        if(im!=null){
                            cdtol.setName(im.getName());
                            cdtol.setId(im.getId());
                            listFMS.add(new FieldMeetingSource(im.getName(), gson.toJson(cdtol)));
                        }
                        break;
                    case "specficationImmigranDoc":
                        if(l.getSpecficationImmigranDoc()!=null && !l.getSpecficationImmigranDoc().trim().equals("")){
                            listFMS.add(new FieldMeetingSource(l.getSpecficationImmigranDoc(), l.getSpecficationImmigranDoc()));
                        }
                        break;
                    case "realtionship":
                        Relationship r = l.getRelationship();
                        if(r!=null &&r.getId()!=null){
                            cdtol.setName(r.getName());
                            cdtol.setId(r.getId());
                            listFMS.add(new FieldMeetingSource(r.getName(),gson.toJson(cdtol)));
                        }
                        break;
                    case "specificationRelationship":
                        if(l.getSpecificationRelationship()!=null&& !l.getSpecificationRelationship().trim().equals("")){
                            listFMS.add(new FieldMeetingSource(l.getSpecificationRelationship(), l.getSpecificationRelationship()));
                        }
                        break;
                    case "timeResidence":
                        if(l.getTimeResidence()!=null&& !l.getTimeResidence().trim().equals("")){
                            listFMS.add(new FieldMeetingSource(l.getTimeResidence(), l.getTimeResidence()));
                        }
                        break;
                }
                break;
        }
        for (FieldMeetingSource fms : listFMS) {
            fms.setFieldVerification(template.getFieldVerification());
            fms.setSourceVerification(template.getSourceVerification());
            fms.setStatusFieldVerification(template.getStatusFieldVerification());
            fms.setFinal(false);
        }
        return listFMS;
    }

    @Autowired
    FieldMeetingSourceRepository fieldMeetingSourceRepository;
    @Autowired
    MaritalStatusRepository maritalStatusRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    ElectionRepository electionRepository;
    @Autowired
    RegisterTypeRepository registerTypeRepository;
    @Autowired
    RelationshipRepository relationshipRepository;
    @Autowired
    DocumentTypeRepository documentTypeRepository;
    @Autowired
    DegreeRepository degreeRepository;
    @Autowired
    DrugTypeRepository drugTypeRepository;
    @Autowired
    PeriodicityRepository periodicityRepository;
    @Autowired
    StatusMeetingRepository statusMeetingRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    HomeTypeRepository homeTypeRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    ImmigrationDocumentRepository immigrationDocumentRepository;

    @Override
    public void createMeetingVirified(Long idCase, Verification verification) {
        Meeting meeting = new Meeting();
        meeting.setDateCreate(new Date());
        List<FieldMeetingSource> listFMS = fieldMeetingSourceRepository.getAllFinalByIdCase(idCase);
        for (FieldMeetingSource fms : listFMS) {
            String[] name = fms.getFieldVerification().getCode().split("\\.");
            Gson gson = new Gson();
            switch (name[0]) {
                case "imputed":
                    if (meeting.getImputed() == null) {
                        meeting.setImputed(new Imputed());
                        meeting.getImputed().setMeeting(meeting);
                    }
                    switch (name[1]) {
                        case "name":
                            meeting.getImputed().setName(fms.getJsonValue());
                            if (meeting.getImputed().getName() != null && meeting.getImputed().getLastNameP() != null && meeting.getImputed().getLastNameM() != null) {
                                meeting.getImputed().setFoneticString(meeting.getImputed().getName().trim().toLowerCase() + meeting.getImputed().getLastNameP().trim().toLowerCase() + meeting.getImputed().getLastNameM().trim().toLowerCase());
                            }
                            break;
                        case "lastNameP":
                            meeting.getImputed().setLastNameP(fms.getJsonValue());
                            if (meeting.getImputed().getName() != null && meeting.getImputed().getLastNameP() != null && meeting.getImputed().getLastNameM() != null) {
                                meeting.getImputed().setFoneticString(meeting.getImputed().getName().trim().toLowerCase() + meeting.getImputed().getLastNameP().trim().toLowerCase() + meeting.getImputed().getLastNameM().trim().toLowerCase());
                            }
                            break;
                        case "lastNameM":
                            meeting.getImputed().setLastNameM(fms.getJsonValue());
                            if (meeting.getImputed().getName() != null && meeting.getImputed().getLastNameP() != null && meeting.getImputed().getLastNameM() != null) {
                                meeting.getImputed().setFoneticString(meeting.getImputed().getName().trim().toLowerCase() + meeting.getImputed().getLastNameP().trim().toLowerCase() + meeting.getImputed().getLastNameM().trim().toLowerCase());
                            }
                            break;
                        case "birthDate":
                            SimpleDateFormat formatter = new SimpleDateFormat(Constants.FORMAT_VERIFICATION_DATE);
                            Date birthDate = new Date();
                            try {
                                birthDate = formatter.parse(fms.getJsonValue());
                            } catch (ParseException e) {
                                logException.Write(e, this.getClass(), "createMeetingVirified", sharedUserService);
                            }
                            meeting.getImputed().setBirthDate(birthDate);
                            break;
                        case "gender":
                            meeting.getImputed().setGender(fms.getJsonValue().equals("true"));
                            break;
                        case "celPhone":
                            meeting.getImputed().setCelPhone(fms.getJsonValue());
                            break;
                        case "nickname":
                            meeting.getImputed().setNickname(fms.getJsonValue());
                            break;
                        case "maritalStatus":
                            CatalogDto catalogDto = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                            if (catalogDto != null && catalogDto.getId() != null)
                                meeting.getImputed().setMaritalStatus(maritalStatusRepository.findOne(catalogDto.getId()));
                            break;
                        case "yearsMaritalStatus":
                            if (fms.getJsonValue() != null && !fms.getJsonValue().equals("")) {
                                meeting.getImputed().setYearsMaritalStatus(Integer.parseInt(fms.getJsonValue()));
                            }
                            break;
                        case "boys":
                            meeting.getImputed().setBoys(Integer.parseInt(fms.getJsonValue()));
                            break;
                        case "dependentBoys":
                            meeting.getImputed().setDependentBoys(Integer.parseInt(fms.getJsonValue()));
                            break;
                        case "birthInfo":
                            CatalogDto cInfoAvail = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                            if (cInfoAvail != null && cInfoAvail.getId() != null) {
                                meeting.getImputed().setBirthInfo(informationAvailabilityRepository.findOne(cInfoAvail.getId()));
                            }
                            break;
                        case "birthCountry":
                            CatalogDto cCountry = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                            if (cCountry != null && cCountry.getId() != null) {
                                meeting.getImputed().setBirthCountry(countryRepository.findOne(cCountry.getId()));
                            }
                            break;
                        case "birthState":
                            meeting.getImputed().setBirthState(fms.getJsonValue());
                            break;
                        case "birthMunicipality":
                            meeting.getImputed().setBirthMunicipality(fms.getJsonValue());
                            break;
                        case "birthLocation":
                            meeting.getImputed().setBirthLocation(fms.getJsonValue());
                            break;
                        case "location":
                            CatalogDto locC = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                            if (locC != null && locC.getId() != null) {
                                meeting.getImputed().setLocation(locationRepository.findOne(locC.getId()));
                            }
                            break;
                    }
                    break;
                case "socialEnvironment":
                    if (meeting.getSocialEnvironment() == null) {
                        meeting.setSocialEnvironment(new SocialEnvironment());
                        meeting.getSocialEnvironment().setMeeting(meeting);
                    }
                    switch (name[1]) {
                        case "physicalCondition":
                            meeting.getSocialEnvironment().setPhysicalCondition(fms.getJsonValue());
                            break;
                        case "comment":
                            meeting.getSocialEnvironment().setComment(fms.getJsonValue());
                            break;
                        case "activities":
                            try {
                                List<RelSocialEnvironmentActivity> relSE = gson.fromJson(fms.getJsonValue(), new TypeToken<List<RelSocialEnvironmentActivity>>() {
                                }.getType());
                                if (relSE != null) {
                                    for (RelSocialEnvironmentActivity r : relSE) {
                                        r.setActivity(activityRepository.findOne(r.getActivity().getId()));
                                        r.setSocialEnvironment(meeting.getSocialEnvironment());
                                        r.setRelId(null);
                                    }
                                    meeting.getSocialEnvironment().setRelSocialEnvironmentActivities(relSE);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println(e.getMessage());
                            }

                            break;
                    }
                    break;
                case "imputedHomes":
                    if (meeting.getImputedHomes() == null) {
                        meeting.setImputedHomes(new ArrayList<ImputedHome>());
                    }
                    Boolean exist = false;
                    for (ImputedHome ihA : meeting.getImputedHomes()) {
                        if (ihA.getIdAux().equals(fms.getIdFieldList())) {
                            exist = true;
                        }
                    }
                    if (!exist) {
                        ImputedHome add = new ImputedHome();
                        add.setIdAux(fms.getIdFieldList());
                        add.setMeeting(meeting);
                        meeting.getImputedHomes().add(add);

                    }
                    for (ImputedHome ih : meeting.getImputedHomes()) {
                        CatalogDto cdto = new CatalogDto();
                        if (ih.getIdAux().equals(fms.getIdFieldList())) {
                            switch (name[1]) {
                                case "address":
                                    AddressDto address = gson.fromJson(fms.getJsonValue(), AddressDto.class);
                                    if (address != null && address.getZipCode() != null) {
                                        ih.setAddress(new Address());
                                        ih.getAddress().setStreet(address.getStreet());
                                        ih.getAddress().setOutNum(address.getOutNum());
                                        ih.getAddress().setInnNum(address.getInnNum());
                                        ih.getAddress().setLocation(locationRepository.findLocationByZipCode(address.getZipCode()).get(0));
                                        ih.getAddress().setAddressString(ih.getAddress().toString());
                                    }

                                    break;
                                case "homeType":
                                    cdto = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                                    if (cdto != null)
                                        ih.setHomeType(homeTypeRepository.findOne(cdto.getId()));
                                    break;
                                case "registerType":
                                    cdto = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                                    if (cdto != null)
                                        ih.setRegisterType(registerTypeRepository.findOne(cdto.getId()));
                                    break;
                                case "timeLive":
                                    ih.setTimeLive(fms.getJsonValue());
                                    break;
                                case "reasonChange":
                                    if (!fms.getJsonValue().equals("")) {
                                        ih.setReasonChange(fms.getJsonValue());
                                    }
                                    break;
                                case "reasonSecondary":
                                    if (!fms.getJsonValue().equals("")) {
                                        ih.setReasonSecondary(fms.getJsonValue());
                                    }
                                    break;
                                case "description":
                                    if (!fms.getJsonValue().equals("")) {
                                        ih.setDescription(fms.getJsonValue());
                                    }
                                    break;
                                case "specification":
                                    if (!fms.getJsonValue().equals("")) {
                                        ih.setDescription(fms.getJsonValue());
                                    }
                                    break;
                                case "schedule":
                                    List<Schedule> listSchedules = gson.fromJson(fms.getJsonValue(), new TypeToken<List<Schedule>>() {
                                    }.getType());
                                    ih.setSchedule(listSchedules);
                                    for (Schedule schedule : listSchedules) {
                                        schedule.setId(null);
                                        schedule.setImputedHome(ih);
                                    }
                                    break;
                                case "phone":
                                    ih.setPhone(fms.getValue());
                                    break;
                            }
                            break;
                        }

                    }
                    break;
                case "socialNetwork":
                    if (meeting.getSocialNetwork() == null) {
                        meeting.setSocialNetwork(new SocialNetwork());
                        meeting.getSocialNetwork().setComment(meeting.getSocialNetwork().getComment());
                        meeting.getSocialNetwork().setPeopleSocialNetwork(new ArrayList<PersonSocialNetwork>());
                        meeting.getSocialNetwork().setMeeting(meeting);
                    }
                    if (fms.getIdFieldList() != null) {
                        Boolean existS = false;
                        for (PersonSocialNetwork personSocialNetwork : meeting.getSocialNetwork().getPeopleSocialNetwork()) {
                            if (personSocialNetwork.getIdAux().equals(fms.getIdFieldList())) {
                                existS = true;
                            }
                        }
                        if (!existS) {
                            PersonSocialNetwork psn = new PersonSocialNetwork();
                            psn.setIdAux(fms.getIdFieldList());
                            psn.setSocialNetwork(meeting.getSocialNetwork());
                            meeting.getSocialNetwork().getPeopleSocialNetwork().add(psn);
                        }
                        for (PersonSocialNetwork psn : meeting.getSocialNetwork().getPeopleSocialNetwork()) {

                            if (psn.getIdAux().equals(fms.getIdFieldList())) {
                                CatalogDto cDto = new CatalogDto();
                                switch (name[1]) {
                                    case "name":
                                        psn.setName(fms.getJsonValue());
                                        break;
                                    case "block":
                                        psn.setBlock(fms.getJsonValue().equals("1"));
                                        break;
                                    case "relationship":
                                        cDto = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                                        if (cDto != null) {
                                            psn.setRelationship(relationshipRepository.findOne(cDto.getId()));
                                        }
                                        break;
                                    case "specificationRelationship":
                                        if (!fms.getJsonValue().equals("")) {
                                            psn.setSpecificationRelationship(fms.getJsonValue());
                                        }
                                        break;
                                    case "phone":
                                        psn.setPhone(fms.getJsonValue());
                                        break;
                                    case "documentType":
                                        cDto = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                                        if (cDto != null) {
                                            psn.setDocumentType(documentTypeRepository.findOne(cDto.getId()));
                                        }
                                        break;
                                    case "specification":
                                        if (!fms.getJsonValue().equals("")) {
                                            psn.setSpecification(fms.getJsonValue());
                                        }
                                        break;
                                    case "age":
                                        psn.setAge(fms.getJsonValue());
                                        break;
                                    case "dependent":
                                        cDto = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                                        if (cDto != null) {
                                            psn.setDependent(electionRepository.findOne(cDto.getId()));
                                        }
                                        break;
                                    case "livingWith":
                                        cDto = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                                        if (cDto != null) {
                                            psn.setLivingWith(electionRepository.findOne(cDto.getId()));
                                        }
                                        break;
                                    case "address":
                                        if (!fms.getJsonValue().equals("")) {
                                            psn.setAddress(fms.getJsonValue());
                                        }
                                        break;
                                    case "isAccompaniment":
                                        Boolean ac = fms.getJsonValue().equals("1");
                                        psn.setIsAccompaniment(ac);
                                        break;
                                }
                                break;
                            }


                        }
                    } else if (name[1].equals("comment")) {
                        meeting.getSocialNetwork().setComment(fms.getValue());
                    }
                    break;
                case "references":
                    if (meeting.getReferences() == null) {
                        meeting.setReferences(new ArrayList<Reference>());
                    }
                    Boolean existR = false;
                    for (Reference reference : meeting.getReferences()) {
                        if (reference.getIdAux().equals(fms.getIdFieldList())) {
                            existR = true;
                        }
                    }
                    if (!existR) {
                        Reference ref = new Reference();
                        ref.setMeeting(meeting);
                        ref.setIdAux(fms.getIdFieldList());
                        meeting.getReferences().add(ref);
                    }
                    for (Reference r : meeting.getReferences()) {
                        if (r.getIdAux().equals(fms.getIdFieldList())) {
                            CatalogDto cDto = new CatalogDto();
                            switch (name[1]) {
                                case "fullName":
                                    r.setFullName(fms.getJsonValue());
                                    break;
                                case "block":
                                    r.setBlock(fms.getJsonValue().equals("1"));
                                    break;
                                case "relationship":
                                    cDto = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                                    if (cDto != null) {
                                        r.setRelationship(relationshipRepository.findOne(cDto.getId()));
                                    }
                                    break;
                                case "specificationRelationship":
                                    if (!fms.getJsonValue().equals("")) {
                                        r.setSpecificationRelationship(fms.getJsonValue());
                                    }
                                    break;
                                case "phone":
                                    r.setPhone(fms.getJsonValue());
                                    break;
                                case "documentType":
                                    cDto = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                                    if (cDto != null) {
                                        r.setDocumentType(documentTypeRepository.findOne(cDto.getId()));
                                    }
                                    break;
                                case "specification":
                                    if (!fms.getJsonValue().equals("")) {
                                        r.setSpecification(fms.getJsonValue());
                                    }
                                    break;
                                case "age":
                                    r.setAge(fms.getJsonValue());
                                    break;
                                case "address":
                                    r.setAddress(fms.getJsonValue());
                                    break;
                                case "isAccompaniment":
                                    Boolean ac = fms.getJsonValue().equals("1");
                                    r.setIsAccompaniment(ac);
                                    break;
                            }
                            break;
                        }
                    }
                    break;
                case "jobs":
                    if (meeting.getJobs() == null) {
                        meeting.setJobs(new ArrayList<Job>());
                    }
                    Boolean existJ = false;
                    for (Job job : meeting.getJobs()) {
                        if (job.getIdAux().equals(fms.getIdFieldList())) {
                            existJ = true;
                        }
                    }
                    if (!existJ) {
                        Job job = new Job();
                        job.setIdAux(fms.getIdFieldList());
                        job.setMeeting(meeting);
                        meeting.getJobs().add(job);
                    }
                    for (Job j : meeting.getJobs()) {
                        if (j.getIdAux().equals(fms.getIdFieldList())) {
                            CatalogDto cDto = new CatalogDto();
                            SimpleDateFormat formatter = new SimpleDateFormat(Constants.FORMAT_VERIFICATION_DATE);
                            switch (name[1]) {
                                case "company":
                                    j.setCompany(fms.getJsonValue());
                                    break;
                                case "block":
                                    j.setBlock(fms.getJsonValue().equals("1"));
                                    break;
                                case "post":
                                    j.setPost(fms.getJsonValue());
                                    break;
                                case "nameHead":
                                    j.setNameHead(fms.getJsonValue());
                                    break;
                                case "phone":
                                    j.setPhone(fms.getJsonValue());
                                    break;
                                case "address":
                                    j.setAddress(fms.getJsonValue());
                                    break;
                                case "registerType":
                                    cDto = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                                    if (cDto != null) {
                                        j.setRegisterType(registerTypeRepository.findOne(cDto.getId()));
                                    }
                                    break;
                                case "start":
                                    Date dateS = new Date();
                                    try {
                                        dateS = formatter.parse(fms.getJsonValue());
                                    } catch (ParseException e) {
                                        logException.Write(e, this.getClass(), "createMeetingVirified", sharedUserService);
                                    }
                                    j.setStart(dateS);
                                    break;
                                case "salaryWeek":
                                    j.setSalaryWeek(fms.getJsonValue());
                                    break;
                                case "startPrev":
                                    Date date = new Date();
                                    try {
                                        date = formatter.parse(fms.getJsonValue());
                                    } catch (ParseException e) {
                                        logException.Write(e, this.getClass(), "createMeetingVirified", sharedUserService);
                                    }
                                    j.setStartPrev(date);
                                    break;
                                case "end":
                                    Date dateE = new Date();
                                    try {
                                        dateE = formatter.parse(fms.getJsonValue());
                                    } catch (ParseException e) {
                                        logException.Write(e, this.getClass(), "createMeetingVirified", sharedUserService);
                                    }
                                    j.setEnd(dateE);
                                    break;
                                case "reasonChange":
                                    j.setReasonChange(fms.getJsonValue());
                                    break;
                                case "schedule":
                                    List<Schedule> listSchedules = gson.fromJson(fms.getJsonValue(), new TypeToken<List<Schedule>>() {
                                    }.getType());
                                    j.setSchedule(listSchedules);
                                    for (Schedule schedule : listSchedules) {
                                        schedule.setId(null);
                                        schedule.setJob(j);
                                    }
                                    break;
                            }
                            break;
                        }
                    }
                    break;
                case "school":
                    if (meeting.getSchool() == null) {
                        meeting.setSchool(new School());
                        meeting.getSchool().setMeeting(meeting);
                    }
                    switch (name[1]) {
                        case "name":
                            meeting.getSchool().setName(fms.getJsonValue());
                            break;
                        case "block":
                            meeting.getSchool().setBlock(fms.getJsonValue().equals("1"));
                            break;
                        case "phone":
                            meeting.getSchool().setPhone(fms.getJsonValue());
                            break;
                        case "address":
                            meeting.getSchool().setAddress(fms.getJsonValue());
                            break;
                        case "degree":
                            DegreeDto degreeDto = gson.fromJson(fms.getJsonValue(), DegreeDto.class);
                            if (degreeDto != null) {
                                meeting.getSchool().setDegree(degreeRepository.findOne(degreeDto.getId()));
                            }
                            break;
                        case "specification":
                            meeting.getSchool().setSpecification(fms.getJsonValue());
                            break;
                        case "schedule":
                            List<Schedule> listSchedules = gson.fromJson(fms.getJsonValue(), new TypeToken<List<Schedule>>() {
                            }.getType());
                            meeting.getSchool().setSchedule(listSchedules);
                            for (Schedule schedule : listSchedules) {
                                schedule.setId(null);
                                schedule.setSchool(meeting.getSchool());
                            }
                            break;
                    }
                    break;
                case "drugs":
                    if (meeting.getDrugs() == null) {
                        meeting.setDrugs(new ArrayList<Drug>());
                    }
                    Boolean existD = false;
                    for (Drug drug : meeting.getDrugs()) {
                        if (drug.getIdAux().equals(fms.getIdFieldList())) {
                            existD = true;
                        }
                    }
                    if (!existD) {
                        Drug drug = new Drug();
                        drug.setIdAux(fms.getIdFieldList());
                        drug.setMeeting(meeting);
                        meeting.getDrugs().add(drug);
                    }
                    for (Drug d : meeting.getDrugs()) {
                        if (d.getIdAux().equals(fms.getIdFieldList())) {
                            SimpleDateFormat formatter = new SimpleDateFormat(Constants.FORMAT_VERIFICATION_DATE);
                            CatalogDto cdtod = new CatalogDto();
                            switch (name[1]) {
                                case "drugType":
                                    cdtod = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                                    if (cdtod != null) {
                                        d.setDrugType(drugTypeRepository.findOne(cdtod.getId()));
                                    }
                                    break;
                                case "specificationType":
                                    if (!fms.getJsonValue().equals(""))
                                        d.setSpecificationType(fms.getJsonValue());
                                    break;
                                case "block":
                                    d.setBlock(fms.getJsonValue().equals("1"));
                                    break;
                                case "periodicity":
                                    cdtod = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                                    if (cdtod != null) {
                                        d.setPeriodicity(periodicityRepository.findOne(cdtod.getId()));
                                    }
                                    break;
                                case "specificationPeriodicity":
                                    if (!fms.getJsonValue().equals("")) {
                                        d.setSpecificationPeriodicity(fms.getJsonValue());
                                    }
                                    break;
                                case "quantity":
                                    d.setQuantity(fms.getJsonValue());
                                    break;
                                case "onsetAge":
                                    d.setOnsetAge(fms.getJsonValue());
                                    break;
                                case "lastUse":
                                    Date lastUse = new Date();
                                    try {
                                        lastUse = formatter.parse(fms.getJsonValue());
                                    } catch (ParseException e) {
                                        logException.Write(e, this.getClass(), "createMeetingVirified", sharedUserService);
                                    }
                                    d.setLastUse(lastUse);
                                    break;
                            }
                            break;
                        }
                    }
                    break;
                case "leaveCountry":
                    if (meeting.getLeaveCountry() == null) {
                        meeting.setLeaveCountry(new LeaveCountry());
                        meeting.getLeaveCountry().setMeeting(meeting);
                    }
                    CatalogDto cdtol = new CatalogDto();
                    switch (name[1]) {
                        case "officialDocumentation":
                            cdtol = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                            if (cdtol != null) {
                                meeting.getLeaveCountry().setOfficialDocumentation(electionRepository.findOne(cdtol.getId()));
                            }
                            break;
                        case "livedCountry":
                            cdtol = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                            if (cdtol != null) {
                                meeting.getLeaveCountry().setLivedCountry(electionRepository.findOne(cdtol.getId()));
                            }
                            break;
                        case "country":
                            cdtol = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                            if (cdtol != null) {
                                meeting.getLeaveCountry().setCountry(countryRepository.findOne(cdtol.getId()));
                            }
                            break;
                        case "state":
                            meeting.getLeaveCountry().setState(fms.getJsonValue());
                            break;
                        case "timeAgo":
                            meeting.getLeaveCountry().setTimeAgo(fms.getJsonValue());
                            break;
                        case "reason":
                            meeting.getLeaveCountry().setReason(fms.getJsonValue());
                            break;
                        case "address":
                            meeting.getLeaveCountry().setAddress(fms.getJsonValue());
                            break;
                        case "familyAnotherCountry":
                            cdtol = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                            if (cdtol != null) {
                                meeting.getLeaveCountry().setFamilyAnotherCountry(electionRepository.findOne(cdtol.getId()));
                            }
                            break;
                        case "communicationFamily":
                            cdtol = gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                            if (cdtol != null) {
                                meeting.getLeaveCountry().setCommunicationFamily(electionRepository.findOne(cdtol.getId()));
                            }
                            break;
                        case "media":
                            meeting.getLeaveCountry().setMedia(fms.getJsonValue());
                            break;
                        case "immigrationDocument":
                            cdtol = gson.fromJson(fms.getJsonValue(),CatalogDto.class);
                            if(cdtol != null){
                                meeting.getLeaveCountry().setImmigrationDocument(immigrationDocumentRepository.findOne(cdtol.getId()));
                            }
                            break;
                        case "specficationImmigranDoc":
                            meeting.getLeaveCountry().setSpecficationImmigranDoc(fms.getJsonValue());
                            break;
                        case "realtionship":
                            cdtol= gson.fromJson(fms.getJsonValue(), CatalogDto.class);
                            if(cdtol!=null){
                                meeting.getLeaveCountry().setRelationship(relationshipRepository.findOne(cdtol.getId()));
                            }
                            break;
                        case "specificationRelationship":
                            meeting.getLeaveCountry().setSpecificationRelationship(fms.getJsonValue());
                            break;
                        case "timeResidence":
                            meeting.getLeaveCountry().setTimeResidence(fms.getJsonValue());
                            break;
                    }
                    break;
            }
        }      ///end fill meeting verification
        //meeting.setCaseDetention(verification.getCaseDetention());
        meeting.setStatus(statusMeetingRepository.findByCode(Constants.S_MEETING_COMPLETE_VERIFICATION));
        verification.setMeetingVerified(meeting);
        //set status meeting y meetingtype
    }
}
