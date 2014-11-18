package com.umeca.repository.catalog;
import com.umeca.model.catalog.TypeNameFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("typeNameFileRepository")
public interface TypeNameFileRepository extends JpaRepository<TypeNameFile,Long> {

    @Query("select e from TypeNameFile e where e.obsolete= false")
    List<TypeNameFile> findNotObsolet();

    @Query("select e from TypeNameFile e " +
            "inner join e.roles r " +
            "where e.id not in (select  tnf.id from UploadFile uf " +
            "inner join uf.typeNameFile tnf " +
            "inner join uf.caseDetention c where tnf.isOnly = true and c.id = :idCase and uf.isObsolete = false) and e.obsolete = false " +
            "and r.id in (select ro.id from User u inner join u.roles ro where u.id = :idUser)")
    List<TypeNameFile> findNotExistByIdCase(@Param("idCase") Long idCase, @Param("idUser") Long idUser);

    @Query("select e from TypeNameFile e where e.code = :code")
    TypeNameFile findByCode(@Param("code")String code);
}
