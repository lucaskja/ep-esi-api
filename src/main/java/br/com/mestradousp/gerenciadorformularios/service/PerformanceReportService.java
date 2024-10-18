package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.ccp.CcpCreateOpinionDto;
import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportCreateDto;
import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportGetResponseDto;
import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportProfessorOpinionDto;
import br.com.mestradousp.gerenciadorformularios.exception.ConflictException;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Ccp;
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

    public List<PerformanceReportGetResponseDto> findStudentPerformancesReport(Long studentId) {
        Student student = this.studentService.findStudentById(studentId).orElseThrow(() -> new NotFoundException("Student not found"));

        PerformanceReportGetResponseDto performanceReportGetResponseDto = PerformanceReportGetResponseDto.builder()
                .professorOpinion(student.getPerformanceReports().getFirst().getProfessorOpinion())
                .professorFinalOpinion(student.getPerformanceReports().getFirst().getProfessorFinalOpinion())
                .ccpOpinion(student.getPerformanceReports().getFirst().getCcpOpinion())
                .ccpFinalOpinion(student.getPerformanceReports().getFirst().getCcpFinalOpinion())
                .academicEventsResume(student.getPerformanceReports().getFirst().getAcademicEventsResume())
                .researchResume(student.getPerformanceReports().getFirst().getResearchResume())
                .studentObservation(student.getPerformanceReports().getFirst().getStudentObservation())
                .qualificationExamDate(student.getExam().getQualificationExamDate())
                .qualificationExamDeadline(student.getExam().getQualificationExamDeadline())
                .languageProficiencyExamDate(student.getExam().getLanguageProficiencyExamDate())
                .languageProficiencyDeadline(student.getExam().getLanguageProficiencyDeadline())
                .assigmentDeadline(student.getExam().getAssigmentDeadline())
                .approvedArticles(student.getArticle().getApprovedArticles())
                .reviewingArticles(student.getArticle().getReviewingArticles())
                .writingArticles(student.getArticle().getWritingArticles())
                .build();

        if (student.getPerformanceReports().size() == 1) {
            return List.of(performanceReportGetResponseDto);
        }

        return List.of(
                performanceReportGetResponseDto,
                PerformanceReportGetResponseDto.builder()
                        .professorOpinion(student.getPerformanceReports().get(1).getProfessorOpinion())
                        .professorFinalOpinion(student.getPerformanceReports().get(1).getProfessorFinalOpinion())
                        .ccpOpinion(student.getPerformanceReports().get(1).getCcpOpinion())
                        .ccpFinalOpinion(student.getPerformanceReports().get(1).getCcpFinalOpinion())
                        .academicEventsResume(student.getPerformanceReports().get(1).getAcademicEventsResume())
                        .researchResume(student.getPerformanceReports().get(1).getResearchResume())
                        .studentObservation(student.getPerformanceReports().get(1).getStudentObservation())
                        .qualificationExamDate(student.getExam().getQualificationExamDate())
                        .qualificationExamDeadline(student.getExam().getQualificationExamDeadline())
                        .languageProficiencyExamDate(student.getExam().getLanguageProficiencyExamDate())
                        .languageProficiencyDeadline(student.getExam().getLanguageProficiencyDeadline())
                        .assigmentDeadline(student.getExam().getAssigmentDeadline())
                        .approvedArticles(student.getArticle().getApprovedArticles())
                        .reviewingArticles(student.getArticle().getReviewingArticles())
                        .writingArticles(student.getArticle().getWritingArticles())
                        .build()
        );
    }
}
