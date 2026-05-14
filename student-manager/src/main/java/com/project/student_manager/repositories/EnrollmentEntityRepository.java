package com.project.student_manager.repositories;

import com.project.student_manager.entities.EnrollmentEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentEntityRepository extends JpaRepository<EnrollmentEntity, Long> {
    Optional<EnrollmentEntity> findByStudentStudentIdAndCourseCourseId(
            Long studentId, Long courseId);

    @Query("SELECT e.enrollmentId FROM EnrollmentEntity e WHERE e.course.courseId = :courseId")
    List<Long> findEnrollmentIdsByCourseId(@Param("courseId") Long courseId);

    void deleteByCourse_CourseId(Long courseId);
}
