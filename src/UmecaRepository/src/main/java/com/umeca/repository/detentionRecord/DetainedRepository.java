package com.umeca.repository.detentionRecord;

import com.umeca.model.dto.detentionRecord.DetainedDto;
import com.umeca.model.dto.shared.AgreementDto;
import com.umeca.model.entities.detentionRecord.Detained;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qDetainedRepository")
public interface DetainedRepository extends JpaRepository<Detained, Long> {

        @Query("select new com.umeca.model.dto.shared.AgreementDto(AG.title, AG.theme, AG.agreementDate, AR.name, " +
                "AR.specification, AG.specArea, AG.isFinished, AG.isDone) from Minute M " +
                "inner join M.agreements AG " +
                "inner join AG.area AR " +
                "where M.id=:minuteId order by AR.name")
        public List<AgreementDto> getAgreementsInfoByMinuteId(@Param("minuteId") Long minuteId);



//    @Query("select new com.umeca.model.dto.detentionRecord.DetainedDto(" +
//            "DET.id, " +
//            "DET.name, " +
//            "DET.lastNameP, " +
//            "DET.lastNameM, " +
//            "DET.age, " +
//            "DET.idFolder," +
//            "DET.initDate," +
//            "DET.initTime, " +
//            "DET.investigationUnit " +
//           // "US.fullname, " +
//           // "DIST.name " +
//            ") " +
//            "from Detained DET ")
//            //"inner join DET.userProsecute US " +
//    public List<DetainedDto> getDetainedInfoForDetentionRecord();


        @Query("select new com.umeca.model.dto.detentionRecord.DetainedDto(" +
                "DET.id, " +
                "DET.name, "+
                "DET.lastNameP, " +
                "DET.lastNameM, " +
                "DET.age, " +
                "DET.idFolder, " +
                "DET.initDate, " +
                "DET.initTime, " +
                "DET.investigationUnit," +
                "DIST.name, " +
                "DET.isProsecute, " +
                "DET.crime " +
                ") " +
                "FROM Detained DET " +
                "inner join DET.district DIST ")
        public List<DetainedDto> getDetainedInfo();


        @Query("select new com.umeca.model.dto.detentionRecord.DetainedDto(" +
                "DET.id, " +
                "DET.registerTimestamp, " +
                "DET.name, "+
                "DET.lastNameP, " +
                "DET.lastNameM, " +
                "DET.initDate, " +
                "DET.initTime, " +
                "DET.idFolder, " +
                "DET.age, " +
                "DET.investigationUnit, " +
                "DET.crime, " +
                "DIST.name, " +
                "DET.isProsecute " +
                ") " +
                "FROM Detained DET " +
                "inner join DET.district DIST " +
                "where DET.isVisibleDetentionRecord = 1")
        public List<DetainedDto> getDetainedVisibleInfo();

}
