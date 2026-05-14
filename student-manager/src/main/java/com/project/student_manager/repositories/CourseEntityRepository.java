package com.project.student_manager.repositories;

import com.project.student_manager.entities.CourseEntity;
import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEntityRepository extends JpaRepository<CourseEntity, Long> {
    Optional<CourseEntity> findByCourseCode(String courseCode);

    Optional<CourseEntity> findByCourseName(String courseName);

    @Query("SELECT c FROM CourseEntity c WHERE c.department = :department AND c.level = :level")
    Page<CourseEntity> findAllByDepartmentAndLevel(
            @Param("department") Department department,
            @Param("level") Level level,
            Pageable pageable);

    @Query("SELECT c FROM CourseEntity c WHERE c.level = :level")
    Page<CourseEntity> findAllByLevel(@Param("level") Level level, Pageable pageable);

    @Query("SELECT c FROM CourseEntity c WHERE c.department = :department")
    Page<CourseEntity> findAllByDepartment(
            @Param("department") Department department, Pageable pageable);

    @Query("SELECT c FROM CourseEntity c")
    Page<CourseEntity> findAllCourses(Pageable pageable);
}
