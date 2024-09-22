package br.com.mestradousp.gerenciadorformularios.dto.student;

import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorResponseDto;
import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;

public record StudentResponseUpdateDto(
        String uspNumber,
        String name,
        ProfessorResponseDto professor,
        LoginStatus loginStatus
) {
}
