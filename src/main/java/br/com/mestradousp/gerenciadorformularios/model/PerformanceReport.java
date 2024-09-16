package br.com.mestradousp.gerenciadorformularios.model;

import br.com.mestradousp.gerenciadorformularios.enums.PerformanceReportStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfomances_report")
public class PerformanceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String professorText;

    @Column(columnDefinition = "TEXT")
    private String professorOpinion;

    @Column(columnDefinition = "TEXT")
    private String studentText;

    @Column(columnDefinition = "TEXT")
    private String ccpText;

    @Column(columnDefinition = "TEXT")
    private String ccpOpinion;

    @NotNull
    private PerformanceReportStatus status;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
