package br.com.mestradousp.gerenciadorformularios.dto.exam;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ExamUpdateDto(
        @NotNull
        Long examId,

        @NotNull
        LocalDate qualificationExamDate,

        @NotNull
        LocalDate qualificationExamDeadline,

        @NotNull
        LocalDate languageProficiencyExamDate,

        @NotNull
        LocalDate languageProficiencyDeadline,

        @NotNull
        LocalDate assigmentDeadline
) {
}
