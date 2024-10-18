package br.com.mestradousp.gerenciadorformularios.dto.performanceReport;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PerformanceReportCreateDto(
    @NotNull
    Long studentId,

    @NotBlank
    String academicEventsResume,

    @NotBlank
    String researchResume,

    @NotBlank
    String studentObservation,

    @NotNull
    Boolean hasDifficult,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate qualificationExamDate,

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate qualificationExamDeadline,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate languageProficiencyExamDate,

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate languageProficiencyDeadline,

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate assigmentDeadline,

    @Min(0)
    @NotNull
    Integer writingArticles,

    @Min(0)
    @NotNull
    Integer reviewingArticles,

    @Min(0)
    @NotNull
    Integer approvedArticles
) {
}
