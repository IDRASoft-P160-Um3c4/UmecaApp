package com.umeca.service.shared;

public interface EventService {

    void addEventTablet(String eventCode, Long idCase, Object details,Long userId);
    void addEvent(String eventCode, Long idCase, Object details);
    Boolean caseHasEvent(String eventCode, Long idCase);
}