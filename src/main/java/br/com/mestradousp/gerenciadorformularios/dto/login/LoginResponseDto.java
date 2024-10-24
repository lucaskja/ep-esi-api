package br.com.mestradousp.gerenciadorformularios.dto.login;

import br.com.mestradousp.gerenciadorformularios.enums.Roles;

public record LoginResponseDto(
        Long userId,
        Roles role,
        String accessToken,
        Integer expiresInMinutes
) {
}
