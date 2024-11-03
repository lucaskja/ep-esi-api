package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.ccp.CcpCreateOpinionDto;
import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportCreateDto;
import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportGetResponseDto;
import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportProfessorOpinionDto;
import br.com.mestradousp.gerenciadorformularios.exception.ConflictException;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.PerformanceReport;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.repository.PerformanceReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PerformanceReportService {
    private final PerformanceReportRepository performanceReportRepository;
    private final StudentService studentService;
    private final ProfessorService professorService;
    private final CcpService ccpService;

    public void createPerformanceReport(PerformanceReportCreateDto dto) {
        Student student = studentService.findStudentById(dto.studentId())
                .orElseThrow(() -> new NotFoundException("Student not found"));

        performanceReportRepository.save(
                PerformanceReport.builder()
                        .academicEventsResume(dto.academicEventsResume())
                        .researchResume(dto.researchResume())
                        .studentObservation(dto.studentObservation())
                        .student(student)
                        .build()
        );
    }

    public void createProfessorOpinion(Long id, PerformanceReportProfessorOpinionDto dto) {
        Professor professor = this.professorService.findProfessorById(dto.professorId()).orElseThrow(() -> new NotFoundException("Professor not found"));
        PerformanceReport performanceReport = this.performanceReportRepository.findById(id).orElseThrow(() -> new NotFoundException("Performance Report not found"));

        if (!professor.getStudents().contains(performanceReport.getStudent())) {
            throw new ConflictException("Professor student and performance report student are different");
        }

        performanceReport.setProfessorOpinion(dto.professorOpinion());
        performanceReport.setProfessorFinalOpinion(dto.professorFinalOpinion());

        this.performanceReportRepository.save(performanceReport);
    }

    public void createCcpOpinion(Long id, CcpCreateOpinionDto dto) {
        this.ccpService.findCcpById(dto.ccpId()).orElseThrow(() -> new NotFoundException("Ccp not found"));
        PerformanceReport performanceReport = this.performanceReportRepository.findById(id).orElseThrow(() -> new NotFoundException("Performance Report not found"));

        performanceReport.setCcpOpinion(dto.ccpOpinion());
        performanceReport.setCcpFinalOpinion(dto.ccpFinalOpinion());

        this.performanceReportRepository.save(performanceReport);
    }

    public List<PerformanceReport> findPerformanceReportByStudentId(Long id) {
        return this.performanceReportRepository.findByStudentId(id);
    }

    public List<PerformanceReportGetResponseDto> findStudentPerformancesReport(Long Id, String authenticatedEmail) {
        Student student = this.studentService.findStudentById(Id)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        if (!student.getEmail().equals(authenticatedEmail)) {
            throw new ConflictException("You are not allowed to view this student's reports");
        }
    
        List<PerformanceReport> performanceReports = student.getPerformanceReports();
    
        if (performanceReports.isEmpty()) {
            throw new NotFoundException("No performance reports found for this student");
        }

        PerformanceReportGetResponseDto performanceReportGetResponseDto = PerformanceReportGetResponseDto.builder()
                .professorOpinion(performanceReports.get(0).getProfessorOpinion())
                .professorFinalOpinion(performanceReports.get(0).getProfessorFinalOpinion())
                .ccpOpinion(performanceReports.get(0).getCcpOpinion())
                .ccpFinalOpinion(performanceReports.get(0).getCcpFinalOpinion())
                .academicEventsResume(performanceReports.get(0).getAcademicEventsResume())
                .researchResume(performanceReports.get(0).getResearchResume())
                .studentObservation(performanceReports.get(0).getStudentObservation())
                .qualificationExamDate(student.getExam().getQualificationExamDate())
                .qualificationExamDeadline(student.getExam().getQualificationExamDeadline())
                .languageProficiencyExamDate(student.getExam().getLanguageProficiencyExamDate())
                .languageProficiencyDeadline(student.getExam().getLanguageProficiencyDeadline())
                .assigmentDeadline(student.getExam().getAssigmentDeadline())
                .approvedArticles(student.getArticle().getApprovedArticles())
                .reviewingArticles(student.getArticle().getReviewingArticles())
                .writingArticles(student.getArticle().getWritingArticles())
                .createdAt(performanceReports.get(0).getCreatedAt())
                .build();

        if (performanceReports.size() == 1) {
            return List.of(performanceReportGetResponseDto);
        }

        return List.of(
                performanceReportGetResponseDto,
                PerformanceReportGetResponseDto.builder()
                        .professorOpinion(performanceReports.get(1).getProfessorOpinion())
                        .professorFinalOpinion(performanceReports.get(1).getProfessorFinalOpinion())
                        .ccpOpinion(performanceReports.get(1).getCcpOpinion())
                        .ccpFinalOpinion(performanceReports.get(1).getCcpFinalOpinion())
                        .academicEventsResume(performanceReports.get(1).getAcademicEventsResume())
                        .researchResume(performanceReports.get(1).getResearchResume())
                        .studentObservation(performanceReports.get(1).getStudentObservation())
                        .qualificationExamDate(student.getExam().getQualificationExamDate())
                        .qualificationExamDeadline(student.getExam().getQualificationExamDeadline())
                        .languageProficiencyExamDate(student.getExam().getLanguageProficiencyExamDate())
                        .languageProficiencyDeadline(student.getExam().getLanguageProficiencyDeadline())
                        .assigmentDeadline(student.getExam().getAssigmentDeadline())
                        .approvedArticles(student.getArticle().getApprovedArticles())
                        .reviewingArticles(student.getArticle().getReviewingArticles())
                        .writingArticles(student.getArticle().getWritingArticles())
                        .createdAt(performanceReports.get(1).getCreatedAt())
                        .build()
        );
    }
}
