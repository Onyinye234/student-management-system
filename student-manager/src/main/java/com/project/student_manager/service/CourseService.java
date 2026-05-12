package com.project.student_manager.service;

import com.project.student_manager.dto.CourseDto;
import com.project.student_manager.entities.CourseEntity;
import com.project.student_manager.enums.ErrorType;
import com.project.student_manager.exceptions.StudentManagerException;
import com.project.student_manager.repositories.CourseEntityRepository;
import com.project.student_manager.request.CourseRegisterationRequest;
import com.project.student_manager.request.CourseUpdateRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseService {

    private final CourseEntityRepository courseEntityRepository;

    // private final EnrollmentEntityRepository enrollmentEntityRepository;
    // private final ScoreEntityRepository scoreEntityRepository;

    public CourseEntity findById(Long id) {
        return courseEntityRepository
            .findById(id)
            .orElseThrow(() ->
                new StudentManagerException(
                    ErrorType.COURSE_NOT_FOUND,
                    "Course not found"
                )
            );
    }

    public CourseDto mapEntityToDto(CourseEntity courseEntity) {
        return new CourseDto(
            courseEntity.getCourseCode(),
            courseEntity.getCourseName(),
            courseEntity.getCourseUnit(),
            courseEntity.getSemester(),
            courseEntity.getLevel()
        );
    }

    public CourseDto registerCourse(CourseRegisterationRequest req) {
        if (
            courseEntityRepository
                .findByCourseCode(req.getCourseCode())
                .isPresent()
        ) {
            throw new StudentManagerException(
                ErrorType.COURSE_ALREADY_REGISTERED,
                "This course has already been registered"
            );
        }

        if (
            courseEntityRepository
                .findByCourseName(req.getCourseName())
                .isPresent()
        ) {
            throw new StudentManagerException(
                ErrorType.COURSE_ALREADY_REGISTERED,
                "This course has already been registered"
            );
        }

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseCode(req.getCourseCode());
        courseEntity.setCourseName(req.getCourseName());
        courseEntity.setCourseUnit(req.getCourseUnit());
        courseEntity.setSemester(req.getSemester());
        courseEntity.setLevel(req.getLevel());
        courseEntity.setDepartment(req.getDepartment());

        courseEntityRepository.save(courseEntity);

        return mapEntityToDto(courseEntity);
    }

    public CourseDto updateCourse(Long id, CourseUpdateRequest req) {
        CourseEntity courseEntity = findById(id);
        if (
            req.getCourseCode() != null &&
            !req.getCourseCode().equals(courseEntity.getCourseCode())
        ) {
            courseEntityRepository
                .findByCourseCode(req.getCourseCode())
                .ifPresent(existingCourse -> {
                    if (!existingCourse.getCourseId().equals(id)) {
                        throw new StudentManagerException(
                            ErrorType.COURSE_ALREADY_REGISTERED,
                            "This course has already been registered"
                        );
                    }
                });
            courseEntity.setCourseCode(req.getCourseCode());
        }

        if (
            req.getCourseName() != null &&
            !req.getCourseName().equals(courseEntity.getCourseName())
        ) {
            courseEntityRepository
                .findByCourseName(req.getCourseName())
                .ifPresent(existingCourse -> {
                    if (!existingCourse.getCourseId().equals(id)) {
                        throw new StudentManagerException(
                            ErrorType.COURSE_ALREADY_REGISTERED,
                            "This course has already been registered"
                        );
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

    // @Transactional
    // public String deleteCourse(Long id) {
    //     CourseEntity courseEntity = findById(id);

    //     List<Long> enrollmentIds =
    //         enrollmentEntityRepository.findEnrollmentIdsByCourseId(id);

    //     if (!enrollmentIds.isEmpty()) {
    //         scoreEntityRepository.deleteByEnrollment_EnrollmentIdIn(
    //             enrollmentIds
    //         );
    //     }

    //     enrollmentEntityRepository.deleteByCourse_Id(id);
    //     courseEntityRepository.delete(courseEntity);

    //     return "Course deleted successfully";
    // }

    public CourseDto getCourseById(Long id) {
        CourseEntity courseEntity = findById(id);
        return mapEntityToDto(courseEntity);
    }

    public List<CourseDto> getAllCourses() {
        List<CourseEntity> courseEntities = courseEntityRepository.findAll();
        return courseEntities.stream().map(this::mapEntityToDto).toList();
    }
}
