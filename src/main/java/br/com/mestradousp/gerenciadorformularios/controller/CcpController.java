package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorRequestCreateDto;
import br.com.mestradousp.gerenciadorformularios.service.CcpService;
import br.com.mestradousp.gerenciadorformularios.service.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/ccp")
public class CcpController {
    private final CcpService ccpService;
    private final ProfessorService professorService;

    @PostMapping("/register/professor")
    public ResponseEntity<Void> createProfessor(@RequestBody @Valid ProfessorRequestCreateDto dto) {
        this.professorService.createProfessor(dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
