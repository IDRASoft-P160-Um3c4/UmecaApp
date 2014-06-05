package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.reviewer.Case;

import java.sql.Time;
import java.util.Date;

public class HearingFormatView {

    public HearingFormatView() {


    }

    public HearingFormatView(Integer caseType, Case caseDet) {

        System.out.println(caseDet);


        switch (caseType) {
            //viene de suspension condicional
            case 1://no existe nada


                break;

            //viene de entrevista
            case 2://existe case y meeting,

                this.imputedName = caseDet.getMeeting().getImputed().getName();

                this.imputedFLastName = caseDet.getMeeting().getImputed().getLastNameP();

                this.imputedSLastName = caseDet.getMeeting().getImputed().getLastNameM();

                this.imputedBirthDate = caseDet.getMeeting().getImputed().getDateBirth();

                this.imputedTel = caseDet.getMeeting().getImputed().getCelPhone();

                break;

            case 3://cargar datos para mostrar existe todo

                this.imputedName = caseDet.getMeeting().getImputed().getName();

                this.imputedFLastName = caseDet.getMeeting().getImputed().getLastNameP();

                this.imputedSLastName = caseDet.getMeeting().getImputed().getLastNameM();

                this.imputedBirthDate = caseDet.getMeeting().getImputed().getDateBirth();

                this.imputedTel = caseDet.getMeeting().getImputed().getCelPhone();

                this.numberDate = caseDet.getHearingFormat().getNoDate();

                this.room = caseDet.getHearingFormat().getRoom();

                this.initTime = caseDet.getHearingFormat().getInitTime();

                this.endTime = caseDet.getHearingFormat().getEndTime();

                this.judgeName = caseDet.getHearingFormat().getJudgeName();

                this.mpName = caseDet.getHearingFormat().getMpName();

                this.defenderName = caseDet.getHearingFormat().getDefenderName();

                this.crimes = caseDet.getHearingFormat().getCrimes();

                this.additionalData = caseDet.getHearingFormat().getAdditionalData();

                this.controlDetention = caseDet.getHearingFormat().getHearingFormatSpecs().getControlDetention();

                this.hearingType = caseDet.getHearingFormat().getHearingFormatSpecs().getHearingType();

                this.imputationDate = caseDet.getHearingFormat().getHearingFormatSpecs().getImputationDate();

                this.linkageRoom = caseDet.getHearingFormat().getHearingFormatSpecs().getLinkageRoom();

                this.linkageDate = caseDet.getHearingFormat().getHearingFormatSpecs().getLinkageDate();

                this.linkageTime = caseDet.getHearingFormat().getHearingFormatSpecs().getLinkageTime();

                break;

        }


    }

    private String numberDate;

    private String room;

    private Time initTime;

    private Time endTime;

    private String judgeName;

    private String mpName;

    private String defenderName;

    private String imputedName;

    private String imputedFLastName;

    private String imputedSLastName;

    private Date imputedBirthDate;

    private String imputedTel;

    private String crimes;

    private String additionalData;

    private Integer controlDetention;

    private Integer hearingType;

    private Date imputationDate;

    private String linkageRoom;

    private Date linkageDate;

    private Time linkageTime;


}