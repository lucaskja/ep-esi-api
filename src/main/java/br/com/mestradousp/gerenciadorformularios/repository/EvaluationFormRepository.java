package br.com.mestradousp.gerenciadorformularios.repository;

import br.com.mestradousp.gerenciadorformularios.model.EvaluationForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvaluationFormRepository extends JpaRepository<EvaluationForm, Long> {
    Optional<EvaluationForm> findByStudentId(Long studentId);
}
