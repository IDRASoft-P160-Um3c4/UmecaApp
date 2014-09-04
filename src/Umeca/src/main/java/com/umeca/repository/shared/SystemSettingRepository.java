package com.umeca.repository.shared;

import com.umeca.model.entities.shared.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemSettingRepository extends JpaRepository<SystemSetting, Long> {

    @Query("SELECT new com.umeca.model.entities.shared.SystemSetting(SS.key, SS.value) FROM SystemSetting SS WHERE SS.group =:group")
    List<SystemSetting> findAllOfGroup(@Param("group") String group);
}
