package com.umeca.service.supervisor;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.HearingFormatSpecs;
import com.umeca.model.entities.supervisor.HearingFormatView;
import com.umeca.repository.supervisor.HearingFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.org.mozilla.javascript.internal.EcmaError;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("hearingFormatService")
public class HearingFormatServiceImpl implements HearingFormatService {

    @Qualifier("qHearingFormatRepository")
    @Autowired
    HearingFormatRepository hearingFormatRepository;

    private SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");

    @Transactional
    @Override
    public ResponseMessage save(HearingFormat hearingFormat){

        ResponseMessage response = new ResponseMessage();
        try {
            hearingFormatRepository.save(hearingFormat);
            response.setHasError(false);
        }catch (Exception e){
            System.out.println("error al guardar hearingFormat!\n");
            System.out.println(e.getMessage());
            response.setHasError(true);
            response.setMessage(e.getMessage());
        }finally {
            return response;
        }
    }

    @Override
    public HearingFormat fillHearingFormatMeeting(HearingFormatView viewFormat, Case existCase) {

        HearingFormat hearingFormat = new HearingFormat();

        hearingFormat.setNoDate(viewFormat.getNumberDate());
        hearingFormat.setRegisterTimestamp(new Timestamp(new Date().getTime()));
        hearingFormat.setRoom(viewFormat.getRoom());
        hearingFormat.setInitTime(viewFormat.getInitTime());
        hearingFormat.setEndTime(viewFormat.getEndTime());
        hearingFormat.setJudgeName(viewFormat.getJudgeName());
        hearingFormat.setMpName(viewFormat.getMpName());
        hearingFormat.setDefenderName(viewFormat.getDefenderName());
        hearingFormat.setCrimes(viewFormat.getCrimes());
        hearingFormat.setAdditionalData(viewFormat.getAdditionalData());

        hearingFormat.setTerms("aaaaaaaaaa");
      //  hearingFormat.setTerms(viewFormat.getTerms()); descomentar
        HearingFormatSpecs hearingFormatSpecs = new HearingFormatSpecs();

        hearingFormatSpecs.setControlDetention(viewFormat.getControlDetention());
        hearingFormatSpecs.setImputationDate(viewFormat.getImputationDate());
        hearingFormatSpecs.setExtension(viewFormat.getExtension());
        hearingFormatSpecs.setLinkageDate(viewFormat.getLinkageDate());
        hearingFormatSpecs.setLinkageTime(viewFormat.getLinkageTime());
        hearingFormatSpecs.setLinkageRoom(viewFormat.getLinkageRoom());
        hearingFormatSpecs.setHearingType(viewFormat.getHearingType());

        //hearingFormatSpecs.setHearingFormat(hearingFormat);
        hearingFormat.setHearingFormatSpecs(hearingFormatSpecs);
        hearingFormat.setCaseDetention(existCase);

        return hearingFormat;
    }

