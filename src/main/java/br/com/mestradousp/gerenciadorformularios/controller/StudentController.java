package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.student.StudentProfileDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.UpdateLattesLinkDto;
import br.com.mestradousp.gerenciadorformularios.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    // Endpoint para buscar o perfil do aluno
    @GetMapping("/{studentId}/profile")
    public ResponseEntity<StudentProfileDto> getStudentProfile(@PathVariable Long studentId, @RequestParam String authenticatedEmail) {
        return ResponseEntity.ok(studentService.getStudentProfile(studentId, authenticatedEmail));
    }

    // Endpoint para atualizar o link do Lattes
    @PutMapping("/{studentId}/profile/lattes")
    public ResponseEntity<Void> updateLattesLink(@PathVariable Long studentId, @RequestBody UpdateLattesLinkDto updateLattesLinkDto, @RequestParam String authenticatedEmail) {
        studentService.updateLattesLink(studentId, updateLattesLinkDto, authenticatedEmail);
        return ResponseEntity.ok().build();
    }
}