package br.com.mestradousp.gerenciadorformularios.dto;

import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class UserResponseUpdateDto {
    private String uspNumber;
    private String name;
    private LoginStatus loginStatus;
}
