package com.project.student_manager.service;

import com.project.student_manager.dto.StudentDto;
<<<<<<< HEAD
import com.project.student_manager.enums.Department;
=======
import com.project.student_manager.entities.StudentEntity;
>>>>>>> aa6c9dafc1ce6f65e33c3a0ef49a97922f6ac86e
import com.project.student_manager.enums.ErrorType;
import com.project.student_manager.enums.Level;
import com.project.student_manager.exceptions.StudentManagerException;
import com.project.student_manager.repositories.StudentEntityRepository;
import com.project.student_manager.request.StudentRegistrationRequest;
<<<<<<< HEAD
import com.project.student_manager.entities.StudentEntity;
import com.project.student_manager.request.UpdateStudentDetailsRequest;
=======
>>>>>>> aa6c9dafc1ce6f65e33c3a0ef49a97922f6ac86e
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.Objects;

=======
>>>>>>> aa6c9dafc1ce6f65e33c3a0ef49a97922f6ac86e
@AllArgsConstructor
@Service
public class StudentService {

    private final StudentEntityRepository studentEntityRepository;

    public StudentDto mapEntityToDto(StudentEntity studentEntity) {
        return new StudentDto(
<<<<<<< HEAD
                studentEntity.getStudentId(),
                studentEntity.getFullName(),
                studentEntity.getEmail(),
                studentEntity.getMatricNumber(),
                studentEntity.getLevel(),
                studentEntity.getEnrolledAt(),
                studentEntity.getDepartment()
=======
            studentEntity.getStudentId(),
            studentEntity.getFullName(),
            studentEntity.getEmail(),
            studentEntity.getMatricNumber(),
            studentEntity.getLevel(),
            studentEntity.getEnrolledAt()
>>>>>>> aa6c9dafc1ce6f65e33c3a0ef49a97922f6ac86e
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

<<<<<<< HEAD
        StudentEntity studentEntity = new StudentEntity(studentRegistrationRequest.getFullName(), studentRegistrationRequest.getEmail(), studentRegistrationRequest.getMatricNumber(), studentRegistrationRequest.getLevel(), studentRegistrationRequest.getDepartment());
=======
        StudentEntity studentEntity = new StudentEntity(
            studentRegistrationRequest.getFullName(),
            studentRegistrationRequest.getEmail(),
            studentRegistrationRequest.getMatricNumber(),
            studentRegistrationRequest.getLevel()
        );
>>>>>>> aa6c9dafc1ce6f65e33c3a0ef49a97922f6ac86e
        studentEntityRepository.save(studentEntity);
        return mapEntityToDto(studentEntity);
    }

    public StudentDto getStudentById(Long studentId){
        StudentEntity studentEntity = studentEntityRepository.findById(studentId)
                .orElseThrow(()->   new StudentManagerException(ErrorType.STUDENT_NOT_FOUND, "Student with this ID doesn't exist"));

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
        return mapEntityToDto(studentEntity);


    }

    public Page<StudentDto>findAllStudents(Department department, Level level,int size, int page){
        Page<StudentEntity>students;
        Pageable pageable = PageRequest.of(page,size);
         if(department != null && level!= null){
            students = studentEntityRepository.findByLevelAndDepartment(pageable,level,department);
        }
        else if(level!= null){
            students = studentEntityRepository.findByLevel(pageable, level);
        }
        else if(department!= null){
            students = studentEntityRepository.findByDepartment(pageable, department);
        }

        else{
            students = studentEntityRepository.findAll(pageable);
        }

       return students.map(this::mapEntityToDto);


    }





}
