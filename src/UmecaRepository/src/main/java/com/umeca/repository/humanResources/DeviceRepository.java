package com.umeca.repository.humanResources;

import com.umeca.model.dto.timeAttendance.DeviceDto;
import com.umeca.model.entities.timeAttendance.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 8/14/2015.
 */
@Repository("qDeviceRepository")
public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("SELECT new com.umeca.model.dto.timeAttendance.DeviceDto(d.id, d.name, d.ip, d.port) FROM Device d WHERE d.isObsolete = false")
    List<DeviceDto> findAllNotObsoloteDevices();
}