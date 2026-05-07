package com.project.student_manager.repositories;

import com.project.student_manager.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByMatricNumber(String matricNumber);
    Optional<StudentEntity> findByEmail(String email);

}
