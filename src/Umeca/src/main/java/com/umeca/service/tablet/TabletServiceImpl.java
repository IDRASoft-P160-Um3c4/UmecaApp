package com.umeca.service.tablet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.dto.tablet.*;
import com.umeca.model.dto.tablet.catalog.TabletCountryDto;
import com.umeca.model.dto.tablet.catalog.TabletStatusCaseDto;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.HearingFormatView;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.reviewer.AddressRepository;
import com.umeca.service.supervisor.HearingFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("tabletService")
public class TabletServiceImpl implements TabletService {

    @Autowired
    CaseRepository caseRepository;

    @Autowired
    HearingFormatService hearingFormatService;

    @Autowired
    AddressRepository addressRepository;

    private TabletCaseDto getCaseDataByCaseId(Long caseId) {
        return caseRepository.getInfoCaseByCaseId(caseId);
    }

    private TabletStatusCaseDto getStatusCaseByCaseId(Long caseId) {
        return caseRepository.getStatusCaseByCaseId(caseId);
    }

    private TabletImputedDto getImputedDataByCaseId(Long caseId) {
        TabletImputedDto currentImputed = caseRepository.getImputedDataByCaseId(caseId);
        currentImputed.setMaritalStatus(caseRepository.getImputedMaritalStatusByCaseId(caseId));
        currentImputed.setBirthCountry(caseRepository.getImputedBirthCountryByCaseId(caseId));
        currentImputed.setLocation(caseRepository.getImputedLocationByCaseId(caseId));
        return currentImputed;
    }

    private TabletSocialNetworkDto getSocialNetworkByCaseId(Long caseId) {
        TabletSocialNetworkDto currentSN = caseRepository.getSocialNetworkByCaseId(caseId);

        if (currentSN != null) {
            List<TabletPersonSocialNetworkDto> lst = caseRepository.getPeopleSocialNetworkByCaseId(caseId);

            for (TabletPersonSocialNetworkDto curr : lst) {
                curr.setRelationship(caseRepository.getRelationshipByPersonId(curr.getId()));
                curr.setDocumentType(caseRepository.getDocumentTypeByPersonId(curr.getId()));
                curr.setDependent(caseRepository.getDependentByPersonId(curr.getId()));
                curr.setLivingWith(caseRepository.getLivingWithByPersonId(curr.getId()));
            }

            currentSN.setPeopleSocialNetwork(lst);
        }

        return currentSN;
    }

    private TabletSchoolDto getSchoolByCaseId(Long caseId) {

        TabletSchoolDto currSch = caseRepository.getSchoolByCaseId(caseId);
        if (currSch != null) {
            currSch.setDegree(caseRepository.getDegreeBySchoolId(currSch.getId()));
            currSch.setSchedule(caseRepository.getScheduleSchoolId(caseId));
        }

        return currSch;
    }

    private TabletSocialEnvironmentDto getSocialEnvironmentByCaseId(Long caseId) {
        TabletSocialEnvironmentDto se = caseRepository.getSocialEnvironmentByCaseId(caseId);

        if (se != null) {
            se.setRelSocialEnvironmentActivities(caseRepository.getRelSocialEnvironmentActivityBySocialEnvId(se.getId()));
        }

        return se;
    }

    private List<TabletImputedHomeDto> getImputedHomeByCaseId(Long caseId) {
        List<TabletImputedHomeDto> lst = caseRepository.getImputedHomeByCaseId(caseId);

        if (lst != null && lst.size() > 0) {
            for (TabletImputedHomeDto currIH : lst) {
                currIH.setSchedule(caseRepository.getScheduleByImputedHomeId(currIH.getId()));
            }
        }
        return lst;
    }

    private List<TabletJobDto> getJobByCaseId(Long caseId) {
        List<TabletJobDto> lst = caseRepository.getJobByCaseId(caseId);

        if (lst != null && lst.size() > 0) {
            for (TabletJobDto currJ : lst) {
                currJ.setSchedule(caseRepository.getScheduleByJobId(currJ.getId()));
            }
        }
        return lst;
    }

