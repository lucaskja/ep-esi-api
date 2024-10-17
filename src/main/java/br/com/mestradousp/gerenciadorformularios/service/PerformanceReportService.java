package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.repository.PerformanceReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PerformanceReportService {
    private final PerformanceReportRepository performanceReportRepository;
    private final ExamService examService;
    private final StudentService studentService;
}
