package com.umeca.repository.humanResources;

import com.umeca.model.entities.humanReources.CourseAchievement;
import com.umeca.model.entities.shared.CourseType;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qCourseTypeRepository")
public interface CourseTypeRepository extends JpaRepository<CourseType, Long> {

    @Query("select new com.umeca.model.shared.SelectList(CT.id,CT.name,CT.specification) from CourseType CT " +
            "where CT.isObsolete=false order by CT.name asc")
    List<SelectList> findNoObsolete();

}