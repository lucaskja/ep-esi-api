package br.com.mestradousp.gerenciadorformularios.dto.student;

import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.enums.Programs;
import br.com.mestradousp.gerenciadorformularios.enums.StudentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentCreateDto {
    @NotBlank
    @Size(min = 8, max = 8)
    private String uspNumber;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private LocalDate dob;

    @NotBlank
    private String documentNumber;

    @NotBlank
    private String birthPlace;

    @NotBlank
    private String nationality;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Programs program;

    @NotBlank
    private String lattes;

    @NotNull
    private LocalDate registrationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LoginStatus loginStatus;

    @NotBlank
    @Size(min = 8, max = 8)
    private String professorUspNumber;
}
