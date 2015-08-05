package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.Channeling;
import com.umeca.model.entities.supervisor.ChannelingDropInfo;
import com.umeca.model.entities.supervisor.ChannelingTrackModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("channelingDropInfoRepository")
public interface ChannelingDropInfoRepository extends JpaRepository<ChannelingDropInfo, Long> {
}
