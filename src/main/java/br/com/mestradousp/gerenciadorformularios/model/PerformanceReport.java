package br.com.mestradousp.gerenciadorformularios.model;

import br.com.mestradousp.gerenciadorformularios.enums.Opinions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
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

    @Enumerated(EnumType.STRING)
    private Opinions professorFinalOpinion;

    @Column(columnDefinition = "TEXT")
    private String ccpOpinion;

    @Enumerated(EnumType.STRING)
    private Opinions ccpFinalOpinion;

    @JsonIgnoreProperties("performanceReports")
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
