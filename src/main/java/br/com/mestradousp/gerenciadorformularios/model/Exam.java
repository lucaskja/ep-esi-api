package br.com.mestradousp.gerenciadorformularios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate qualificationExamDate;

    private LocalDate proficientExamDate;

    @NotNull
    private LocalDate finalAssigmentDeadline;

    @NotNull
    private LocalDate qualificationExamDeadline;

    @Min(0)
    private Integer writingArticles;

    @Min(0)
    private Integer reviewingArticles;

    @Min(0)
    private Integer approvedArticles;
}
