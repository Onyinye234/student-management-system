package com.project.student_manager.repositories;

import entities.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentEntityRepository extends JpaRepository<EnrollmentEntity, Long> {
}
