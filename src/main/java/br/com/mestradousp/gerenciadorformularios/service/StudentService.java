package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.student.StudentCreateDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentResponseDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.util.StudentMapper;
import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.exception.ConflictException;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ProfessorService professorService;

    @Autowired
    public StudentService(
            StudentRepository studentRepository,
            ProfessorService professorService

    ) {
        this.studentRepository = studentRepository;
        this.professorService = professorService;
    }

    public Student findStudentById(String id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    public void validateIfStudentExists(String id) {
        this.studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found"));
    }

    public void validateIfStudentIsAlreadyCreated(String id) {
        this.studentRepository.findById(id).ifPresent(a -> { throw new ConflictException("User already exists"); });
    }

    public StudentResponseDto createStudent(StudentCreateDto studentDto) {
        Professor advisor = professorService.findProfessorById(studentDto.getProfessorUspNumber());

        Student studentToSave = StudentMapper.toModel(studentDto, advisor);

        return StudentMapper.toResponseDto(studentRepository.save(studentToSave));
    }

    public StudentResponseUpdateDto updateLogin(Student student, LoginStatus status) {
        student.setLoginStatus(status);

        return StudentMapper.toResponseUpdateDto(this.studentRepository.save(student));
    }
}
