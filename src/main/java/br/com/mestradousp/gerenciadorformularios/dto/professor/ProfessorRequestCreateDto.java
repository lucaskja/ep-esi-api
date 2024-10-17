package br.com.mestradousp.gerenciadorformularios.dto.professor;

import br.com.mestradousp.gerenciadorformularios.enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorRequestCreateDto(
        @NotBlank
        String name,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotNull
        Roles role,

        @NotBlank
        String uspNumber
) {
}
