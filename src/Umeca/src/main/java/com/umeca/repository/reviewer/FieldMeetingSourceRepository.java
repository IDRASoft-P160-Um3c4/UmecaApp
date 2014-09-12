package com.umeca.repository.reviewer;
import com.umeca.model.catalog.FieldVerification;
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
            "WHERE fms.sourceVerification.id = :idSource and fv.idSubsection= :idSubsection AND fms.statusFieldVerification.name <> :status")
    List<FieldMeetingSource> getGroupFieldMeeting(@Param("idSource") Long idSource, @Param("idSubsection") Integer idSubsection,@Param("status") String stFieldVerifUnable);

    @Query("select fms from FieldMeetingSource fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "WHERE fms.sourceVerification.id = :idSource and fv.idSubsection= :idSubsection and fms.idFieldList=:idList  AND fms.statusFieldVerification.name <> :status")
    List<FieldMeetingSource> getGroupFieldMeetingWithIdList(@Param("idSource")Long idSource,@Param("idSubsection") Integer idSubsection,@Param("idList") Long idList, @Param("status")String status);


    @Query("select fms from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id=:idCase and fv.idSubsection = :idSubsection and fms.isFinal=true")
    List<FieldMeetingSource> findListFinalByIdSubsection(@Param("idCase")Long idCase,@Param("idSubsection") Integer idSubsection);

    @Query("select fms from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "INNER JOIN fms.statusFieldVerification as st " +
            "WHERE c.id =:idCase and fv.idSubsection=:idSubsection and st.name=:status")
    List<FieldMeetingSource> getExistUnableFieldMeeting(@Param("idSubsection")Integer idSubsection,@Param("idCase") Long idCase,@Param("status")String code);

    @Query("select fms from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "INNER JOIN fms.statusFieldVerification as st " +
            "WHERE c.id =:idCase and fv.idSubsection=:idSubsection and st.name=:status and fms.idFieldList=:idList")
    List<FieldMeetingSource> getExistUnableFieldMeetingWithIdList(@Param("idSubsection")Integer idSubsection,@Param("idList") Long idList,@Param("idCase") Long idCase,@Param("status") String status);

    @Query("select fv from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "INNER JOIN fms.statusFieldVerification as st " +
            " where sv.id =:idImputed and st.name <> :status and c.id=:idCase and fms.id  not in (:list)")
    List<FieldVerification> getIdsSubsectionMessing(@Param("idCase")Long idCase,@Param("idImputed") Long idImputed,@Param("status")String status,@Param("list") List<Long> list);

    @Query("select fms from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "WHERE c.id =:idCase and fms.isFinal = true")
    List<FieldMeetingSource> getAllFinalByIdCase(@Param("idCase")Long idCase);

    @Query("select fms from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "WHERE c.id =:idCase and fms.isFinal = true and fv.sectionCode=:sectionCode order by fms.idFieldList,fv.idSubsection, fv.indexField asc ")
    List<FieldMeetingSource> getAllFinalByIdCaseAndSectionCode(@Param("idCase")Long idCase,@Param("sectionCode")Integer sectionCode);

    @Query("select fms from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id=:idCase and fv.idSubsection = :idSubsection and fms.isFinal=true and fms.idFieldList =:idList")
    List<FieldMeetingSource> findListFinalByIdSubsectionWithIdList(@Param("idCase")Long idCase,@Param("idSubsection") Integer idSubsection,@Param("idList") Long idList);

    @Query("select distinct (fms.idFieldList) from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "WHERE c.id =:idCase and fms.fieldVerification.sectionCode=:sectionCode")
    List<Long> getIdsListBySectionCode(@Param("sectionCode")Integer i,@Param("idCase") Long idCase);

    @Query("select distinct (fms.idFieldList) from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "WHERE c.id =:idCase and fms.fieldVerification.sectionCode=:sectionCode and sv.id=:idSource")
    List<Long> getIdsListOfSectionCodeOfSource(@Param("sectionCode")Integer i,@Param("idCase") Long idCase,@Param("idSource")Long idSource);

    @Query("select fms.id from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id = :idCase and fv.code= :code and fms.idFieldList=:idList and fms.isFinal=true")
    List<Long> getIdFieldMSFinalByCaseCodeIdList(@Param("idCase")Long idCase,@Param("idList") Long idList,@Param("code") String code);

    @Query("select fms.id from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id = :idCase and fv.code=:code and fms.isFinal=true")
    List<Long> getIdFieldMSFinalByCaseCode(@Param("idCase")Long idCase,@Param("code") String code);

    @Query("select fms from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id = :idCase and sv.id=:idSource  AND fms.statusFieldVerification.name <> :status and fv.sectionCode = :sectionCode  order by fms.idFieldList,fv.idSubsection, fv.indexField asc")
    List<FieldMeetingSource> getFieldMeetingBySource(@Param("idCase")Long id,@Param("idSource") Long idSource, @Param("status")String status,@Param("sectionCode")Integer sectionCode);

    @Query("select fms.id from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id = :idCase and sv.id=:idSource and fv.idSubsection = :idSubsection")
    List<Long> getFMSByIdSubsection(@Param("idCase")Long idCase,@Param("idSource") Long idSource,@Param("idSubsection") Integer idSub);

    @Query("select fms.id from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id = :idCase and sv.id=:idSource and fv.idSubsection = :idSubsection and fms.idFieldList = :idList")
    List<Long> getFMSByIdSubsectionWithIdList(@Param("idCase")Long idCase,@Param("idSource") Long idSource,@Param("idSubsection") Integer idSub,@Param("idList")Long idList);

    @Query("select fms from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id =:idCase and fv.sectionCode = :idSection" )
    List<FieldMeetingSource> getGroupFieldMeetingByIdCase(@Param("idCase")Long idCase,@Param("idSection") Integer idSection);

    @Query("select fms from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id =:idCase and fv.sectionCode = :idSection and fms.idFieldList =:idList" )
    List<FieldMeetingSource> getGroupFieldMeetingByIdCaseWhitIdList(@Param("idCase")Long idCase,@Param("idSection") Integer idSection, @Param("idList") Long idList);

}
