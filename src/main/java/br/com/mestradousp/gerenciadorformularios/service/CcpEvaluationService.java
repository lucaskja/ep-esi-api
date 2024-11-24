package br.com.mestradousp.gerenciadorformularios.service;

import br.com.mestradousp.gerenciadorformularios.dto.ccp.CcpEvaluationDto;
import br.com.mestradousp.gerenciadorformularios.enums.EvaluationStatus;
import br.com.mestradousp.gerenciadorformularios.exception.ConflictException;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import br.com.mestradousp.gerenciadorformularios.model.CcpEvaluation;
import br.com.mestradousp.gerenciadorformularios.model.EvaluationForm;
import br.com.mestradousp.gerenciadorformularios.repository.CcpEvaluationRepository;
import br.com.mestradousp.gerenciadorformularios.repository.EvaluationFormRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CcpEvaluationService {

    private final CcpEvaluationRepository ccpEvaluationRepository;
    private final EvaluationFormRepository evaluationFormRepository;

    public CcpEvaluationService(CcpEvaluationRepository ccpEvaluationRepository, EvaluationFormRepository evaluationFormRepository) {
        this.ccpEvaluationRepository = ccpEvaluationRepository;
        this.evaluationFormRepository = evaluationFormRepository;
    }

    @Transactional
    public void submitCcpEvaluation(CcpEvaluationDto ccpEvaluationDto) {
        EvaluationForm form = evaluationFormRepository.findById(ccpEvaluationDto.getFormId())
            .orElseThrow(() -> new NotFoundException("Formulário não encontrado"));

        if (!form.isReviewedByProfessor()) {
            throw new ConflictException("O formulário ainda não foi avaliado pelo docente.");
        }

        String studentName = form.getStudent().getStudentInformation().getName();

        CcpEvaluation evaluation = CcpEvaluation.builder()
            .reviewerName(ccpEvaluationDto.getReviewerName())
            .roles(ccpEvaluationDto.getRoles())
            .studentName(studentName)
            .performanceFeedback(ccpEvaluationDto.getFeedback())
            .opinions(ccpEvaluationDto.getOpinions())
            .evaluationForm(form)
            .build();

        ccpEvaluationRepository.save(evaluation);
        form.setStatus(EvaluationStatus.APPROVED_BY_CCP);
        evaluationFormRepository.save(form);
    }
}
