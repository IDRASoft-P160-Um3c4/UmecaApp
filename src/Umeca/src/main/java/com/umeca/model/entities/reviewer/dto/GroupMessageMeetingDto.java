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

    public GroupMessageMeetingDto(String section, List<String> messages) {
        this.section = section;
        this.messages = messages;
    }

    String section;
    List<String> messages;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
