package br.com.mestradousp.gerenciadorformularios.dto.professor;

import lombok.Builder;

@Builder
public record GetProfessorDto(
        Long professorId,
        String professorName
) {
}
