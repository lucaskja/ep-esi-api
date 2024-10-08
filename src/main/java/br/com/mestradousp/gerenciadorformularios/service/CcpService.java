package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentResponseDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.user.UpdateUserDto;
import br.com.mestradousp.gerenciadorformularios.dto.util.ProfessorMapper;
import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CcpService {
    private final StudentService studentService;
    private final ProfessorService professorService;

    @Autowired
    public CcpService(StudentService studentService, ProfessorService professorService) {
        this.studentService = studentService;
        this.professorService = professorService;
    }

    public ProfessorResponseUpdateDto updateProfessorLogin(UpdateUserDto dto) {
        Optional<Professor> professorOptional = professorService.findProfessorById(dto.uspNumber());

        if (professorOptional.isPresent()) {
            Professor professor = professorOptional.get();
            professorService.updateLogin(professor, dto.status());
            return new ProfessorResponseUpdateDto(professor.getUspNumber(), professor.getName(), dto.status());
        }

        throw new NotFoundException("User not found with USP number: " + dto.uspNumber());
    }

    public StudentResponseUpdateDto updateStudentLogin(UpdateUserDto dto) {
        Optional<Student> studentOptional = studentService.findStudentById(dto.uspNumber());

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            studentService.updateLogin(student, dto.status());

            return new StudentResponseUpdateDto(
                    student.getUspNumber(),
                    student.getName(),
                    ProfessorMapper.toResponseDto(student.getProfessor()),
                    dto.status()
            );
        }

        throw new NotFoundException("User not found with USP number: " + dto.uspNumber());
    }

    public List<StudentResponseDto> getAllPendingStudents(LoginStatus status) {
        return this.studentService.getAllPendingStudents(status);
    }
}
