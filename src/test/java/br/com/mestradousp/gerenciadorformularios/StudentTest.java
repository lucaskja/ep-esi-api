package br.com.mestradousp.gerenciadorformularios;

import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.enums.Roles;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.model.StudentInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private Student student;

    @BeforeEach
    public void setUp() {
        // Inicializando a instância de StudentInformation com dados
        StudentInformation studentInformation = new StudentInformation();
        studentInformation.setName("Student Name");

        // Usando o Builder para criar o Student com os parâmetros necessários
        student = Student.builder()
                .uspNumber("123456")
                .email("student@example.com")
                .password("password123")
                .loginStatus(LoginStatus.APPROVED)
                .role(Roles.STUDENT)
                .studentInformation(studentInformation)
                .build();
    }

    @Test
    public void testStudentInformationCreation() {
        // Verificando se o nome foi corretamente configurado em StudentInformation
        assertEquals("Student Name", student.getStudentInformation().getName());
    }

    @Test
    public void testStudentUspNumber() {
        // Verificando se o número USP foi corretamente atribuído
        assertEquals("123456", student.getUspNumber());
    }

    @Test
    public void testStudentEmail() {
        // Verificando se o email foi corretamente atribuído
        assertEquals("student@example.com", student.getEmail());
    }

    @Test
    public void testStudentPassword() {
        // Verificando se a senha foi corretamente atribuída
        assertEquals("password123", student.getPassword());
    }

    @Test
    public void testStudentLoginStatus() {
        // Verificando se o status de login foi corretamente atribuído
        assertEquals(LoginStatus.APPROVED, student.getLoginStatus());
    }

    @Test
    public void testStudentRole() {
        // Verificando se o papel (role) do student foi atribuído corretamente
        assertEquals(Roles.STUDENT, student.getRole());
    }

    @Test
    public void testIsAccountEnabled() {
        // Verificando se o isEnabled retorna true quando o loginStatus é "APPROVED"
        assertTrue(student.isEnabled());
    }

    @Test
    public void testIsAccountNonExpired() {
        // Verificando se o isAccountNonExpired retorna true
        assertTrue(student.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        // Verificando se o isAccountNonLocked retorna true
        assertTrue(student.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        // Verificando se isCredentialsNonExpired retorna true
        assertTrue(student.isCredentialsNonExpired());
    }

    @Test
    public void testGetAuthorities() {
        // Verificando se getAuthorities retorna o papel correto
        List<String> authorities = student.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        assertTrue(authorities.contains("ROLE_STUDENT"));
    }
}
