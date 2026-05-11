// package com.project.student_manager.repositories;
// import com.project.student_manager.entities.EnrollmentEntity;
// import java.util.List;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;
// @Repository
// public interface EnrollmentEntityRepository extends JpaRepository<EnrollmentEntity, Long> {
//     @Query(
//         "select enrollment.enrollmentId
//         from EnrollmentEntity enrollment
//         where enrollment.course.id = :courseId"
//     )
//     List<Long> findEnrollmentIdsByCourseId(@Param("courseId") Long courseId);
//     void deleteByCourse_Id(Long courseId);
// }
