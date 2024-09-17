package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.student.StudentCreateDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentResponseDto;
import br.com.mestradousp.gerenciadorformularios.service.ProfessorService;
import br.com.mestradousp.gerenciadorformularios.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final ProfessorService professorService;

    @Autowired
    public StudentController(StudentService studentService, ProfessorService professorService) {
        this.studentService = studentService;
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody @Valid StudentCreateDto dto) {
        studentService.validateIfStudentIsAlreadyCreated(dto.getUspNumber());
        professorService.validateIfProfessorExists(dto.getProfessorUspNumber());

        return ResponseEntity.ok(this.studentService.createStudent(dto));
    }
}
