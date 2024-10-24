package br.com.mestradousp.gerenciadorformularios.model;

import br.com.mestradousp.gerenciadorformularios.enums.Opinions;
import br.com.mestradousp.gerenciadorformularios.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CcpEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String reviewerName;

    @Enumerated(EnumType.STRING)
    private Roles roles; 

    @NotNull
    private String studentName;

    @NotBlank
    private String performanceFeedback;

    @Enumerated(EnumType.STRING)
    private Opinions opinions;

    @ManyToOne
    private EvaluationForm evaluationForm;
}
