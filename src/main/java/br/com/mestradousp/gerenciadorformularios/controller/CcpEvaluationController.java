package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.ccp.CcpEvaluationDto;
import br.com.mestradousp.gerenciadorformularios.service.CcpEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ccp-evaluation")
public class CcpEvaluationController {

    private final CcpEvaluationService ccpEvaluationService;

    public CcpEvaluationController(CcpEvaluationService ccpEvaluationService) {
        this.ccpEvaluationService = ccpEvaluationService;
    }

    @PostMapping
    public ResponseEntity<Void> submitCcpEvaluation(@RequestBody CcpEvaluationDto ccpEvaluationDto) {
        ccpEvaluationService.submitCcpEvaluation(ccpEvaluationDto);
        return ResponseEntity.ok().build();
    }
}
