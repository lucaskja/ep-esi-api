package br.com.mestradousp.gerenciadorformularios.dto.performanceReport;

import br.com.mestradousp.gerenciadorformularios.enums.Opinions;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PerformanceReportProfessorOpinionDto(
        @NotNull
        Long professorId,

        @NotBlank
        String professorOpinion,

        @NotNull
        Opinions professorFinalOpinion
) {
}