    @Override
    public HearingFormat fillHearingFormatConditional(HearingFormatView viewFormat) {

        Case caseDetention = new Case();
        Meeting meeting = new Meeting();
        Imputed imputed = new Imputed();

        caseDetention.setIdFolder(viewFormat.getIdFolderCode());

        meeting.setCaseDetention(caseDetention);

        imputed.setName(viewFormat.getImputedName());
        imputed.setLastNameP(viewFormat.getImputedFLastName());
        imputed.setLastNameM(viewFormat.getImputedSLastName());

        try {

            Date brthDt = sdf.parse(viewFormat.getImputedBirthDate());

            imputed.setDateBirth(brthDt);
        }catch (Exception e){
            System.out.println("Error al parsear la fecha de nacimiento!");
            e.getMessage();
        }

        imputed.setCelPhone(viewFormat.getImputedTel());

        meeting.setImputed(imputed);

        caseDetention.setMeeting(meeting);

        HearingFormat hearingFormat = new HearingFormat();

        hearingFormat.setNoDate(viewFormat.getNumberDate());
        hearingFormat.setRegisterTimestamp(new Timestamp(new Date().getTime()));
        hearingFormat.setRoom(viewFormat.getRoom());
        hearingFormat.setInitTime(viewFormat.getInitTime());
        hearingFormat.setEndTime(viewFormat.getEndTime());
        hearingFormat.setJudgeName(viewFormat.getJudgeName());
        hearingFormat.setMpName(viewFormat.getMpName());
        hearingFormat.setCrimes(viewFormat.getCrimes());
        hearingFormat.setAdditionalData(viewFormat.getAdditionalData());
        hearingFormat.setTerms(viewFormat.getTerms());
        HearingFormatSpecs hearingFormatSpecs = new HearingFormatSpecs();

        hearingFormatSpecs.setControlDetention(viewFormat.getControlDetention());
        hearingFormatSpecs.setImputationDate(viewFormat.getImputationDate());
        hearingFormatSpecs.setExtension(viewFormat.getExtension());
        hearingFormatSpecs.setLinkageDate(viewFormat.getLinkageDate());
        hearingFormatSpecs.setLinkageTime(viewFormat.getLinkageTime());
        hearingFormatSpecs.setLinkageRoom(viewFormat.getLinkageRoom());
        hearingFormatSpecs.setHearingType(viewFormat.getHearingType());

        hearingFormatSpecs.setHearingFormat(hearingFormat);
        hearingFormat.setHearingFormatSpecs(hearingFormatSpecs);

        hearingFormat.setCaseDetention(caseDetention);

        return hearingFormat;
    }

    @Override
    public HearingFormatView fillForView(Case existCase){


        HearingFormatView hearingFormatView = new HearingFormatView();

        if(existCase.getHearingFormat()!=null) {

            hearingFormatView.setNumberDate(existCase.getHearingFormat().getNoDate());
            hearingFormatView.setRoom(existCase.getHearingFormat().getRoom());
            hearingFormatView.setInitTime(existCase.getHearingFormat().getInitTime());
            hearingFormatView.setEndTime(existCase.getHearingFormat().getEndTime());
            hearingFormatView.setJudgeName(existCase.getHearingFormat().getJudgeName());
            hearingFormatView.setMpName(existCase.getHearingFormat().getMpName());
            hearingFormatView.setDefenderName(existCase.getHearingFormat().getDefenderName());
            hearingFormatView.setCrimes(existCase.getHearingFormat().getCrimes());
            hearingFormatView.setAdditionalData(existCase.getHearingFormat().getAdditionalData());
            hearingFormatView.setTerms(existCase.getHearingFormat().getTerms());
            hearingFormatView.setControlDetention(existCase.getHearingFormat().getHearingFormatSpecs().getControlDetention());
            hearingFormatView.setHearingType(existCase.getHearingFormat().getHearingFormatSpecs().getHearingType());
            hearingFormatView.setImputationDate(existCase.getHearingFormat().getHearingFormatSpecs().getImputationDate());
            hearingFormatView.setLinkageRoom(existCase.getHearingFormat().getHearingFormatSpecs().getLinkageRoom());
            hearingFormatView.setLinkageDate(existCase.getHearingFormat().getHearingFormatSpecs().getLinkageDate());
            hearingFormatView.setLinkageTime(existCase.getHearingFormat().getHearingFormatSpecs().getLinkageTime());
            hearingFormatView.setExtension(existCase.getHearingFormat().getHearingFormatSpecs().getExtension());
            hearingFormatView.setCanSave(false);

        }else
            hearingFormatView.setCanSave(true);

        hearingFormatView.setImputedName(existCase.getMeeting().getImputed().getName());
        hearingFormatView.setImputedFLastName(existCase.getMeeting().getImputed().getLastNameP());
        hearingFormatView.setImputedSLastName(existCase.getMeeting().getImputed().getLastNameM());

        String strDate= sdf.format(existCase.getMeeting().getImputed().getDateBirth());

        hearingFormatView.setImputedBirthDate(strDate);
        hearingFormatView.setImputedTel(existCase.getMeeting().getImputed().getCelPhone());

        return hearingFormatView;
    }
}
