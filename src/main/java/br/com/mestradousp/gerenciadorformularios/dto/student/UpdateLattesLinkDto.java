package br.com.mestradousp.gerenciadorformularios.dto.student;

import jakarta.validation.constraints.NotBlank;


public record UpdateLattesLinkDto(
        @NotBlank
        String lattesLink
) {
}