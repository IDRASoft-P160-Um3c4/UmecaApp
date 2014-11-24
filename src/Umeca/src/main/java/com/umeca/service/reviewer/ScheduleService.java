package com.umeca.service.reviewer;

import com.umeca.model.catalog.dto.ScheduleLogDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 19/05/14
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ScheduleService {
    Object getSchedules(Long id, Object classObjetc);
    Boolean saveSchedules(String schedules, Long id, Class classType);

    String getSchedulesVerificationValue(Long id, Object classObjetc);

    List<ScheduleLogDto> getFramingScheduleByIdCase(Long id);
}
