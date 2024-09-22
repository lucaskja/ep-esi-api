package br.com.mestradousp.gerenciadorformularios.model;

import br.com.mestradousp.gerenciadorformularios.enums.PerformanceReportStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

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

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String studentText;

    @Column(columnDefinition = "TEXT")
    private String ccpText;

    @Column(columnDefinition = "TEXT")
    private String ccpOpinion;

    @NotNull
    private PerformanceReportStatus status;

    @CreationTimestamp
    private LocalDate createdAt;

    private Boolean hasDifficult;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
