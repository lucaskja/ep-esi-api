package br.com.mestradousp.gerenciadorformularios.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String tag;

    @NotBlank
    private String name;

    @NotNull
    @Min(2005)
    private Integer year;

    @NotNull
    @Min(1)
    private Integer semester;

    @ManyToOne
    @JsonIgnoreProperties("taughtCourses")
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;
}
