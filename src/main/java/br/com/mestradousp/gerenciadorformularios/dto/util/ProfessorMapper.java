package br.com.mestradousp.gerenciadorformularios.dto.util;

import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorResponseDto;
import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.model.Professor;

public abstract class ProfessorMapper {
    public static ProfessorResponseUpdateDto toResponseUpdateDto(Professor model) {
        return new ProfessorResponseUpdateDto(
                model.getUspNumber(),
                model.getName(),
                model.getLoginStatus()
        );
    };

    public static ProfessorResponseDto toResponseDto(Professor model) {
        return new ProfessorResponseDto(
                model.getUspNumber(),
                model.getName()
        );
    };
}
