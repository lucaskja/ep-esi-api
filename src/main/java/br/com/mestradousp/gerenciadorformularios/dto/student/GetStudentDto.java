package br.com.mestradousp.gerenciadorformularios.dto.student;

import br.com.mestradousp.gerenciadorformularios.model.PerformanceReport;
import lombok.Builder;

import java.util.List;

@Builder
public record GetStudentDto(
        Long studentId,
        String uspNumber,
        String studentName,
        List<PerformanceReport> reports
) {
}
