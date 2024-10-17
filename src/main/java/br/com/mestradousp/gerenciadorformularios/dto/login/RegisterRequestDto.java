package br.com.mestradousp.gerenciadorformularios.dto.login;

import br.com.mestradousp.gerenciadorformularios.enums.Programs;
import br.com.mestradousp.gerenciadorformularios.enums.Roles;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterRequestDto(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String password,

        @NotBlank
        String uspNumber,

        @NotNull
        @Enumerated(EnumType.STRING)
        Roles role,

        @NotBlank
        String name,

        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dob,

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
        Long professorId
) {
}
