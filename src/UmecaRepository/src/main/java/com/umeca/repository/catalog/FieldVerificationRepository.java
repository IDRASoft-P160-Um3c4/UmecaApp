package com.umeca.repository.catalog;
import com.umeca.model.catalog.FieldVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Repository("fieldVerificationRepository")
public interface FieldVerificationRepository extends JpaRepository<FieldVerification,Long> {

    public static final String Q_GETLISTBYCLASS_WITHIDLIST = "select fms.id from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id = :idCase and fv.code like :code and fms.isFinal = :isFinal and fms.idFieldList = :idList";
    public static final String Q_GETLISTBYCLASS = "select fms.id from Case as c " +
            "INNER JOIN c.verification.sourceVerifications sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv " +
            "where c.id = :idCase and fv.code like :code and fms.isFinal = :isFinal";

    @Query("select f from FieldVerification as f where f.code=:code")
    FieldVerification findByCode(@Param("code")String name);

    @Query("select f.id from FieldVerification as f where f.idSubsection = (select fv.idSubsection from FieldVerification as fv where fv.code=:code)")
    List<Long> getListSubsectionByCode(@Param("code")String code);

    @Query("select f from FieldVerification as f where f.isObsolete = false")
    List<FieldVerification> findValidFields();

    @Query("select f.idSubsection from FieldVerification as f where f.code = :code")
    Integer getIdSubsectionByCode(@Param("code")String code);

    @Query("select fv from FieldVerification as fv where fv.idSubsection =:idSubsection " +
            "and fv.indexField =(select min(f.indexField) from FieldVerification as f where f.idSubsection=:idSubsection)")
    List<FieldVerification> getFieldVerificationMinByIdSubsection(@Param("idSubsection") Integer idSubsection);

    @Query("select distinct (fv.idSubsection) from Case as c " +
            "INNER JOIN c.verification.sourceVerifications as sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv "+
            "where fv.sectionCode = :sectionCode and c.id=:idCase and sv.visible = false")
    List<Integer> getSubsectionsBySectionCode(@Param("sectionCode") Integer i,@Param("idCase") Long idCase);

    @Query("select fv from FieldVerification as fv " +
            "where fv.idSubsection = :idSubsection order by fv.indexField")
    List<FieldVerification> getMinFieldByIdSubsection(@Param("idSubsection")Integer idSubsection);

    @Query("select distinct (fv.idSubsection) from Case as c " +
            "INNER JOIN c.verification.sourceVerifications as sv " +
            "INNER JOIN sv.fieldMeetingSourceList as fms " +
            "INNER JOIN fms.fieldVerification as fv "+
            "where fv.sectionCode = :sectionCode and c.id=:idCase and fms.idFieldList=:idList and sv.visible = false")
    List<Integer> getSubsectionsBySectionCodeWithIdList(@Param("sectionCode") Integer i,@Param("idCase") Long idCase,@Param("idList") Long idList);
}
