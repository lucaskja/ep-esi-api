package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.login.RegisterRequestDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentProfileDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.UpdateLattesLinkDto;
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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ProfessorService professorService;
    private final StudentInformationService studentInformationService;
    private final StudentCourseService studentCourseService;
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

    public void updateStudent(Student student) {
        this.studentRepository.save(student);
    }

    // Método para buscar o perfil do aluno pelo ID
    public StudentProfileDto getStudentProfile(Long studentId, String authenticatedEmail) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        if (!student.getEmail().equals(authenticatedEmail)) {
            throw new ConflictException("You are not allowed to view this student's profile");
        }

        return StudentProfileDto.builder()
                .studentId(student.getId())
                .uspNumber(student.getUspNumber())
                .name(student.getName()) // Pega o nome do StudentInformation
                .email(student.getEmail())
                .dob(student.getDob()) // Data de nascimento do StudentInformation
                .birthPlace(student.getBirthPlace()) // Local de nascimento do StudentInformation
                .nationality(student.getNationality()) // Nacionalidade do StudentInformation
                .course(student.getProgram().toString()) // Programa (mestrado/doutorado) do StudentInformation
                .professor(student.getProfessor().getName()) // Nome do orientador
                .lattes(student.getLattes()) // Link para o Lattes do StudentInformation
                .enrollmentDate(student.getStudentInformation().getCreatedAt()) // Data de matrícula (createdAt da StudentInformation)
                .qualificationExamApprovalDate(student.getExam().getQualificationExamDate()) // Data de aprovação no exame de qualificação
                .languageProficiencyApprovalDate(student.getExam().getLanguageProficiencyExamDate()) // Data de aprovação no exame de proficiência
                .finalWorkDepositDeadline(student.getExam().getAssigmentDeadline()) // Data limite para depósito do trabalho final
                .approvedCourses(studentCourseService.getApprovedCourses(studentId)) // Lista de disciplinas aprovadas
                .failedCourses(studentCourseService.getFailedCourses(studentId)) // Lista de disciplinas reprovadas
                .build();
    }

    // Método para atualizar o link do Lattes
    public void updateLattesLink(Long studentId, UpdateLattesLinkDto updateLattesLinkDto, String authenticatedEmail) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        if (!student.getEmail().equals(authenticatedEmail)) {
            throw new ConflictException("You are not allowed to edit this student's profile");
        }

        student.setLattes(updateLattesLinkDto.getLattesLink());
        studentRepository.save(student);
    }
}
