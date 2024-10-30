package br.com.mestradousp.gerenciadorformularios.dto.evaluation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluationFormDto {
    private Long studentId;
    private Long professorId;
    private String role;
    private String performanceReview;
    private String performance;
}