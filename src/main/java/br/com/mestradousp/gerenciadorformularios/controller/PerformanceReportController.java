package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.article.ArticleUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.exam.ExamUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportCreateDto;
import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportGetResponseDto;
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
@RequestMapping("api/performance-report")
public class PerformanceReportController {
    private final PerformanceReportService performanceReportService;
    private final ExamService examService;
    private final ArticleService articleService;
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Void> createPerformanceReport(@RequestBody @Valid PerformanceReportCreateDto dto) {
        List<PerformanceReport> performanceReports = performanceReportService.findPerformanceReportByStudentId(dto.studentId());

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

    @GetMapping("/student/{id}")
    public ResponseEntity<List<PerformanceReportGetResponseDto>> getStudentPerformancesReport(
            @PathVariable Long id, Principal principal) {

        // Obter o e-mail do usuário autenticado
        String authenticatedUserEmail = principal.getName();

        // Verificar se o aluno autenticado corresponde ao aluno do relatório
        Student student = studentService.findStudentById(id)
            .orElseThrow(() -> new NotFoundException("Student not found"));

        if (!student.getEmail().equals(authenticatedUserEmail)) {
            throw new BadRequestException("You are not allowed to view this student's reports");
        }

        // Buscar relatórios de performance
        return ResponseEntity.ok(this.performanceReportService.findStudentPerformancesReport(id, authenticatedUserEmail));
    }
}
