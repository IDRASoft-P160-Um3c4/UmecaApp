package com.umeca.model.entities.director.view;

import com.umeca.model.catalog.MaritalStatus;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Vmware on 26/08/2014.
 */
public class ReportExcelFiltersDto {

    private List<Long> gender;
    private List<Long> maritalStIds;
    private List<Long> hasJob;
    private List<Long> academicLvlIds;
    private List<Long> drugsIds;
    private List<Long> riskLvlIds;

    private Long countryId;
    private Long stateId;
    private Long locationId;






    private Calendar initDate;
    private Calendar endDate;
    private List<Long> statusID;
    private Boolean findEvaluation;
    private Boolean findSupervision;


}