    private TabletVerificationDto getVerificationByCaseId(Long caseId) {
        TabletVerificationDto currVer = caseRepository.getVerificationByCaseId(caseId);

        if (currVer != null) {
            List<TabletSourceVerificationDto> lst = caseRepository.getSourceVerificationByVerificationId(currVer.getId());

            if (lst != null && lst.size() > 0) {
                for (TabletSourceVerificationDto currSV : lst) {
                    currSV.setFieldMeetingSourceList(caseRepository.getFieldMeetingSourceBySourceId(currSV.getId()));
                }
            }

            currVer.setSourceVerifications(lst);
        }

        return currVer;
    }

    private TabletMeetingDto getMeetingDataByCaseId(Long caseId) {

        TabletMeetingDto currentMeeting = caseRepository.getMeetingDataByCaseId(caseId);
        currentMeeting.setStatus(caseRepository.getStatusMeetingDataByCaseId(caseId));
        currentMeeting.setReviewer(caseRepository.getUserMeetingDataByCaseId(caseId));
        currentMeeting.setImputed(this.getImputedDataByCaseId(caseId));
        currentMeeting.setSocialNetwork(this.getSocialNetworkByCaseId(caseId));
        currentMeeting.setSchool(this.getSchoolByCaseId(caseId));
        currentMeeting.setSocialEnvironment(this.getSocialEnvironmentByCaseId(caseId));
        currentMeeting.setLeaveCountry(caseRepository.getLeaveCountryByCaseId(caseId));
        currentMeeting.setReferences(caseRepository.getReferencesByCaseId(caseId));
        currentMeeting.setImputedHomes(this.getImputedHomeByCaseId(caseId));
        currentMeeting.setJobs(this.getJobByCaseId(caseId));
        currentMeeting.setDrugs(caseRepository.getDrugByCaseId(caseId));

        return currentMeeting;
    }

    private List<TabletHearingFormatDto> getHearingFormatByCaseId(Long caseId) {
        List<TabletHearingFormatDto> lstHF = caseRepository.getLastHearingFormatByCaseId(caseId, new PageRequest(0, 1));

        if (lstHF != null && lstHF.size() > 0) {
            TabletHearingFormatDto currHF = lstHF.get(0);
            currHF.setAssignedArrangements(caseRepository.getAssignedArrangementByFormatId(currHF.getId()));
            currHF.setContacts(caseRepository.getContactsByFormatId(currHF.getId()));
            currHF.setCrimeList(caseRepository.getCrimesByFormatId(currHF.getId()));
        } else {
            HearingFormatView hfv = hearingFormatService.fillNewHearingFormatForView(caseId);
            TabletHearingFormatDto hf = new TabletHearingFormatDto(hfv);

            if (hfv.getIdAddres() != null) {
                TabletAddressDto tabletAddressDto = new TabletAddressDto(addressRepository.findOne(hfv.getIdAddres()));
                hf.getHearingImputed().setAddress(tabletAddressDto);
            }

            if (hfv.getListCrime() != null) {
                List<TabletCrimeDto> lstCrime = new ArrayList<>();
                lstCrime = new Gson().fromJson(hfv.getListCrime(), new TypeToken<List<TabletCrimeDto>>() {
                }.getType());

                hf.setCrimeList(lstCrime);
            }

            lstHF.add(hf);
        }
        return lstHF;
    }


    public TabletCaseDto getAllCaseByIdCase(Long idCase) {
        try {
            TabletCaseDto currentCase = this.getCaseDataByCaseId(idCase);
            currentCase.setStatus(this.getStatusCaseByCaseId(idCase));
            currentCase.setMeeting(this.getMeetingDataByCaseId(idCase));
            currentCase.setVerification(this.getVerificationByCaseId(idCase));
            currentCase.setHearingFormats(this.getHearingFormatByCaseId(idCase));
            return currentCase;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
