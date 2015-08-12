package com.umeca.repository.shared;

import com.umeca.model.entities.shared.LogCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogCaseRepository extends JpaRepository<LogCase, Long> {

    @Query("select new com.umeca.model.entities.shared.LogCase(lg.date, lg.completionDate , lg.activity, u.fullname, lg.title, lg.resume) from LogCase lg " +
            "inner join lg.caseDetention cd " +
            "inner join lg.user u " +
            "where cd.id = :idCase order by lg.date asc")
    List<LogCase> findAllByCase(@Param("idCase")Long id);

    @Query("select  count (l.id) from LogCase l " +
            "inner join l.caseDetention cd " +
            "where cd.id = :idCase")
    Long countLogByIdCase(@Param("idCase")Long idCase);
}
