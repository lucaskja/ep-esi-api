package br.com.mestradousp.gerenciadorformularios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate qualificationExamDate;

    @NotNull
    private LocalDate qualificationExamDeadline;

    private LocalDate languageProficiencyExamDate;

    @NotNull
    private LocalDate languageProficiencyDeadline;

    @NotNull
    private LocalDate assigmentDeadline;
}
