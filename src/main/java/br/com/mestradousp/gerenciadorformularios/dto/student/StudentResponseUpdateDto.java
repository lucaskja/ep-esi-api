package br.com.mestradousp.gerenciadorformularios.dto.student;

import br.com.mestradousp.gerenciadorformularios.dto.UserResponseUpdateDto;
import br.com.mestradousp.gerenciadorformularios.dto.professor.ProfessorResponseDto;
import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseUpdateDto extends UserResponseUpdateDto {
    private ProfessorResponseDto professor;

    public StudentResponseUpdateDto(String uspNumber, String name, ProfessorResponseDto professor, LoginStatus loginStatus) {
        super(uspNumber, name, loginStatus);
        this.professor = professor;
    }
}
