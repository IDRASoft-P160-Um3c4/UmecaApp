package com.umeca.repository.supervisor;

import com.umeca.model.entities.supervisor.ChannelingDropInfo;
import com.umeca.model.entities.supervisorManager.ChannelingInfoDropModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("channelingDropInfoRepository")
public interface ChannelingDropInfoRepository extends JpaRepository<ChannelingDropInfo, Long> {

    @Query("SELECT new com.umeca.model.entities.supervisorManager.ChannelingInfoDropModel(cdi.id, cdi.creationDate, cdi.creatorComments, " +
            "cdt.name, cu.fullname, cd.idMP, i.name, i.lastNameP, i.lastNameM, c.name, d.name) " +
            "FROM ChannelingDropInfo AS cdi " +
            "INNER JOIN cdi.channelingDropType cdt " +
            "INNER JOIN cdi.creatorUser cu " +
            "INNER JOIN cdi.channeling c " +
            "INNER JOIN c.caseDetention cd " +
            "INNER JOIN cd.meeting.imputed i " +
            "INNER JOIN c.district d " +
            "WHERE cdi.id =:id ")
    ChannelingInfoDropModel getChannelingDropInfoById(@Param("id")Long id);
}
