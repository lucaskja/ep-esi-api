package br.com.mestradousp.gerenciadorformularios.controller;

import br.com.mestradousp.gerenciadorformularios.dto.student.GetStudentDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentProfileDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.UpdateLattesLinkDto;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{studentId}/profile")
    public ResponseEntity<StudentProfileDto> getStudentProfile(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentProfile(studentId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetStudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/pendent-students")
    public ResponseEntity<List<Student>> getStudentWithPendentStatus() {
        return ResponseEntity.ok(studentService.getStudentsWithPendentStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findStudentById(id).orElseThrow(() -> new NotFoundException("Student not found")));
    }

    @PatchMapping("/{studentId}/profile/lattes")
    public ResponseEntity<Void> updateLattesLink(
            @PathVariable Long studentId,
            @RequestBody UpdateLattesLinkDto updateLattesLinkDto
    ) {
        studentService.updateLattesLink(studentId, updateLattesLinkDto);
        return ResponseEntity.ok(null);
    }
}