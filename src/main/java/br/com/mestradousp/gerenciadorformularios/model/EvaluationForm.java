package br.com.mestradousp.gerenciadorformularios.model;

import br.com.mestradousp.gerenciadorformularios.enums.EvaluationStatus;
import br.com.mestradousp.gerenciadorformularios.enums.Opinions;
import br.com.mestradousp.gerenciadorformularios.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evaluation_form")
public class EvaluationForm {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor reviewer; 

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; 

    @NotBlank
    private String performanceReview; 

    @Enumerated(EnumType.STRING)
    private Opinions opinions;  

    @Enumerated(EnumType.STRING)
    private EvaluationStatus status; 

    private boolean reviewedByProfessor;

    public boolean isReviewedByProfessor() {
        return this.reviewedByProfessor;
    }
}
