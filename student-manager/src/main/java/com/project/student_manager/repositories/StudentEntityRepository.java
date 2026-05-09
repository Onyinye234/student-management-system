package com.project.student_manager.repositories;

import com.project.student_manager.entities.StudentEntity;
import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByMatricNumber(String matricNumber);
    Optional<StudentEntity> findByEmail(String email);

    @Query("SELECT s FROM StudentEntity s" )
    Page<StudentEntity>findAllStudents(Pageable pageable);

    @Query("""
       SELECT s
       FROM StudentEntity s
       WHERE s.level = :level
       """)
    Page<StudentEntity>findByLevel(Pageable pageable, @Param("level") Level level);

    @Query("""
    SELECT s
    FROM StudentEntity s
    WHERE s.department = :department
    """)
    Page<StudentEntity>findByDepartment(Pageable pageable, @Param("department") Department department);

    @Query("""
    SELECT s 
    FROM  StudentEntity s
    WHERE s.level=:level
    AND s.department=:department
    """
    )
    Page<StudentEntity>findByLevelAndDepartment(Pageable pageable,
                                                @Param("level") Level level,
                                                @Param("department") Department department);

}
