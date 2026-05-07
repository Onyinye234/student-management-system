package com.project.student_manager.repositories;

import com.project.student_manager.entities.CourseEntity;
import com.project.student_manager.enums.Level;
import com.project.student_manager.enums.Semester;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEntityRepository
    extends JpaRepository<CourseEntity, Long>
{
    Optional<CourseEntity> findByCourseCode(String courseCode);
    Optional<CourseEntity> findByCourseName(String courseName);
    Optional<CourseEntity> findBySemester(Semester semester);
    Optional<CourseEntity> findByLevel(Level level);
}
