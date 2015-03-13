package com.umeca.repository.humanResources;

import com.umeca.model.dto.humanResources.CourseAchievementDto;
import com.umeca.model.entities.humanReources.CourseAchievement;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.model.shared.SelectList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("qCourseAchievementRepository")
public interface CourseAchievementRepository extends JpaRepository<CourseAchievement, Long> {

    @Query("select new com.umeca.model.dto.humanResources.CourseAchievementDto(CA.id, E.id, CA.name, CA.place,CT.id,CA.specCourseType,SDT.id,CA.specDocType,CA.start,CA.end) from CourseAchievement CA " +
            "inner join CA.employee E " +
            "inner join CA.schoolDocumentType SDT " +
            "inner join CA.courseType CT " +
            "where CA.id=:idCourse and E.id = :idEmployee")
    CourseAchievementDto findCourseAchievmentDtoByIds(@Param("idEmployee") Long idEmployee, @Param("idCourse") Long idCourse);

    @Query("select CA from CourseAchievement CA " +
            "inner join CA.employee E " +
            "where CA.id=:idCourse and E.id = :idEmployee")
    CourseAchievement findCourseAchievmentByIds(@Param("idEmployee") Long idEmployee, @Param("idCourse") Long idCourse);

}