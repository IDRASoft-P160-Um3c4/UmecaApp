package com.umeca.service.shared;


import com.google.gson.Gson;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.entities.reviewer.Crime;
import com.umeca.model.entities.reviewer.dto.CrimeDto;
import com.umeca.model.entities.supervisor.SupervisionLogReport;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.reviewer.CrimeRepository;
import com.umeca.repository.supervisor.*;
import com.umeca.service.reviewer.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogCaseServiceImpl implements LogCaseService {

    @Autowired
    HearingFormatRepository hearingFormatRepository;
    @Autowired
    FramingReferenceRepository framingReferenceRepository;
    @Autowired
    MonitoringPlanRepository monitoringPlanRepository;
    @Autowired
    ArrangementRepository arrangementRepository;
    @Autowired
    SupervisionActivityRepository supervisionActivityRepository;
    @Autowired
    ActivityGoalRepository activityGoalRepository;
    @Autowired
    CrimeRepository crimeRepository;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    FramingMeetingRepository framingMeetingRepository;

    @Override
    public void fillgeneralDataLog(Long caseId, ModelAndView model) {
        Long lHearingFormatId = hearingFormatRepository.lastHearingFormatIdsByIdCase(caseId);
        SupervisionLogReport slr = hearingFormatRepository.findSupervisionLogReportById(lHearingFormatId);
        Long id = monitoringPlanRepository.getMonPlanIdByCaseId(caseId);
        List<Crime> listCrime = crimeRepository.findListCrimeHearingFormatByIdHF(lHearingFormatId);
        List<String> listCrimeDtos = new ArrayList<>();
        for(Crime c: listCrime){
            listCrimeDtos.add(new CrimeDto().toStringCrime(c));
        }
        Gson gson = new Gson();
        model.addObject("imputedName", slr.getImputedName());
        model.addObject("mpId", id);
        model.addObject("crime", gson.toJson(listCrimeDtos));
        model.addObject("judge", slr.getJudge());
        model.addObject("defender", slr.getDefender());
        model.addObject("mp", slr.getMp());
        model.addObject("imputedTel", slr.getImputedTel());
        model.addObject("imputedAddr", slr.getImputedAddr());
        List<SelectList> lstMoral = framingReferenceRepository.findAccompanimentReferences(id);

        String cad = "";
        for (SelectList act : lstMoral) {
            if (cad != "")
                cad += " / ";
            cad += act.getName() + " - " + act.getDescription();
        }

        model.addObject("moralName", cad);
        //model.addObject("moralPhone", slr.getMoralPhone());

        List<SelectList> lstResol = hearingFormatRepository.getInfoResolution(id);

        String lastResol = "", allResol = "";

        if (lstResol != null && lstResol.size() > 0) {
            for (int i = 0; i < lstResol.size(); i++) {

                if (allResol != "")
                    allResol += " , ";

                allResol += CalendarExt.calendarToFormatString(lstResol.get(i).getCalendar(), Constants.FORMAT_CALENDAR_I);

                if (lstResol.get(i).getIdAux() == HearingFormatConstants.HEARING_TYPE_MC)
                    allResol += " - MC";
                else if (lstResol.get(i).getIdAux() == HearingFormatConstants.HEARING_TYPE_SCP)
                    allResol += " - SCPP";
            }
        }

        String[] arr = allResol.split(",");
        if (arr.length > 0)
            lastResol = arr[arr.length - 1];
        else
            lastResol = allResol;

        model.addObject("prevResolution", allResol);
        model.addObject("lastResolution", lastResol);

        String closeComment = "";
        closeComment = monitoringPlanRepository.getCloseComment(id, MonitoringConstants.STATUS_PENDING_END, Constants.CASE_STATUS_CLOSED);

        model.addObject("closeComment", closeComment);

        List<SelectList> lstGeneric = arrangementRepository.findLstArrangementByCaseId(caseId);

        String sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstHfAssignedArrangement", sLstGeneric);

        lstGeneric = supervisionActivityRepository.findByMonPlanId(id);
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstActivities", sLstGeneric);

        lstGeneric = activityGoalRepository.findByMonPlanId(id);
        sLstGeneric = gson.toJson(lstGeneric);
        model.addObject("lstGoals", sLstGeneric);

        model.addObject("schedules",gson.toJson(scheduleService.getFramingScheduleByIdCase(caseId)));

        model.addObject("lstRisk", gson.toJson(framingMeetingRepository.getSelectedTRiskByIdCase(caseId)));
        model.addObject("lstThreat", gson.toJson(framingMeetingRepository.getSelectedThreatByIdCase(caseId)));


    }


}
