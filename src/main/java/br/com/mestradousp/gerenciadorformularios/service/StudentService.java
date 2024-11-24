package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.login.RegisterRequestDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.GetStudentDto;
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

import java.util.List;
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

    public List<Student> getStudentWithPerformanceReport() {
        return this.studentRepository.findStudentsWithZeroPerformanceReport();
    }

    public List<GetStudentDto> getAllStudents() {
        return this.studentRepository.findAll().stream()
                .map(student -> (
                    GetStudentDto.builder()
                            .studentName(student.getStudentInformation().getName())
                            .studentId(student.getId())
                            .uspNumber(student.getUspNumber())
                            .reports(student.getPerformanceReports())
                            .build()
                    )
                )
                .toList();
    }

    public StudentProfileDto getStudentProfile(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        return StudentProfileDto.builder()
                .studentId(student.getId())
                .uspNumber(student.getUspNumber())
                .name(student.getStudentInformation().getName())
                .email(student.getEmail())
                .dob(student.getStudentInformation().getDob())
                .birthPlace(student.getStudentInformation().getBirthPlace())
                .nationality(student.getStudentInformation().getNationality())
                .course(student.getStudentInformation().getProgram().toString())
                .professor(student.getProfessor().getName())
                .lattes(student.getStudentInformation().getLattes())
                .enrollmentDate(student.getStudentInformation().getCreatedAt())
                .qualificationExamApprovalDate(student.getExam().getQualificationExamDate())
                .languageProficiencyApprovalDate(student.getExam().getLanguageProficiencyExamDate())
                .finalWorkDepositDeadline(student.getExam().getAssigmentDeadline())
                .approvedCourses(studentCourseService.getApprovedCourses(studentId))
                .failedCourses(studentCourseService.getFailedCourses(studentId))
                .build();
    }

    public List<GetStudentDto> getStudentsByProfessorId(Long professorId) {
        return this.studentRepository.findByProfessorId(professorId).stream().
                map(student ->
                    GetStudentDto.builder()
                            .studentId(student.getId())
                            .uspNumber(student.getUspNumber())
                            .studentName(student.getStudentInformation().getName())
                            .reports(student.getPerformanceReports())
                            .build()
                )
                .toList();
    }

    public GetStudentDto getStudentByProfessorIdAndStudentId(Long professorId, Long studentId) {
        Student student = this.studentRepository.findByProfessorIdAndStudentId(professorId, studentId);

        return GetStudentDto.builder()
                .studentId(student.getId())
                .uspNumber(student.getUspNumber())
                .studentName(student.getStudentInformation().getName())
                .reports(student.getPerformanceReports())
                .build();
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

    public void updateLattesLink(
            Long studentId,
            UpdateLattesLinkDto updateLattesLinkDto
    ) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        student.getStudentInformation().setLattes(updateLattesLinkDto.lattesLink());
        studentRepository.save(student);
    }
}
