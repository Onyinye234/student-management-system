package com.project.student_manager.service;

import com.project.student_manager.dto.CourseDto;
import com.project.student_manager.entities.CourseEntity;
import com.project.student_manager.enums.ErrorType;
import com.project.student_manager.exceptions.StudentManagerException;
import com.project.student_manager.repositories.CourseEntityRepository;
import com.project.student_manager.request.CourseRegisterationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseService {

    private final CourseEntityRepository courseEntityRepository;

    public CourseDto mapEntityToDto(CourseEntity courseEntity) {
        return new CourseDto(
            courseEntity.getCourseCode(),
            courseEntity.getCourseName(),
            courseEntity.getCourseUnit(),
            courseEntity.getSemester() != null
                ? courseEntity.getSemester().name()
                : null,
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

        courseEntityRepository.save(courseEntity);

        return mapEntityToDto(courseEntity);
    }
}
