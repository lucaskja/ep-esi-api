package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.professor.GetProfessorDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.GetStudentDto;
import br.com.mestradousp.gerenciadorformularios.service.ProfessorService;
import br.com.mestradousp.gerenciadorformularios.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final StudentService studentService;
    private final ProfessorService professorService;

    @GetMapping("/all")
    public ResponseEntity<List<GetProfessorDto>> getAllProfessors() {
        return ResponseEntity.ok(this.professorService.getAllProfessors());
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<GetStudentDto>> getAllProfessorStudents(@PathVariable Long id) {
        return ResponseEntity.ok(this.studentService.getStudentsByProfessorId(id));
    }

    @GetMapping("{id}/student/{studentId}")
    public ResponseEntity<GetStudentDto> getAllProfessorStudents(
            @PathVariable Long id,
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(this.studentService.getStudentByProfessorIdAndStudentId(id, studentId));
    }
}
