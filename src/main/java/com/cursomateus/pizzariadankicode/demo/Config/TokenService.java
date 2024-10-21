package com.cursomateus.pizzariadankicode.demo.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cursomateus.pizzariadankicode.demo.user.Usuario;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String createToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("1234");
            LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(20);

            return JWT.create().withIssuer("Danki Code Pizzaria")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(expirationDate.toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);

        } catch (JWTCreationException ex) {
            throw new RuntimeException("Erro ao criar token: " + ex.getMessage());
        }
    }

    public String getUserToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("1234");

            return JWT.require(algorithm)
                    .withIssuer("Danki Code Pizzaria")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException ex) {
            throw new RuntimeException("Token inválido");
        }
    }
}
