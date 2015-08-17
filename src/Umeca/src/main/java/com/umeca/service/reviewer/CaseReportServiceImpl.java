package com.umeca.service.reviewer;

import com.umeca.infrastructure.Convert;
import com.umeca.model.entities.reviewer.FieldMeetingSource;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.reviewer.View.CaseReportView;
import com.umeca.model.entities.reviewer.View.Section;
import com.umeca.model.entities.reviewer.View.TechnicalReviewInfoFileAllSourcesView;
import com.umeca.model.entities.reviewer.dto.SourceVerificationDto;
import com.umeca.model.entities.shared.Event;
import com.umeca.model.shared.Constants;
import com.umeca.repository.EventRepository;
import com.umeca.repository.reviewer.FieldMeetingSourceRepository;
import com.umeca.repository.reviewer.SourceVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("caseReportService")
public class CaseReportServiceImpl implements CaseReportService {


    @Autowired
    EventRepository eventRepository;

    @Autowired
    SourceVerificationRepository sourceVerificationRepository;

    @Autowired
    FieldMeetingSourceRepository fieldMeetingSourceRepository;

    @Override
    public CaseReportView getCaseReportSheetById(Long id) {
        return eventRepository.getCaseReportById(id);
    }

    @Override
    public TechnicalReviewInfoFileAllSourcesView fillInfoFileAllSourcesFromEvent(Long id) {
        TechnicalReviewInfoFileAllSourcesView file = new TechnicalReviewInfoFileAllSourcesView();
        Event event = eventRepository.findOne(id);

        Meeting meeting = event.getCaseDetention().getMeeting();
        Imputed im = meeting.getImputed();
        file.setIdFolder(Convert.convertToValidString(event.getCaseDetention().getIdFolder()));
        file.setName(Convert.convertToValidString(im.getName()));
        file.setLastNameP(Convert.convertToValidString(im.getLastNameP()));
        file.setLastNameM(Convert.convertToValidString(im.getLastNameM()));

        file.setAddress(Convert.convertToValidString(meeting.getImputedHomes().get(0).getAddress().getAddressString()));
        String template = "Campo: {0} <br/>Valor: {1}";
        Long idCase = event.getCaseDetention().getId();
        List<Long> sourcesId = sourceVerificationRepository.getAllSourcesByCase(idCase);

        for (Long idSource : sourcesId) {
            SourceVerificationDto sv = new SourceVerificationDto();
            sv.dtoSourceVerification(sourceVerificationRepository.findOne(idSource));
            sv.setSections(new ArrayList<Section>());
            for (int i = 0; i < Constants.NAMES_MEETING.length; i++) {
                List<FieldMeetingSource> fieldMeetingSources = fieldMeetingSourceRepository.getFieldMeetingBySource(idCase, idSource, Constants.ST_FIELD_VERIF_UNABLE, (i + 1));
                if (fieldMeetingSources != null && fieldMeetingSources.size() > 0) {
                    Section s = new Section(fieldMeetingSources.get(0).getFieldVerification().getSection());
                    List<String> messages = new ArrayList<>();
                    for (FieldMeetingSource fms : fieldMeetingSources) {
                        String finalString = template.replace("{0}", Convert.convertToValidString(fms.getFieldVerification().getFieldName()));
                        finalString = finalString.replace("{1}", Convert.convertToValidString(fms.getValue()));
                        //finalString = finalString;
                        messages.add(finalString);
                    }
                    s.setValues(messages);
                    sv.getSections().add(s);

                }
            }

            file.getSources().add(sv);

        }
        return file;
    }
}
