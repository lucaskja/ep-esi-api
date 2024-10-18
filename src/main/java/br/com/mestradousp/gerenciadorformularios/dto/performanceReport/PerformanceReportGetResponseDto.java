package br.com.mestradousp.gerenciadorformularios.dto.performanceReport;

import br.com.mestradousp.gerenciadorformularios.enums.Opinions;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PerformanceReportGetResponseDto(
        String professorOpinion,

        Opinions professorFinalOpinion,

        String  ccpOpinion,

        Opinions ccpFinalOpinion,

        String academicEventsResume,

        String researchResume,

        String studentObservation,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate qualificationExamDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate qualificationExamDeadline,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate languageProficiencyExamDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate languageProficiencyDeadline,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate assigmentDeadline,

        @Min(0)
        Integer writingArticles,

        @Min(0)
        Integer reviewingArticles,

        @Min(0)
        Integer approvedArticles
) {
}
