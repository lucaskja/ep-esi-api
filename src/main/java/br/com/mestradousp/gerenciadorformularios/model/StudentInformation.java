package br.com.mestradousp.gerenciadorformularios.model;

import br.com.mestradousp.gerenciadorformularios.enums.Programs;
import br.com.mestradousp.gerenciadorformularios.enums.StudentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_information")
public class StudentInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate dob;

    @NotBlank
    private String birthPlace;

    @NotBlank
    private String nationality;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Programs program;

    @NotBlank
    private String lattes;

    @CreationTimestamp
    private LocalDate createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StudentStatus status;
}
