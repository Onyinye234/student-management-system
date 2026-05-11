package com.project.student_manager.repositories;

import com.project.student_manager.entities.CourseEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEntityRepository
    extends JpaRepository<CourseEntity, Long>
{
    Optional<CourseEntity> findByCourseCode(String courseCode);
    Optional<CourseEntity> findByCourseName(String courseName);
}
