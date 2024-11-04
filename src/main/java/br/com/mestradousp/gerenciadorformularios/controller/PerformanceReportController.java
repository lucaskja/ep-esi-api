package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.article.ArticleUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.ccp.CcpCreateOpinionDto;
import br.com.mestradousp.gerenciadorformularios.dto.exam.ExamUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportCreateDto;
import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportGetResponseDto;
import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportProfessorOpinionDto;
import br.com.mestradousp.gerenciadorformularios.exception.ConflictException;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.exception.BadRequestException;
import br.com.mestradousp.gerenciadorformularios.model.Article;
import br.com.mestradousp.gerenciadorformularios.model.Exam;
import br.com.mestradousp.gerenciadorformularios.model.PerformanceReport;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.service.ArticleService;
import br.com.mestradousp.gerenciadorformularios.service.ExamService;
import br.com.mestradousp.gerenciadorformularios.service.PerformanceReportService;
import br.com.mestradousp.gerenciadorformularios.service.StudentService;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/performance-report")
public class PerformanceReportController {
    private final PerformanceReportService performanceReportService;
    private final ExamService examService;
    private final ArticleService articleService;
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Void> createPerformanceReport(@RequestBody @Valid PerformanceReportCreateDto dto) {
        List<PerformanceReport> performanceReports = performanceReportService.findPerformanceReportByStudentId(dto.studentId());

        if (performanceReports.size() > 2) {
            throw new ConflictException("It already has 2 Performance Reports");
        }

        Student student = studentService.findStudentById(dto.studentId()).orElseThrow(() -> new NotFoundException("Student not found"));

        if (performanceReports.isEmpty()) {
            this.performanceReportService.createPerformanceReport(dto);
            Exam exam = this.examService.createExam(dto);
            Article article = this.articleService.createArticle(dto);

            student.setExam(exam);
            student.setArticle(article);

            studentService.updateStudent(student);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        this.performanceReportService.createPerformanceReport(dto);

        student.getExam().setQualificationExamDate(dto.qualificationExamDate());
        student.getExam().setQualificationExamDeadline(dto.qualificationExamDeadline());
        student.getExam().setLanguageProficiencyExamDate(dto.languageProficiencyExamDate());
        student.getExam().setLanguageProficiencyDeadline(dto.languageProficiencyDeadline());
        student.getExam().setAssigmentDeadline(dto.assigmentDeadline());

        this.examService.updateExam(student.getExam());

        student.getArticle().setApprovedArticles(student.getArticle().getApprovedArticles() + dto.approvedArticles());
        student.getArticle().setReviewingArticles(dto.reviewingArticles());
        student.getArticle().setWritingArticles(dto.writingArticles());

        this.articleService.updateArticle(student.getArticle());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/professor/opinion/{id}")
    public ResponseEntity<Void> createProfessorOpinion(
            @PathVariable Long id,
            @Valid @RequestBody PerformanceReportProfessorOpinionDto dto
    ) {
        this.performanceReportService.createProfessorOpinion(id, dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/ccp/opinion/{id}")
    public ResponseEntity<Void> createCppOpinion(
            @PathVariable Long id,
            @Valid @RequestBody CcpCreateOpinionDto dto
    ) {
        this.performanceReportService.createCcpOpinion(id, dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<PerformanceReportGetResponseDto>> getStudentPerformancesReport(
            @PathVariable Long id,
            Principal principal
    ) {
        String authenticatedUserEmail = principal.getName();

        return ResponseEntity.ok(this.performanceReportService.findStudentPerformancesReport(id, authenticatedUserEmail));
    }
}
