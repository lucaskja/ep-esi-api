package br.com.mestradousp.gerenciadorformularios.dto.professor;

import br.com.mestradousp.gerenciadorformularios.dto.UserResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public class ProfessorResponseUpdateDto extends UserResponseUpdateDto {
    public ProfessorResponseUpdateDto(String uspNumber, String name, LoginStatus loginStatus) {
        super(uspNumber, name, loginStatus);
    }
}
