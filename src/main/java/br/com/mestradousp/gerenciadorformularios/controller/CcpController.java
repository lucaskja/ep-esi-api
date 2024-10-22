package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.ccp.CcpCreateOpinionDto;
import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorRequestCreateDto;
import br.com.mestradousp.gerenciadorformularios.service.*;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/ccp")
public class CcpController {
    private final PerformanceReportService performanceReportService;
    private final ProfessorService professorService;
    private final StudentService studentService;
    private final EmailService emailService;

    @PostMapping("/register/professor")
    public ResponseEntity<Void> createProfessor(@RequestBody @Valid ProfessorRequestCreateDto dto) {
        this.professorService.createProfessor(dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/opinion/{id}")
    public ResponseEntity<Void> createCppOpinion(
            @PathVariable Long id,
            @Valid @RequestBody CcpCreateOpinionDto dto
    ) {
        this.performanceReportService.createCcpOpinion(id, dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/student/{id}/approve")
    public ResponseEntity<Void> approveStudent(@PathVariable Long id) {
        this.studentService.approveStudent(id);

        return ResponseEntity.ok(null);
    }
}
