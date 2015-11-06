package com.umeca.service.shared;

import com.umeca.model.entities.director.view.ReportExcelFiltersDto;
import com.umeca.model.entities.supervisor.ReportExcelSummary;
import com.umeca.repository.shared.ReportExcelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DeveloperII on 04/11/2015.
 */

@Service("excelReportService")
public class ExcelReportServiceImpl implements ExcelReportService {

    private ReportExcelRepository reportExcelRepository;

    @Override
    public List<Long> findCasesByFilters(List<Long> idsCasesInDateRange, ReportExcelFiltersDto filtersDto) {
        List<Long> idsStatusCase = null;
        List<Long> idsStatusMeeting = null;
        List<Long> idsStatusVerfi = null;

        List<Long> idsGender = null;
        List<Long> idsMartialSt = null;

        List<Long> idsAcademicLvl = null;
        List<Long> idsActualJob = null;
        List<Long> idsDrugs = null;
        List<Long> idsRiskLvl = null;
        List<Long> idsHearingType = null;
        List<Long> idsWithMonP = null;
        List<Long> idsHomePlace = null;

        List<Long> idsCrimesInLegal = null;
        List<Long> idsCrimesInFormat = null;
        List<Long> idsFinalCrimes = null;

        List<Long> idsArrangements = null;
        List<Long> idsActivities = null;

        List<Long> finalIds = null;

        if (idsCasesInDateRange.size() > 0) {

            if (filtersDto.getLstGenderBool().size() > 0) {
                List<Integer> lstGenderInt = new ArrayList<>();

                for (Boolean act : filtersDto.getLstGenderBool()) {
                    if (act == true)
                        lstGenderInt.add(1);
                    else if (act == false)
                        lstGenderInt.add(2);
                }

                idsGender = reportExcelRepository.findIdCasesByGender(filtersDto.getLstGenderBool(), lstGenderInt, idsCasesInDateRange);

            }

            if (filtersDto.getLstStatusCase().size() > 0) {
                idsStatusCase = reportExcelRepository.findIdCasesByStatusCase(filtersDto.getLstStatusCase(), idsCasesInDateRange);
            }

            if (filtersDto.getLstStatusMeeting().size() > 0) {
                idsStatusMeeting = reportExcelRepository.findIdCasesByStatusMeeting(filtersDto.getLstStatusMeeting(), idsCasesInDateRange);
            }

            if (filtersDto.getLstStatusVerification().size() > 0) {
                idsStatusVerfi = reportExcelRepository.findIdCasesByStatusVerification(filtersDto.getLstStatusVerification(), idsCasesInDateRange);
            }

            if (filtersDto.getLstMaritalSt().size() > 0) {
                idsMartialSt = reportExcelRepository.findIdCasesByMaritalSt(filtersDto.getLstMaritalSt(), idsCasesInDateRange);
            }

            if (filtersDto.getHasJob() != null && filtersDto.getHasJob() == true) {
                idsActualJob = reportExcelRepository.findIdCasesWithActualJob(idsCasesInDateRange);
            }

            if (filtersDto.getLstAcademicLvl().size() > 0) {
                idsAcademicLvl = reportExcelRepository.findIdCasesByAcademicLvl(filtersDto.getLstAcademicLvl(), idsCasesInDateRange);
            }

            if (filtersDto.getLstDrugs().size() > 0) {
                idsDrugs = reportExcelRepository.findIdCasesByDrugs(filtersDto.getLstDrugs(), idsCasesInDateRange);
            }

            if (filtersDto.getLstLvlRisk().size() > 0) {
                idsRiskLvl = reportExcelRepository.findIdCasesByRiskLvl(filtersDto.getLstLvlRisk(), idsCasesInDateRange);
            }

            if (filtersDto.getLstHearingType().size() > 0) {
                idsHearingType = reportExcelRepository.findIdCasesByHearingType(filtersDto.getLstHearingType(), idsCasesInDateRange);
            }

            if (filtersDto.getHasMonP() != null && filtersDto.getHasMonP() == true) {
                idsWithMonP = reportExcelRepository.findIdCasesWithMonP(idsCasesInDateRange);
            }

            if (filtersDto.getHomePlace() != null && filtersDto.getHomePlace() == true) {
                idsHomePlace = reportExcelRepository.findIdCasesByLocation(idsCasesInDateRange, filtersDto.getIdLoc());
            }

            if (filtersDto.getLstCrime().size() > 0) {
                idsCrimesInLegal = reportExcelRepository.findIdCasesByCrimesInLegal(idsCasesInDateRange, filtersDto.getLstCrime());
            }

            if (filtersDto.getLstCrime().size() > 0) {
                idsCrimesInFormat = reportExcelRepository.findIdCasesByCrimesInFormat(idsCasesInDateRange, filtersDto.getLstCrime());
            }

            if (filtersDto.getLstActivities().size() > 0) {
                idsActivities = reportExcelRepository.findIdCasesByMonitoringActivities(idsCasesInDateRange, filtersDto.getLstActivities());
            }

            if (filtersDto.getLstArrangement().size() > 0) {
                idsArrangements = reportExcelRepository.findIdCasesByArrangements(idsCasesInDateRange, filtersDto.getLstArrangement());
            }

            //intersecciones de las listas
            finalIds = idsCasesInDateRange;

            if (idsGender != null) {
                finalIds = this.intersectIds(idsCasesInDateRange, idsGender);
            }

            if (idsStatusCase != null) {
                finalIds = this.intersectIds(finalIds, idsStatusCase);
            }

            if (idsStatusMeeting != null) {
                finalIds = this.intersectIds(finalIds, idsStatusMeeting);
            }

            if (idsStatusVerfi != null) {
                finalIds = this.intersectIds(finalIds, idsStatusVerfi);
            }

            if (idsMartialSt != null) {
                finalIds = this.intersectIds(finalIds, idsMartialSt);
            }

            if (idsActualJob != null) {
                finalIds = this.intersectIds(finalIds, idsActualJob);
            }

            if (idsDrugs != null) {
                finalIds = this.intersectIds(finalIds, idsDrugs);
            }

            if (idsAcademicLvl != null) {
                finalIds = this.intersectIds(finalIds, idsAcademicLvl);
            }

            if (idsRiskLvl != null) {
                finalIds = this.intersectIds(finalIds, idsRiskLvl);
            }

            if (idsRiskLvl != null) {
                finalIds = this.intersectIds(finalIds, idsRiskLvl);
            }

            if (idsHearingType != null) {
                finalIds = this.intersectIds(finalIds, idsHearingType);
            }

            if (idsWithMonP != null) {
                finalIds = this.intersectIds(finalIds, idsWithMonP);
            }

            if (idsHomePlace != null) {
                finalIds = this.intersectIds(finalIds, idsHomePlace);
            }

            if (idsCrimesInLegal != null && idsCrimesInFormat != null) {
                idsFinalCrimes = this.conjunctionIds(idsCrimesInLegal, idsCrimesInFormat);
            }

            if (idsFinalCrimes != null) {
                finalIds = this.intersectIds(finalIds, idsFinalCrimes);
            }

            if (idsActivities != null) {
                finalIds = this.intersectIds(finalIds, idsActivities);
            }

            if (idsArrangements != null) {
                finalIds = this.intersectIds(finalIds, idsArrangements);
            }
        }

        return finalIds;
    }

    @Override
    public List<Long> intersectIds(List<Long> listA, List<Long> listB) {
        List<Long> intersectList = new ArrayList<>();

        for (Long act : listB) {
            if (listA.contains(act))
                intersectList.add(act);
        }

        return intersectList;
    }

    @Override
    public List<Long> conjunctionIds(List<Long> listA, List<Long> listB) {
        List<Long> conjunctionList = new ArrayList<>();

        for (Long act : listA) {
            conjunctionList.add(act);
        }

        for (Long act : listB) {
            if (!listA.contains(act))
                conjunctionList.add(act);
        }

        return conjunctionList;
    }

    @Override
    public ReportExcelSummary fillSummary(String filters) {
        ReportExcelSummary summary = new ReportExcelSummary();

        return summary;
    }
}
