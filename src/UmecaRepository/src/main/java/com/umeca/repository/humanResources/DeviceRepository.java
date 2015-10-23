package com.umeca.repository.humanResources;

import com.umeca.model.entities.timeAttendance.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 8/14/2015.
 */
@Repository("qDeviceRepository")
public interface DeviceRepository extends JpaRepository<Device, Long> {
}