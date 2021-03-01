package de.rottstegge.restmenu.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import de.rottstegge.restmenu.model.JwtResponse;
import de.rottstegge.restmenu.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import static de.rottstegge.restmenu.web.filter.JWTConstants.TOKEN_TYPE;

@Service
public class JwtServiceImpl implements JwtService {

    private final String secretKey;
    private long expirationSeconds;

    public JwtServiceImpl(@Value("${application.security.jwt.secret}") String secretKey,
                          @Value("${application.security.jwt.expiration-seconds}") long expirationSeconds) {
        this.secretKey = secretKey;
        this.expirationSeconds = expirationSeconds;
    }

    @Override
    public JwtResponse create(String username) {
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (expirationSeconds * 1000)))
                .sign(Algorithm.HMAC512(secretKey));

        return new JwtResponse(token, TOKEN_TYPE.toLowerCase(), expirationSeconds);
    }

    @Override
    public DecodedJWT verify(String jwt) {
        return JWT.require(Algorithm.HMAC512(secretKey.getBytes()))
                .build()
                .verify(jwt.replace(TOKEN_TYPE + " ", ""));
    }
}
