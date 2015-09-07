package com.umeca.service.managereval;
import com.umeca.model.shared.SelectList;
import java.util.List;


public interface hola {

    List<SelectList> getData(int initDate, int endDate, String filter);
}
