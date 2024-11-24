package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.enums.EvaluationStatus;
import br.com.mestradousp.gerenciadorformularios.enums.Opinions;
import br.com.mestradousp.gerenciadorformularios.enums.Roles;
import br.com.mestradousp.gerenciadorformularios.exception.ConflictException;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.exception.UnauthorizedException;
import br.com.mestradousp.gerenciadorformularios.model.EvaluationForm;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import br.com.mestradousp.gerenciadorformularios.model.Student;
import br.com.mestradousp.gerenciadorformularios.repository.EvaluationFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationFormService {

    private final EvaluationFormRepository evaluationFormRepository;
    private final ProfessorService professorService;
    private final StudentService studentService;

    public void submitCCPEvaluation(Long studentId, Long professorId, String review, Roles role, Opinions opinions) {
        Student student = studentService.findStudentById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        Professor professor = professorService.findProfessorById(professorId)
                .orElseThrow(() -> new NotFoundException("Professor not found"));

        Optional<EvaluationForm> existingEvaluation = evaluationFormRepository.findByStudentId(studentId);
        if (existingEvaluation.isEmpty() || existingEvaluation.get().getRoles() != Roles.PROFESSOR) {
            throw new ConflictException("Docente ainda não realizou a avaliação");
        }

        EvaluationForm evaluationForm = EvaluationForm.builder()
                .student(student)
                .reviewer(professor)
                .roles(role)
                .performanceReview(review)
                .opinions(opinions)
                .status(EvaluationStatus.APPROVED_BY_CCP)
                .build();

        evaluationFormRepository.save(evaluationForm);
    }

    public String getEvaluationStatus(Long formId, Long studentId) {
        EvaluationForm form = evaluationFormRepository.findById(formId)
                .orElseThrow(() -> new NotFoundException("Formulário não encontrado"));

        if (!form.getStudent().getId().equals(studentId)) {
            throw new UnauthorizedException("Acesso negado: o formulário não pertence a este discente.");
        }

        return form.getStatus().getDescription();
    }
}
