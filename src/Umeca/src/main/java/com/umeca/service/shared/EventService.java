package com.umeca.service.shared;

public interface EventService {


    void addEvent(String eventCode, Long idCase, Object details);
    Boolean caseHasEvent(String eventCode, Long idCase);
}