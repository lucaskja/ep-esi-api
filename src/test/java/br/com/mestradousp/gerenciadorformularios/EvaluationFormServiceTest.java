package br.com.mestradousp.gerenciadorformularios;

import br.com.mestradousp.gerenciadorformularios.enums.EvaluationStatus;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.exception.UnauthorizedException;
import br.com.mestradousp.gerenciadorformularios.model.EvaluationForm;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.repository.EvaluationFormRepository;
import br.com.mestradousp.gerenciadorformularios.service.EvaluationFormService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EvaluationFormServiceTest {

    private EvaluationFormService evaluationFormService;
    private EvaluationFormRepository evaluationFormRepository;

    @BeforeEach
    void setUp() {
        evaluationFormRepository = mock(EvaluationFormRepository.class);
        evaluationFormService = new EvaluationFormService(evaluationFormRepository, null, null);
    }

    @Test
    void shouldReturnCorrectStatus() {
        Long formId = 1L;
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);

        EvaluationForm form = EvaluationForm.builder()
                .id(formId)
                .student(student)
                .status(EvaluationStatus.REVIEWED_BY_PROFESSOR)
                .build();

        when(evaluationFormRepository.findById(formId)).thenReturn(Optional.of(form));

        String status = evaluationFormService.getEvaluationStatus(formId, studentId);

        assertEquals("Reviewed by professor", status);
        verify(evaluationFormRepository, times(1)).findById(formId);
    }

    @Test
    void shouldThrowExceptionIfFormDoesNotBelongToStudent() {
        Long formId = 1L;
        Long studentId = 1L;
        Long otherStudentId = 2L;

        Student otherStudent = new Student();
        otherStudent.setId(otherStudentId);

        EvaluationForm form = EvaluationForm.builder()
                .id(formId)
                .student(otherStudent)
                .status(EvaluationStatus.REVIEWED_BY_PROFESSOR)
                .build();

        when(evaluationFormRepository.findById(formId)).thenReturn(Optional.of(form));

        UnauthorizedException exception = assertThrows(UnauthorizedException.class, 
                () -> evaluationFormService.getEvaluationStatus(formId, studentId));

        assertEquals("Acesso negado: o formulário não pertence a este discente.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfFormNotFound() {
        Long formId = 1L;

        when(evaluationFormRepository.findById(formId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, 
                () -> evaluationFormService.getEvaluationStatus(formId, 1L));

        assertEquals("Formulário não encontrado", exception.getMessage());
    }
}
