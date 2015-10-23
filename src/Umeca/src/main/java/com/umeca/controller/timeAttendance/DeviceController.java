package com.umeca.controller.timeAttendance;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.DeviceDto;
import com.umeca.model.entities.timeAttendance.Device;
import com.umeca.repository.humanResources.DeviceRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.device.DeviceService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mi on 5/4/2015.
 */
@Controller
public class DeviceController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private SharedLogExceptionService logException;
    @Autowired
    private SharedUserService sharedUserService;
    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/humanResources/device/index", method = RequestMethod.GET)
    public String index() {
        return "/humanResources/timeAttendance/device/index";
    }

    @RequestMapping(value = "/humanResources/device/list", method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("ip"));
                    add(r.get("port"));
                    add(r.get("isObsolete"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("name"))
                    return r.get("name");
                if (field.equals("ip"))
                    return r.get("ip");
                if (field.equals("port"))
                    return r.get("port");
                return null;
            }
        }, Device.class, DeviceDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/device/upsertDevice", method = RequestMethod.POST)
    public ModelAndView upsertDevice(@RequestParam(required = false) Long id){
        ModelAndView model = new ModelAndView("/humanResources/timeAttendance/device/upsert");

        Gson gson = new Gson();
        Device device = deviceRepository.findOne(id);

        if (device != null)
            model.addObject("device", gson.toJson(device));

        return model;
    }

    @RequestMapping(value = "/humanResources/device/doUpsertDevice", method = RequestMethod.POST)
    public ResponseMessage doUpsertDevice(@ModelAttribute DeviceDto deviceDto){

        ResponseMessage response = new ResponseMessage();
        try {
            response = deviceService.upsertDevice(deviceDto);
        }
        catch(Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertDevice", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        }
        finally {
            return response;
        }
    }

    public DeviceService getDeviceService() {
        return deviceService;
    }

    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
}