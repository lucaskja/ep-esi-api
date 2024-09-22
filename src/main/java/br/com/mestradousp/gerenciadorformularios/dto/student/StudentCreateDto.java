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

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StudentCreateDto (
    @NotBlank
    @Size(min = 8, max = 8)
    String uspNumber,

    @NotBlank
    String name,

    @NotBlank
    @Email
    String email,

    @NotNull
    LocalDate dob,

    @NotBlank
    String documentNumber,

    @NotBlank
    String birthPlace,

    @NotBlank
    String nationality,

    @NotNull
    @Enumerated(EnumType.STRING)
    Programs program,

    @NotBlank
    String lattes,

    @NotNull
    LocalDate registrationDate,

    @NotNull
    @Enumerated(EnumType.STRING)
    StudentStatus status,

    @NotNull
    @Enumerated(EnumType.STRING)
    LoginStatus loginStatus,

    @NotBlank
    @Size(min = 8, max = 8)
    String professorUspNumber
) {}
