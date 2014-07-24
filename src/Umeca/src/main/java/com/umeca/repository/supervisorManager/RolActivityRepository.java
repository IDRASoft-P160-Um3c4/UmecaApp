package com.umeca.repository.supervisorManager;

import com.umeca.model.dto.supervisorManager.RolActivityResponse;
import com.umeca.model.entities.supervisorManager.RolActivity;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qRolActivityRepository")
public interface RolActivityRepository extends JpaRepository<RolActivity, Long>{

    @Query("SELECT new com.umeca.model.dto.supervisorManager.RolActivityResponse(ra.id, ra.end, ra.start, ra.supervisor.id, ra.status) " +
            "FROM RolActivity ra " +
            "WHERE ra.status <>:status AND " +
            "(ra.searchStart =:yearmonthStart OR ra.searchEnd =:yearmonthStart OR ra.searchStart =:yearmonthEnd OR ra.searchEnd =:yearmonthEnd)")
    List<RolActivityResponse> getAllActivities(@Param("status")String status, @Param("yearmonthStart")int yearmonthStart, @Param("yearmonthEnd")int yearmonthEnd);


    @Query("SELECT DISTINCT new com.umeca.model.shared.SelectList(su.id, su.username, su.fullname) " +
            "FROM RolActivity ra INNER JOIN ra.supervisor su " +
            "WHERE ra.status <>:status AND " +
            "(ra.searchStart =:yearmonthStart OR ra.searchEnd =:yearmonthStart OR ra.searchStart =:yearmonthEnd OR ra.searchEnd =:yearmonthEnd)")
    List<SelectList> getAllValidSupervisors(@Param("status")String status, @Param("yearmonthStart")int yearmonthStart, @Param("yearmonthEnd")int yearmonthEnd);
}


