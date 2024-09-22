package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportCreateDto;
import br.com.mestradousp.gerenciadorformularios.dto.util.StudentMapper;
import br.com.mestradousp.gerenciadorformularios.model.Exam;
import br.com.mestradousp.gerenciadorformularios.model.PerformanceReport;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.repository.PerformanceReportRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class PerformanceReportService {
    private final PerformanceReportRepository performanceReportRepository;
    private final ExamService examService;
    private final StudentService studentService;

    public PerformanceReportService(
            PerformanceReportRepository performanceReportRepository,
            StudentService studentService,
            ExamService examService
    ) {
        this.performanceReportRepository = performanceReportRepository;
        this.studentService = studentService;
        this.examService = examService;
    }

    private Exam buildExamFromDto(PerformanceReportCreateDto dto) {
        Exam exam = new Exam(
                null,
                null,
                null,
                dto.finalAssigmentDeadline(),
                dto.qualificationExamDeadline(),
                dto.writingArticles(),
                dto.reviewingArticles(),
                dto.approvedArticles()
        );

        if (dto.proficientExamDate() != null) exam.setProficientExamDate(dto.proficientExamDate());

        if (dto.qualificationExamDate() != null) exam.setQualificationExamDate(dto.qualificationExamDate());

        return exam;
    }

    private PerformanceReport buildPerformanceReportFromDto(PerformanceReportCreateDto dto, Student student) {
        return new PerformanceReport(
                null,
                null,
                null,
                dto.studentText(),
                null,
                null,
                dto.status(),
                null,
                dto.hasDifficult(),
                student
        );
    }

    public void createPerformanceReport(PerformanceReportCreateDto performanceReportDto) {
        Student student = studentService.validateIfStudentExists(performanceReportDto.studentId());

        Exam exam = buildExamFromDto(performanceReportDto);

        PerformanceReport report = buildPerformanceReportFromDto(performanceReportDto, student);

        student.setExam(exam);
        this.studentService.updateStudent(student);

        examService.createExam(exam);

        performanceReportRepository.save(report);
    }
}
