package br.com.mestradousp.gerenciadorformularios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.mestradousp.gerenciadorformularios.enums.EvaluationStatus;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.exception.UnauthorizedException;
import br.com.mestradousp.gerenciadorformularios.model.EvaluationForm;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.repository.EvaluationFormRepository;
import br.com.mestradousp.gerenciadorformularios.service.EvaluationFormService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class EvaluationFormServiceTest {

    @Mock
    private EvaluationFormRepository evaluationFormRepository;

    @InjectMocks
    private EvaluationFormService evaluationFormService;

    public EvaluationFormServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEvaluationStatus_Success() {
        // Arrange
        Long formId = 1L;
        Long studentId = 123L;

        Student student = new Student();
        student.setId(studentId);

        EvaluationForm form = new EvaluationForm();
        form.setId(formId);
        form.setStudent(student);
        form.setStatus(EvaluationStatus.REVIEWED_BY_PROFESSOR);

        when(evaluationFormRepository.findById(formId)).thenReturn(Optional.of(form));

        // Act
        String status = evaluationFormService.getEvaluationStatus(formId, studentId);

        // Assert
        assertEquals("Reviewed by professor", status);
        verify(evaluationFormRepository, times(1)).findById(formId);
    }

    @Test
    public void testGetEvaluationStatus_FormNotFound() {
        // Arrange
        Long formId = 1L;
        Long studentId = 123L;

        when(evaluationFormRepository.findById(formId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> evaluationFormService.getEvaluationStatus(formId, studentId));
        verify(evaluationFormRepository, times(1)).findById(formId);
    }

    @Test
    public void testGetEvaluationStatus_UnauthorizedAccess() {
        // Arrange
        Long formId = 1L;
        Long studentId = 123L;
        Long anotherStudentId = 456L;

        Student anotherStudent = new Student();
        anotherStudent.setId(anotherStudentId);

        EvaluationForm form = new EvaluationForm();
        form.setId(formId);
        form.setStudent(anotherStudent);
        form.setStatus(EvaluationStatus.REVIEWED_BY_PROFESSOR);

        when(evaluationFormRepository.findById(formId)).thenReturn(Optional.of(form));

        // Act & Assert
        assertThrows(UnauthorizedException.class, () -> evaluationFormService.getEvaluationStatus(formId, studentId));
        verify(evaluationFormRepository, times(1)).findById(formId);
    }
}