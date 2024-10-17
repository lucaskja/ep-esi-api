package br.com.mestradousp.gerenciadorformularios.dto.login;

public record LoginResponseDto(
        String accessToken,
        Integer expiresInMinutes
) {
}
