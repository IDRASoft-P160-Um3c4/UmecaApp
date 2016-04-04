package com.umeca.service.device;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.DeviceUse;
import com.umeca.model.dto.timeAttendance.DeviceDto;
import com.umeca.model.entities.timeAttendance.Device;
import com.umeca.repository.humanResources.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 8/18/2015.
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    DeviceRepository deviceRepository;

    @Override
    @Transactional
    public ResponseMessage upsertDevice(DeviceDto deviceDto) {

        Device device;
        Long deviceId = deviceDto.getId();

        if (deviceId != null && deviceId.longValue() > 0L){
            device = deviceRepository.findOne(deviceId);
        }
        else{
            device = new Device();
        }

        DeviceUse deviceUse = new DeviceUse();
        deviceUse.setId(deviceDto.getDeviceUse());

        device.setName(deviceDto.getName());
        device.setIp(deviceDto.getIp());
        device.setPort(deviceDto.getPort());
        device.setDeviceUse(deviceUse);

        device.setIsObsolete(false);

        deviceRepository.save(device);

        ResponseMessage resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("El dispositivo ha sido registrado con éxito.");
        return resp;
    }

    @Transactional
    @Override
    public ResponseMessage deleteDevice(DeviceDto deviceDto){

        Device device = deviceRepository.findOne(deviceDto.getId());
        ResponseMessage resp = new ResponseMessage();

        if (device != null) {
            deviceRepository.delete(device);
            resp.setHasError(false);
            resp.setMessage("El dispositivo ha sido registrado con éxito.");
        }
        else {
            resp.setHasError(true);
            resp.setMessage("El registro del dispositivo, ya no está disponible.");
        }


        return resp;
    }
}
