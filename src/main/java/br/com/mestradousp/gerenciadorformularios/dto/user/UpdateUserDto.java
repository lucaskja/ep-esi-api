package br.com.mestradousp.gerenciadorformularios.dto.user;

import br.com.mestradousp.gerenciadorformularios.enums.LoginStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateUserDto {
    @NotBlank
    @Size(min = 8, max = 8)
    String uspNumber;

    @NotNull
    LoginStatus status;
}
