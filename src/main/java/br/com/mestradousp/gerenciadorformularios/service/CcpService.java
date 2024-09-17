package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.UserResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.user.UpdateUserDto;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CcpService {
    private final StudentService studentService;
    private final ProfessorService professorService;

    @Autowired
    public CcpService(StudentService studentService, ProfessorService professorService) {
        this.studentService = studentService;
        this.professorService = professorService;
    }

    public UserResponseUpdateDto updateUserLogin(UpdateUserDto dto) {
        Professor professor = this.professorService.findProfessorById(dto.getUspNumber());
        Student student = this.studentService.findStudentById(dto.getUspNumber());

        if (professor != null) return this.professorService.updateLogin(professor, dto.getStatus());

        if (student != null) return this.studentService.updateLogin(student, dto.getStatus());

        throw new NotFoundException("User not found");
    }
}
