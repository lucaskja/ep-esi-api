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
import br.com.mestradousp.gerenciadorformularios.utils.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void validateIfStudentIsAlreadyCreated(String uspNumber) {
        new EntityValidator<>(
                studentRepository,
                () -> new NotFoundException("Student not found"),
                () -> new ConflictException("Student already exists")
        ).validateIfEntityAlreadyCreated(uspNumber);
    }

    public Student validateIfStudentExists(String uspNumber) {
        return new EntityValidator<>(
                studentRepository,
                () -> new NotFoundException("Student not found"),
                () -> new ConflictException("Student already exists")
        ).validateIfEntityExists(uspNumber);
    }

    public StudentResponseDto createStudent(StudentCreateDto studentDto) {
        this.validateIfStudentIsAlreadyCreated(studentDto.uspNumber());
        Professor advisor = professorService.validateProfessor(studentDto.professorUspNumber());

        Student studentToSave = StudentMapper.toModel(studentDto, advisor);

        return StudentMapper.toResponseDto(studentRepository.save(studentToSave));
    }

    public Optional<Student> findStudentById(String id) {
        return this.studentRepository.findById(id);
    }

    public List<StudentResponseDto> getAllPendingStudents(LoginStatus status) {
        return StudentMapper.toResponseDto(this.studentRepository.findAllByLoginStatusEquals(status));
    }

    public void updateLogin(Student student, LoginStatus status) {
        student.setLoginStatus(status);
        this.studentRepository.save(student);
    }

    public void updateStudent(Student student) {
        this.validateIfStudentExists(student.getUspNumber());

        this.studentRepository.save(student);
    }
}
