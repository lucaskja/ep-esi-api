package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.performanceReport.PerformanceReportCreateDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentCreateDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentResponseDto;
import br.com.mestradousp.gerenciadorformularios.exception.ConflictException;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.PerformanceReport;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.service.PerformanceReportService;
import br.com.mestradousp.gerenciadorformularios.service.ProfessorService;
import br.com.mestradousp.gerenciadorformularios.service.StudentService;
import br.com.mestradousp.gerenciadorformularios.utils.EntityValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final PerformanceReportService performanceReportService;

    public StudentController(StudentService studentService, PerformanceReportService performanceReportService) {
        this.studentService = studentService;
        this.performanceReportService = performanceReportService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody @Valid StudentCreateDto dto) {
        return new ResponseEntity<>(this.studentService.createStudent(dto), HttpStatus.CREATED);
    }

    @PostMapping("/performance-report")
    public ResponseEntity<Void> createStudentReport(@RequestBody @Valid PerformanceReportCreateDto dto) {
        this.performanceReportService.createPerformanceReport(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
