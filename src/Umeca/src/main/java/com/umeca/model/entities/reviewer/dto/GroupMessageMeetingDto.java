package com.umeca.model.entities.reviewer.dto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 25/06/14
 * Time: 07:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupMessageMeetingDto {

    public GroupMessageMeetingDto(String section) {
        this.section = section;
    }

    public GroupMessageMeetingDto(String section, List<String> listString) {
        this.section = section;
        this.listString = listString;
    }

    String section;
    List<String> listString;
    String messages;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<String> getListString() {
        return listString;
    }

    public void setListString(List<String> listString) {
        this.listString = listString;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
