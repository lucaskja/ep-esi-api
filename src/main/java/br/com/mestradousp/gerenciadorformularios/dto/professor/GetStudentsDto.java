package br.com.mestradousp.gerenciadorformularios.dto.professor;

import br.com.mestradousp.gerenciadorformularios.model.PerformanceReport;
import lombok.Builder;

import java.util.List;

@Builder
public record GetStudentsDto(
        String uspNumber,
        String studentName,
        List<PerformanceReport> reports
) {
}
