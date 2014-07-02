package com.umeca.repository;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.FindLegalBefore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/2/14
 * Time: 8:10 PM
 */
@Repository("qCaseRepository")
public interface CaseRepository extends JpaRepository<Case, Long>{

    @Query("SELECT c FROM Case c WHERE c.idFolder =:idFolder")
    Case findByIdFolder(@Param("idFolder")String idFolder);

    @Query("SELECT c FROM Case c WHERE c.idMP =:idMP")
    Case findByIdMP(@Param("idMP")String idMP);

    @Query("select  new com.umeca.model.entities.reviewer.FindLegalBefore(c.idMP,c.idFolder,s.description) from Case as c " +
            "INNER JOIN c.status as s " +
            "INNER JOIN c.meeting.imputed as i " +
            "where i.name=:name and i.lastNameP = :lastNameP and i.lastNameM = :lastNameM and c.id <> :idCase")
    List<FindLegalBefore> findLegalBefore(@Param("idCase")Long id, @Param("name") String name, @Param("lastNameP") String lastNameP, @Param("lastNameM") String lastNameM);
}


