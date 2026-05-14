package com.project.student_manager.service;

import com.project.student_manager.dto.CourseDto;
import com.project.student_manager.entities.CourseEntity;
import com.project.student_manager.enums.Department;
import com.project.student_manager.enums.ErrorType;
import com.project.student_manager.enums.Level;
import com.project.student_manager.exceptions.StudentManagerException;
import com.project.student_manager.repositories.CourseEntityRepository;
import com.project.student_manager.request.CourseRegisterationRequest;
import com.project.student_manager.request.CourseUpdateRequest;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseEntityRepository courseEntityRepository;

    @Lazy private final EnrollmentService enrollmentService;

    private final RecordService recordService;

    public CourseService(
            CourseEntityRepository courseEntityRepository,
            @Lazy EnrollmentService enrollmentService,
            RecordService recordService) {
        this.courseEntityRepository = courseEntityRepository;
        this.enrollmentService = enrollmentService;
        this.recordService = recordService;
    }

    public CourseEntity findByCourseId(Long courseId) {
        return courseEntityRepository
                .findById(courseId)
                .orElseThrow(
                        () ->
                                new StudentManagerException(
                                        ErrorType.COURSE_NOT_FOUND, "Course not found"));
    }

    public CourseDto mapEntityToDto(CourseEntity courseEntity) {
        return new CourseDto(
                courseEntity.getCourseId(),
                courseEntity.getCourseCode(),
                courseEntity.getCourseName(),
                courseEntity.getCourseUnit(),
                courseEntity.getSemester(),
                courseEntity.getLevel(),
                courseEntity.getDepartment());
    }

    public CourseDto registerCourse(CourseRegisterationRequest courseRegisterationRequest) {
        if (courseEntityRepository
                .findByCourseCode(courseRegisterationRequest.getCourseCode())
                .isPresent()) {
            throw new StudentManagerException(
                    ErrorType.COURSE_ALREADY_REGISTERED, "This course has already been registered");
        }

        if (courseEntityRepository
                .findByCourseName(courseRegisterationRequest.getCourseName())
                .isPresent()) {
            throw new StudentManagerException(
                    ErrorType.COURSE_ALREADY_REGISTERED, "This course has already been registered");
        }

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseCode(courseRegisterationRequest.getCourseCode());
        courseEntity.setCourseName(courseRegisterationRequest.getCourseName());
        courseEntity.setCourseUnit(courseRegisterationRequest.getCourseUnit());
        courseEntity.setSemester(courseRegisterationRequest.getSemester());
        courseEntity.setLevel(courseRegisterationRequest.getLevel());
        courseEntity.setDepartment(courseRegisterationRequest.getDepartment());

        courseEntityRepository.save(courseEntity);

        return mapEntityToDto(courseEntity);
    }

    public CourseDto updateCourse(Long courseId, CourseUpdateRequest req) {
        CourseEntity courseEntity = findByCourseId(courseId);
        if (req.getCourseCode() != null
                && !req.getCourseCode().equals(courseEntity.getCourseCode())) {
            courseEntityRepository
                    .findByCourseCode(req.getCourseCode())
                    .ifPresent(
                            existingCourse -> {
                                if (!existingCourse.getCourseId().equals(courseId)) {
                                    throw new StudentManagerException(
                                            ErrorType.COURSE_ALREADY_REGISTERED,
                                            "This course has already been registered");
                                }
                            });
            courseEntity.setCourseCode(req.getCourseCode());
        }

        if (req.getCourseName() != null
                && !req.getCourseName().equals(courseEntity.getCourseName())) {
            courseEntityRepository
                    .findByCourseName(req.getCourseName())
                    .ifPresent(
                            existingCourse -> {
                                if (!existingCourse.getCourseId().equals(courseId)) {
                                    throw new StudentManagerException(
                                            ErrorType.COURSE_ALREADY_REGISTERED,
                                            "This course has already been registered");
                                }
                            });
            courseEntity.setCourseName(req.getCourseName());
        }

        if (req.getCourseUnit() != null) {
            courseEntity.setCourseUnit(req.getCourseUnit());
        }

        if (req.getSemester() != null) {
            courseEntity.setSemester(req.getSemester());
        }

        if (req.getLevel() != null) {
            courseEntity.setLevel(req.getLevel());
        }

        if (req.getDepartment() != null) {
            courseEntity.setDepartment(req.getDepartment());
        }

        courseEntityRepository.save(courseEntity);

        return mapEntityToDto(courseEntity);
    }

    @Transactional
    public String deleteCourse(Long courseId) {
        CourseEntity courseEntity = findByCourseId(courseId);

        // Find enrollment IDs for the course
        List<Long> enrollmentIds = enrollmentService.findEnrollmentIdsByCourseId(courseId);

        // Delete records by enrollment IDs
        if (!enrollmentIds.isEmpty()) {
            recordService.deleteRecordsByEnrollmentIds(enrollmentIds);
        }

        // Delete enrollments by course ID
        enrollmentService.deleteEnrollmentsByCourseId(courseId);
        courseEntityRepository.delete(courseEntity);

        return "Course deleted successfully";
    }

    public CourseDto getCourseById(Long courseId) {
        CourseEntity courseEntity = findByCourseId(courseId);
        return mapEntityToDto(courseEntity);
    }

    public Page<CourseDto> getAllCourses(Department department, Level level, int page, int size) {
        Page<CourseEntity> courses;
        Pageable pageable = PageRequest.of(page, size);
        if (department != null && level != null) {
            courses =
                    courseEntityRepository.findAllByDepartmentAndLevel(department, level, pageable);
        } else if (department != null) {
            courses = courseEntityRepository.findAllByDepartment(department, pageable);
        } else if (level != null) {
            courses = courseEntityRepository.findAllByLevel(level, pageable);
        } else {
            courses = courseEntityRepository.findAllCourses(pageable);
        }
        return courses.map(this::mapEntityToDto);
    }
}
