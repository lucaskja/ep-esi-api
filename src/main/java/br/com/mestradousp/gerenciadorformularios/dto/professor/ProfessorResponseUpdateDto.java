package br.com.mestradousp.gerenciadorformularios.dto.professor;

import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;

public record ProfessorResponseUpdateDto (
        String uspNumber,
        String name,
        LoginStatus loginStatus
) {
}
