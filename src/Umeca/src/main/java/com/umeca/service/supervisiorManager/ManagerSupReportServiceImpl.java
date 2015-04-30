package com.umeca.service.supervisiorManager;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.model.catalog.Location;
import com.umeca.model.entities.supervisor.ManagerSupExcelReportInfo;
import com.umeca.model.entities.supervisor.ManagerSupReportParams;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.shared.ReportExcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManagerSupReportServiceImpl implements ManagerSupReportService {

    @Autowired
    ReportExcelRepository reportExcelRepository;

    public ManagerSupExcelReportInfo getCountByArrangements(ManagerSupReportParams params, ManagerSupExcelReportInfo info) {

        List<String> lstStatus = new ArrayList<String>() {{
            add(Constants.CASE_STATUS_CLOSED);
        }};

        //obtengo los casos con su ultimo formato registrado dentro del rango y que no este en los status
        List<Object> lstObjects;

        if (params.getDistrictId() != null)
            lstObjects = reportExcelRepository.getLastFormatInDatesByDistrict(params.getiDate(), params.geteDate(), lstStatus, params.getDistrictId());
        else
            lstObjects = reportExcelRepository.getLastFormatInDates(params.getiDate(), params.geteDate(), lstStatus);


//        List<Long> idsCases = new ArrayList<>();
        List<Long> idsFormats = new ArrayList<>();

        //obtengo los casos
        if (lstObjects != null && lstObjects.size() > 0) {
            for (int a = 0; a < lstObjects.size(); a++) {
                Object[] obj = (Object[]) lstObjects.get(a);
                idsFormats.add(Long.parseLong(obj[1].toString()));
            }
        }

        //total de casos encontrados con formato de audiencia registrado en el rango de fechas
        info.setTotArrangementCases(new Long(idsFormats.size()));

        if (idsFormats.isEmpty())
            idsFormats.add(-1L);

        //obtengo el conteo de casos por obligaciones para los formatos encontrados dento del rango de fechas
        List<Object> lstObjArrangement = reportExcelRepository.getCountCasesByArrangementInFormatsId(idsFormats);

        List<SelectList> finalResult = new ArrayList<>();

        if (lstObjArrangement != null && lstObjArrangement.size() > 0) {
            for (int a = 0; a < lstObjArrangement.size(); a++) {
                Object[] obj = (Object[]) lstObjArrangement.get(a);
                SelectList sl = new SelectList(Long.parseLong(obj[0].toString()), obj[1].toString());
                finalResult.add(sl);
            }
        }

        info.setLstCasesByArrangement(finalResult);

        return info;
    }

    public ManagerSupExcelReportInfo getCountByDrugs(ManagerSupReportParams params, ManagerSupExcelReportInfo info) {

        List<String> lstStatus = new ArrayList<String>() {{
            add(Constants.CASE_STATUS_CLOSED);
        }};

        //obtengo los casos con entrevista de encuadre termminada y que la tenga alguna droga registrada que no sea 'no consume'
        List<Object> lstObjects;

        if (params.getDistrictId() != null)
            lstObjects = reportExcelRepository.getCasesWithDrugsInFinishedFMByDistrict(params.getiDate(), params.geteDate(), lstStatus, params.getDistrictId());
        else
            lstObjects = reportExcelRepository.getCasesWithDrugsInFinishedFM(params.getiDate(), params.geteDate(), lstStatus);

        List<Long> idsCases = new ArrayList<>();

        //obtengo los casos
        if (lstObjects != null && lstObjects.size() > 0) {
            for (int a = 0; a < lstObjects.size(); a++) {
                Object[] obj = (Object[]) lstObjects.get(a);
                idsCases.add(Long.parseLong(obj[0].toString()));
            }
        }

        //total de casos encontrados con entrevista de encuadre finalizada en el rango de fechas
        info.setTotDrugsCases(new Long(idsCases.size()));

        if (idsCases.isEmpty())
            idsCases.add(-1L);

        List<Object> lstObjArrangement = reportExcelRepository.getCountCasesByDrugs(idsCases);

        List<SelectList> finalResult = new ArrayList<>();

        if (lstObjArrangement != null && lstObjArrangement.size() > 0) {
            for (int a = 0; a < lstObjArrangement.size(); a++) {
                Object[] obj = (Object[]) lstObjArrangement.get(a);
                SelectList sl = new SelectList(Long.parseLong(obj[0].toString()), obj[1].toString());
                finalResult.add(sl);
            }
        }

        info.setLstCasesByDrugs(finalResult);

        return info;
    }

    public ManagerSupExcelReportInfo getCountByJob(ManagerSupReportParams params, ManagerSupExcelReportInfo info) {

        List<String> lstStatus = new ArrayList<String>() {{
            add(Constants.CASE_STATUS_CLOSED);
        }};

        //obtengo los casos con entrevista de encuadre termminada y que la tenga alguna droga registrada que no sea 'no consume'
        List<Object> lstObjects;

        if (params.getDistrictId() != null)
            lstObjects = reportExcelRepository.getCasesWithJobInFinishedFMByDistrict(params.getiDate(), params.geteDate(), lstStatus, params.getDistrictId());
        else
            lstObjects = reportExcelRepository.getCasesWithJobInFinishedFM(params.getiDate(), params.geteDate(), lstStatus);

        List<Long> idsCases = new ArrayList<>();

        //obtengo los casos
        if (lstObjects != null && lstObjects.size() > 0) {
            for (int a = 0; a < lstObjects.size(); a++) {
                Object[] obj = (Object[]) lstObjects.get(a);
                idsCases.add(Long.parseLong(obj[0].toString()));
            }
        }

        //total de casos encontrados con entrevista de encuadre finalizada en el rango de fechas
        info.setTotJobCases(new Long(idsCases.size()));

        if (idsCases.isEmpty())
            idsCases.add(-1L);

        List<Object> lstObjArrangement = reportExcelRepository.getCountCasesByJob(idsCases);

        List<SelectList> finalResult = new ArrayList<>();

        if (lstObjArrangement != null && lstObjArrangement.size() > 0) {
            for (int a = 0; a < lstObjArrangement.size(); a++) {
                Object[] obj = (Object[]) lstObjArrangement.get(a);
                SelectList sl = new SelectList(Long.parseLong(obj[0].toString()), obj[1].toString());
                finalResult.add(sl);
            }
        }

        info.setLstCasesByJob(finalResult);

        return info;
    }

    public ManagerSupExcelReportInfo getCountClosedCases(ManagerSupReportParams params, ManagerSupExcelReportInfo info) {

        List<String> lstStatus = new ArrayList<String>() {{
            add(Constants.CASE_STATUS_CLOSED);
        }};

        //obtengo los casos en status de cierre y que hayan sido cerrados entre el rango de fechas
        List<Object> lstObjects;

        if (params.getDistrictId() != null)
            lstObjects = reportExcelRepository.getCountClosedCasesByDistrict(params.getiDate(), params.geteDate(), lstStatus, params.getDistrictId());
        else
            lstObjects = reportExcelRepository.getCountClosedCases(params.getiDate(), params.geteDate(), lstStatus);

        List<SelectList> finalResult = new ArrayList<>();
        Long sum = 0L;
        //obtengo los casos
        if (lstObjects != null && lstObjects.size() > 0) {
            for (int a = 0; a < lstObjects.size(); a++) {
                Object[] obj = (Object[]) lstObjects.get(a);
                Long val = Long.parseLong(obj[0].toString());
                sum += val;
                SelectList sl = new SelectList(val, obj[1].toString());
                finalResult.add(sl);
            }
        }

        //total de casos encontrados con entrevista de encuadre finalizada en el rango de fechas
        info.setTotClosedCases(sum);
        info.setLstClosedCases(finalResult);

        return info;
    }

    @Autowired
    private LocationRepository locationRepository;

    public ManagerSupExcelReportInfo getCountCasesByDetentionPlace(ManagerSupReportParams params, ManagerSupExcelReportInfo info) {

        List<String> lstStatus = new ArrayList<String>() {{
            add(Constants.CASE_STATUS_CLOSED);
        }};

        Object total = null;

        Location loc = locationRepository.findOne(params.getLocationId());

        info.setLocationName(loc.getMunicipality().getState().getName() + ", " + loc.getMunicipality().getName() + ", " + loc.getName());

        if (params.getDistrictId() != null)
            total = reportExcelRepository.getCountCasesByDetentionPlaceDistrict(params.getiDate(), params.geteDate(), lstStatus, params.getDistrictId(), params.getLocationId());
        else
            total = reportExcelRepository.getCountCasesByDetentionPlace(params.getiDate(), params.geteDate(), lstStatus, params.getLocationId());

        info.setTotCasesDetentionPlace(Long.parseLong(total.toString()));
        return info;
    }

}
