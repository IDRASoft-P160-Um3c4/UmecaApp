package com.umeca.repository.humanResources;

import com.umeca.model.dto.humanResources.UmecaJobDto;
import com.umeca.model.entities.humanReources.UmecaJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("qUmecaJobRepository")
public interface UmecaJobRepository extends JpaRepository<UmecaJob, Long> {

    @Query("select new com.umeca.model.dto.humanResources.UmecaJobDto(UJ.id,UJ.nameHead,E.id,UJ.salary,UJ.startDate,UJ.endDate,R.id,D.id) " +
            "from UmecaJob UJ " +
            "inner join UJ.employee E " +
            "inner join UJ.role R " +
            "inner join UJ.district D " +
            "where UJ.id = :idReference and E.id =:idEmployee")
    UmecaJobDto findUmecaJobDtoByIds(@Param("idEmployee") Long idEmployee, @Param("idReference") Long idReference);

    @Query("select UJ from UmecaJob UJ " +
            "inner join UJ.employee E " +
            "where UJ.id = :idUmecaJob and E.id =:idEmployee")
    UmecaJob findUmecaJobByIds(@Param("idEmployee") Long idEmployee, @Param("idUmecaJob") Long idUmecaJob);
}