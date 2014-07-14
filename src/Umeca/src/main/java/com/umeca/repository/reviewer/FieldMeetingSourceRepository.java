package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.FieldMeetingSource;
import com.umeca.model.entities.reviewer.SourceVerification;
import com.umeca.model.entities.reviewer.View.SearchToChoiceIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("fieldMeetingRepository")
public interface FieldMeetingSourceRepository extends JpaRepository<FieldMeetingSource,Long> {
    @Query("select fms.id from Case as c " +
            "INNER JOIN c.verification.sourceVerifications as sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv "+
            "where sv.id = :idSource and c.id=:idCase and fv.code = :code")
    Long getIdMeetingSourceByCode(@Param("idCase") Long idCase, @Param("idSource") Long idSource, @Param("code") String name);

    @Query("select fms.id from Case as c " +
            "INNER JOIN c.verification.sourceVerifications as sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv "+
            "where sv.id = :idSource and c.id=:idCase and fv.code = :code and fms.idFieldList=:idList")
    Long getIdMeetingSourceByCodeWithIdList(@Param("idCase") Long idCase, @Param("idSource") Long idSource, @Param("code") String name, @Param("idList") Long idList);

    @Query("select fms from Case as c " +
            "INNER JOIN c.verification as v " +
            "INNER JOIN v.sourceVerifications as sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv "+
            "WHERE c.id = :idCase and sv.visible = false and fv.id = :idField and fms.idFieldList = :idList")
    FieldMeetingSource findMeetingSourceByIdFieldVerification(@Param("idCase")Long idCase, @Param("idList") Long idList, @Param("idField")Long idField);

    @Query("select fms from Case as c " +
            "INNER JOIN c.verification as v " +
            "INNER JOIN v.sourceVerifications as sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv "+
            "WHERE c.id = :idCase and sv.visible = false and fv.id = :idFv")
    FieldMeetingSource findMeetingSourceByIdFieldVerificationWithoutId(@Param("idCase")Long idCase,@Param("idFv") Long idFv);

    @Query("select new com.umeca.model.entities.reviewer.View.SearchToChoiceIds(sv.id,fv.idSubsection) from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id = :idCase and fv.code = :code")
    List<SearchToChoiceIds> getIdSourceByCode(@Param("idCase")Long idCase,@Param("code") String code);

    @Query("select new com.umeca.model.entities.reviewer.View.SearchToChoiceIds(sv.id,fv.idSubsection) from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id = :idCase and fv.code = :code and fms.idFieldList = :idList")
    List<SearchToChoiceIds> getIdSourceByCodeWhithIdList(@Param("idCase")Long idCase,@Param("code") String code,@Param("idList") Long idList);

    @Query("select fms from FieldMeetingSource fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "WHERE fms.sourceVerification.id = :idSource and fv.idSubsection= :idSubsection")
    List<FieldMeetingSource> getGroupFieldMeeting(@Param("idSource")Long idSource,@Param("idSubsection") Integer idSubsection);

    @Query("select fms from FieldMeetingSource fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "WHERE fms.sourceVerification.id = :idSource and fv.idSubsection= :idSubsection and fms.idFieldList=:idList")
    List<FieldMeetingSource> getGroupFieldMeetingWithIdList(@Param("idSource")Long idSource,@Param("idSubsection") Integer idSubsection,@Param("idList") Long idList);


    @Query("select fms from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id=:idCase and fv.idSubsection = :idSubsection and fms.isFinal=true")
    List<FieldMeetingSource> findListFinalByIdSubsection(@Param("idCase")Long idCase,@Param("idSubsection") Long idSubsection);
}
