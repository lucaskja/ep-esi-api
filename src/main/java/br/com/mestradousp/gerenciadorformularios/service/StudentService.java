package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.login.RegisterRequestDto;
import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.enums.Roles;
import br.com.mestradousp.gerenciadorformularios.enums.StudentStatus;
import br.com.mestradousp.gerenciadorformularios.exception.ConflictException;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.model.StudentInformation;
import br.com.mestradousp.gerenciadorformularios.repository.StudentRepository;
import br.com.mestradousp.gerenciadorformularios.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ProfessorService professorService;
    private final StudentInformationService studentInformationService;
    private final PasswordEncoder passwordEncoder = new PasswordEncoder();

    public void createStudent(RegisterRequestDto dto) {
        this.findStudentByEmail(dto.email()).ifPresent(student -> {
            throw new ConflictException("Student already exists");
        });

        Professor professor = this.professorService.findProfessorById(dto.professorId())
                .orElseThrow(() -> new NotFoundException("Professor not found"));

        Student student = Student.builder()
                .email(dto.email())
                .password(this.passwordEncoder.encode(dto.password()))
                .uspNumber(dto.uspNumber())
                .professor(professor)
                .loginStatus(LoginStatus.PENDENT)
                .role(Roles.STUDENT)
                .build();

        StudentInformation studentInformation = this.studentInformationService
                .createStudentInformation(StudentInformation.builder()
                        .name(dto.name())
                        .dob(dto.dob())
                        .birthPlace(dto.birthPlace())
                        .nationality(dto.nationality())
                        .program(dto.program())
                        .lattes(dto.lattes())
                        .status(StudentStatus.ENROLLED)
                        .build()
                );

        student.setStudentInformation(studentInformation);

        this.studentRepository.save(student);
    }

    public Optional<Student> findStudentByEmail(String email) {
        return this.studentRepository.findByEmail(email);
    }

    public Optional<Student> findStudentById(Long id) {
        return this.studentRepository.findById(id);
    }

    public void approveStudent(Long id) {
        Student student = this.studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        student.setLoginStatus(LoginStatus.APPROVED);

        this.studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return this.studentRepository.save(student);
    }
}
