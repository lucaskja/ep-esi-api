package br.com.mestradousp.gerenciadorformularios;

import br.com.mestradousp.gerenciadorformularios.controller.CcpController;
import br.com.mestradousp.gerenciadorformularios.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CcpControllerTest {
    @Mock
    private StudentService studentService;

    @InjectMocks
    private CcpController ccpController;

    CcpControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldApproveStudent() {
        // Mockando
        Long studentId = 1L;
        doNothing().when(studentService).approveStudent(studentId);

        // Ação
        ResponseEntity<Void> response = ccpController.approveStudent(studentId);

        // Verificação
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(studentService, times(1)).approveStudent(studentId);
    }

}
