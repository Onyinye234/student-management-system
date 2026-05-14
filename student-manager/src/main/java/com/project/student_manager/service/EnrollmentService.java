package com.project.student_manager.service;

import com.project.student_manager.entities.CourseEntity;
import com.project.student_manager.entities.EnrollmentEntity;
import com.project.student_manager.entities.StudentEntity;
import com.project.student_manager.enums.ErrorType;
import com.project.student_manager.exceptions.StudentManagerException;
import com.project.student_manager.repositories.EnrollmentEntityRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentEntityRepository enrollmentEntityRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(
            EnrollmentEntityRepository enrollmentEntityRepository,
            StudentService studentService,
            @Lazy CourseService courseService) {
        this.enrollmentEntityRepository = enrollmentEntityRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public EnrollmentEntity findByStudentIdAndCourseId(Long studentId, Long courseId) {
        return enrollmentEntityRepository
                .findByStudentStudentIdAndCourseCourseId(studentId, courseId)
                .orElseThrow(
                        () ->
                                new StudentManagerException(
                                        ErrorType.ENROLLMENT_NOT_FOUND,
                                        "Student has not been enrolled for this course"));
    }

    public List<Long> findEnrollmentIdsByCourseId(Long courseId) {
        return enrollmentEntityRepository.findEnrollmentIdsByCourseId(courseId);
    }

    public void deleteEnrollmentsByCourseId(Long courseId) {
        enrollmentEntityRepository.deleteByCourse_CourseId(courseId);
    }

    @Transactional
    public void enrollStudentForCourse(Long studentId, Long courseId) {
        StudentEntity student = studentService.getStudentById(studentId);
        CourseEntity course = courseService.findByCourseId(courseId);

        if (enrollmentEntityRepository
                .findByStudentStudentIdAndCourseCourseId(studentId, courseId)
                .isPresent()) {
            throw new StudentManagerException(
                    ErrorType.ENROLLMENT_ALREADY_EXISTS,
                    "Student has already been enrolled for this course");
        }

        if (!course.getLevel().equals(student.getLevel())) {
            throw new StudentManagerException(
                    ErrorType.NOT_ELIGIBLE, "Student is not eligible for this course");
        }
        if (!course.getDepartment().equals(student.getDepartment())) {
            throw new StudentManagerException(
                    ErrorType.NOT_ELIGIBLE, "Student is not eligible for this course");
        }
        EnrollmentEntity enrollment = new EnrollmentEntity();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setLevel(course.getLevel());
        enrollment.setSemester(course.getSemester());
        enrollmentEntityRepository.save(enrollment);
        student.getEnrollments().add(enrollment);
        course.getEnrollments().add(enrollment);
    }
}
