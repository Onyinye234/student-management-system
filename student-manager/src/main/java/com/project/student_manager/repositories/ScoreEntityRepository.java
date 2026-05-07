package com.project.student_manager.repositories;

import entities.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreEntityRepository extends JpaRepository<ScoreEntity, Long> {
}
