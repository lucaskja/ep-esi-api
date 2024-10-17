package br.com.mestradousp.gerenciadorformularios.service;


import br.com.mestradousp.gerenciadorformularios.dto.login.LoginResponseDto;
import br.com.mestradousp.gerenciadorformularios.exception.NotFoundException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
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
    private static final Integer EXPIRATION_TIME_IN_MINUTES = 5;

    public LoginResponseDto generateToken(UserDetails user) {
        Algorithm algorithm = Algorithm.HMAC256(privateKey);

        String token = JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getUsername())
                .withExpiresAt(this.getExpirationDate())
                .sign(algorithm);

        return new LoginResponseDto(token, EXPIRATION_TIME_IN_MINUTES);
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

