package com.umeca.repository.catalog;

import com.umeca.model.catalog.DeviceUse;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qDeviceUseRepository")
public interface DeviceUseRepository extends JpaRepository<DeviceUse, Long>{

    @Query("select new com.umeca.model.shared.SelectList(D.id,D.name) from DeviceUse D where D.isObsolete=false")
    public List<SelectList> findNoObsolete();

    @Query("select d.name from DeviceUse d where d.id = :idDeviceUse")
    public String findDeviceUseNameById(@Param("idDeviceUse") Long idDeviceUse);
}


