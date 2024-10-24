package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.evaluation.EvaluationFormDto;
import br.com.mestradousp.gerenciadorformularios.enums.Opinions;
import br.com.mestradousp.gerenciadorformularios.enums.Roles;
import br.com.mestradousp.gerenciadorformularios.service.EvaluationFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
public class EvaluationFormController {

    private final EvaluationFormService evaluationFormService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitEvaluation(@RequestBody EvaluationFormDto evaluationFormDto) {
        evaluationFormService.submitCCPEvaluation(
                evaluationFormDto.getStudentId(),
                evaluationFormDto.getProfessorId(),
                evaluationFormDto.getPerformanceReview(),
                Roles.valueOf(evaluationFormDto.getRole()),
                Opinions.valueOf(evaluationFormDto.getPerformance()));

        return ResponseEntity.ok("Formulário de avaliação submetido com sucesso");
    }
}
