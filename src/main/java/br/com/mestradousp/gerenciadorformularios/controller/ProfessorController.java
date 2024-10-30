package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportProfessorOpinionDto;
import br.com.mestradousp.gerenciadorformularios.service.PerformanceReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("professor")
public class ProfessorController {
    private final PerformanceReportService performanceReportService;

    @PostMapping("/performance-report/opinion/{id}")
    public ResponseEntity<Void> createProfessorOpinion(
            @PathVariable Long id,
            @Valid @RequestBody PerformanceReportProfessorOpinionDto dto
            ) {
        this.performanceReportService.createProfessorOpinion(id, dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
