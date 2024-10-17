package br.com.mestradousp.gerenciadorformularios.model;

import br.com.mestradousp.gerenciadorformularios.enums.Opinions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
    private String academicEventsResume;

    @Column(columnDefinition = "TEXT")
    private String researchResume;

    @Column(columnDefinition = "TEXT")
    private String studentObservation;

    @Column(columnDefinition = "TEXT")
    private String professorOpinion;

    private Opinions professorFinalOpinion;

    @Column(columnDefinition = "TEXT")
    private String ccpOpinion;

    private Opinions ccpFinalOpinion;

    private Boolean hasDifficult;

    @JsonIgnoreProperties("performanceReports")
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
