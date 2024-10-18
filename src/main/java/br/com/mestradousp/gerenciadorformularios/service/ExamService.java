package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.exam.ExamUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportCreateDto;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Exam;
import br.com.mestradousp.gerenciadorformularios.repository.ExamRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

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
