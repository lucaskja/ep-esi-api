package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportProfessorOpinionDto;
import br.com.mestradousp.gerenciadorformularios.dto.professor.GetStudentsDto;
import br.com.mestradousp.gerenciadorformularios.service.PerformanceReportService;
import br.com.mestradousp.gerenciadorformularios.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("professor")
public class ProfessorController {
    private final StudentService studentService;

    @GetMapping("/{id}/students")
    public ResponseEntity<List<GetStudentsDto>> getAllProfessorStudents(@PathVariable Long id) {
        return ResponseEntity.ok(this.studentService.getStudentsByProfessorId(id));
    }
}
