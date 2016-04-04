package com.umeca.repository.humanResources;

import com.umeca.model.dto.timeAttendance.DeviceDto;
import com.umeca.model.entities.timeAttendance.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qDeviceRepository")
public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("SELECT new com.umeca.model.dto.timeAttendance.DeviceDto(d.id, d.name, d.ip, d.port) FROM Device d WHERE d.isObsolete = false")
    List<DeviceDto> findAllNotObsoloteDevices();


    @Query("SELECT new com.umeca.model.dto.timeAttendance.DeviceDto(d.id, d.name, d.ip, d.port, d.deviceUse.id, d.isObsolete) FROM Device d WHERE d.id = :idDevice")
    DeviceDto findDevice(@Param("idDevice") Long idDevice);


    @Query("SELECT new com.umeca.model.dto.timeAttendance.DeviceDto(d.id, d.name, d.ip, d.port) FROM Device d WHERE d.isObsolete = false and d.deviceUse.code = :deviceUse")
    List<DeviceDto> findAllNotObsoloteDevicesByUse(@Param("deviceUse") String deviceUse);


}