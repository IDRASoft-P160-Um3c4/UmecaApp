package com.umeca.repository.catalog;
import com.umeca.model.catalog.FieldVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("fieldVerificationRepository")
public interface FieldVerificationRepository extends JpaRepository<FieldVerification,Long> {


    @Query("select f from FieldVerification as f where f.code=:code")
    FieldVerification findByCode(@Param("code")String name);

    @Query("select f.id from FieldVerification as f where f.idSubsection = (select fv.idSubsection from FieldVerification as fv where fv.code=:code)")
    List<Long> getListSubsectionByCode(@Param("code")String code);

    @Query("select f from FieldVerification as f where f.isObsolete = false")
    List<FieldVerification> findValidFields();

    @Query("select f.idSubsection from FieldVerification as f where f.code = :code")
    Integer getIdSubsectionByCode(@Param("code")String code);

    @Query("select f from FieldVerification as f where  f.idSubsection in :idSubsectionLists")
    List<FieldVerification> getListByClass(@Param("idSubsectionLists") List<Integer> idSubsectionLists);
}
