package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportCreateDto;
import br.com.mestradousp.gerenciadorformularios.model.Exam;
import br.com.mestradousp.gerenciadorformularios.repository.ExamRepository;
import org.springframework.stereotype.Service;


@Service
public class ExamService {
    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Exam createExam(PerformanceReportCreateDto dto) {
        return this.examRepository.save(Exam.builder()
                .qualificationExamDate(dto.qualificationExamDate())
                .qualificationExamDeadline(dto.qualificationExamDeadline())
                .languageProficiencyExamDate(dto.languageProficiencyExamDate())
                .languageProficiencyDeadline(dto.languageProficiencyDeadline())
                .assigmentDeadline(dto.assigmentDeadline())
                .build()
        );
    }

    public void updateExam(Exam exam) {
        this.examRepository.save(exam);
    }
}
