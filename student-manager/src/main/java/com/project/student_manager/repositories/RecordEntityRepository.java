package com.project.student_manager.repositories;

import com.project.student_manager.entities.RecordEntity;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordEntityRepository extends JpaRepository<RecordEntity, Long> {
    void deleteByEnrollment_EnrollmentIdIn(List<Long> enrollmentIds);
    public Optional<RecordEntity> findByEnrollment_EnrollmentId(Long enrollmentId);
}
