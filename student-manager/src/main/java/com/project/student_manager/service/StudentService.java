package com.project.student_manager.service;

import com.project.student_manager.dto.StudentDto;
import com.project.student_manager.entities.StudentEntity;
import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.ErrorType;
import com.project.student_manager.enums.Level;
import com.project.student_manager.exceptions.StudentManagerException;
import com.project.student_manager.repositories.StudentEntityRepository;
import com.project.student_manager.request.StudentRegistrationRequest;
import com.project.student_manager.request.UpdateStudentDetailsRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentEntityRepository studentEntityRepository;

    public StudentEntity getStudentById(Long studentId){
        return studentEntityRepository.findById(studentId)
                .orElseThrow(()->   new StudentManagerException(ErrorType.STUDENT_NOT_FOUND, "Student with this ID doesn't exist"));

    }

    public StudentDto mapEntityToDto(StudentEntity studentEntity) {
        return new StudentDto(
                studentEntity.getStudentId(),
                studentEntity.getFullName(),
                studentEntity.getEmail(),
                studentEntity.getMatricNumber(),
                studentEntity.getLevel(),
                studentEntity.getEnrolledAt(),
                studentEntity.getDepartment()
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

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFullName(studentRegistrationRequest.getFullName());
        studentEntity.setEmail(studentRegistrationRequest.getEmail());
        studentEntity.setMatricNumber(studentRegistrationRequest.getMatricNumber());
        studentEntity.setLevel(studentRegistrationRequest.getLevel());
        studentEntity.setDepartment(studentRegistrationRequest.getDepartment());
        studentEntityRepository.save(studentEntity);
        return mapEntityToDto(studentEntity);



    }

    public StudentDto getStudentAndMapToDto(Long studentId){

        StudentEntity studentEntity = getStudentById(studentId);
        return mapEntityToDto(studentEntity);
    }


    public StudentDto updateStudentDetails(Long studentId, UpdateStudentDetailsRequest updateStudentDetailsRequest){
        StudentEntity studentEntity = studentEntityRepository.findById(studentId)
                .orElseThrow(()-> new StudentManagerException(ErrorType.STUDENT_NOT_FOUND, "Student with this ID doesn't exist"));

        if(Objects.nonNull(updateStudentDetailsRequest.getFullName())){
            studentEntity.setFullName(updateStudentDetailsRequest.getFullName());
            studentEntityRepository.save(studentEntity);
        }
        if(Objects.nonNull(updateStudentDetailsRequest.getEmail())){
            studentEntity.setEmail(updateStudentDetailsRequest.getEmail());
            studentEntityRepository.save(studentEntity);


        }
        if(Objects.nonNull(updateStudentDetailsRequest.getLevel())){
            studentEntity.setLevel(updateStudentDetailsRequest.getLevel());

            studentEntityRepository.save(studentEntity);

        }
        if(Objects.nonNull(updateStudentDetailsRequest.getDepartment())){
            studentEntity.setDepartment(updateStudentDetailsRequest.getDepartment());
            studentEntityRepository.save(studentEntity);

        }

        return mapEntityToDto(studentEntity);


    }

    public Page<StudentDto>findAllStudents(Department department, Level level,int size, int page) {
        Page<StudentEntity> students;
        Pageable pageable = PageRequest.of(page, size, Sort.by("fullName").ascending());
        if (department != null && level != null) {
            students = studentEntityRepository.findByLevelAndDepartment(pageable, level, department);
        } else if (level != null) {
            students = studentEntityRepository.findByLevel(pageable, level);
        } else if (department != null) {
            students = studentEntityRepository.findByDepartment(pageable, department);
        } else {
            students = studentEntityRepository.findAll(pageable);
        }

        return students.map(this::mapEntityToDto);


    }





}
