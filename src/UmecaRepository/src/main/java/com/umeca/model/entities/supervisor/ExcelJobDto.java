package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.dto.ScheduleDto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Vmware on 18/08/2014.
 */
public class ExcelJobDto {

    private Long idCase;
    private Long id;
    private String post;
    private String nameHead;
    private String company;
    private String phone;
    private Date startPrev;
    private Date start;
    private String salary;
    private Date end;
    private String startStr;
    private String startPrevStr;
    private String endStr;
    private String reasonChange;
    private String address;
    private String registerType;
    private Long registerTypeId;
    private Boolean block;
    private List<ScheduleDto> schedule;
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ExcelJobDto(Long idCase, String post, String nameHead, String company, String phone, Date startPrev, Date start, String salary, Date end, String reasonChange, String address, String registerType, Long registerTypeId, Boolean block) {
        this.idCase = idCase;
        this.post = post;
        this.nameHead = nameHead;
        this.company = company;
        this.phone = phone;
        this.startPrev = startPrev;
        this.start = start;
        this.salary = salary;
        this.end = end;
        this.reasonChange = reasonChange;
        this.address = address;
        this.registerType = registerType;
        this.registerTypeId = registerTypeId;
        this.block = block;

        try {

            if (start != null)
                this.startStr = sdf.format(start);
            if (start != null)
                this.startStr = sdf.format(start);
            if (end != null)
                this.endStr = sdf.format(end);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error al parsear las fechas, constructor ExcelJobDto");
        }
    }

    public ExcelJobDto(Long idCase, Long id, String company, String post, String nameHead, String phone, String registerType, Date start, Date startPrev, Date end, String salary, String reasonChange, String address, Boolean block) {
        this.idCase = idCase;
        this.id = id;
        this.post = post;
        this.nameHead = nameHead;
        this.company = company;
        this.phone = phone;
        try {

            if (startPrev != null)
                this.startPrevStr = sdf.format(startPrev);
            if (start != null)
                this.startStr = sdf.format(start);
            if (end != null)
                this.endStr = sdf.format(end);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error al parsear las fechas, constructor ExcelJobDto");
        }
        this.salary = salary;

        this.reasonChange = reasonChange;
        this.address = address;
        this.registerType = registerType;
        this.address = address;
        this.block = block;
    }

    public ExcelJobDto(Long idCase, Boolean block, String registerType){
        this.idCase = idCase;
        this.block = block;
        this.registerType = registerType;
    }


    public String scheduleToStr() {
        String schStr = "";

        if (this.schedule != null && this.schedule.size() > 0) {
            for (ScheduleDto act : this.schedule) {
                if (schStr.isEmpty() == false)
                    schStr += "; ";
                schStr += act.getDay() + ", de " + act.getStart() + " a " + act.getEnd();
            }
        }
        return schStr;
    }


    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getNameHead() {
        return nameHead;
    }

    public void setNameHead(String nameHead) {
        this.nameHead = nameHead;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStartPrev() {
        return startPrev;
    }

    public void setStartPrev(Date startPrev) {
        this.startPrev = startPrev;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getReasonChange() {
        return reasonChange;
    }

    public void setReasonChange(String reasonChange) {
        this.reasonChange = reasonChange;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public Long getRegisterTypeId() {
        return registerTypeId;
    }

    public void setRegisterTypeId(Long registerTypeId) {
        this.registerTypeId = registerTypeId;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartStr() {
        return startStr;
    }

    public void setStartStr(String startStr) {
        this.startStr = startStr;
    }

    public String getStartPrevStr() {
        return startPrevStr;
    }

    public void setStartPrevStr(String startPrevStr) {
        this.startPrevStr = startPrevStr;
    }

    public String getEndStr() {
        return endStr;
    }

    public void setEndStr(String endStr) {
        this.endStr = endStr;
    }

    public List<ScheduleDto> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleDto> schedule) {
        this.schedule = schedule;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
