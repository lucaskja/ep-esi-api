package br.com.mestradousp.gerenciadorformularios.dto.login;

public record LoginResponseDto(
        Long userId,
        String accessToken,
        Integer expiresInMinutes
) {
}
