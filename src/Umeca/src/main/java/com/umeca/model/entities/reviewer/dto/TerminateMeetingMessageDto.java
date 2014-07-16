package com.umeca.model.entities.reviewer.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 25/06/14
 * Time: 07:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class TerminateMeetingMessageDto {

    List<GroupMessageMeetingDto> groupMessage;
    public final String template = "entity es un campo requerido." ;
    public final String templateVerification = "De entity field falta por ser verificado." ;
    public final String templateVerificationSingle = "entity falta por ser verificado." ;

    public TerminateMeetingMessageDto() {
        groupMessage = new ArrayList<>();
    }

    public List<GroupMessageMeetingDto> getGroupMessage() {
        return groupMessage;
    }

    public void setGroupMessage(List<GroupMessageMeetingDto> groupMessage) {
        this.groupMessage = groupMessage;
    }


    public boolean  existsMessageProperties(){
        for(GroupMessageMeetingDto g : groupMessage){
            if(g.getMessages().size()>0){
                return true;
            }
        }
        return false;
    }
}
