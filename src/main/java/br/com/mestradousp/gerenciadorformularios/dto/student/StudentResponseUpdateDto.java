package br.com.mestradousp.gerenciadorformularios.dto.student;

import br.com.mestradousp.gerenciadorformularios.dto.UserResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import br.com.mestradousp.gerenciadorformularios.model.Professor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseUpdateDto extends UserResponseUpdateDto {
    private Professor professor;

    public StudentResponseUpdateDto(String uspNumber, String name, Professor professor, LoginStatus loginStatus) {
        super(uspNumber, name, loginStatus);
        this.professor = professor;
    }
}
