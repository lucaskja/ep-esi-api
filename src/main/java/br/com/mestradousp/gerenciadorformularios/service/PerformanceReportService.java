package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportCreateDto;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.PerformanceReport;
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

    public List<PerformanceReport> findPerformanceReportByStudentId(Long id) {
        return this.performanceReportRepository.findByStudentId(id);
    }
}
