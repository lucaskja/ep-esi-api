package br.com.mestradousp.gerenciadorformularios;

import br.com.mestradousp.gerenciadorformularios.dto.student.StudentProfileDto;
import br.com.mestradousp.gerenciadorformularios.enums.Programs;
import br.com.mestradousp.gerenciadorformularios.enums.StudentStatus;
import br.com.mestradousp.gerenciadorformularios.service.StudentCourseService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentCourseService studentCourseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configurando as informações do estudante
        StudentInformation studentInformation = new StudentInformation();
        studentInformation.setName("John Doe");
        studentInformation.setProgram(Programs.MASTER);
        studentInformation.setDob(LocalDate.of(1990, 1, 1));
        studentInformation.setBirthPlace("São Paulo");
        studentInformation.setNationality("Brasileiro");
        studentInformation.setLattes("http://lattes.cnpq.br/123456789");
        studentInformation.setStatus(StudentStatus.ENROLLED);

        // Configurando o professor
        Professor professor = new Professor();
        professor.setName("Dr. Smith");

        // Configurando o exame
        Exam exam = new Exam();
        exam.setQualificationExamDate(LocalDate.of(2023, 6, 15)); 
        exam.setLanguageProficiencyExamDate(LocalDate.of(2023, 3, 10));

        // Configurando o estudante
        Student student = new Student();
        student.setStudentInformation(studentInformation);
        student.setEmail("email@exemplo.com");
        student.setProfessor(professor);
        student.setExam(exam);

        // Simulando o retorno do repositório para o teste
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        // Simulando o retorno dos cursos aprovados e reprovados
        when(studentCourseService.getApprovedCourses(anyLong())).thenReturn(List.of("Course 1", "Course 2"));
        when(studentCourseService.getFailedCourses(anyLong())).thenReturn(List.of("Course 3"));
    }

    @Test
    public void testGetStudentProfile_Success() {
        // Testa o método getStudentProfile
        StudentProfileDto result = studentService.getStudentProfile(1L, "email@exemplo.com");

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testGetStudentProfile_NotFound() {
        // Configura o mock para retornar vazio (simulando não encontrado)
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Verifica se a exceção NotFoundException é lançada
        assertThrows(NotFoundException.class, () -> {
            studentService.getStudentProfile(1L, "test@student.com");
        });
    }
}