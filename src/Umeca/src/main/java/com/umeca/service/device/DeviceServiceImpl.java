package com.umeca.service.device;

import com.umeca.infrastructure.model.ResponseMessage;
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

        Device device = new Device();

        if (deviceDto.getId() == null || deviceDto.getId() == 0)
            device = deviceRepository.findOne(deviceDto.getId());

        device.setName(deviceDto.getName());
        device.setIp(deviceDto.getIp());
        device.setPort(deviceDto.getPort());
        device.setIsObsolete(false);

        deviceRepository.save(device);

        ResponseMessage resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("El dispositivo ha sido registrado con éxito.");
        return resp;
    }
}
