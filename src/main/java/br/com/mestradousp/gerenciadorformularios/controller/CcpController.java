package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorRequestCreateDto;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Ccp;
import br.com.mestradousp.gerenciadorformularios.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ccp")
public class CcpController {
    private final ProfessorService professorService;
    private final StudentService studentService;
    private final CcpService ccpService;

    @PostMapping("/register/professor")
    public ResponseEntity<Void> createProfessor(@RequestBody @Valid ProfessorRequestCreateDto dto) {
        this.professorService.createProfessor(dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/register/approve/student/{id}")
    public ResponseEntity<Void> approveStudent(@PathVariable Long id) {
        this.studentService.approveStudent(id);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ccp> getCcpById(@PathVariable Long id) {
        return ResponseEntity.ok(this.ccpService.findCcpById(id).orElseThrow(() -> new NotFoundException("User not found")));
    }
}
