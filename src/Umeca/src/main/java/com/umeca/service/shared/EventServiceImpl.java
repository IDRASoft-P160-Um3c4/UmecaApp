package com.umeca.service.shared;


import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.shared.Event;
import com.umeca.model.shared.Constants;
import com.umeca.repository.EventRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.EventTypeRepository;
import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventTypeRepository eventTypeRepository;
    @Autowired
    SharedLogExceptionService logException;


    @Override
    public void addEvent(String eventCode, Long idCase, Object details) {

        try {
            Long idUser = sharedUserService.GetLoggedUserId();
            User user = userRepository.findOne(idUser);
            Case cd = new Case();
            cd.setId(idCase);

            Event event = new Event();


            event.setCaseDetention(cd);

            if (details != null)
                event.setComments((String) details);

            event.setUser(user);

            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            Integer dateId = Integer.parseInt(df.format(date));

            event.setDate(date);
            event.setDateId(dateId);

            event.setEventType(eventTypeRepository.findByCode(eventCode));
            eventRepository.save(event);

        } catch (Exception e) {
            logException.Write(e, this.getClass(), "addEvent", sharedUserService);
            e.printStackTrace();

        }
    }

    @Override
    public Boolean caseHasEvent(String eventCode, Long idCase) {
        List<Event> events;
        try{
            events = eventRepository.getEventsFromCase(idCase);
            for(Event event : events){
                if(event.getEventType().getName().equals(eventCode))
                {
                    return true;
                }
            }
        }
        catch (Exception e){
            logException.Write(e, this.getClass(), "addEvent", sharedUserService);
            e.printStackTrace();

        }
        return false;
    }
}
