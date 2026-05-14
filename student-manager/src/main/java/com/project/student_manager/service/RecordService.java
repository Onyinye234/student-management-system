package com.project.student_manager.service;

import com.project.student_manager.entities.EnrollmentEntity;
import com.project.student_manager.entities.RecordEntity;
import com.project.student_manager.enums.Grade;
import com.project.student_manager.repositories.RecordEntityRepository;
import com.project.student_manager.request.UpdateScoreRequest;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecordService {

    private final EnrollmentService enrollmentService;
    private final RecordEntityRepository recordEntityRepository;

    public void deleteRecordsByEnrollmentIds(List<Long> enrollmentIds) {
        recordEntityRepository.deleteByEnrollment_EnrollmentIdIn(enrollmentIds);
    }

    public Grade grading(Float total) {
        if (total >= 70) {
            return Grade.GRADE_A;
        } else if (total >= 60) {
            return Grade.GRADE_B;
        } else if (total >= 50) {
            return Grade.GRADE_C;
        } else if (total >= 45) {
            return Grade.GRADE_D;
        } else if (total >= 40) {
            return Grade.GRADE_E;
        } else {
            return Grade.GRADE_F;
        }
    }

    public void computeTotalAndGrade(RecordEntity recordEntity) {
        float ca = recordEntity.getCa() == null ? 0 : recordEntity.getCa();
        float exam = recordEntity.getExam() == null ? 0 : recordEntity.getExam();

        recordEntity.setTotal(ca + exam);

        recordEntity.setGrade(grading(recordEntity.getTotal()));
    }

    public void UpdateScore(Long studentId, Long courseId, UpdateScoreRequest updateScoreRequest) {
        EnrollmentEntity enrollmentEntity =
                enrollmentService.findByStudentIdAndCourseId(studentId, courseId);
        RecordEntity recordEntity =
                recordEntityRepository
                        .findByEnrollment_EnrollmentId(enrollmentEntity.getEnrollmentId())
                        .orElseGet(
                                () -> {
                                    RecordEntity newRecordEntity = new RecordEntity();
                                    newRecordEntity.setEnrollment(enrollmentEntity);
                                    return newRecordEntity;
                                });

        if (Objects.nonNull(updateScoreRequest.getCa())
                && Objects.nonNull(updateScoreRequest.getExam())) {
            recordEntity.setCa(updateScoreRequest.getCa());
            recordEntity.setExam(updateScoreRequest.getExam());
            computeTotalAndGrade(recordEntity);
            recordEntityRepository.save(recordEntity);
        } else if (Objects.nonNull(updateScoreRequest.getExam())) {
            recordEntity.setExam(updateScoreRequest.getExam());
            computeTotalAndGrade(recordEntity);
            recordEntityRepository.save(recordEntity);
        } else if (Objects.nonNull(updateScoreRequest.getCa())) {
            recordEntity.setCa(updateScoreRequest.getCa());
            computeTotalAndGrade(recordEntity);
            recordEntityRepository.save(recordEntity);
        }
    }
}
