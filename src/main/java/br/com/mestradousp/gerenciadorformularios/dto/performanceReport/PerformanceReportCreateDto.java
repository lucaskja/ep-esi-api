package br.com.mestradousp.gerenciadorformularios.dto.performanceReport;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PerformanceReportCreateDto(
    @NotNull
    String studentId,

    @NotBlank
    String studentText,

    @NotNull
    Boolean hasDifficult,

    LocalDate qualificationExamDate,

    LocalDate proficientExamDate,

    @NotNull
    LocalDate finalAssigmentDeadline,

    @NotNull
    LocalDate qualificationExamDeadline,

    @NotNull
    @Min(0)
    Integer writingArticles,

    @NotNull
    @Min(0)
    Integer reviewingArticles,

    @NotNull
    @Min(0)
    Integer approvedArticles
) {
}
