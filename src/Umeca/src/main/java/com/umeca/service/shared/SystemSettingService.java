package com.umeca.service.shared;

import com.umeca.model.entities.shared.SystemSetting;
import java.util.List;

public interface SystemSettingService {
    List<SystemSetting> findAllOfGroup(String group);
    String findOneValue(String group, String key);
    void initSystemSettings();
}
