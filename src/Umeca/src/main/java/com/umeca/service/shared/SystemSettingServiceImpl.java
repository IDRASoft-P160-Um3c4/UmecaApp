package com.umeca.service.shared;

import com.umeca.model.entities.shared.SystemSetting;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SharedSystemSetting;
import com.umeca.repository.shared.SystemSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        /*HashMap<String, String> hmSystemSettings = new HashMap<>();

        for(SystemSetting systemSetting : lstSystemSettings){
            hmSystemSettings.put(systemSetting.getKey(), systemSetting.getValue());
        }

        return hmSystemSettings;    */
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
}
