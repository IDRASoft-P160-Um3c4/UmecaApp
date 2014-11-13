package com.umeca.repository.reviewer;
import com.umeca.model.entities.reviewer.Drug;
import com.umeca.model.entities.reviewer.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("schoolRepository")
public interface SchoolRepository extends JpaRepository<School,Long> {

    @Query("select sch from School sch " +
            "inner join sch.framingMeeting fm " +
            "inner join fm.caseDetention cd " +
            "where cd.id=:idCase")
    public School getSchoolByIdCase(@Param("idCase")Long idCase);
}
