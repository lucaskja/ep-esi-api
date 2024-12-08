package br.com.mestradousp.gerenciadorformularios;

import br.com.mestradousp.gerenciadorformularios.dto.login.RegisterRequestDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.StudentProfileDto;
import br.com.mestradousp.gerenciadorformularios.dto.student.UpdateLattesLinkDto;
import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.enums.Programs;
import br.com.mestradousp.gerenciadorformularios.enums.Roles;
import br.com.mestradousp.gerenciadorformularios.enums.StudentStatus;
import br.com.mestradousp.gerenciadorformularios.exception.ConflictException;
import br.com.mestradousp.gerenciadorformularios.service.ProfessorService;
import br.com.mestradousp.gerenciadorformularios.service.StudentCourseService;
import br.com.mestradousp.gerenciadorformularios.service.StudentInformationService;
import br.com.mestradousp.gerenciadorformularios.service.StudentService;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.Exam;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.model.StudentInformation;
import br.com.mestradousp.gerenciadorformularios.repository.StudentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentCourseService studentCourseService;

    @Mock
    private ProfessorService professorService;

    @Mock
    private StudentInformationService studentInformationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock para StudentInformation
        StudentInformation studentInformation = new StudentInformation();
        studentInformation.setName("John Doe");
        studentInformation.setProgram(Programs.MASTER);
        studentInformation.setDob(LocalDate.of(1990, 1, 1));
        studentInformation.setBirthPlace("São Paulo");
        studentInformation.setNationality("Brasileiro");
        studentInformation.setLattes("http://lattes.cnpq.br/123456789");
        studentInformation.setStatus(StudentStatus.ENROLLED);

        when(studentInformationService.createStudentInformation(any(StudentInformation.class)))
                .thenReturn(studentInformation);

        // Mock para Professor
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setName("Dr. Smith");
        when(professorService.findProfessorById(1L)).thenReturn(Optional.of(professor));

        // Mock para Exam
        Exam exam = new Exam();
        exam.setQualificationExamDate(LocalDate.of(2023, 6, 15));
        exam.setLanguageProficiencyExamDate(LocalDate.of(2023, 3, 10));

        // Mock para Student
        Student student = new Student();
        student.setStudentInformation(studentInformation);
        student.setEmail("email@exemplo.com");
        student.setProfessor(professor);
        student.setExam(exam);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        // Mock para StudentCourseService
        when(studentCourseService.getApprovedCourses(anyLong())).thenReturn(List.of("Course 1", "Course 2"));
        when(studentCourseService.getFailedCourses(anyLong())).thenReturn(List.of("Course 3"));
    }

    @Test
    public void testGetStudentProfile_Success() {
        StudentProfileDto result = studentService.getStudentProfile(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.name());
    }

    @Test
    void testGetStudentProfile_NotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> studentService.getStudentProfile(1L));
    }

    @Test
    public void testCreateStudent_Success() {
        RegisterRequestDto dto = createValidRegisterRequestDto();

        studentService.createStudent(dto);

        verify(studentRepository, times(1)).save(any(Student.class));
        verify(studentInformationService, times(1)).createStudentInformation(any(StudentInformation.class));
    }

    @Test
    public void testCreateStudent_Conflict() {
        RegisterRequestDto dto = createValidRegisterRequestDto();

        when(studentRepository.findByEmail(dto.email())).thenReturn(Optional.of(new Student()));

        assertThrows(ConflictException.class, () -> studentService.createStudent(dto));
    }

    @Test
    public void testApproveStudent_Success() {
        Student student = new Student();
        student.setId(1L);
        student.setLoginStatus(LoginStatus.PENDENT);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        studentService.approveStudent(1L);

        assertEquals(LoginStatus.APPROVED, student.getLoginStatus());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testApproveStudent_NotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> studentService.approveStudent(1L));
    }

    @Test
    public void testUpdateLattesLink_Success() {
        UpdateLattesLinkDto dto = new UpdateLattesLinkDto("http://lattes.cnpq.br/987654321");
        Student student = new Student();
        StudentInformation studentInformation = new StudentInformation();
        studentInformation.setLattes("http://lattes.cnpq.br/123456789");
        student.setStudentInformation(studentInformation);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        studentService.updateLattesLink(1L, dto);

        assertEquals("http://lattes.cnpq.br/987654321", student.getStudentInformation().getLattes());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testUpdateLattesLink_NotFound() {
        UpdateLattesLinkDto dto = new UpdateLattesLinkDto("http://lattes.cnpq.br/987654321");

        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> studentService.updateLattesLink(1L, dto));
    }

    private RegisterRequestDto createValidRegisterRequestDto() {
        return new RegisterRequestDto(
                "john.doe@example.com",
                "password",
                "123456",
                Roles.STUDENT,
                "John Doe",
                LocalDate.of(1990, 1, 1),
                "São Paulo",
                "Brazil",
                Programs.MASTER,
                "http://lattes.cnpq.br/123456789",
                1L
        );
    }
}
