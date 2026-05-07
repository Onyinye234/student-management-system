package com.project.student_manager.repositories;

import com.project.student_manager.entities.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreEntityRepository extends JpaRepository<ScoreEntity, Long> {
}
