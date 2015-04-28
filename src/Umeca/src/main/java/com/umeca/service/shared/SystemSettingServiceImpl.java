package com.umeca.service.shared;

import com.umeca.model.entities.shared.SystemSetting;
import com.umeca.model.entities.shared.SystemSettingValues;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SharedSystemSetting;
import com.umeca.repository.shared.SystemSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

@Service
public class SystemSettingServiceImpl implements SystemSettingService {

    @Autowired
    SystemSettingRepository systemSettingsRepository;

    @Override
    public List<SystemSetting> findAllOfGroup(String group) {
        List<SystemSetting> lstSystemSettings = systemSettingsRepository.findAllOfGroup(group);
        return lstSystemSettings;
    }

    @Override
    public String findOneValue(String group, String key) {
        return systemSettingsRepository.findOneValue(group, key);
    }


    @Autowired
    SharedLogExceptionService logException;

    @Override
    public void initSystemSettings() {
        try{
            SharedSystemSetting.MonPlanHoursToAuthorize = Long.parseLong(findOneValue(Constants.SYSTEM_SETTINGS_MONPLAN, Constants.SYSTEM_SETTINGS_MONPLAN_HOURS_TO_AUTHORIZE));
        }catch (Exception e){
            logException.Write(e, this.getClass(), "initSystemSettings", "NA");
            SharedSystemSetting.MonPlanHoursToAuthorize = 72;
        }
    }


    private static SystemSettingValues _systemSettingValues;
    public SystemSettingValues getSystemSettingsValues(){

        if(_systemSettingValues != null){
            return _systemSettingValues;
        }

        _systemSettingValues = new SystemSettingValues();

        List<SystemSetting> lstSystemSettings = systemSettingsRepository.findAllOfGroup(Constants.SYSTEM_SETTINGS_SCHEDULE_HEARING);
        for(SystemSetting ss : lstSystemSettings){
            switch (ss.getKey()){
                case Constants.SYSTEM_SETTINGS_SCHEDULE_LST_IDS_ARRANGEMENT:
                    _systemSettingValues.setLstIdsArrangement(ConvertToLstLong(ss.getValue()));
                    break;
                case Constants.SYSTEM_SETTINGS_SCHEDULE_LST_IDS_ARRANGEMENT_REMINDER:
                    _systemSettingValues.setLstIdsArrangementReminder(ConvertToLstLong(ss.getValue()));
                    break;
                case Constants.SYSTEM_SETTINGS_SCHEDULE_HEARING_DAYS_BEFORE_FOR_REMINDER:
                    _systemSettingValues.setDaysBeforeForReminder(Integer.parseInt(ss.getValue()));
                    break;
                case Constants.SYSTEM_SETTINGS_SCHEDULE_HEARING_SUPERVISION_ACTIVITY_ID:
                    _systemSettingValues.setSupervisionActivityId(Long.parseLong(ss.getValue()));
                    break;
                case Constants.SYSTEM_SETTINGS_SCHEDULE_HEARING_SUPERVISION_ACTIVITY_ID_REMINDER:
                    _systemSettingValues.setSupervisionActivityIdReminder(Long.parseLong(ss.getValue()));
                    break;
                case Constants.SYSTEM_SETTINGS_SCHEDULE_HEARING_GOAL_ACTIVITY_ID:
                    _systemSettingValues.setGoalActivityId(Long.parseLong(ss.getValue()));
                    break;
                case Constants.SYSTEM_SETTINGS_SCHEDULE_HEARING_GOAL_ACTIVITY_ID_REMINDER:
                    _systemSettingValues.setGoalActivityIdReminder(Long.parseLong(ss.getValue()));
                    break;
            }
        }



        return _systemSettingValues;
    }

    private List<Long> ConvertToLstLong(String value) {
        String [] arrValues = value.split(",");
        List<Long> lstValues = new ArrayList<>();

        for(String sValue : arrValues){
            lstValues.add(Long.parseLong(sValue));
        }
        return lstValues;
    }
}
