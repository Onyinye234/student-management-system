package com.project.student_manager.service;

import com.project.student_manager.dto.StudentDto;
import com.project.student_manager.entities.StudentEntity;
import com.project.student_manager.enums.ErrorType;
import com.project.student_manager.exceptions.StudentManagerException;
import com.project.student_manager.repositories.StudentEntityRepository;
import com.project.student_manager.request.StudentRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentEntityRepository studentEntityRepository;

    public StudentDto mapEntityToDto(StudentEntity studentEntity) {
        return new StudentDto(
            studentEntity.getStudentId(),
            studentEntity.getFullName(),
            studentEntity.getEmail(),
            studentEntity.getMatricNumber(),
            studentEntity.getLevel(),
            studentEntity.getEnrolledAt()
        );
    }

    public StudentDto enrollStudent(
        StudentRegistrationRequest studentRegistrationRequest
    ) {
        if (
            studentEntityRepository
                .findByEmail(studentRegistrationRequest.getEmail())
                .isPresent()
        ) {
            throw new StudentManagerException(
                ErrorType.STUDENT_ALREADY_REGISTERED,
                "Student already exists!"
            );
        }
        if (
            studentEntityRepository
                .findByMatricNumber(
                    studentRegistrationRequest.getMatricNumber()
                )
                .isPresent()
        ) {
            throw new StudentManagerException(
                ErrorType.STUDENT_ALREADY_REGISTERED,
                "Student already exists!"
            );
        }

        StudentEntity studentEntity = new StudentEntity(
            studentRegistrationRequest.getFullName(),
            studentRegistrationRequest.getEmail(),
            studentRegistrationRequest.getMatricNumber(),
            studentRegistrationRequest.getLevel()
        );
        studentEntityRepository.save(studentEntity);
        return mapEntityToDto(studentEntity);
    }
}
