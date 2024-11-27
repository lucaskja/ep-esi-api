package br.com.mestradousp.gerenciadorformularios.service;


import br.com.mestradousp.gerenciadorformularios.dto.login.LoginResponseDto;
import br.com.mestradousp.gerenciadorformularios.enums.Roles;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class JwtTokenService {
    @Value("${jwt.private.key:myscret}")
    private String privateKey;

    private static final String ISSUER = "auth";
    private static final Integer EXPIRATION_TIME_IN_MINUTES = 30;

    public LoginResponseDto generateToken(UserDetails user, Long id, Roles role) {
        Algorithm algorithm = Algorithm.HMAC256(privateKey);

        String token = JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getUsername())
                .withExpiresAt(this.getExpirationDate())
                .sign(algorithm);

        return new LoginResponseDto(id, role, token, EXPIRATION_TIME_IN_MINUTES);
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(privateKey);

        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant getExpirationDate() {
        return LocalDateTime
                .now()
                .plusMinutes(EXPIRATION_TIME_IN_MINUTES)
                .atZone(ZoneId.systemDefault())
                .toInstant();
    }
}

